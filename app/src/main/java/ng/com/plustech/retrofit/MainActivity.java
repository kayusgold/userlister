package ng.com.plustech.retrofit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import java.util.List;

import ng.com.plustech.retrofit.ViewModel.UserViewModel;
import ng.com.plustech.retrofit.models.Address;
import ng.com.plustech.retrofit.models.User;
import ng.com.plustech.retrofit.Repository.UserRepository;
import ng.com.plustech.retrofit.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private UserRepository userRepository;
    private UserViewModel userViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setLifecycleOwner(this);

        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        userViewModel.init();

        userRepository = UserRepository.getInstance();
        LiveData<List<User>> users = userViewModel.getUsers();
        if(users != null) {
            users.observe(this, new Observer<List<User>>() {
                @Override
                public void onChanged(List<User> users) {
                    if(users.size() > 0) {
                        binding.setUser(users.get(0));
                        Address address = users.get(0).getAddress();
                        binding.setAddress(address);
                    }
                }
            });
        }
    }
}