package com.example.hivemanager;

import android.provider.ContactsContract;

import java.sql.SQLException;
import java.util.ArrayList;

public class Profile {
    private String username;
    private String password; //not sure if we want this...
    private String firstname;
    private String lastname; // TODO nothing with firstname/lastname yet.
    private String phone;
    private String email;
    private String profilePhoto; //TODO change when profile photo storage is figured out
    private ArrayList<Hive> hives;
    private ArrayList<Apiary> apiaries;
    private ArrayList<Profile> friends;
    private String address;
    private String zipcode;

    Profile() {
        username = null;
        password = null;
        firstname = null;
        lastname = null;
        phone = null;
        email = null;
        profilePhoto = null;
        hives = new ArrayList<>();
        apiaries = new ArrayList<>();
        friends = new ArrayList<>();
        address = null;
        zipcode = null;

    }

    public String getUsername() {
        return username;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getProfilePhoto() {
        return profilePhoto;
    }

    public ArrayList<Hive> getHives() {
        return hives;
    }

    public ArrayList<Apiary> getApiaries() {
        return apiaries;
    }

    public ArrayList<Profile> getFriends() {
        return friends;
    }

    Profile(String username, String password, String firstname, String lastname, String phone,
            String email, String profilePhoto, ArrayList<Hive> hives, ArrayList<Apiary> apiaries,
            ArrayList<Profile> friends, String address, String zipcode) {
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.phone = phone;
        this.email = email;
        this.profilePhoto = profilePhoto;
        this.hives = hives;
        this.apiaries = apiaries;
        this.friends = friends;
        this.address = address;
        this.zipcode = zipcode;

    }

    /**
     * Adds a user as this Profile's friend. Will add the opposite user as a friend as well.
     *
     * @param user the friend to be added.
     */
    public void addFriend(Profile user) {
        friends.add(user);

    }

    /**
     * @param hive the Hive to be associated with this Profile.
     */
    public void addHive(Hive hive) {
        hives.add(hive);

    }

    /**
     * @param apiary the apiary to be associated with this Profile.
     */
    public void addApiary(Apiary apiary) {
        apiaries.add(apiary);

    }

    /**
     * TODO not sure if we want this...
     * @param password the password to overwrite the current password.
     */
    public void changePassword(String password) {
        this.password = password;

    }

    /**
     * @param phone the phone number to be associated with this Profile.
     */
    public void changePhone(String phone) {
        this.phone = phone;

    }


    /**
     * @param hive the hive to be removed from hives.
     */
    public void deleteHive(Hive hive) {
        hives.remove(hive);

        try {
            DatabaseHelper.deleteHive(hive);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     * @param apiary the apiary to be removed from apiaries.
     */
    public void deleteApiary(Apiary apiary) {
        apiaries.remove(apiary);

        try {
            DatabaseHelper.deleteApiary(apiary.getAddress());
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setProfilePhoto(String profilePhoto) {
        this.profilePhoto = profilePhoto;
    }

    public void setHives(ArrayList<Hive> hives) {
        this.hives = hives;
    }

    public void setApiaries(ArrayList<Apiary> apiaries) {
        this.apiaries = apiaries;
    }

    public void setAddress(String address){
        this.address = address;
    }

    public void setZipcode(String zipcode){
        this.zipcode = zipcode;
    }

    public String getAddress() {
        return this.address;
    }

    public String getZipcode() {return this.zipcode;}

}
