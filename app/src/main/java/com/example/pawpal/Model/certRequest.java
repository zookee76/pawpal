package com.example.pawpal.Model;

public class certRequest {
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

    // Getters
    public String getRequestNumber() { return requestNumber; }
    public String getPetName() { return petName; }
    public String getOwnerName() { return ownerName; }
    public String getRequestType() { return requestType; }
    public String getDate() { return date; }
    public String getStatus() { return status; }
}

