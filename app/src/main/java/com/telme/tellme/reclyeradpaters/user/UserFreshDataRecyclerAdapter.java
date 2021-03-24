package com.telme.tellme.reclyeradpaters.user;

import android.content.Context;
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
import com.google.gson.Gson;
import com.telme.tellme.Customer;
import com.telme.tellme.FreshDataActivity;
import com.telme.tellme.Holidays;
import com.telme.tellme.IntrestedCustomer;
import com.telme.tellme.R;
import com.telme.tellme.User;
import com.telme.tellme.UserCustomerPojo;
import com.telme.tellme.rest.UserCustomeRestUtil;

import org.json.JSONException;

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

public class UserFreshDataRecyclerAdapter  extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private static final int TYPE = 1;
    private final Context context;
    private final List<Object> listRecyclerItem;

    public UserFreshDataRecyclerAdapter(Context context, List<Object> listRecyclerItem) {
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
        private EditText details;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
            mobile = (TextView) itemView.findViewById(R.id.mobile);
            varient = (TextView) itemView.findViewById(R.id.variant);
            hypo = (TextView) itemView.findViewById(R.id.hypo);
            checkBoxInrested = (CheckBox) itemView.findViewById(R.id.fresh_data_intrested);
            checkBoxNotIntrested = (CheckBox) itemView.findViewById(R.id.fresh_data_notintrested);
            checkBoxNotAnswered = (CheckBox) itemView.findViewById(R.id.fresh_data_notanswered);
            details = (EditText) itemView.findViewById(R.id.details);
            details.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                    return true;
                }
            });
            final ObjectMapper objectMapper = new ObjectMapper();
            checkBoxInrested.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    // makes the set disappear when checkbox is ticked.
                    if(isChecked){
                        try {
                        UserCustomerPojo userCustomerPojo =
                                (UserCustomerPojo)listRecyclerItem.get(getAdapterPosition());
                            userCustomerPojo.getCustomer().setDetails(details.getText().toString());
                            updateIntrested(userCustomerPojo);
                        listRecyclerItem.remove(getAdapterPosition());
                            notifyItemRemoved(getAdapterPosition());
                            checkBoxInrested.setChecked(false);
                        Toast.makeText(checkBoxInrested.getContext(), "Done!", Toast.LENGTH_LONG).show();
                            listRecyclerItem.add(listRecyclerItem.size(), getItem(userCustomerPojo.getUser().toString()));
                            notifyDataSetChanged();
                        } catch (IOException | JSONException e) {
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
                            userCustomerPojo.getCustomer().setDetails(details.getText().toString());
                            updatenotIntrested(userCustomerPojo);
                        listRecyclerItem.remove(getAdapterPosition());
                            notifyItemRemoved(getAdapterPosition());
                            checkBoxNotIntrested.setChecked(false);
                            Toast.makeText(checkBoxInrested.getContext(), "Done!", Toast.LENGTH_LONG).show();
                            listRecyclerItem.add(listRecyclerItem.size(), getItem(userCustomerPojo.getUser().toString()));
                            notifyDataSetChanged();
                        } catch (IOException | JSONException e) {
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
                            userCustomerPojo.getCustomer().setDetails(details.getText().toString());
                            updatenotAnswered(userCustomerPojo);
                            listRecyclerItem.remove(getAdapterPosition());
                            notifyItemRemoved(getAdapterPosition());
                            checkBoxNotAnswered.setChecked(false);
                            Toast.makeText(checkBoxInrested.getContext(), "Done!", Toast.LENGTH_LONG).show();
                            listRecyclerItem.add(listRecyclerItem.size(), getItem(userCustomerPojo.getUser().toString()));
                            notifyDataSetChanged();
                        } catch (IOException | JSONException e) {
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
        postData("addtonotintrested",body);
    }

    private void updatenotAnswered(UserCustomerPojo userCustomerPojo) throws IOException {

        Gson json = new Gson();
        String body = json.toJson(userCustomerPojo, UserCustomerPojo.class);
        postData("addtonotanswerd",body);
    }

    private void updateIntrested(UserCustomerPojo userCustomerPojo) throws IOException {

        Gson json = new Gson();
        String body = json.toJson(userCustomerPojo, UserCustomerPojo.class);
        postData("addtointrested",body);
    }

    private UserCustomerPojo getItem(String user) throws IOException, JSONException {
       return UserCustomeRestUtil.fetchData(user,"getonecustomer");
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        switch (i) {
            case TYPE:

            default:

                View layoutView = LayoutInflater.from(viewGroup.getContext()).inflate(
                        R.layout.users_freshdata_list_item, viewGroup, false);
                return new UserFreshDataRecyclerAdapter.ItemViewHolder((layoutView));
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

        int viewType = getItemViewType(i);

        switch (viewType) {
            case TYPE:
            default:

                UserFreshDataRecyclerAdapter.ItemViewHolder itemViewHolder = (UserFreshDataRecyclerAdapter.ItemViewHolder) viewHolder;
                UserCustomerPojo userCustomerPojo = (UserCustomerPojo) listRecyclerItem.get(i);
                Customer customer = userCustomerPojo.getCustomer();
                itemViewHolder.name.setText(customer.getName());
                itemViewHolder.mobile.setText(customer.getMobile());
                itemViewHolder.varient.setText(customer.getVarient());
                itemViewHolder.hypo.setText(customer.getHypo());
                itemViewHolder.details.setText(customer.getDetails());

        }

    }


    @Override
    public int getItemCount() {
        return listRecyclerItem.size();
    }
}