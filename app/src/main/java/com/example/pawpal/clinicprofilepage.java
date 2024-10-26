package com.example.pawpal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class clinicprofilepage extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_clinicprofile);

        //Back Handle
        ImageView backImg = findViewById(R.id.iv_back);
        TextView backTxt = findViewById(R.id.tv_back);

        View.OnClickListener backListnr = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(clinicprofilepage.this, chomedashboard.class);
                startActivity(intent);
            }
        };

        // Logout Handle
        TextView logout = findViewById(R.id.tv_logout);
        ImageView logoutimg =  findViewById(R.id.iv_logout);

        View.OnClickListener logoutLstnr = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(clinicprofilepage.this, clogin.class);
                startActivity(intent);
            }
        };

        // Edit Handle
        ImageView editprofile = findViewById(R.id.iv_arrowprofile);
        ImageView changepw = findViewById(R.id.iv_arrowpw);

        editprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(clinicprofilepage.this, ceditclinicprofile.class);
                startActivity(intent);
            }
        });

        changepw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(clinicprofilepage.this, changepw.class);
                startActivity(intent);
            }
        });

        logout.setOnClickListener(logoutLstnr);
        logoutimg.setOnClickListener(logoutLstnr);

        backImg.setOnClickListener(backListnr);
        backTxt.setOnClickListener(backListnr);

        //Navigation Handle
        ImageView home, calendar, pets, files, profile;
        home = findViewById(R.id.iv_home);
        calendar = findViewById(R.id.iv_calendar);
        files = findViewById((R.id.iv_files));
        profile = findViewById(R.id.iv_userprofile);
        pets = findViewById(R.id.iv_pets);

        //Link to navigation buttons
        pets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(clinicprofilepage.this, clinicpets.class);
                startActivity(intent);
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(clinicprofilepage.this, chomedashboard.class);

                startActivity(intent);
            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(clinicprofilepage.this, clinicprofilepage.class);
                startActivity(intent);
            }
        });

        files.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(clinicprofilepage.this, consolidatedsummary.class);
                intent.putExtra("IS_PET_OWNER", false);
                startActivity(intent);
            }
        });

        calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(clinicprofilepage.this, appointmentspage.class);
                intent.putExtra("IS_PET_OWNER", false);
                startActivity(intent);
            }
        });
    }
}
