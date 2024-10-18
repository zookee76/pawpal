package com.example.pawpal;

public class overviewentry {

    private int petImage;
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

