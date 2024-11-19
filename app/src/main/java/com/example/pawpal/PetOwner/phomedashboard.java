package com.example.pawpal.PetOwner;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.utils.widget.ImageFilterView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pawpal.Adapter.dietmedAdapter;
import com.example.pawpal.Model.dietmed;
import com.example.pawpal.R;
import com.example.pawpal.Shared.appointmentspage;
import com.example.pawpal.Shared.consolidatedsummary;
import com.example.pawpal.Shared.petspage;
import com.example.pawpal.Shared.petprofilepage;

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

        // multipet dashboard
        // will change pa should handle the logic for displaying pets based on the number of pets owned
        ImageFilterView center, left, right;
        center = findViewById(R.id.img_center);
        left = findViewById(R.id.img_left);
        right = findViewById(R.id.img_right);

        View.OnClickListener petListner = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(phomedashboard.this, pabout.class);
                // Handle passing of specific pet detail
                startActivity(intent);
            }
        };

        center.setOnClickListener(petListner);
        right.setOnClickListener(petListner);
        left.setOnClickListener(petListner);

        TextView meddiet = findViewById(R.id.tv_alldiet);
        TextView schedules = findViewById(R.id.tv_allapp);
        ImageView petImg = findViewById(R.id.img_center);

        petImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(phomedashboard.this, pabout.class);
                startActivity(intent);
            }
        });

        meddiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(phomedashboard.this, pmediet.class);
                startActivity(intent);
            }
        });

        schedules.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(phomedashboard.this, pschedules.class);
                startActivity(intent);
            }
        });

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
                finish();
            }
        });

        pets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(phomedashboard.this, petspage.class);
                intent.putExtra("IS_PET_OWNER", true);
                startActivity(intent);
                finish();
            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(phomedashboard.this, petprofilepage.class);
                startActivity(intent);
                finish();
            }
        });

        files.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(phomedashboard.this, consolidatedsummary.class);
                intent.putExtra("IS_PET_OWNER", true);
                startActivity(intent);
                finish();
            }
        });

        calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(phomedashboard.this, appointmentspage.class);
                intent.putExtra("IS_PET_OWNER", true);
                startActivity(intent);
                finish();
            }
        });
    }

    private void loadDietMedications(){
        dietmedList.add(new dietmed(R.drawable.fiimage, "Aspirin", "10-09-2024, 08:00 AM"));
        dietmedList.add(new dietmed(R.drawable.fiimage, "Antibiotic", "11-09-2024, 10:00 AM"));
    }
}