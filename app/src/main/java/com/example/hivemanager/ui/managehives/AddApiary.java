package com.example.hivemanager.ui.managehives;

import android.os.Bundle;
import android.widget.Button;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.hivemanager.Apiary;
import com.example.hivemanager.DatabaseHelper;
import com.example.hivemanager.MainActivity;
import com.example.hivemanager.R;

public class AddApiary extends Fragment {
    private Button addApiaryBtn;
    private EditText address;
    private EditText zipCode;
    private ApiaryAdapter apiaryAdapter;

    public AddApiary(ApiaryAdapter apiaryAdapter) {
        this.apiaryAdapter = apiaryAdapter;
    }

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


        zipCode = (EditText) view.findViewById(R.id.newApiaryZipCode);


        addApiaryBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick (View v) {
                Apiary apiary = new Apiary(address.getText().toString(), zipCode.getText().toString());
                MainActivity.getUser().addApiary(apiary);
                apiaryAdapter.notifyDataSetChanged();

                Fragment fragment = new ManageApiaries();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.nav_host_fragment, fragment);
                transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
                transaction.commit();
            }
        });
        // Inflate the layout for this fragment
        return view;
    }

    //public class registerApiary
}
