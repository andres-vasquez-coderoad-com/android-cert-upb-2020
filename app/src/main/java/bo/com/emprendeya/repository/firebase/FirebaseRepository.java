package bo.com.emprendeya.repository.firebase;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import java.util.List;

import bo.com.emprendeya.model.Base;
import bo.com.emprendeya.model.Post;
import bo.com.emprendeya.model.users.User;
import bo.com.emprendeya.repository.firebase.auth.FirebaseAuthManager;
import bo.com.emprendeya.repository.firebase.db.FirebaseDbManager;
import bo.com.emprendeya.repository.firebase.storage.FirebaseStorageManager;

public class FirebaseRepository {
    private static final String LOG = FirebaseRepository.class.getSimpleName();
    private static FirebaseRepository instance;

    private FirebaseAuthManager auth;
    private FirebaseDbManager db;
    private FirebaseStorageManager storage;


    public static FirebaseRepository getInstance() {
        if (instance == null) {
            instance = new FirebaseRepository();
        }
        return instance;
    }

    public FirebaseRepository() {
        auth = new FirebaseAuthManager();
        db = new FirebaseDbManager();
        storage = new FirebaseStorageManager();
    }

    public LiveData<Base<User>> loginWithEmailPassword(String email, String password) {
        return auth.loginWithEmailPassword(email, password);
    }

    public LiveData<Base<User>> loginWithGoogle(String idToken) {
        return auth.loginWithGoogle(idToken);
    }

    public LiveData<Base<List<Post>>> getPopularPosts() {
        MutableLiveData<Base<List<Post>>> results = new MutableLiveData<>();
        return results;
    }

    public LiveData<Base<User>> getCurrentUser() {
        return auth.getCurrentUser();
    }

    public void signOut() {
        auth.signOut();
    }
}
