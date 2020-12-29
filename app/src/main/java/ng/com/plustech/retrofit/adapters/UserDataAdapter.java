package ng.com.plustech.retrofit.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ng.com.plustech.retrofit.R;
import ng.com.plustech.retrofit.databinding.RecyclerListItemBinding;
import ng.com.plustech.retrofit.interfaces.HandleClickInterface;
import ng.com.plustech.retrofit.models.Address;
import ng.com.plustech.retrofit.models.User;

public class UserDataAdapter extends RecyclerView.Adapter<UserDataAdapter.UserDataViewHolder> {
    private List<User> userList;

    HandleClickInterface handleClickInterface;

    public UserDataAdapter(HandleClickInterface handleClickInterface) {
        this.handleClickInterface = handleClickInterface;
    }

    @NonNull
    @Override
    public UserDataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerListItemBinding listItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.recycler_list_item, parent, false);
        return new UserDataViewHolder(listItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull UserDataAdapter.UserDataViewHolder holder, int position) {
        User currentUser = userList.get(position);
        Address address = currentUser.getAddress();
        holder.recyclerListItemBinding.setUser(currentUser);
        holder.recyclerListItemBinding.setAddress(address);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleClickInterface.getSelectedUser(v, currentUser);
                Log.e("Info", "We suppose to navigate to where we will show " + currentUser.getName() + "'s details.");
            }
        });
    }

    @Override
    public int getItemCount() {
        if(userList != null) {
            return userList.size();
        }
        return 0;
    }

    public void setUserList(List<User> users) {
        this.userList = users;
        notifyDataSetChanged();
    }

    class UserDataViewHolder extends RecyclerView.ViewHolder{
        private RecyclerListItemBinding recyclerListItemBinding;
        public UserDataViewHolder(@NonNull RecyclerListItemBinding recyclerListItemBinding) {
            super(recyclerListItemBinding.getRoot());
            this.recyclerListItemBinding = recyclerListItemBinding;
        }
    }
}
