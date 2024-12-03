package com.mobdeve.pawpal.Model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class clinicVet implements Parcelable {
    private long vetID, imageID;
    private String firstName, lastName, emailAdd, password, contactNo;

    public clinicVet(long imageID, String firstName, String lastName, String emailAdd, String password, String contactNo) {
        this.imageID = imageID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAdd = emailAdd;
        this.password = password;
        this.contactNo = contactNo;
    }

    public clinicVet(long vetID, long imageID, String firstName, String lastName, String emailAdd, String password, String contactNo) {
        this.vetID = vetID;
        this.imageID = imageID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAdd = emailAdd;
        this.password = password;
        this.contactNo = contactNo;
    }

    protected clinicVet(Parcel in) {
        vetID = in.readLong();
        imageID = in.readLong();
        firstName = in.readString();
        lastName = in.readString();
        emailAdd = in.readString();
        password = in.readString();
        contactNo = in.readString();
    }

    public static final Creator<clinicVet> CREATOR = new Creator<clinicVet>() {
        @Override
        public clinicVet createFromParcel(Parcel in) {
            return new clinicVet(in);
        }

        @Override
        public clinicVet[] newArray(int size) {
            return new clinicVet[size];
        }
    };

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
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


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeLong(vetID);
        parcel.writeLong(imageID);
        parcel.writeString(firstName);
        parcel.writeString(lastName);
        parcel.writeString(emailAdd);
        parcel.writeString(password);
        parcel.writeString(contactNo);
    }
}
