package bo.com.emprendeya.repository;

import androidx.lifecycle.LiveData;

import java.util.List;

import bo.com.emprendeya.model.Base;
import bo.com.emprendeya.model.Post;
import bo.com.emprendeya.model.Startup;
import bo.com.emprendeya.model.users.User;

public interface RepositoryImpl {
    LiveData<Base<User>> loginWithEmailPassword(String email, String password);

    LiveData<Base<User>> loginWithGoogle();

    LiveData<Base<List<Startup>>> getStartups(String category);

    LiveData<Base<List<Post>>> getPopularPosts();
}
