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
import java.sql.SQLException;
import java.sql.Statement;

public class RegisterActivity extends AppCompatActivity {
    EditText userName,firstName,lastName,email,phoneNumber,password,address,zip,ppref;
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
        address = (EditText)findViewById(R.id.etAddress);
        zip = (EditText)findViewById(R.id.etZip);
        // TODO ppref commented out
//        ppref = (ImageView)findViewById(R.id.etPPReference);
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

            // Upon successful registration, empties text and moves the user to the Main View.
            if (isSuccess) {

                // Empties all text.
                userName.setText("");
                firstName.setText("");
                lastName.setText("");
                email.setText("");
                phoneNumber.setText("");
                password.setText("");
                // TODO ppref commented out
                //ppref.setText("");
                address.setText("");
                zip.setText("");

                // Sets the userName.
                MainActivity.userName = userName.getText();

                // Moves the user to the Main View upon successful registration.
                Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(intent);

            }
            // If registration is unsuccessful, prints an appropriate error message.
            else {

                // If the username was taken, writes "Invalid Username".
                if (z.contains("Duplicate")) status.setText("Invalid Username");
                // Otherwise, writes "Unexpected Error".
                else status.setText("Unexpected Error");

            }
        }

        @Override
        protected String doInBackground(String... strings) {
            try {
                con = establishConnection();

                // Exits if connection fails.
                if (con == null) {
                    isSuccess = false;
                    z = "Check your Internet Connection";

                }
                // Attempts to perform a query if connection is successful.
                else {

                    // Query format string.
                    // TODO ppref is currently set to null, replace as appropriate
                    String sql = "INSERT INTO Beekeeper VALUES ('" + userName.getText() + "' , '"
                            + firstName.getText() + "', '" + email.getText() + "','" + lastName.getText() +
                            "','" + phoneNumber.getText() + "','" + /*ppref.getText()*/"NULL" + "', '"
                            + password.getText() + "','" + address.getText()  + "', '" + zip.getText() + "')";

                    // Attempts to execute the query.
                    stmt = con.createStatement();
                    stmt.executeUpdate(sql);

                    // If the query is successful, will proceed to Apiaries screen.
                    isSuccess = true;
                    z = "Success";

                }
            }
            // If a SQL exception occurs, returns the error message.
            catch (SQLException excpt) {
                isSuccess = false;
                z = excpt.getMessage();

                Log.d("BOPER", excpt.getMessage()); // tODO REMOVE

            }
            // If an unexpected exception occurs, returns the error message.
            catch (Exception excpt) {
                isSuccess = false;
                z = excpt.getMessage();

                Log.d("BOPER", excpt.getMessage()); // tODO REMOVE

            }

            // Returns status info.
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