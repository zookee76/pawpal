package com.example.pawpal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class cregister extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_clinicregister);

        TextView tvlogin = findViewById(R.id.tv_cliniclogin);
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
                Intent intent = new Intent(cregister.this, clogin.class);
                startActivity(intent);
                finish();
            }
        });
    }
}