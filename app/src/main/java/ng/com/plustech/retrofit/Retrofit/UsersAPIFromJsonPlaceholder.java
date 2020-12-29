package ng.com.plustech.retrofit.Retrofit;

import java.util.List;

import ng.com.plustech.retrofit.models.User;
import retrofit2.Call;
import retrofit2.http.GET;

public interface UsersAPIFromJsonPlaceholder {
    @GET("users")
    Call<List<User>> getUsers();
}
