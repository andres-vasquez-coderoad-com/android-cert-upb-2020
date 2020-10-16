package bo.com.emprendeya.repository;

import android.app.Application;

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

    public MockRepository(Application application) {
    }

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
        String json = "[{\"uuid\":\"1\",\"displayName\":\"Pollos Copacabana Pollos Copacabana Pollos Copacabana Pollos Copacabana Pollos Copacabana Pollos Copacabana\",\"coverPhoto\":\"https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcSYpJEW0OZ6P34scHKLP07kP3xMWy0Bv4Dywg&usqp=CAU\",\"address\":\"Calacoto Calle 19\",\"categories\":[\"FOOD\",\"DELIVERY\"],\"posts\":[],\"codivInfo\":{\"Temperatura\":\"SI\",\"Pediluvio\":\"SI\",\"Solo Delivery\":\"SI\",\"Aforo\":\"40%\"},\"schedule\":{\"Lun-Vie\":[\"10:00-12:30\",\"14:00-20:00\"],\"Sábado\":[\"10:00-13:00\"],\"Domingo\":[]}},{\"uuid\":\"2\",\"displayName\":\"Pollos Don Coco\",\"coverPhoto\":\"https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcRov7JEgomRqNB2ZLsSB7ewnXwoohJW-sNvlw&usqp=CAU\",\"address\":\"Callatayud\",\"categories\":[\"FOOD\",\"DELIVERY\"],\"posts\":[],\"codivInfo\":{\"Temperatura\":\"SI\",\"Pediluvio\":\"SI\",\"Solo Delivery\":\"SI\",\"Aforo\":\"40%\"},\"schedule\":{\"Lun-Vie\":[\"10:00-20:30\"],\"Sábado\":[\"10:00-13:00\"],\"Domingo\":[]}},{\"uuid\":\"3\",\"displayName\":\"Ellis\",\"coverPhoto\":\"https://media-cdn.tripadvisor.com/media/photo-s/12/e4/14/87/new-york-style-pizza.jpg\",\"address\":\"San Miguel, calle montenegro\",\"categories\":[\"FOOD\",\"DELIVERY\"],\"posts\":[],\"codivInfo\":{\"Temperatura\":\"SI\",\"Pediluvio\":\"SI\",\"Solo Delivery\":\"SI\",\"Aforo\":\"40%\"},\"schedule\":{\"Lun-Vie\":[\"10:00-12:30\",\"14:00-20:00\"],\"Sábado\":[\"10:00-13:00\"],\"Domingo\":[]}},{\"uuid\":\"4\",\"displayName\":\"Burger King\",\"coverPhoto\":\"https://scontent.fymy1-1.fna.fbcdn.net/v/t1.0-9/s720x720/118085011_10158701410064791_3787707351054763520_o.jpg?_nc_cat=105&_nc_sid=110474&_nc_ohc=bFPczNrczS0AX9JRcd6&_nc_ht=scontent.fymy1-1.fna&tp=7&oh=16923397f953e24081c28aeb71be7dcd&oe=5F883CB1\",\"address\":\"Calacoto Calle 19\",\"categories\":[\"FOOD\",\"DELIVERY\"],\"posts\":[],\"codivInfo\":{\"Temperatura\":\"SI\",\"Pediluvio\":\"SI\",\"Solo Delivery\":\"SI\",\"Aforo\":\"40%\"},\"schedule\":{\"Lun-Vie\":[\"10:00-12:30\",\"14:00-20:00\"],\"Sábado\":[\"10:00-13:00\"],\"Domingo\":[]}},{\"uuid\":\"5\",\"displayName\":\"Chicharrones Las Rieles \",\"coverPhoto\":\"https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcRic8DaWRahs6-CkJqSZJoIWwdnH5mR7C43VA&usqp=CAU \",\"address\":\"Megacenter Local 16\",\"categories\":[\"FOOD\",\"DELIVERY\"],\"posts\":[],\"codivInfo\":{\"Temperatura\":\"SI\",\"Pediluvio\":\"SI\",\"Solo Delivery\":\"SI\",\"Aforo\":\"40%\"},\"schedule\":{\"Lun-Vie\":[\"10:00-12:30\",\"14:00-20:00\"],\"Sábado\":[\"10:00-13:00\"],\"Domingo\":[]}},{\"uuid\":\"6\",\"displayName\":\"Casa del Camba Express\",\"coverPhoto\":\"https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcScsm8DALJTCQ3woYavLxB_630kTx7Zvl4rYQ&usqp=CAU\",\"address\":\"Av. Sanchez Bustamante\",\"categories\":[\"FOOD\",\"DELIVERY\"],\"posts\":[],\"codivInfo\":{\"Temperatura\":\"SI\",\"Pediluvio\":\"SI\",\"Solo Delivery\":\"SI\",\"Aforo\":\"40%\"},\"schedule\":{\"Lun-Vie\":[\"10:00-12:30\",\"14:00-20:00\"],\"Sábado\":[\"10:00-13:00\"],\"Domingo\":[]}}]";
        Type listType = new TypeToken<ArrayList<Startup>>() {
        }.getType();
        List<Startup> startupList = new Gson().fromJson(json, listType);
        results.postValue(new Base<>(startupList));
        return results;
    }

    @Override
    public LiveData<Base<List<Post>>> getPopularPosts() {
        MutableLiveData<Base<List<Post>>> results = new MutableLiveData<>();

        new Thread(() -> {
            try {
                Thread.sleep(5000);
                results.postValue(new Base<>(getPosts()));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        return results;
    }


    private List<Post> getPosts() {
        String json = "[{\"uuid\":\"1\",\"coverPhoto\":\"https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcSYpJEW0OZ6P34scHKLP07kP3xMWy0Bv4Dywg&usqp=CAU\",\"content\":\"Pollos Copabacabana - Promo Primavera: 1bs una presa extra\",\"title\":\"1bs una presa extra\",\"timestamp\":1601902972,\"categories\":[\"FOOD\",\"DELIVERY\",\"PROMO\"],\"likes\":[\"1\",\"2\",\"3\",\"99\"]},{\"uuid\":\"2\",\"coverPhoto\":\"https://fastly.4sqi.net/img/general/600x600/55787581_yDpn1GNf78dU-m5n5bpY63NBg0L9pdhQiyVwmOHrr0U.jpg \",\"content\":\"Pollos Don coco - Ahora en la Zona Sur\",\"title\":\"Ahora en la zona sur\",\"timestamp\":1601902973,\"categories\":[\"FOOD\"],\"likes\":[\"1\",\"77\",\"88\",\"98\"]},{\"uuid\":\"3\",\"coverPhoto\":\"https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcST3e1WiRivXKQL9pszwbvLDWndj0gs3H-pxA&usqp=CAU\",\"content\":\"Las Rieles - Pide tu fricharron\",\"title\":\"Pide tu fricharron\",\"timestamp\":1601902975,\"categories\":[\"FOOD\",\"DELIVERY\"],\"likes\":[\"1\",\"77\"]},{\"uuid\":\"4\",\"coverPhoto\":\"https://scontent.flpb2-1.fna.fbcdn.net/v/t1.0-9/35841623_1706824722749818_3918040596735852544_o.jpg?_nc_cat=108&_nc_sid=730e14&_nc_ohc=MfzXJTsUkxIAX8_-WlQ&_nc_ht=scontent.flpb2-1.fna&oh=1a429e2b77ae5d06f09e5d7dfa3afec6&oe=5FA01582 \",\"content\":\"Casa del Camba Express - Churrasco 2x1\",\"title\":\"Churrasco 2x1\",\"timestamp\":1601902983,\"categories\":[\"FOOD\",\"DELIVERY\"],\"likes\":[\"1\",\"98\"]},{\"uuid\":\"5\",\"coverPhoto\":\"https://scontent.flpb2-2.fna.fbcdn.net/v/t31.0-8/fr/cp0/e15/q65/18891584_688781747975940_5608987134874905271_o.jpg?_nc_cat=111&_nc_sid=05277f&_nc_ohc=hpT233DfYO0AX_HEC0G&_nc_ht=scontent.flpb2-2.fna&tp=14&oh=1ee38747d638d5f63dc60e226230b459&oe=5FA01939 \",\"content\":\"Ellis - 2 conos x 25bs\",\"title\":\"2 conos x 25bs\",\"timestamp\":1601902978,\"categories\":[\"FOOD\",\"DELIVERY\"],\"likes\":[\"1\"]}]";
        Type listType = new TypeToken<ArrayList<Post>>() {
        }.getType();
        List<Post> yourClassList = new Gson().fromJson(json, listType);
        return yourClassList;
    }
}
