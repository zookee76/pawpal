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

import androidx.appcompat.app.AppCompatActivity;

import com.mobdeve.pawpal.Database.DBHelper;
import com.mobdeve.pawpal.Model.appointment;
import com.mobdeve.pawpal.Model.petOwners;
import com.mobdeve.pawpal.Model.pets;
import com.mobdeve.pawpal.R;
import com.mobdeve.pawpal.Shared.appointmentspage;
import com.mobdeve.pawpal.Shared.consolidatedsummary;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class addAppointments extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private DBHelper db;
    private Spinner petSpinner, ownerSpinner, appTypeSpinner, appStatusSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addappointments);

        db = new DBHelper(getApplicationContext());

        // Initialize spinners
        petSpinner = findViewById(R.id.pet_name_spinner);
        ownerSpinner = findViewById(R.id.add_owner_name_spinner);
        appTypeSpinner = findViewById(R.id.apptype_spinner);
        appStatusSpinner = findViewById(R.id.add_appointment_status_spinner);

        petSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedPetName = (String) parent.getItemAtPosition(position);
                Long petID = petMap.get(selectedPetName); // Retrieve petID using pet name
                if (petID != null) {
                    Log.d("SelectedPet", "Pet Name: " + selectedPetName + ", Pet ID: " + petID);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Handle no selection if needed
            }
        });

        ownerSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedOwnerName = (String) parent.getItemAtPosition(position);
                Long ownerID = ownerMap.get(selectedOwnerName); // Retrieve ownerID using owner name
                if (ownerID != null) {
                    Log.d("SelectedOwner", "Owner Name: " + selectedOwnerName + ", Owner ID: " + ownerID);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Handle no selection if needed
            }
        });

        appTypeSpinner.setOnItemSelectedListener(this);
        appStatusSpinner.setOnItemSelectedListener(this);

        populatePetNames();
        populateOwnerNames();
        populateAppTypes();
        populateStatusTypes();

        // Back button handling
        findViewById(R.id.iv_back).setOnClickListener(this::goBack);
        findViewById(R.id.tv_back).setOnClickListener(this::goBack);

        // Initialize inputs and date picker
        EditText veterinarianInput = findViewById(R.id.appadd_vet);
        EditText appDateInput = findViewById(R.id.appadd_date);
        appDateInput.setOnClickListener(view -> showDatePicker(appDateInput));

        Button addAppointmentButton = findViewById(R.id.btn_addappointment);
        addAppointmentButton.setOnClickListener(view -> {
            addAppointment(veterinarianInput, appDateInput);
        });

        // Setup navigation links
        setupNavigation();
    }

    private void goBack(View view) {
        Intent intent = new Intent(this, clinicpets.class);
        startActivity(intent);
        finish();
    }

    private void showDatePicker(EditText dateInput) {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        new DatePickerDialog(this, (view, selectedYear, selectedMonth, selectedDay) -> {
            String selectedDate = selectedDay + "/" + (selectedMonth + 1) + "/" + selectedYear;
            dateInput.setText(selectedDate);
        }, year, month, day).show();
    }

    private void addAppointment(EditText veterinarianInput, EditText appDateInput) {
        String veterinarian = veterinarianInput.getText().toString().trim();
        String date = appDateInput.getText().toString().trim();
        String petName = getSelectedItem(petSpinner);
        String ownerName = getSelectedItem(ownerSpinner);
        String appointmentType = getSelectedItem(appTypeSpinner);
        String appointmentStatus = getSelectedItem(appStatusSpinner);

        // Retrieve IDs using HashMaps
        Long petID = petMap.get(petName);
        Long ownerID = ownerMap.get(ownerName);

        // Validate inputs
        if (veterinarian.isEmpty() || date.isEmpty() || petName.isEmpty() || ownerName.isEmpty() ||
                appointmentType.isEmpty() || appointmentStatus.isEmpty() || petID == null || ownerID == null) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        Log.d("ADD_APPOINTMENT", "Veterinarian: " + veterinarian);
        Log.d("ADD_APPOINTMENT", "Date: " + date);
        Log.d("ADD_APPOINTMENT", "Pet Name: " + petName + " (ID: " + petID + ")");
        Log.d("ADD_APPOINTMENT", "Owner Name: " + ownerName + " (ID: " + ownerID + ")");
        Log.d("ADD_APPOINTMENT", "Appointment Type: " + appointmentType);
        Log.d("ADD_APPOINTMENT", "Appointment Status: " + appointmentStatus);

        appointment newAppointment = new appointment(
                petID,
                ownerID,
                1,
                petName,
                ownerName,
                appointmentType,
                date,
                veterinarian,
                appointmentStatus
        );

        long newAppointmentID = db.addAppointment(newAppointment);

        if (newAppointmentID > 0) {
            Toast.makeText(this, "Appointment added successfully!", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, clinicpets.class));
            finish();
        } else {
            Toast.makeText(this, "Failed to add appointment.", Toast.LENGTH_SHORT).show();
        }
    }


    private String getSelectedItem(Spinner spinner) {
        return spinner.getSelectedItem() != null ? spinner.getSelectedItem().toString().trim() : "";
    }

    private Map<String, Long> petMap = new HashMap<>();

    private void populatePetNames() {
        List<pets> petsList = db.getAllPets();
        List<String> petNames = new ArrayList<>();

        for (pets pet : petsList) {
            petNames.add(pet.getName());
            petMap.put(pet.getName(), pet.getID());
        }

        setupSpinner(petSpinner, petNames, "No Pets Available");
    }

    private Map<String, Long> ownerMap = new HashMap<>();

    private void populateOwnerNames() {
        List<petOwners> ownersList = db.getPetOwners();
        List<String> ownerNames = new ArrayList<>();

        for (petOwners owner : ownersList) {
            String fullName = owner.getFname() + " " + owner.getLname();
            ownerNames.add(fullName);
            ownerMap.put(fullName, owner.getID());
        }

        setupSpinner(ownerSpinner, ownerNames, "No Owners Available");
    }

    private void populateAppTypes() {
        List<String> appTypes = List.of("General Checkup", "Lab Testing", "Extraction");
        setupSpinner(appTypeSpinner, appTypes, "No Types Available");
    }

    private void populateStatusTypes() {
        List<String> statusTypes = List.of("Pending", "Completed", "Canceled");
        setupSpinner(appStatusSpinner, statusTypes, "No Status Available");
    }

    private void setupSpinner(Spinner spinner, List<String> items, String defaultText) {
        if (items.isEmpty()) {
            items = List.of(defaultText);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    private void setupNavigation() {
        findViewById(R.id.iv_home).setOnClickListener(view -> navigateTo(chomedashboard.class));
        findViewById(R.id.iv_userprofile).setOnClickListener(view -> navigateTo(clinicprofilepage.class));
        findViewById(R.id.iv_files).setOnClickListener(view -> navigateTo(consolidatedsummary.class));
        findViewById(R.id.iv_calendar).setOnClickListener(view -> navigateTo(appointmentspage.class));
        findViewById(R.id.iv_pets).setOnClickListener(view -> navigateTo(clinicpets.class));
    }

    private void navigateTo(Class<?> targetActivity) {
        Intent intent = new Intent(this, targetActivity);
        startActivity(intent);
        finish();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {}

    @Override
    public void onNothingSelected(AdapterView<?> parent) {}
}
