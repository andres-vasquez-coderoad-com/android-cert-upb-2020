package bo.com.emprendeya.repository.local;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import bo.com.emprendeya.model.Base;
import bo.com.emprendeya.model.Startup;

public class LocalRepository {
    public static final String LOG = LocalRepository.class.getSimpleName();

    public LocalRepository(Application application) {

    }

    public LiveData<Base<List<Startup>>> getStartups() {
        return null;
    }

    public void update(List<Startup> startups) {

    }
}
