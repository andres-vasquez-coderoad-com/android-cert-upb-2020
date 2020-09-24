package bo.com.emprendeya.repository;

import android.app.Application;

import bo.com.emprendeya.repository.local.LocalRepository;

public class Repository implements RepositoryImpl {
    private LocalRepository local;

    public Repository(Application application) {
        local = new LocalRepository(application);
    }
}
