package com.example.pawpal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class plogin extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_petlogin);
        TextView tvregister = findViewById(R.id.tv_register);
        TextView forgotpw = findViewById(R.id.tv_forgotpw);

        Button login = findViewById(R.id.btn_login);

        tvregister.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(plogin.this, pregister.class);
                startActivity(intent);
            }
        });

        forgotpw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(plogin.this, pforgotpw.class);
                startActivity(intent);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(plogin.this, phomedashboard.class);
                startActivity(intent);
            }
        });
    }
}
