package bo.com.emprendeya.repository.firebase.db;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;

import bo.com.emprendeya.model.Base;
import bo.com.emprendeya.model.users.User;
import bo.com.emprendeya.utils.Constants;

public class FirebaseDbManager {

    private FirebaseDatabase db;

    public FirebaseDbManager() {
        db = FirebaseDatabase.getInstance();
    }

    public LiveData<Base<User>> updateUser(User user) {
        MutableLiveData<Base<User>> results = new MutableLiveData<>();
        String path = Constants.FIREBASE_PATH_USERS + "/" + user.getUuid();
        db.getReference(path).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    results.postValue(new Base<>(user));
                } else {
                    results.postValue(new Base<>(Constants.ERROR_REGISTER_DB, task.getException()));
                }
            }
        });
        return results;
    }
}
