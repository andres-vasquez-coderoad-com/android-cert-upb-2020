package bo.com.emprendeya.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import bo.com.emprendeya.model.Base;
import bo.com.emprendeya.model.users.User;
import bo.com.emprendeya.repository.MockRepository;
import bo.com.emprendeya.repository.Repository;
import bo.com.emprendeya.repository.RepositoryImpl;

public class LoginViewModel extends AndroidViewModel {

    private RepositoryImpl repository;

    public LoginViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository(application);
    }

    public LiveData<Base<User>> loginWithEmailPassword(String email, String password) {
        return repository.loginWithEmailPassword(email, password);
    }

    public LiveData<Base<User>> loginWithGoogle(String idToken) {
        return repository.loginWithGoogle(idToken);
    }

    public LiveData<Base<User>> getCurrentUser() {
        return repository.getCurrentUser();
    }
}
