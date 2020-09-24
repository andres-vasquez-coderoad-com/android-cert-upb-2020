package bo.com.emprendeya.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.Arrays;
import java.util.List;

import bo.com.emprendeya.models.Base;
import bo.com.emprendeya.models.users.User;
import bo.com.emprendeya.models.users.UserProfile;

public class MockRepository implements RepositoryImpl {
    private String[] validRegularEmails = new String[]{"test@email.com", "test1@email.com"};
    private String[] validStartupEmails = new String[]{"startup@email.com"};
    private String[] validAdminEmails = new String[]{"admin@email.com"};

    @Override
    public LiveData<Base<User>> loginWithEmailPassword(String email, String password) {
        MutableLiveData<Base<User>> result = new MutableLiveData<>();
        List<String> regularEmails = Arrays.asList(validRegularEmails);
        List<String> startupEmails = Arrays.asList(validStartupEmails);
        List<String> adminEmails = Arrays.asList(validAdminEmails);

        User user = new User(email, password);
        if (regularEmails.contains(email)) {
            user.setProfile(UserProfile.REGULAR);
        } else if (startupEmails.contains(email)) {
            user.setProfile(UserProfile.STARTUP);
        } else if (adminEmails.contains(email)) {
            user.setProfile(UserProfile.ADMIN);
        }
        result.postValue(new Base(user));
        return result;
    }

    @Override
    public LiveData<Base<User>> loginWithGoogle() {
        return null;
    }
}
