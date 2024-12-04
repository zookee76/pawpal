package com.mobdeve.pawpal.Model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class petOwners implements Parcelable {
    private long ID, imageID;
    private String fname, lname, email, password, username, contactNo;

    private String fullname;

    public petOwners(){
        this.fname = "";
        this.lname = "";
        this.email = "";
        this.password = "";
    }

    public petOwners(String fname) {
        this.fullname = fname;
    }

    public petOwners(String fname, String lname){
        this.fname = fname;
        this.lname = lname;
    }

    // can use this for registering
    public petOwners(String fname, String lname, String email, String password, String username, String contactNo, long imageID) {
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.password = password;
        this.username = username;
        this.imageID = imageID;
        this.contactNo = contactNo;
    }

    // Use for login

    public petOwners(long ID, long imageID, String fname, String lname, String email, String password, String username, String contactNo) {
        this.ID = ID;
        this.imageID = imageID;
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.password = password;
        this.username = username;
        this.contactNo = contactNo;
    }

    public petOwners(long ID, long imageID, String fname, String lname, String email, String username, String contactNo) {
        this.ID = ID;
        this.imageID = imageID;
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.username = username;
        this.contactNo = contactNo;
    }

    public petOwners(String fname, String lname, String email, String username, String contactno, String password){
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.username = username;
        this.contactNo = contactno;
        this.password = password;
    }

    public petOwners(String fname, String lname, String email, String contactNo, long imageID){
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.contactNo = contactNo;
        this.imageID = imageID;
    }

    public petOwners(String fname, String lname, String email, String password, String username, int imageID, String fullname) {
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.password = password;
        this.username = username;
        this.imageID = imageID;
        this.fullname = fullname;
    }

    public petOwners(String name, int imageID) {
        this.fullname = name;
        this.imageID = imageID;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getUsername() {
        return username;
    }

    public String getFullname(){
        this.fullname  = fname + " " + lname;
        return fullname;
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
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

    public long getImageID() {
        return imageID;
    }

    public void setImageID(int imageID) {
        this.imageID = imageID;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(fname);
        parcel.writeString(lname);
        parcel.writeString(username);
        parcel.writeString(email);
        parcel.writeString(password);
        parcel.writeLong(imageID);
        parcel.writeLong(ID);
    }

    protected petOwners(Parcel in){
        fname = in.readString();
        lname = in.readString();
        username = in.readString();
        email = in.readString();
        password = in.readString();
        imageID = in.readLong();
        ID = in.readLong();
    }

    public static final Creator<petOwners> CREATOR = new Creator<petOwners>() {
        @Override
        public petOwners createFromParcel(Parcel in) {
            return new petOwners(in);
        }

        @Override
        public petOwners[] newArray(int size) {
            return new petOwners[size];
        }
    };

    @Override
    public String toString() {
        return "petOwners{" +
                "ID=" + ID +
                ", imageID=" + imageID +
                ", fname='" + fname + '\'' +
                ", lname='" + lname + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", contactNo='" + contactNo + '\'' +
                '}';
    }
}
