package com.example.hivemanager.ui.hivestatus;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hivemanager.Inspection;
import com.example.hivemanager.MainActivity;
import com.example.hivemanager.R;
import com.example.hivemanager.ui.managehives.EditApiary;

public class HealthFragment extends Fragment {
    private int apiaryPosition;
    private int hivePosition;
    private TextView healthValue;
    private TextView inspections;
    private String inspectionString;
    private TextView honeyStores;
    private TextView queenProduction;
    private TextView gains;
    private TextView losses;
    private RecyclerView inspectionRV;
    private InspectionAdapter inspectionAdapter;
    private RecyclerView.LayoutManager inspectionLayoutManager;
    private ImageView addInspection;

    public HealthFragment(int apiaryPosition, int hivePosition) {
        this.apiaryPosition = apiaryPosition;
        this.hivePosition = hivePosition;
        inspectionString = "";
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_health, container, false);

        inspectionRV = view.findViewById(R.id.inspectionRecycle);
        addInspection = view.findViewById(R.id.addInspection_button);
        inspectionRV.setHasFixedSize(true);

        inspectionLayoutManager = new LinearLayoutManager(getActivity());
        inspectionAdapter = new InspectionAdapter(MainActivity.getUser().getApiaries().get(apiaryPosition).getHives().get(hivePosition).getInspections());

        inspectionRV.setLayoutManager(inspectionLayoutManager);
        inspectionRV.setAdapter(inspectionAdapter);

        inspectionAdapter.setOnItemClickListener(new InspectionAdapter.onItemClickListener() {
            @Override
            public void onDeleteClick(int position) {
                deleteInspection(position);
            }

            @Override
            public void onEditClick(int position) {
                editInspection(position);

            }
        });

        addInspection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Fragment fragment = new AddInspection(inspectionAdapter, apiaryPosition, hivePosition);
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.nav_host_fragment, fragment);
                transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
                transaction.commit();

            }
        });

        healthValue = view.findViewById(R.id.health_value);
        honeyStores = view.findViewById(R.id.honeyStores_value);
        queenProduction = view.findViewById(R.id.queenProduction_value);
        gains = view.findViewById(R.id.gains_value);
        losses = view.findViewById(R.id.losses_value);
        healthValue.setText(String.format("%d", MainActivity.getUser().getApiaries().get(apiaryPosition).getHives().get(hivePosition).getHealth()));
        honeyStores.setText(String.format("%d", MainActivity.getUser().getApiaries().get(apiaryPosition).getHives().get(hivePosition).getHoneyStores()));
        queenProduction.setText(String.format("%d", MainActivity.getUser().getApiaries().get(apiaryPosition).getHives().get(hivePosition).getQueenProduction()));
        gains.setText(String.format("%d", MainActivity.getUser().getApiaries().get(apiaryPosition).getHives().get(hivePosition).getGains()));
        losses.setText(String.format("%d", MainActivity.getUser().getApiaries().get(apiaryPosition).getHives().get(hivePosition).getLosses()));

        Log.d("INSPECTION SIZE", String.format("%d", MainActivity.getUser().getApiaries().get(apiaryPosition).getHives().get(hivePosition).getInspections().size()));
        return view;

    }

    private void deleteInspection(int position) {
        final int inspectionPosition = position;
        new AlertDialog.Builder(getContext())
                .setTitle("Delete Inspection")
                .setMessage("Are you sure you want to delete this inspection?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int position) {
                        MainActivity.getUser().getApiaries().get(apiaryPosition).getHives().get(hivePosition).deleteInspection(inspectionPosition);
                        inspectionAdapter.notifyDataSetChanged();
                    }
                })
                .setNegativeButton(android.R.string.cancel, null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    private void editInspection(int position) {
        Fragment fragment = new EditInspection(inspectionAdapter, apiaryPosition, hivePosition, position);
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.nav_host_fragment, fragment);
        transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
        transaction.commit();

    }


}