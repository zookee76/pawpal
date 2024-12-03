package com.mobdeve.pawpal.Model;

public class consolidatedrecords {

    // Medical Documents
    private int recordNo;
    private long vetID, petID;
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

    public consolidatedrecords(long vetID, long petID, String title, String docType, String docDate, String veterinarian, String file) {
        this.vetID = vetID;
        this.petID = petID;
        this.title = title;
        this.docType = docType;
        this.docDate = docDate;
        this.veterinarian = veterinarian;
        this.file = file;
    }

    public consolidatedrecords(int recordNo, long vetID, long petID, String title, String docType, String docDate, String veterinarian, String file) {
        this.recordNo = recordNo;
        this.vetID = vetID;
        this.petID = petID;
        this.title = title;
        this.docType = docType;
        this.docDate = docDate;
        this.veterinarian = veterinarian;
        this.file = file;
    }

    public long getVetID() {
        return vetID;
    }

    public void setVetID(long vetID) {
        this.vetID = vetID;
    }

    public long getPetID() {
        return petID;
    }

    public void setPetID(long petID) {
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
