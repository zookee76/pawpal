package com.mobdeve.pawpal.Model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.Date;

public class pets implements Parcelable {
    private String name, breed, sex, color, markings, birthdate;
    private int age;
    private Double height, weight;
    private long ID, imageID, ownerID;

    public pets(String name, String breed, String sex, String color, String markings, String birthdate, int age, Double height, Double weight, long ID, long imageID, long ownerID) {
        this.name = name;
        this.breed = breed;
        this.sex = sex;
        this.color = color;
        this.markings = markings;
        this.birthdate = birthdate;
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.ID = ID;
        this.imageID = imageID;
        this.ownerID = ownerID;
    }

    public pets(String name, String breed, String sex, String color, String markings, String birthdate, int age, long ownerID, long imageID, Double height, Double weight) {
        this.name = name;
        this.breed = breed;
        this.sex = sex;
        this.color = color;
        this.markings = markings;
        this.birthdate = birthdate;
        this.age = age;
        this.ownerID = ownerID;
        this.imageID = imageID;
        this.height = height;
        this.weight = weight;
    }

    protected pets(Parcel in) {
        name = in.readString();
        breed = in.readString();
        sex = in.readString();
        color = in.readString();
        markings = in.readString();
        birthdate = in.readString();
        age = in.readInt();
        if (in.readByte() == 0) {
            height = null;
        } else {
            height = in.readDouble();
        }
        if (in.readByte() == 0) {
            weight = null;
        } else {
            weight = in.readDouble();
        }
        ID = in.readLong();
        imageID = in.readLong();
        ownerID = in.readLong();
    }

    public static final Creator<pets> CREATOR = new Creator<pets>() {
        @Override
        public pets createFromParcel(Parcel in) {
            return new pets(in);
        }

        @Override
        public pets[] newArray(int size) {
            return new pets[size];
        }
    };

    public long getImageID() {
        return imageID;
    }

    public void setImageID(long imageID) {
        this.imageID = imageID;
    }

    public void setOwnerID(long ownerID) {
        this.ownerID = ownerID;
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public long getOwnerID() {
        return ownerID;
    }

    public String getMarkings() {
        return markings;
    }

    public void setMarkings(String markings) {
        this.markings = markings;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(breed);
        parcel.writeString(sex);
        parcel.writeString(color);
        parcel.writeString(markings);
        parcel.writeString(birthdate);
        parcel.writeInt(age);
        if (height == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeDouble(height);
        }
        if (weight == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeDouble(weight);
        }
        parcel.writeLong(ID);
        parcel.writeLong(imageID);
        parcel.writeLong(ownerID);
    }
}

