package com.example.hivemanager.ui.profile;


import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.hivemanager.MainActivity;
import com.example.hivemanager.R;
import com.example.hivemanager.RegisterActivity;

import java.io.FileNotFoundException;
import java.io.IOException;


public class EditProfileFragment extends Fragment {

    private ImageView addPhoto;
    private TextView username;
    private EditText firstName;
    private EditText lastName;
    private EditText address;
    private EditText zip;
    private EditText phone;
    private EditText email;
    private ProfileAdapter profileAdapter;
    private int profilePosition;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_editprofile, container, false);

        username = (TextView) view.findViewById(R.id.etUsernameProfile);
        firstName = (EditText) view.findViewById(R.id.etFirstNameProfile);
        lastName = (EditText) view.findViewById(R.id.etLastNameProfile);
        address = (EditText) view.findViewById(R.id.etAddressProfile);
        zip = (EditText) view.findViewById(R.id.etZipProfile);
        phone = (EditText) view.findViewById(R.id.etPhoneNumberProfile);
        email = (EditText) view.findViewById(R.id.etEmailProfile);
        addPhoto = (ImageView) view.findViewById(R.id.profile_pic);

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
