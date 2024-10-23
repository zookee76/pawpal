package com.example.pawpal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

        rvApp = findViewById(R.id.rv_appointments);
        rvApp.setLayoutManager(new LinearLayoutManager(this));

        //Sample Data
        appointmentList = new ArrayList<>();
        appointmentList.add(new appointment("0001", "Casper", "Ash Corpuz",
                "General Checkup", "October 1, 2024 9AM", "Dr. Vet", "Completed"));
        appointmentList.add(new appointment("0002", "Tyler", "Nicole Corpuz",
                "Vaccination", "October 2, 2024 10AM", "Dr. Whiskers", "Scheduled"));

        appAdapter = new appointmentAdapter(this,appointmentList);
        rvApp.setAdapter(appAdapter);

        //Back Handle
        ImageView backImg = findViewById(R.id.iv_back);
        TextView backTxt = findViewById(R.id.tv_back);

        View.OnClickListener backListnr = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        };

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
                Intent intent = new Intent(appointmentspage.this, chomedashboard.class);
                startActivity(intent);
            }
        });

        pets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // add here
            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // add here
            }
        });

        files.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(appointmentspage.this, consolidatedsummary.class);
                startActivity(intent);
            }
        });

        calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(appointmentspage.this, appointmentspage.class);
                startActivity(intent);
            }
        });

        // Add links to remaining navigation pages (calendar, files)
    }
}