package bo.com.emprendeyalo.preparation.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import bo.com.emprendeyalo.preparation.models.Base;
import bo.com.emprendeyalo.preparation.models.users.User;
import bo.com.emprendeyalo.preparation.repository.Repository;
import bo.com.emprendeyalo.preparation.repository.RepositoryImpl;

public class LoginViewModel extends AndroidViewModel {

    private static final String LOG = LoginViewModel.class.getSimpleName();
    private RepositoryImpl repository;

    public LoginViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository(application);
    }

    public LiveData<Base<User>> loginWithEmailPassword(String email, String password) {
        return this.repository.loginWithEmailPassword(email, password);
    }

    public LiveData<Base<User>> loginWithGoogle(String idToken) {
        return this.repository.loginWithGoogle(idToken);
    }

    public LiveData<Base<User>> getCurrentUser() {
        return this.repository.getCurrentUser();
    }
}
