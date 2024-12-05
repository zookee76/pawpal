package com.mobdeve.pawpal.ClinicOwner;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.mobdeve.pawpal.Database.DBHelper;
import com.mobdeve.pawpal.Model.clinicVet;
import com.mobdeve.pawpal.R;

public class cchangepw extends AppCompatActivity {
    private DBHelper DB;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_changepw);
        // Get Data
        DB = new DBHelper(getApplicationContext());
        Intent intent = getIntent();
        clinicVet vet = intent.getParcelableExtra("USER_DATA");

        // Elements
        EditText email = findViewById(R.id.email),
                oldpw = findViewById(R.id.oldpw),
                newpw = findViewById(R.id.newpw);

        Button savepw = findViewById(R.id.btn_savepw);

        savepw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String editemail, editoldpw, editnewpw;
                editemail = email.getText().toString();
                editoldpw = oldpw.getText().toString();
                editnewpw = newpw.getText().toString();

                if (editemail.isEmpty() || editoldpw.isEmpty() || editnewpw.isEmpty()) {
                    Toast.makeText(cchangepw.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                Boolean isUpdated = DB.changePwVet(editemail, editoldpw, editnewpw);

                long vetID = vet.getVetID();
                Intent intent = new Intent(cchangepw.this, clinicprofilepage.class);
                if(isUpdated){
                    clinicVet updatedVet = DB.getVetBy(vetID);
                    intent.putExtra("USER_DATA", updatedVet);
                    startActivity(intent);
                    finish();
                    Toast.makeText(cchangepw.this, "Password updated successfully", Toast.LENGTH_SHORT).show();
                }else{
                    if (!DB.doesVetEmailMatch(editemail, vetID)) {
                        Toast.makeText(cchangepw.this, "Email does not exist", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(cchangepw.this, "Old password does not match", Toast.LENGTH_SHORT).show();
                    }
                };
            }
        });
    }
}
