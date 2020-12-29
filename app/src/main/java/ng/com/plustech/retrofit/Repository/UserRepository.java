package ng.com.plustech.retrofit.Repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import ng.com.plustech.retrofit.models.User;
import ng.com.plustech.retrofit.Retrofit.RetrofitClient;
import ng.com.plustech.retrofit.interfaces.UsersAPIFromJsonPlaceholder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class UserRepository {
    private LiveData<List<User>> mUsers;
    private static UserRepository instance;
    public static final String BASE_URL = "https://jsonplaceholder.typicode.com/";
    //create/get retrofit instance
    private static final Retrofit retrofit = RetrofitClient.getClient(BASE_URL);

    public static UserRepository getInstance(){
        if(instance == null){
            instance = new UserRepository();
        }
        return instance;
    }

    public MutableLiveData<List<User>> getUser() {
        MutableLiveData<List<User>> usersData = new MutableLiveData<>();
        //implement the UsersAPIJsonplaceholder interface
        UsersAPIFromJsonPlaceholder usersAPIFromJsonPlaceholder = retrofit.create(UsersAPIFromJsonPlaceholder.class);

        //make network request
        Call<List<User>> call = usersAPIFromJsonPlaceholder.getUsers();

        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if(!response.isSuccessful()) {
                    Log.e("Error", "Code: "+response.code());
                    return;
                }
                usersData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                usersData.setValue(null);
            }
        });
        return usersData;
    }
}
