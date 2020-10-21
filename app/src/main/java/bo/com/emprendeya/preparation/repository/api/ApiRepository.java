package bo.com.emprendeya.preparation.repository.api;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import bo.com.emprendeya.preparation.models.Base;
import bo.com.emprendeya.preparation.models.Startup;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApiRepository {
    private static final String LOG = ApiRepository.class.getSimpleName();
    private static ApiRepository instance;

    private StartupApi startupApi;

    public static ApiRepository getInstance() {
        if (instance == null) {
            instance = new ApiRepository();
        }
        return instance;
    }

    public ApiRepository() {
        startupApi = ApiService.createService(StartupApi.class);
    }

    public LiveData<Base<List<Startup>>> getStartups() {
        MutableLiveData<Base<List<Startup>>> results = new MutableLiveData<>();
        startupApi.getStartup("media").enqueue(new Callback<List<Startup>>() {
            @Override
            public void onResponse(Call<List<Startup>> call, Response<List<Startup>> response) {
                if (response.isSuccessful()) {
                    results.postValue(new Base<>(response.body()));
                } else {
                    results.postValue(new Base<>(response.message(), new NullPointerException()));
                }
            }

            @Override
            public void onFailure(Call<List<Startup>> call, Throwable t) {
                results.postValue(new Base<>(t.getMessage(), new Exception(t)));
            }
        });
        return results;
    }
}
