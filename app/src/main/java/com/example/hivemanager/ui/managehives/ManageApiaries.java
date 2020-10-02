package com.example.hivemanager.ui.managehives;
import com.example.hivemanager.Apiary;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
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

    private ArrayList<Hive> mHives;

    private RecyclerView apiaryRecyclerView;

    public ManageApiaries() {
        // Required empty public constructor
    }


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
        apiaryRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        apiaryRecyclerView.setAdapter(new ApiaryAdapter(MainActivity.getUser().getApiaries()));
        return view;


    }
}