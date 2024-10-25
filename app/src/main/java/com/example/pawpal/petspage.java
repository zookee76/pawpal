package com.example.pawpal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class petspage extends AppCompatActivity {

    private RecyclerView rvPets;
    private petAdapter adapter;
    private List<pets> petsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_mypets);

        //Back Handle
        ImageView backImg = findViewById(R.id.iv_back);
        TextView backTxt = findViewById(R.id.tv_back);

        //Navigation Handle
        ImageView home, calendar, pets, files, profile;
        home = findViewById(R.id.iv_home);
        calendar = findViewById(R.id.iv_calendar);
        pets = findViewById(R.id.iv_pets);
        files = findViewById((R.id.iv_files));
        profile = findViewById(R.id.iv_userprofile);

        // RecyclerView Handle
        rvPets = findViewById(R.id.rv_pets);
        rvPets.setLayoutManager(new LinearLayoutManager(this));
        petsList = new ArrayList<>();
        boolean isPetOwner = getIntent().getBooleanExtra("IS_PET_OWNER", false);

        if(isPetOwner){
            loadPets();
            adapter = new petAdapter(this, petsList);
            rvPets.setAdapter(adapter);

            // back handle
            View.OnClickListener backListnr = new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(petspage.this, phomedashboard.class);
                    startActivity(intent);
                }
            };

            backImg.setOnClickListener(backListnr);
            backTxt.setOnClickListener(backListnr);

            //Link to navigation buttons
            home.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(petspage.this, phomedashboard.class);

                    startActivity(intent);
                }
            });

            pets.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(petspage .this, petspage.class);
                    intent.putExtra("IS_PET_OWNER", true);
                    startActivity(intent);
                }
            });

            profile.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(petspage.this, userprofilepage.class);
                    startActivity(intent);
                }
            });

            files.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(petspage.this, consolidatedsummary.class);
                    intent.putExtra("IS_PET_OWNER", true);
                    startActivity(intent);
                }
            });

            calendar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(petspage.this, appointmentspage.class);
                    intent.putExtra("IS_PET_OWNER", true);
                    startActivity(intent);
                }
            });

        }
        else{
            loadpetsperowner();
            Intent intent = getIntent();

            // back handle
            View.OnClickListener backListnr = new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(petspage.this, medrecordspage.class);
                    startActivity(intent);
                }
            };

            backImg.setOnClickListener(backListnr);
            backTxt.setOnClickListener(backListnr);

            adapter = new petAdapter(this, petsList, isPetOwner);
            rvPets.setAdapter(adapter);
            TextView pagetitle = findViewById(R.id.tv_mypets);
            String title = intent.getStringExtra("PETS_PAGE_TITLE");
            pagetitle.setText(title);

            //Link to navigation buttons
            home.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(petspage.this, chomedashboard.class);
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
                    Intent intent = new Intent(petspage.this, consolidatedsummary.class);
                    startActivity(intent);
                }
            });

            calendar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(petspage.this, appointmentspage.class);
                    startActivity(intent);
                }
            });
        }

    }

    // use this when logged as pet owner
    private void loadPets(){
        // sample data for pets change for getting pets according too pet owner
        petsList.add(new pets("Callie", "Domestic Short Hair", "Female", 4, R.drawable.cat2));
    }
    // use this when logged as clinic owner
    private void loadpetsperowner(){
        // sample data for pets change  with logic for getting all pets
        petsList.add(new pets("Callie", "Domestic Short Hair", "Female", 4, R.drawable.cat2));
        petsList.add(new pets("Casper", "Domestic Short Hair", "Male", 3, R.drawable.cat2));
        petsList.add(new pets("Tyler", "Persian Cat", "Male", 3, R.drawable.cat2));
    }
}
