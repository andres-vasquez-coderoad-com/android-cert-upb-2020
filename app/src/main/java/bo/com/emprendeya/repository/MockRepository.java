package bo.com.emprendeya.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import bo.com.emprendeya.models.Base;
import bo.com.emprendeya.models.Startup;
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
        } else {
            result.postValue(new Base("Error", new NullPointerException()));
            return result;
        }
        result.postValue(new Base(user));
        return result;
    }

    @Override
    public LiveData<Base<User>> loginWithGoogle() {
        return null;
    }

    @Override
    public LiveData<Base<List<Startup>>> getStartups(String category) {
        MutableLiveData<Base<List<Startup>>> result = new MutableLiveData<>();
        List<Startup> list = new ArrayList<>();
        list.add(new Startup("1",
                "https://images.squarespace-cdn.com/content/v1/58e306c359cc689fc7b613e9/1493150371561-9YQ7KQBLMU7DPKQP47UH/ke17ZwdGBToddI8pDm48kAlIP4PFSFSzdbKF_bP3y0wUqsxRUqqbr1mOJYKfIPR7LoDQ9mXPOjoJoqy81S2I8N_N4V1vUb5AoIIIbLZhVYxCRW4BPu10St3TBAUQYVKckb3AVJqW93B_Yrl1GKfkNTFC290qmMoARMHYgq2SctW8vHQ7-dyjZJ481VG-F6Q7/El-Rey-16_tacos.jpg?format=1500w",
                "Tacos el Rey",
                "FOOD",
                "Obrajes calle 16"));
        list.add(new Startup("2",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcSdaX9aazCdxacZnRsPUYWXCn-Nl_LLmzUK2Q&usqp=CAU",
                "Emparrillados",
                "FOOD",
                "Alto seguencoma C6"));
        list.add(new Startup("3",
                "https://www.chovi.com/wp-content/uploads/2017/01/receta-kebab-casero.jpg",
                "Kebbabs El Turco",
                "FOOD",
                "San Miguel, Montenegro"));
        result.postValue(new Base<>(list));
        return result;
    }
}
