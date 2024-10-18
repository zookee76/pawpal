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

        View.OnClickListener backListnr = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(consolidatedsummary.this, chomedashboard.class);
                startActivity(intent);
            }
        };

        backImg.setOnClickListener(backListnr);
        backTxt.setOnClickListener(backListnr);

        rvConsolidatedRecordList = findViewById(R.id.rv_consolrecordscard);
        rvConsolidatedRecordList.setLayoutManager(new LinearLayoutManager(this));

        consolidatedrecordsList = new ArrayList<>();
        loadConsolidatedSummaries();

        adapter = new consolidatedrecordadapter(this, consolidatedrecordsList);
        rvConsolidatedRecordList.setAdapter(adapter);

        //Navigation Handle
        ImageView home, calendar, files, profile;
        home = findViewById(R.id.iv_home);
        calendar = findViewById(R.id.iv_calendar);
        files = findViewById((R.id.iv_files));
        profile = findViewById(R.id.iv_userprofile);

        //Link to navigation buttons
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(consolidatedsummary.this, chomedashboard.class);
                startActivity(intent);
            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(consolidatedsummary.this, userprofilepage.class);
                startActivity(intent);
            }
        });

        files.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(consolidatedsummary.this, consolidatedsummary.class);
                startActivity(intent);
            }
        });

        calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(consolidatedsummary.this, pschedules.class);
                startActivity(intent);
            }
        });
    }

    private void loadConsolidatedSummaries() {
        consolidatedrecordsList.add(new consolidatedrecords("Title", "Lab Results", "19/10/2024", "Dr. QuackQuack", "casper_results"));
        consolidatedrecordsList.add(new consolidatedrecords("Title", "Lab Results", "19/10/2024", "Dr. QuackQuack", "casper_results"));
        consolidatedrecordsList.add(new consolidatedrecords("Title", "Lab Results", "19/10/2024", "Dr. QuackQuack", "casper_results"));
    }
}
