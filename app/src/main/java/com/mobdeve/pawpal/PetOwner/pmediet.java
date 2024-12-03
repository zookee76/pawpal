package com.mobdeve.pawpal.PetOwner;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.mobdeve.pawpal.Adapter.medicaldocadapter;
import com.mobdeve.pawpal.Adapter.medietAdapter;
import com.mobdeve.pawpal.Database.DBHelper;
import com.mobdeve.pawpal.Model.dietmed;
import com.mobdeve.pawpal.Model.medicaldoc;
import com.mobdeve.pawpal.Model.petOwners;
import com.mobdeve.pawpal.Model.pets;
import com.mobdeve.pawpal.R;
import com.mobdeve.pawpal.Shared.appointmentspage;
import com.mobdeve.pawpal.Shared.consolidatedsummary;
import com.mobdeve.pawpal.Shared.petspage;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class pmediet extends AppCompatActivity
{
    private DBHelper DB;
    private TextView petName;
    private RecyclerView rvMediet;
    private medietAdapter adapter;
    private List<dietmed> dietmedList;
    private pets petData;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_petprofilemediet);

        DB = new DBHelper(getApplicationContext());
        Intent intent = getIntent();
        petData = intent.getParcelableExtra("PET_DATA");
        petOwners ownerData = intent.getParcelableExtra("OWNER_DATA");

        // ELEMENTS
        ImageView petImage = findViewById(R.id.petImage);
        petName = findViewById(R.id.petName);

        long imageID = petData.getImageID();
        String imagePath = DB.getImagePath(imageID);

        if(imagePath!=null){
            File imgFile = new File(imagePath);
            if(imgFile.exists()){
                Glide.with(getApplicationContext())
                        .load(imgFile)
                        .into(petImage);
            }
        }

        rvMediet = findViewById(R.id.rv_mediet);
        rvMediet.setLayoutManager(new LinearLayoutManager(this));

        dietmedList = new ArrayList<>();
        loadalldietmed();

        adapter = new medietAdapter(this, dietmedList, DB);
        rvMediet.setAdapter(adapter);

        //Back Handle
        ImageView backImg = findViewById(R.id.iv_back);
        TextView backTxt = findViewById(R.id.tv_back);

        ImageView shareImg = findViewById(R.id.iv_share);

        View.OnClickListener backListnr = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*
                Intent intent = new Intent(pmediet.this, phomedashboard.class);
                startActivity(intent);

                 */
                finish();
            }
        };

        View.OnClickListener shareListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(pmediet.this, shareqr.class);
                startActivity(intent);
            }
        };

        shareImg.setOnClickListener(shareListener);

        backImg.setOnClickListener(backListnr);
        backTxt.setOnClickListener(backListnr);

        TextView meddiet = findViewById(R.id.medicationdietcategory);
        TextView about = findViewById(R.id.aboutcategory);
        TextView schedules = findViewById(R.id.schedulecategory);
        TextView meddocs = findViewById(R.id.medicaldocscategory);

        meddocs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(pmediet.this, pmedicaldocs.class);
                intent.putExtra("USER_DATA", ownerData);
                intent.putExtra("PET_DATA", petData);
                startActivity(intent);
                finish();
            }
        });

        meddiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(pmediet.this, pmediet.class);
                intent.putExtra("USER_DATA", ownerData);
                intent.putExtra("PET_DATA", petData);
                startActivity(intent);
                finish();
            }
        });

        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(pmediet.this, pabout.class);
                intent.putExtra("USER_DATA", ownerData);
                intent.putExtra("PET_DATA", petData);
                startActivity(intent);
                finish();
            }
        });

        schedules.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(pmediet.this, pschedules.class);
                intent.putExtra("USER_DATA", ownerData);
                intent.putExtra("PET_DATA", petData);
                startActivity(intent);
                finish();
            }
        });

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
                Intent intent = new Intent(pmediet.this, phomedashboard.class);
                intent.putExtra("USER_DATA", ownerData);
                startActivity(intent);
                finish();
            }
        });

        pets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(pmediet.this, petspage.class);
                intent.putExtra("USER_DATA", ownerData);
                intent.putExtra("IS_PET_OWNER", true);
                startActivity(intent);
                finish();
            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(pmediet.this, petprofilepage.class);
                intent.putExtra("USER_DATA", ownerData);
                startActivity(intent);
                finish();
            }
        });

        files.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(pmediet.this, consolidatedsummary.class);
                intent.putExtra("USER_DATA", ownerData);
                intent.putExtra("IS_PET_OWNER", true);
                startActivity(intent);
                finish();
            }
        });

        calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(pmediet.this, appointmentspage.class);
                intent.putExtra("USER_DATA", ownerData);
                intent.putExtra("IS_PET_OWNER", true);
                startActivity(intent);
                finish();
            }
        });
    }

    protected void onResume() {
        super.onResume();
        dietmedList.clear();
        loadalldietmed();
        if (adapter != null) {
            adapter.notifyDataSetChanged();
            Log.d("RESUMING", "Checking");
        }
    }

    private void loadalldietmed(){
        dietmedList.clear();
        long petID = petData.getID();

        if (DB != null) {
            List<dietmed> dmed = DB.getPetDietmed(petID);
            Log.d("DIETMEDSIZE", "Size: " + dmed.size());

            if (dmed != null && !dmed.isEmpty()) {
                dietmedList.addAll(dmed);
            }
        } else {
            Log.e("DIETMEDSIZE", "petsDB is not initialized.");
        }

        if(adapter != null){
            adapter.notifyDataSetChanged();

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK){
            reloadDietmed();
        }
    }

    private void reloadDietmed(){
        long petID = petData.getID();
        if (dietmedList != null && !dietmedList.isEmpty()) {
            dietmedList.clear();
        }
        dietmedList.clear();
        dietmedList.addAll(DB.getPetDietmed(petID));

        adapter.notifyDataSetChanged();
    }

}
