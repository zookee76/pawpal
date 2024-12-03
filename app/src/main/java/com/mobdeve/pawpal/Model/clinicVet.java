package com.mobdeve.pawpal.Model;

public class clinicVet {
    private long vetID, imageID;
    private String firstName, lastName, emailAdd, password;

    public clinicVet(long imageID, String firstName, String lastName, String emailAdd, String password) {
        this.imageID = imageID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAdd = emailAdd;
        this.password = password;
    }

    public clinicVet(long vetID, long imageID, String firstName, String lastName, String emailAdd, String password) {
        this.vetID = vetID;
        this.imageID = imageID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAdd = emailAdd;
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailAdd() {
        return emailAdd;
    }

    public void setEmailAdd(String emailAdd) {
        this.emailAdd = emailAdd;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getVetID() {
        return vetID;
    }

    public void setVetID(long vetID) {
        this.vetID = vetID;
    }

    public long getImageID() {
        return imageID;
    }

    public void setImageID(long imageID) {
        this.imageID = imageID;
    }
}
