package com.example.hivemanager;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;

/**
 * This class represents a Hive in the HiveManagement system.
 */
public class Hive {
    private int health;
    private ArrayList<Date> inspectionDates;
    private ArrayList<Integer> inspectionResults;
    private int honeyStores;
    private int queenProduction;
    private ArrayList<Equipment> hiveEquipment;
    private int losses;
    private int gains;
    private int hiveID;

    public Hive(int hiveID) { //TODO should we always require at least an ID is entered upon object construction
        health = 0;
        inspectionDates = new ArrayList<>();
        inspectionResults = new ArrayList<>();
        honeyStores = 0;
        queenProduction = 0;
        hiveEquipment = new ArrayList<>();
        losses = 0;
        gains = 0;
        this.hiveID = hiveID;
    }

    public Hive(int hiveID, int health, ArrayList<Date> inspectionDates, ArrayList<Integer> inspectionResults,
                int honeyStores, int queenProduction, ArrayList<Equipment> hiveEquipment, int losses, int gains) {
        this.health = health;
        this.inspectionDates = inspectionDates;
        this.inspectionResults = inspectionResults;
        this.honeyStores = honeyStores;
        this.queenProduction = queenProduction;
        this.hiveEquipment = hiveEquipment;
        this.losses = losses;
        this.gains = gains;
        this.hiveID = hiveID;
    }

    public void inspectionComplete(Date date, Integer result) {

    }

    public int harvestHoney() {
        return 0;
    }

    /**
     * @param equipment pieces of equipment to be added to the equipmentInventory.
     */
    public void addEquipment(ArrayList<Equipment> equipment) {
        hiveEquipment.addAll(equipment);

    }

    /**
     * @param equipment pieces of equipment to be removed from the equipmentInventory.
     */
    public void removeEquipment(ArrayList<Equipment> equipment) {
        hiveEquipment.removeAll(equipment);

    }

    /**
     * @return health the health of this Hive.
     */
    public int getHealth() {
        return health;

    }

    /**
     * @return inspectionDates the list of inspectionDates.
     */
    public ArrayList<Date> getInspectionDates() {
        return inspectionDates;

    }

    /**
     * @return inspectionResults the results of inspections.
     */
    public ArrayList<Integer> getInspectionResults() {
        return inspectionResults;

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
}