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
import com.mobdeve.pawpal.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class clogin extends AppCompatActivity {
    private DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Initialize DB
        DB = new DBHelper(getApplicationContext());

        setContentView(R.layout.activity_cliniclogin);
        TextView tvregister = findViewById(R.id.tv_clinicregister);
        TextView forgotpw = findViewById(R.id.tv_forgotpw);
        Button login_btn = findViewById(R.id.btn_login);

        // User Inputs
        EditText logindeets = findViewById(R.id.text_cliniccredentials);
        EditText loginpw = findViewById(R.id.text_clinicloginpw);

        tvregister.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(clogin.this, cregister.class);
                startActivity(intent);
                finish();
            }
        });

        forgotpw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(clogin.this, cforgotpw.class);
                startActivity(intent);
            }
        });

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String loginDetails = logindeets.getText().toString().trim();
                String password = loginpw.getText().toString();

                if(loginDetails.isEmpty() || password.isEmpty()){
                    Toast.makeText(clogin.this, "Please fill in both username/email and password", Toast.LENGTH_SHORT).show();
                }
                else{
                    if(DB == null){
                        Toast.makeText(clogin.this, "Database initialization failed.", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    clinicVet vet = DB.checkVetLogin(loginDetails, password);

                    if(vet !=  null){
                        navigateToDashboard(vet);
                    }else{
                        Toast.makeText(clogin.this, "Invalid login credentials. Please try again.", Toast.LENGTH_SHORT).show();
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

    private void navigateToDashboard(clinicVet vet){
        Intent intent = new Intent(clogin.this, chomedashboard.class);
        intent.putExtra("USER_DATA", vet);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}