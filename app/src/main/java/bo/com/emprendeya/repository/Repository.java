package bo.com.emprendeya.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import bo.com.emprendeya.models.Base;
import bo.com.emprendeya.models.Post;
import bo.com.emprendeya.models.Startup;
import bo.com.emprendeya.models.users.User;
import bo.com.emprendeya.repository.api.ApiRepository;
import bo.com.emprendeya.repository.local.LocalRepository;

public class Repository implements RepositoryImpl {
    private LocalRepository local;
    private ApiRepository apiRepository;

    public Repository(Application application) {
        local = new LocalRepository(application);
    }

    @Override
    public LiveData<Base<User>> loginWithEmailPassword(String email, String password) {
        MutableLiveData<Base<User>> results = new MutableLiveData<>();
        User user = new User(email, password);
        user.setUuid("1");
        user.setDisplayName("Demo");
        results.setValue(new Base<>(user));
        return results;
    }

    @Override
    public LiveData<Base<User>> loginWithGoogle() {
        return null;
    }

    @Override
    public LiveData<Base<List<Startup>>> getStartups(String category) {
        return ApiRepository.getInstance().getStartups();
    }

    @Override
    public LiveData<Base<List<Post>>> getPopularPosts() {
        return null;
    }
}
