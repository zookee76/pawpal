package com.mobdeve.pawpal.ClinicOwner;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.mobdeve.pawpal.Database.DBHelper;
import com.mobdeve.pawpal.Model.clinicVet;
import com.mobdeve.pawpal.R;
import com.mobdeve.pawpal.Shared.appointmentspage;
import com.mobdeve.pawpal.Shared.consolidatedsummary;
import com.mobdeve.pawpal.Welcome.welcomerole;

public class clinicprofilepage extends AppCompatActivity {
    private DBHelper DB;
    private long vetID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clinicprofile);

        // Get Data
        DB = new DBHelper(getApplicationContext());
        Intent intent = getIntent();
        clinicVet vet = intent.getParcelableExtra("USER_DATA");
        if(vet != null){
            String firstName = vet.getFirstName(),
                    lastName = vet.getLastName();
            String fullname = firstName + " " + lastName;
            String email = vet.getEmailAdd();
            String contact = vet.getContactNo();

            TextView tvName = findViewById(R.id.tv_userfullname);
            TextView tvEmail = findViewById(R.id.tv_useremail);
            TextView tvContact = findViewById(R.id.tv_contactno);
            vetID = vet.getVetID();
            tvName.setText(fullname);
            tvEmail.setText(email);
            tvContact.setText(contact);

            Log.d("CHECK clinicpets", "VET NAME: " + vet.getFirstName() + "VET ID: " + vet.getVetID());

        }else{
            Log.d("CHECK clinicpets", "NO VET");
        }
        //Back Handle
        ImageView backImg = findViewById(R.id.iv_back);
        TextView backTxt = findViewById(R.id.tv_back);

        View.OnClickListener backListnr = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(clinicprofilepage.this, chomedashboard.class);
                intent.putExtra("USER_DATA", vet);
                startActivity(intent);
                finish();
            }
        };

        // Logout Handle
        TextView logout = findViewById(R.id.tv_logout);
        ImageView logoutimg =  findViewById(R.id.iv_logout);

        View.OnClickListener logoutLstnr = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(clinicprofilepage.this, welcomerole.class);
                startActivity(intent);
                finish();
            }
        };

        // Edit Handle
        ImageView editprofile = findViewById(R.id.iv_arrowprofile);
        ImageView changepw = findViewById(R.id.iv_arrowpw);

        editprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(clinicprofilepage.this, ceditclinicprofile.class);
                intent.putExtra("USER_DATA", vet);
                long vetID = vet.getVetID();
                Log.d("CHECK PROFLIE PASS", "VET ID: "+ vetID);
                startActivity(intent);
            }
        });

        changepw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(clinicprofilepage.this, cchangepw.class);
                intent.putExtra("USER_DATA", vet);
                startActivity(intent);
                finish();
            }
        });

        logout.setOnClickListener(logoutLstnr);
        logoutimg.setOnClickListener(logoutLstnr);

        backImg.setOnClickListener(backListnr);
        backTxt.setOnClickListener(backListnr);

        //Navigation Handle
        ImageView home, calendar, pets, files, profile;
        home = findViewById(R.id.iv_home);
        calendar = findViewById(R.id.iv_calendar);
        files = findViewById((R.id.iv_files));
        profile = findViewById(R.id.iv_userprofile);
        pets = findViewById(R.id.iv_pets);

        //Link to navigation buttons
        pets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(clinicprofilepage.this, clinicpets.class);
                intent.putExtra("USER_DATA", vet);
                startActivity(intent);
                finish();
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(clinicprofilepage.this, chomedashboard.class);
                intent.putExtra("USER_DATA", vet);

                startActivity(intent);
                finish();
            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(clinicprofilepage.this, clinicprofilepage.class);
                intent.putExtra("USER_DATA", vet);
                startActivity(intent);
                finish();
            }
        });

        files.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(clinicprofilepage.this, consolidatedsummary.class);
                intent.putExtra("USER_DATA", vet);
                intent.putExtra("IS_PET_OWNER", false);
                startActivity(intent);
                finish();
            }
        });

        calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(clinicprofilepage.this, appointmentspage.class);
                intent.putExtra("USER_DATA", vet);
                intent.putExtra("IS_PET_OWNER", false);
                startActivity(intent);
                finish();
            }
        });
    }
}
