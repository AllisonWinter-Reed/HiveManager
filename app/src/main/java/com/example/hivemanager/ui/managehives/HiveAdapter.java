package com.example.hivemanager.ui.managehives;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.hivemanager.R;

public class HiveAdapter extends RecyclerView.Adapter<HiveAdapter.HiveNote> {

    //pass and connect to the database somehow

   public static class HiveNote extends RecyclerView.ViewHolder {
       private TextView hiveName;
       private TextView hiveHealth;
       private TextView honeyStored;
       private TextView queenProduction;

       public HiveNote(View itemView) {
           super(itemView);
           hiveName = itemView.findViewById(R.id.hiveName);
           hiveHealth = itemView.findViewById(R.id.hiveHealth);
           honeyStored = itemView.findViewById(R.id.honeyStores);
           queenProduction = itemView.findViewById(R.id.queenProduction);
        }

    }

    @NonNull
    @Override
    public HiveAdapter.HiveNote onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.hive_view, parent, false);
        return new HiveNote(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull HiveAdapter.HiveNote holder, int position) {

       ///TODO connect to database and fill in the information below
        holder.hiveName.setText(""); //TODO set name
        holder.hiveHealth.setText(String.format("Health: %d", 0)); //TODO change 0
        holder.honeyStored.setText(String.format("Honey Stored: %d", 0)); //TODO change 0
        holder.queenProduction.setText(String.format("Queen Production: %d", 0)); //TODO chage 0

    }

    @Override
    public int getItemCount() {
       ///TODO return hive count size (pull from database?)
        return 0;
    }
}
