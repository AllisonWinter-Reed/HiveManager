package com.example.hivemanager;


import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.AsyncTaskLoader;

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
import android.widget.Toast;

import com.example.hivemanager.ui.home.HomeFragment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class LoginActivity extends AppCompatActivity {

    EditText usernameLogin, passwordLogin;
    Button bLogin;
    TextView status,registerhere;

    Connection con;
    Statement stmt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);




        usernameLogin = (EditText) findViewById(R.id.etUsernameLogin);
        passwordLogin = (EditText) findViewById(R.id.etPasswordLogin);
        bLogin = (Button) findViewById(R.id.buttonLogin);
        status = (TextView)findViewById(R.id.logstatus);
        registerhere = (TextView)findViewById(R.id.tvregisterhere);



        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                {
                    new LoginActivity.checkLogin().execute("");
                }
            }
        });

        registerhere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                {
                    Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                    startActivity(intent);
                }
            }
        });



    }


    public class checkLogin extends AsyncTask<String,String,String>
    {

        String z = null;
        Boolean isSuccess = false;



        protected void onPreExecute() {


        }

        @Override
        protected String doInBackground(String... strings) {
            con = establishConnection();
            if(con == null){
                status.setText("Check internet connection");
               // Toast.makeText(LoginActivity.this, "Check internet connection", Toast.LENGTH_LONG).show();

            }
            else {

                try {
                    String sql = "SELECT * FROM Beekeeper WHERE Username = '"+usernameLogin.getText()+"' AND password = '"+passwordLogin.getText()+"'";
                    stmt = con.createStatement();
                    ResultSet rs = stmt.executeQuery(sql);

                    if (rs.next()) {
                      // Toast.makeText(LoginActivity.this, "Login Success", Toast.LENGTH_LONG).show();

                        status.setText("Login Success");
                        status.setTextColor(getResources().getColor(R.color.start_green));
                        if (status.getText() == "Login Success") {

                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                        }
                    }

                     else {
                         runOnUiThread(new Runnable() {
                             @Override
                             public void run() {
                                 status.setText("Login Incorrect");
                                 status.setTextColor(getResources().getColor(R.color.end_red));
                             }
                         });
                       // Toast.makeText(LoginActivity.this, "Incorrect Username or Password", Toast.LENGTH_LONG).show();
                        usernameLogin.setText("");
                        passwordLogin.setText("");
                    }

                } catch (Exception e) {
                    isSuccess = false;
                    Log.e("SQL Error : ", e.getMessage());
                }
            }

            return z;

        }

        @Override
        protected void onPostExecute(String s) {

            super.onPostExecute(s);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    MainActivity.userName = usernameLogin.getText().toString();
                    Log.d("USERNAME", MainActivity.userName); //TODO delete
                }
            });
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