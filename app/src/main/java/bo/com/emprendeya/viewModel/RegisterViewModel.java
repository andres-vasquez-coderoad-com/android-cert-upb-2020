package bo.com.emprendeya.viewModel;

import android.app.Application;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import bo.com.emprendeya.model.Base;
import bo.com.emprendeya.model.users.User;
import bo.com.emprendeya.repository.Repository;
import bo.com.emprendeya.repository.RepositoryImpl;

public class RegisterViewModel extends AndroidViewModel {

    private RepositoryImpl repository;

    public RegisterViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository(application);
    }

    public LiveData<Base<User>> register(User user, Uri image) {
        return repository.registerUser(user, image);
    }
}
