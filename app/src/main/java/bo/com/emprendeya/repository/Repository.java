package bo.com.emprendeya.repository;

import androidx.lifecycle.LiveData;

import java.util.List;

import bo.com.emprendeya.model.Base;
import bo.com.emprendeya.model.Post;
import bo.com.emprendeya.model.Startup;
import bo.com.emprendeya.model.users.User;
import bo.com.emprendeya.repository.api.ApiRepository;
import bo.com.emprendeya.repository.firebase.FirebaseRepository;

public class Repository implements RepositoryImpl {
    @Override
    public LiveData<Base<User>> loginWithEmailPassword(String email, String password) {
        return FirebaseRepository.getInstance().loginWithEmailPassword(email, password);
    }

    @Override
    public LiveData<Base<User>> loginWithGoogle() {
        return FirebaseRepository.getInstance().loginWithGoogle();
    }

    @Override
    public LiveData<Base<List<Startup>>> getStartups(String category) {
        return ApiRepository.getInstance().getStartups();
    }

    @Override
    public LiveData<Base<List<Post>>> getPopularPosts() {
        return FirebaseRepository.getInstance().getPopularPosts();
    }
}
