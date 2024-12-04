package com.mobdeve.pawpal.Model;

public class vaccination {
    private String vaccType, petName, ownerName, vaxDateTime, vaxVet, vaxStatus;
    private long vaxID, petID, ownerID, vetID, appID;

    public vaccination(){}

    public vaccination(String vaccType, String petName, String ownerName, String vaxDateTime, String vaxVet, String vaxStatus, long vaxID, long petID, long ownerID, long vetID) {
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

    public vaccination(String vaccType, String petName, String ownerName, String vaxDateTime, String vaxVet, String vaxStatus, long vaxID, long petID, long ownerID, long vetID, long appID) {
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
        this.appID = appID;
    }

    public long getAppID() {
        return appID;
    }

    public void setAppID(long appID) {
        this.appID = appID;
    }

    public long getVaxID() {
        return vaxID;
    }

    public void setVaxID(long vaxID) {
        this.vaxID = vaxID;
    }

    public long getPetID() {
        return petID;
    }

    public void setPetID(long petID) {
        this.petID = petID;
    }

    public long getOwnerID() {
        return ownerID;
    }

    public void setOwnerID(long ownerID) {
        this.ownerID = ownerID;
    }

    public long getVetID() {
        return vetID;
    }

    public void setVetID(long vetID) {
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
