package com.example.hivemanager.ui.managehives;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.hivemanager.MainActivity;
import com.example.hivemanager.R;

public class EditHive extends Fragment {

    private Button saveBtn;
    private EditText tHealth;
    private EditText tHoneyStores;
    private EditText tQueenProduction;
    private EditText tGains;
    private EditText tLosses;
    private HiveAdapter hiveAdapter;
    private TextView editHiveHeader;

    private int apiaryPosition;
    private int hivePosition;

    public EditHive(HiveAdapter hiveAdapter, int apiaryPosition, int hivePosition) {
        this.hiveAdapter = hiveAdapter;
        this.apiaryPosition = apiaryPosition;
        this.hivePosition = hivePosition;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_add_hive, container, false);

        saveBtn = (Button) view.findViewById(R.id.addHiveFinalStep);
        tHealth = view.findViewById(R.id.health_editText);
        tHoneyStores = view.findViewById(R.id.honeyStores_editText);
        tQueenProduction = view.findViewById(R.id.queenProduction_editText);
        tGains = view.findViewById(R.id.gains_editText);
        tLosses = view.findViewById(R.id.losses_editText);


        editHiveHeader = view.findViewById(R.id.newHiveHeader);

        saveBtn.setText("Save");
        tHealth.setText(String.format("%d",MainActivity.getUser().getApiaries().get(apiaryPosition).getHives().get(hivePosition).getHealth()));
        tHoneyStores.setText(String.format("%d",MainActivity.getUser().getApiaries().get(apiaryPosition).getHives().get(hivePosition).getHoneyStores()));
        tQueenProduction.setText(String.format("%d",MainActivity.getUser().getApiaries().get(apiaryPosition).getHives().get(hivePosition).getQueenProduction()));
        tGains.setText(String.format("%d",MainActivity.getUser().getApiaries().get(apiaryPosition).getHives().get(hivePosition).getGains()));
        tLosses.setText(String.format("%d",MainActivity.getUser().getApiaries().get(apiaryPosition).getHives().get(hivePosition).getLosses()));

        editHiveHeader.setText("Edit Hive");

        saveBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Log.d("SAVE CLICKED", "SAVE CLICKED");


                int health = Integer.parseInt(tHealth.getText().toString());
                int honeyStores = Integer.parseInt(tHoneyStores.getText().toString());
                int queenProduction = Integer.parseInt(tQueenProduction.getText().toString());
                int gains = Integer.parseInt(tGains.getText().toString());
                int losses = Integer.parseInt(tLosses.getText().toString());
//
                MainActivity.getUser().editHive(apiaryPosition, hivePosition, health, honeyStores, queenProduction, gains, losses);
                hiveAdapter.notifyDataSetChanged();
//
                Fragment fragment = new ManageHivesFragment(apiaryPosition);
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.nav_host_fragment, fragment);
                transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
                transaction.commit();
            }
        });

        return view;
    }
}
