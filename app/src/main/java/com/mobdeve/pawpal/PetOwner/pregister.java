package com.mobdeve.pawpal.PetOwner;

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
import com.mobdeve.pawpal.Model.petOwners;
import com.mobdeve.pawpal.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class pregister extends AppCompatActivity {

    private DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_petregister);

        // Initialize DB
        DB = new DBHelper(getApplicationContext());

        TextView tvlogin = findViewById(R.id.tv_login);
        Button signup_btn = findViewById(R.id.btn_signup);

        // User Inputs
        EditText fname = findViewById(R.id.text_firstname);
        EditText lname = findViewById(R.id.text_lastname);
        EditText email = findViewById(R.id.text_emailadd);
        EditText password = findViewById(R.id.text_password);
        EditText username = findViewById(R.id.text_username);
        EditText contact = findViewById(R.id.text_contactno);

        tvlogin.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(pregister.this, plogin.class);
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
                String uname = username.getText().toString().trim();
                String contactno = contact.getText().toString();

                if(first.isEmpty() || last.isEmpty() || eadd.isEmpty() || pw.isEmpty() || uname.isEmpty()){
                    Toast.makeText(pregister.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                }
                else if(first.length() < 2 || last.length() < 2 ) {
                    Toast.makeText(pregister.this, "First and Last names must be at least 2 characters", Toast.LENGTH_SHORT).show();
                }
                else if(contactno.length() < 11 ){
                    Toast.makeText(pregister.this, "Invalid Contact No", Toast.LENGTH_SHORT).show();
                }
                else if(uname.length() < 4){
                    Toast.makeText(pregister.this, "Username must be at least 4 characters", Toast.LENGTH_SHORT).show();
                }
                else{
                    if (DB == null) {
                        Toast.makeText(pregister.this, "Database initialization failed.", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    boolean userExists = DB.checkIfUserExists(eadd);

                    if(userExists) {
                        Toast.makeText(pregister.this, "User already exists", Toast.LENGTH_SHORT).show();
                    }
                    else if(!isValidEmail(eadd)){
                        Toast.makeText(pregister.this, "Please enter a valid email address", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        long imageID = 0;
                        petOwners newOwner = new petOwners(first, last, eadd, pw, uname, contactno, imageID);

                        long result = DB.addPetOwner(newOwner);

                        if(result > 0){
                            Toast.makeText(pregister.this, "Registration successful!", Toast.LENGTH_SHORT).show();

                            Log.d("REGISTRATION_RESULT", "Result: " + result);

                            Intent intent = new Intent(pregister.this, phomedashboard.class);
                            intent.putExtra("USER_DATA", newOwner);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);

                            startActivity(intent);
                        }
                        else{
                            Toast.makeText(pregister.this, "Registration failed.", Toast.LENGTH_SHORT).show();
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
