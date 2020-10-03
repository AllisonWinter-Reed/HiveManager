package com.example.hivemanager.ui.hivestatus;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hivemanager.Equipment;
import com.example.hivemanager.Inspection;
import com.example.hivemanager.R;

import java.util.ArrayList;

public class EquipmentAdapter extends RecyclerView.Adapter<EquipmentAdapter.EquipmentNote> {
    ArrayList<Equipment> mEquipment;
    private onItemClickListener mListener;


    EquipmentAdapter(ArrayList<Equipment> mInspection) {
        this.mEquipment = mInspection;
    }

    public void setOnItemClickListener(EquipmentAdapter.onItemClickListener listener) {
        mListener = listener;
    }


    public interface onItemClickListener {
        void onDeleteClick(int position);
        void onEditClick(int position);
    }

    public static class EquipmentNote extends RecyclerView.ViewHolder {
        private TextView equipmentName;
        private ImageView deleteEquipmentButton;
        private ImageView editEquipmentButton;



        public EquipmentNote(@NonNull View itemView, final onItemClickListener listener) {
            super(itemView);

            equipmentName = itemView.findViewById(R.id.inventoryItem_name);

            deleteEquipmentButton = itemView.findViewById(R.id.deleteInventory_button);
            editEquipmentButton = itemView.findViewById(R.id.editInventory_button);



            deleteEquipmentButton.setOnClickListener(new View.OnClickListener() {
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

            editEquipmentButton.setOnClickListener(new View.OnClickListener() {
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
    public EquipmentAdapter.EquipmentNote onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.inspection_view, parent, false);
        return new EquipmentNote(itemView, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull EquipmentAdapter.EquipmentNote holder, int position) {

        Equipment holderE = mEquipment.get(position);

        holder.equipmentName.setText(holderE.getName());

    }

    @Override
    public int getItemCount() {
        return mEquipment.size();

    }
}
