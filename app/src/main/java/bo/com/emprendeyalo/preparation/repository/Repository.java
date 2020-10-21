package bo.com.emprendeyalo.preparation.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

import bo.com.emprendeyalo.preparation.models.Base;
import bo.com.emprendeyalo.preparation.models.Post;
import bo.com.emprendeyalo.preparation.models.Startup;
import bo.com.emprendeyalo.preparation.models.users.User;
import bo.com.emprendeyalo.preparation.repository.api.ApiRepository;
import bo.com.emprendeyalo.preparation.repository.firebase.FirebaseRepository;
import bo.com.emprendeyalo.preparation.repository.local.LocalRepository;

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
    public LiveData<Base<User>> getCurrentUser() {
        return FirebaseRepository.getInstance().getCurrentUser();
    }

    @Override
    public LiveData<Base<User>> loginWithGoogle(String idToken) {
        return FirebaseRepository.getInstance().loginWithGoogle(idToken);
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

    @Override
    public void logout() {
        FirebaseRepository.getInstance().logout();
    }
}
