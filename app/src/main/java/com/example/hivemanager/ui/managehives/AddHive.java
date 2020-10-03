package com.example.hivemanager.ui.managehives;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.hivemanager.Hive;
import com.example.hivemanager.MainActivity;
import com.example.hivemanager.R;
import com.example.hivemanager.ui.hivestatus.HiveStatusFragment;

public class AddHive extends Fragment {
    EditText tHealth;
    EditText tHoneyStores;
    EditText tQueenProduction;
    EditText tGains;
    EditText tLosses;
    int apiaryPosition;

    Button addHiveBtn;
    HiveAdapter hiveAdapter;

    public AddHive(int apiaryPosition, HiveAdapter hiveAdapter) {
        this.apiaryPosition = apiaryPosition;
        this.hiveAdapter = hiveAdapter;
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_hive, container, false);

        tHealth = view.findViewById(R.id.health_editText);
        tHoneyStores = view.findViewById(R.id.honeyStores_editText);
        tQueenProduction = view.findViewById(R.id.queenProduction_editText);
        tGains = view.findViewById(R.id.gains_editText);
        tLosses = view.findViewById(R.id.losses_editText);
        addHiveBtn = view.findViewById(R.id.addHiveFinalStep);

        addHiveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int health = Integer.parseInt(tHealth.getText().toString());
                int honeyStores = Integer.parseInt(tHoneyStores.getText().toString());
                int queenProduction = Integer.parseInt(tQueenProduction.getText().toString());
                int gains = Integer.parseInt(tGains.getText().toString());
                int losses = Integer.parseInt(tLosses.getText().toString());


                if(health < 0 || health > 100) {
                    tHealth.setTextColor(getResources().getColor(R.color.end_red));

                    //TODO handle incorrect input
                }

                MainActivity.getUser().addHive(new Hive(health, honeyStores, queenProduction, gains, losses), apiaryPosition, hiveAdapter);

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