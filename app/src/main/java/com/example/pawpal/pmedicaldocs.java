package com.example.pawpal;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class pmedicaldocs extends AppCompatActivity {

    private RecyclerView rvMedDocs;
    private List<medicaldoc> medicaldocList;
    private medicaldocadapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_petprofilemedical);

        rvMedDocs = findViewById(R.id.rv_medicaldoccard);
        rvMedDocs.setLayoutManager(new LinearLayoutManager(this));

        medicaldocList = new ArrayList<>();
        loadMedicalDocs();

        adapter = new medicaldocadapter(this, medicaldocList);
        rvMedDocs.setAdapter(adapter);

        //Back Handle
        ImageView backImg = findViewById(R.id.iv_back);
        TextView backTxt = findViewById(R.id.tv_back);

        ImageView shareImg = findViewById(R.id.iv_share);

        View.OnClickListener backListnr = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(pmedicaldocs.this, phomedashboard.class);
                startActivity(intent);
            }
        };

        View.OnClickListener shareListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(pmedicaldocs.this, shareqr.class);
                startActivity(intent);
            }
        };

        backImg.setOnClickListener(backListnr);
        backTxt.setOnClickListener(backListnr);

        shareImg.setOnClickListener(shareListener);

        TextView meddiet = findViewById(R.id.medicationdietcategory);
        TextView about = findViewById(R.id.aboutcategory);
        TextView schedules = findViewById(R.id.schedulecategory);
        TextView meddocs = findViewById(R.id.medicaldocscategory);

        meddocs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(pmedicaldocs.this, pmedicaldocs.class);
                startActivity(intent);
            }
        });

        meddiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(pmedicaldocs.this, pmediet.class);
                startActivity(intent);
            }
        });

        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(pmedicaldocs.this, pabout.class);
                startActivity(intent);
            }
        });

        schedules.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(pmedicaldocs.this, pschedules.class);
                startActivity(intent);
            }
        });

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
                Intent intent = new Intent(pmedicaldocs.this, phomedashboard.class);
                startActivity(intent);
            }
        });

        pets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(pmedicaldocs.this, petspage.class);
                startActivity(intent);
            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(pmedicaldocs.this, userprofilepage.class);
                startActivity(intent);
            }
        });

        files.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(pmedicaldocs.this, pmedicaldocs.class);
                startActivity(intent);
            }
        });

        calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(pmedicaldocs.this, pschedules.class);
                startActivity(intent);
            }
        });
    }

    private void loadMedicalDocs(){
        medicaldocList.add(new medicaldoc("Title/Name of Document", "Type of Document", "19/10/2024", "Dr. QuackQuack", "casper_doc"));
        medicaldocList.add(new medicaldoc("Title/Name of Document", "Type of Document", "19/10/2024", "Dr. QuackQuack", "casper_doc"));
        medicaldocList.add(new medicaldoc("Title/Name of Document", "Type of Document", "19/10/2024", "Dr. QuackQuack", "casper_doc"));
    }
}
