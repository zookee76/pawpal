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

import com.mobdeve.pawpal.Adapter.petOwnerAdapter;
import com.mobdeve.pawpal.Model.petOwners;
import com.mobdeve.pawpal.R;
import com.mobdeve.pawpal.Shared.appointmentspage;
import com.mobdeve.pawpal.Shared.consolidatedsummary;
import com.mobdeve.pawpal.Database.DBHelper;

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
    private DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_clinicmedrecords);

        db = new DBHelper(getApplicationContext());

        //Back Handle
        ImageView backImg = findViewById(R.id.iv_back);
        TextView backTxt = findViewById(R.id.tv_back);
        View.OnClickListener backListnr = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(medrecordspage.this, chomedashboard.class);
                medrecordspage.this.startActivity(intent);
                finish();
            }
        };
        backImg.setOnClickListener(backListnr);
        backTxt.setOnClickListener(backListnr);

        rvOwners = findViewById(R.id.rv_petownerss);
        rvOwners.setLayoutManager(new LinearLayoutManager(this));

        petOwnersList = new ArrayList<>();
        groupedOwners = new HashMap<>();
        firstletters = new ArrayList<>();

        petOwnersList = db.getPetOwners();

        Collections.sort(petOwnersList, new Comparator<petOwners>() {
            @Override
            public int compare(petOwners owner1, petOwners owner2) {
                String fname1 = owner1.getFname();
                String fname2 = owner2.getFname();

                if (fname1 == null && fname2 == null) {
                    return 0;
                }
                if (fname1 == null) {
                    return -1;
                }
                if (fname2 == null) {
                    return 1;
                }
                return fname1.compareToIgnoreCase(fname2);
            }
        });


        ownerAdapter = new petOwnerAdapter(this, petOwnersList, groupedOwners, firstletters);
        ownerAdapter.groupOwners();

        rvOwners.setAdapter(ownerAdapter);

        //Navigation Handle
        ImageView home, calendar, pets, files, profile;
        home = findViewById(R.id.iv_home);
        calendar = findViewById(R.id.iv_calendar);
        files = findViewById((R.id.iv_files));
        profile = findViewById(R.id.iv_userprofile);
        pets = findViewById(R.id.iv_pets);

        //Link to navigation buttons
        pets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(medrecordspage.this, clinicpets.class);
                startActivity(intent);
                finish();
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(medrecordspage.this, chomedashboard.class);
                startActivity(intent);
                finish();
            }
        });

        calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(medrecordspage.this, appointmentspage.class);
                intent.putExtra("IS_PET_OWNER", false);
                startActivity(intent);
                finish();
            }
        });

        files.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(medrecordspage.this, consolidatedsummary.class);
                intent.putExtra("IS_PET_OWNER", false);
                startActivity(intent);
                finish();
            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(medrecordspage.this, clinicprofilepage.class);
                startActivity(intent);
                finish();
            }
        });
    }
}