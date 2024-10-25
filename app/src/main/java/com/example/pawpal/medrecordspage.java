package com.example.pawpal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class medrecordspage extends AppCompatActivity {
    private RecyclerView rvOwners;
    private petOwnerAdapter ownerAdapter;
    private List<petOwners> petOwnersList;
    private Map<String, List<petOwners>> groupedOwners;
    private List<String> firstletters;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_clinicmedrecords);

        //Back Handle
        ImageView backImg = findViewById(R.id.iv_back);
        TextView backTxt = findViewById(R.id.tv_back);
        View.OnClickListener backListnr = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(medrecordspage.this, chomedashboard.class);
                medrecordspage.this.startActivity(intent);
            }
        };
        backImg.setOnClickListener(backListnr);
        backTxt.setOnClickListener(backListnr);

        rvOwners = findViewById(R.id.rv_owners);
        rvOwners.setLayoutManager(new LinearLayoutManager(this));

        petOwnersList = new ArrayList<>();
        groupedOwners = new HashMap<>();
        firstletters = new ArrayList<>();

        //Sample Data
        petOwnersList = new ArrayList<>();
        petOwnersList.add(new petOwners("Ashley Corpuz"));
        petOwnersList.add(new petOwners("Herise Visto"));
        petOwnersList.add(new petOwners("Nicole "));
        petOwnersList.add(new petOwners("Janah"));
        petOwnersList.add(new petOwners("Javi Del Rosario"));
        petOwnersList.add(new petOwners("Harry"));
        petOwnersList.add(new petOwners("Niall"));
        petOwnersList.add(new petOwners("Zayn"));
        petOwnersList.add(new petOwners("Louis"));
        petOwnersList.add(new petOwners("Liam"));

        Collections.sort(petOwnersList, new Comparator<petOwners>() {
            @Override
            public int compare(petOwners owner1, petOwners owner2) {
                return owner1.getName().compareToIgnoreCase(owner2.getName());
            }
        });

        ownerAdapter = new petOwnerAdapter(this, petOwnersList, groupedOwners, firstletters);
        ownerAdapter.groupOwners();

        rvOwners.setAdapter(ownerAdapter);

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
                Intent intent = new Intent(medrecordspage.this, chomedashboard.class);
                startActivity(intent);
            }
        });

        calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(medrecordspage.this, appointmentspage.class);
                intent.putExtra("IS_PET_OWNER", false);
                startActivity(intent);
            }
        });

        files.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(medrecordspage.this, consolidatedsummary.class);
                intent.putExtra("IS_PET_OWNER", false);
                startActivity(intent);
            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // add here
            }
        });
    }
}