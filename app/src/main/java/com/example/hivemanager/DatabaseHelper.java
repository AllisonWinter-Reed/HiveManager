package com.example.hivemanager;

import java.sql.*;
import java.util.ArrayList;

public class DatabaseHelper {


    //connects to Database
    public Connection establishConnection() {

        Connection connection = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");

            connection = DriverManager.getConnection("jdbc:mysql://uwhivemanager506.cmnpa3ypkmwq.us-east-2.rds.amazonaws.com:3306/hive_manager", "admin", "Hivemanager123");
        } catch (Exception e) {
            System.out.println("error");
        }
        return connection;
    }

    //returns an ArrayList of all the user fields in the format[username,firstname,email,lastname,phone_number,ppr,password,address,zipcode]
    public ArrayList getUserData(String userName) throws SQLException {

        Connection con;
        Statement stmt;

        con = establishConnection();

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


    //return a list of hives with the same address i.e. same Apiary
    public ArrayList getHives(String addr) throws SQLException {

        Connection conn;
        Statement stmt;

        conn = establishConnection();

        String sql = "SELECT * FROM Hive WHERE Address = '" + addr.toString() + "'";
        stmt = conn.createStatement();

        ResultSet rs = stmt.executeQuery(sql);
        ArrayList hivelist = new ArrayList();


        while(rs.next()){

            String hiveid = rs.getString(("HiveId"));
            hivelist.add(hiveid);

        }

        return hivelist;

    }


    public ArrayList getHiveInfo(int hiveID) throws SQLException {
        Connection con;
        Statement stmt;

        con = establishConnection();

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

}
