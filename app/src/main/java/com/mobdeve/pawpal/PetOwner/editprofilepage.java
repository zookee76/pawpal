package com.mobdeve.pawpal.PetOwner;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mobdeve.pawpal.Database.DBHelper;
import com.mobdeve.pawpal.Model.images;
import com.mobdeve.pawpal.Model.petOwners;
import com.mobdeve.pawpal.R;
import com.mobdeve.pawpal.Shared.appointmentspage;
import com.mobdeve.pawpal.Shared.consolidatedsummary;
import com.mobdeve.pawpal.Shared.petspage;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class editprofilepage extends AppCompatActivity {
    private List<images> imagesList;
    private DBHelper db;
    private Uri selectedPhotoUri;
    private ImageView pfp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editprofile);

        db = new DBHelper(getApplicationContext());

        // Elements
        pfp = findViewById(R.id.iv_pfp);
        EditText fname = findViewById(R.id.text_editfirstname);
        EditText lname = findViewById(R.id.text_editlastname);
        EditText email = findViewById(R.id.text_editemailadd);
        EditText contact = findViewById(R.id.text_editcontactno);

        FloatingActionButton addPhoto = findViewById(R.id.btn_addphoto);
        Button save = findViewById(R.id.btn_saveprofile);

        // Get Data
        if (db != null) {
            imagesList = db.getAllImages();
        } else {
            Log.e("DBHelper", "DBHelper is not initialized properly.");
        }

        Intent intent = getIntent();
        petOwners user = intent.getParcelableExtra("USER_DATA");
        if (user != null) {
            long imageID = user.getImageID();
            if(imageID > 0){
                String imagePath = db.getImagePath(imageID);
                if(imagePath != null){
                    File imgFile = new File(imagePath);
                    if(imgFile.exists()){
                        Glide.with(this)
                                .load(imgFile)
                                .into(pfp);
                    }
                }
            }
        }

        addPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 100); // Request Code = 100
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String first, last, eadd, contactno;
                first = fname.getText().toString();
                last = lname.getText().toString();
                eadd = email.getText().toString();
                contactno = contact.getText().toString();

                long userID = user.getID();

                if(!eadd.isEmpty() && !isValidEmail(eadd)){
                    Toast.makeText(editprofilepage.this, "Invalid Email", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(!contactno.isEmpty() && contactno.length() < 11){
                    Toast.makeText(editprofilepage.this, "Invalid Contact No", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(first.isEmpty())
                    first = user.getFname();

                if(last.isEmpty())
                    last = user.getLname();

                if(eadd.isEmpty())
                    eadd = user.getEmail();

                if(contactno.isEmpty())
                    contactno = user.getContactNo();

                try{
                    long prevImageID = user.getImageID();
                    long imageID = prevImageID;
                    String username = user.getUsername();

                    if(selectedPhotoUri != null){
                        File savedImageFile = images.saveImageToDirectory(selectedPhotoUri, getApplicationContext());
                        String imagePath = savedImageFile.getAbsolutePath();

                        int userType = 2; // PET = 1; PETOWNER = 2; CLINICOWNER = 3
                        imageID = db.addImage(imagePath, userID, userType);
                        Log.d("PHOTOUPDATE:", "SUCCESS newimageID: " + imageID);
                    }
                    else{
                        Log.d("PHOTOUPDATE:", "NONE");
                    }



                    petOwners updatedUser = new petOwners(userID, imageID, first, last, eadd, username, contactno);
                    long upID = updatedUser.getID();

                    Log.d("IMAGEIDUPDATE:", "oldimageID"+ user.getImageID() + "newimageID: " + updatedUser.getImageID());
                    Log.d("USER UPDATE", "UPDATES User ID" +userID+ " oldimageID: "+user.getImageID() + " : " + first + " " + last + " " + eadd + " " + contactno + " ImageID: " + imageID);
                    boolean isUpdated = db.updatePetOwner(updatedUser, userID);

                    Intent intent = new Intent(editprofilepage.this, petprofilepage.class);

                    if(isUpdated){
                        intent.putExtra("USER_DATA", updatedUser);
                        Log.d("UPDATEDUSER", "UPDATES User ID" +updatedUser.getID()+ ": " + updatedUser.getFname());
                        Log.d("UPDATEDUSER", "User updated successfully: " + updatedUser.toString());
                        startActivity(intent);
                    }else{
                        intent.putExtra("USER_DATA", user);
                        Log.d("UPDATEDUSER", "Failed to update user");
                        startActivity(intent);
                    }
                }catch (IOException e){
                    Log.d("UPDATEDUSER", "FAILED");
                    e.printStackTrace();
                }
            }
        });

        //Back Handle
        ImageView backImg = findViewById(R.id.iv_back);
        TextView backTxt = findViewById(R.id.tv_back);

        View.OnClickListener backListnr = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(editprofilepage.this, phomedashboard.class);
                intent.putExtra("USER_DATA", user);
                startActivity(intent);
                finish();
            }
        };

        backImg.setOnClickListener(backListnr);
        backTxt.setOnClickListener(backListnr);

        //Navigation Handle
        ImageView home, calendar, pets, files, profile;
        home = findViewById(R.id.iv_home);
        calendar = findViewById(R.id.iv_calendar);
        pets = findViewById(R.id.iv_pets);
        files = findViewById((R.id.iv_files));
        profile = findViewById(R.id.iv_userprofile);

        //Link to navigation buttons
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(editprofilepage.this, phomedashboard.class);
                intent.putExtra("USER_DATA", user);
                startActivity(intent);
                finish();
            }
        });

        pets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(editprofilepage.this, petspage.class);
                intent.putExtra("IS_PET_OWNER", true);
                intent.putExtra("USER_DATA", user);
                startActivity(intent);
                finish();
            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(editprofilepage.this, petprofilepage.class);
                intent.putExtra("USER_DATA", user);
                startActivity(intent);
                finish();
            }
        });

        files.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(editprofilepage.this, consolidatedsummary.class);
                intent.putExtra("USER_DATA", user);
                intent.putExtra("IS_PET_OWNER", true);
                startActivity(intent);
                finish();
            }
        });

        calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(editprofilepage.this, appointmentspage.class);
                intent.putExtra("USER_DATA", user);
                intent.putExtra("IS_PET_OWNER", true);
                startActivity(intent);
                finish();
            }
        });
    }

    private boolean isValidEmail(String email) {
        String emailPattern = "[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}";
        Pattern pattern = Pattern.compile(emailPattern);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public void saveProfile(){

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == RESULT_OK && data != null) {
            selectedPhotoUri = data.getData(); // Save the photo URI
            pfp.setImageURI(selectedPhotoUri); // Display the photo in the ImageView
        }
    }
}
