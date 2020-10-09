package bo.com.emprendeya.repository.local;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;

import java.util.List;

import bo.com.emprendeya.models.Startup;
import bo.com.emprendeya.repository.local.dao.StartupDao;
import bo.com.emprendeya.repository.local.db.StartupDatabase;

public class LocalRepository {
    public static final String LOG = LocalRepository.class.getSimpleName();

    private StartupDao startupDao;
    private LiveData<List<Startup>> startups;

    public LocalRepository(Application application) {
        StartupDatabase db = StartupDatabase.getDatabase(application);
        startupDao = db.startupApiDao();
    }

    public LiveData<List<Startup>> getStartups() {
        return startupDao.getAll();
    }

    public void update(List<Startup> startups) {
        Thread thread = new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        List<Long> ids = startupDao.insert(startups);
                        Log.e(LOG, "Insert:" + ids.size());
                    }
                }
        );
        thread.start();
    }
}
