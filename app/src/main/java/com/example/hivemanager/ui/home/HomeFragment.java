package com.example.hivemanager.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.hivemanager.R;
import com.example.hivemanager.ui.hivestatus.HiveStatusFragment;
import com.example.hivemanager.ui.managehives.ManageHivesFragment;
import com.example.hivemanager.ui.profile.ProfileFragment;

public class HomeFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

//    public void openHiveStatus(View view) {
//        Fragment fragment = new HiveStatusFragment();
//        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//        transaction.replace(((R.id.frag_container_home)), fragment);
//        transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
//        transaction.commit();
//    }
//
//    public void openManageHives(View view) {
//        Fragment fragment = new ManageHivesFragment();
//        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//        transaction.replace(((R.id.frag_container_home)), fragment);
//        transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
//        transaction.commit();
//    }
//
//    public void openProfile(View view) {
//        Fragment fragment = new ProfileFragment();
//        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//        transaction.replace(((R.id.frag_container_home)), fragment);
//        transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
//        transaction.commit();
//    }
}