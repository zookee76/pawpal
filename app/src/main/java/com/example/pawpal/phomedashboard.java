package com.example.pawpal;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class phomedashboard extends AppCompatActivity {

    private RecyclerView rvDietMedList;
    private dietmedAdapter adapter;
    private List<dietmed> dietmedList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_phomedashboard);

        // RecyclerView Handle
        rvDietMedList = findViewById(R.id.rv_medlist);
        rvDietMedList.setLayoutManager(new LinearLayoutManager(this));

        dietmedList = new ArrayList<>();
        loadDietMedications();

        adapter = new dietmedAdapter(this, dietmedList);
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
                startActivity(intent);
            }
        });

        pets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(phomedashboard.this, petspage.class);
                startActivity(intent);
            }
        });

        // Add links to remaining navigation pages (calendar, files, profile)
    }

    private void loadDietMedications(){
        dietmedList.add(new dietmed(R.drawable.fiimage, "Aspirin", "10-09-2024, 08:00 AM"));
        dietmedList.add(new dietmed(R.drawable.fiimage, "Antibiotic", "11-09-2024, 10:00 AM"));
    }
}