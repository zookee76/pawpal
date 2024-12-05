package com.mobdeve.pawpal.PetOwner;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.mobdeve.pawpal.ClinicOwner.cchangepw;
import com.mobdeve.pawpal.ClinicOwner.clinicprofilepage;
import com.mobdeve.pawpal.Database.DBHelper;
import com.mobdeve.pawpal.Model.clinicVet;
import com.mobdeve.pawpal.Model.petOwners;
import com.mobdeve.pawpal.R;

public class pchangepw extends AppCompatActivity {

    private DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changepw);

        // Get Data
        DB = new DBHelper(getApplicationContext());
        Intent intent = getIntent();
        petOwners user = intent.getParcelableExtra("USER_DATA");

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
                    Toast.makeText(pchangepw.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                Boolean isUpdated = DB.changePwPetowner(editemail, editoldpw, editnewpw);

                long ownerID = user.getID();
                Intent intent = new Intent(pchangepw.this, petprofilepage.class);
                if(isUpdated){
                    petOwners updatedOwner = DB.getPetOwner(ownerID);
                    intent.putExtra("USER_DATA", updatedOwner);
                    startActivity(intent);
                    finish();
                    Toast.makeText(pchangepw.this, "Password updated successfully", Toast.LENGTH_SHORT).show();
                }else{
                    if (!DB.doesPetOwnerEmailMatch(editemail, ownerID)) {
                        Toast.makeText(pchangepw.this, "Email does not exist", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(pchangepw.this, "Old password does not match", Toast.LENGTH_SHORT).show();
                    }
                };
            }
        });
    }
}