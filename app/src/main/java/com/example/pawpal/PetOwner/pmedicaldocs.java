package com.example.pawpal.PetOwner;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pawpal.Adapter.medicaldocadapter;
import com.example.pawpal.Model.medicaldoc;
import com.example.pawpal.R;
import com.example.pawpal.Shared.appointmentspage;
import com.example.pawpal.Shared.consolidatedsummary;
import com.example.pawpal.Shared.petspage;
import com.example.pawpal.Shared.petprofilepage;

public class pmedicaldocs extends AppCompatActivity {

    private RecyclerView rvMedDocs;
    private List<medicaldoc> medicaldocList;
    private medicaldocadapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_petprofilemedical);

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
                startActivity(intent);
                finish();
            }
        });

        meddiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(pmedicaldocs.this, pmediet.class);
                startActivity(intent);
                finish();
            }
        });

        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(pmedicaldocs.this, pabout.class);
                startActivity(intent);
                finish();
            }
        });

        schedules.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(pmedicaldocs.this, pschedules.class);
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
                startActivity(intent);
                finish();
            }
        });

        pets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(pmedicaldocs.this, petspage.class);
                intent.putExtra("IS_PET_OWNER", true);
                startActivity(intent);
                finish();
            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(pmedicaldocs.this, petprofilepage.class);
                startActivity(intent);
                finish();
            }
        });

        files.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(pmedicaldocs.this, consolidatedsummary.class);
                intent.putExtra("IS_PET_OWNER", true);
                startActivity(intent);
                finish();
            }
        });

        calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(pmedicaldocs.this, appointmentspage.class);
                intent.putExtra("IS_PET_OWNER", true);
                startActivity(intent);
                finish();
            }
        });
    }

    private void loadMedicalDocs(){
        medicaldocList.add(new medicaldoc("Title/Name of Document", "Type of Document", "19/10/2024", "Dr. QuackQuack", "casper_doc"));
        medicaldocList.add(new medicaldoc("Title/Name of Document", "Type of Document", "19/10/2024", "Dr. QuackQuack", "casper_doc"));
        medicaldocList.add(new medicaldoc("Title/Name of Document", "Type of Document", "19/10/2024", "Dr. QuackQuack", "casper_doc"));
    }
}
