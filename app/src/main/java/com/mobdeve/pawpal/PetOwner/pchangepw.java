package com.mobdeve.pawpal.PetOwner;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.mobdeve.pawpal.R;
import com.mobdeve.pawpal.Shared.petprofilepage;

public class pchangepw extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changepw);

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