package com.mobdeve.pawpal.Model;

import android.content.Context;
import android.net.Uri;

import com.mobdeve.pawpal.Shared.userType;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class images {
    private long id, ownerId;
    private String imagePath;
    private userType userType;

    public images(String imagePath, long ownerId, userType type) {
        this.imagePath = imagePath;
        this.ownerId = ownerId;
        this.userType = type;
    }

    public userType getUserType(){
        return userType;
    }

    public void setUserType(userType type){
        this.userType = type;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(long ownerId) {
        this.ownerId = ownerId;
    }

    public static File saveImageToDirectory(Uri imageUri, Context context) throws IOException{
        InputStream inputStream = context.getContentResolver().openInputStream(imageUri);

        File directory = new File(context.getFilesDir(), "images");
        if(!directory.exists()){
            directory.mkdir(); // creates dir
        }

        File imageFile = new File(directory, "image_" + System.currentTimeMillis() + ".jpg");

        OutputStream outputStream = new FileOutputStream(imageFile);

        byte[] buffer = new byte[1024];
        int len;
        while((len = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, len);
        }

        inputStream.close();
        outputStream.close();

        return imageFile;
    }
}
