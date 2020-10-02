package com.example.hivemanager;

/**
 * Equipment for Hives/Apiaries. This will be associated with the relevant Hives/Apiaries
 * by being placed within them.
 */
public class Equipment {
    private String name;

    /**
     * Constructor for Equipment.
     *
     * @param name the name for this piece of equipment.
     */
    public Equipment(String name) { this.name = name; }

    /**
     * @return name the name of this piece of Equipment.
     */
    public String getName() { return name; }

    /**
     * @param name the new name of this piece of Equipment.
     */
    public void setName(String name) { this.name = name; }

}
