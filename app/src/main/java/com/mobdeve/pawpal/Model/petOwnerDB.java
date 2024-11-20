package com.mobdeve.pawpal.Model;

public class petOwnerDB {
    private int petOwnerID;
    private String petOwnerName;
    private String email;
    private String password;
    private String petProfile;

    public petOwnerDB(int petOwnerID, String petOwnerName, String email, String password, String petProfile) {
        this.petOwnerID = petOwnerID;
        this.petOwnerName = petOwnerName;
        this.email = email;
        this.password = password;
        this.petProfile = petProfile;
    }

    public int getPetOwnerID() {
        return petOwnerID;
    }

    public void setPetOwnerID(int petOwnerID) {
        this.petOwnerID = petOwnerID;
    }

    public String getPetOwnerName() {
        return petOwnerName;
    }

    public void setPetOwnerName(String petOwnerName) {
        this.petOwnerName = petOwnerName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPetProfile() {
        return petProfile;
    }

    public void setPetProfile(String petProfile) {
        this.petProfile = petProfile;
    }
}
