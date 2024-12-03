package com.mobdeve.pawpal.ClinicOwner;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mobdeve.pawpal.Adapter.clinicpetsAdapter;
import com.mobdeve.pawpal.Database.DBHelper;
import com.mobdeve.pawpal.Model.clinicVet;
import com.mobdeve.pawpal.Model.petOwners;
import com.mobdeve.pawpal.Model.pets;
import com.mobdeve.pawpal.Model.images;
import com.mobdeve.pawpal.R;
import com.mobdeve.pawpal.Shared.appointmentspage;
import com.mobdeve.pawpal.Shared.consolidatedsummary;

import java.util.ArrayList;
import java.util.List;

public class clinicpets extends AppCompatActivity {
    private RecyclerView rvClinicPets;
    private clinicpetsAdapter adapter;
    private List<pets> clinicpetsList;
    private DBHelper db;
    private List<images> imagesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_clinicpets);

        // Get Data
        Intent intent = getIntent();
        clinicVet vet = intent.getParcelableExtra("USER_DATA");
        if(vet != null){
            String firstName = vet.getFirstName();
            TextView tvName = findViewById(R.id.tv_vetname);
            tvName.setText(firstName);
            Log.d("CHECK clinicpets", "VET NAME: " + vet.getFirstName() + "VET ID: " + vet.getVetID());

        }else{
            Log.d("CHECK clinicpets", "NO VET");

        }

        FloatingActionButton addPets = findViewById(R.id.btn_addpets);
        addPets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(clinicpets.this, addPets.class);
                intent.putExtra("USER_DATA", vet);
                startActivityForResult(intent, 1);
                //startActivity(intent);
            }
        });


        // back handle
        ImageView backImg = findViewById(R.id.iv_back);
        TextView backTxt = findViewById(R.id.tv_back);
        View.OnClickListener backListnr = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(clinicpets.this, chomedashboard.class);
                startActivity(intent);
                finish();
            }
        };

        backImg.setOnClickListener(backListnr);
        backTxt.setOnClickListener(backListnr);

        //Navigation Handle
        ImageView home, calendar, files, profile, pets;
        home = findViewById(R.id.iv_home);
        calendar = findViewById(R.id.iv_calendar);
        files = findViewById((R.id.iv_files));
        profile = findViewById(R.id.iv_userprofile);
        pets = findViewById(R.id.iv_pets);

        //Link to navigation buttons
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(clinicpets.this, chomedashboard.class);
                startActivity(intent);
                finish();
            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(clinicpets.this, clinicprofilepage.class);
                startActivity(intent);
                finish();
            }
        });

        files.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(clinicpets.this, consolidatedsummary.class);
                intent.putExtra("IS_PET_OWNER", false);
                startActivity(intent);
                finish();
            }
        });

        calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(clinicpets.this, appointmentspage.class);
                intent.putExtra("IS_PET_OWNER", false);
                startActivity(intent);
                finish();
            }
        });

        pets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(clinicpets.this, clinicpets.class);
                startActivity(intent);
                finish();
            }
        });

        db = new DBHelper(getApplicationContext());

        rvClinicPets = findViewById(R.id.rv_clinicpets);
        if (clinicpetsList == null) {
            clinicpetsList = new ArrayList<>();
        }

        loadAllPets();
        TextView message = findViewById(R.id.tv_message);

        List<images> imagesList = db.getAllImages();

        adapter = new clinicpetsAdapter(clinicpetsList, this, db, imagesList);

        if(clinicpetsList.isEmpty()){
            message.setVisibility(View.VISIBLE);
            message.setText("No Pets Available");
        }
        else{
            // grid layout
            message.setVisibility(View.GONE);
        }
        int numOfCol = calculateNoOfCols(this, 150);
        rvClinicPets.setLayoutManager(new GridLayoutManager(this, numOfCol));
        rvClinicPets.setAdapter(adapter);

        rvClinicPets.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                if(clinicpetsList.size() != imagesList.size()){
                    adapter.updateLists(clinicpetsList, imagesList);
                }
                rvClinicPets.getViewTreeObserver().removeOnPreDrawListener(this);
                return true;
            }
        });
    }

    protected void onResume() {
        super.onResume();
        clinicpetsList.clear();
        loadAllPets();
        if (adapter != null) {
            adapter.notifyDataSetChanged();
            Log.d("RESUMING", "Checking");
        }
    }

    private int calculateNoOfCols(Context cxt, float colWidthDp){
        float columndWidth = colWidthDp * cxt.getResources().getDisplayMetrics().density;
        float screenWidthdp = getResources().getDisplayMetrics().widthPixels;
        return Math.max(1, (int) (screenWidthdp/columndWidth));
    }

    private void loadAllPets(){
        clinicpetsList.clear();
        if (db != null) {
            List<pets> petsList = db.getAllPets();
            Log.d("PETSSIZE", "Pets size: " + petsList.size());

            if (petsList != null && !petsList.isEmpty()) {
                clinicpetsList.addAll(petsList);
            }
        } else {
            Log.e("PETSBNOT", "petsDB is not initialized.");
        }

        if(adapter != null){
            adapter.notifyDataSetChanged();

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK){
            reloadClinicPets();
        }
    }

    private void reloadClinicPets(){
        if (clinicpetsList != null && !clinicpetsList.isEmpty()) {
            clinicpetsList.clear();
        }
        clinicpetsList.addAll(db.getAllPets());
        imagesList.clear();
        imagesList.addAll(db.getAllImages());

        adapter.notifyDataSetChanged();
    }
}