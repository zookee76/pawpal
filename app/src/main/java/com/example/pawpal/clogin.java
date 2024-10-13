package com.example.pawpal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class clogin extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_cliniclogin);
        TextView tvregister = findViewById(R.id.tv_clinicregister);
        TextView forgotpw = findViewById(R.id.tv_forgotpw);

        tvregister.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(clogin.this, cregister.class);
                startActivity(intent);
            }
        });

        forgotpw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(clogin.this, pforgotpw.class);
                startActivity(intent);
            }
        });
    }
}
