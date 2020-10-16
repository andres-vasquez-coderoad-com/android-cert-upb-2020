package bo.com.emprendeya.repository.local;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import bo.com.emprendeya.model.Base;
import bo.com.emprendeya.model.Startup;
import bo.com.emprendeya.repository.local.dao.StartupDao;
import bo.com.emprendeya.repository.local.db.EmprendeYaDatabase;

public class LocalRepository {
    public static final String LOG = LocalRepository.class.getSimpleName();

    private StartupDao startupDao;

    public LocalRepository(Application application) {
        EmprendeYaDatabase db = EmprendeYaDatabase.getDatabase(application);
        startupDao = db.startupDao();
    }

    public LiveData<List<Startup>> getStartups() {
        return startupDao.getAll();
    }

    public void update(List<Startup> startups) {
        new Thread(() -> {
            startupDao.insert(startups);
        }).start();
    }
}
