package bo.com.emprendeya.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import java.util.List;

import bo.com.emprendeya.model.Base;
import bo.com.emprendeya.model.Post;
import bo.com.emprendeya.model.Startup;
import bo.com.emprendeya.model.users.User;
import bo.com.emprendeya.repository.api.ApiRepository;
import bo.com.emprendeya.repository.firebase.FirebaseRepository;
import bo.com.emprendeya.repository.local.LocalRepository;

public class Repository implements RepositoryImpl {
    private LocalRepository local;

    public Repository(Application application) {
        local = new LocalRepository(application);
    }

    @Override
    public LiveData<Base<User>> loginWithEmailPassword(String email, String password) {
        return FirebaseRepository.getInstance().loginWithEmailPassword(email, password);
    }

    @Override
    public LiveData<Base<User>> loginWithGoogle() {
        return FirebaseRepository.getInstance().loginWithGoogle();
    }

    @Override
    public LiveData<Base<List<Startup>>> getStartups(String category) {
        MutableLiveData<Base<List<Startup>>> results = new MutableLiveData<>();
        //First local
        local.getStartups().observeForever(new Observer<List<Startup>>() {
            @Override
            public void onChanged(List<Startup> startups) {
                results.postValue(new Base<>(startups));
            }
        });

        //API
        ApiRepository.getInstance().getStartups().observeForever(new Observer<Base<List<Startup>>>() {
            @Override
            public void onChanged(Base<List<Startup>> listBase) {
                if (listBase.isSuccess()) {
                    //Publicar al UI
                    results.postValue(listBase);

                    //Actualizar la db
                    local.update(listBase.getData());
                }
            }
        });

        return results;
    }

    @Override
    public LiveData<Base<List<Post>>> getPopularPosts() {
        return FirebaseRepository.getInstance().getPopularPosts();
    }
}
