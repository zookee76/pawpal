package com.mobdeve.pawpal.Shared;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.mobdeve.pawpal.PetOwner.editprofilepage;
import com.mobdeve.pawpal.PetOwner.pchangepw;
import com.mobdeve.pawpal.PetOwner.phomedashboard;
import com.mobdeve.pawpal.R;
import com.mobdeve.pawpal.Welcome.welcomerole;

public class petprofilepage extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_userprofile);

        //Back Handle
        ImageView backImg = findViewById(R.id.iv_back);
        TextView backTxt = findViewById(R.id.tv_back);

        View.OnClickListener backListnr = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(petprofilepage.this, phomedashboard.class);
                startActivity(intent);
                finish();
            }
        };

        // Logout Handle
        TextView logout = findViewById(R.id.tv_logout);
        ImageView logoutimg =  findViewById(R.id.iv_logout);

        View.OnClickListener logoutLstnr = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(petprofilepage.this, welcomerole.class);
                startActivity(intent);
                finish();
            }
        };

        // Edit Handle
        ImageView editprofile = findViewById(R.id.iv_arrowprofile);
        ImageView changepw = findViewById(R.id.iv_arrowpw);

        editprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(petprofilepage.this, editprofilepage.class);
                startActivity(intent);
            }
        });

        changepw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(petprofilepage.this, pchangepw.class);
                startActivity(intent);
                finish();
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
        pets = findViewById(R.id.iv_pets);
        files = findViewById((R.id.iv_files));
        profile = findViewById(R.id.iv_userprofile);

        //Link to navigation buttons
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(petprofilepage.this, phomedashboard.class);

                startActivity(intent);
                finish();
            }
        });

        pets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(petprofilepage.this, petspage.class);
                intent.putExtra("IS_PET_OWNER", true);
                startActivity(intent);
                finish();
            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(petprofilepage.this, petprofilepage.class);
                startActivity(intent);
                finish();
            }
        });

        files.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(petprofilepage.this, consolidatedsummary.class);
                intent.putExtra("IS_PET_OWNER", true);
                startActivity(intent);
                finish();
            }
        });

        calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(petprofilepage.this, appointmentspage.class);
                intent.putExtra("IS_PET_OWNER", true);
                startActivity(intent);
                finish();
            }
        });
    }
}