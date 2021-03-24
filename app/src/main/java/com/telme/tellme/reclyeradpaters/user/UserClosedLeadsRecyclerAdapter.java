package com.telme.tellme.reclyeradpaters.user;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.telme.tellme.Customer;
import com.telme.tellme.R;
import com.telme.tellme.UserCustomerPojo;
import java.util.List;

public class UserClosedLeadsRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private static final int TYPE = 1;
    private final Context context;
    private final List<Object> listRecyclerItem;


    public UserClosedLeadsRecyclerAdapter(Context context, List<Object> listRecyclerItem) {
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
                        R.layout.users_myclosed_list_item, viewGroup, false);
                return new UserClosedLeadsRecyclerAdapter.ItemViewHolder((layoutView));
        }

    }
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

        int viewType = getItemViewType(i);

        switch (viewType) {
            case TYPE:
            default:

                UserClosedLeadsRecyclerAdapter.ItemViewHolder itemViewHolder = (UserClosedLeadsRecyclerAdapter.ItemViewHolder) viewHolder;
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
