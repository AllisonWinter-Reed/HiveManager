package com.example.hivemanager.ui.hivestatus;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.hivemanager.Inspection;
import com.example.hivemanager.MainActivity;
import com.example.hivemanager.R;

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



        for(Inspection inspection : MainActivity.getUser().getApiaries().get(apiaryPosition).getHives().get(hivePosition).getInspections()) {
                inspectionString = inspectionString + String.format("%s\n%s\n\n", inspection.getDate(), inspection.getResult());
        }

        inspections.setText(inspectionString);
        return view;

    }

}