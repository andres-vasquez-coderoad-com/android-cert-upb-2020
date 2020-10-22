package bo.com.emprendeya.repository.firebase.auth;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import bo.com.emprendeya.model.Base;
import bo.com.emprendeya.model.users.User;
import bo.com.emprendeya.utils.Constants;
import bo.com.emprendeya.utils.FirebaseMapper;

public class FirebaseAuthManager {

    private FirebaseAuth mAuth;

    public FirebaseAuthManager() {
        mAuth = FirebaseAuth.getInstance();
    }

    public LiveData<Base<User>> getCurrentUser() {
        MutableLiveData<Base<User>> results = new MutableLiveData<>();
        FirebaseUser firebaseUser = mAuth.getCurrentUser();
        if (firebaseUser != null) {
            results.postValue(new Base<>(FirebaseMapper.firebaseUserToUser(firebaseUser)));
        }
        return results;
    }

    public LiveData<Base<User>> loginWithEmailPassword(String email, String password) {
        MutableLiveData<Base<User>> results = new MutableLiveData<>();
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser firebaseUser = mAuth.getCurrentUser();
                            results.postValue(new Base<>(FirebaseMapper.firebaseUserToUser(firebaseUser)));
                        } else {
                            results.postValue(new Base<>(Constants.ERROR_LOGIN, task.getException()));
                        }
                    }
                });

        return results;
    }

    public LiveData<Base<User>> registerUser(User user) {
        MutableLiveData<Base<User>> results = new MutableLiveData<>();
        mAuth.createUserWithEmailAndPassword(user.getEmail(), user.getPassword())
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser firebaseUser = mAuth.getCurrentUser();
                            results.postValue(new Base<>(FirebaseMapper.firebaseUserToUser(firebaseUser)));
                        } else {
                            if (task.getException() instanceof FirebaseAuthException) {
                                if (((FirebaseAuthException) task.getException()).getErrorCode().equals("ERROR_EMAIL_ALREADY_IN_USE")) {
                                    results.postValue(new Base<>(Constants.ERROR_REGISTER_EMAIL_ALREADY_EXISTS, task.getException()));
                                } else {
                                    results.postValue(new Base<>(Constants.ERROR_REGISTER, task.getException()));
                                }
                            } else {
                                results.postValue(new Base<>(Constants.ERROR_REGISTER, task.getException()));
                            }
                        }
                    }
                });
        return results;
    }

    public LiveData<Base<User>> loginWithGoogle(String idToken) {
        MutableLiveData<Base<User>> results = new MutableLiveData<>();
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        mAuth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
                    User user = FirebaseMapper.firebaseUserToUser(firebaseUser);
                    results.postValue(new Base<>(user));
                } else {
                    results.postValue(new Base<>(Constants.ERROR_LOGIN_GOOGLE, task.getException()));
                }
            }
        });
        return results;
    }

    public LiveData<Base<Boolean>> forgotPassword(String email) {
        MutableLiveData<Base<Boolean>> results = new MutableLiveData<>();
        this.mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    results.postValue(new Base<>(true));
                } else {
                    results.postValue(new Base<>(Constants.ERROR_SERVER, task.getException()));
                }
            }
        });
        return results;
    }

    public void signOut() {
        this.mAuth.signOut();
    }
}
