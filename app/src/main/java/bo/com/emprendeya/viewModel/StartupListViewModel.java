package bo.com.emprendeya.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import bo.com.emprendeya.model.Base;
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
}
