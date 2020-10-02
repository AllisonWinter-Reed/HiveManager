package com.example.hivemanager.ui.hivestatus;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hivemanager.MainActivity;
import com.example.hivemanager.R;


public class MetricsFragment extends Fragment {
    private int apiaryPosition;
    private int hivePosition;
    private TextView honeyStores;
    private TextView queenProduction;
    private TextView gains;
    private TextView losses;

    public MetricsFragment(int apiaryPosition, int hivePosition) {
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
        View view = inflater.inflate(R.layout.fragment_metrics, container, false);
        honeyStores = view.findViewById(R.id.honeyStores_value);
        queenProduction = view.findViewById(R.id.queenProduction_value);
        gains = view.findViewById(R.id.gains_value);
        losses = view.findViewById(R.id.losses_value);

        honeyStores.setText(String.format("%d", MainActivity.getUser().getApiaries().get(apiaryPosition).getHives().get(hivePosition).getHoneyStores()));
        queenProduction.setText(String.format("%d", MainActivity.getUser().getApiaries().get(apiaryPosition).getHives().get(hivePosition).getQueenProduction()));
        gains.setText(String.format("%d", MainActivity.getUser().getApiaries().get(apiaryPosition).getHives().get(hivePosition).getGains()));
        losses.setText(String.format("%d", MainActivity.getUser().getApiaries().get(apiaryPosition).getHives().get(hivePosition).getLosses()));
        return view;
    }
}