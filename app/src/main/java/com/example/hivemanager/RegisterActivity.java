package com.example.hivemanager;
import java.io.*;
import java.lang.*;
import java.util.*;


import android.app.ProgressDialog;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.graphics.Bitmap;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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
    EditText userName, firstName, lastName, email, phoneNumber, password, address, zip;
    ImageView ppref;
    Button registerbtn;
    TextView status, backToLogin;
    Connection con;
    Statement stmt;
    public static final int GET_FROM_GALLERY = 3;
    private Bitmap bitmap;
    public String ppUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        userName = (EditText) findViewById(R.id.etUsername);
        firstName = (EditText) findViewById(R.id.etFirstName);
        lastName = (EditText) findViewById(R.id.etLastName);
        email = (EditText) findViewById(R.id.etEmail);
        phoneNumber = (EditText) findViewById(R.id.etPhoneNumber);
        password = (EditText) findViewById(R.id.etPassword);
        address = (EditText) findViewById(R.id.etAddress);
        zip = (EditText) findViewById(R.id.etZip);
        ppref = (ImageView) findViewById(R.id.etPPReference);
        registerbtn = (Button) findViewById((R.id.bRegister));
        status = (TextView) findViewById(R.id.regstatus);
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
                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    public void registerProfilePic(View view) {
        startActivityForResult(new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI), GET_FROM_GALLERY);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //Detects request codes
        if (requestCode == GET_FROM_GALLERY && resultCode == RegisterActivity.RESULT_OK) {
            Uri selectedImage = data.getData();
            ppUrl = selectedImage.getPath();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};
            Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            ImageView newPP = (ImageView) findViewById(R.id.etPPReference);
            newPP.setImageBitmap(BitmapFactory.decodeFile(picturePath));
            Bitmap bitmap = null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage);
                newPP.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public class registerUser extends AsyncTask<String, String, String> {
        String z = "";
        Boolean isSuccess = false;

        @Override
        protected void onPreExecute() {
            status.setText("Registering");
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
                address.setText("");
                zip.setText("");
                // Sets the userName.

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        MainActivity.userName = userName.getText().toString();
                        Log.d("USERNAME", MainActivity.userName); //TODO delete
                    }
                });
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
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
                    z = "Check your internet connection";
                }
                // Attempts to perform a query if connection is successful.
                else {

                    DatabaseHelper.createUser(userName.getText().toString(), firstName.getText().toString(), email.getText().toString(), lastName.getText().toString()
                            , phoneNumber.getText().toString(), password.getText().toString(), address.getText().toString(), zip.getText().toString(), "NULL");


                    // If the query is successful, will proceed to Home screen.
                    isSuccess = true;
                    z = "Success";
                }
            }
            // If a SQL exception occurs, returns the error message.
            catch (SQLException excpt) {
                isSuccess = false;
                z = excpt.getMessage();
            }
            // If an unexpected exception occurs, returns the error message.
            catch (Exception excpt) {
                isSuccess = false;
                z = excpt.getMessage();
            }
            // Returns status info.
            return z;
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
}