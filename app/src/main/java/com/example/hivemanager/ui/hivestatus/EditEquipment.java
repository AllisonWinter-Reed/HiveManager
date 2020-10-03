package com.example.hivemanager.ui.hivestatus;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.hivemanager.MainActivity;
import com.example.hivemanager.R;

import java.text.SimpleDateFormat;

public class EditEquipment extends Fragment {

    EquipmentAdapter inventoryAdapter;
    int apiaryPosition;
    int hivePosition;
    EditText equipmentName;
    Button saveInventoryButton;
    private int inventoryPosition;
    EditText editInventoryHeader;

    public EditEquipment(EquipmentAdapter inventoryAdapter, int apiaryPosition, int hivePosition, int inventoryPosition) {
        this.inventoryAdapter = inventoryAdapter;
        this.inventoryPosition = inventoryPosition;
        this.hivePosition = hivePosition;
        this.apiaryPosition = apiaryPosition;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_add_inspection, container, false);
        equipmentName = view.findViewById(R.id.equipment_input);

        saveInventoryButton = (Button) view.findViewById(R.id.addInspection_button);
        editInventoryHeader = view.findViewById(R.id.newInspectionHeader);

        saveInventoryButton.setText("Save");

        editInventoryHeader.setText("Edit Equipment");

        saveInventoryButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Log.d("SAVE CLICKED", "SAVE CLICKED");
                String equipmentNameS = equipmentName.getText().toString();

                MainActivity.getUser().getApiaries().get(apiaryPosition).getHives().get(hivePosition).editInventory(inventoryPosition, equipmentNameS, apiaryPosition);
                inventoryAdapter.notifyDataSetChanged();

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
