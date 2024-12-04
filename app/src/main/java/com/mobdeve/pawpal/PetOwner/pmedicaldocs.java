package com.mobdeve.pawpal.PetOwner;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.mobdeve.pawpal.Adapter.medicaldocadapter;
import com.mobdeve.pawpal.Database.DBHelper;
import com.mobdeve.pawpal.Model.medicaldoc;
import com.mobdeve.pawpal.Model.petOwners;
import com.mobdeve.pawpal.Model.pets;
import com.mobdeve.pawpal.R;
import com.mobdeve.pawpal.Shared.appointmentspage;
import com.mobdeve.pawpal.Shared.consolidatedsummary;
import com.mobdeve.pawpal.Shared.petspage;

public class pmedicaldocs extends AppCompatActivity {

    private DBHelper DB;
    private TextView petName;
    private pets petData;
    private petOwners ownerData;

    private RecyclerView rvMedDocs;
    private List<medicaldoc> medicaldocList;
    private medicaldocadapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_petprofilemedical);

        DB = new DBHelper(getApplicationContext());
        Intent intent = getIntent();
        petData = intent.getParcelableExtra("PET_DATA");
        ownerData = intent.getParcelableExtra("OWNER_DATA");

        //ELEMENTS
        ImageView petImage = findViewById(R.id.petImage);
        petName = findViewById(R.id.petName);
        petName.setText(petData.getName());

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

        rvMedDocs = findViewById(R.id.rv_medicaldoccard);
        rvMedDocs.setLayoutManager(new LinearLayoutManager(this));

        medicaldocList = new ArrayList<>();
        loadMedicalDocs();

        adapter = new medicaldocadapter(this, medicaldocList);
        rvMedDocs.setAdapter(adapter);

        //Back Handle
        ImageView backImg = findViewById(R.id.iv_back);
        TextView backTxt = findViewById(R.id.tv_back);

        ImageView shareImg = findViewById(R.id.iv_share);

        View.OnClickListener backListnr = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*
                Intent intent = new Intent(pmedicaldocs.this, phomedashboard.class);
                startActivity(intent);

                 */
                finish();
            }
        };

        View.OnClickListener shareListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(pmedicaldocs.this, shareqr.class);
                startActivity(intent);
            }
        };

        backImg.setOnClickListener(backListnr);
        backTxt.setOnClickListener(backListnr);

        shareImg.setOnClickListener(shareListener);

        TextView meddiet = findViewById(R.id.medicationdietcategory);
        TextView about = findViewById(R.id.aboutcategory);
        TextView schedules = findViewById(R.id.schedulecategory);
        TextView meddocs = findViewById(R.id.medicaldocscategory);

        meddocs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(pmedicaldocs.this, pmedicaldocs.class);
                intent.putExtra("USER_DATA", ownerData);
                intent.putExtra("PET_DATA", petData);
                startActivity(intent);
                finish();
            }
        });

        meddiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(pmedicaldocs.this, pmediet.class);
                intent.putExtra("USER_DATA", ownerData);
                intent.putExtra("PET_DATA", petData);
                startActivity(intent);
                finish();
            }
        });

        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(pmedicaldocs.this, pabout.class);
                intent.putExtra("USER_DATA", ownerData);
                intent.putExtra("PET_DATA", petData);
                startActivity(intent);
                finish();
            }
        });

        schedules.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(pmedicaldocs.this, pschedules.class);
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
                Intent intent = new Intent(pmedicaldocs.this, phomedashboard.class);
                intent.putExtra("USER_DATA", ownerData);
                startActivity(intent);
                finish();
            }
        });

        pets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(pmedicaldocs.this, petspage.class);
                intent.putExtra("IS_PET_OWNER", true);
                intent.putExtra("USER_DATA", ownerData);
                startActivity(intent);
                finish();
            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(pmedicaldocs.this, petprofilepage.class);
                intent.putExtra("USER_DATA", ownerData);
                startActivity(intent);
                finish();
            }
        });

        files.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(pmedicaldocs.this, consolidatedsummary.class);
                intent.putExtra("IS_PET_OWNER", true);
                intent.putExtra("USER_DATA", ownerData);
                finish();
            }
        });

        calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(pmedicaldocs.this, appointmentspage.class);
                intent.putExtra("IS_PET_OWNER", true);
                intent.putExtra("USER_DATA", ownerData);
                startActivity(intent);
                finish();
            }
        });
    }


    private void loadMedicalDocs(){
        long petID = petData.getID();
        medicaldocList.add(new medicaldoc(1,"Title/Name of Document", "Type of Document", "19/10/2024", "Dr. QuackQuack", "casper_doc"));
        medicaldocList.add(new medicaldoc(2,"Title/Name of Document", "Type of Document", "19/10/2024", "Dr. QuackQuack", "casper_doc"));
        medicaldocList.add(new medicaldoc(3,"Title/Name of Document", "Type of Document", "19/10/2024", "Dr. QuackQuack", "casper_doc"));
    }
}
