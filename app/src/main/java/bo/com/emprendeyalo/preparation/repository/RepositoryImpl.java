package bo.com.emprendeyalo.preparation.repository;

import androidx.lifecycle.LiveData;

import java.util.List;

import bo.com.emprendeyalo.preparation.models.Base;
import bo.com.emprendeyalo.preparation.models.Post;
import bo.com.emprendeyalo.preparation.models.Startup;
import bo.com.emprendeyalo.preparation.models.users.User;

public interface RepositoryImpl {
    LiveData<Base<User>> loginWithEmailPassword(String email, String password);

    LiveData<Base<User>> getCurrentUser();

    LiveData<Base<User>> loginWithGoogle(String idToken);

    LiveData<Base<List<Startup>>> getStartups(String category);

    LiveData<Base<List<Post>>> getPopularPosts();

    void logout();
}
