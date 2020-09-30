import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;

public class Hive {

    private int health;
    private ArrayList<Date> inspectionDates;
    private ArrayList<Integer> inspectionResults;
    private int honeyStores;
    private int queenProduction;
    private ArrayList<String> hiveEquipment;
    private ArrayList<String> equipmentInventory;
    private int losses;
    private int gains;
    private int hiveID;

    Hive(int hiveID) { //TODO should we alway require at least an ID is entered upon object construction
        health = 0;
        inspectionDates = new ArrayList<>();
        inspectionResults = new ArrayList<>();
        honeyStores = 0;
        queenProduction = 0;
        hiveEquipment = new ArrayList<>();
        equipmentInventory = new ArrayList<>();
        losses = 0;
        gains = 0;
        this.hiveID = hiveID;
    }

    Hive(int hiveID, int health, ArrayList<Date> inspectionDates, ArrayList<Integer> inspectionResults,
         int honeyStores, int queenProduction, ArrayList<String> hiveEquipment,
         ArrayList<String> equipmentInventory, int losses, int gains) {
        this.health = health;
        this.inspectionDates = inspectionDates;
        this.inspectionResults = inspectionResults;
        this.honeyStores = honeyStores;
        this.queenProduction = queenProduction;
        this.hiveEquipment = hiveEquipment;
        this.equipmentInventory = equipmentInventory;
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
    public void addEquipmentToInv(ArrayList<String> equipment) {
        equipmentInventory.addAll(equipment);

    }

    /**
     * @param equipment pieces of equipment to be removed from the equipmentInventory.
     */
    public void removeEquipmentInv(ArrayList<String> equipment) {
        equipmentInventory.removeAll(equipment);

    }

    /**
     * @param equipment pieces of equipment to be added to the hiveEquipment.
     */
    public void addEquipmentToHive(ArrayList<String> equipment) {
        hiveEquipment.addAll(equipment);

    }

    /**
     * @param equipment pieces of equipment to be removed from the hiveEquipment.
     */
    public void removeEquipmentHive(ArrayList<String> equipment) {
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
    public ArrayList<String> getHiveEquipment() {
        return hiveEquipment;

    }

    /**
     * @return equipmentInventory the inventory of equipment.
     */
    public ArrayList<String> getEquipmentInventory() {
        return equipmentInventory;

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
