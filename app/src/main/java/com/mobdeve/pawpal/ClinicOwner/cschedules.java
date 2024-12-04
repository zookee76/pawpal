package com.mobdeve.pawpal.ClinicOwner;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.mobdeve.pawpal.Adapter.scheduleAdapter;
import com.mobdeve.pawpal.Database.DBHelper;
import com.mobdeve.pawpal.Model.appointment;
import com.mobdeve.pawpal.Model.clinicVet;
import com.mobdeve.pawpal.Model.petOwners;
import com.mobdeve.pawpal.Model.pets;
import com.mobdeve.pawpal.R;
import com.mobdeve.pawpal.Shared.appointmentspage;
import com.mobdeve.pawpal.Shared.consolidatedsummary;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class cschedules extends AppCompatActivity {
    private DBHelper DB;
    private clinicVet vetData;
    private pets petData;
    private petOwners petOwnerData;
    private RecyclerView rvApp;
    private scheduleAdapter adapter;
    private List<appointment> appointmentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_clinicpetprofileschedule);

        // Get Data
        DB = new DBHelper(getApplicationContext());
        Intent intent = getIntent();
        vetData = intent.getParcelableExtra("VET_DATA");
        petData = intent.getParcelableExtra("PET_DATA");
        petOwnerData = intent.getParcelableExtra("PETOWNER_DATA");

        long petID = petData.getID();
        petOwnerData = DB.getPetOwner(petID);

        // ELEMENTS
        TextView petName = findViewById(R.id.petName);
        ImageView petImage = findViewById(R.id.petImage);
        petName = findViewById(R.id.petName);
        petName.setText(petData.getName());

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

        rvApp = findViewById(R.id.rv_appointments);
        rvApp.setLayoutManager(new LinearLayoutManager(this));

        appointmentList = new ArrayList<>();
        loadallAppointments();

        adapter = new scheduleAdapter(this, appointmentList, DB);
        rvApp.setAdapter(adapter);

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
                Intent intent = new Intent(cschedules.this, cabout.class);
                intent.putExtra("PET_DATA", petData);
                intent.putExtra("VET_DATA", vetData);
                intent.putExtra("PETOWNER_DATA", petOwnerData);
                startActivity(intent);
                finish();
            }
        });

        meddocu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(cschedules.this, cmedicaldocs.class);
                intent.putExtra("PET_DATA", petData);
                intent.putExtra("VET_DATA", vetData);
                intent.putExtra("PETOWNER_DATA", petOwnerData);
                startActivity(intent);
                finish();
            }
        });

        meddiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(cschedules.this, cmedicationdiet.class);
                intent.putExtra("PET_DATA", petData);
                intent.putExtra("VET_DATA", vetData);
                intent.putExtra("PETOWNER_DATA", petOwnerData);
                startActivity(intent);
                finish();
            }
        });

        schedules.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(cschedules.this, cschedules.class);
                intent.putExtra("PET_DATA", petData);
                intent.putExtra("VET_DATA", vetData);
                intent.putExtra("PETOWNER_DATA", petOwnerData);
                startActivity(intent);
                finish();
            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(cschedules.this, editschedules.class);
                intent.putExtra("PET_DATA", petData);
                intent.putExtra("VET_DATA", vetData);
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
                Intent intent = new Intent(cschedules.this, clinicpets.class);
                intent.putExtra("USER_DATA", vetData);
                startActivity(intent);
                finish();
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(cschedules.this, chomedashboard.class);
                intent.putExtra("USER_DATA", vetData);
                startActivity(intent);
                finish();
            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(cschedules.this, clinicprofilepage.class);
                intent.putExtra("USER_DATA", vetData);
                startActivity(intent);
                finish();
            }
        });

        files.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(cschedules.this, consolidatedsummary.class);
                intent.putExtra("USER_DATA", vetData);
                intent.putExtra("IS_PET_OWNER", false);
                startActivity(intent);
                finish();
            }
        });

        calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(cschedules.this, appointmentspage.class);
                intent.putExtra("USER_DATA", vetData);
                intent.putExtra("IS_PET_OWNER", false);
                startActivity(intent);
                finish();
            }
        });

    }

    private void loadallAppointments(){
        appointmentList.clear();
        long petID = petData.getID();

        if (DB != null) {
            List<appointment> app = DB.getPetAppointment(petID);
            Log.d("APPSIZE", "Size: " + app.size() + " PETID: " + petID);

            if (app != null && !app.isEmpty()) {
                appointmentList.addAll(app);
            }
        } else {
            Log.e("APPSIZE", "appointment DB is not initialized.");
        }

        if(adapter != null){
            adapter.notifyDataSetChanged();

        }
    }
}