package com.example.improvedgyminventory.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.improvedgyminventory.GymItem;
import com.example.improvedgyminventory.R;

import java.util.List;

public class ItemRVAdapter extends RecyclerView.Adapter<ItemRVAdapter.GymItemViewHolder> {

    private List<GymItem> items;
    private ItemDelegate delegate;

    public ItemRVAdapter(List<GymItem> its, ItemDelegate del) {
        items = its;
        delegate = del;
    }

    public interface ItemDelegate {
        void clickItem(GymItem item);
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
    public GymItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rv_entry, parent, false);

        return new GymItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull GymItemViewHolder holder, final int position) {
        holder.itemTextView.setText(items.get(position).getItemName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delegate.clickItem(items.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
