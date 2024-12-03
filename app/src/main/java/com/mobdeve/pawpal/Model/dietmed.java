package com.mobdeve.pawpal.Model;

public class dietmed {
    private int image, presNo, petID;
    private String medicationName, purpose, dosage, administration, freq_and_duration;
    private String note;
    private String datetime;

    public dietmed(int image, String note, String datetime) {
        this.image = image;
        this.note = note;
        this.datetime = datetime;
    }

    public dietmed(int image, int petID, String medicationName, String purpose, String dosage, String administration, String freq_and_duration, String note, String datetime) {
        this.image = image;
        this.petID = petID;
        this.medicationName = medicationName;
        this.purpose = purpose;
        this.dosage = dosage;
        this.administration = administration;
        this.freq_and_duration = freq_and_duration;
        this.note = note;
        this.datetime = datetime;
    }

    public dietmed(int image, int presNo, int petID, String medicationName, String purpose, String dosage, String administration, String freq_and_duration, String note, String datetime) {
        this.image = image;
        this.presNo = presNo;
        this.petID = petID;
        this.medicationName = medicationName;
        this.purpose = purpose;
        this.dosage = dosage;
        this.administration = administration;
        this.freq_and_duration = freq_and_duration;
        this.note = note;
        this.datetime = datetime;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }
}
