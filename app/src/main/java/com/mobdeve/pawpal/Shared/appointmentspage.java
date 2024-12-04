package com.mobdeve.pawpal.Shared;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mobdeve.pawpal.Adapter.appointmentAdapter;
import com.mobdeve.pawpal.Database.DBHelper;
import com.mobdeve.pawpal.Model.appointment;
import com.mobdeve.pawpal.Model.clinicVet;
import com.mobdeve.pawpal.Model.petOwners;
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
    private DBHelper DB;
    private petOwners owner;
    private clinicVet vet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clinicappointment);

        DB = new DBHelper(getApplicationContext());
        // Get Data
        Intent intent = getIntent();

        appointmentList = new ArrayList<>();

        // TEXT SEARCH
        SearchView search = findViewById(R.id.text_search);

        // SEARCH FUNCTION
        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                appAdapter.filter(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                appAdapter.filter(s);
                return false;
            }
        });

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
            owner = intent.getParcelableExtra("USER_DATA");
            loadappointments();

            appAdapter = new appointmentAdapter(this, appointmentList, DB, owner);
            rvApp.setAdapter(appAdapter);

            //back handle for pet owner
            View.OnClickListener backListnr = new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(appointmentspage.this, phomedashboard.class);
                    intent.putExtra("USER_DATA", owner);
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
                    intent.putExtra("USER_DATA", owner);
                    startActivity(intent);
                    finish();
                }
            });

            pets.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(appointmentspage.this, petspage.class);
                    intent.putExtra("IS_PET_OWNER", true);
                    intent.putExtra("USER_DATA", owner);
                    startActivity(intent);
                    finish();
                }
            });

            profile.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(appointmentspage.this, petprofilepage.class);
                    intent.putExtra("USER_DATA", owner);
                    startActivity(intent);
                    finish();
                }
            });

            files.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(appointmentspage.this, consolidatedsummary.class);
                    intent.putExtra("IS_PET_OWNER", true);
                    intent.putExtra("USER_DATA", owner);
                    startActivity(intent);
                    finish();
                }
            });

            calendar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(appointmentspage.this, appointmentspage.class);
                    startActivity(intent);
                    intent.putExtra("USER_DATA", owner);
                    finish();
                }
            });
        }
        else{
            vet = intent.getParcelableExtra("USER_DATA");
            loadclinicappointments();
            appAdapter = new appointmentAdapter(this, appointmentList, isPetOwner, DB, vet);
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
        long ownerID = owner.getID();

        if (DB != null) {
            appointmentList.clear();
            appointmentList = DB.getAppointmentByPetOwner(ownerID);
            Log.d("APPOINTMENTS SIZE", "Size: " + appointmentList.size());
        } else {
            Log.e("APPOINTMENTS SIZE", "petsDB is not initialized.");
        }
    }
    //clinic data
    private void loadclinicappointments(){
        long vetID = vet.getVetID();
        if(DB!=null){
            appointmentList.clear();
            appointmentList = DB.getAppointmentByVet(vetID);
            Log.d("APPOINTMENTS SIZE", "VETID: "+vetID+ "Size: " + appointmentList.size());
        }else{
            Log.e("APPOINTMENTS SIZE", "DB is not initialized.");
        }
    }
}