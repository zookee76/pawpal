package com.mobdeve.pawpal.Model;

public class petDetailsDB {
    private long petID, petDetailsID;
    private String description;

    public petDetailsDB(long petID, String description) {
        this.petID = petID;
        this.description = description;
    }

    public petDetailsDB(long petID, long petDetailsID, String description) {
        this.petID = petID;
        this.petDetailsID = petDetailsID;
        this.description = description;
    }

    // Getters and Setters


    public long getPetID() {
        return petID;
    }

    public void setPetID(long petID) {
        this.petID = petID;
    }

    public long getPetDetailsID() {
        return petDetailsID;
    }

    public void setPetDetailsID(long petDetailsID) {
        this.petDetailsID = petDetailsID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}

