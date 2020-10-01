package com.example.hivemanager;

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
import java.sql.Statement;


public class RegisterActivity extends AppCompatActivity {

    EditText userName,firstName,lastName,email,phoneNumber,password,ppref;
    Button registerbtn;
    TextView status, backToLogin;

    Connection con;
    Statement stmt;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        userName = (EditText)findViewById(R.id.etUsernameRegister);
        firstName = (EditText)findViewById(R.id.etFirstName);
        lastName = (EditText)findViewById(R.id.etLastName);
        email = (EditText)findViewById(R.id.etEmail);
        phoneNumber = (EditText)findViewById(R.id.etPhoneNumber);
        password = (EditText)findViewById(R.id.etPassword);
        ppref = (EditText)findViewById(R.id.etPPReference);
        registerbtn = (Button)findViewById((R.id.bRegister));
        status = (TextView)findViewById(R.id.regstatus);
        backToLogin = (TextView) findViewById(R.id.tvToLogin);

        registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new RegisterActivity.registerUser().execute("");


            }
        });

        backToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                {
                    Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                    startActivity(intent);
                }
            }
        });

    }

    public class registerUser extends AsyncTask<String,String,String> {
        String z  ="";
        Boolean isSuccess = false;


        @Override
        protected void onPreExecute() {
            status.setText("Sending Data to Database");

        }

        @Override
        protected void onPostExecute(String s) {
            status.setText("Registration Successful");
            userName.setText("");
            firstName.setText("");
            lastName.setText("");
            email.setText("");
            phoneNumber.setText("");
            password.setText("");
            ppref.setText("");



        }

        @Override
        protected String doInBackground(String... strings) {

            try{
                con = establishConnection();
                if (con == null){
                    z = "Check your Internet Connection";
                }
                else{
                    String sql = "INSERT INTO Beekeeper VALUES ('"+userName.getText()+"' , '"+firstName.getText()+"', '"+email.getText()+"','"+lastName.getText()+"','"+phoneNumber.getText()+"','"+ppref.getText()+"', '"+password.getText()+"')";

                    stmt = con.createStatement();

                    stmt.executeUpdate(sql);



                }
            }
            catch (Exception e){
                isSuccess = false;
                z = e.getMessage();

            }
            return z;
        }

    }



    @SuppressLint("NewApi")
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



