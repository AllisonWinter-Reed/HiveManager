package com.example.hivemanager.ui.managehives;

import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hivemanager.Hive;
import com.example.hivemanager.MainActivity;
import com.example.hivemanager.R;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DriverManager;

public class ManageHivesFragment extends Fragment {

    private RecyclerView hiveRecyclerView;

    // TODO DEBUG REMOVE
    private void initHives() {
        ArrayList<Hive> Hives = new ArrayList<Hive>();
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
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        initHives();
        final View view = inflater.inflate(R.layout.fragment_managehives, container, false);
        hiveRecyclerView = view.findViewById(R.id.hiveRecycle);
        hiveRecyclerView.setHasFixedSize(true);
        hiveRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        hiveRecyclerView.setAdapter(new HiveAdapter());
        return view;
    }

    /**
     * TODO remove when helper created
     *
     * @return
     */
    private Connection establishConnection() {
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
