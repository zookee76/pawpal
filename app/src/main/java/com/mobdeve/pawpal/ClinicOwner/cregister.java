package com.mobdeve.pawpal.ClinicOwner;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.mobdeve.pawpal.Database.DBHelper;
import com.mobdeve.pawpal.Model.clinicVet;
import com.mobdeve.pawpal.PetOwner.pregister;
import com.mobdeve.pawpal.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class cregister extends AppCompatActivity {
    private DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clinicregister);
        // Initialize DB
        DB = new DBHelper(getApplicationContext());
        // User Inputs
        EditText fname = findViewById(R.id.text_fname);
        EditText lname = findViewById(R.id.text_lastname);
        EditText email = findViewById(R.id.text_clinicemailadd);
        EditText password = findViewById(R.id.text_clinicpassword);
        EditText contact = findViewById(R.id.text_contactNo);
        TextView tvlogin = findViewById(R.id.tv_login);
        Button signup_btn = findViewById(R.id.btn_signup);

        tvlogin.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(cregister.this, clogin.class);
                startActivity(intent);
                finish();
            }
        });

        signup_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String first = fname.getText().toString().trim();
                String last = lname.getText().toString().trim();
                String eadd = email.getText().toString().trim();
                String pw = password.getText().toString();
                String contactno = contact.getText().toString();

                if(first.isEmpty() || last.isEmpty() || eadd.isEmpty() || pw.isEmpty()){
                    Toast.makeText(cregister.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                }
                else if(first.length() < 2 || last.length() < 2 ) {
                    Toast.makeText(cregister.this, "First and Last names must be at least 2 characters", Toast.LENGTH_SHORT).show();
                }
                else if(contactno.length() < 11 ){
                    Toast.makeText(cregister.this, "Invalid Contact No", Toast.LENGTH_SHORT).show();
                }
                else{
                    if(DB==null){
                        Toast.makeText(cregister.this, "Database initialization failed.", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    boolean vetExists = DB.checkIfVetExists(eadd);

                    if(vetExists){
                        Toast.makeText(cregister.this, "Vet already exists", Toast.LENGTH_SHORT).show();
                    } else if (!isValidEmail(eadd)) {
                        Toast.makeText(cregister.this, "Please enter a valid email address", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        long imageID = 0;
                        clinicVet vet = new clinicVet(imageID, first,last, eadd, pw, contactno);

                        long result = DB.addVet(vet);

                        if(result>0){
                            Toast.makeText(cregister.this, "Registration successful!", Toast.LENGTH_SHORT).show();

                            Log.d("REGISTRATION_RESULT", "Result: " + result);

                            Intent intent = new Intent(cregister.this, chomedashboard.class);
                            intent.putExtra("USER_DATA", vet);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                            finish();

                        }else{
                            Toast.makeText(cregister.this, "Registration failed.", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
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