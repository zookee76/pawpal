package com.mobdeve.pawpal.ClinicOwner;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mobdeve.pawpal.Adapter.vaxAdapter;
import com.mobdeve.pawpal.Model.clinicVet;
import com.mobdeve.pawpal.Model.vaccination;
import com.mobdeve.pawpal.R;
import com.mobdeve.pawpal.Database.DBHelper;
import com.mobdeve.pawpal.Model.appointment;
import com.mobdeve.pawpal.Shared.consolidatedsummary;
import com.mobdeve.pawpal.Shared.appointmentspage;

import java.util.ArrayList;
import java.util.List;

public class vaccinationpage extends AppCompatActivity {
    private RecyclerView rvVax;
    private vaxAdapter vaxAdapter;
    private List<vaccination> vaccinationList;
    private DBHelper DB;
    private clinicVet vet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_vaccinationpage);

        // Initialize the database helper
        DB = new DBHelper(getApplicationContext());

        // Get the current vet data from the intent
        vet = getIntent().getParcelableExtra("USER_DATA");

        rvVax = findViewById(R.id.rv_vaccinations);
        rvVax.setLayoutManager(new LinearLayoutManager(this));

        // Initialize the vaccination list
        vaccinationList = new ArrayList<>();

        // Load vaccinations (appointments with appType "Vaccination")
        loadVaccinations();

        // Set up the adapter
        vaxAdapter = new vaxAdapter(this, vaccinationList);
        rvVax.setAdapter(vaxAdapter);

        // Back Handle
        ImageView backImg = findViewById(R.id.iv_back);
        TextView backTxt = findViewById(R.id.tv_back);
        View.OnClickListener backListnr = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(vaccinationpage.this, chomedashboard.class);
                vaccinationpage.this.startActivity(intent);
                finish();
            }
        };
        backImg.setOnClickListener(backListnr);
        backTxt.setOnClickListener(backListnr);

        // Navigation Handle
        ImageView home, calendar, pets, files, profile;
        home = findViewById(R.id.iv_home);
        calendar = findViewById(R.id.iv_calendar);
        pets = findViewById(R.id.iv_pets);
        files = findViewById((R.id.iv_files));
        profile = findViewById(R.id.iv_userprofile);

        // Link to navigation buttons
        pets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(vaccinationpage.this, clinicpets.class);
                startActivity(intent);
                finish();
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(vaccinationpage.this, chomedashboard.class);
                startActivity(intent);
                finish();
            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(vaccinationpage.this, clinicprofilepage.class);
                startActivity(intent);
                finish();
            }
        });

        files.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(vaccinationpage.this, consolidatedsummary.class);
                intent.putExtra("IS_PET_OWNER", false);
                startActivity(intent);
                finish();
            }
        });

        calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(vaccinationpage.this, appointmentspage.class);
                intent.putExtra("IS_PET_OWNER", false);
                startActivity(intent);
                finish();
            }
        });

    }

    private void loadVaccinations() {
            if (DB != null) {
                List<appointment> appointments = DB.getAllAppointments();
                for (appointment app : appointments) {
                    if ("Vaccination".equals(app.getAppType())) {
                        vaccination vax = new vaccination(
                                "Vaccine",
                                app.getPetName(),
                                app.getOwnerName(),
                                app.getAppDateTime(),
                                app.getAppVet(),
                                app.getAppStatus(),
                                0,
                                app.getPetID(),
                                app.getOwnerID(),
                                app.getVetID()
                        );
                        vaccinationList.add(vax);
                    }
                }
            } else {
            }
    }
}
