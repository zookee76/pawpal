package com.mobdeve.pawpal.Model;

public class nutritionplan {
    private long ID, petID, vetID;
    private String dietname, servingSize, note;

    public nutritionplan(){

    }

    public nutritionplan(long ID, long petID, long vetID, String dietname, String servingSize, String note) {
        this.ID = ID;
        this.petID = petID;
        this.vetID = vetID;
        this.dietname = dietname;
        this.servingSize = servingSize;
        this.note = note;
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public long getPetID() {
        return petID;
    }

    public void setPetID(long petID) {
        this.petID = petID;
    }

    public long getVetID() {
        return vetID;
    }

    public void setVetID(long vetID) {
        this.vetID = vetID;
    }

    public String getDietname() {
        return dietname;
    }

    public void setDietname(String dietname) {
        this.dietname = dietname;
    }

    public String getServingSize() {
        return servingSize;
    }

    public void setServingSize(String servingSize) {
        this.servingSize = servingSize;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
