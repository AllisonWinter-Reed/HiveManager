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

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.hivemanager.R;
import com.example.hivemanager.RegisterActivity;

import java.io.FileNotFoundException;
import java.io.IOException;


public class EditProfileFragment extends Fragment {

    private ImageView addPhoto;
    private EditText firstname; //TODO
    private EditText lastname;  //TODO
    private EditText address;   // ...
    private EditText zipcode;
    private EditText phonenumber;
    private EditText email;
    private ProfileAdapter profileAdapter;
    private int profilePosition;    // ...

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_editprofile, container, false);

        addPhoto = (ImageView) view.findViewById(R.id.addImage);
        // TODO: Save button
        // TODO: firstname.setText(...) etc.
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
