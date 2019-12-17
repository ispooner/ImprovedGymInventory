package com.example.improvedgyminventory;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.improvedgyminventory.adapters.ItemCountRVAdapter;
import com.example.improvedgyminventory.adapters.ItemRVAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ItemRVAdapter.ItemDelegate, ItemEntryFragment.ItemCountListener {

    InventoryDatabaseHelper dbHelper;

    ItemRVAdapter itemRVAdapter;
    ItemCountRVAdapter itemCountRVAdapter;

    private ItemEntryFragment entryFragment = new ItemEntryFragment();

    RecyclerView itemRecycler;
    RecyclerView itemCountRecycler;

    ArrayList<GymItem> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new InventoryDatabaseHelper(this, null, null, 1);

        items = dbHelper.getItems();
        if(items.size() == 0) {
            GymItem dumbbell = new GymItem("Dumbbells", 0);
            GymItem treadmill = new GymItem("Treadmill", 0);
            GymItem weights = new GymItem("Weights", 0);
            items.add(dumbbell);
            items.add(treadmill);
            items.add(weights);
            dbHelper.insertItem(dumbbell);
            dbHelper.insertItem(treadmill);
            dbHelper.insertItem(weights);
        }

        itemRVAdapter = new ItemRVAdapter(items, this);
        itemCountRVAdapter = new ItemCountRVAdapter(items);

        LinearLayoutManager itemManager = new LinearLayoutManager(this);
        LinearLayoutManager itemCountManager = new LinearLayoutManager(this);

        itemRecycler = findViewById(R.id.itemRecycler);
        itemRecycler.setLayoutManager(itemManager);
        itemRecycler.setAdapter(itemRVAdapter);
        itemCountRecycler = findViewById(R.id.itemCountRecycler);
        itemCountRecycler.setLayoutManager(itemCountManager);
        itemCountRecycler.setAdapter(itemCountRVAdapter);
    }

    @Override
    public void clickItem(GymItem item) {
        Toast.makeText(this, item.getItemName() + ": " + item.getCount(), Toast.LENGTH_LONG).show();
        Bundle bundle = new Bundle();
        bundle.putParcelable("item_key", item);
        entryFragment.setArguments(bundle);
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_frame, entryFragment)
                .addToBackStack(entryFragment.getTag())
                .commit();
    }

    @Override
    public void onSubmit(GymItem item) {
        dbHelper.updateItem(item);
        for(GymItem cur : items) {
            if(cur.getItemName().equals(item.getItemName())) {
                cur.setCount(item.getCount());
            }
        }
        itemCountRVAdapter.notifyDataSetChanged();
    }
}
