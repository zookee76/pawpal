package com.mobdeve.pawpal.PetOwner;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.mobdeve.pawpal.R;

public class pforgotpw extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_forgotpw);

        Button newpw_btn = findViewById(R.id.btn_newpw);

        newpw_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(pforgotpw.this, plogin.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
