package com.example.hivemanager.ui.hivestatus;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.hivemanager.Apiary;
import com.example.hivemanager.Inspection;
import com.example.hivemanager.MainActivity;
import com.example.hivemanager.R;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;

public class EditInspection extends Fragment {

    private Button saveBtn;
    private EditText date;
    private EditText result;
    private InspectionAdapter inspectionAdapter;
    private TextView editInspectionHeader;

    private int apiaryPosition;
    private int hivePosition;
    private int inspectionPosition;

    public EditInspection(InspectionAdapter inspectionAdapter, int apiaryPosition, int hivePosition, int inspectionPosition) {
        this.inspectionAdapter = inspectionAdapter;
        this.inspectionPosition = inspectionPosition;
        this.hivePosition = hivePosition;
        this.apiaryPosition = apiaryPosition;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_add_inspection, container, false);

        saveBtn = (Button) view.findViewById(R.id.addInspection_button);
        date = (EditText) view.findViewById(R.id.inspectionDate_input);
        result = (EditText) view.findViewById(R.id.inspectionMessage);
        editInspectionHeader = view.findViewById(R.id.newInspectionHeader);

        saveBtn.setText("Save");
        String dateS = new SimpleDateFormat("dd-MM-yyyy").format(MainActivity.getUser().getApiaries().get(apiaryPosition).getHives().get(hivePosition).getInspections().get(inspectionPosition).getDate());
        date.setText(dateS);
        result.setText(MainActivity.getUser().getApiaries().get(apiaryPosition).getHives().get(hivePosition).getInspections().get(inspectionPosition).getResult());
        editInspectionHeader.setText("Edit Inspection");

        saveBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Log.d("SAVE CLICKED", "SAVE CLICKED");


                String dateS = date.getText().toString();
                String resultS = result.getText().toString();

                MainActivity.getUser().getApiaries().get(apiaryPosition).getHives().get(hivePosition).editInspection(inspectionPosition, dateS, resultS);
                inspectionAdapter.notifyDataSetChanged();

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
