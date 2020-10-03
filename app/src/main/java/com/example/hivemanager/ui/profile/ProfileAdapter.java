package com.example.hivemanager.ui.profile;

import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hivemanager.Profile;
import com.example.hivemanager.R;

import java.util.ArrayList;


public class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.ProfileNote> {
    static ArrayList<Profile> mProfile;
    private onItemClickListener mListener;

    ProfileAdapter(ArrayList<Profile> mProfile) { ProfileAdapter.mProfile = mProfile; }

    public interface onItemClickListener {
        void onItemClick(int position);
        void onEditClick(int position);
    }

    public void setOnItemClickListener(onItemClickListener listener) { mListener = listener; }

    public static class ProfileNote extends RecyclerView.ViewHolder {
        private ImageView addPhoto;
//        private EditText username;
        private EditText firstname;
        private EditText lastname;
        private EditText address;
        private EditText zipcode;
        private EditText phonenumber;
        private EditText email;
//        private EditText password;
        private Button editProfileButton;


        public ProfileNote(@NonNull View itemView, final ProfileAdapter.onItemClickListener listener) {
            super(itemView);
            addPhoto = itemView.findViewById(R.id.profile_pic);
//            username = itemView.findViewById(R.id.etUsernameProfile);
            firstname = itemView.findViewById(R.id.etFirstNameProfile);
            lastname = itemView.findViewById(R.id.etLastNameProfile);
            address = itemView.findViewById(R.id.etAddressProfile);
            zipcode = itemView.findViewById(R.id.etZipProfile);
            phonenumber = itemView.findViewById(R.id.etPhoneNumberProfile);
            email = itemView.findViewById(R.id.etEmailProfile);
//            password = itemView.findViewById(R.id.etPasswordProfile);
            editProfileButton = itemView.findViewById(R.id.button_editProfile);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });

            editProfileButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onEditClick(position);
                        }
                    }
                }
            });
        }
    }

    @NonNull
    @Override
    public ProfileAdapter.ProfileNote onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_editprofile, parent, false);
        return new ProfileNote(itemView, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ProfileAdapter.ProfileNote holder, int position) {

        Profile holderP = mProfile.get(position);

      //  holder.addPhoto.setImageBitmap(Bitmap, holderP.getProfilePhoto()));               //TODO?????
        holder.firstname.setText(String.format("First Name %d", holderP.getFirstname()));
        holder.lastname.setText(String.format("Last Name %d", holderP.getLastname()));
        holder.address.setText(String.format("Address %d", holderP.getAddress()));
        holder.zipcode.setText(String.format("Zipcode %d", holderP.getZipcode()));
        holder.phonenumber.setText(String.format("Phone Number %d", holderP.getPhone()));
        holder.email.setText(String.format("Email %d", holderP.getEmail()));

    }
    // need to remove class declaration error
    @Override
    public int getItemCount() {
        return mProfile.size();
    }
}
