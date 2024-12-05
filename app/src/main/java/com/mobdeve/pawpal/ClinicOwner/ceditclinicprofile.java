package com.mobdeve.pawpal.ClinicOwner;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.mobdeve.pawpal.Database.DBHelper;
import com.mobdeve.pawpal.Model.clinicVet;
import com.mobdeve.pawpal.PetOwner.editprofilepage;
import com.mobdeve.pawpal.PetOwner.petprofilepage;
import com.mobdeve.pawpal.R;
import com.mobdeve.pawpal.Shared.appointmentspage;
import com.mobdeve.pawpal.Shared.consolidatedsummary;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ceditclinicprofile extends AppCompatActivity {
    private DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_cliniceditprofile);

        // Get Data
        DB = new DBHelper(getApplicationContext());
        Intent intent = getIntent();
        clinicVet vet = intent.getParcelableExtra("USER_DATA");

        EditText tvfname = findViewById(R.id.text_editfname),
                tvlname  = findViewById(R.id.text_editlname),
                tvemail = findViewById(R.id.text_editemailadd),
                tvcontact = findViewById(R.id.text_editcontactno);

        Button save = findViewById(R.id.btn_saveprofile);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String first, last, eadd, contactno;
                first = tvfname.getText().toString();
                last = tvlname.getText().toString();
                eadd = tvemail.getText().toString();
                contactno = tvcontact.getText().toString();

                long vetID = vet.getVetID();

                Log.d("CHECKING EDIT", first + last + eadd);

                if(!eadd.isEmpty() && !isValidEmail(eadd)){
                    eadd = vet.getEmailAdd();
                    Toast.makeText(ceditclinicprofile.this, "Invalid Email", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(!contactno.isEmpty() && contactno.length() < 11){
                    contactno = vet.getContactNo();
                    Toast.makeText(ceditclinicprofile.this, "Invalid Contact No", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(first.isEmpty()) first = vet.getFirstName();

                if(last.isEmpty()) last = vet.getLastName();

                if(eadd.isEmpty()) eadd = vet.getEmailAdd();

                if(contactno.isEmpty()) contactno = vet.getContactNo();

                long imageID = vet.getImageID();
                String password = vet.getPassword();
                clinicVet updateVet = new clinicVet(vetID, imageID, first, last, eadd,password, contactno);
                long tobeUpdatedID = vet.getVetID();
                Log.d("CHECK EDITED PROFILE", updateVet.toString());
                Boolean isUpdated = DB.updateVet(updateVet, tobeUpdatedID);

                Intent intent = new Intent(ceditclinicprofile.this, clinicprofilepage.class);

                if(isUpdated){
                    intent.putExtra("USER_DATA", updateVet);
                    Log.d("UPDATEDUSER", "UPDATES User ID" +updateVet.getVetID()+ ": " + updateVet.getFirstName());
                    Log.d("UPDATEDUSER", "User updated successfully: " + updateVet.toString());
                    startActivity(intent);
                }else{
                    intent.putExtra("USER_DATA", updateVet);
                    Log.d("UPDATEDUSER", "Failed to update user");
                    startActivity(intent);
                }
            }
        });

        //Save and Back Handle
        ImageView backImg = findViewById(R.id.iv_back);
        TextView backTxt = findViewById(R.id.tv_back);

        View.OnClickListener backListnr = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ceditclinicprofile.this, clinicprofilepage.class);
                intent.putExtra("USER_DATA", vet);
                startActivity(intent);
                finish();
            }
        };

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
                Intent intent = new Intent(ceditclinicprofile.this, clinicpets.class);
                intent.putExtra("USER_DATA", vet);
                startActivity(intent);
                finish();
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ceditclinicprofile.this, chomedashboard.class);
                intent.putExtra("USER_DATA", vet);
                startActivity(intent);
                finish();
            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ceditclinicprofile.this, clinicprofilepage.class);
                intent.putExtra("USER_DATA", vet);
                startActivity(intent);
                finish();
            }
        });

        files.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ceditclinicprofile.this, consolidatedsummary.class);
                intent.putExtra("USER_DATA", vet);
                intent.putExtra("IS_PET_OWNER", false);
                startActivity(intent);
                finish();
            }
        });

        calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ceditclinicprofile.this, appointmentspage.class);
                intent.putExtra("USER_DATA", vet);
                intent.putExtra("IS_PET_OWNER", false);
                startActivity(intent);
                finish();
            }
        });
    }

    private boolean isValidEmail(String email) {
        String emailPattern = "[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}";
        Pattern pattern = Pattern.compile(emailPattern);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
