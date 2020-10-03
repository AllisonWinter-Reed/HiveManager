package com.example.hivemanager;

import android.provider.ContactsContract;

import java.lang.reflect.Array;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * This class represents a Hive in the HiveManagement system.
 */
public class Hive {
    private int health;
    private int honeyStores;
    private int queenProduction;
    private int losses;
    private int gains;
    private int hiveID;
    private ArrayList<Inspection> inspections;
    private ArrayList<Equipment> hiveEquipment;

    public Hive(int hiveID) { //TODO should we always require at least an ID is entered upon object construction
        health = 0;
        inspections = new ArrayList<>();
        honeyStores = 0;
        queenProduction = 0;
        hiveEquipment = new ArrayList<>();
        losses = 0;
        gains = 0;
        this.hiveID = hiveID;
    }

    public Hive(int hiveID, int health, ArrayList<Inspection> inspections,
                int honeyStores, int queenProduction, ArrayList<Equipment> hiveEquipment, int losses, int gains) {
        this.health = health;
        this.inspections = inspections;
        this.honeyStores = honeyStores;
        this.queenProduction = queenProduction;
        this.hiveEquipment = hiveEquipment;
        this.losses = losses;
        this.gains = gains;
        this.hiveID = hiveID;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setHoneyStores(int honeyStores) {
        this.honeyStores = honeyStores;
    }

    public void setQueenProduction(int queenProduction) {
        this.queenProduction = queenProduction;
    }

    public void setLosses(int losses) {
        this.losses = losses;
    }

    public void setGains(int gains) {
        this.gains = gains;
    }

    public Hive(int health, int honeyStores, int queenProduction, int gains, int losses) {
        this.health = health;
        this.honeyStores = honeyStores;
        this.queenProduction = queenProduction;
        this.gains = gains;
        this.losses = losses;
        this.inspections = new ArrayList<>();
        this.hiveID = -1; //-1 means no hiveID has been assigned.


    }


    public void inspectionComplete(Date date, Integer result) {

    }

    public int harvestHoney() {
        return 0;
    }

    /**
     * @param equipment pieces of equipment to be added to hiveEquipment.
     */
    public void setEquipment(ArrayList<Equipment> equipment) {
        hiveEquipment = equipment;

    }

    /**
     * @param equipment a piece of equipment to be added to hiveEquipment.
     */
    public void addEquipment(Equipment equipment) {
        hiveEquipment.add(equipment);

    }

    /**
     * @param equipment a piece of equipment to be removed from hiveEquipment.
     */
    public void removeEquipment(Equipment equipment) {
        hiveEquipment.remove(equipment);

    }

    /**
     * @return health the health of this Hive.
     */
    public int getHealth() {
        return health;

    }


    /**
     * @param inspection the inspection to be deleted.
     */
    public void deleteInspection(int inspection) {

        try {
            DatabaseHelper.deleteInspection(inspections.get(inspection).getID());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        inspections.remove(inspection);
    }

    public void editInspection(int inspection, String date, String result) {
        try {
            DatabaseHelper.editInspection(inspections.get(inspection).getID(), hiveID, result, date);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        SimpleDateFormat sdf = new SimpleDateFormat(("dd-MM-yyyy"));
        Date dateN = null;
        try {
            dateN = sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        java.sql.Date sqlDate = new java.sql.Date(dateN.getTime());


        inspections.get(inspection).setDate(sqlDate);
        inspections.get(inspection).setResult(result);

    }

    public void addInspection(String dateS, String resultS) {
        SimpleDateFormat sdf = new SimpleDateFormat(("dd-MM-yyyy"));
        Date date = null;
        try {
            date = sdf.parse(dateS);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int inspectionID = -1;

        java.sql.Date sqlDate = new java.sql.Date(date.getTime());

        try {
            inspectionID = DatabaseHelper.addInspection(hiveID, resultS, dateS);
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        inspections.add(new Inspection(inspectionID, hiveID, sqlDate, resultS));


    }

    /**
     * @param inspections the new results of inspections.
     */
    public void setInspections(ArrayList<Inspection> inspections) {
        this.inspections = inspections;
    }

    /**
     * @return inspections the results of inspections.
     */
    public ArrayList<Inspection> getInspections() {
        return inspections;

    }


    /**
     * @return honeyStores the stores of honey.
     */
    public int getHoneyStores() {
        return honeyStores;

    }

    /**
     * @return queenProduction.
     */
    public int getQueenProduction() {
        return queenProduction;

    }

    /**
     * @return hiveEquipment the equipment for this Hive.
     */
    public ArrayList<Equipment> getEquipment() {
        return hiveEquipment;

    }

    /**
     * @return losses.
     */
    public int getLosses() {
        return losses;

    }

    /**
     * @return gains.
     */
    public int getGains() {
        return gains;
    }

    /**
     * @return hiveID the unique ID for this Hive.
     */
    public int getHiveID() {
        return hiveID;

    }

    public void setHiveID(int hiveID) {
        this.hiveID = hiveID;
    }


    public void deleteEquipment(int equipmentPosition) {
        try {
            DatabaseHelper.deleteEquipment(hiveEquipment.get(equipmentPosition).getID());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        hiveEquipment.remove(equipmentPosition);


    }

    public void addInventory(String equipmentS, int apiaryPosition) {

        int equipmentID = -1;
        try {
            equipmentID = DatabaseHelper.addEquipment(String.format("%d",hiveID)
                    , MainActivity.getUser().getApiaries().get(apiaryPosition).getAddress(), equipmentS);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        hiveEquipment.add(new Equipment(equipmentID, equipmentS));


    }

    public void editInventory(int equipmentPosition, String equipmentNameS, int apiaryPosition) {
        try {
            DatabaseHelper.editInspection(hiveEquipment.get(equipmentPosition).getID(), hiveID, MainActivity.getUser().getApiaries().get(apiaryPosition).getAddress(), equipmentNameS);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        hiveEquipment.get(equipmentPosition).setName(equipmentNameS);


    }
}