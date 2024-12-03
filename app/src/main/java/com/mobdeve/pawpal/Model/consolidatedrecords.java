package com.mobdeve.pawpal.Model;

public class consolidatedrecords {

    // Medical Documents
    private int recordNo, vetID, petID;
    private String title;
    private String docType;
    private String docDate;
    private String veterinarian;
    private String file;

    public consolidatedrecords() {

    }

    public consolidatedrecords(int recordNo, String title, String docType, String docDate, String veterinarian, String file) {
        this.recordNo = recordNo;
        this.title = title;
        this.docType = docType;
        this.docDate = docDate;
        this.veterinarian = veterinarian;
        this.file = file;
    }

    public consolidatedrecords(int recordNo, int vetID, int petID, String title, String docType, String docDate, String veterinarian, String file) {
        this.recordNo = recordNo;
        this.vetID = vetID;
        this.petID = petID;
        this.title = title;
        this.docType = docType;
        this.docDate = docDate;
        this.veterinarian = veterinarian;
        this.file = file;
    }

    public int getVetID() {
        return vetID;
    }

    public void setVetID(int vetID) {
        this.vetID = vetID;
    }

    public int getPetID() {
        return petID;
    }

    public void setPetID(int petID) {
        this.petID = petID;
    }

    public int getRecordNo() {
        return recordNo;
    }

    public void setRecordNo(int recordNo) {
        this.recordNo = recordNo;
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
