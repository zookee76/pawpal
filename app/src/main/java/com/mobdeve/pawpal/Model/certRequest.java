package com.mobdeve.pawpal.Model;

public class certRequest {
    private int certID, petID, ownerID;
    private String requestNumber;
    private String petName;
    private String ownerName;
    private String requestType;
    private String date;
    private String status;

    // Constructor
    public certRequest(String requestNumber, String petName, String ownerName, String requestType, String date, String status) {
        this.requestNumber = requestNumber;
        this.petName = petName;
        this.ownerName = ownerName;
        this.requestType = requestType;
        this.date = date;
        this.status = status;
    }

    public certRequest(int certID, int petID, int ownerID, String requestNumber, String petName, String ownerName, String requestType, String date, String status) {
        this.certID = certID;
        this.petID = petID;
        this.ownerID = ownerID;
        this.requestNumber = requestNumber;
        this.petName = petName;
        this.ownerName = ownerName;
        this.requestType = requestType;
        this.date = date;
        this.status = status;
    }

    public certRequest(int petID, int ownerID, String requestNumber, String petName, String ownerName, String requestType, String date, String status) {
        this.petID = petID;
        this.ownerID = ownerID;
        this.requestNumber = requestNumber;
        this.petName = petName;
        this.ownerName = ownerName;
        this.requestType = requestType;
        this.date = date;
        this.status = status;
    }

    // Getters
    public String getRequestNumber() { return requestNumber; }
    public String getPetName() { return petName; }
    public String getOwnerName() { return ownerName; }
    public String getRequestType() { return requestType; }
    public String getDate() { return date; }
    public String getStatus() { return status; }

    public int getCertID() {
        return certID;
    }

    public void setCertID(int certID) {
        this.certID = certID;
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

    public void setRequestNumber(String requestNumber) {
        this.requestNumber = requestNumber;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

