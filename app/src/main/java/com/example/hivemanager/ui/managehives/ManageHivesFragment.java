package com.example.hivemanager.ui.managehives;

import android.os.Bundle;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hivemanager.MainActivity;
import com.example.hivemanager.R;

public class ManageHivesFragment extends Fragment {

    private RecyclerView hiveRecyclerView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_managehives, container, false);
        hiveRecyclerView = view.findViewById(R.id.hiveRecycle);
        hiveRecyclerView.setHasFixedSize(true);
        hiveRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        hiveRecyclerView.setAdapter(new HiveAdapter());
        return view;
    }
}
