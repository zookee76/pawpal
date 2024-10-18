package com.example.pawpal;

public class consolidatedrecords {

    private String title;
    private String docType;
    private String docDate;
    private String veterinarian;
    private String file;

    public consolidatedrecords(String title, String docType, String docDate, String veterinarian, String file) {
        this.title = title;
        this.docType = docType;
        this.docDate = docDate;
        this.veterinarian = veterinarian;
        this.file = file;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDocType() {
        return docType;
    }

    public void setDocType(String docType) {
        this.docType = docType;
    }

    public String getDocDate() {
        return docDate;
    }

    public void setDocDate(String docDate) {
        this.docDate = docDate;
    }

    public String getVeterinarian() {
        return veterinarian;
    }

    public void setVeterinarian(String veterinarian) {
        this.veterinarian = veterinarian;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }
}
