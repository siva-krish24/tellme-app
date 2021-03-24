package com.telme.tellme;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdminRecylerAdapter extends  RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE = 1;
    private final Context context;
    private final List<Object> listRecyclerItem;


    public AdminRecylerAdapter(Context context, List<Object> listRecyclerItem) {
        this.context = context;
        this.listRecyclerItem = listRecyclerItem;
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {

        private TextView name;
        private TextView mobile;
        private TextView varient;
        private TextView hypo;
        private Button makeLogin;


        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
            mobile = (TextView) itemView.findViewById(R.id.mobile);
            varient = (TextView) itemView.findViewById(R.id.variant);
            hypo = (TextView) itemView.findViewById(R.id.hypo);
            makeLogin = (Button) itemView.findViewById(R.id.makelogin);
            makeLogin.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent myIntent = new Intent(LoginActivity.this, UserRegActivity.class);
                            startActivity(myIntent);
                        }
                    }
            );
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        switch (i) {
            case TYPE:

            default:

                View layoutView = LayoutInflater.from(viewGroup.getContext()).inflate(
                        R.layout.admin_list_item, viewGroup, false);

                return new ItemViewHolder((layoutView));
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

        int viewType = getItemViewType(i);

        switch (viewType) {
            case TYPE:
            default:

                ItemViewHolder itemViewHolder = (ItemViewHolder) viewHolder;
                Holidays holidays = (Holidays) listRecyclerItem.get(i);

                itemViewHolder.name.setText(holidays.getName());
                itemViewHolder.mobile.setText(holidays.getMobile());
                itemViewHolder.varient.setText(holidays.getVarient());
                itemViewHolder.hypo.setText(holidays.getHypo());

        }

    }

    @Override
    public int getItemCount() {
        return listRecyclerItem.size();
    }


}
