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

import com.mobdeve.pawpal.Model.pets;
import com.mobdeve.pawpal.Model.petOwners;
import com.mobdeve.pawpal.Model.appointment;

public class addAppointments extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private DBHelper db;
    private Spinner appTypeSpinner;  // To select document type
    private Spinner appStatusSpinner, petSpinner, ownerSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_addappointments);  // Updated layout name

        db = new DBHelper(getApplicationContext());

        // Initialize document type spinner and handle selection
        petSpinner = findViewById(R.id.pet_name_spinner);
        petSpinner.setOnItemSelectedListener(this);
        populatePetNames();

        // Initialize document type spinner and handle selection
        ownerSpinner = findViewById(R.id.add_owner_name_spinner);
        ownerSpinner.setOnItemSelectedListener(this);
        populateOwnerNames();

        // Initialize document type spinner and handle selection
        appTypeSpinner = findViewById(R.id.apptype_spinner);
        appTypeSpinner.setOnItemSelectedListener(this);
        populateAppTypes();

        // Initialize document type spinner and handle selection
        appStatusSpinner = findViewById(R.id.add_appointment_status_spinner);
        appStatusSpinner.setOnItemSelectedListener(this);
        populateStatusTypes();

        // Back button handling
        ImageView backImg = findViewById(R.id.iv_back);
        TextView backTxt = findViewById(R.id.tv_back);
        View.OnClickListener backListnr = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(addAppointments.this, clinicpets.class);
                startActivity(intent);
                finish();
            }
        };

        backImg.setOnClickListener(backListnr);
        backTxt.setOnClickListener(backListnr);

        // Navigation setup (same as before)
        setupNavigation();

        // User inputs for the record
        EditText recordVeterinarian = findViewById(R.id.appadd_vet);
        EditText appDate = findViewById(R.id.appadd_date);
        Button addAppointmentButton = findViewById(R.id.btn_addappointment);

        // Date Picker for record date
        appDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(addAppointments.this,
                        (view, selectedYear, selectedMonth, selectedDay) -> {
                            String selectedDate = selectedDay + "/" + (selectedMonth + 1) + "/" + selectedYear;
                            appDate.setText(selectedDate);  // Display selected date
                        }, year, month, day);
                datePickerDialog.show();
            }
        });

        // Add record button click
        addAppointmentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get values from user inputs
                String veterinarian = recordVeterinarian.getText().toString().trim();
                String date = appDate.getText().toString().trim();
                String petName = petSpinner.getSelectedItem() != null ? petSpinner.getSelectedItem().toString().trim() : "";
                String ownerName = ownerSpinner.getSelectedItem() != null ? ownerSpinner.getSelectedItem().toString().trim() : "";
                String appointmentType = appTypeSpinner.getSelectedItem() != null ? appTypeSpinner.getSelectedItem().toString().trim() : "";
                String appointmentStatus = appStatusSpinner.getSelectedItem() != null ? appStatusSpinner.getSelectedItem().toString().trim() : "";

                // Validate inputs
                if (veterinarian.isEmpty() || date.isEmpty() || petName.isEmpty() || ownerName.isEmpty() ||
                        appointmentType.isEmpty() || appointmentStatus.isEmpty()) {
                    Toast.makeText(addAppointments.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Log the values for debugging
                Log.d("ADD_APPOINTMENT", "Veterinarian: " + veterinarian);
                Log.d("ADD_APPOINTMENT", "Date: " + date);
                Log.d("ADD_APPOINTMENT", "Pet Name: " + petName);
                Log.d("ADD_APPOINTMENT", "Owner Name: " + ownerName);
                Log.d("ADD_APPOINTMENT", "Appointment Type: " + appointmentType);
                Log.d("ADD_APPOINTMENT", "Appointment Status: " + appointmentStatus);

                // Create new appointment object and add to the database
                appointment newAppointment = new appointment(petName, ownerName, veterinarian, date, appointmentType, appointmentStatus);
                long newAppointmentID = db.addAppointment(newAppointment); // Assuming a method to add appointments

                // Handle result of database operation
                if (newAppointmentID > 0) {
                    Toast.makeText(addAppointments.this, "Appointment added successfully!", Toast.LENGTH_SHORT).show();
                    Log.d("NEW_APPOINTMENT", "Appointment ID: " + newAppointmentID);

                    // Redirect to the appropriate page or update UI
                    Intent intent = new Intent(addAppointments.this, clinicpets.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(addAppointments.this, "Failed to add appointment.", Toast.LENGTH_SHORT).show();
                    Log.e("ADD_APPOINTMENT_ERROR", "Failed to insert appointment into database");
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

    private void populatePetNames() {
        List<pets> petsList = db.getAllPets();
        Log.d("populatePetNames", "Total pets fetched from database: " + petsList.size());

        List<String> petNames = new ArrayList<>();
        for (pets pet : petsList) {
            if (pet != null && pet.getName() != null) {
                petNames.add(pet.getName());
            } else {
                Log.w("populatePetNames", "Found a null pet or pet with null name");
            }
        }
        Log.d("populatePetNames", "Total valid pet names extracted: " + petNames.size());

        if (!petNames.isEmpty()) {
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, petNames);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            appTypeSpinner.setAdapter(adapter);
        } else {
            List<String> defaultList = new ArrayList<>();
            defaultList.add("No Pets Available");
            ArrayAdapter<String> defaultAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, defaultList);
            defaultAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            appTypeSpinner.setAdapter(defaultAdapter);
        }
    }

    private void populateOwnerNames() {
        List<petOwners> ownersList = db.getPetOwners();
        Log.d("populateOwnerNames", "Total owners fetched from database: " + ownersList.size());

        // Extract full names into a new list
        List<String> ownerNames = new ArrayList<>();
        for (petOwners owner : ownersList) {
            if (owner != null && owner.getFname() != null && owner.getLname() != null) {
                String fullName = owner.getFname() + " " + owner.getLname();
                ownerNames.add(fullName);
            } else {
                Log.w("populateOwnerNames", "Found a null owner or owner with null name components");
            }
        }
        Log.d("populateOwnerNames", "Total valid owner names extracted: " + ownerNames.size());

        if (!ownerNames.isEmpty()) {
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, ownerNames);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            appTypeSpinner.setAdapter(adapter);
        } else {
            List<String> defaultList = new ArrayList<>();
            defaultList.add("No Owners Available");
            ArrayAdapter<String> defaultAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, defaultList);
            defaultAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            appTypeSpinner.setAdapter(defaultAdapter);
        }
    }


    private void populateAppTypes() {
        List<String> docTypes = new ArrayList<>();
        docTypes.add("General Checkup");
        docTypes.add("Lab Testing");
        docTypes.add("Extraction");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, docTypes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        appTypeSpinner.setAdapter(adapter);
    }

    private void populateStatusTypes() {
        List<String> statusTypes = new ArrayList<>();
        statusTypes.add("Pending");
        statusTypes.add("Completed");
        statusTypes.add("Canceled");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, statusTypes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        appTypeSpinner.setAdapter(adapter);
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
            Intent intent = new Intent(addAppointments.this, chomedashboard.class);
            startActivity(intent);
            finish();
        });

        profile.setOnClickListener(view -> {
            Intent intent = new Intent(addAppointments.this, clinicprofilepage.class);
            startActivity(intent);
            finish();
        });

        files.setOnClickListener(view -> {
            Intent intent = new Intent(addAppointments.this, consolidatedsummary.class);
            startActivity(intent);
            finish();
        });

        calendar.setOnClickListener(view -> {
            Intent intent = new Intent(addAppointments.this, appointmentspage.class);
            startActivity(intent);
            finish();
        });

        pets.setOnClickListener(view -> {
            Intent intent = new Intent(addAppointments.this, clinicpets.class);
            startActivity(intent);
            finish();
        });
    }
}
