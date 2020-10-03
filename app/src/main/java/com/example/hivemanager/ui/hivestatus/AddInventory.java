package com.example.hivemanager.ui.hivestatus;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.hivemanager.Inspection;
import com.example.hivemanager.MainActivity;
import com.example.hivemanager.R;

public class AddInventory extends Fragment {

    EquipmentAdapter equipmentAdapter;
    int apiaryPosition;
    int hivePosition;
    EditText equipmentName;
    Button addInspectionButton;


    public AddInventory(EquipmentAdapter equipmentAdapter, int apiaryPosition, int hivePosition) {
        this.equipmentAdapter = equipmentAdapter;
        this.apiaryPosition = apiaryPosition;
        this.hivePosition = hivePosition;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_add_inventory, container, false);

        equipmentName = view.findViewById(R.id.equipment_input);
        addInspectionButton = view.findViewById(R.id.addInspection_button);

        addInspectionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String equipmentS = equipmentName.getText().toString();

                MainActivity.getUser().getApiaries().get(apiaryPosition).getHives().get(hivePosition).addInventory(equipmentS, apiaryPosition);

                Fragment fragment = new HiveStatusFragment(apiaryPosition, hivePosition);
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.nav_host_fragment, fragment);
                transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
                transaction.commit();
            }
        });

        return view;
    }
}