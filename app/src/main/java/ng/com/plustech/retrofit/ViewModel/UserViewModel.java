package ng.com.plustech.retrofit.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import ng.com.plustech.retrofit.Repository.UserRepository;
import ng.com.plustech.retrofit.models.User;

public class UserViewModel extends ViewModel {
    private MutableLiveData<List<User>> vmUsers;

    public void init() {
        if(vmUsers != null) {
            return;
        }
        UserRepository userRepository = UserRepository.getInstance();
        vmUsers = userRepository.getUser();
    }

    public LiveData<List<User>> getUsers() {
        return vmUsers;
    }
}
