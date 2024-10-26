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

public class consolidatedsummary extends AppCompatActivity {

    private RecyclerView rvConsolidatedRecordList;
    private consolidatedrecordadapter adapter;
    private List<consolidatedrecords> consolidatedrecordsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_consolidatedsummary);

        //Back Handle
        ImageView backImg = findViewById(R.id.iv_back);
        TextView backTxt = findViewById(R.id.tv_back);

        //Navigation Handle
        ImageView home, calendar, files, profile, pets;
        home = findViewById(R.id.iv_home);
        pets = findViewById(R.id.iv_pets);
        calendar = findViewById(R.id.iv_calendar);
        files = findViewById((R.id.iv_files));
        profile = findViewById(R.id.iv_userprofile);

        //Recyclerview handle
        rvConsolidatedRecordList = findViewById(R.id.rv_consolrecordscard);
        rvConsolidatedRecordList.setLayoutManager(new LinearLayoutManager(this));
        consolidatedrecordsList = new ArrayList<>();
        boolean isPetOwner = getIntent().getBooleanExtra("IS_PET_OWNER", false);

        if(isPetOwner){
            loadConsolidatedSummaries();
            adapter = new consolidatedrecordadapter(this, consolidatedrecordsList);
            rvConsolidatedRecordList.setAdapter(adapter);

            // backhandle
            View.OnClickListener backListnr = new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(consolidatedsummary.this, phomedashboard.class);
                    startActivity(intent);
                    finish();
                }
            };
            backImg.setOnClickListener(backListnr);
            backTxt.setOnClickListener(backListnr);

            //Link to navigation buttons
            home.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(consolidatedsummary.this, phomedashboard.class);
                    startActivity(intent);
                    finish();
                }
            });

            pets.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(consolidatedsummary.this, petspage.class);
                    intent.putExtra("IS_PET_OWNER", true);
                    startActivity(intent);
                    finish();
                }
            });

            profile.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(consolidatedsummary.this, userprofilepage.class);
                    startActivity(intent);
                    finish();
                }
            });

            files.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(consolidatedsummary.this, consolidatedsummary.class);
                    intent.putExtra("IS_PET_OWNER", true);
                    startActivity(intent);
                    finish();
                }
            });

            calendar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(consolidatedsummary.this, appointmentspage.class);
                    intent.putExtra("IS_PET_OWNER", true);
                    startActivity(intent);
                    finish();
                }
            });
        }
        else{
            loadClinicConsoSum();
            adapter = new consolidatedrecordadapter(this, consolidatedrecordsList, isPetOwner);
            rvConsolidatedRecordList.setAdapter(adapter);

            // back handle
            View.OnClickListener backListnr = new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(consolidatedsummary.this, chomedashboard.class);
                    startActivity(intent);
                    finish();
                }
            };
            backImg.setOnClickListener(backListnr);
            backTxt.setOnClickListener(backListnr);

            //Link to navigation buttons
            pets.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(consolidatedsummary.this, clinicpets.class);
                    startActivity(intent);
                    finish();
                }
            });

            home.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(consolidatedsummary.this, chomedashboard.class);
                    startActivity(intent);
                    finish();
                }
            });

            profile.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(consolidatedsummary.this, clinicprofilepage.class);
                    startActivity(intent);
                    finish();
                }
            });

            files.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(consolidatedsummary.this, consolidatedsummary.class);
                    intent.putExtra("IS_PET_OWNER", false);
                    startActivity(intent);
                    finish();
                }
            });

            calendar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(consolidatedsummary.this, appointmentspage.class);
                    intent.putExtra("IS_PET_OWNER", false);
                    startActivity(intent);
                    finish();
                }
            });
        }
    }

    private void loadConsolidatedSummaries() {
        consolidatedrecordsList.add(new consolidatedrecords("Title", "Lab Results", "19/10/2024", "Dr. QuackQuack", "casper_results"));
    }

    private void loadClinicConsoSum(){
        consolidatedrecordsList.add(new consolidatedrecords("Title", "Lab Results", "19/10/2024", "Dr. QuackQuack", "casper_results"));
        consolidatedrecordsList.add(new consolidatedrecords("Title", "Lab Results", "19/10/2024", "Dr. QuackQuack", "casper_results"));
    }
}
