package com.mobdeve.pawpal.Model;

public class vaccination {
    private String vaccType, petName, ownerName, vaxDateTime, vaxVet, vaxStatus;
    private int vaxID, petID, ownerID, vetID;

    public vaccination(String vaccType, String petName, String ownerName, String vaxDateTime, String vaxVet, String vaxStatus) {
        this.vaccType = vaccType;
        this.petName = petName;
        this.ownerName = ownerName;
        this.vaxDateTime = vaxDateTime;
        this.vaxVet = vaxVet;
        this.vaxStatus = vaxStatus;
    }

    public vaccination(String vaccType, String petName, String ownerName, String vaxDateTime, String vaxVet, String vaxStatus, int vaxID, int petID, int ownerID, int vetID) {
        this.vaccType = vaccType;
        this.petName = petName;
        this.ownerName = ownerName;
        this.vaxDateTime = vaxDateTime;
        this.vaxVet = vaxVet;
        this.vaxStatus = vaxStatus;
        this.vaxID = vaxID;
        this.petID = petID;
        this.ownerID = ownerID;
        this.vetID = vetID;
    }

    public vaccination(String vaccType, String petName, String ownerName, String vaxDateTime, String vaxVet, String vaxStatus, int petID, int ownerID, int vetID) {

        this.vaccType = vaccType;
        this.petName = petName;
        this.ownerName = ownerName;
        this.vaxDateTime = vaxDateTime;
        this.vaxVet = vaxVet;
        this.vaxStatus = vaxStatus;
        this.petID = petID;
        this.ownerID = ownerID;
        this.vetID = vetID;
    }

    public int getVaxID() {
        return vaxID;
    }

    public void setVaxID(int vaxID) {
        this.vaxID = vaxID;
    }

    public int getPetID() {
        return petID;
    }

    public void setPetID(int petID) {
        this.petID = petID;
    }

    public int getOwnerID() {
        return ownerID;
    }

    public void setOwnerID(int ownerID) {
        this.ownerID = ownerID;
    }

    public int getVetID() {
        return vetID;
    }

    public void setVetID(int vetID) {
        this.vetID = vetID;
    }

    public String getVaccType() {
        return vaccType;
    }

    public void setVaccType(String vaccType) {
        this.vaccType = vaccType;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getVaxDateTime() {
        return vaxDateTime;
    }

    public void setVaxDateTime(String vaxDateTime) {
        this.vaxDateTime = vaxDateTime;
    }

    public String getVaxVet() {
        return vaxVet;
    }

    public void setVaxVet(String vaxVet) {
        this.vaxVet = vaxVet;
    }

    public String getVaxStatus() {
        return vaxStatus;
    }

    public void setVaxStatus(String vaxStatus) {
        this.vaxStatus = vaxStatus;
    }
}
