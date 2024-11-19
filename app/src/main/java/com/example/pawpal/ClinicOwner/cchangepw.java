package com.example.pawpal.ClinicOwner;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.pawpal.R;

public class cchangepw extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_changepw);

        Button savepw = findViewById(R.id.btn_savepw);

        savepw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(cchangepw.this, clinicprofilepage.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
