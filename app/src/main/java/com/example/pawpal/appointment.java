package com.example.pawpal;

public class appointment {
    private String appNo, petName, ownerName, appType, appDateTime, appVet, appStatus;

    public appointment(String appNo, String petName, String ownerName, String appType, String appDateTime, String appVet, String appStatus) {
        this.appNo = appNo;
        this.petName = petName;
        this.ownerName = ownerName;
        this.appType = appType;
        this.appDateTime = appDateTime;
        this.appVet = appVet;
        this.appStatus = appStatus;
    }

    public String getAppNo() {
        return appNo;
    }

    public void setAppNo(String appNo) {
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
