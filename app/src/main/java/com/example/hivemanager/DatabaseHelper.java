package com.example.hivemanager;

import android.os.StrictMode;
import android.util.Log;

import java.sql.*;
import java.util.ArrayList;

public class DatabaseHelper {

    //connects to Database
    private static Connection establishConnection() {
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

    //returns an ArrayList of all the user fields in the format[username,firstname,email,lastname,phone_number,ppr,password,address,zipcode]
    public static ArrayList getUserData(String userName) throws SQLException {
        Statement stmt;
        Connection con = establishConnection();

        String sql = "SELECT * FROM Beekeeper WHERE Username = '" + userName.toString() + "'";
        stmt = con.createStatement();

        ResultSet rs = stmt.executeQuery(sql);
        ArrayList userlist = new ArrayList();
        while (rs.next()) {

            String username = rs.getString("Username");
            userlist.add(username);
            String firstname = rs.getString("First_Name");
            userlist.add(firstname);
            String email = rs.getString("Email");
            userlist.add(email);
            String lastname = rs.getString("Last_name");
            userlist.add(lastname);
            String phone_number = rs.getString("Phone_Number");
            userlist.add(phone_number);
            String ppr = rs.getString("ProfilePicReference");
            userlist.add(ppr);
            String password = rs.getString("password");
            userlist.add(password);
            String address = rs.getString("Address");
            userlist.add(address);
            String zipcode = rs.getString("Zipcode");
            userlist.add(zipcode);


        }


        return userlist;

    }

    /**
     * Returns a list of Apiaries associated with the inputed user.
     *
     * The Apiaries will be initialised with Address and Zipcode, Hives and Equipment will be left
     * empty.
     *
     * @param user the user to retrieve Apiaries for.
     * @return a list of Apiaries associated with the inputed user.
     * @throws SQLException if a SQL query fails.
     */
    public static ArrayList<Apiary> getApiaries(String user) throws SQLException {
        String sql;
        Statement stmt;
        ResultSet results;
        ArrayList<Apiary> apiaries = new ArrayList<>();
        Connection con = establishConnection();

        System.out.println("TOBY : " + con);

        // Issues a SQL query to find all Apiaries associated with user.
        sql = "SELECT * " +
                "FROM Apiary " +
                "WHERE Username = \"" + user + "\""
        ;
        stmt = con.createStatement();
        results = stmt.executeQuery(sql);

        // Fills the Arraylist of Apiaries.
        while (results.next()) {
            apiaries.add(new Apiary(results.getString("Address"),
                    results.getString("Zipcode")));

        }

        // Returns the list of apiaries associated with user.
        return apiaries;

    }

    /**
     * Returns a list of Hives associated with the inputed address.
     *
     * The Hives will be initialised with all data except equipment and inspection dates.
     *
     * @param address the address to retrieve Hives for.
     * @return the hives associated with the inputed address.
     * @throws SQLException if a SQL query fails.
     */
    public static ArrayList<Hive> getHivesAddr(String address) throws SQLException {
        String sql;
        Statement stmt;
        ResultSet results;
        ArrayList<Hive> hives = new ArrayList<Hive>();
        Connection con = establishConnection();

        // Issues a SQL query to find all Hives associated with address.
        sql = "SELECT * " +
                "FROM Hive " +
                "WHERE Address = \"" + address + "\""
        ;
        stmt = con.createStatement();
        results = stmt.executeQuery(sql);

        // Fills the Arraylist of Hives.
        while (results.next()) {
            hives.add(new Hive(
                    Integer.parseInt(results.getString("HiveId")),
                    Integer.parseInt(results.getString("Health")),
                    null, null, // TODO inspections not in database
                    Integer.parseInt(results.getString("Honey_stores")),
                    Integer.parseInt(results.getString("Queen_Production")),
                    null,
                    Integer.parseInt(results.getString("Losses")),
                    Integer.parseInt(results.getString("Gains"))));

        }

        // Returns the ArrayList of Hives associated with address.
        return hives;

    }

    //return a list of hive IDs
    // with the same address i.e. same Apiary
    public static ArrayList getHives(String addr) throws SQLException {
        Statement stmt;
        Connection con = establishConnection();

        String sql = "SELECT * FROM Hive WHERE Address = '" + addr.toString() + "'";
        stmt = con.createStatement();

        ResultSet rs = stmt.executeQuery(sql);
        ArrayList hivelist = new ArrayList();


        while(rs.next()){

            String hiveid = rs.getString(("HiveId"));
            hivelist.add(hiveid);

        }

        return hivelist;

    }


    public static ArrayList getHiveInfo(int hiveID) throws SQLException {
        Statement stmt;
        Connection con = establishConnection();

        String sql = "SELECT * FROM Hive WHERE HiveId = '" + hiveID + "'";
        stmt = con.createStatement();

        ResultSet rs = stmt.executeQuery(sql);
        ArrayList hiveInfo = new ArrayList();
        while (rs.next()) {

            String hiveId = rs.getString("HiveId");
            hiveInfo.add(hiveId);
            String health = rs.getString("Health");
            hiveInfo.add(health);
            String honey_stores = rs.getString("Honey_stores");
            hiveInfo.add(honey_stores);
            String queenproduction1 = rs.getString("Queen_Production");
            hiveInfo.add(queenproduction1);
            String gains = rs.getString("Gains");
            hiveInfo.add(gains);
            String losses = rs.getString("Losses");
            hiveInfo.add(losses);
            String address = rs.getString("Address");
            hiveInfo.add(address);
            String zipcode = rs.getString("Zipcode");
            hiveInfo.add(zipcode);


        }


        return hiveInfo;

    }

    //adds an apiary into the database, CANNOT ADD INTO APIARY IF THERE IS NO USER WITH THE SAME USERNAME
    public static void addApiary(String username, String address,  String zipcode) throws SQLException {
        Statement stmt;
        Connection con = establishConnection();

        String sql = "INSERT INTO Apiary VALUES ('" + address + "','" + username + "','" + zipcode + "')";

        stmt = con.createStatement();
        stmt.executeUpdate(sql);

    }

    //adds an hive in to the database, increments HiveId by 1
    public static void addHive(String Health, String Honey_stores, String Queen_production, String Gains, String Losses, String Address, String zipcode) throws SQLException {

        Connection con1;
        Connection con2;
        Statement stmt1;
        Statement stmt2;



        con1 = establishConnection();
        String sql1 = "SELECT MAX(HiveId) FROM Hive";
        stmt1 = con1.createStatement();
        ResultSet rs = stmt1.executeQuery(sql1);

        int currMax = 0;

        while(rs.next()) {
            currMax = rs.getInt(1);
        }

        int newIndex;
        newIndex  = currMax + 1;

        con2 = establishConnection();
        String sql2 = "INSERT INTO Hive VALUES ('"+newIndex+"','" + Health + "','" + Honey_stores + "','"+ Queen_production +"','"+ Gains +"','"+Losses+"','"+Address+"','"+zipcode+"')";
        stmt2 = con1.createStatement();
        stmt2.executeUpdate(sql2);




    }

    public static void deleteApiary(String Address) throws SQLException {

        Connection con;
        Statement stmt;

        con = establishConnection();
        String sql = "DELETE FROM Apiary WHERE Address = '"+ Address +"'";
        stmt = con.createStatement();
        stmt.executeUpdate(sql);

    }




}