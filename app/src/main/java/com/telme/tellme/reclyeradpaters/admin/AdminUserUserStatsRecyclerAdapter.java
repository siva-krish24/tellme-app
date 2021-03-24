package com.telme.tellme.reclyeradpaters.admin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.telme.tellme.Customer;
import com.telme.tellme.R;
import com.telme.tellme.States;
import com.telme.tellme.UserCustomerPojo;
import com.telme.tellme.UserDate;

import java.io.IOException;
import java.util.List;

public class AdminUserUserStatsRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private static final int TYPE = 1;
    private final Context context;
    private final List<Object> listRecyclerItem;

    public AdminUserUserStatsRecyclerAdapter(Context context, List<Object> listRecyclerItem) {
        this.context = context;
        this.listRecyclerItem = listRecyclerItem;
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {

        private TextView totalcalls;
        private TextView followups;
        private TextView notintrestedcalls;
        private TextView notansweredcalls;
        private TextView logins;
        private TextView leadsclosed;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            totalcalls = (TextView) itemView.findViewById(R.id.totalcalls);
            followups = (TextView) itemView.findViewById(R.id.intrestedcalls);
            notansweredcalls =  (TextView) itemView.findViewById(R.id.notansweredcalls);
            notintrestedcalls = (TextView) itemView.findViewById(R.id.notintrestedcalls);
            logins = (TextView) itemView.findViewById(R.id.logins);
            leadsclosed =  (TextView) itemView.findViewById(R.id.closedleads);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        switch (i) {
            case TYPE:

            default:

                View layoutView = LayoutInflater.from(viewGroup.getContext()).inflate(
                        R.layout.admin_user_stats_list_item, viewGroup, false);
                return new AdminUserUserStatsRecyclerAdapter.ItemViewHolder((layoutView));
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

        int viewType = getItemViewType(i);

        switch (viewType) {
            case TYPE:
            default:

                AdminUserUserStatsRecyclerAdapter.ItemViewHolder itemViewHolder = (AdminUserUserStatsRecyclerAdapter.ItemViewHolder) viewHolder;
                States states = (States) listRecyclerItem.get(i);
                itemViewHolder.totalcalls.setText("TotalCalls: " + states.getTotal());
                itemViewHolder.followups.setText("FollowUps: " + states.getFollowups());
                itemViewHolder.notansweredcalls.setText("NotAnswered: " + states.getNotanswered());
                itemViewHolder.notintrestedcalls.setText("NotIntrested: " + states.getNotintrested());
                itemViewHolder.logins.setText("Logins: " + states.getLogins());
                itemViewHolder.leadsclosed.setText("Leads Closed: " + states.getClosed());
        }

    }


    @Override
    public int getItemCount() {
        return listRecyclerItem.size();
    }
}
