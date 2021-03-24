package com.telme.tellme.reclyeradpaters.user;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.telme.tellme.Customer;
import com.telme.tellme.R;
import com.telme.tellme.UserCustomerPojo;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

public class UserLoginsRecyclerAdapter  extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private static final int TYPE = 1;
    private final Context context;
    private final List<Object> listRecyclerItem;


    public UserLoginsRecyclerAdapter(Context context, List<Object> listRecyclerItem) {
        this.context = context;
        this.listRecyclerItem = listRecyclerItem;
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {

        private TextView name;
        private TextView mobile;


        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
            mobile = (TextView) itemView.findViewById(R.id.mobile);
        }

    }



    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        switch (i) {
            case TYPE:

            default:

                View layoutView = LayoutInflater.from(viewGroup.getContext()).inflate(
                        R.layout.users_mylogins_list_item, viewGroup, false);
                return new UserLoginsRecyclerAdapter.ItemViewHolder((layoutView));
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

        int viewType = getItemViewType(i);

        switch (viewType) {
            case TYPE:
            default:

                UserLoginsRecyclerAdapter.ItemViewHolder itemViewHolder = (UserLoginsRecyclerAdapter.ItemViewHolder) viewHolder;
                UserCustomerPojo userCustomerPojo = (UserCustomerPojo) listRecyclerItem.get(i);
                Customer customer = userCustomerPojo.getCustomer();
                itemViewHolder.name.setText(customer.getName());
                itemViewHolder.mobile.setText(customer.getMobile());
        }

    }


    @Override
    public int getItemCount() {
        return listRecyclerItem.size();
    }
}