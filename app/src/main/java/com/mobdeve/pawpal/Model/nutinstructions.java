package com.mobdeve.pawpal.Model;

public class nutinstructions {
    private long ID, nutplanID;
    private String step;

    public nutinstructions(){}
    public nutinstructions(long ID, long nutplanID, String step) {
        this.ID = ID;
        this.nutplanID = nutplanID;
        this.step = step;
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public long getNutplanID() {
        return nutplanID;
    }

    public void setNutplanID(long nutplanID) {
        this.nutplanID = nutplanID;
    }

    public String getStep() {
        return step;
    }

    public void setStep(String step) {
        this.step = step;
    }
}
