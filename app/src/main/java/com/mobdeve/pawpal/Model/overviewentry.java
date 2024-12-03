package com.mobdeve.pawpal.Model;

public class overviewentry {

    private int petImage, overviewID, petID, ownerID, appID, vetID;
    private String timeText;
    private String petName;
    private String ownerName;
    private String appointmentType;
    private String veterinarian;

    public overviewentry(int petImage, String timeText, String petName, String ownerName,
                         String appointmentType, String veterinarian) {
        this.petImage = petImage;
        this.timeText = timeText;
        this.petName = petName;
        this.ownerName = ownerName;
        this.appointmentType = appointmentType;
        this.veterinarian = veterinarian;
    }

    public overviewentry(int petImage, int petID, int ownerID, int appID, int vetID, String timeText, String petName, String ownerName, String appointmentType, String veterinarian) {
        this.petImage = petImage;
        this.petID = petID;
        this.ownerID = ownerID;
        this.appID = appID;
        this.vetID = vetID;
        this.timeText = timeText;
        this.petName = petName;
        this.ownerName = ownerName;
        this.appointmentType = appointmentType;
        this.veterinarian = veterinarian;
    }

    public overviewentry(int petImage, int overviewID, int petID, int ownerID, int appID, int vetID, String timeText, String petName, String ownerName, String appointmentType, String veterinarian) {
        this.petImage = petImage;
        this.overviewID = overviewID;
        this.petID = petID;
        this.ownerID = ownerID;
        this.appID = appID;
        this.vetID = vetID;
        this.timeText = timeText;
        this.petName = petName;
        this.ownerName = ownerName;
        this.appointmentType = appointmentType;
        this.veterinarian = veterinarian;
    }

    public int getOverviewID() {
        return overviewID;
    }

    public void setOverviewID(int overviewID) {
        this.overviewID = overviewID;
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

    public int getAppID() {
        return appID;
    }

    public void setAppID(int appID) {
        this.appID = appID;
    }

    public int getVetID() {
        return vetID;
    }

    public void setVetID(int vetID) {
        this.vetID = vetID;
    }

    public int getPetImage() {
        return petImage;
    }

    public void setPetImage(int petImage) {
        this.petImage = petImage;
    }

    public String getTimeText() {
        return timeText;
    }

    public void setTimeText(String timeText) {
        this.timeText = timeText;
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

    public String getAppointmentType() {
        return appointmentType;
    }

    public void setAppointmentType(String appointmentType) {
        this.appointmentType = appointmentType;
    }

    public String getVeterinarian() {
        return veterinarian;
    }

    public void setVeterinarian(String veterinarian) {
        this.veterinarian = veterinarian;
    }
}

