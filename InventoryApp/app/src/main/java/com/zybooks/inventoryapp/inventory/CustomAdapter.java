package com.zybooks.inventoryapp.inventory;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.zybooks.inventoryapp.R;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    Activity activity;
    private ArrayList item_id, item_name, item_quantity;


    CustomAdapter(Activity activity, Context context, ArrayList item_id, ArrayList item_name, ArrayList item_quantity) {
        this.activity = activity;
        this.context = context;
        this.item_id = item_id;
        this.item_name = item_name;
        this.item_quantity = item_quantity;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.item_id_txt.setText(String.valueOf(item_id.get(position)));
        holder.item_name_txt.setText(String.valueOf(item_name.get(position)));
        holder.item_quantity_txt.setText(String.valueOf(item_quantity.get(position)));
    }

    @Override
    public int getItemCount() {
        return item_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        LinearLayout inventoryLayout;

        TextView item_id_txt, item_name_txt, item_quantity_txt;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            item_id_txt = itemView.findViewById(R.id.item_id1);
            item_name_txt = itemView.findViewById(R.id.item_name1);
            item_quantity_txt = itemView.findViewById(R.id.item_quantity1);
            inventoryLayout = itemView.findViewById(R.id.inventoryLayout);
        }
    }
}
