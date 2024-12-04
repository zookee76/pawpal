package com.mobdeve.pawpal.Shared;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mobdeve.pawpal.Adapter.appointmentAdapter;
import com.mobdeve.pawpal.Model.appointment;
import com.mobdeve.pawpal.PetOwner.petprofilepage;
import com.mobdeve.pawpal.R;
import com.mobdeve.pawpal.ClinicOwner.chomedashboard;
import com.mobdeve.pawpal.ClinicOwner.clinicpets;
import com.mobdeve.pawpal.ClinicOwner.clinicprofilepage;
import com.mobdeve.pawpal.PetOwner.phomedashboard;

import java.util.ArrayList;
import java.util.List;

public class appointmentspage extends AppCompatActivity {

    private RecyclerView  rvApp;
    private appointmentAdapter appAdapter;
    private List<appointment> appointmentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clinicappointment);

        //Back Handle
        ImageView backImg = findViewById(R.id.iv_back);
        TextView backTxt = findViewById(R.id.tv_back);

        //Navigation Handle
        ImageView home, calendar, pets, files, profile;
        home = findViewById(R.id.iv_home);
        calendar = findViewById(R.id.iv_calendar);
        pets = findViewById(R.id.iv_pets);
        files = findViewById((R.id.iv_files));
        profile = findViewById(R.id.iv_userprofile);

        // Recycler view
        boolean isPetOwner = getIntent().getBooleanExtra("IS_PET_OWNER", false);
        rvApp = findViewById(R.id.rv_appointments);
        rvApp.setLayoutManager(new LinearLayoutManager(this));
        appointmentList = new ArrayList<>();

        if(isPetOwner){
            loadappointments();

            appAdapter = new appointmentAdapter(this,appointmentList);
            rvApp.setAdapter(appAdapter);

            //back handle for pet owner
            View.OnClickListener backListnr = new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(appointmentspage.this, phomedashboard.class);
                    startActivity(intent);
                    finish();
                }
            };
            backImg.setOnClickListener(backListnr);
            backTxt.setOnClickListener(backListnr);

            //Link to navigation buttons
            home.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(appointmentspage.this, phomedashboard.class);

                    startActivity(intent);
                    finish();
                }
            });

            pets.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(appointmentspage.this, petspage.class);
                    intent.putExtra("IS_PET_OWNER", true);
                    startActivity(intent);
                    finish();
                }
            });

            profile.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(appointmentspage.this, petprofilepage.class);
                    startActivity(intent);
                    finish();
                }
            });

            files.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(appointmentspage.this, consolidatedsummary.class);
                    intent.putExtra("IS_PET_OWNER", true);
                    startActivity(intent);
                    finish();
                }
            });

            calendar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(appointmentspage.this, appointmentspage.class);
                    startActivity(intent);
                    finish();
                }
            });
        }
        else{

            FloatingActionButton addPets = findViewById(R.id.btn_addappointments);
            addPets.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(appointmentspage.this, com.mobdeve.pawpal.ClinicOwner.addRecords.class);
                    startActivityForResult(intent, 1);
                    //startActivity(intent);
                }
            });

            loadclinicappointments();
            appAdapter = new appointmentAdapter(this, appointmentList, isPetOwner);
            rvApp.setAdapter(appAdapter);

            // back handle
            View.OnClickListener backListnr = new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(appointmentspage.this, chomedashboard.class);
                    startActivity(intent);
                    finish();
                }
            };
            backImg.setOnClickListener(backListnr);
            backTxt.setOnClickListener(backListnr);


            //Link to navigation buttons
            pets.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(appointmentspage.this, clinicpets.class);
                    startActivity(intent);
                    finish();
                }
            });

            home.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(appointmentspage.this, chomedashboard.class);
                    startActivity(intent);
                    finish();
                }
            });

            profile.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(appointmentspage.this, clinicprofilepage.class);
                    startActivity(intent);
                    finish();
                }
            });

            files.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(appointmentspage.this, consolidatedsummary.class);
                    intent.putExtra("IS_PET_OWNER", false);
                    startActivity(intent);
                    finish();
                }
            });

            calendar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(appointmentspage.this, appointmentspage.class);
                    intent.putExtra("IS_PET_OWNER", false);
                    startActivity(intent);
                    finish();
                }
            });
        }
    }

    //petowner data
    private void loadappointments(){
        //appointmentList.add(new appointment("001", "Casper", "Ashley Corpuz", "General Checkup", "October 1, 2024. 9AM", "Dr. Vet", "Completed"));
    }
    //clinic data
    private void loadclinicappointments(){
        //appointmentList.add(new appointment("001", "Casper", "Ashley Corpuz", "General Checkup", "October 1, 2024. 9AM", "Dr. Vet", "Completed"));
        //appointmentList.add(new appointment("002", "Casper", "Ashley Corpuz", "General Checkup", "October 1, 2024. 9AM", "Dr. Vet", "Scheduled"));
    }
}