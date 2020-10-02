package com.example.hivemanager;

import java.util.ArrayList;

public class Apiary {
    private ArrayList<Hive> hives;
    private ArrayList<Equipment> equipment;
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
        equipment = new ArrayList<Equipment>();
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
     * @param hive the Hive to remove from this Apiary.
     */
    public void removeHive(Hive hive) {
        hives.remove(hive);

    }

    /**
     * @param equip a piece of equipment to be added to this Hive.
     */
    public void addEquipment(Equipment equip) {
        equipment.add(equip);

    }

    /**
     * @param equip a piece of equipment to be removed from this Hive.
     */
    public void removeEquipment(Equipment equip) {
        equipment.remove(equip);

    }

    /**
     * @param equip a filled ArrayList of equipment to be added to this Hive.
     */
    public void setEquipment(ArrayList<Equipment> equip) {
        equipment = equip;

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