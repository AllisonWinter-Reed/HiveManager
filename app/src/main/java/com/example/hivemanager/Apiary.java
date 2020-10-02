package com.example.hivemanager;

import java.util.ArrayList;

public class Apiary {
    private ArrayList<Hive> hives;
    private String address;
    private String zipcode;

    public Apiary() {
        hives = new ArrayList<>();
    }

    public Apiary(ArrayList<Hive> hives) {
        this.hives = hives;
    }

    public Apiary(String addr, String zip) {
        hives = new ArrayList<Hive>();
        address = addr;
        zipcode = zip;

    }

    /**
     * @param hive the Hive to add to this Apiary.
     */
    public void addHive(Hive hive) { hives.add(hive); }

    /**
     * @return the list of Hives within this Apiary.
     */
    public ArrayList<Hive> getHives() { return hives; }

    /**
     * @param hives the Hives to add to this Apiary.
     */
    public void setHives(ArrayList<Hive> hives) { this.hives = hives; }

    /**
     * @param hive the Hive to remove from this Apiary.
     */
    public void removeHive(Hive hive) {
        hives.remove(hive);

    }

    /**
     * @param addr the new address for this Apiary.
     */
    public void setAddress(String addr) { address = addr; }

    /**
     * @return address the address for this Apiary.
     */
    public String getAddress() { return address; }

    /**
     * @param zip the new zipcode for this Apiary.
     */
    public void setZip(String zip) { zipcode = zip; }

    /**
     * @return the zipcode for this Apiary.
     */
    public String getZip() { return zipcode; }

}