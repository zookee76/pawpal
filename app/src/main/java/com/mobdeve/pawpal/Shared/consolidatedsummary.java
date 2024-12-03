package com.mobdeve.pawpal.Shared;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mobdeve.pawpal.Adapter.consolidatedrecordadapter;
import com.mobdeve.pawpal.Model.consolidatedrecords;
import com.mobdeve.pawpal.PetOwner.petprofilepage;
import com.mobdeve.pawpal.R;
import com.mobdeve.pawpal.ClinicOwner.chomedashboard;
import com.mobdeve.pawpal.ClinicOwner.clinicpets;
import com.mobdeve.pawpal.ClinicOwner.clinicprofilepage;
import com.mobdeve.pawpal.PetOwner.phomedashboard;

import java.util.ArrayList;
import java.util.List;

public class consolidatedsummary extends AppCompatActivity {

    private RecyclerView rvConsolidatedRecordList;
    private consolidatedrecordadapter adapter;
    private List<consolidatedrecords> recordsList;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consolidatedsummary);

        // Back Handle
        ImageView backImg = findViewById(R.id.iv_back);
        TextView backTxt = findViewById(R.id.tv_back);

        // Navigation Handle
        ImageView home, calendar, files, profile, pets;
        home = findViewById(R.id.iv_home);
        pets = findViewById(R.id.iv_pets);
        calendar = findViewById(R.id.iv_calendar);
        files = findViewById(R.id.iv_files);
        profile = findViewById(R.id.iv_userprofile);

        // Search View setup
        searchView = findViewById(R.id.searchbartext);  // Assuming search_view is added in your layout XML.

        // Recyclerview handle
        rvConsolidatedRecordList = findViewById(R.id.rv_consolrecordscard);
        rvConsolidatedRecordList.setLayoutManager(new LinearLayoutManager(this));
        recordsList = new ArrayList<>();

        rvConsolidatedRecordList.setVisibility(View.GONE);

        boolean isPetOwner = getIntent().getBooleanExtra("IS_PET_OWNER", false);

        if (isPetOwner) {
            loadConsolidatedSummaries();
            adapter = new consolidatedrecordadapter(this, recordsList);
            rvConsolidatedRecordList.setAdapter(adapter);

            // Search functionality
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    adapter.filterList(newText); // Call the filter method in adapter
                    return false;
                }
            });

            // back handle
            View.OnClickListener backListnr = new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(consolidatedsummary.this, phomedashboard.class);
                    startActivity(intent);
                    finish();
                }
            };
            backImg.setOnClickListener(backListnr);
            backTxt.setOnClickListener(backListnr);

            // Link to navigation buttons
            home.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(consolidatedsummary.this, phomedashboard.class);
                    startActivity(intent);
                    finish();
                }
            });

            pets.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(consolidatedsummary.this, petspage.class);
                    intent.putExtra("IS_PET_OWNER", true);
                    startActivity(intent);
                    finish();
                }
            });

            profile.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(consolidatedsummary.this, petprofilepage.class);
                    startActivity(intent);
                    finish();
                }
            });

            files.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(consolidatedsummary.this, consolidatedsummary.class);
                    intent.putExtra("IS_PET_OWNER", true);
                    startActivity(intent);
                    finish();
                }
            });

            calendar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(consolidatedsummary.this, appointmentspage.class);
                    intent.putExtra("IS_PET_OWNER", true);
                    startActivity(intent);
                    finish();
                }
            });
        } else {
            loadClinicConsoSum();
            adapter = new consolidatedrecordadapter(this, recordsList, isPetOwner);
            rvConsolidatedRecordList.setAdapter(adapter);

            // Search functionality
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    adapter.filterList(newText); // Call the filter method in adapter
                    return false;
                }
            });

            // back handle
            View.OnClickListener backListnr = new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(consolidatedsummary.this, chomedashboard.class);
                    startActivity(intent);
                    finish();
                }
            };
            backImg.setOnClickListener(backListnr);
            backTxt.setOnClickListener(backListnr);

            // Link to navigation buttons
            pets.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(consolidatedsummary.this, clinicpets.class);
                    startActivity(intent);
                    finish();
                }
            });

            home.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(consolidatedsummary.this, chomedashboard.class);
                    startActivity(intent);
                    finish();
                }
            });

            profile.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(consolidatedsummary.this, clinicprofilepage.class);
                    startActivity(intent);
                    finish();
                }
            });

            files.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(consolidatedsummary.this, consolidatedsummary.class);
                    intent.putExtra("IS_PET_OWNER", false);
                    startActivity(intent);
                    finish();
                }
            });

            calendar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(consolidatedsummary.this, appointmentspage.class);
                    intent.putExtra("IS_PET_OWNER", false);
                    startActivity(intent);
                    finish();
                }
            });
        }
    }

    private void loadConsolidatedSummaries() {
        recordsList.add(new consolidatedrecords(1, "Title", "Lab Results", "19/10/2024", "Dr. QuackQuack", "casper_results"));
        recordsList.add(new consolidatedrecords(4, "Title222", "Lab Results", "19/10/2024", "Dr. QuackQuack", "meester_results"));
    }

    private void loadClinicConsoSum() {
        recordsList.add(new consolidatedrecords(2, "Title", "Lab Results", "19/10/2024", "Dr. QuackQuack", "casper_results"));
        recordsList.add(new consolidatedrecords(3, "Title", "Lab Results", "19/10/2024", "Dr. QuackQuack", "casper_results"));
    }
}
