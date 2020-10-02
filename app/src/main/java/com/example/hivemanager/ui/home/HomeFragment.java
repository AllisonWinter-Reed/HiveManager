package com.example.hivemanager.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.hivemanager.R;

public class HomeFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_home, container, false);
    }

//    public void openHiveStatus(View view) {
//        Fragment fragment = new HiveStatusFragment();
//        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
//        transaction.replace(((R.id.frag_container_home)), fragment);
//        transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
//        transaction.commit();
//    }
//
//    public void openManageHives(View view) {
//        Fragment fragment = new ManageHivesFragment();
//        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
//        transaction.replace(((R.id.frag_container_home)), fragment);
//        transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
//        transaction.commit();
//    }
//
//    public void openProfile(View view) {
//        Fragment fragment = new ProfileFragment();
//        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
//        transaction.replace(((R.id.frag_container_home)), fragment);
//        transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
//        transaction.commit();
//    }
}