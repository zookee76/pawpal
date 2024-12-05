package com.mobdeve.pawpal.ClinicOwner;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

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

public class cabout extends AppCompatActivity {
    private DBHelper DB;
    private TextView petName,petDesc,dateOfBirth,age,gender,breed,height,weight,petcolor,markings,ownername,cellno,address, aboutTitle;
    private pets petData;
    private petOwners ownerData;
    private clinicVet vetData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clinicpetprofileabout);

        DB = new DBHelper(getApplicationContext());
        Intent intent = getIntent();
        petData = intent.getParcelableExtra("PET_DATA");
        vetData = intent.getParcelableExtra("VET_DATA");
        long petID = petData.getID();
        petOwners retrievedOwnerData = DB.getPetOwner(petID);

        Log.d("CHECKpabout", "ownerData: " + ownerData);
        // ELEMENTS
        ImageView petImage = findViewById(R.id.petImage);
        petName = findViewById(R.id.petName);
        petName.setText(petData.getName());
        petDesc = findViewById(R.id.aboutdescription);
        dateOfBirth = findViewById(R.id.dateofbirth);
        age = findViewById(R.id.age);
        gender = findViewById(R.id.gender);
        breed = findViewById(R.id.breed);
        height = findViewById(R.id.height);
        weight = findViewById(R.id.weight);
        petcolor = findViewById(R.id.petcolor);
        markings = findViewById(R.id.markings);
        ownername = findViewById(R.id.ownername);
        cellno = findViewById(R.id.cellphonenumber);
        address = findViewById(R.id.address);
        aboutTitle = findViewById(R.id.aboutTitle);

        if(retrievedOwnerData != null){
            ownerData = retrievedOwnerData;
            populatePetDetails(petData);
            populatePetOwnerDetails(ownerData);
        }
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

        // options
        TextView about, meddocu, meddiet, schedules;
        ImageView edit;

        about = findViewById(R.id.aboutcategory);
        meddocu = findViewById(R.id.medicaldocscategory);
        meddiet = findViewById(R.id.medicationdietcategory);
        schedules = findViewById(R.id.schedulecategory);
        edit = findViewById(R.id.iv_edit);

        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(cabout.this, cabout.class);
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
                Intent intent = new Intent(cabout.this, cmedicaldocs.class);
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
                Intent intent = new Intent(cabout.this, cmedicationdiet.class);
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
                Intent intent = new Intent(cabout.this, cschedules.class);
                intent.putExtra("PET_DATA", petData);
                intent.putExtra("VET_DATA", vetData);
                intent.putExtra("PETOWNER_DATA", ownerData);
                startActivity(intent);
                finish();
            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(cabout.this, editclinicabout.class);
                intent.putExtra("PET_DATA", petData);
                intent.putExtra("VET_DATA", vetData);
                intent.putExtra("PETOWNER_DATA", ownerData);
                startActivityForResult(intent, 1001);
            }
        });

        //Navigation Handle
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
                Intent intent = new Intent(cabout.this, clinicpets.class);
                intent.putExtra("USER_DATA", vetData);
                startActivity(intent);
                finish();
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(cabout.this, chomedashboard.class);
                intent.putExtra("USER_DATA", vetData);
                startActivity(intent);
                finish();
            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(cabout.this, clinicprofilepage.class);
                intent.putExtra("USER_DATA", vetData);
                startActivity(intent);
                finish();
            }
        });

        files.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(cabout.this, consolidatedsummary.class);
                intent.putExtra("USER_DATA", vetData);
                intent.putExtra("IS_PET_OWNER", false);
                startActivity(intent);
                finish();
            }
        });

        calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(cabout.this, appointmentspage.class);
                intent.putExtra("USER_DATA", vetData);
                intent.putExtra("IS_PET_OWNER", false);
                startActivity(intent);
                finish();
            }
        });
    }
    private void populatePetDetails(pets pet){
     //   aboutTitle.setText("About "+ pet.getName());
        dateOfBirth.setText("Date of Birth: "+ pet.getBirthdate());
        age.setText("Age: "+pet.getAge());
        gender.setText("Sex: "+pet.getSex());
        breed.setText("Breed: "+pet.getBreed());
        height.setText("Height: "+pet.getHeight());
        weight.setText("Weight: "+pet.getWeight());
        petcolor.setText("Color: "+pet.getColor());
        markings.setText("Markings: "+pet.getMarkings());
    }

    private void populatePetOwnerDetails(petOwners owner){
        ownername.setText("Name: "+owner.getFullname());
        cellno.setText("Cell No.: "+owner.getContactNo());
        address.setText("Address: "+owner.getEmail());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1001 && resultCode == RESULT_OK && data != null) {
            // Retrieve updated pet data
            petData = data.getParcelableExtra("PET_DATA");
            ownerData = data.getParcelableExtra("OWNER_DATA");
            vetData = data.getParcelableExtra("VET_DATA");
            refreshUI();
        }
    }

    private void refreshUI() {
        populatePetDetails(petData);
        populatePetOwnerDetails(ownerData);
    }
}