package ng.com.plustech.retrofit.interfaces;

import java.util.List;

import ng.com.plustech.retrofit.models.User;
import retrofit2.Call;
import retrofit2.http.GET;

public interface UsersAPIFromJsonPlaceholder {
    @GET("users")
    Call<List<User>> getUsers();
}
