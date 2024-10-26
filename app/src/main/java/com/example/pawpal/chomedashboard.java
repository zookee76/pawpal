package com.example.pawpal;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

public class chomedashboard extends AppCompatActivity {

    private RecyclerView rv_overviewentries;
    private overviewentryadapter adapter;
    private List<overviewentry> overviewentryList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_clinicdashboard);

        rv_overviewentries = findViewById(R.id.rv_overviewentries);
        rv_overviewentries.setLayoutManager(new LinearLayoutManager(this));

        overviewentryList = new ArrayList<>();
        loadOverviewEntries();

        adapter = new overviewentryadapter(this, overviewentryList);
        rv_overviewentries.setAdapter(adapter);

        FrameLayout rectangle1 = findViewById(R.id.rectangle1); // petownermanagement
        FrameLayout rectangle2 = findViewById(R.id.rectangle2); // appmanager
        FrameLayout rectangle3 = findViewById(R.id.rectangle3); // vacc sched
        FrameLayout rectangle4 = findViewById(R.id.rectangle4); // medrecords
        FrameLayout rectangle5 = findViewById(R.id.rectangle5); // certreq

        rectangle1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(chomedashboard.this, medrecordspage.class);
                startActivity(intent);
            }
        });

        rectangle2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(chomedashboard.this, appointmentspage.class);
                startActivity(intent);
            }
        });

        rectangle3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(chomedashboard.this, vaccinationpage.class);
                startActivity(intent);
            }
        });

        rectangle4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(chomedashboard.this, consolidatedsummary.class);
                startActivity(intent);
            }
        });

        rectangle5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(chomedashboard.this, certreq.class);
                startActivity(intent);
            }
        });

        //Navigation Handle
        ImageView home, calendar, files, profile, pets;
        home = findViewById(R.id.iv_home);
        calendar = findViewById(R.id.iv_calendar);
        files = findViewById((R.id.iv_files));
        profile = findViewById(R.id.iv_userprofile);
        pets = findViewById(R.id.iv_pets);

        //Link to navigation buttons
        pets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(chomedashboard.this, clinicpets.class);
                startActivity(intent);
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(chomedashboard.this, chomedashboard.class);
                startActivity(intent);
            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(chomedashboard.this, clinicprofilepage.class);
                startActivity(intent);
            }
        });

        files.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(chomedashboard.this, consolidatedsummary.class);
                intent.putExtra("IS_PET_OWNER", false);
                startActivity(intent);
            }
        });

        calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(chomedashboard.this, appointmentspage.class);
                intent.putExtra("IS_PET_OWNER", false);
                startActivity(intent);
            }
        });
    }

    private void loadOverviewEntries() {
        overviewentryList.add(new overviewentry(R.drawable.cat2, "9:00 AM", "Casper", "Ashley Corpuz", "General Check-Up", "Dr. QuackQuack"));
        overviewentryList.add(new overviewentry(R.drawable.cat2, "9:00 AM", "Casper", "Ashley Corpuz", "General Check-Up", "Dr. QuackQuack"));
    }
}
