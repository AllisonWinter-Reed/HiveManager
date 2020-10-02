package com.example.hivemanager.ui.managehives;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.hivemanager.Apiary;
import com.example.hivemanager.MainActivity;
import com.example.hivemanager.R;

public class EditApiary extends Fragment {

    private Button saveBtn;
    private EditText address;
    private EditText zipCode;
    private ApiaryAdapter apiaryAdapter;
    private int apiaryPosition;

    public EditApiary(ApiaryAdapter apiaryAdapter, int apiaryPosition) {
        this.apiaryAdapter = apiaryAdapter;
        this.apiaryPosition = apiaryPosition;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_add_apiary, container, false);

        saveBtn = (Button) view.findViewById(R.id.addApiaryButton);
        address = (EditText) view.findViewById(R.id.newApiaryAddress);
        zipCode = (EditText) view.findViewById(R.id.newApiaryZipCode);

        saveBtn.setText("Save");
        address.setText(MainActivity.getUser().getApiaries().get(apiaryPosition).getAddress());
        zipCode.setText(MainActivity.getUser().getApiaries().get(apiaryPosition).getZip());

        saveBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Log.d("SAVE CLICKED", "SAVE CLICKED");


//                String newAddress = address.getText().toString();
//                String newZipCode = zipCode.getText().toString();
//
//                MainActivity.getUser().editApiary(apiaryPosition, newAddress, newZipCode);
//                apiaryAdapter.notifyDataSetChanged();
//
//                Fragment fragment = new ManageApiaries();
//                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
//                transaction.replace(R.id.nav_host_fragment, fragment);
//                transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
//                transaction.commit();
            }
        });

        return view;
    }
}
