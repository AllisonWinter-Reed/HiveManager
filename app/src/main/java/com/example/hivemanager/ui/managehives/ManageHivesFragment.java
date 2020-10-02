package com.example.hivemanager.ui.managehives;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hivemanager.Hive;
import com.example.hivemanager.Hive;
import com.example.hivemanager.Apiary;
import com.example.hivemanager.MainActivity;
import com.example.hivemanager.R;
import com.example.hivemanager.ui.hivestatus.HiveStatusFragment;

import java.util.ArrayList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DriverManager;

public class ManageHivesFragment extends Fragment {

    private int hivePosition;
    private int apiaryPosition;

    private RecyclerView hiveRecyclerView;
    private HiveAdapter hiveAdapter;
    private RecyclerView.LayoutManager hiveLayoutManager;


    public ManageHivesFragment(Integer position) {
                this.apiaryPosition = position;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {super.onCreate(savedInstanceState);}

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_managehives, container, false);
        hiveRecyclerView = view.findViewById(R.id.hiveRecycle);
        hiveRecyclerView.setHasFixedSize(true);
        hiveLayoutManager = new LinearLayoutManager(getActivity());
        hiveAdapter = new HiveAdapter(MainActivity.getUser().getApiaries().get(apiaryPosition).getHives());

        hiveRecyclerView.setLayoutManager(hiveLayoutManager);
        hiveRecyclerView.setAdapter(hiveAdapter);

        hiveAdapter.setOnItemClickListener(new HiveAdapter.onItemClickListener() {
            @Override
            public void onItemClick(int position) {
                hivePosition = position;
                Fragment fragment = new HiveStatusFragment(apiaryPosition, hivePosition);
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.nav_host_fragment, fragment);
                transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
                transaction.commit();
            }
            @Override
            public void onDeleteClick(int position) {
                deleteItem(position);
            }
        });


        return view;
    }

    private void deleteItem(int position) {
        new AlertDialog.Builder(getContext())
                .setTitle("Delete Hive")
                .setMessage("Are you sure you want to delete this Hive?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int position) {
                        MainActivity.getUser().deleteHive(MainActivity.getUser().getApiaries().get(apiaryPosition).getHives().get(position));
                        hiveAdapter.notifyItemRemoved(position);
                    }
                })
                .setNegativeButton(android.R.string.cancel, null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }
}
