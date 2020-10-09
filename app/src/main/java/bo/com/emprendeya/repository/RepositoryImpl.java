package bo.com.emprendeya.repository;

import androidx.lifecycle.LiveData;

import java.util.List;

import bo.com.emprendeya.models.Base;
import bo.com.emprendeya.models.Post;
import bo.com.emprendeya.models.Startup;
import bo.com.emprendeya.models.users.User;

public interface RepositoryImpl {
    LiveData<Base<User>> loginWithEmailPassword(String email, String password);

    LiveData<Base<User>> loginWithGoogle();

    LiveData<Base<List<Startup>>> getStartups(String category);

    LiveData<Base<List<Post>>> getPopularPosts();
}
