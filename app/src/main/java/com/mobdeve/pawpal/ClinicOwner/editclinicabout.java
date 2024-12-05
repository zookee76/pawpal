package com.mobdeve.pawpal.ClinicOwner;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.mobdeve.pawpal.Database.DBHelper;
import com.mobdeve.pawpal.Model.clinicVet;
import com.mobdeve.pawpal.Model.petOwners;
import com.mobdeve.pawpal.Model.pets;
import com.mobdeve.pawpal.R;
import com.mobdeve.pawpal.Shared.appointmentspage;
import com.mobdeve.pawpal.Shared.consolidatedsummary;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;

public class editclinicabout extends AppCompatActivity {
    private DBHelper DB;
    private pets petData;
    private petOwners ownerData;
    private TextView petName;
    private EditText petDesc,dateOfBirth,age,breed,height,weight,petcolor,markings;
    private Button saveDesc, saveProfile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliniceditabout);

        DB = new DBHelper(getApplicationContext());
        Intent intent = getIntent();
        petData = intent.getParcelableExtra("PET_DATA");
        clinicVet vetData = intent.getParcelableExtra("VET_DATA");
        long petID = petData.getID();
        ownerData = DB.getPetOwner(petID);

        // ELEMENTS
        ImageView petImage = findViewById(R.id.petImage);
        petName = findViewById(R.id.petName);
        petName.setText(petData.getName());

        // USER INPUTS
        petDesc = findViewById(R.id.text_description);
        saveDesc= findViewById(R.id.btn_savedesc);

        dateOfBirth = findViewById(R.id.text_birthdate);
        age = findViewById(R.id.text_age);
        breed = findViewById(R.id.text_breed);
        height = findViewById(R.id.text_height);
        weight = findViewById(R.id.text_weight);
        petcolor = findViewById(R.id.text_color);
        markings = findViewById(R.id.text_marking);
        saveProfile = findViewById(R.id.btn_saveprofile);

        dateOfBirth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(editclinicabout.this,
                        (v, selectedYear, selectedMonth, selectedDay) -> {
                            String selectedDate = selectedDay + "/" + (selectedMonth + 1) + "/" + selectedYear;
                            dateOfBirth.setText(selectedDate); // Display on EditText
                        }, year, month, day);
                datePickerDialog.show();
            }
        });

        long imageID = petData.getImageID();
        String imagePath = DB.getImagePath(imageID);

        if(imagePath!=null){
            File imgFile = new File(imagePath);
            if(imgFile.exists()){
                Glide.with(getApplicationContext())
                        .load(imgFile)
                        .into(petImage);
            }
        }

        if(petData != null){
            petName.setText(petData.getName());
        }

        // Saving pet description
        saveDesc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        // Saving Pet Profile
        saveProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String editname = petData.getName();
                String editbreed = breed.getText().toString().trim();
                String editcolor = petcolor.getText().toString().trim();
                String editmarkings = markings.getText().toString().trim();
                String editbdate = dateOfBirth.getText().toString().trim();
                String editsex = petData.getSex();
                String editowner = ownerData.getFullname();
                String editage = age.getText().toString().trim();
                String editheight = height.getText().toString().trim();
                String editweight = weight.getText().toString().trim();

                Integer editedage = null;
                Double editedheight = null, editedweight = null;
                // Initialize first with old data
                if (editbreed.isEmpty()){
                    editbreed = petData.getBreed();
                }
                if(editcolor.isEmpty() ){
                    editcolor = petData.getColor();
                }
                if(editmarkings.isEmpty()){
                    editmarkings = petData.getMarkings();
                }
                if(editbdate.isEmpty()){
                    editbdate = petData.getBirthdate();
                }
                if(editsex.isEmpty()){
                    editsex = petData.getSex();
                }
                if(editage.isEmpty()){
                    editedage = petData.getAge();
                }else{
                    editedage = Integer.parseInt(editage);
                }
                if(editheight.isEmpty()){
                    editedheight = petData.getWeight();
                }else{
                    editedheight = Double.parseDouble(editheight);
                }
                if(editweight.isEmpty()){
                    editedweight = petData.getWeight();
                }else{
                    editedweight = Double.parseDouble(editweight);
                }

                long ownerID = ownerData.getID();
                long imageID = petData.getImageID();
                long vetID = vetData.getVetID();
                long petID = petData.getID();
                pets updatePet = new pets(editname, editbreed, editsex, editcolor, editmarkings, editbdate, editedage, editedheight, editedweight, petID, imageID, ownerID, vetID);

                if(petID>0){
                    Boolean isUpdated =  DB.updatePet(updatePet);
                    if(isUpdated){
                        Toast.makeText(editclinicabout.this, "Pet updated successfully.", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(editclinicabout.this, cabout.class);
                        Log.d("CHECKEDIT ABOUT", "Updated petID: " + updatePet.getID());
                        intent.putExtra("PET_DATA", petData);
                        intent.putExtra("VET_DATA", vetData);
                        setResult(RESULT_OK, intent);
                        startActivity(intent);
                    }else{
                        Toast.makeText(editclinicabout.this, "Failed to update pet.", Toast.LENGTH_SHORT).show();
                        Log.d("CHECKEDIT ABOUT", "FAILED TO UPDATE");
                    }
                }else{
                    Log.d("CHECKEDIT ABOUT", "FAILED TO UPDATE");
                    Toast.makeText(editclinicabout.this, "Failed to update pet.", Toast.LENGTH_SHORT).show();
                }

            }
        });


        //Back Handle
        ImageView backImg = findViewById(R.id.iv_back);
        TextView backTxt = findViewById(R.id.tv_back);

        View.OnClickListener backListnr = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        };
        backImg.setOnClickListener(backListnr);
        backTxt.setOnClickListener(backListnr);

        // Navigations
        TextView about, meddocu, meddiet, schedules;
        ImageView edit;

        about = findViewById(R.id.aboutcategory);
        meddocu = findViewById(R.id.medicaldocscategory);
        meddiet = findViewById(R.id.medicationdietcategory);
        schedules = findViewById(R.id.schedulecategory);

        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(editclinicabout.this, cabout.class);

                intent.putExtra("PET_DATA", petData);
                intent.putExtra("VET_DATA", vetData);
                intent.putExtra("PETOWNER_DATA", ownerData);
                startActivity(intent);
                finish();
            }
        });

        meddocu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(editclinicabout.this, cmedicaldocs.class);
                intent.putExtra("PET_DATA", petData);
                intent.putExtra("VET_DATA", vetData);
                intent.putExtra("PETOWNER_DATA", ownerData);
                startActivity(intent);
                finish();
            }
        });

        meddiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(editclinicabout.this, cmedicationdiet.class);
                intent.putExtra("PET_DATA", petData);
                intent.putExtra("VET_DATA", vetData);
                intent.putExtra("PETOWNER_DATA", ownerData);
                startActivity(intent);
                finish();
            }
        });

        schedules.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(editclinicabout.this, cschedules.class);
                intent.putExtra("PET_DATA", petData);
                intent.putExtra("VET_DATA", vetData);
                intent.putExtra("PETOWNER_DATA", ownerData);
                startActivity(intent);
                finish();
            }
        });

        //Main Navigation Handle
        ImageView home, calendar, files, profile, pets;
        home = findViewById(R.id.iv_home);
        calendar = findViewById(R.id.iv_calendar);
        files = findViewById((R.id.iv_files));
        profile = findViewById(R.id.iv_userprofile);
        pets = findViewById(R.id.iv_pets);

        //Link to navigation buttons
        pets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(editclinicabout.this, clinicpets.class);
                intent.putExtra("USER_DATA", vetData);
                startActivity(intent);
                finish();
            }
        });
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(editclinicabout.this, chomedashboard.class);
                intent.putExtra("USER_DATA", vetData);
                startActivity(intent);
                finish();
            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(editclinicabout.this, clinicprofilepage.class);
                intent.putExtra("USER_DATA", vetData);
                startActivity(intent);
                finish();
            }
        });

        files.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(editclinicabout.this, consolidatedsummary.class);
                intent.putExtra("IS_PET_OWNER", false);
                intent.putExtra("USER_DATA", vetData);
                startActivity(intent);
                finish();
            }
        });

        calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(editclinicabout.this, appointmentspage.class);
                intent.putExtra("IS_PET_OWNER", false);
                intent.putExtra("USER_DATA", vetData);
                startActivity(intent);
                finish();
            }
        });

        // Logic to handle inputs

    }
}