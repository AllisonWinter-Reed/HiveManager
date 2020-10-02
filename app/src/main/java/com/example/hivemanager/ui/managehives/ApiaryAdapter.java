package com.example.hivemanager.ui.managehives;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hivemanager.Apiary;
import com.example.hivemanager.R;

import java.util.ArrayList;

public class ApiaryAdapter extends RecyclerView.Adapter<ApiaryAdapter.ApiaryNote> {
    ArrayList<Apiary> mApiary;
    private onItemClickListener mListener;

    ApiaryAdapter(ArrayList<Apiary> mApiary) {
        this.mApiary = mApiary;
    }

    public interface onItemClickListener {
        void onItemClick(int position);
        void onDeleteClick(int position);
        void onEditClick(int position);
    }

    public void setOnItemClickListener(onItemClickListener listener) {
        mListener = listener;
    }



    public static class ApiaryNote extends RecyclerView.ViewHolder {
        private TextView apiaryName;
        private TextView apiaryAddress;
        private TextView numberHives;
        private Button deleteApiaryButton;
        private Button editApiaryButton;

        public ApiaryNote(@NonNull View itemView, final onItemClickListener listener) {
            super(itemView);
            apiaryName = itemView.findViewById(R.id.apiaryName);
            apiaryAddress = itemView.findViewById(R.id.apiaryLocation);
            numberHives = itemView.findViewById(R.id.numberHives);
            deleteApiaryButton = itemView.findViewById(R.id.deleteApiary_button);
            editApiaryButton = itemView.findViewById(R.id.editApiary_button);

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

            deleteApiaryButton.setOnClickListener(new View.OnClickListener() {
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

            editApiaryButton.setOnClickListener(new View.OnClickListener() {
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
    public ApiaryAdapter.ApiaryNote onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.apiary_view, parent, false);
        return new ApiaryNote(itemView, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ApiaryAdapter.ApiaryNote holder, int position) {

        Apiary holderA = mApiary.get(position);

        holder.apiaryName.setText(String.format("Apiary %d", position + 1));
        holder.apiaryAddress.setText(holderA.getAddress());
        holder.numberHives.setText(String.format("Number of Hives: %d", holderA.getHives().size()));
        if(holderA.getHives().size() == 0) {
            holder.deleteApiaryButton.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public int getItemCount() {
        return mApiary.size();
        //TODO
    }
}
