package com.mobdeve.pawpal.PetOwner;

import android.content.Intent;
import android.os.Bundle;
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

public class plogin extends AppCompatActivity {
    private DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize DB
        DB = new DBHelper(getApplicationContext());

        setContentView(R.layout.activity_petlogin);
        TextView tvregister = findViewById(R.id.tv_register);
        TextView forgotpw = findViewById(R.id.tv_forgotpw);

        Button login = findViewById(R.id.btn_login);

        // User Inputs
        EditText logindeets = findViewById(R.id.text_logincredentials);
        EditText loginpw = findViewById(R.id.text_loginpw);

        tvregister.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(plogin.this, pregister.class);
                startActivity(intent);
                finish();
            }
        });

        forgotpw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(plogin.this, pforgotpw.class);
                startActivity(intent);
                finish();
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String loginDetails = logindeets.getText().toString();
                String password = loginpw.getText().toString();

                if(loginDetails.isEmpty() || password.isEmpty()){
                    Toast.makeText(plogin.this, "Please fill in both username/email and password", Toast.LENGTH_SHORT).show();
                }
                else{
                    if(DB == null){
                        Toast.makeText(plogin.this, "Database initialization failed.", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    petOwners owner = DB.checkLogin(loginDetails, password);

                    if (owner != null) {
                        navigateToDashboard(owner);
                    } else {
                        Toast.makeText(plogin.this, "Invalid login credentials. Please try again.", Toast.LENGTH_SHORT).show();
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

    private void navigateToDashboard(petOwners user){
        Intent intent = new Intent(plogin.this, phomedashboard.class);


        intent.putExtra("USER_DATA", user);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
