package com.mobdeve.pawpal.Model;

public class nutritionfacts {
    private long ID, nutplanID;
    private String calories, fat, fiber, protein, keyNutrients;

    public  nutritionfacts(){}

    public nutritionfacts(long ID, long nutplanID, String calories, String fat, String fiber, String protein, String keyNutrients) {
        this.ID = ID;
        this.nutplanID = nutplanID;
        this.calories = calories;
        this.fat = fat;
        this.fiber = fiber;
        this.protein = protein;
        this.keyNutrients = keyNutrients;
    }

    public String getCalories() {
        return calories;
    }

    public void setCalories(String calories) {
        this.calories = calories;
    }

    public String getFat() {
        return fat;
    }

    public void setFat(String fat) {
        this.fat = fat;
    }

    public String getFiber() {
        return fiber;
    }

    public void setFiber(String fiber) {
        this.fiber = fiber;
    }

    public String getProtein() {
        return protein;
    }

    public void setProtein(String protein) {
        this.protein = protein;
    }

    public String getKeyNutrients() {
        return keyNutrients;
    }

    public void setKeyNutrients(String keyNutrients) {
        this.keyNutrients = keyNutrients;
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
}
