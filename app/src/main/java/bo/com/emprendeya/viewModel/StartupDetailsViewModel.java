package bo.com.emprendeya.viewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import bo.com.emprendeya.model.Startup;

public class StartupDetailsViewModel extends ViewModel {
    private MutableLiveData<Startup> startup = new MutableLiveData<>();

    public MutableLiveData<Startup> getStartup() {
        return startup;
    }

    public void setStartup(Startup startup) {
        this.startup.postValue(startup);
    }
}