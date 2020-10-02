package com.example.hivemanager.ui.profile;

import android.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import com.example.hivemanager.MainActivity;
import com.example.hivemanager.R;

public class ProfileFragment extends Fragment {

    private TextView et_userName;
    private TextView et_email;
    private TextView et_phone;
    private TextView et_address;

    public ProfileFragment(){

    }

     public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
         View view = inflater.inflate(R.layout.fragment_profile, container, false);

         et_userName = view.findViewById(R.id.profile_name);
         et_email = view.findViewById(R.id.profile_email);
         et_phone = view.findViewById(R.id.profile_phone);
         et_address = view.findViewById(R.id.profile_address);

         et_userName.setText(String.format("%s %s", MainActivity.getUser().getFirstname(), MainActivity.getUser().getLastname()));
         et_email.setText(MainActivity.getUser().getEmail());
         et_phone.setText(MainActivity.getUser().getPhone());
         et_address.setText(String.format("%s \nMadison, WI %s", MainActivity.getUser().getAddress(), MainActivity.getUser().getZipcode()));

         Button editProfileBtn = (Button) view.findViewById(R.id.button_editProfile);
         editProfileBtn.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 EditProfileFragment editProfileFragment = new EditProfileFragment();
                 FragmentTransaction transaction = getFragmentManager().beginTransaction();
                 transaction.replace(R.id.nav_host_fragment, editProfileFragment);
                 transaction.commit();
             }
         });
         return view;
    }
    public void editProfile(View view) {
    }

    public void saveSelection(View view) {
    }
}