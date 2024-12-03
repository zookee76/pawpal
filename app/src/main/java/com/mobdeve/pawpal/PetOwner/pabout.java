package com.mobdeve.pawpal.PetOwner;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.mobdeve.pawpal.Database.DBHelper;
import com.mobdeve.pawpal.Model.petOwners;
import com.mobdeve.pawpal.Model.pets;
import com.mobdeve.pawpal.R;
import com.mobdeve.pawpal.Shared.appointmentspage;
import com.mobdeve.pawpal.Shared.consolidatedsummary;
import com.mobdeve.pawpal.Shared.petspage;

import org.w3c.dom.Text;

import java.io.File;

public class pabout extends AppCompatActivity
{
    private DBHelper DB;
    private TextView petName,petDesc,dateOfBirth,age,gender,breed,height,weight,petcolor,markings,ownername,cellno,address;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_petprofileabout);

        DB = new DBHelper(getApplicationContext());
        Intent intent = getIntent();
        pets petData = intent.getParcelableExtra("PET_DATA");
        petOwners ownerData = intent.getParcelableExtra("OWNER_DATA");

        //ELEMENTS
        ImageView petImage = findViewById(R.id.petImage);
        petName = findViewById(R.id.petName);
        petDesc = findViewById(R.id.aboutdescription);
        dateOfBirth = findViewById(R.id.dateofbirth);
        age = findViewById(R.id.age);
        gender = findViewById(R.id.gender);
        breed = findViewById(R.id.breed);
        height = findViewById(R.id.height);
        weight = findViewById(R.id.weight);
        petcolor = findViewById(R.id.petcolor);
        markings = findViewById(R.id.markings);
        ownername = findViewById(R.id.ownername);
        cellno = findViewById(R.id.cellphonenumber);
        address = findViewById(R.id.address);

        long imageID = petData.getImageID();
        String imagePath = DB.getImagePath(imageID);

        if(imagePath!=null){
            File imgFile = new File(imagePath);
            if(imgFile.exists()){
                Glide.with(getApplicationContext())
                        .load(imgFile)
                        .into(petImage);
            }
        }

        populatePetDetails(petData);
        populatePetOwnerDetails(ownerData);

        //Back Handle
        ImageView backImg = findViewById(R.id.iv_back);
        TextView backTxt = findViewById(R.id.tv_back);

        ImageView shareImg = findViewById(R.id.iv_share);

        View.OnClickListener backListnr = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*
                Intent intent = new Intent(pabout.this, phomedashboard.class);
                startActivity(intent);

                 */
                finish();
            }
        };

        View.OnClickListener shareListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(pabout.this, shareqr.class);
                startActivity(intent);
            }
        };

        shareImg.setOnClickListener(shareListener);

        backImg.setOnClickListener(backListnr);
        backTxt.setOnClickListener(backListnr);

        TextView meddiet = findViewById(R.id.medicationdietcategory);
        TextView about = findViewById(R.id.aboutcategory);
        TextView schedules = findViewById(R.id.schedulecategory);
        TextView meddocs = findViewById(R.id.medicaldocscategory);

        meddocs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(pabout.this, pmedicaldocs.class);
                intent.putExtra("USER_DATA", ownerData);
                intent.putExtra("PET_DATA", petData);
                startActivity(intent);
                finish();
            }
        });

        meddiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(pabout.this, pmediet.class);
                intent.putExtra("USER_DATA", ownerData);
                intent.putExtra("PET_DATA", petData);
                startActivity(intent);
                finish();
            }
        });

        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(pabout.this, pabout.class);
                intent.putExtra("USER_DATA", ownerData);
                intent.putExtra("PET_DATA", petData);
                startActivity(intent);
                finish();
            }
        });

        schedules.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(pabout.this, pschedules.class);
                intent.putExtra("USER_DATA", ownerData);
                intent.putExtra("PET_DATA", petData);
                startActivity(intent);
                finish();
            }
        });

        //Navigation Handle
        ImageView home, calendar, pets, files, profile;
        home = findViewById(R.id.iv_home);
        calendar = findViewById(R.id.iv_calendar);
        pets = findViewById(R.id.iv_pets);
        files = findViewById((R.id.iv_files));
        profile = findViewById(R.id.iv_userprofile);

        //Link to navigation buttons
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(pabout.this, phomedashboard.class);
                intent.putExtra("USER_DATA", ownerData);
                startActivity(intent);
                finish();
            }
        });

        pets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(pabout.this, petspage.class);
                intent.putExtra("IS_PET_OWNER", true);
                intent.putExtra("USER_DATA", ownerData);
                startActivity(intent);
                finish();
            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(pabout.this, petprofilepage.class);
                intent.putExtra("USER_DATA", ownerData);
                startActivity(intent);
                finish();
            }
        });

        files.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(pabout.this, consolidatedsummary.class);
                intent.putExtra("IS_PET_OWNER", true);
                intent.putExtra("USER_DATA", ownerData);
                finish();
            }
        });

        calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(pabout.this, appointmentspage.class);
                intent.putExtra("IS_PET_OWNER", true);
                intent.putExtra("USER_DATA", ownerData);
                startActivity(intent);
                finish();
            }
        });
    }

    private void populatePetDetails(pets pet){
        petName.setText(pet.getName());
        dateOfBirth.setText("Date of Birth: "+pet.getBirthdate());
        age.setText("Age: "+pet.getAge());
        gender.setText("Sex: "+pet.getSex());
        breed.setText("Breed: "+pet.getBreed());
        height.setText("Height: "+pet.getHeight());
        weight.setText("Weight: "+pet.getWeight());
        petcolor.setText("Color: "+pet.getColor());
        markings.setText("Markings: "+pet.getMarkings());
    }

    private void populatePetOwnerDetails(petOwners owner){
        ownername.setText("Name: "+owner.getFullname());
        cellno.setText("Cell No.: "+owner.getContactNo());
        address.setText("Address: "+owner.getEmail());
    }
}
