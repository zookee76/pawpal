package com.example.pawpal;

public class vaccination {
    private String vaccType, petName, ownerName, vaxDateTime, vaxVet, vaxStatus;

    public vaccination(String vaccType, String petName, String ownerName, String vaxDateTime, String vaxVet, String vaxStatus) {
        this.vaccType = vaccType;
        this.petName = petName;
        this.ownerName = ownerName;
        this.vaxDateTime = vaxDateTime;
        this.vaxVet = vaxVet;
        this.vaxStatus = vaxStatus;
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
