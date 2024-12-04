package com.mobdeve.pawpal.ClinicOwner;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.mobdeve.pawpal.Adapter.dietinstructionsAdapter;
import com.mobdeve.pawpal.Adapter.medietAdapter;
import com.mobdeve.pawpal.Database.DBHelper;
import com.mobdeve.pawpal.Model.clinicVet;
import com.mobdeve.pawpal.Model.dietmed;
import com.mobdeve.pawpal.Model.nutinstructions;
import com.mobdeve.pawpal.Model.nutritionfacts;
import com.mobdeve.pawpal.Model.nutritionplan;
import com.mobdeve.pawpal.Model.petOwners;
import com.mobdeve.pawpal.Model.pets;
import com.mobdeve.pawpal.R;
import com.mobdeve.pawpal.Shared.appointmentspage;
import com.mobdeve.pawpal.Shared.consolidatedsummary;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class cmedicationdiet extends AppCompatActivity {
    private DBHelper DB;
    private clinicVet vetData;
    private pets petData;
    private petOwners petOwnerData;
    private RecyclerView rvMediet, rvInstructions;
    private medietAdapter adapter;
    private dietinstructionsAdapter stepAdapter;
    private List<dietmed> dietmedList;
    private nutritionplan plan;
    private nutritionfacts facts;
    private List<nutinstructions> instructions;
    private boolean isCalSelected = false, isFatsSelected = false, isFiberSelected = false, isProtSelected = false, isKeyNutSelected = false;
    private CardView nutritionplancard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_clinicpetprofilemediet);

        // Get Data
        DB = new DBHelper(getApplicationContext());
        Intent intent = getIntent();
        vetData = intent.getParcelableExtra("VET_DATA");
        petData = intent.getParcelableExtra("PET_DATA");
        petOwnerData = intent.getParcelableExtra("PETOWNER_DATA");

        long petID = petData.getID();
        petOwnerData = DB.getPetOwner(petID);

        // ELEMENTS
        TextView petName = findViewById(R.id.petName);
        ImageView petImage = findViewById(R.id.petImage);
        petName = findViewById(R.id.petName);
        petName.setText(petData.getName());
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

        // ELEMENTS TO UPDATE
        TextView dietname, nutplanservsize, calories, fats, fiber, protein, keynutrients;
        dietname = findViewById(R.id.tv_dietname);
        nutplanservsize = findViewById(R.id.nutplanservingsize);

        Button Calories, Fats, Fiber, Protein, KeyNutrients;
        Calories = findViewById(R.id.calories);
        Fats = findViewById(R.id.fats);
        Fiber = findViewById(R.id.fiber);
        Protein = findViewById(R.id.protein);
        KeyNutrients = findViewById(R.id.keyNutrients);
        nutritionplancard = findViewById(R.id.nutritionplancard);

        TextView note = findViewById(R.id.nutplanotebody);

        rvMediet = findViewById(R.id.rv_mediet);
        rvMediet.setLayoutManager(new LinearLayoutManager(this));

        dietmedList = new ArrayList<>();
        loadalldietmed();

        adapter = new medietAdapter(this, dietmedList, DB);
        rvMediet.setAdapter(adapter);

        // NUTRITION FACTS
        instructions = new ArrayList<>();
        String calVal, fatsVal, fiberVal, proteinVal, keyNutrientsVal;
        loadDietPlan();
        if(plan != null){
            dietname.setText(plan.getDietname());
            nutplanservsize.setText("Serving Size: "+plan.getServingSize());
            note.setText(plan.getNote());
        }

        if(facts!=null){
            ColorStateList tintColor = ColorStateList.valueOf(Color.parseColor("#D6EAFC"));
            ColorStateList defaultColor = ColorStateList.valueOf(Color.parseColor("#0C439E"));
            calVal = facts.getCalories();
            fatsVal = facts.getFat();
            fiberVal = facts.getFiber();
            proteinVal = facts.getProtein();
            keyNutrientsVal = facts.getKeyNutrients();

            View.OnClickListener calListner = new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    isCalSelected = !isCalSelected;
                    if (isCalSelected) {
                        Calories.setText(calVal + "kcal");
                        Calories.setTextColor(Color.parseColor("#0C439E"));
                        Calories.setBackgroundTintList(tintColor);
                    } else {
                        Calories.setText("Calories"); // Revert back to default value
                        Calories.setTextColor(Color.parseColor("#FFFFFF"));
                        Calories.setBackgroundTintList(defaultColor); // Revert to default background color
                    }
                }
            };

            View.OnClickListener fatsListner = new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    isFatsSelected = !isFatsSelected;
                    if (isFatsSelected) {
                        Fats.setText(fatsVal + "g");
                        Fats.setTextColor(Color.parseColor("#0C439E"));
                        Fats.setBackgroundTintList(tintColor);
                    } else {
                        Fats.setText("Fats"); // Revert back to default value
                        Fats.setTextColor(Color.parseColor("#FFFFFF"));
                        Fats.setBackgroundTintList(defaultColor); // Revert to default background color
                    }
                }
            };

            View.OnClickListener fiberListner = new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    isFiberSelected = !isFiberSelected;
                    if(isFiberSelected){
                        Fiber.setText(fiberVal + "g");
                        Fiber.setTextColor(Color.parseColor("#0C439E"));
                        Fiber.setBackgroundTintList(tintColor);
                    }else{
                        Fiber.setText("Fiber");
                        Fiber.setTextColor(Color.parseColor("#FFFFFF"));
                        Fiber.setBackgroundTintList(defaultColor);
                    }

                }
            };

            View.OnClickListener proteinListner = new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    isProtSelected = !isProtSelected;
                    if(isProtSelected){
                        Protein.setText(proteinVal + "g");
                        Protein.setTextColor(Color.parseColor("#0C439E"));
                        Protein.setBackgroundTintList(tintColor);
                    }else{
                        Protein.setText("Protein");
                        Protein.setTextColor(Color.parseColor("#FFFFFF"));
                        Protein.setBackgroundTintList(defaultColor);
                    }
                }
            };

            View.OnClickListener keyNutrientsListner = new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    isKeyNutSelected = !isKeyNutSelected;
                    if(isKeyNutSelected){
                        KeyNutrients.setText(keyNutrientsVal);
                        KeyNutrients.setTextColor(Color.parseColor("#0C439E"));
                        KeyNutrients.setBackgroundTintList(tintColor);
                    }else{
                        KeyNutrients.setText("Protein");
                        KeyNutrients.setTextColor(Color.parseColor("#FFFFFF"));
                        KeyNutrients.setBackgroundTintList(defaultColor);
                    }
                }
            };

            Calories.setOnClickListener(calListner);
            Fats.setOnClickListener(fatsListner);
            Fiber.setOnClickListener(fiberListner);
            Protein.setOnClickListener(proteinListner);
            KeyNutrients.setOnClickListener(keyNutrientsListner);
        }

        rvInstructions = findViewById(R.id.rv_instructions);
        rvInstructions.setLayoutManager(new LinearLayoutManager(this));

        stepAdapter = new dietinstructionsAdapter(this, instructions);
        rvInstructions.setAdapter(stepAdapter);

        //Back Handle
        ImageView backImg = findViewById(R.id.iv_back);
        TextView backTxt = findViewById(R.id.tv_back);

        View.OnClickListener backListnr = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        };
        backImg.setOnClickListener(backListnr);
        backTxt.setOnClickListener(backListnr);

        // options
        TextView about, meddocu, meddiet, schedules;
        ImageView edit;

        about = findViewById(R.id.aboutcategory);
        meddocu = findViewById(R.id.medicaldocscategory);
        meddiet = findViewById(R.id.medicationdietcategory);
        schedules = findViewById(R.id.schedulecategory);
        edit = findViewById(R.id.iv_edit);

        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(cmedicationdiet.this, cabout.class);
                intent.putExtra("PET_DATA", petData);
                intent.putExtra("VET_DATA", vetData);
                intent.putExtra("PETOWNER_DATA", petOwnerData);
                startActivity(intent);
                finish();
            }
        });

        meddocu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(cmedicationdiet.this, cmedicaldocs.class);
                intent.putExtra("PET_DATA", petData);
                intent.putExtra("VET_DATA", vetData);
                intent.putExtra("PETOWNER_DATA", petOwnerData);
                startActivity(intent);
                finish();
            }
        });

        meddiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(cmedicationdiet.this, cmedicationdiet.class);
                intent.putExtra("PET_DATA", petData);
                intent.putExtra("VET_DATA", vetData);
                intent.putExtra("PETOWNER_DATA", petOwnerData);
                startActivity(intent);
                finish();
            }
        });

        schedules.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(cmedicationdiet.this, cschedules.class);
                intent.putExtra("PET_DATA", petData);
                intent.putExtra("VET_DATA", vetData);
                intent.putExtra("PETOWNER_DATA", petOwnerData);
                startActivity(intent);
                finish();
            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(cmedicationdiet.this, editmedicationdiet.class);
                intent.putExtra("PET_DATA", petData);
                intent.putExtra("VET_DATA", vetData);
                intent.putExtra("PETOWNER_DATA", petOwnerData);
                startActivity(intent);
            }
        });

        //Navigation Handle
        ImageView home, calendar, files, profile, pets;
        home = findViewById(R.id.iv_home);
        calendar = findViewById(R.id.iv_calendar);
        files = findViewById((R.id.iv_files));
        profile = findViewById(R.id.iv_userprofile);
        pets = findViewById(R.id.iv_pets);

        //Link to navigation buttons
        pets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(cmedicationdiet.this, clinicpets.class);
                intent.putExtra("USER_DATA", vetData);
                startActivity(intent);
                finish();
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(cmedicationdiet.this, chomedashboard.class);
                intent.putExtra("USER_DATA", vetData);
                startActivity(intent);
                finish();
            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(cmedicationdiet.this, clinicprofilepage.class);
                intent.putExtra("USER_DATA", vetData);
                startActivity(intent);
                finish();
            }
        });

        files.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(cmedicationdiet.this, consolidatedsummary.class);
                intent.putExtra("USER_DATA", vetData);
                intent.putExtra("IS_PET_OWNER", false);
                startActivity(intent);
                finish();
            }
        });

        calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(cmedicationdiet.this, appointmentspage.class);
                intent.putExtra("USER_DATA", vetData);
                intent.putExtra("IS_PET_OWNER", false);
                startActivity(intent);
                finish();
            }
        });
    }

    protected void onResume() {
        super.onResume();
        dietmedList.clear();
        loadalldietmed();
        if (adapter != null) {
            adapter.notifyDataSetChanged();
            Log.d("RESUMING", "Checking");
        }
    }

    private void loadalldietmed(){
        dietmedList.clear();
        long petID = petData.getID();

        if (DB != null) {
            List<dietmed> dmed = DB.getPetDietmed(petID);
            Log.d("DIETMEDSIZE", "Size: " + dmed.size());

            if (dmed != null && !dmed.isEmpty()) {
                dietmedList.addAll(dmed);
            }
        } else {
            Log.e("DIETMEDSIZE", "petsDB is not initialized.");
        }

        if(adapter != null){
            adapter.notifyDataSetChanged();

        }
    }

    private void loadDietPlan(){
        long petID = petData.getID();
        if (DB != null){
            plan = DB.getNutritionPlanByPet(petID);
            if(plan != null){
                Log.d("CHECKDIETPLAN: ", "Diet Name: "+plan.getDietname());
                loadFacts();
                loadInstructions();
            }else{
                Log.d("CHECKDIETPLAN: ", "NO DIET PLAN");
                nutritionplancard.setVisibility(View.GONE);
            }
        }else{
            Log.d("CHECKDIETPLANDB: ", "NO DB");
        }

    }

    private void loadFacts(){
        long planID = plan.getID();
        if (DB != null){
            facts = DB.getFactsByDiet(planID);
            Log.d("FACTS CHECK", "FACT ID: " + facts.getID());
        }else{
            Log.d("FACTS CHECK", "No Facts");
        }
    }

    private void loadInstructions(){
        long planID = plan.getID();

        if (DB != null) {
            instructions.clear();
            List<nutinstructions> step = DB.getStepsByDiet(planID);
            Log.d("INSTRUCTIONS SIZE", "Size: " + step.size());
            if (step != null && !step.isEmpty()) {
                instructions.addAll(step);
            }
        } else {
            Log.e("INSTRUCTIONS SIZE", "petsDB is not initialized.");
        }
        if(stepAdapter != null){
            stepAdapter.notifyDataSetChanged();
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK){
            reloadDietmed();
        }
    }

    private void reloadDietmed(){
        long petID = petData.getID();
        if (dietmedList != null && !dietmedList.isEmpty()) {
            dietmedList.clear();
        }
        dietmedList.clear();
        dietmedList.addAll(DB.getPetDietmed(petID));

        adapter.notifyDataSetChanged();
    }

}