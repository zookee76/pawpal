package com.mobdeve.pawpal.Model;

public class appointment {
    private long appNo, petID, ownerID, vetID;

    private String petName, ownerName, appType, appDateTime, appVet, appStatus;

    public appointment(){}

    public appointment(long petID, long ownerID, long vetID, String petName, String ownerName, String appType, String appDateTime, String appVet, String appStatus) {
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

    public appointment(String petName, String ownerName, String appType, String appDateTime, String appVet, String appStatus) {
        this.petName = petName;
        this.ownerName = ownerName;
        this.appType = appType;
        this.appDateTime = appDateTime;
        this.appVet = appVet;
        this.appStatus = appStatus;
    }

    public appointment(long appNo, long petID, long ownerID, long vetID, String petName, String ownerName, String appType, String appDateTime, String appVet, String appStatus) {
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

    public long getAppNo() {
        return appNo;
    }

    public void setAppNo(long appNo) {
        this.appNo = appNo;
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
