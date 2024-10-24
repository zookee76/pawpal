package com.example.pawpal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class cabout extends AppCompatActivity
{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_clinicpetprofileabout);

        //Back Handle
        ImageView backImg = findViewById(R.id.iv_back);
        TextView backTxt = findViewById(R.id.tv_back);

        ImageView editImg = findViewById(R.id.iv_edit);

        View.OnClickListener backListnr = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(cabout.this, chomedashboard.class);
                startActivity(intent);
            }
        };

        View.OnClickListener editListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(cabout.this, editabout.class);
                startActivity(intent);
            }
        };

        editImg.setOnClickListener(editListener);

        backImg.setOnClickListener(backListnr);
        backTxt.setOnClickListener(backListnr);

        TextView cmeddiet = findViewById(R.id.medicationdietcategory);
        TextView cabout = findViewById(R.id.aboutcategory);
        TextView cschedules = findViewById(R.id.schedulecategory);
        TextView cmeddocs = findViewById(R.id.medicaldocscategory);

        cmeddocs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(cabout.this, pmedicaldocs.class);
                startActivity(intent);
            }
        });

        cmeddiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(cabout.this, pmediet.class);
                startActivity(intent);
            }
        });

        cabout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(cabout.this, pabout.class);
                startActivity(intent);
            }
        });

        cschedules.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(cabout.this, pschedules.class);
                startActivity(intent);
            }
        });

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
                Intent intent = new Intent(cabout.this, chomedashboard.class);
                startActivity(intent);
            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(cabout.this, userprofilepage.class);
                startActivity(intent);
            }
        });

        files.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(cabout.this, consolidatedsummary.class);
                startActivity(intent);
            }
        });

        calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(cabout.this, appointmentspage.class);
                startActivity(intent);
            }
        });
    }
}
