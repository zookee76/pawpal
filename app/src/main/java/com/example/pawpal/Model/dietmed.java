package com.example.pawpal.Model;

public class dietmed {
    private int image;
    private String note;
    private String datetime;

    public dietmed(int image, String note, String datetime) {
        this.image = image;
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
