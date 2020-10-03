package com.example.hivemanager.ui.hivestatus;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.hivemanager.Inspection;
import com.example.hivemanager.MainActivity;
import com.example.hivemanager.R;

public class AddInspection extends Fragment {

    InspectionAdapter inspectionAdapter;
    int apiaryPosition;
    int hivePosition;
    EditText date;
    EditText result;
    Button addInspectionButton;


    public AddInspection(InspectionAdapter inspectionAdapter, int apiaryPosition, int hivePosition) {
        this.inspectionAdapter = inspectionAdapter;
        this.apiaryPosition = apiaryPosition;
        this.hivePosition = hivePosition;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view =  inflater.inflate(R.layout.fragment_add_inspection, container, false);

       date = view.findViewById(R.id.inspectionDate_input);
       result = view.findViewById(R.id.inspectionMessage);
       addInspectionButton = view.findViewById(R.id.addInspection_button);

       addInspectionButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               String dateS = date.getText().toString();
               String resultS = result.getText().toString();

               MainActivity.getUser().getApiaries().get(apiaryPosition).getHives().get(hivePosition).addInspection(dateS, resultS);

               Fragment fragment = new HealthFragment(apiaryPosition, hivePosition);
               FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
               transaction.replace(R.id.nav_host_fragment, fragment);
               transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
               transaction.commit();
           }
       });

       return view;
    }
}