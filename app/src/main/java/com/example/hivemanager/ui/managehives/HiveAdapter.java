package com.example.hivemanager.ui.managehives;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hivemanager.Apiary;
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
import com.example.hivemanager.ui.hivestatus.HiveStatusFragment;

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
    private HiveAdapter.onItemClickListener mListener;


    public interface onItemClickListener {
        void onItemClick(int position);
        void onDeleteClick(int position);
    }

    public void setOnItemClickListener(HiveAdapter.onItemClickListener listener) {
        mListener = listener;
    }

    public HiveAdapter(ArrayList<Hive> hives) {
        this.hives = hives;
    }

    //pass and connect to the database somehow

   public static class HiveNote extends RecyclerView.ViewHolder {
       private TextView hiveName;
       private TextView hiveHealth;
       private TextView honeyStored;
       private TextView queenProduction;
       private Button deleteHive;

       public HiveNote(View itemView, final onItemClickListener listener) {
           super(itemView);
           hiveName = itemView.findViewById(R.id.hiveName);
           hiveHealth = itemView.findViewById(R.id.hiveHealth);
           honeyStored = itemView.findViewById(R.id.honeyStores);
           queenProduction = itemView.findViewById(R.id.queenProduction);
           deleteHive = itemView.findViewById(R.id.deleteHive);

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
           deleteHive.setOnClickListener(new View.OnClickListener() {
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

        }

    }



    @NonNull
    @Override
    public HiveNote onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.hive_view, parent, false);
        return new HiveNote(itemView, mListener);

    }

    @Override
    public void onBindViewHolder(@NonNull HiveAdapter.HiveNote holder, int position) {

        Hive holderH = hives.get(position);

        holder.hiveName.setText(String.format("Hive %d", holderH.getHiveID()));
        holder.hiveHealth.setText(String.format("Health: %d", holderH.getHealth()));
        holder.honeyStored.setText(String.format("Honey Stored: %d", holderH.getHoneyStores()));
        holder.queenProduction.setText(String.format("Queen Production: %d", holderH.getQueenProduction()));

    }

    @Override
    public int getItemCount() {
        return hives.size();
    }
}
