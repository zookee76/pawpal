package com.example.pawpal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

public class certreq extends AppCompatActivity {

    private RecyclerView rvCertReqList;
    private certReqadapter adapter;
    private List<certRequest> certRequestList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_clinicrequest);

        //Back Handle
        ImageView backImg = findViewById(R.id.iv_back);
        TextView backTxt = findViewById(R.id.tv_back);

        View.OnClickListener backListnr = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(certreq.this, chomedashboard.class);
                startActivity(intent);
            }
        };

        backImg.setOnClickListener(backListnr);
        backTxt.setOnClickListener(backListnr);

        rvCertReqList = findViewById(R.id.clinicrequestRecyclerView);
        rvCertReqList.setLayoutManager(new LinearLayoutManager(this));

        certRequestList = new ArrayList<>();
        loadCertReqs();

        adapter = new certReqadapter(this, certRequestList);
        rvCertReqList.setAdapter(adapter);

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
                Intent intent = new Intent(certreq.this, chomedashboard.class);
                startActivity(intent);
            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // add here
            }
        });

        files.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(certreq.this, consolidatedsummary.class);
                intent.putExtra("IS_PET_OWNER", false);
                startActivity(intent);
            }
        });

        calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(certreq.this, pschedules.class);
                intent.putExtra("IS_PET_OWNER", false);
                startActivity(intent);
            }
        });
    }

    private void loadCertReqs() {
        certRequestList.add(new certRequest("001", "Casper", "Ashley Corpuz", "Medical Document", "10/24/2024", "Pending"));
        certRequestList.add(new certRequest("002", "Casper", "Ashley Corpuz", "Medical Document", "10/24/2024", "Pending"));
    }
}
