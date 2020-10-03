package com.example.hivemanager.ui.hivestatus;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hivemanager.MainActivity;
import com.example.hivemanager.R;

public class EquipmentFragment extends Fragment {
    private int apiaryPosition;
    private int hivePosition;
    private TextView hiveEquipment;
    private ImageView addInventoryButton;
    private RecyclerView inventoryRV;
    private EquipmentAdapter inventoryAdapter;
    private RecyclerView.LayoutManager inventoryLayoutManager;
    private ImageView addInventory;



    public EquipmentFragment(int apiaryPosition, int hivePosition) {
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
        View view = inflater.inflate(R.layout.fragment_equipment, container, false);

        inventoryRV = view.findViewById(R.id.inventoryRecycle);
        addInventory = view.findViewById(R.id.addInventory_button);
        inventoryRV.setHasFixedSize(true);

        inventoryLayoutManager = new LinearLayoutManager(getActivity());
        inventoryAdapter = new EquipmentAdapter(MainActivity.getUser().getApiaries().get(apiaryPosition).getHives().get(hivePosition).getEquipment());

        inventoryRV.setLayoutManager(inventoryLayoutManager);
        inventoryRV.setAdapter(inventoryAdapter);

        inventoryAdapter.setOnItemClickListener(new EquipmentAdapter.onItemClickListener() {
            @Override
            public void onDeleteClick(int position) {
                deleteInventory(position);
            }

            @Override
            public void onEditClick(int position) {
                editInventory(position);

            }
        });

        addInventory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Fragment fragment = new AddInventory(inventoryAdapter, apiaryPosition, hivePosition);
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.nav_host_fragment, fragment);
                transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
                transaction.commit();

            }
        });

        return view;
    }

    private void deleteInventory(int position) {
        final int inspectionPosition = position;
        new AlertDialog.Builder(getContext())
                .setTitle("Delete Equipment")
                .setMessage("Are you sure you want to delete this piece of equipment?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int position) {
                        MainActivity.getUser().getApiaries().get(apiaryPosition).getHives().get(hivePosition).deleteEquipment(inspectionPosition);
                        inventoryAdapter.notifyDataSetChanged();
                    }
                })
                .setNegativeButton(android.R.string.cancel, null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    private void editInventory(int position) {
        Fragment fragment = new EditEquipment(inventoryAdapter, apiaryPosition, hivePosition, position);
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.nav_host_fragment, fragment);
        transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
        transaction.commit();


    }
}