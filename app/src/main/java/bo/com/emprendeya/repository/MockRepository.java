package bo.com.emprendeya.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import bo.com.emprendeya.model.Base;
import bo.com.emprendeya.model.Post;
import bo.com.emprendeya.model.Startup;
import bo.com.emprendeya.model.users.AdminUser;
import bo.com.emprendeya.model.users.StartupUser;
import bo.com.emprendeya.model.users.User;
import bo.com.emprendeya.utils.Constants;
import bo.com.emprendeya.utils.Validations;

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

        if (Validations.isEmpty(email) || Validations.isEmpty(password)) {
            results.postValue(new Base(Constants.ERROR_EMPTY_VALUES, null));
            return results;
        }

        if (!Validations.isValidEmail(email)) {
            results.postValue(new Base(Constants.ERROR_INVALID_EMAIL, null));
            return results;
        }

        //Server
        for (User user : getFakeUsers()) {
            if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                results.postValue(new Base(user));
                return results;
            }
        }

        results.postValue(new Base(Constants.ERROR_LOGIN, null));
        return results;
    }

    @Override
    public LiveData<Base<User>> loginWithGoogle() {
        return null;
    }

    @Override
    public LiveData<Base<List<Startup>>> getStartups(String category) {
        MutableLiveData<Base<List<Startup>>> results = new MutableLiveData<>();
        Startup startup1 = new Startup();
        startup1.setUuid("1");
        startup1.setDisplayName("Pollos Copacabana");

        Startup startup2 = new Startup();
        startup2.setUuid("2");
        startup2.setDisplayName("Pollos don Coco");

        List<Startup> startupList = new ArrayList<>();
        startupList.add(startup1);
        startupList.add(startup2);

        results.postValue(new Base<>(startupList));
        return results;
    }

    @Override
    public LiveData<Base<List<Post>>> getPopularPosts() {
        return null;
    }


    private List<Post> getPosts() {
        //Serializar
        Post post = new Post();
        post.setUuid("1");
        post.setTitle("Presas a 1bs");
        String json2 = new Gson().toJson(post);

        //Deserializar
        String json1 = "{\"uuid\":\"1\",\"coverPhoto\":\"https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcSYpJEW0OZ6P34scHKLP07kP3xMWy0Bv4Dywg&usqp=CAU\",\"content\":\"Pollos Copabacabana - Promo Primavera: 1bs una presa extra\",\"title\":\"1bs una presa extra\",\"timestamp\":1601902972,\"categories\":[\"FOOD\",\"DELIVERY\",\"PROMO\"],\"likes\":[\"1\",\"2\",\"3\",\"99\"]}";
        Post post1 = new Gson().fromJson(json1, Post.class);

        String json = "[{\"uuid\":\"1\",\"coverPhoto\":\"https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcSYpJEW0OZ6P34scHKLP07kP3xMWy0Bv4Dywg&usqp=CAU\",\"content\":\"Pollos Copabacabana - Promo Primavera: 1bs una presa extra\",\"title\":\"1bs una presa extra\",\"timestamp\":1601902972,\"categories\":[\"FOOD\",\"DELIVERY\",\"PROMO\"],\"likes\":[\"1\",\"2\",\"3\",\"99\"]},{\"uuid\":\"2\",\"coverPhoto\":\"https://fastly.4sqi.net/img/general/600x600/55787581_yDpn1GNf78dU-m5n5bpY63NBg0L9pdhQiyVwmOHrr0U.jpg \",\"content\":\"Pollos Don coco - Ahora en la Zona Sur\",\"title\":\"Ahora en la zona sur\",\"timestamp\":1601902973,\"categories\":[\"FOOD\"],\"likes\":[\"1\",\"77\",\"88\",\"98\"]},{\"uuid\":\"3\",\"coverPhoto\":\"https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcST3e1WiRivXKQL9pszwbvLDWndj0gs3H-pxA&usqp=CAU\",\"content\":\"Las Rieles - Pide tu fricharron\",\"title\":\"Pide tu fricharron\",\"timestamp\":1601902975,\"categories\":[\"FOOD\",\"DELIVERY\"],\"likes\":[\"1\",\"77\"]},{\"uuid\":\"4\",\"coverPhoto\":\"https://scontent.flpb2-1.fna.fbcdn.net/v/t1.0-9/35841623_1706824722749818_3918040596735852544_o.jpg?_nc_cat=108&_nc_sid=730e14&_nc_ohc=MfzXJTsUkxIAX8_-WlQ&_nc_ht=scontent.flpb2-1.fna&oh=1a429e2b77ae5d06f09e5d7dfa3afec6&oe=5FA01582 \",\"content\":\"Casa del Camba Express - Churrasco 2x1\",\"title\":\"Churrasco 2x1\",\"timestamp\":1601902983,\"categories\":[\"FOOD\",\"DELIVERY\"],\"likes\":[\"1\",\"98\"]},{\"uuid\":\"5\",\"coverPhoto\":\"https://scontent.flpb2-2.fna.fbcdn.net/v/t31.0-8/fr/cp0/e15/q65/18891584_688781747975940_5608987134874905271_o.jpg?_nc_cat=111&_nc_sid=05277f&_nc_ohc=hpT233DfYO0AX_HEC0G&_nc_ht=scontent.flpb2-2.fna&tp=14&oh=1ee38747d638d5f63dc60e226230b459&oe=5FA01939 \",\"content\":\"Ellis - 2 conos x 25bs\",\"title\":\"2 conos x 25bs\",\"timestamp\":1601902978,\"categories\":[\"FOOD\",\"DELIVERY\"],\"likes\":[\"1\"]}]";
        Type listType = new TypeToken<ArrayList<Post>>() {
        }.getType();
        List<Post> yourClassList = new Gson().fromJson(json, listType);
        return yourClassList;
    }
}
