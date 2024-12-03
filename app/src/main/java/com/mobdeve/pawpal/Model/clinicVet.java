package com.mobdeve.pawpal.Model;

public class clinicVet {
    private int vetID;
    private int[] pets;

    public clinicVet(int vetID, int[] pets) {
        this.vetID = vetID;
        this.pets = pets;
    }

    public int getVetID() {
        return vetID;
    }

    public void setVetID(int vetID) {
        this.vetID = vetID;
    }

    public int[] getPets() {
        return pets;
    }

    public void setPets(int[] pets) {
        this.pets = pets;
    }
}
