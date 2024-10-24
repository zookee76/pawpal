package com.example.pawpal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class schedules extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_clinicpetprofileschedule);

        //Back Handle
        ImageView backImg = findViewById(R.id.iv_back);
        TextView backTxt = findViewById(R.id.tv_back);

        View.OnClickListener backListnr = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(schedules.this, petspage.class);
                intent.putExtra("IS_PET_OWNER", false);
                intent.putExtra("PETS_PAGE_TITLE", "List of Pets");
                schedules.this.startActivity(intent);
            }
        };
        backImg.setOnClickListener(backListnr);
        backTxt.setOnClickListener(backListnr);

        // options
        TextView about, meddocu, meddiet, schedules;
        ImageView edit;

        about = findViewById(R.id.aboutcategory);
        meddocu = findViewById(R.id.medicaldocscategory);
        meddiet = findViewById(R.id.medicationdietcategory);
        schedules = findViewById(R.id.schedulecategory);
        edit = findViewById(R.id.iv_edit);

        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(schedules.this, cliniceditabout.class);
                // handle data here
                startActivity(intent);
            }
        });

        meddocu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(schedules.this, medicaldocs.class);
                // handle data here
                startActivity(intent);
            }
        });

        meddiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(schedules.this, medicationdiet.class);
                // handle data here
                startActivity(intent);
            }
        });

        schedules.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(schedules.this, schedules.class);
                // handle data here
                startActivity(intent);
            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(schedules.this, editschedules.class);
                // handle data here
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
                Intent intent = new Intent(schedules.this, chomedashboard.class);
                startActivity(intent);
            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(schedules.this, userprofilepage.class);
                startActivity(intent);
            }
        });

        files.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(schedules.this, consolidatedsummary.class);
                startActivity(intent);
            }
        });

        calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(schedules.this, appointmentspage.class);
                startActivity(intent);
            }
        });

    }
}