package bo.com.emprendeya.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

import bo.com.emprendeya.model.Base;
import bo.com.emprendeya.model.users.AdminUser;
import bo.com.emprendeya.model.users.StartupUser;
import bo.com.emprendeya.model.users.User;
import bo.com.emprendeya.utils.Constants;

public class MockRepository implements RepositoryImpl {

    public List<User> getFakeUsers() {
        List<User> fakeUsers = new ArrayList<>();
        User paola = new User("paola.rivas@email.com", "test123");
        paola.setUuid("1");
        paola.setDisplayName("Paola Rivas");
        fakeUsers.add(paola); //Regular

        fakeUsers.add(new User("weimar.torres@email.com", "test124")); //Regular
        fakeUsers.add(new StartupUser("jordi.ugarte@email.com", "test125")); //Startup
        fakeUsers.add(new AdminUser("sergio.laguna@email.com", "test126")); //Admin
        return fakeUsers;
    }

    @Override
    public LiveData<Base<User>> loginWithEmailPassword(String email, String password) {
        MutableLiveData<Base<User>> results = new MutableLiveData<>();

        boolean found = false;
        for (User user : getFakeUsers()) {
            if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                found = true;
                results.postValue(new Base(user));
            }
        }
        if (!found) {
            results.postValue(new Base(Constants.ERROR_LOGIN, null));
        }
        return results;
    }

    @Override
    public LiveData<Base<User>> loginWithGoogle() {
        return null;
    }
}
