package com.example.pawpal.ClinicOwner;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pawpal.Adapter.clinicpetsAdapter;
import com.example.pawpal.Model.pets;
import com.example.pawpal.R;
import com.example.pawpal.Shared.appointmentspage;
import com.example.pawpal.Shared.consolidatedsummary;

import java.util.ArrayList;
import java.util.List;

public class clinicpets extends AppCompatActivity {
    private RecyclerView rvClinicPets;
    private clinicpetsAdapter adapter;
    private List<pets> clinicpetsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_clinicpets);

        // back handle
        ImageView backImg = findViewById(R.id.iv_back);
        TextView backTxt = findViewById(R.id.tv_back);
        View.OnClickListener backListnr = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(clinicpets.this, chomedashboard.class);
                startActivity(intent);
                finish();
            }
        };

        backImg.setOnClickListener(backListnr);
        backTxt.setOnClickListener(backListnr);

        //Navigation Handle
        ImageView home, calendar, files, profile, pets;
        home = findViewById(R.id.iv_home);
        calendar = findViewById(R.id.iv_calendar);
        files = findViewById((R.id.iv_files));
        profile = findViewById(R.id.iv_userprofile);
        pets = findViewById(R.id.iv_pets);

        //Link to navigation buttons
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(clinicpets.this, chomedashboard.class);
                startActivity(intent);
                finish();
            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(clinicpets.this, clinicprofilepage.class);
                startActivity(intent);
                finish();
            }
        });

        files.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(clinicpets.this, consolidatedsummary.class);
                intent.putExtra("IS_PET_OWNER", false);
                startActivity(intent);
                finish();
            }
        });

        calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(clinicpets.this, appointmentspage.class);
                intent.putExtra("IS_PET_OWNER", false);
                startActivity(intent);
                finish();
            }
        });

        pets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(clinicpets.this, clinicpets.class);
                startActivity(intent);
                finish();
            }
        });

        rvClinicPets = findViewById(R.id.rv_clinicpets);
        clinicpetsList = new ArrayList<>();
        adapter = new clinicpetsAdapter(clinicpetsList, this);

        // grid layout
        int numOfCol = calculateNoOfCols(this, 150);
        rvClinicPets.setLayoutManager(new GridLayoutManager(this, numOfCol));
        rvClinicPets.setAdapter(adapter);

        loadAllPets();
    }

    private int calculateNoOfCols(Context cxt, float colWidthDp){
        float columndWidth = colWidthDp * cxt.getResources().getDisplayMetrics().density;
        float screenWidthdp = getResources().getDisplayMetrics().widthPixels;
        return Math.max(1, (int) (screenWidthdp/columndWidth));
    }

    private void loadAllPets(){
        //Sample Data
        clinicpetsList.add(new pets("Callie", "Domestic Short Hair", "Female", 4, R.drawable.callie));
        clinicpetsList.add(new pets("Casper", "Domestic Short Hair", "Male", 3, R.drawable.casper));
        clinicpetsList.add(new pets("Tyler", "Persian Cat", "Male", 3, R.drawable.tyler));
        clinicpetsList.add(new pets("Callie", "Domestic Short Hair", "Female", 4, R.drawable.callie));
        clinicpetsList.add(new pets("Casper", "Domestic Short Hair", "Male", 3, R.drawable.casper));
        clinicpetsList.add(new pets("Tyler", "Persian Cat", "Male", 3, R.drawable.tyler));

        adapter.notifyDataSetChanged();
    }
}