package bo.com.emprendeya.preparation.repository.firebase.authentication;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

import bo.com.emprendeya.preparation.models.Base;
import bo.com.emprendeya.preparation.models.users.User;
import bo.com.emprendeya.preparation.utils.FirebaseMapper;

public class FirebaseAuthentication {

    private FirebaseAuth mAuth;

    public FirebaseAuthentication() {
        this.mAuth = FirebaseAuth.getInstance();
    }

    public LiveData<Base<User>> getCurrentUser() {
        MutableLiveData<Base<User>> results = new MutableLiveData<>();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            results.postValue(new Base<>(FirebaseMapper.firebaseUserToUser(currentUser)));
        } else {
            results.postValue(new Base<>("Not valid", new NullPointerException("Not valid")));
        }
        return results;
    }

    public MutableLiveData<Base<User>> loginWithEmail(String email, String password) {
        MutableLiveData<Base<User>> results = new MutableLiveData<>();
        this.mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            results.postValue(new Base<>(FirebaseMapper.firebaseUserToUser(mAuth.getCurrentUser())));
                        } else {
                            results.postValue(new Base("login Failure",
                                    new NullPointerException()));
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        results.postValue(new Base<>("login.onFailure", e));
                    }
                });
        return results;
    }

    public LiveData<Base> register(String email, String password) {
        final MutableLiveData<Base> results = new MutableLiveData<>();
        this.mAuth.createUserWithEmailAndPassword(email, password);
        return results;
    }
}
