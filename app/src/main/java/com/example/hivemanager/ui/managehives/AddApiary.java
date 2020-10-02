package com.example.hivemanager.ui.managehives;

import android.os.Bundle;
import android.widget.Button;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import com.example.hivemanager.DatabaseHelper;
import com.example.hivemanager.MainActivity;
import com.example.hivemanager.R;

public class AddApiary extends Fragment {
    private Button addApiaryBtn;
    private EditText address;
    private EditText zipCode;
    private String apiaryAddress;
    private String apiaryZipCode;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_add_apiary, container, false);
        addApiaryBtn = (Button) view.findViewById(R.id.addApiaryButton);
        address = (EditText) view.findViewById(R.id.newApiaryAddress);
        apiaryAddress = address.getText().toString();
        zipCode = (EditText) view.findViewById(R.id.newApiaryZipCode);
        apiaryZipCode = zipCode.getText().toString();
        addApiaryBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick (View v) {
                try {
                    DatabaseHelper.addApiary(MainActivity.userName,apiaryAddress,apiaryZipCode);
                } catch (Exception e) {
                    //TODO: error msg
                }
            }
        });
        // Inflate the layout for this fragment
        return view;
    }

    //public class registerApiary
}
