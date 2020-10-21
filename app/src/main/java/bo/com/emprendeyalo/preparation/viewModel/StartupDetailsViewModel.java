package bo.com.emprendeyalo.preparation.viewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import bo.com.emprendeyalo.preparation.models.Startup;

public class StartupDetailsViewModel extends ViewModel {
    private MutableLiveData<Startup> startup = new MutableLiveData<>();

    public MutableLiveData<Startup> getStartup() {
        return startup;
    }

    public void setStartup(Startup startup) {
        this.startup.postValue(startup);
    }
}