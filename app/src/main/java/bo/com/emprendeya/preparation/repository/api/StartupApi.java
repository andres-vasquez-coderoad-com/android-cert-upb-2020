package bo.com.emprendeya.preparation.repository.api;

import java.util.List;

import bo.com.emprendeya.preparation.models.Startup;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface StartupApi {
    @GET("json%2Fstartups.json")
    Call<List<Startup>> getStartup(@Query("alt") String alt);
}
