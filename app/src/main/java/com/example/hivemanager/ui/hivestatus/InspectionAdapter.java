package com.example.hivemanager.ui.hivestatus;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hivemanager.Apiary;
import com.example.hivemanager.Inspection;
import com.example.hivemanager.R;

import java.util.ArrayList;

public class InspectionAdapter extends RecyclerView.Adapter<InspectionAdapter.InspectionNote> {
    ArrayList<Inspection> mInspection;
    private onItemClickListener mListener;



    InspectionAdapter(ArrayList<Inspection> mInspection) {
        this.mInspection = mInspection;
    }

    public void setOnItemClickListener(InspectionAdapter.onItemClickListener listener) {
        mListener = listener;
    }


    public interface onItemClickListener {
        void onDeleteClick(int position);
        void onEditClick(int position);
    }

    public static class InspectionNote extends RecyclerView.ViewHolder {
        private TextView inspectionDate;
        private TextView inspectionNote;
        private Button deleteInspectionButton;
        private Button editInspectionButton;



        public InspectionNote(@NonNull View itemView, final onItemClickListener listener) {
            super(itemView);
            inspectionDate = itemView.findViewById(R.id.inspectionDate_view);
            inspectionNote = itemView.findViewById(R.id.inspectionNote_view);
            deleteInspectionButton = itemView.findViewById(R.id.trashcan_button);
            editInspectionButton = itemView.findViewById(R.id.editInspection_button);



            deleteInspectionButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION) {
                            listener.onDeleteClick(position);
                        }
                    }
                }
            });

            editInspectionButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION) {
                            listener.onEditClick(position);
                        }
                    }
                }
            });
        }
    }

    @NonNull
    @Override
    public InspectionAdapter.InspectionNote onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.inspection_view, parent, false);
        return new InspectionNote(itemView, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull InspectionAdapter.InspectionNote holder, int position) {

        Inspection holderI = mInspection.get(position);

        holder.inspectionDate.setText(String.format("Date: %s", holderI.getDate()));
        holder.inspectionNote.setText(String.format("Result: %s", holderI.getResult()));
    }

    @Override
    public int getItemCount() {
        return mInspection.size();
        //TODO
    }
}
