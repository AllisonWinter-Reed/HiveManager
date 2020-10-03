package com.example.hivemanager.ui.profile;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.hivemanager.DatabaseHelper;
import com.example.hivemanager.MainActivity;
import com.example.hivemanager.R;
import com.example.hivemanager.RegisterActivity;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;


public class EditProfileFragment extends Fragment {

    private TextView username;
    private ImageView addPhoto;
    private EditText firstName;
    private EditText lastName;
    private EditText address;
    private EditText zip;
    private EditText phone;
    private EditText email;
    private ProfileAdapter profileAdapter;
    private int profilePosition;
    private Button saveButton;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_editprofile, container, false);

        username = (TextView) view.findViewById(R.id.editUsernameProfile);
        firstName = (EditText) view.findViewById(R.id.editFirstNameProfile);
        lastName = (EditText) view.findViewById(R.id.editLastNameProfile);
        address = (EditText) view.findViewById(R.id.editAddressProfile);
        zip = (EditText) view.findViewById(R.id.editZipProfile);
        phone = (EditText) view.findViewById(R.id.editPhoneNumberProfile);
        email = (EditText) view.findViewById(R.id.editEmailProfile);
        addPhoto = (ImageView) view.findViewById(R.id.profile_pic);
        saveButton = (Button) view.findViewById(R.id.bSaveProfile);
        saveButton.setText("Save");

        username.setText(MainActivity.getUser().getUsername());
        firstName.setHint(MainActivity.getUser().getFirstname());
        lastName.setHint(MainActivity.getUser().getLastname());
        address.setHint(MainActivity.getUser().getAddress());
        zip.setHint(MainActivity.getUser().getAddress());
        phone.setHint(MainActivity.getUser().getPhone());
        email.setHint(MainActivity.getUser().getEmail());
        addPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerProfilePic(view);
            }
        });
        saveButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Log.d("SAVE CLICKED", "SAVE CLICKED");
                String userName = username.getText().toString();
                String newFirstname = firstName.getText().toString();
                String newLastName = lastName.getText().toString();
                String newAddress = address.getText().toString();
                String newZip = zip.getText().toString();
                String newPhone = phone.getText().toString();
                String newEmail = email.getText().toString();

                try {
                    DatabaseHelper.editUser(userName, newFirstname, newLastName, newAddress, newZip, newPhone, newEmail);
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                new AlertDialog.Builder(getContext())
                        .setTitle("Save Changes")
                        .setMessage("These changes will be applied next time you log in")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int position) {
                                Fragment fragment = new ProfileFragment();
                                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                                transaction.replace(R.id.nav_host_fragment, fragment);
                                transaction.addToBackStack(null);
                                transaction.commit();
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();


            }
        });
        return view;
    }

    public static final int GET_FROM_GALLERY = 3;

    public void registerProfilePic(View view) {
        startActivityForResult(new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI), GET_FROM_GALLERY);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        //Detects request codes
        if(requestCode==GET_FROM_GALLERY && resultCode == RegisterActivity.RESULT_OK) {
            Uri selectedImage = data.getData();
            Bitmap bitmap = null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(this.getActivity().getContentResolver(), selectedImage);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
