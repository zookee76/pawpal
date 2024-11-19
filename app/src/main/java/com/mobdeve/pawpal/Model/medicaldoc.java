package com.mobdeve.pawpal.Model;

public class medicaldoc {
    private String title;
    private String type;
    private String date;
    private String veterinarian;
    private String fileName;

    public medicaldoc(String title, String type, String date, String veterinarian, String fileName) {
        this.title = title;
        this.type = type;
        this.date = date;
        this.veterinarian = veterinarian;
        this.fileName = fileName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getVeterinarian() {
        return veterinarian;
    }

    public void setVeterinarian(String veterinarian) {
        this.veterinarian = veterinarian;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
