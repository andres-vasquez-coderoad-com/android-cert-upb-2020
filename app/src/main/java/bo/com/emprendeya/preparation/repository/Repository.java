package bo.com.emprendeya.preparation.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import bo.com.emprendeya.preparation.models.Base;
import bo.com.emprendeya.preparation.models.Post;
import bo.com.emprendeya.preparation.models.Startup;
import bo.com.emprendeya.preparation.models.users.User;
import bo.com.emprendeya.preparation.repository.api.ApiRepository;
import bo.com.emprendeya.preparation.repository.firebase.FirebaseRepository;
import bo.com.emprendeya.preparation.repository.local.LocalRepository;

public class Repository implements RepositoryImpl {
    private LocalRepository local;
    private ApiRepository apiRepository;

    public Repository(Application application) {
        local = new LocalRepository(application);
    }

    @Override
    public LiveData<Base<User>> loginWithEmailPassword(String email, String password) {
        return FirebaseRepository.getInstance().loginWithEmailPassword(email, password);
    }

    @Override
    public LiveData<Base<User>> loginWithGoogle() {
        return null;
    }

    @Override
    public LiveData<Base<List<Startup>>> getStartups(String category) {
        MutableLiveData<Base<List<Startup>>> results = new MutableLiveData<>();
        local.getStartups().observeForever(startups -> results.postValue(new Base<>(startups)));
        ApiRepository.getInstance().getStartups().observeForever(listBase -> {
            results.postValue(new Base<>(listBase.getData()));
            local.update(listBase.getData());
        });

        return results;
    }

    @Override
    public LiveData<Base<List<Post>>> getPopularPosts() {
        return null;
    }
}
