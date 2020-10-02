package com.example.hivemanager;

/**
 * Equipment for Hives/Apiaries. This will be associated with the relevant Hives/Apiaries
 * by being placed within them.
 */
public class Equipment {
    private int ID;
    private String name;

    /**
     * Constructor for Equipment.
     *
     * @param ID the ID for this piece of equipment.
     * @param name the name for this piece of equipment.
     */
    public Equipment(int ID, String name) {
        this.ID = ID;
        this.name = name;

    }

    /**
     * @return name the name of this piece of Equipment.
     */
    public String getName() { return name; }

    /**
     * @param name the new name of this piece of Equipment.
     */
    public void setName(String name) { this.name = name; }

    /**
     * @return ID the ID of this piece of Equipment.
     */
    public int getID() { return ID; }

    /**
     * @param ID the Id of this piece of Equipment.
     */
    public void setID(int ID) { this.ID = ID; }

}
