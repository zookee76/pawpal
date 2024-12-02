package com.mobdeve.pawpal.PetOwner;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.mobdeve.pawpal.Model.petOwners;
import com.mobdeve.pawpal.R;

public class pchangepw extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changepw);

        // Get Data
        Intent intent = getIntent();
        petOwners user = intent.getParcelableExtra("USER_DATA");
        if (user != null) {
            String fullname = user.getFullname();
            String email = user.getEmail();
            TextView tvUserFName = findViewById(R.id.tv_userfullname);
            tvUserFName.setText(fullname);

            TextView tvEmail = findViewById(R.id.tv_useremail);
            tvEmail.setText(email);
        }

        Button savepw = findViewById(R.id.btn_savepw);

        savepw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(pchangepw.this, petprofilepage.class);
                startActivity(intent);
                finish();
            }
        });
    }
}