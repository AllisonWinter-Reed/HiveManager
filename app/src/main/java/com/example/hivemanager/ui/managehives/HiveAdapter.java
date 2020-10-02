package com.example.hivemanager.ui.managehives;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hivemanager.MainActivity;
import com.example.hivemanager.Hive;
import com.example.hivemanager.R;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.hivemanager.R;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

public class HiveAdapter extends RecyclerView.Adapter<HiveAdapter.HiveNote> {
    static ArrayList<Hive> hives;

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
    public HiveNote onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.hive_view, parent, false);
        return new HiveNote(itemView);

    }

    static public void initHives(ArrayList<Hive> inputHives) {
       hives = inputHives;

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
