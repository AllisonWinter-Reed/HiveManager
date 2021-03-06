package com.example.hivemanager;
import java.sql.Date;

public class Inspection {
    private int ID;
    private int hiveID;
    private Date date;
    private String result;

    /**
     * Constructor for an Inspection.
     *
     * @param ID the unique ID for this Inspection.
     * @param hiveID the Hive that was inspected.
     * @param date the date of the Inspection.
     * @param result the result of the Inspection.
     */
    public Inspection(int ID, int hiveID, Date date, String result) {
        this.ID = ID;
        this.hiveID = hiveID;
        this.date = date;
        this.result = result;

    }

    public Inspection(Date date, String result, int hiveID) {
        this.date = date;
        this.result = result;
        this.hiveID = hiveID;
        this.ID = -1;
    }

    /**
     * @param ID the new ID for this Inspection.
     */
    public void setID(int ID) { this.ID = ID; };

    /**
     * @return ID the ID for this Inspection.
     */
    public int getID() { return this.ID; };

    /**
     * @param hiveID the new hiveID for this Inspection.
     */
    public void setHiveID(int hiveID) { this.hiveID = hiveID; };

    /**
     * @return hiveID the hiveID for this Inspection.
     */
    public int getHiveID() { return this.hiveID; };

    /**
     * @param date the new date for this Inspection.
     */
    public void setDate(Date date) { this.date = date; }

    /**
     * @return date the date for this Inspection.
     */
    public Date getDate() { return this.date; }

    /**
     * @param result the new result for this Inspection.
     */
    public void setResult(String result) { this.result = result; }

    /**
     * @return result the result for this Inspection.
     */
    public String getResult() { return this.result; }

}
