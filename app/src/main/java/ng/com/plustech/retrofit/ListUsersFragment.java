package ng.com.plustech.retrofit;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Parcel;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

import ng.com.plustech.retrofit.Repository.UserRepository;
import ng.com.plustech.retrofit.ViewModel.UserViewModel;
import ng.com.plustech.retrofit.adapters.UserDataAdapter;
import ng.com.plustech.retrofit.databinding.FragmentListUsersBinding;
import ng.com.plustech.retrofit.databinding.FragmentSingleUserBinding;
import ng.com.plustech.retrofit.databinding.RecyclerListItemBinding;
import ng.com.plustech.retrofit.interfaces.HandleClickInterface;
import ng.com.plustech.retrofit.models.Address;
import ng.com.plustech.retrofit.models.User;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ListUsersFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListUsersFragment extends Fragment implements HandleClickInterface {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    FragmentListUsersBinding binding;
    UserViewModel userViewModel;
    ProgressBar progressBar;
    UserDataAdapter userDataAdapter;

    public ListUsersFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ListUsersFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ListUsersFragment newInstance(String param1, String param2) {
        ListUsersFragment fragment = new ListUsersFragment();
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
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_list_users, container, false);

        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        // bind RecyclerView
        progressBar = binding.progressBar;
        RecyclerView recyclerView = binding.usersRecyclerview;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        userDataAdapter = new UserDataAdapter(this);
        recyclerView.setAdapter(userDataAdapter);

        initializeView();

        binding.retryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initializeView();
            }
        });

        return binding.getRoot();
    }

    private void initializeView() {
        progressBar.setVisibility(View.VISIBLE);
        binding.retryButton.setVisibility(View.GONE);
        binding.networkStatus.setVisibility(View.GONE);
        if(isNetworkAvailable(getContext())) {
            userViewModel.init();

            LiveData<List<User>> users = userViewModel.getUsers();
            if(users != null) {
                users.observe(getViewLifecycleOwner(), new Observer<List<User>>() {
                    @Override
                    public void onChanged(List<User> users) {
                        if(users.size() > 0) {
                            userDataAdapter.setUserList(users);
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });
            }
        } else {
            binding.networkStatus.setVisibility(View.VISIBLE);
            binding.retryButton.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);
        }
    }

    public boolean isNetworkAvailable(Context context) {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkCapabilities capabilities = manager.getNetworkCapabilities(manager.getActiveNetwork());
        boolean isAvailable = false;

        if (capabilities!= null) {
            if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                isAvailable = true;
            } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                isAvailable = true;
            } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                isAvailable = true;
            }
        }
        return isAvailable;
    }

    public boolean isInternetAvailable() {
        try {
            InetAddress address = InetAddress.getByName("www.google.com");
            return !address.equals("");
        } catch (UnknownHostException e) {
            // Log error
        }
        return false;
    }

    @Override
    public void getSelectedUser(View v, User user) {
        Log.e("Info", "I got the user: "+ user.toString());
        Bundle bundle = new Bundle();
        bundle.putParcelable("user", user);

        Navigation.findNavController(v).navigate(R.id.action_listUsersFragment2_to_singleUserFragment2, bundle);
    }
}