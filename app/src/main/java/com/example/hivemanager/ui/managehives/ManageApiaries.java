package com.example.hivemanager.ui.managehives;
import com.example.hivemanager.Apiary;

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

import com.example.hivemanager.Apiary;
import com.example.hivemanager.Hive;
import com.example.hivemanager.MainActivity;
import com.example.hivemanager.R;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ManageApiaries extends Fragment {


    private RecyclerView apiaryRecyclerView;
    private ApiaryAdapter apiaryAdapter;
    private RecyclerView.LayoutManager apiaryLayoutManager;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_manage_apiaries, container, false);
        apiaryRecyclerView = view.findViewById(R.id.apiaryRecycle);
        apiaryRecyclerView.setHasFixedSize(true);
        apiaryLayoutManager = new LinearLayoutManager(getActivity());
        apiaryAdapter = new ApiaryAdapter(MainActivity.getUser().getApiaries());

        apiaryRecyclerView.setLayoutManager(apiaryLayoutManager);
        apiaryRecyclerView.setAdapter(apiaryAdapter);

        apiaryAdapter.setOnItemClickListener(new ApiaryAdapter.onItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Fragment fragment = new ManageHivesFragment(position);
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.nav_host_fragment, fragment);
                transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
                transaction.commit();
            }
            @Override
            public void onDeleteClick(int position) {
                deleteItem(position);
            }
            @Override
            public void onEditClick(int position) {
                editItem(position);
            }
        });
        return view;


    }

    private void deleteItem(int position) {
        new AlertDialog.Builder(getContext())
                .setTitle("Delete Apiary")
                .setMessage("Are you sure you want to delete this Apiary?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int position) {
                        MainActivity.getUser().deleteApiary(MainActivity.getUser().getApiaries().get(position));
                        apiaryAdapter.notifyItemRemoved(position);
                    }
                })
                .setNegativeButton(android.R.string.cancel, null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    private void editItem(int position) {

    }
}