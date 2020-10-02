package com.example.hivemanager.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.hivemanager.LoginActivity;
import com.example.hivemanager.MainActivity;
import com.example.hivemanager.R;

public class HomeFragment extends Fragment {


    ImageView img_logout;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_home, container, false);


        img_logout = view.findViewById(R.id.image_logout);

        img_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                {
                    Intent intent = new Intent(getContext(), LoginActivity.class);
                    startActivity(intent);
                    MainActivity.userName = "";
                }
            }
        });

        return view;

    }
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
