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

    public long getImageID() {
        return imageID;
    }

    public void setImageID(long imageID) {
        this.imageID = imageID;
    }

    public long getPresNo() {
        return presNo;
    }

    public void setPresNo(long presNo) {
        this.presNo = presNo;
    }

    public long getPetID() {
        return petID;
    }

    public void setPetID(long petID) {
        this.petID = petID;
    }

    public String getMedicationName() {
        return medicationName;
    }

    public void setMedicationName(String medicationName) {
        this.medicationName = medicationName;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public String getAdministration() {
        return administration;
    }

    public void setAdministration(String administration) {
        this.administration = administration;
    }

    public String getFreq_and_duration() {
        return freq_and_duration;
    }

    public void setFreq_and_duration(String freq_and_duration) {
        this.freq_and_duration = freq_and_duration;
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
