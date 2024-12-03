package com.mobdeve.pawpal.Model;

public class overviewentry {

    private long petImage, overviewID, petID, ownerID, appID, vetID;
    private String timeText;
    private String petName;
    private String ownerName;
    private String appointmentType;
    private String veterinarian;

    public overviewentry(long petImage, String timeText, String petName, String ownerName,
                         String appointmentType, String veterinarian) {
        this.petImage = petImage;
        this.timeText = timeText;
        this.petName = petName;
        this.ownerName = ownerName;
        this.appointmentType = appointmentType;
        this.veterinarian = veterinarian;
    }

    public overviewentry(long petImage, long petID, long ownerID, long appID, long vetID, String timeText, String petName, String ownerName, String appointmentType, String veterinarian) {
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

    public overviewentry(long petImage, long overviewID, long petID, long ownerID, long appID, long vetID, String timeText, String petName, String ownerName, String appointmentType, String veterinarian) {
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

    public long getOverviewID() {
        return overviewID;
    }

    public void setOverviewID(long overviewID) {
        this.overviewID = overviewID;
    }

    public long getPetID() {
        return petID;
    }

    public void setPetID(long petID) {
        this.petID = petID;
    }

    public long getPetImage() {
        return petImage;
    }

    public void setPetImage(long petImage) {
        this.petImage = petImage;
    }

    public long getOwnerID() {
        return ownerID;
    }

    public void setOwnerID(long ownerID) {
        this.ownerID = ownerID;
    }

    public long getAppID() {
        return appID;
    }

    public void setAppID(long appID) {
        this.appID = appID;
    }

    public long getVetID() {
        return vetID;
    }

    public void setVetID(long vetID) {
        this.vetID = vetID;
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

