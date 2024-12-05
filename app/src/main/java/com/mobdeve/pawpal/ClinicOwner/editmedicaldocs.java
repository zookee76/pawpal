package com.mobdeve.pawpal.ClinicOwner;

import android.content.Intent;
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

public class editmedicaldocs extends AppCompatActivity {
    private DBHelper DB;
    private pets petData;
    private clinicVet vetData;
    private petOwners ownerData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliniceditmedical);
        // Get Data
        DB = new DBHelper(getApplicationContext());
        Intent intent = getIntent();
        petData = intent.getParcelableExtra("PET_DATA");
        vetData = intent.getParcelableExtra("VET_DATA");
        long petID = petData.getID();
        ownerData = DB.getPetOwner(petID);

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
                Intent intent = new Intent(editmedicaldocs.this, cabout.class);
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
                Intent intent = new Intent(editmedicaldocs.this, cmedicaldocs.class);
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
                Intent intent = new Intent(editmedicaldocs.this, cmedicationdiet.class);
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
                Intent intent = new Intent(editmedicaldocs.this, cschedules.class);
                intent.putExtra("PET_DATA", petData);
                intent.putExtra("VET_DATA", vetData);
                intent.putExtra("PETOWNER_DATA", ownerData);
                startActivity(intent);
                finish();
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
                Intent intent = new Intent(editmedicaldocs.this, clinicpets.class);
                intent.putExtra("USER_DATA", vetData);
                startActivity(intent);
                finish();
            }
        });
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(editmedicaldocs.this, chomedashboard.class);
                intent.putExtra("USER_DATA", vetData);
                startActivity(intent);
                finish();
            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(editmedicaldocs.this, clinicprofilepage.class);
                intent.putExtra("USER_DATA", vetData);
                startActivity(intent);
                finish();
            }
        });

        files.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(editmedicaldocs.this, consolidatedsummary.class);
                intent.putExtra("USER_DATA", vetData);
                intent.putExtra("IS_PET_OWNER", false);
                startActivity(intent);
                finish();
            }
        });

        calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(editmedicaldocs.this, appointmentspage.class);
                intent.putExtra("USER_DATA", vetData);
                intent.putExtra("IS_PET_OWNER", false);
                startActivity(intent);
                finish();
            }
        });

        // Logic to handle inputs
    }
}