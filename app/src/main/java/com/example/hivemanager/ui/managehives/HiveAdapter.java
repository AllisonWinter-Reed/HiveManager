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
    ArrayList<Hive> Hives;

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
        try {

            // Connects to the database TODO remove.
            Statement stmt;
            Connection con = establishConnection();

            // Exits if connection fails.
            // TODO replace this with Samraaj database helper.
            // TODO exception handling? But it should not fail when the helper is done...
            if (con == null);
            // Attempts to perform a query if connection is successful.
            else {

                // TODO there should be a better way of doing this
                String name = MainActivity.userName.toString();

                // Finds all Hives.
                String sql = "SELECT * " +
                        "FROM Hive " +
                        "WHERE Address IN (" +
                        "SELECT Address " +
                        "FROM Apiary " +
                        "WHERE Username = " + "\"test\"" + ")"
                ;
                stmt = con.createStatement();
                ResultSet results = stmt.executeQuery(sql);

                // Fills the arraylist of Hives.
                // TODO null values
                while (results.next()) {
                    Hives.add(new Hive(
                            Integer.parseInt(results.getString("HiveId")),
                            Integer.parseInt(results.getString("Health")),
                            null, null, // TODO inspections not in database
                            Integer.parseInt(results.getString("Honey_stores")),
                            Integer.parseInt(results.getString("Queen_Production")),
                            null, // TODO equipment
                            null, // TODO two equipment variables?
                            Integer.parseInt(results.getString("Losses")),
                            Integer.parseInt(results.getString("Gains"))));
                        // TODO no zipcode in Hive

                }

                /* TODO DEBUG REMOVE
                String str = "";
                str = results.getString("Address");
                Log.d("BOPER", str);

                Statement st;
                // Finds all Hives.
                String pql = "SELECT * " +
                        "FROM Hive " +
                        "WHERE Address IN (" +
                        "SELECT Address " +
                        "FROM Apiary " +
                        "WHERE Username = " + "\"test\"" + ")"
                        ;
                st = con.createStatement();
                ResultSet results = st.executeQuery(pql);
                results.next();

                String str = "";
                str = results.getString("Address");
                Log.d("BOPER", str);*/

            }
        }
        // If a SQL exception occurs, returns the error message.
        catch (SQLException excpt) {
            // TODO DEBUG REMOVE
            Log.d("EXCEPTION:", excpt.getMessage());

        }
        // If an unexpected exception occurs, returns the error message.
        catch (Exception excpt) {
            // TODO DEBUG REMOVE
            Log.d("EXCEPTION:", excpt.getMessage());

        }

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

    /**
     * TODO remove when helper created
     *
     * @return
     */
    public Connection establishConnection() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection connection = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://uwhivemanager506.cmnpa3ypkmwq.us-east-2.rds.amazonaws.com:3306/hive_manager", "admin", "Hivemanager123");

        } catch (Exception e) {
            Log.e("SQL Connection Error : ", e.getMessage());

        }

        return connection;

    }
}
