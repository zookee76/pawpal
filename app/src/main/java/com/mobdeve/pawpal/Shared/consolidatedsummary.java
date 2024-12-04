package com.mobdeve.pawpal.Shared;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mobdeve.pawpal.Adapter.consolidatedrecordadapter;
import com.mobdeve.pawpal.Database.DBHelper;
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
    private DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consolidatedsummary);

        db = new DBHelper(getApplicationContext());

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
        searchView = findViewById(R.id.searchbartext);

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

            FloatingActionButton addPets = findViewById(R.id.btn_addrecord);
            addPets.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(consolidatedsummary.this, com.mobdeve.pawpal.ClinicOwner.addRecords.class);
                    startActivityForResult(intent, 1);
                    //startActivity(intent);
                }
            });

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
        DBHelper dbHelper = new DBHelper(this);
        Cursor cursor = dbHelper.getAllConsolidatedRecords();

        // Loop through the cursor and add records to the list
        if (cursor != null && cursor.moveToFirst()) {
            do {
                String title = cursor.getString(cursor.getColumnIndex(DBHelper.RECORD_TITLE));
                String docType = cursor.getString(cursor.getColumnIndex(DBHelper.DOC_TYPE));
                String docDate = cursor.getString(cursor.getColumnIndex(DBHelper.DOC_DATE));
                String veterinarian = cursor.getString(cursor.getColumnIndex(DBHelper.VETERINARIAN));
                String file = cursor.getString(cursor.getColumnIndex(DBHelper.RECORD_FILE));

                // Add records to the list
                recordsList.add(new consolidatedrecords(title, docType, docDate, veterinarian, file));
            } while (cursor.moveToNext());

            cursor.close();
            Log.d("LOAD_SUMMARIES", "Number of records in the list: " + recordsList.size());
        }
    }

    private void loadClinicConsoSum() {
        Cursor cursor = db.getAllConsolidatedRecords();

        // Loop through the cursor and add records to the list
        if (cursor != null && cursor.moveToFirst()) {
            do {
                String title = cursor.getString(cursor.getColumnIndex(DBHelper.RECORD_TITLE));
                String docType = cursor.getString(cursor.getColumnIndex(DBHelper.DOC_TYPE));
                String docDate = cursor.getString(cursor.getColumnIndex(DBHelper.DOC_DATE));
                String veterinarian = cursor.getString(cursor.getColumnIndex(DBHelper.VETERINARIAN));
                String file = cursor.getString(cursor.getColumnIndex(DBHelper.RECORD_FILE));

                // Add records to the list
                recordsList.add(new consolidatedrecords(title, docType, docDate, veterinarian, file));
            } while (cursor.moveToNext());

            cursor.close();
            Log.d("LOAD_SUMMARIES", "Number of records in the list: " + recordsList.size());
        }
    }
}
