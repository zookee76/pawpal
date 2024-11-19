package com.mobdeve.pawpal.Model;

public class petOwners {
    private String name, email, password;
    private int ownerphoto;

    public petOwners(String name) {
        this.name = name;
    }

    // can use this for registering
    public petOwners(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public petOwners(String name, int ownerphoto) {
        this.name = name;
        this.ownerphoto = ownerphoto;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public int getOwnerphoto() {
        return ownerphoto;
    }

    public void setOwnerphoto(int ownerphoto) {
        this.ownerphoto = ownerphoto;
    }
}
