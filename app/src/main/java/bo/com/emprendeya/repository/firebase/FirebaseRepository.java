package bo.com.emprendeya.repository.firebase;

import android.net.Uri;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import java.util.Calendar;
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
        return registerAndUpdateDb(auth.loginWithEmailPassword(email, password), null, null);
    }

    public LiveData<Base<User>> loginWithGoogle(String idToken) {
        return registerAndUpdateDb(auth.loginWithGoogle(idToken), null, null);
    }

    public LiveData<Base<User>> register(User user, Uri image) {
        return registerAndUpdateDb(auth.registerUser(user), user, image);
    }

    private LiveData<Base<User>> registerAndUpdateDb(LiveData<Base<User>> registerFunction, User user, Uri image) {
        MutableLiveData<Base<User>> results = new MutableLiveData<>();
        registerFunction.observeForever(new Observer<Base<User>>() {
            @Override
            public void onChanged(Base<User> userBase) {
                if (userBase.isSuccess()) {
                    User registeredUser = userBase.getData();
                    registeredUser.setLastLogin(Calendar.getInstance().getTimeInMillis());
                    if (user != null && registeredUser.getPhoto().isEmpty()) {
                        registeredUser.setPhoto(user.getPhoto() != null ? user.getPhoto() : "");
                    }

                    if (image != null) {
                        storage.uploadUserPhoto(registeredUser, image).observeForever(stringBase -> {
                            if (stringBase.isSuccess()) {
                                //Register in database
                                registeredUser.setPhoto(stringBase.getData());
                                db.updateUser(registeredUser).observeForever(userBase1 -> results.postValue(userBase1));
                            } else {
                                results.postValue(new Base<>(stringBase.getErrorCode(), stringBase.getException()));
                            }
                        });
                    } else {
                        //Register in database
                        db.updateUser(registeredUser).observeForever(new Observer<Base<User>>() {
                            @Override
                            public void onChanged(Base<User> userBase) {
                                results.postValue(userBase);
                            }
                        });
                    }
                } else {
                    //Return results
                    results.postValue(userBase);
                }
            }
        });
        return results;
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

    public LiveData<Base<String>> addPostToStartup(String uuidStartup, Post post, Uri image) {
        MutableLiveData<Base<String>> results = new MutableLiveData<>();
        //Step 1
        db.addPostToStartup(uuidStartup, post).observeForever(uuidPost -> {
            if (uuidPost.isSuccess()) {
                //Step 2
                storage.uploadStartupPhoto(uuidPost.getData(), image).observeForever(photoUrl -> {
                    if (photoUrl.isSuccess()) {
                        //Step 3
                        db.updateStartupImage(uuidStartup, uuidPost.getData(), photoUrl.getData()).observeForever(resultBase -> {
                            if (resultBase.isSuccess()) {
                                results.postValue(uuidPost);
                            } else {
                                results.postValue(new Base<>(resultBase.getErrorCode(), resultBase.getException()));
                            }
                        });
                    } else {
                        results.postValue(photoUrl);
                    }
                });
            } else {
                results.postValue(uuidPost);
            }
        });
        return results;
    }

    public LiveData<Base<List<Post>>> observeStartupPost(String uuidStartup) {
        return db.observeStartupPost(uuidStartup);
    }
}
