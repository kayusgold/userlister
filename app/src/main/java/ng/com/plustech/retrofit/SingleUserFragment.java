package ng.com.plustech.retrofit;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import ng.com.plustech.retrofit.Repository.UserRepository;
import ng.com.plustech.retrofit.ViewModel.UserViewModel;
import ng.com.plustech.retrofit.databinding.FragmentSingleUserBinding;
import ng.com.plustech.retrofit.models.Address;
import ng.com.plustech.retrofit.models.User;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SingleUserFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SingleUserFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SingleUserFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SingleUserFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SingleUserFragment newInstance(String param1, String param2) {
        SingleUserFragment fragment = new SingleUserFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FragmentSingleUserBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_single_user, container, false);

        //get passed user
        User user = getArguments().getParcelable("user");

        binding.setUser(user);
        binding.setAddress(user.getAddress());

//        UserViewModel userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
//
//        userViewModel.init();
//
//        LiveData<List<User>> users = userViewModel.getUsers();
//        if(users != null) {
//            users.observe(getViewLifecycleOwner(), new Observer<List<User>>() {
//                @Override
//                public void onChanged(List<User> users) {
//                    if(users.size() > 0) {
//                        binding.setUser(users.get(0));
//                        Address address = users.get(0).getAddress();
//                        binding.setAddress(address);
//                    }
//                }
//            });
//        }
        return binding.getRoot();
    }
}