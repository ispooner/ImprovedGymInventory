package com.example.improvedgyminventory;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;



public class ItemEntryFragment extends Fragment {

    public interface ItemCountListener {
        void onSubmit(GymItem item);
    }

    private ItemCountListener listener;

    TextView nameView;
    EditText countEdit;
    Button submitButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_entry, container, false);
        nameView = view.findViewById(R.id.item_name_textView);
        countEdit = view.findViewById(R.id.itemCount_editView);
        submitButton = view.findViewById(R.id.inventory_button);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final GymItem item = (GymItem) getArguments().getParcelable("item_key");

        if(item != null) {
            nameView.setText(item.getItemName());
            countEdit.setText(" ");
            submitButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    try {
                        Integer toAdd = Integer.parseInt(countEdit.getText().toString().trim());
                        item.setCount(item.getCount() + toAdd);
                        listener.onSubmit(item);
                        getActivity().getSupportFragmentManager().popBackStack();
                    } catch (NumberFormatException e) {
                        Toast.makeText(getContext(), "Must be integer", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof ItemCountListener) {
            this.listener = (ItemCountListener)context;
        }
    }
}


























