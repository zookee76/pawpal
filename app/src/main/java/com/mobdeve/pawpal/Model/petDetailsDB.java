package com.mobdeve.pawpal.Model;

public class petDetailsDB {
    private int petID, petDetailsID;
    private String description;

    public petDetailsDB(int petID, String description) {
        this.petID = petID;
        this.description = description;
    }

    public petDetailsDB(int petID, int petDetailsID, String description) {
        this.petID = petID;
        this.petDetailsID = petDetailsID;
        this.description = description;
    }

    // Getters and Setters
    public int getPetID() {
        return petID;
    }

    public void setPetID(int petID) {
        this.petID = petID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPetDetailsID() {
        return petDetailsID;
    }

    public void setPetDetailsID(int petDetailsID) {
        this.petDetailsID = petDetailsID;
    }
}

