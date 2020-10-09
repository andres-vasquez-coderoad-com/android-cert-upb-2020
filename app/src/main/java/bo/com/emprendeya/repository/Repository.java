package bo.com.emprendeya.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import bo.com.emprendeya.models.Base;
import bo.com.emprendeya.models.Post;
import bo.com.emprendeya.models.Startup;
import bo.com.emprendeya.models.users.User;
import bo.com.emprendeya.repository.local.LocalRepository;

public class Repository implements RepositoryImpl {
    private LocalRepository local;

    public Repository(Application application) {
        local = new LocalRepository(application);
    }

    @Override
    public LiveData<Base<User>> loginWithEmailPassword(String email, String password) {
        return null;
    }

    @Override
    public LiveData<Base<User>> loginWithGoogle() {
        return null;
    }

    @Override
    public LiveData<Base<List<Startup>>> getStartups(String category) {
        return null;
    }

    @Override
    public LiveData<Base<List<Post>>> getPopularPosts() {
        return null;
    }
}
