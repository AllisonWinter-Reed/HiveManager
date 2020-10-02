package com.example.hivemanager;

import androidx.fragment.app.FragmentContainer;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.fragment.app.Fragment;

import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.ContactsContract;
import android.text.Editable;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.hivemanager.ui.hivestatus.HiveStatusFragment;
import com.example.hivemanager.ui.hivestatus.HealthFragment;
import com.example.hivemanager.ui.home.HomeFragment;
import com.example.hivemanager.ui.managehives.AddApiary;
import com.example.hivemanager.ui.managehives.ManageApiaries;
import com.example.hivemanager.ui.managehives.ManageHivesFragment;
import com.example.hivemanager.ui.profile.EditProfileFragment;
import com.example.hivemanager.ui.profile.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private FragmentManager fragmentManager;
    public static String userName;
    private static Profile user;

    public static Profile getUser() {
        return user;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //TODO check for user authentication

        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_profile, R.id.navigation_apiaries)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this,
                navController,
                appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

        try {
            user = DatabaseHelper.initUser(userName);
        } catch (SQLException e) {
            Log.e("SQL", "Error with initUser");
            e.printStackTrace();
        }
        initApiaries();

    }

    public void openHiveStatus(View view) {
        Fragment fragment = new HiveStatusFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.nav_host_fragment, fragment);
        transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
        transaction.commit();
    }

    public void openManageHives(View view) {
        Integer position = (Integer) view.getTag();
        Fragment fragment = new ManageHivesFragment(position);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.nav_host_fragment, fragment);
        transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
        transaction.commit();
    }

    public void openProfile(View view) {
        Fragment fragment = new ProfileFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.nav_host_fragment, fragment);
        transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
        transaction.commit();
    }

//    public void openHealthTab(View view) {
//        Fragment fragment = new HealthFragment();
//        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//        transaction.replace(R.id.nav_host_fragment, fragment);
//        transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
//        transaction.commit();
//    }

    public void openManageApiaries(View view) {
        Fragment fragment = new ManageApiaries();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.nav_host_fragment, fragment);
        transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
        transaction.commit();
    }

    public void addApiary(View view) {
        Fragment fragment = new AddApiary();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.nav_host_fragment, fragment);
        transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
        transaction.commit();
    }

    public void editProfile(View view) {
        Fragment fragment = new EditProfileFragment();
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().add(R.id.nav_host_fragment, fragment).commit();

    }

    // TODO DEBUG REMOVE
    private void initApiaries() {
        try {

            // Associates apiaries with the user.
            user.getApiaries().addAll(DatabaseHelper.getApiaries(userName.toString()));

            // Associates Hives with each Apiary.
            for (Apiary apiary : user.getApiaries())
                apiary.setHives(DatabaseHelper.getHives(apiary.getAddress()));

            Log.d("PROBLEM", "AFTERHIVES");

            // Associates Equipment with each Apiary and Hive.
            for (Apiary apiary : user.getApiaries()) {

                // Adds equipment to the Apiary.
                apiary.setEquipment(DatabaseHelper.getApiaryEquipment(apiary.getAddress()));

                // Addsd equipment to each Hive in the Apiary.
                for (Hive hive : apiary.getHives()) {
                    hive.setEquipment(DatabaseHelper.getHiveEquipment(String.valueOf(hive.getHiveID())));

                }
            }

            // TODO DEBUG REMOVE prints contents of this User's hives
            for (Apiary currApiary : user.getApiaries()) {
                Log.d("Apiary", "address : " + currApiary.getAddress() + "zipcode : " + currApiary.getZip());

                // Apiary equipment.
                for (Equipment equipment : currApiary.getEquipment()) {
                    // Log.d("ApiaryEquipment", equipment.get);
                }

                // Hives.
                for (Hive hive : currApiary.getHives()) {
                    Log.d("Hive",
                            "HiveID : " + String.valueOf(hive.getHiveID()) + "\n" +
                                    "health : " + String.valueOf(hive.getHealth()) + "\nhoneyStores : " + String.valueOf(hive.getHoneyStores()) +
                                    "\nQueen Production : " + String.valueOf(hive.getQueenProduction()) + "\nLosses : " + String.valueOf(hive.getLosses())
                                    + "\ngains : " + String.valueOf(hive.getGains()));

                }
            }
        }
        // If a SQL exception occurs, logs the error message.
        catch (SQLException excpt) {
            Log.d("ERROR:", excpt.getMessage());

        }
        // If an unexpected exception occurs, logs the error message.
        catch (Exception excpt) {
            Log.d("ERROR:", excpt.getMessage());

        }
    }

    public void addHive(View view) {

    }
}