import java.util.ArrayList;

public class Apiary {

    private ArrayList<Hive> hives;

    Apiary() {
        hives = new ArrayList<>();
    }

    Apiary(ArrayList<Hive> hives) {
        this.hives = hives;
    }


    /**
     * @param hive the Hive to add to this Apiary.
     */
    public void addHive(Hive hive) {
        hives.add(hive);

    }

    /**
     * @param hive the Hive to remove from this Apiary.
     */
    public void removeHive(Hive hive) {
        hives.remove(hive);

    }
}
