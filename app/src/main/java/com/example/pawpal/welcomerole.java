package com.example.pawpal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class welcomerole extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.welcome_role);

        // Clickables

        ImageView petOwnerImg = findViewById(R.id.petownerimg);
        ImageView clinicOwnerImg = findViewById(R.id.clinicownerimg);
        TextView petOwnerTxt = findViewById(R.id.owner_text);
        TextView clinicOwnerTxt = findViewById(R.id.clinic_text);

        // Link to login pages
        View.OnClickListener petClickLstnr = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(welcomerole.this, plogin.class);
                startActivity(intent);
            }
        };
        View.OnClickListener clinicClickLstnr = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(welcomerole.this, clogin.class);
                startActivity(intent);
            }
        };

        // Set Listeners
        petOwnerImg.setOnClickListener(petClickLstnr);
        petOwnerTxt.setOnClickListener(petClickLstnr);

        clinicOwnerImg.setOnClickListener(clinicClickLstnr);
        clinicOwnerTxt.setOnClickListener(clinicClickLstnr);
    }
}
