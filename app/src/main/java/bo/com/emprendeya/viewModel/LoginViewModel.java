package bo.com.emprendeya.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import bo.com.emprendeya.models.Base;
import bo.com.emprendeya.models.users.User;
import bo.com.emprendeya.repository.MockRepository;
import bo.com.emprendeya.repository.RepositoryImpl;

public class LoginViewModel extends AndroidViewModel {

    private static final String LOG = LoginViewModel.class.getSimpleName();
    private RepositoryImpl repository;

    public LoginViewModel(@NonNull Application application) {
        super(application);
        repository = new MockRepository();
    }

    public LiveData<Base<User>> loginWithEmailPassword(String email, String password) {
        return this.repository.loginWithEmailPassword(email, password);
    }
}
