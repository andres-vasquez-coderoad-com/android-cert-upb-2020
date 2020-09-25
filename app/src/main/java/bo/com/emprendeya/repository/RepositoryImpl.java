package bo.com.emprendeya.repository;

import androidx.lifecycle.LiveData;

import bo.com.emprendeya.model.Base;
import bo.com.emprendeya.model.users.User;

public interface RepositoryImpl {
    LiveData<Base<User>> loginWithEmailPassword(String email, String password);

    LiveData<Base<User>> loginWithGoogle();
}
