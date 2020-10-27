package bo.com.emprendeya.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import bo.com.emprendeya.model.Base;
import bo.com.emprendeya.model.Post;
import bo.com.emprendeya.model.Startup;
import bo.com.emprendeya.repository.Repository;
import bo.com.emprendeya.repository.RepositoryImpl;

public class StartupDetailsViewModel extends AndroidViewModel {
    private RepositoryImpl repository;

    private MutableLiveData<Startup> startup = new MutableLiveData<>();

    public StartupDetailsViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository(application);
    }

    public MutableLiveData<Startup> getStartup() {
        return startup;
    }

    public void setStartup(Startup startup) {
        this.startup.postValue(startup);
    }

    public LiveData<Base<List<Post>>> observePosts(String uuid) {
        return repository.observeStartupPost(uuid);
    }
}