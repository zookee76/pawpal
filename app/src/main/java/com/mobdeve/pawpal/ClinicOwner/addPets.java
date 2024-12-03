package com.mobdeve.pawpal.ClinicOwner;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mobdeve.pawpal.Database.DBHelper;
import com.mobdeve.pawpal.IntentKeys;
import com.mobdeve.pawpal.Model.clinicVet;
import com.mobdeve.pawpal.Model.images;
import com.mobdeve.pawpal.Model.petOwners;
import com.mobdeve.pawpal.Model.pets;
import com.mobdeve.pawpal.R;
import com.mobdeve.pawpal.Shared.appointmentspage;
import com.mobdeve.pawpal.Shared.consolidatedsummary;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class addPets extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Spinner petOwnerSpinner;
    private Uri selectedPhotoUri;
    private ImageView petphoto;
    private DBHelper db;
    private String firstName, lastName;
    private long vetID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_addpets);
        // Get Data
        Intent intent = getIntent();
        clinicVet vet = intent.getParcelableExtra("USER_DATA");
        if(vet != null){
            firstName = vet.getFirstName();
            lastName = vet.getLastName();
            vetID = vet.getVetID();
            Log.d("VET ID", "VETID:" + vetID);

            TextView tvName = findViewById(R.id.text_vetname);
            tvName.setText(firstName + " " + lastName);
        }

        db = new DBHelper(getApplicationContext());

        Spinner petSex = findViewById(R.id.add_petsex);
        petOwnerSpinner = findViewById(R.id.add_owner);
        petSex.setOnItemSelectedListener(this);
        populatePetOwner();

        // back handle
        ImageView backImg = findViewById(R.id.iv_back);
        TextView backTxt = findViewById(R.id.tv_back);
        View.OnClickListener backListnr = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(addPets.this, clinicpets.class);
                startActivity(intent);
                finish();
            }
        };

        backImg.setOnClickListener(backListnr);
        backTxt.setOnClickListener(backListnr);

        //Navigation Handle
        ImageView home, calendar, files, profile, pets;
        home = findViewById(R.id.iv_home);
        calendar = findViewById(R.id.iv_calendar);
        files = findViewById((R.id.iv_files));
        profile = findViewById(R.id.iv_userprofile);
        pets = findViewById(R.id.iv_pets);

        //Link to navigation buttons
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(addPets.this, chomedashboard.class);
                startActivity(intent);
                finish();
            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(addPets.this, clinicprofilepage.class);
                startActivity(intent);
                finish();
            }
        });

        files.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(addPets.this, consolidatedsummary.class);
                intent.putExtra("IS_PET_OWNER", false);
                startActivity(intent);
                finish();
            }
        });

        calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(addPets.this, appointmentspage.class);
                intent.putExtra("IS_PET_OWNER", false);
                startActivity(intent);
                finish();
            }
        });

        pets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(addPets.this, clinicpets.class);
                startActivity(intent);
                finish();
            }
        });

        // user inputs
        EditText petname = findViewById(R.id.add_petname);
        EditText petbreed = findViewById(R.id.add_petbreed);
        EditText petage = findViewById(R.id.add_petage);
        petphoto = findViewById(R.id.add_petphoto);
        EditText petbirthdate = findViewById(R.id.add_petbirthdate);
        EditText petheight = findViewById(R.id.add_petheight);
        EditText petweight = findViewById(R.id.add_petweight);
        EditText petcolor = findViewById(R.id.add_petcolor);
        EditText petmarkings = findViewById(R.id.add_petmarkings);

        Button addPet = findViewById(R.id.btn_addpet);
        FloatingActionButton addPetPhoto = findViewById(R.id.btn_addpetphoto);

        petbirthdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(addPets.this,
                        (view, selectedYear, selectedMonth, selectedDay) -> {
                            String selectedDate = selectedDay + "/" + (selectedMonth + 1) + "/" + selectedYear;
                            petbirthdate.setText(selectedDate); // Display on EditText
                        }, year, month, day);
                datePickerDialog.show();
            }
        });

        addPetPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 100); // Request Code = 100
            }
        });

        addPet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = petname.getText().toString().trim();
                String breed = petbreed.getText().toString().trim();
                String color = petcolor.getText().toString().trim();
                String markings = petmarkings.getText().toString().trim();
                String bdate = petbirthdate.getText().toString().trim();
                String sex = petSex.getSelectedItem().toString().trim();
                String owner = petOwnerSpinner.getSelectedItem().toString().trim();

                Integer age = null;
                Double height = null;
                Double weight = null;


                if (name.isEmpty() || breed.isEmpty() || color.isEmpty() || markings.isEmpty() ||
                bdate.isEmpty() || sex.isEmpty() || owner.isEmpty() ) {
                    Toast.makeText(addPets.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                }
                else if (selectedPhotoUri == null) {
                    Toast.makeText(addPets.this, "Please add a photo for the pet.", Toast.LENGTH_SHORT).show();
                }
                else{
                    try {
                        age = Integer.parseInt(petage.getText().toString().trim());
                        if (age <= 0) {
                            Toast.makeText(addPets.this, "Age must be greater than 0", Toast.LENGTH_SHORT).show();
                            return;
                        }
                    } catch (NumberFormatException e) {
                        Toast.makeText(addPets.this, "Invalid age. Please enter a valid number", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    try {
                        height = Double.parseDouble(petheight.getText().toString().trim());
                        if (height <= 0) {
                            Toast.makeText(addPets.this, "Height must be greater than 0", Toast.LENGTH_SHORT).show();
                            return;
                        }
                    } catch (NumberFormatException e) {
                        Toast.makeText(addPets.this, "Invalid height. Please enter a valid number", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    try {
                        weight = Double.parseDouble(petweight.getText().toString().trim());
                        if (weight <= 0) {
                            Toast.makeText(addPets.this, "Weight must be greater than 0", Toast.LENGTH_SHORT).show();
                            return;
                        }
                    } catch (NumberFormatException e) {
                        Toast.makeText(addPets.this, "Invalid weight. Please enter a valid number", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    long ownerID = db.getOwnerID(owner);
                    long imageID = 0;
                    long vet = vetID;
                    Log.d("CHECK VET ID AGAIN", "ID: "+ vet);
                    pets newPet = new pets(imageID, ownerID, vet, name, breed, sex, color, markings, bdate, age, height, weight);
                    long newPetID = db.addPet(newPet);
                    Log.d("NEWPETID", "ID: "+newPetID);

                    if(newPetID > 0){
                        try{
                            File savedImageFile = images.saveImageToDirectory(selectedPhotoUri, getApplicationContext());
                            String imagePath = savedImageFile.getAbsolutePath();
                            int userType = 1; // PET = 1; PETOWNER = 2; CLINICOWNER = 3
                            long newimageID = db.addImage(imagePath, newPetID, userType);
                            Log.d("NEWIMAGEID", "ID: "+newimageID);
                            db.addPetPhoto(newPetID, newimageID);

                        } catch (IOException e){
                            e.printStackTrace();
                        }
                    }

                    Log.d("NEWPET", "Name=" + name + ", Breed=" + breed + ", Age=" + age + ", IMAGEID= " + imageID + " Owner ID=" + newPetID + "VET ID" + vetID);

                    if(newPetID>0){
                        Toast.makeText(addPets.this, "Pet added successfully!", Toast.LENGTH_SHORT).show();
                        Intent resultIntent = new Intent();
                        setResult(RESULT_OK, resultIntent);

                        Log.d("REGISTRATION_RESULT", "Result: " + newPetID);
                        Intent intent = new Intent(addPets.this, clinicpets.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(addPets.this, "Failed to add pet.", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    private void populatePetOwner(){
        List<petOwners> petOwnersList = db.getPetOwners();
        List<String> ownerList = new ArrayList<>();
        for(petOwners owner : petOwnersList){
            String fullname = owner.getFullname();
            ownerList.add(fullname);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, ownerList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        petOwnerSpinner.setAdapter(adapter);
        petOwnerSpinner.setOnItemSelectedListener(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == RESULT_OK && data != null) {
            selectedPhotoUri = data.getData(); // Save the photo URI
            petphoto.setImageURI(selectedPhotoUri); // Display the photo in the ImageView
        }
    }
}