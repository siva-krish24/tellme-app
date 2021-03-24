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

import static com.telme.tellme.rest.UserCustomeRestUtil.postData;

public class UserNotAnsweredRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private static final int TYPE = 1;
    private final Context context;
    private final List<Object> listRecyclerItem;

    public UserNotAnsweredRecyclerAdapter(Context context, List<Object> listRecyclerItem) {
        this.context = context;
        this.listRecyclerItem = listRecyclerItem;

    }
    public class ItemViewHolder extends RecyclerView.ViewHolder {

        private TextView name;
        private TextView mobile;
        private TextView varient;
        private TextView hypo;
        private CheckBox checkBoxInrested;
        private CheckBox checkBoxNotIntrested;
        private CheckBox checkBoxNotAnswered;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
            mobile = (TextView) itemView.findViewById(R.id.mobile);
            varient = (TextView) itemView.findViewById(R.id.variant);
            hypo = (TextView) itemView.findViewById(R.id.hypo);
            checkBoxInrested = (CheckBox) itemView.findViewById(R.id.user_intrested);
            checkBoxNotIntrested = (CheckBox) itemView.findViewById(R.id.user_notintrested);
            checkBoxNotAnswered = (CheckBox) itemView.findViewById(R.id.user_notanswered);
            final ObjectMapper objectMapper = new ObjectMapper();
            checkBoxInrested.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    // makes the set disappear when checkbox is ticked.
                    if(isChecked){
                        try {
                            UserCustomerPojo userCustomerPojo =
                                    (UserCustomerPojo)listRecyclerItem.get(getAdapterPosition());
                            updateIntrested(userCustomerPojo);
                            listRecyclerItem.remove(getAdapterPosition());
                            notifyItemRemoved(getAdapterPosition());
                            Toast.makeText(checkBoxInrested.getContext(), "Done!", Toast.LENGTH_LONG).show();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                }

            });
            checkBoxNotIntrested.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    // makes the set disappear when checkbox is ticked.
                    if(isChecked){
                        try {
                            UserCustomerPojo userCustomerPojo =
                                    (UserCustomerPojo)listRecyclerItem.get(getAdapterPosition());
                            updatenotIntrested(userCustomerPojo);
                            listRecyclerItem.remove(getAdapterPosition());
                            notifyItemRemoved(getAdapterPosition());
                            Toast.makeText(checkBoxInrested.getContext(), "Done!", Toast.LENGTH_LONG).show();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
            checkBoxNotAnswered.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    // makes the set disappear when checkbox is ticked.
                    if(isChecked){
                        try {
                            UserCustomerPojo userCustomerPojo =
                                    (UserCustomerPojo)listRecyclerItem.get(getAdapterPosition());
                            updatenotAnswered(userCustomerPojo);
                            listRecyclerItem.remove(getAdapterPosition());
                            notifyItemRemoved(getAdapterPosition());
                            Toast.makeText(checkBoxInrested.getContext(), "Done!", Toast.LENGTH_LONG).show();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });


        }

    }
    private void updatenotIntrested(UserCustomerPojo userCustomerPojo) throws IOException {

        Gson json = new Gson();
        String body = json.toJson(userCustomerPojo, UserCustomerPojo.class);
        String urlstr = "http://192.168.29.120:8080/addtonotintrested";
        postData("addtonotintrested",body);
    }

    private void updatenotAnswered(UserCustomerPojo userCustomerPojo) throws IOException {

        Gson json = new Gson();
        String body = json.toJson(userCustomerPojo, UserCustomerPojo.class);
        String urlstr = "http://192.168.29.120:8080/addtonotanswerd";
        postData("addtonotanswerd",body);
    }

    private void updateIntrested(UserCustomerPojo userCustomerPojo) throws IOException {

        Gson json = new Gson();
        String body = json.toJson(userCustomerPojo, UserCustomerPojo.class);
        String urlstr = "http://192.168.29.120:8080/addtointrested";
        postData("addtointrested",body);
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        switch (i) {
            case TYPE:

            default:

                View layoutView = LayoutInflater.from(viewGroup.getContext()).inflate(
                        R.layout.users_notanswered_list_item, viewGroup, false);
                return new UserNotAnsweredRecyclerAdapter.ItemViewHolder((layoutView));
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

        int viewType = getItemViewType(i);

        switch (viewType) {
            case TYPE:
            default:

                UserNotAnsweredRecyclerAdapter.ItemViewHolder itemViewHolder = (UserNotAnsweredRecyclerAdapter.ItemViewHolder) viewHolder;
                UserCustomerPojo userCustomerPojo = (UserCustomerPojo) listRecyclerItem.get(i);
                Customer customer = userCustomerPojo.getCustomer();
                itemViewHolder.name.setText(customer.getName());
                itemViewHolder.mobile.setText(customer.getMobile());
                itemViewHolder.varient.setText(customer.getVarient());
                itemViewHolder.hypo.setText(customer.getHypo());

        }

    }


    @Override
    public int getItemCount() {
        return listRecyclerItem.size();
    }
}