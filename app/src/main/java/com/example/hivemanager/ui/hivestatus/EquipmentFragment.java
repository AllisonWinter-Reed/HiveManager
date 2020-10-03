package com.example.hivemanager.ui.hivestatus;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hivemanager.Equipment;
import com.example.hivemanager.MainActivity;
import com.example.hivemanager.R;

public class EquipmentFragment extends Fragment {
    private int apiaryPosition;
    private int hivePosition;
    private TextView hiveEquipment;
    private String sHiveEquipment;



    public EquipmentFragment(int apiaryPosition, int hivePosition) {
        this.apiaryPosition = apiaryPosition;
        this.hivePosition = hivePosition;

        sHiveEquipment = "";
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

        for(Equipment equipment: MainActivity.getUser().getApiaries().get(apiaryPosition).getHives().get(hivePosition).getEquipment()) {
            sHiveEquipment += String.format("%s\n", equipment.getName());
        }

        hiveEquipment.setText(sHiveEquipment);
        return view;
    }
}