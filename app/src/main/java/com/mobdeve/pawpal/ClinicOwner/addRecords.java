package com.mobdeve.pawpal.ClinicOwner;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.mobdeve.pawpal.Database.DBHelper;
import com.mobdeve.pawpal.Model.consolidatedrecords;  // Assuming records model to store various records
import com.mobdeve.pawpal.R;
import com.mobdeve.pawpal.Shared.appointmentspage;
import com.mobdeve.pawpal.Shared.consolidatedsummary;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class addRecords extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private DBHelper db;
    private Spinner docTypeSpinner;  // To select document type

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_addrecords);  // Updated layout name

        db = new DBHelper(getApplicationContext());

        // Initialize document type spinner and handle selection
        docTypeSpinner = findViewById(R.id.add_record_type_spinner);
        docTypeSpinner.setOnItemSelectedListener(this);
        populateDocTypes();  // Populating with document types (e.g., Appointment, Health Record, etc.)

        // Back button handling
        ImageView backImg = findViewById(R.id.iv_back);
        TextView backTxt = findViewById(R.id.tv_back);
        View.OnClickListener backListnr = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(addRecords.this, clinicpets.class);
                startActivity(intent);
                finish();
            }
        };

        backImg.setOnClickListener(backListnr);
        backTxt.setOnClickListener(backListnr);

        // Navigation setup (same as before)
        setupNavigation();

        // User inputs for the record
        EditText recordTitle = findViewById(R.id.add_title);
        EditText recordVeterinarian = findViewById(R.id.add_vet);
        EditText recordDate = findViewById(R.id.add_date);
        EditText recordFile = findViewById(R.id.add_filepath);

        Button addRecordButton = findViewById(R.id.btn_addrecord);

        // Date Picker for record date
        recordDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(addRecords.this,
                        (view, selectedYear, selectedMonth, selectedDay) -> {
                            String selectedDate = selectedDay + "/" + (selectedMonth + 1) + "/" + selectedYear;
                            recordDate.setText(selectedDate);  // Display selected date
                        }, year, month, day);
                datePickerDialog.show();
            }
        });

        // Add record button click
        addRecordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = recordTitle.getText().toString().trim();
                String veterinarian = recordVeterinarian.getText().toString().trim();
                String date = recordDate.getText().toString().trim();
                String file = recordFile.getText().toString().trim();
                String docType = docTypeSpinner.getSelectedItem().toString().trim();

                if (title.isEmpty() || veterinarian.isEmpty() || date.isEmpty() || file.isEmpty() || docType.isEmpty()) {
                    Toast.makeText(addRecords.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                } else
                {
                    // Creating new record object with the fields
                    consolidatedrecords newRecord = new consolidatedrecords(title, docType, date, veterinarian, file);
                    long newRecordID = db.addRecord(newRecord);  // Assuming a method to add records
                    Log.d("NEWRECORD", "Record ID: " + newRecordID);

                    if (newRecordID > 0) {
                    Toast.makeText(addRecords.this, "Record added successfully!", Toast.LENGTH_SHORT).show();
                    Intent resultIntent = new Intent();
                    setResult(RESULT_OK, resultIntent);

                    Log.d("ADDRECORD_RESULT", "Result: " + newRecordID);
                    Intent intent = new Intent(addRecords.this, clinicpets.class);
                    startActivity(intent);
                    } else {
                        Toast.makeText(addRecords.this, "Failed to add record.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        // Handle selection change for document type
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        // Handle when no item is selected
    }

    private void populateDocTypes() {
        List<String> docTypes = new ArrayList<>();
        docTypes.add("Appointment");
        docTypes.add("Health Record");
        docTypes.add("Lab Results");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, docTypes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        docTypeSpinner.setAdapter(adapter);
    }

    // Setup navigation links
    private void setupNavigation() {
        ImageView home, calendar, files, profile, pets;
        home = findViewById(R.id.iv_home);
        calendar = findViewById(R.id.iv_calendar);
        files = findViewById((R.id.iv_files));
        profile = findViewById(R.id.iv_userprofile);
        pets = findViewById(R.id.iv_pets);

        // Navigation links
        home.setOnClickListener(view -> {
            Intent intent = new Intent(addRecords.this, chomedashboard.class);
            startActivity(intent);
            finish();
        });

        profile.setOnClickListener(view -> {
            Intent intent = new Intent(addRecords.this, clinicprofilepage.class);
            startActivity(intent);
            finish();
        });

        files.setOnClickListener(view -> {
            Intent intent = new Intent(addRecords.this, consolidatedsummary.class);
            startActivity(intent);
            finish();
        });

        calendar.setOnClickListener(view -> {
            Intent intent = new Intent(addRecords.this, appointmentspage.class);
            startActivity(intent);
            finish();
        });

        pets.setOnClickListener(view -> {
            Intent intent = new Intent(addRecords.this, clinicpets.class);
            startActivity(intent);
            finish();
        });
    }
}
