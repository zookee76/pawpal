package com.mobdeve.pawpal.PetOwner;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.mobdeve.pawpal.Database.DBHelper;
import com.mobdeve.pawpal.Model.petOwners;
import com.mobdeve.pawpal.R;
import com.mobdeve.pawpal.Shared.appointmentspage;
import com.mobdeve.pawpal.Shared.consolidatedsummary;
import com.mobdeve.pawpal.Shared.petspage;
import com.mobdeve.pawpal.Welcome.welcomerole;

import java.io.File;

public class petprofilepage extends AppCompatActivity {
    private DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userprofile);

        DB = new DBHelper(getApplicationContext());
        // Get Data
        Intent intent = getIntent();
        petOwners user = intent.getParcelableExtra("USER_DATA");
        if (user != null) {
            String fullname = user.getFullname();
            String email = user.getEmail();
            String contact = user.getContactNo();
            TextView tvUserFName = findViewById(R.id.tv_userfullname);
            tvUserFName.setText(fullname);

            TextView tvEmail = findViewById(R.id.tv_useremail);
            tvEmail.setText(email);

            TextView tvContact = findViewById(R.id.tv_contact);
            tvContact.setText(contact);

            ImageView pfp = findViewById(R.id.iv_userpfp);

            long imageID = user.getImageID();
            String imagePath = DB.getImagePath(imageID);

            Log.d("CHECKIMAGE", "IMAGEID: " + imageID + " IMAGEPATH: " +imagePath);
            if(imagePath!=null){
                File imgFile = new File(imagePath);
                if(imgFile.exists()){
                    Glide.with(getApplicationContext())
                            .load(imgFile)
                            .into(pfp);
                }
            }
        }

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
                intent.putExtra("USER_DATA", user);
                startActivity(intent);
            }
        });

        changepw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(petprofilepage.this, pchangepw.class);
                intent.putExtra("USER_DATA", user);
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
        pets = findViewById(R.id.iv_pets);
        files = findViewById((R.id.iv_files));
        profile = findViewById(R.id.iv_userprofile);

        //Link to navigation buttons
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(petprofilepage.this, phomedashboard.class);
                intent.putExtra("USER_DATA", user);
                startActivity(intent);
                finish();
            }
        });

        pets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(petprofilepage.this, petspage.class);
                intent.putExtra("IS_PET_OWNER", true);
                intent.putExtra("USER_DATA", user);
                startActivity(intent);
                finish();
            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(petprofilepage.this, petprofilepage.class);
                intent.putExtra("USER_DATA", user);
                startActivity(intent);
                finish();
            }
        });

        files.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(petprofilepage.this, consolidatedsummary.class);
                intent.putExtra("IS_PET_OWNER", true);
                intent.putExtra("USER_DATA", user);
                startActivity(intent);
                finish();
            }
        });

        calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(petprofilepage.this, appointmentspage.class);
                intent.putExtra("IS_PET_OWNER", true);
                intent.putExtra("USER_DATA", user);
                startActivity(intent);
                finish();
            }
        });
    }
}
