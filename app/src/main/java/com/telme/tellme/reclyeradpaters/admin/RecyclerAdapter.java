package com.telme.tellme.reclyeradpaters.admin;

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
import com.telme.tellme.Holidays;
import com.telme.tellme.IntrestedCustomer;
import com.telme.tellme.R;

import java.io.IOException;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE = 1;
    private final Context context;
    private final List<Object> listRecyclerItem;

    public RecyclerAdapter(Context context, List<Object> listRecyclerItem) {
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
            checkBoxInrested = (CheckBox) itemView.findViewById(R.id.fresh_data_intrested);
            checkBoxNotIntrested = (CheckBox) itemView.findViewById(R.id.fresh_data_notintrested);
            checkBoxNotAnswered = (CheckBox) itemView.findViewById(R.id.fresh_data_notanswered);
            final ObjectMapper objectMapper = new ObjectMapper();
            checkBoxInrested.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    // makes the set disappear when checkbox is ticked.
                    if(isChecked){
                        try {
                            objectMapper.readValue(listRecyclerItem
                                    .get(getAdapterPosition()).toString(), IntrestedCustomer.class);

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        listRecyclerItem.remove(getAdapterPosition());
                        notifyItemRemoved(getAdapterPosition());
                        Toast.makeText(checkBoxInrested.getContext(), "Done!", Toast.LENGTH_LONG).show();
                    }

                }

            });
            checkBoxNotIntrested.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    // makes the set disappear when checkbox is ticked.
                    if(isChecked){
                        listRecyclerItem.remove(getAdapterPosition());
                        notifyItemRemoved(getAdapterPosition());
                        Toast.makeText(checkBoxInrested.getContext(), "Done!", Toast.LENGTH_LONG).show();
                    }

                }

            });


        }

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        switch (i) {
            case TYPE:

            default:

                View layoutView = LayoutInflater.from(viewGroup.getContext()).inflate(
                        R.layout.activity_user_fresh_data, viewGroup, false);



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
