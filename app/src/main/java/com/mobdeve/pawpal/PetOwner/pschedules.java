package com.mobdeve.pawpal.PetOwner;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.mobdeve.pawpal.Adapter.medietAdapter;
import com.mobdeve.pawpal.Adapter.scheduleAdapter;
import com.mobdeve.pawpal.Database.DBHelper;
import com.mobdeve.pawpal.Model.appointment;
import com.mobdeve.pawpal.Model.dietmed;
import com.mobdeve.pawpal.Model.petOwners;
import com.mobdeve.pawpal.Model.pets;
import com.mobdeve.pawpal.R;
import com.mobdeve.pawpal.Shared.appointmentspage;
import com.mobdeve.pawpal.Shared.consolidatedsummary;
import com.mobdeve.pawpal.Shared.petspage;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class pschedules extends AppCompatActivity
{
    private DBHelper DB;
    private TextView petName;
    private RecyclerView rvApp;
    private scheduleAdapter adapter;
    private List<appointment> appointmentList;
    private pets petData;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_petprofileschedule);

        DB = new DBHelper(getApplicationContext());
        Intent intent = getIntent();
        petData = intent.getParcelableExtra("PET_DATA");
        petOwners ownerData = intent.getParcelableExtra("OWNER_DATA");

        // ELEMENTS
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

        ImageView shareImg = findViewById(R.id.iv_share);

        View.OnClickListener backListnr = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*
                Intent intent = new Intent(pschedules.this, phomedashboard.class);
                startActivity(intent);

                 */
                finish();
            }
        };

        View.OnClickListener shareListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(pschedules.this, shareqr.class);
                startActivity(intent);
            }
        };

        shareImg.setOnClickListener(shareListener);

        backImg.setOnClickListener(backListnr);
        backTxt.setOnClickListener(backListnr);

        TextView meddiet = findViewById(R.id.medicationdietcategory);
        TextView about = findViewById(R.id.aboutcategory);
        TextView schedules = findViewById(R.id.schedulecategory);
        TextView meddocs = findViewById(R.id.medicaldocscategory);

        meddocs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(pschedules.this, pmedicaldocs.class);
                intent.putExtra("USER_DATA", ownerData);
                intent.putExtra("PET_DATA", petData);
                startActivity(intent);
                finish();
            }
        });

        meddiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(pschedules.this, pmediet.class);
                intent.putExtra("USER_DATA", ownerData);
                intent.putExtra("PET_DATA", petData);
                startActivity(intent);
                finish();
            }
        });

        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(pschedules.this, pabout.class);
                intent.putExtra("USER_DATA", ownerData);
                intent.putExtra("PET_DATA", petData);
                startActivity(intent);
                finish();
            }
        });

        schedules.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(pschedules.this, pschedules.class);
                intent.putExtra("USER_DATA", ownerData);
                intent.putExtra("PET_DATA", petData);
                startActivity(intent);
                finish();
            }
        });

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
                Intent intent = new Intent(pschedules.this, phomedashboard.class);
                intent.putExtra("USER_DATA", ownerData);

                startActivity(intent);
                finish();
            }
        });

        pets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(pschedules.this, petspage.class);
                intent.putExtra("USER_DATA", ownerData);
                intent.putExtra("IS_PET_OWNER", true);
                startActivity(intent);
                finish();
            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(pschedules.this, petprofilepage.class);
                intent.putExtra("USER_DATA", ownerData);
                startActivity(intent);
                finish();
            }
        });

        files.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(pschedules.this, consolidatedsummary.class);
                intent.putExtra("USER_DATA", ownerData);
                intent.putExtra("IS_PET_OWNER", true);
                startActivity(intent);
                finish();
            }
        });

        calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(pschedules.this, appointmentspage.class);
                intent.putExtra("USER_DATA", ownerData);
                intent.putExtra("IS_PET_OWNER", true);
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
