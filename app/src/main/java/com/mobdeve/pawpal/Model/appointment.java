package com.mobdeve.pawpal.Model;

public class appointment {
    private int appNo, petID, ownerID, vetID;
    private String petName, ownerName, appType, appDateTime, appVet, appStatus;

    public appointment(int appNo, String petName, String ownerName, String appType, String appDateTime, String appVet, String appStatus) {
        this.appNo = appNo;
        this.petName = petName;
        this.ownerName = ownerName;
        this.appType = appType;
        this.appDateTime = appDateTime;
        this.appVet = appVet;
        this.appStatus = appStatus;
    }

    public appointment(int appNo, int petID, int ownerID, int vetID, String petName, String ownerName, String appType, String appDateTime, String appVet, String appStatus) {
        this.appNo = appNo;
        this.petID = petID;
        this.ownerID = ownerID;
        this.vetID = vetID;
        this.petName = petName;
        this.ownerName = ownerName;
        this.appType = appType;
        this.appDateTime = appDateTime;
        this.appVet = appVet;
        this.appStatus = appStatus;
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

    public int getAppNo() {
        return appNo;
    }

    public void setAppNo(int appNo) {
        this.appNo = appNo;
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

    public String getAppType() {
        return appType;
    }

    public void setAppType(String appType) {
        this.appType = appType;
    }

    public String getAppDateTime() {
        return appDateTime;
    }

    public void setAppDateTime(String appDateTime) {
        this.appDateTime = appDateTime;
    }

    public String getAppVet() {
        return appVet;
    }

    public void setAppVet(String appVet) {
        this.appVet = appVet;
    }

    public String getAppStatus() {
        return appStatus;
    }

    public void setAppStatus(String appStatus) {
        this.appStatus = appStatus;
    }
}
