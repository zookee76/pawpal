package com.mobdeve.pawpal.Model;

public class petDB {
    private int petID;
    private String petName;
    private String breed;
    private String sex;
    private int age;
    private String petProfile;
    private String birthdate;
    private double height;
    private double weight;
    private String color;
    private String markings;
    private int petOwnerID;

    public petDB(int petID, String petName, String breed, int age, String sex, String petProfile, String birthdate, double height, double weight, String color, int petOwnerID, String markings) {
        this.petID = petID;
        this.petName = petName;
        this.breed = breed;
        this.age = age;
        this.sex = sex;
        this.petProfile = petProfile;
        this.birthdate = birthdate;
        this.height = height;
        this.weight = weight;
        this.color = color;
        this.petOwnerID = petOwnerID;
        this.markings = markings;
    }

    // Getters and Setters
    public int getPetID() {
        return petID;
    }

    public void setPetID(int petID) {
        this.petID = petID;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPetProfile() {
        return petProfile;
    }

    public void setPetProfile(String petProfile) {
        this.petProfile = petProfile;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getMarkings() {
        return markings;
    }

    public void setMarkings(String markings) {
        this.markings = markings;
    }

    public int getPetOwnerID() {
        return petOwnerID;
    }

    public void setPetOwnerID(int petOwnerID) {
        this.petOwnerID = petOwnerID;
    }
}

