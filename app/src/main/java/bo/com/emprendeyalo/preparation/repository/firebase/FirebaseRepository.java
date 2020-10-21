package bo.com.emprendeyalo.preparation.repository.firebase;

import androidx.lifecycle.LiveData;

import bo.com.emprendeyalo.preparation.models.Base;
import bo.com.emprendeyalo.preparation.models.users.User;
import bo.com.emprendeyalo.preparation.repository.firebase.authentication.FirebaseAuthentication;
import bo.com.emprendeyalo.preparation.repository.firebase.database.FirebaseDb;
import bo.com.emprendeyalo.preparation.repository.firebase.storage.FirebaseStorageManager;

public class FirebaseRepository {
    private static final String LOG = FirebaseRepository.class.getSimpleName();
    private static FirebaseRepository instance;

    private FirebaseAuthentication auth;
    private FirebaseDb db;
    private FirebaseStorageManager storage;

    public static FirebaseRepository getInstance() {
        if (instance == null) {
            instance = new FirebaseRepository();
        }
        return instance;
    }

    public FirebaseRepository() {
        auth = new FirebaseAuthentication();
        db = new FirebaseDb();
        storage = new FirebaseStorageManager();
    }

    public LiveData<Base<User>> loginWithEmailPassword(String email, String password) {
        return auth.loginWithEmail(email, password);
    }

    public LiveData<Base<User>> loginWithGoogle(String idToken) {
        return auth.loginWithGoogle(idToken);
    }

    public LiveData<Base<User>> getCurrentUser() {
        return auth.getCurrentUser();
    }

    public void logout() {
        auth.logout();
    }

    public void demoData() {
        db.demoData();
    }
}
