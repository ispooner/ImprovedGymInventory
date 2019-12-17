package com.example.improvedgyminventory.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;

import com.example.improvedgyminventory.GymItem;
import com.example.improvedgyminventory.R;

import java.util.List;

public class ItemCountRVAdapter extends Adapter<ItemCountRVAdapter.GymItemViewHolder> {

    private List<GymItem> items;

    public ItemCountRVAdapter(List<GymItem> its) {
        items = its;
    }


    protected class GymItemViewHolder extends RecyclerView.ViewHolder {
        TextView itemTextView;

        public GymItemViewHolder(View itemView) {
            super(itemView);
            itemTextView = itemView.findViewById(R.id.item_textView);
        }
    }

    @NonNull
    @Override
    public ItemCountRVAdapter.GymItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rv_entry, parent, false);

        return new ItemCountRVAdapter.GymItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemCountRVAdapter.GymItemViewHolder holder, final int position) {
        holder.itemTextView.setText(items.get(position).getItemName() + ": " + items.get(position).getCount());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
