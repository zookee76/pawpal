package com.mobdeve.pawpal.PetOwner;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.utils.widget.ImageFilterView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.mobdeve.pawpal.Adapter.dietmedAdapter;
import com.mobdeve.pawpal.Database.DBHelper;
import com.mobdeve.pawpal.Model.appointment;
import com.mobdeve.pawpal.Model.dietmed;
import com.mobdeve.pawpal.Model.petOwners;
import com.mobdeve.pawpal.Model.pets;
import com.mobdeve.pawpal.Model.vaccination;
import com.mobdeve.pawpal.R;
import com.mobdeve.pawpal.Shared.appointmentspage;
import com.mobdeve.pawpal.Shared.consolidatedsummary;
import com.mobdeve.pawpal.Shared.petspage;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class phomedashboard extends AppCompatActivity {

    private RecyclerView rvDietMedList;
    private dietmedAdapter adapter;
    private List<dietmed> dietmedList;
    private DBHelper DB;
    private List<pets> petsList;
    private petOwners user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_phomedashboard);

        // Get Data
        DB = new DBHelper(getApplicationContext());
        Intent intent = getIntent();
        user = intent.getParcelableExtra("USER_DATA");
        long ownerID = user.getID();
        dietmedList = new ArrayList<>();
        if(user != null){
            String username = user.getUsername();
            TextView tvName = findViewById(R.id.tv_name);
            tvName.setText("Hello, " + username);
        }

        // ELEMENTS
        ImageView profilepic = findViewById(R.id.iv_profile);
        long profileImageID = user.getImageID();
        if(profileImageID >0){
            String imagePath = DB.getImagePath(profileImageID);
            if(imagePath != null){
                File imgFile = new File(imagePath);
                if(imgFile.exists()){
                    Glide.with(this)
                            .load(imgFile)
                            .into(profilepic);
                }
            }
        }else{
            profilepic.setImageResource(R.drawable.fiimage);
        }

        // multipet dashboard
        // will change pa should handle the logic for displaying pets based on the number of pets owned
        ImageFilterView center, left, right;
        center = findViewById(R.id.img_center);
        left = findViewById(R.id.img_left);
        right = findViewById(R.id.img_right);

        View.OnClickListener petListner = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(phomedashboard.this, pabout.class);
                // Handle passing of specific pet detail
                startActivity(intent);
            }
        };

        center.setOnClickListener(petListner);
        right.setOnClickListener(petListner);
        left.setOnClickListener(petListner);

        TextView meddiet = findViewById(R.id.tv_alldiet);
        TextView schedules = findViewById(R.id.tv_allapp);
        ImageView petImg = findViewById(R.id.img_center);

        petImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(phomedashboard.this, pabout.class);
                startActivity(intent);
            }
        });

        meddiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(phomedashboard.this, pmediet.class);
                startActivity(intent);
            }
        });

        schedules.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(phomedashboard.this, pschedules.class);
                startActivity(intent);
            }
        });

        // Appointment Card View
        TextView appType, appTypeDetail, vetDoctor, note, message;
                appType = findViewById(R.id.tv_apptype);
                appTypeDetail = findViewById(R.id.tv_apptypedetail);
                vetDoctor = findViewById(R.id.tv_doctor);
                note = findViewById(R.id.tv_note);
                message = findViewById(R.id.tv_message);

        CardView appdeets = findViewById(R.id.cv_vaccdeets);
        if(user != null){
            petsList = DB.getPetsByOwner(ownerID);

            appointment latest = DB.getLatestAppByPetOwner(ownerID);
            message.setVisibility(View.GONE);
            if (latest != null) {
                String appointmentType = latest.getAppType();
                String doctor = latest.getAppVet();
                String datetime = latest.getAppDateTime();

                long appID = latest.getAppNo();
                appType.setText(appointmentType);
                vetDoctor.setText("Dr. " + doctor);
                note.setText("Date and Time: "+datetime);
                if(appointmentType.equals("Vaccination")){
                    vaccination vax = DB.getSpecificVaccination(appID);
                    Log.d("VACCINATION", "VAX ID: " + vax.getVaxID());
                    appTypeDetail.setText(vax.getVaccType());
                }else{

                }
            }else{
                message.setVisibility(View.VISIBLE);
                appdeets.setVisibility(View.GONE);
            }
        }else{
            message.setVisibility(View.VISIBLE);
        }
        // RecyclerView Handle

        rvDietMedList = findViewById(R.id.rv_medlist);
        rvDietMedList.setLayoutManager(new LinearLayoutManager(this));

        loadDietMedications();

        adapter = new dietmedAdapter(this, dietmedList, DB);
        rvDietMedList.setAdapter(adapter);

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
                Intent intent = new Intent(phomedashboard.this, phomedashboard.class);
                intent.putExtra("USER_DATA", user);
                startActivity(intent);
                //finish();
            }
        });

        pets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(phomedashboard.this, petspage.class);
                intent.putExtra("IS_PET_OWNER", true);
                intent.putExtra("USER_DATA", user);
                startActivity(intent);
                //finish();
            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(phomedashboard.this, petprofilepage.class);
                intent.putExtra("USER_DATA", user);
                startActivity(intent);
                //finish();
            }
        });

        files.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(phomedashboard.this, consolidatedsummary.class);
                intent.putExtra("IS_PET_OWNER", true);
                intent.putExtra("USER_DATA", user);
                startActivity(intent);
                //finish();
            }
        });

        calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(phomedashboard.this, appointmentspage.class);
                intent.putExtra("IS_PET_OWNER", true);
                intent.putExtra("USER_DATA", user);
                startActivity(intent);
                //finish();
            }
        });
    }

    private void loadDietMedications(){
        long userID = user.getID();

        if (DB != null) {
            dietmedList.clear();
            dietmedList = DB.getDietmedByPetOwner(userID);
        }else {
            Log.e("INSTRUCTIONS SIZE", "DB is not initialized.");
        }
    }
}