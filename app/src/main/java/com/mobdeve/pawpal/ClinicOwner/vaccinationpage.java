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
import com.mobdeve.pawpal.Model.vaccination;
import com.mobdeve.pawpal.R;
import com.mobdeve.pawpal.Shared.appointmentspage;
import com.mobdeve.pawpal.Shared.consolidatedsummary;

import java.util.ArrayList;
import java.util.List;

public class vaccinationpage extends AppCompatActivity {
    private RecyclerView rvVax;
    private com.mobdeve.pawpal.Adapter.vaxAdapter vaxAdapter;
    private List<vaccination> vaccinationList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_vaccinationpage);

        rvVax = findViewById(R.id.rv_vaccinations);
        rvVax.setLayoutManager(new LinearLayoutManager(this));

        //Sample Data
        vaccinationList = new ArrayList<>();
        vaccinationList.add(new vaccination("Heatworm Vaccination", "Casper", "Ash Corpuz",
                        "October 28, 2024", "Dr. Abcdef Ghijklmnop", "Upcoming"));

        vaxAdapter = new vaxAdapter(this, vaccinationList);
        rvVax.setAdapter(vaxAdapter);

        //Back Handle
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

        //Navigation Handle
        ImageView home, calendar, pets, files, profile;
        home = findViewById(R.id.iv_home);
        calendar = findViewById(R.id.iv_calendar);
        pets = findViewById(R.id.iv_pets);
        files = findViewById((R.id.iv_files));
        profile = findViewById(R.id.iv_userprofile);

        //Link to navigation buttons
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

        pets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(vaccinationpage.this, clinicpets.class);
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
}