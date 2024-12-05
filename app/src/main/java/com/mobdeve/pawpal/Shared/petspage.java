package com.mobdeve.pawpal.Shared;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mobdeve.pawpal.Adapter.petAdapter;
import com.mobdeve.pawpal.Database.DBHelper;
import com.mobdeve.pawpal.Model.clinicVet;
import com.mobdeve.pawpal.Model.petOwners;
import com.mobdeve.pawpal.Model.pets;
import com.mobdeve.pawpal.Model.images;
import com.mobdeve.pawpal.PetOwner.petprofilepage;
import com.mobdeve.pawpal.R;
import com.mobdeve.pawpal.ClinicOwner.chomedashboard;
import com.mobdeve.pawpal.ClinicOwner.clinicprofilepage;
import com.mobdeve.pawpal.ClinicOwner.medrecordspage;
import com.mobdeve.pawpal.PetOwner.phomedashboard;

import java.util.ArrayList;
import java.util.List;

public class petspage extends AppCompatActivity {

    private DBHelper DB;
    private List<images> imagesList;

    private RecyclerView rvPets;
    private petAdapter adapter;
    private List<pets> petsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypets);

        // DB
        DB = new DBHelper(getApplicationContext());

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
            Intent intent = getIntent();
            petOwners petOwner = intent.getParcelableExtra("USER_DATA");
            long ownerID = petOwner.getID();
            loadPets(ownerID);
            adapter = new petAdapter(this, petsList, DB, petOwner);
            rvPets.setAdapter(adapter);

            // back handle
            View.OnClickListener backListnr = new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(petspage.this, phomedashboard.class);
                    intent.putExtra("USER_DATA", petOwner);
                    startActivity(intent);
                    finish();
                }
            };

            backImg.setOnClickListener(backListnr);
            backTxt.setOnClickListener(backListnr);

            //Link to navigation buttons
            home.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(petspage.this, phomedashboard.class);
                    intent.putExtra("USER_DATA", petOwner);
                    startActivity(intent);
                    finish();
                }
            });

            pets.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(petspage .this, petspage.class);
                    intent.putExtra("IS_PET_OWNER", true);
                    intent.putExtra("USER_DATA", petOwner);
                    startActivity(intent);
                    finish();
                }
            });

            profile.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(petspage.this, petprofilepage.class);
                    intent.putExtra("USER_DATA", petOwner);
                    startActivity(intent);
                    finish();
                }
            });

            files.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(petspage.this, consolidatedsummary.class);
                    intent.putExtra("USER_DATA", petOwner);
                    intent.putExtra("IS_PET_OWNER", true);
                    startActivity(intent);
                    finish();
                }
            });

            calendar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(petspage.this, appointmentspage.class);
                    intent.putExtra("IS_PET_OWNER", true);
                    intent.putExtra("USER_DATA", petOwner);
                    startActivity(intent);
                    finish();
                }
            });
        }
        else{
            Intent intent = getIntent();
            clinicVet vet = intent.getParcelableExtra("USER_DATA");
            long ownerID = getIntent().getLongExtra("OWNERID", -1);

            if(ownerID != -1){
                loadPets(ownerID);
            }
            else{

            }

            petOwners selectedOwner = DB.getPetOwnerByID(ownerID);

            adapter = new petAdapter(this, petsList, DB, selectedOwner, isPetOwner);
            rvPets.setAdapter(adapter);
            TextView pagetitle = findViewById(R.id.tv_mypets);
            String title = intent.getStringExtra("PETS_PAGE_TITLE");
            pagetitle.setText(title);

            // back handle
            View.OnClickListener backListnr = new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(petspage.this, medrecordspage.class);
                    startActivity(intent);
                    finish();
                }
            };

            backImg.setOnClickListener(backListnr);
            backTxt.setOnClickListener(backListnr);
            //Link to navigation buttons
            home.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(petspage.this, chomedashboard.class);
                    intent.putExtra("USER_DATA", vet);
                    startActivity(intent);
                    finish();
                }
            });

            profile.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(petspage.this, clinicprofilepage.class);
                    intent.putExtra("USER_DATA", vet);
                    startActivity(intent);
                    finish();
                }
            });

            files.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(petspage.this, consolidatedsummary.class);
                    intent.putExtra("USER_DATA", vet);
                    startActivity(intent);
                    finish();
                }
            });

            calendar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(petspage.this, appointmentspage.class);
                    intent.putExtra("USER_DATA", vet);
                    startActivity(intent);
                    finish();
                }
            });
        }

    }

    // use this when logged as pet owner
    private void loadPets(long ownerID){
        List<pets> pets = new ArrayList<>();
        pets = DB.getPetsByOwner(ownerID);
        petsList.clear();
        this.petsList.addAll(pets);
        Log.d("loadPets", "Loaded " + petsList.size() + " pets for owner ID " + ownerID);
        // sample data for pets change for getting pets according too pet owner
        //petsList.add(new pets("Casper", "Domestic Short Hair", "Male", 3, R.drawable.casper));
    }
    // use this when logged as clinic owner
    private void loadpetsperowner(long ownerID){

    }
}
