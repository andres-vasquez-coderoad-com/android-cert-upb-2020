package bo.com.emprendeya.repository;

import android.net.Uri;

import androidx.lifecycle.LiveData;

import java.util.List;

import bo.com.emprendeya.model.Base;
import bo.com.emprendeya.model.Post;
import bo.com.emprendeya.model.Startup;
import bo.com.emprendeya.model.users.User;

public interface RepositoryImpl {
    /* ************************* Users **************************/
    LiveData<Base<User>> loginWithEmailPassword(String email, String password);

    LiveData<Base<User>> loginWithGoogle(String idToken);

    LiveData<Base<List<Startup>>> getStartups(String category);

    LiveData<Base<List<Post>>> getPopularPosts();

    LiveData<Base<User>> getCurrentUser();

    LiveData<Base<User>> registerUser(User user, Uri photo);

    void signOut();

    /* ************************* Posts **************************/

    LiveData<Base<String>> addPostToStartup(String uuidStartup, Post post, Uri image);

    LiveData<Base<List<Post>>> observeStartupPost(String uuidStartup);
}
