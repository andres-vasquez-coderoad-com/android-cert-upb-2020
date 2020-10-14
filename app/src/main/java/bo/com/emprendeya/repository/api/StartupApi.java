package bo.com.emprendeya.repository.api;

import java.util.List;

import bo.com.emprendeya.model.Startup;
import bo.com.emprendeya.utils.Constants;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface StartupApi {
    @GET(Constants.RESOURCE_STARTUPS)
    Call<List<Startup>> getStartups(@Query("alt") String alt);

    //Esto no servira
    //Get by ID
    @GET(Constants.RESOURCE_STARTUPS + "/{id}")
    Call<Startup> getOneStartup(@Path("id") int id);

    //Create startup
    @POST(Constants.RESOURCE_STARTUPS)
    Call<Startup> createStartup(@Body Startup startup);

    @PUT(Constants.RESOURCE_STARTUPS)
    Call<Startup> create(@Body Startup startup);

    @PATCH(Constants.RESOURCE_STARTUPS + "/{id}")
    Call<Startup> update(@Path("id") int id, @Body Startup startup);

    @DELETE(Constants.RESOURCE_STARTUPS + "/{id}")
    Call delete(@Path("id") int id);
}
