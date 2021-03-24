package com.telme.tellme.reclyeradpaters.user;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
import com.telme.tellme.Holidays;
import com.telme.tellme.IntrestedCustomer;
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

public class UserFollowupsRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private static final int TYPE = 1;
    private final Context context;
    private final List<Object> listRecyclerItem;
    public UserFollowupsRecyclerAdapter( Context context, List<Object> listRecyclerItem) {
        this.context = context;
        this.listRecyclerItem = listRecyclerItem;
    }


    public class ItemViewHolder extends RecyclerView.ViewHolder {

        private TextView name;
        private TextView mobile;
        private TextView varient;
        private TextView hypo;
        private EditText details;
        private Button submit;
        private Button notIntrested;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
            mobile = (TextView) itemView.findViewById(R.id.mobile);
            varient = (TextView) itemView.findViewById(R.id.variant);
            hypo = (TextView) itemView.findViewById(R.id.hypo);
            details = (EditText) itemView.findViewById(R.id.details);
            submit = (Button) itemView.findViewById(R.id.user_followups_submit);
            notIntrested = (Button) itemView.findViewById(R.id.user_followups_notintrested);
            final ObjectMapper objectMapper = new ObjectMapper();
            submit.setOnClickListener(new CompoundButton.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        UserCustomerPojo userCustomerPojo =
                                (UserCustomerPojo)listRecyclerItem.get(getAdapterPosition());
                        listRecyclerItem.remove(userCustomerPojo);
                       notifyItemRemoved(getAdapterPosition());
                        userCustomerPojo.getCustomer().setDetails(details.getText().toString());
                        listRecyclerItem.add(userCustomerPojo);
                        updateIntrested(userCustomerPojo);
                        Toast.makeText(submit.getContext(), "Done!", Toast.LENGTH_LONG).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            notIntrested.setOnClickListener(new CompoundButton.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        UserCustomerPojo userCustomerPojo =
                                (UserCustomerPojo)listRecyclerItem.get(getAdapterPosition());
                        userCustomerPojo.getCustomer().setDetails(details.getText().toString());
                        updateNotIntrested(userCustomerPojo);
                        listRecyclerItem.remove(getAdapterPosition());
                        notifyItemRemoved(getAdapterPosition());
                        Toast.makeText(submit.getContext(), "Done!", Toast.LENGTH_LONG).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

    }
    private void updateNotIntrested(UserCustomerPojo userCustomerPojo) throws IOException {

        Gson json = new Gson();
        String body = json.toJson(userCustomerPojo, UserCustomerPojo.class);

        postData("addtonotintrested",body);
    }

    private void updateIntrested(UserCustomerPojo userCustomerPojo) throws IOException {

        Gson json = new Gson();
        String body = json.toJson(userCustomerPojo, UserCustomerPojo.class);
        postData("addtointrested",body);
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        switch (i) {
            case TYPE:

            default:

                View layoutView = LayoutInflater.from(viewGroup.getContext()).inflate(
                        R.layout.user_followups_list_item, viewGroup, false);
                return new UserFollowupsRecyclerAdapter.ItemViewHolder((layoutView));
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

        int viewType = getItemViewType(i);

        switch (viewType) {
            case TYPE:
            default:

                UserFollowupsRecyclerAdapter.ItemViewHolder itemViewHolder = (UserFollowupsRecyclerAdapter.ItemViewHolder) viewHolder;
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