package com.mobdeve.pawpal.ClinicOwner;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.mobdeve.pawpal.Database.DBHelper;
import com.mobdeve.pawpal.Model.clinicVet;
import com.mobdeve.pawpal.Model.petOwners;
import com.mobdeve.pawpal.Model.pets;
import com.mobdeve.pawpal.R;
import com.mobdeve.pawpal.Shared.appointmentspage;
import com.mobdeve.pawpal.Shared.consolidatedsummary;

public class cabout extends AppCompatActivity {

    private DBHelper DB;
    private clinicVet vetData;
    private pets petData;
    private petOwners petOwnerData;
    private TextView petProfile, petName, aboutTitle, aboutDesc,dateOfBirth,age,gender,breed,height,weight,petcolor,markings,ownername,cellno,address;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clinicpetprofileabout);

        // Get Data
        Intent intent = getIntent();
        vetData = intent.getParcelableExtra("VET_DATA");
        petData = intent.getParcelableExtra("PET_DATA");

        long petID = petData.getID();
        petOwnerData = DB.getPetOwner(petID);

        // ELEMENTS
        ImageView petImage = findViewById(R.id.petImage);
        aboutDesc = findViewById(R.id.aboutdescription);
        petName = findViewById(R.id.petName);
        petName.setText(petData.getName());

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
        aboutTitle = findViewById(R.id.aboutTitle2);

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
                intent.putExtra("USER_DATA", vetData);
                intent.putExtra("VET_DATA", petData);
                intent.putExtra("PETOWNER_DATA", petOwnerData);
                startActivity(intent);
                finish();
            }
        });

        meddocu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(cabout.this, cmedicaldocs.class);
                intent.putExtra("USER_DATA", vetData);
                intent.putExtra("VET_DATA", petData);
                intent.putExtra("PETOWNER_DATA", petOwnerData);
                startActivity(intent);
                finish();
            }
        });

        meddiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(cabout.this, cmedicationdiet.class);
                intent.putExtra("USER_DATA", vetData);
                intent.putExtra("VET_DATA", petData);
                intent.putExtra("PETOWNER_DATA", petOwnerData);
                startActivity(intent);
                finish();
            }
        });

        schedules.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(cabout.this, cschedules.class);
                intent.putExtra("USER_DATA", vetData);
                intent.putExtra("VET_DATA", petData);
                intent.putExtra("PETOWNER_DATA", petOwnerData);
                startActivity(intent);
                finish();
            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(cabout.this, editclinicabout.class);
                intent.putExtra("USER_DATA", vetData);
                intent.putExtra("VET_DATA", petData);
                intent.putExtra("PETOWNER_DATA", petOwnerData);
                startActivity(intent);
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
                startActivity(intent);
                finish();
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(cabout.this, chomedashboard.class);
                startActivity(intent);
                finish();
            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(cabout.this, clinicprofilepage.class);
                startActivity(intent);
                finish();
            }
        });

        files.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(cabout.this, consolidatedsummary.class);
                intent.putExtra("IS_PET_OWNER", false);
                startActivity(intent);
                finish();
            }
        });

        calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(cabout.this, appointmentspage.class);
                intent.putExtra("IS_PET_OWNER", false);
                startActivity(intent);
                finish();
            }
        });
    }
}