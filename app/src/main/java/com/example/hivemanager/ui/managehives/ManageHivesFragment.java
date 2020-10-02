package com.example.hivemanager.ui.managehives;

import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hivemanager.Hive;
import com.example.hivemanager.Hive;
import com.example.hivemanager.Apiary;
import com.example.hivemanager.MainActivity;
import com.example.hivemanager.R;

import java.util.ArrayList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DriverManager;

public class ManageHivesFragment extends Fragment {

    private ArrayList<Hive> mHives;
    private Integer position;

    private RecyclerView hiveRecyclerView;

    public ManageHivesFragment(Integer position) {
        Log.d("POSITION", String.format("%d", position));
        this.position = position;
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {



        final View view = inflater.inflate(R.layout.fragment_managehives, container, false);
        hiveRecyclerView = view.findViewById(R.id.hiveRecycle);
        hiveRecyclerView.setHasFixedSize(true);
        hiveRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        Log.d("ManageHives", String.format("%d",MainActivity.getUser().getApiaries().size()));
        Log.d("ManageHives", String.format("%d",MainActivity.getUser().getApiaries().get(position).getHives().size()));
        hiveRecyclerView.setAdapter(new HiveAdapter(MainActivity.getUser().getApiaries().get(position).getHives()));
        return view;
    }
}
