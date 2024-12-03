package com.mobdeve.pawpal.Model;

public class dietmed {
    private long imageID, presNo, petID;
    private String medicationName, purpose, dosage, administration, freq_and_duration;
    private String note;
    private String datetime;

    public dietmed(long imageID, String note, String datetime) {
        this.imageID = imageID;
        this.note = note;
        this.datetime = datetime;
    }

    public dietmed(long imageID, long petID, String medicationName, String purpose, String dosage, String administration, String freq_and_duration, String note, String datetime) {
        this.imageID = imageID;
        this.petID = petID;
        this.medicationName = medicationName;
        this.purpose = purpose;
        this.dosage = dosage;
        this.administration = administration;
        this.freq_and_duration = freq_and_duration;
        this.note = note;
        this.datetime = datetime;
    }

    public dietmed(long imageID, long presNo, long petID, String medicationName, String purpose, String dosage, String administration, String freq_and_duration, String note, String datetime) {
        this.imageID = imageID;
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

    public long getImage() {
        return imageID;
    }

    public void setImage(long image) {
        this.imageID = image;
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
