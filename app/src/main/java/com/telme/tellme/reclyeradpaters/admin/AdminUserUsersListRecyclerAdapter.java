package com.telme.tellme.reclyeradpaters.admin;

import android.content.Context;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.telme.tellme.AdminHomeActivity;
import com.telme.tellme.Customer;
import com.telme.tellme.FreshDataActivity;
import com.telme.tellme.R;
import com.telme.tellme.User;
import com.telme.tellme.UserCustomerPojo;
import com.telme.tellme.UserHome;
import com.telme.tellme.reclyeradpaters.user.UserFreshDataRecyclerAdapter;

import java.io.IOException;
import java.util.List;

public class AdminUserUsersListRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private static final int TYPE = 1;
    private final Context context;
    private final List<User> listRecyclerItem;

    public AdminUserUsersListRecyclerAdapter(Context context, List<User> listRecyclerItem) {
        this.context = context;
        this.listRecyclerItem = listRecyclerItem;
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {

        private TextView name;


        public ItemViewHolder(@NonNull  View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.admin_user_list_username);
        }

    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        switch (i) {
            case TYPE:

            default:

                View layoutView = LayoutInflater.from(viewGroup.getContext()).inflate(
                        R.layout.admin_user_list_item, viewGroup, false);
                return new AdminUserUsersListRecyclerAdapter.ItemViewHolder((layoutView));
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

        int viewType = getItemViewType(i);

        switch (viewType) {
            case TYPE:
            default:

                AdminUserUsersListRecyclerAdapter.ItemViewHolder itemViewHolder =
                        (AdminUserUsersListRecyclerAdapter.ItemViewHolder) viewHolder;
                User user = (User) listRecyclerItem.get(i);
                itemViewHolder.name.setText(user.getName());
        }

    }


    @Override
    public int getItemCount() {
        return listRecyclerItem.size();
    }
}
