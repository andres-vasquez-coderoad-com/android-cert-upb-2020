package bo.com.emprendeya.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import bo.com.emprendeya.repository.Repository;

public class StartupListViewModel extends AndroidViewModel {
    private static final String LOG = StartupListViewModel.class.getSimpleName();
    Repository repository;

    public StartupListViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository(application);
    }
}
