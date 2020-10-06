package bo.com.emprendeya.repository;

import androidx.lifecycle.LiveData;

import java.util.List;

import bo.com.emprendeya.model.Base;
import bo.com.emprendeya.model.Post;
import bo.com.emprendeya.model.Startup;
import bo.com.emprendeya.model.users.User;

public class Repository implements RepositoryImpl {
    @Override
    public LiveData<Base<User>> loginWithEmailPassword(String email, String password) {
        return null;
    }

    @Override
    public LiveData<Base<User>> loginWithGoogle() {
        return null;
    }

    @Override
    public LiveData<Base<List<Startup>>> getStartups(String category) {
        return null;
    }

    @Override
    public LiveData<Base<List<Post>>> getPopularPosts() {
        return null;
    }
}
