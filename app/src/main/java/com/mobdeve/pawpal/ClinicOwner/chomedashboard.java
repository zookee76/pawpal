package com.mobdeve.pawpal.ClinicOwner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.mobdeve.pawpal.Model.clinicVet;
import com.mobdeve.pawpal.Model.petOwners;
import com.mobdeve.pawpal.R;
import com.mobdeve.pawpal.Shared.appointmentspage;
import com.mobdeve.pawpal.Shared.consolidatedsummary;
import com.mobdeve.pawpal.Model.overviewentry;
import com.mobdeve.pawpal.Adapter.overviewentryadapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class chomedashboard extends AppCompatActivity {

    private RecyclerView rv_overviewentries;
    private overviewentryadapter adapter;
    private List<overviewentry> overviewentryList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clinicdashboard);

        // Get Data
        Intent intent = getIntent();
        clinicVet vet = intent.getParcelableExtra("USER_DATA");
        if(vet != null){
            String firstName = vet.getFirstName();
            TextView tvName = findViewById(R.id.tv_vetname);
            tvName.setText("Hello, " + firstName);
            Log.d("CHECK LOGGED  VET", "VET NAME: " + vet.getFirstName() + "VET ID: " + vet.getVetID());

        }

        TextView overviewDate = findViewById(R.id.overviewDate);
        String currentDate = new SimpleDateFormat("MMMM dd, yyyy", Locale.getDefault()).format(new Date());
        overviewDate.setText(currentDate);

        rv_overviewentries = findViewById(R.id.rv_overviewentries);
        rv_overviewentries.setLayoutManager(new LinearLayoutManager(this));

        overviewentryList = new ArrayList<>();
        loadOverviewEntries();

        adapter = new overviewentryadapter(this, overviewentryList);
        rv_overviewentries.setAdapter(adapter);

        FrameLayout rectangle1 = findViewById(R.id.rectangle1); // petownermanagement
        FrameLayout rectangle2 = findViewById(R.id.rectangle2); // appmanager
        FrameLayout rectangle3 = findViewById(R.id.rectangle3); // vacc sched
        FrameLayout rectangle4 = findViewById(R.id.rectangle4); // medrecords
        FrameLayout rectangle5 = findViewById(R.id.rectangle5); // certreq

        rectangle1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(chomedashboard.this, medrecordspage.class);
                intent.putExtra("USER_DATA", vet);
                startActivity(intent);
            }
        });

        rectangle2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(chomedashboard.this, appointmentspage.class);
                intent.putExtra("USER_DATA", vet);
                startActivity(intent);
            }
        });

        rectangle3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(chomedashboard.this, vaccinationpage.class);
                intent.putExtra("USER_DATA", vet);
                startActivity(intent);
            }
        });

        rectangle4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(chomedashboard.this, consolidatedsummary.class);
                intent.putExtra("USER_DATA", vet);
                startActivity(intent);
            }
        });

        rectangle5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(chomedashboard.this, certreq.class);
                intent.putExtra("USER_DATA", vet);

                startActivity(intent);
            }
        });

        //Navigation Handle
        ImageView home, calendar, files, profile, pets;
        home = findViewById(R.id.iv_home);
        calendar = findViewById(R.id.iv_calendar);
        files = findViewById((R.id.iv_files));
        profile = findViewById(R.id.iv_userprofile);
        pets = findViewById(R.id.iv_pets);

        //Link to navigation buttons
        pets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(chomedashboard.this, clinicpets.class);
                intent.putExtra("USER_DATA", vet);
                Log.d("CHECK VET TO CLINICS", "VET NAME: " + vet.getFirstName() + "VET ID: " + vet.getVetID());
                startActivity(intent);
                finish();
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(chomedashboard.this, chomedashboard.class);
                intent.putExtra("USER_DATA", vet);
                startActivity(intent);
                finish();
            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(chomedashboard.this, clinicprofilepage.class);
                intent.putExtra("USER_DATA", vet);
                startActivity(intent);
                finish();
            }
        });

        files.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(chomedashboard.this, consolidatedsummary.class);
                intent.putExtra("USER_DATA", vet);
                intent.putExtra("IS_PET_OWNER", false);
                startActivity(intent);
                finish();
            }
        });

        calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(chomedashboard.this, appointmentspage.class);
                intent.putExtra("USER_DATA", vet);
                intent.putExtra("IS_PET_OWNER", false);
                startActivity(intent);
                finish();
            }
        });
    }

    private void loadOverviewEntries() {
        overviewentryList.add(new overviewentry(R.drawable.cat2, "9:00 AM", "Casper", "Ashley Corpuz", "General Check-Up", "Dr. QuackQuack"));
        overviewentryList.add(new overviewentry(R.drawable.cat2, "9:00 AM", "Casper", "Ashley Corpuz", "General Check-Up", "Dr. QuackQuack"));
    }
}
