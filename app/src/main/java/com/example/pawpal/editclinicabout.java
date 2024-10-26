package com.example.pawpal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class editclinicabout extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliniceditabout);
        //Back Handle
        ImageView backImg = findViewById(R.id.iv_back);
        TextView backTxt = findViewById(R.id.tv_back);

        View.OnClickListener backListnr = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(editclinicabout.this, cabout.class);
                editclinicabout.this.startActivity(intent);
            }
        };
        backImg.setOnClickListener(backListnr);
        backTxt.setOnClickListener(backListnr);

        // Navigations
        TextView about, meddocu, meddiet, schedules;
        ImageView edit;

        about = findViewById(R.id.aboutcategory);
        meddocu = findViewById(R.id.medicaldocscategory);
        meddiet = findViewById(R.id.medicationdietcategory);
        schedules = findViewById(R.id.schedulecategory);

        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(editclinicabout.this, cabout.class);
                // handle data here
                startActivity(intent);
            }
        });

        meddocu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(editclinicabout.this, cmedicaldocs.class);
                // handle data here
                startActivity(intent);
            }
        });

        meddiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(editclinicabout.this, cmedicationdiet.class);
                // handle data here
                startActivity(intent);
            }
        });

        schedules.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(editclinicabout.this, cschedules.class);
                // handle data here
                startActivity(intent);
            }
        });

        //Main Navigation Handle
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
                Intent intent = new Intent(editclinicabout.this, clinicpets.class);
                startActivity(intent);
            }
        });
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(editclinicabout.this, chomedashboard.class);
                startActivity(intent);
            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(editclinicabout.this, userprofilepage.class);
                startActivity(intent);
            }
        });

        files.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(editclinicabout.this, consolidatedsummary.class);
                intent.putExtra("IS_PET_OWNER", false);
                startActivity(intent);
            }
        });

        calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(editclinicabout.this, appointmentspage.class);
                intent.putExtra("IS_PET_OWNER", false);
                startActivity(intent);
            }
        });

        // Logic to handle inputs

    }
}