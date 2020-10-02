package com.example.hivemanager.ui.hivestatus;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hivemanager.R;

public class EquipmentFragment extends Fragment {
    private int apiaryPosition;
    private int hivePosition;
    private TextView hiveEquipment;
    private TextView inventoryEquipment;
    private String sHiveEquipment;
    private String sInvE;



    public EquipmentFragment(int apiaryPosition, int hivePosition) {
        this.apiaryPosition = apiaryPosition;
        this.hivePosition = hivePosition;

        sHiveEquipment = "";
        sInvE = "";
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_equipment, container, false);

        hiveEquipment = view.findViewById(R.id.hiveEquipment_value);
        inventoryEquipment = view.findViewById(R.id.inventoryEquipment_value);

        //TODO loop through equipment stored and format the string

        hiveEquipment.setText(sHiveEquipment);
        inventoryEquipment.setText(sInvE);
        return view;
    }
}