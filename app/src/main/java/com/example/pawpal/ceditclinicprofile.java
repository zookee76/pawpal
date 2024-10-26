package com.example.pawpal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ceditclinicprofile extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_cliniceditprofile);

        //Save and Back Handle
        ImageView backImg = findViewById(R.id.iv_back);
        TextView backTxt = findViewById(R.id.tv_back);
        Button save = findViewById(R.id.btn_save);

        View.OnClickListener backListnr = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ceditclinicprofile.this, clinicprofilepage.class);
                startActivity(intent);
                finish();
            }
        };

        backImg.setOnClickListener(backListnr);
        backTxt.setOnClickListener(backListnr);
        save.setOnClickListener(backListnr);

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
                Intent intent = new Intent(ceditclinicprofile.this, clinicpets.class);
                startActivity(intent);
                finish();
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ceditclinicprofile.this, chomedashboard.class);
                startActivity(intent);
                finish();
            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ceditclinicprofile.this, clinicprofilepage.class);
                startActivity(intent);
                finish();
            }
        });

        files.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ceditclinicprofile.this, consolidatedsummary.class);
                intent.putExtra("IS_PET_OWNER", false);
                startActivity(intent);
                finish();
            }
        });

        calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ceditclinicprofile.this, appointmentspage.class);
                intent.putExtra("IS_PET_OWNER", false);
                startActivity(intent);
                finish();
            }
        });
    }
}
