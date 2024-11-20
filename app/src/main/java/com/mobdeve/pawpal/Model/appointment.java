package com.mobdeve.pawpal.Model;

public class appointment {
    private int appNo;
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
