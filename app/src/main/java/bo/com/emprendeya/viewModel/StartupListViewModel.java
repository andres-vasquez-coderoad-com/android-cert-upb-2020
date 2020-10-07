package bo.com.emprendeya.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import java.util.List;

import bo.com.emprendeya.model.Base;
import bo.com.emprendeya.model.Post;
import bo.com.emprendeya.model.Startup;
import bo.com.emprendeya.repository.MockRepository;
import bo.com.emprendeya.repository.RepositoryImpl;

public class StartupListViewModel extends AndroidViewModel {

    private RepositoryImpl repository;

    public StartupListViewModel(@NonNull Application application) {
        super(application);
        repository = new MockRepository();
    }

    public LiveData<Base<List<Startup>>> getStartups(String category) {
        return repository.getStartups(category);
    }

    public LiveData<Base<List<Post>>> getPopularPosts() {
        MutableLiveData<Base<List<Post>>> results = new MutableLiveData<>();
        repository.getPopularPosts().observeForever(new Observer<Base<List<Post>>>() {
            @Override
            public void onChanged(Base<List<Post>> listBase) {
                //Order the results
                //Like +++ First
                //TODO Map<Post(uuid), int>
                results.postValue(listBase);
            }
        });
        return results;
    }
}
