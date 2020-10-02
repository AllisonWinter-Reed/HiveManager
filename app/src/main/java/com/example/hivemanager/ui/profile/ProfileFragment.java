package com.example.hivemanager.ui.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProviders;

import com.example.hivemanager.R;

public class ProfileFragment extends Fragment {

     public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
         View view = inflater.inflate(R.layout.fragment_profile, container, false);
         Button editProfileBtn = (Button) view.findViewById(R.id.button_editProfile);
         editProfileBtn.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 EditProfileFragment editProfileFragment = new EditProfileFragment();
                 FragmentManager manager = getChildFragmentManager();
                 manager.beginTransaction().replace(R.id.frag_container_profile, editProfileFragment,
                         editProfileFragment.getTag()).commit();
             }
         });
         return view;
    }
    public void editProfile(View view) {
    }

    public void saveSelection(View view) {
    }
}