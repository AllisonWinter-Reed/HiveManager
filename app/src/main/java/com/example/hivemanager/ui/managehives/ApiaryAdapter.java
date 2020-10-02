package com.example.hivemanager.ui.managehives;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hivemanager.Apiary;
import com.example.hivemanager.R;

import java.util.ArrayList;

public class ApiaryAdapter extends RecyclerView.Adapter<ApiaryAdapter.ApiaryNote> {
    ArrayList<Apiary> mApiary;

    ApiaryAdapter(ArrayList<Apiary> mApiary) {
        this.mApiary = mApiary;
    }

    public static class ApiaryNote extends RecyclerView.ViewHolder {
        private TextView apiaryName;
        private TextView apiaryAddress;
        private TextView numberHives;

        public ApiaryNote(@NonNull View itemView) {
            super(itemView);
            apiaryName = itemView.findViewById(R.id.apiaryName);
            apiaryAddress = itemView.findViewById(R.id.apiaryLocation);
            numberHives = itemView.findViewById(R.id.numberHives);
        }
    }

    @NonNull
    @Override
    public ApiaryAdapter.ApiaryNote onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.apiary_view, parent, false);
        return new ApiaryNote(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ApiaryAdapter.ApiaryNote holder, int position) {

        holder.apiaryName.setText("");  //TODO
        holder.apiaryAddress.setText("");   //TODO
        holder.numberHives.setText(String.format("Number of Hives: %d", 0)); //TODO

    }

    @Override
    public int getItemCount() {
        return 0; //TODO
    }
}
