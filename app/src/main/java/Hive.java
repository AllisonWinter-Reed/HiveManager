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

    Hive() {
        health = 0;
        inspectionDates = new ArrayList<>();
        inspectionResults = new ArrayList<>();
        honeyStores = 0;
        queenProduction = 0;
        hiveEquipment = new ArrayList<>();
        equipmentInventory = new ArrayList<>();
        losses = 0;
        gains = 0;
    }

    Hive(int health, ArrayList<Date> inspectionDates, ArrayList<Integer> inspectionResults,
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
    }

    public void inspectionComplete(Date date, Integer result) {

    }

    public int harvestHoney() {
        return 0;
    }

    public void addEquipmentToInv(ArrayList<String> equipment) {

    }

    public void removeEquipmentInv(ArrayList<String> equipment) {

    }

    public void removeEquipmentHive(ArrayList<String> equipment) {

    }

    public void addEquipmentToHive(ArrayList<String> equipment) {

    }

}
