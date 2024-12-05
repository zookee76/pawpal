package com.mobdeve.pawpal.ClinicOwner;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Locale;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mobdeve.pawpal.Adapter.editscheduleAdapter;
import com.mobdeve.pawpal.Adapter.scheduleAdapter;
import com.mobdeve.pawpal.Database.DBHelper;
import com.mobdeve.pawpal.Model.appointment;
import com.mobdeve.pawpal.Model.clinicVet;
import com.mobdeve.pawpal.Model.petOwners;
import com.mobdeve.pawpal.Model.pets;
import com.mobdeve.pawpal.R;
import com.mobdeve.pawpal.Shared.appointmentspage;
import com.mobdeve.pawpal.Shared.consolidatedsummary;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class editschedules extends AppCompatActivity {

    private DBHelper DB;
    private pets petData;
    private clinicVet vetData;
    private petOwners ownerData;
    private TextView petName;
    private RecyclerView rvApp;
    private FloatingActionButton addAppointment;
    private editscheduleAdapter adapter;
    private List<appointment> appointmentList;
    private CardView cvAddApp;
    private Calendar calendar;
    private EditText editapptype, editdatetime;
    private appointment newApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_cliniceditschedule);
        // Get Data
        DB = new DBHelper(getApplicationContext());
        Intent intent = getIntent();
        petData = intent.getParcelableExtra("PET_DATA");
        vetData = intent.getParcelableExtra("VET_DATA");
        long petID = petData.getID();
        ownerData = DB.getPetOwner(petID);


        // ELEMENTS
        ImageView petImage = findViewById(R.id.petImage);
        petName = findViewById(R.id.petName);
        petName.setText(petData.getName());
        addAppointment = findViewById(R.id.add_appointment);
        cvAddApp = findViewById(R.id.cv_appointment);
        cvAddApp.setVisibility(View.GONE);
        editdatetime = findViewById(R.id.text_datetime);
        editapptype = findViewById(R.id.text_apptype);
        Button addprofile = findViewById(R.id.btn_addprofile);
        calendar = Calendar.getInstance();
        editdatetime.setInputType(InputType.TYPE_NULL);
        editdatetime.setOnClickListener(v -> showDatePicker());

        addAppointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cvAddApp.getVisibility() == View.VISIBLE) {
                    cvAddApp.setVisibility(View.GONE);
                } else {
                    cvAddApp.setVisibility(View.VISIBLE);
                    addprofile.setVisibility(View.VISIBLE);
                    addprofile.setClickable(true);
                    }
            }
        });

        addprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String apptype = editapptype.getText().toString().trim();
                String appdatetime = editdatetime.getText().toString();
                String petname = petData.getName(),
                        ownerName = ownerData.getFullname(),
                        vetName = vetData.getFirstName() + " " + vetData.getLastName(),
                        status = "Scheduled";
                long ownerID = ownerData.getID(),
                        vetID = vetData.getVetID(),
                        petID = petData.getID();

                if (apptype.isEmpty() || appdatetime.isEmpty()){
                    Toast.makeText(editschedules.this, "Please fill in all fields.", Toast.LENGTH_SHORT).show();
                    return;
                }
                newApp = new appointment(petID,ownerID, vetID, petname, ownerName, apptype, appdatetime, vetName, status);

                Log.d("CHECK NEW APP", "NEW APP: " + newApp.toString());

                if (newApp != null) {
                    long newAppID = DB.addAppointment(newApp);

                    if (newAppID > 0) {
                        Toast.makeText(editschedules.this, "Appointment added successfully!", Toast.LENGTH_SHORT).show();
                        Intent resultIntent = new Intent();
                        setResult(RESULT_OK, resultIntent);

                        Log.d("REGISTRATION_RESULT", "Result: " + newAppID);
                        Intent intent = new Intent(editschedules.this, cschedules.class);
                        intent.putExtra("VET_DATA", vetData);
                        intent.putExtra("PET_DATA", petData);
                        startActivity(intent);
                    } else {
                        Toast.makeText(editschedules.this, "Failed to add appointment.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(editschedules.this, "Failed to add appointment. Invalid inputs", Toast.LENGTH_SHORT).show();
                }
            }
        });

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

        rvApp = findViewById(R.id.rv_appointments);
        rvApp.setLayoutManager(new LinearLayoutManager(this));

        appointmentList = new ArrayList<>();
        loadallAppointments();

        adapter = new editscheduleAdapter(this, appointmentList, DB);
        rvApp.setAdapter(adapter);

        //Back Handle
        ImageView backImg = findViewById(R.id.iv_back);
        TextView backTxt = findViewById(R.id.tv_back);
        View.OnClickListener backListnr = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        };
        backImg.setOnClickListener(backListnr);
        backTxt.setOnClickListener(backListnr);

        // Navigations
        TextView about, meddocu, meddiet, schedules;
        ImageView edit;

        about = findViewById(R.id.aboutcategory);
        meddocu = findViewById(R.id.medicaldocscategory);
        meddiet = findViewById(R.id.medicationdietcategory);
        schedules = findViewById(R.id.schedulecategory);

        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(editschedules.this, cabout.class);
                // handle data here
                startActivity(intent);
                finish();
            }
        });

        meddocu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(editschedules.this, cmedicaldocs.class);
                // handle data here
                startActivity(intent);
                finish();
            }
        });

        meddiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(editschedules.this, cmedicationdiet.class);
                // handle data here
                startActivity(intent);
                finish();
            }
        });

        schedules.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(editschedules.this, cschedules.class);
                // handle data here
                startActivity(intent);
                finish();
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
                Intent intent = new Intent(editschedules.this, clinicpets.class);
                startActivity(intent);
                finish();
            }
        });
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(editschedules.this, chomedashboard.class);
                startActivity(intent);
                finish();
            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(editschedules.this, clinicprofilepage.class);
                startActivity(intent);
                finish();
            }
        });

        files.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(editschedules.this, consolidatedsummary.class);
                intent.putExtra("IS_PET_OWNER", false);
                startActivity(intent);
                finish();
            }
        });

        calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(editschedules.this, appointmentspage.class);
                intent.putExtra("IS_PET_OWNER", false);
                startActivity(intent);
                finish();
            }
        });

        // Logic to handle inputs

    }

    private void loadallAppointments(){
        appointmentList.clear();
        long petID = petData.getID();
        if (DB != null) {
            List<appointment> app = DB.getPetAppointment(petID);
            Log.d("APPSIZE", "Size: " + app.size() + " PETID: " + petID);

            if (app != null && !app.isEmpty()) {
                appointmentList.addAll(app);
            }
        } else {
            Log.e("APPSIZE", "appointment DB is not initialized.");
        }

        if(adapter != null){
            adapter.notifyDataSetChanged();

        }
    }

    private void showDatePicker() {
        // Get the current date
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        // Show DatePickerDialog
        new DatePickerDialog(
                this,
                (view, selectedYear, selectedMonth, selectedDay) -> {
                    // Update the Calendar object with the selected date
                    calendar.set(Calendar.YEAR, selectedYear);
                    calendar.set(Calendar.MONTH, selectedMonth);
                    calendar.set(Calendar.DAY_OF_MONTH, selectedDay);

                    // After selecting a date, show TimePickerDialog
                    showTimePicker();
                },
                year,
                month,
                day
        ).show();
    }

    private void showTimePicker() {
        // Get the current time
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        // Show TimePickerDialog
        new TimePickerDialog(
                this,
                (view, selectedHour, selectedMinute) -> {
                    // Update the Calendar object with the selected time
                    calendar.set(Calendar.HOUR_OF_DAY, selectedHour);
                    calendar.set(Calendar.MINUTE, selectedMinute);

                    // Format the date and time and display in EditText
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault());
                    editdatetime.setText(sdf.format(calendar.getTime()));
                },
                hour,
                minute,
                true // Use 24-hour format
        ).show();
    }
}