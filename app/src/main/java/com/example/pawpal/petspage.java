package com.example.pawpal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class petspage extends AppCompatActivity {

    private RecyclerView rvPets;
    private petAdapter adapter;
    private List<pets> petsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_mypets);

        //Back Handle
        ImageView backImg = findViewById(R.id.iv_back);
        TextView backTxt = findViewById(R.id.tv_back);

        View.OnClickListener backListnr = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(petspage.this, phomedashboard.class);
                startActivity(intent);
            }
        };

        backImg.setOnClickListener(backListnr);
        backTxt.setOnClickListener(backListnr);

        // RecyclerView Handle

        rvPets = findViewById(R.id.rv_pets);
        rvPets.setLayoutManager(new LinearLayoutManager(this));

        petsList = new ArrayList<>();
        loadPets();

        adapter = new petAdapter(this, petsList);
        rvPets.setAdapter(adapter);

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
                Intent intent = new Intent(petspage.this, phomedashboard.class);
                startActivity(intent);
            }
        });

        pets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(petspage.this, petspage.class);
                startActivity(intent);
            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(petspage.this, userprofilepage.class);
                startActivity(intent);
            }
        });

        // Add links to remaining navigation pages (calendar, files)

    }

    private void loadPets(){
        // sample data for pets
        petsList.add(new pets("Callie", "Domestic Short Hair", "Female", 4, R.drawable.cat2));
        petsList.add(new pets("Casper", "Domestic Short Hair", "Male", 3, R.drawable.cat2));
        petsList.add(new pets("Tyler", "Persian Cat", "Male", 3, R.drawable.cat2));
    }
}
