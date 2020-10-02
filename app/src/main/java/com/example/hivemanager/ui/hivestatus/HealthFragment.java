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

import com.example.hivemanager.MainActivity;
import com.example.hivemanager.R;

public class HealthFragment extends Fragment {
    private int apiaryPosition;
    private int hivePosition;
    private TextView healthValue;
    private TextView inspections;
    private String inspectionString;

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

        inspections.setText(inspectionString);

        View view = inflater.inflate(R.layout.fragment_health, container, false);

        healthValue = getView().findViewById(R.id.health_value);
        inspections = getView().findViewById(R.id.inspections_value);
        healthValue.setText(String.format("%d", MainActivity.getUser().getApiaries().get(apiaryPosition).getHives().get(hivePosition).getHealth()));

        //TODO loop through inspection data in the data base to populate inspection string

        inspections.setText(inspectionString);
        return view;

    }

}