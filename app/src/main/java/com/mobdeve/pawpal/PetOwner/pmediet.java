package com.mobdeve.pawpal.PetOwner;

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
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.mobdeve.pawpal.Adapter.dietinstructionsAdapter;
import com.mobdeve.pawpal.Adapter.medicaldocadapter;
import com.mobdeve.pawpal.Adapter.medietAdapter;
import com.mobdeve.pawpal.Database.DBHelper;
import com.mobdeve.pawpal.Model.dietmed;
import com.mobdeve.pawpal.Model.medicaldoc;
import com.mobdeve.pawpal.Model.nutinstructions;
import com.mobdeve.pawpal.Model.nutritionfacts;
import com.mobdeve.pawpal.Model.nutritionplan;
import com.mobdeve.pawpal.Model.petOwners;
import com.mobdeve.pawpal.Model.pets;
import com.mobdeve.pawpal.R;
import com.mobdeve.pawpal.Shared.appointmentspage;
import com.mobdeve.pawpal.Shared.consolidatedsummary;
import com.mobdeve.pawpal.Shared.petspage;

import org.checkerframework.checker.units.qual.K;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class pmediet extends AppCompatActivity
{
    private DBHelper DB;
    private TextView petName;
    private RecyclerView rvMediet, rvInstructions;
    private medietAdapter adapter;
    private dietinstructionsAdapter stepAdapter;
    private List<dietmed> dietmedList;
    private nutritionplan plan;
    private nutritionfacts facts;
    private List<nutinstructions> instructions;
    private pets petData;
    private petOwners ownerData;
    private boolean isCalSelected = false, isFatsSelected = false, isFiberSelected = false, isProtSelected = false, isKeyNutSelected = false;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_petprofilemediet);

        DB = new DBHelper(getApplicationContext());
        Intent intent = getIntent();
        petData = intent.getParcelableExtra("PET_DATA");
        ownerData = intent.getParcelableExtra("OWNER_DATA");
        // ELEMENTS
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

        ImageView shareImg = findViewById(R.id.iv_share);

        View.OnClickListener backListnr = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*
                Intent intent = new Intent(pmediet.this, phomedashboard.class);
                startActivity(intent);

                 */
                finish();
            }
        };

        View.OnClickListener shareListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(pmediet.this, shareqr.class);
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
                Intent intent = new Intent(pmediet.this, pmedicaldocs.class);
                intent.putExtra("USER_DATA", ownerData);
                intent.putExtra("PET_DATA", petData);
                startActivity(intent);
                finish();
            }
        });

        meddiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(pmediet.this, pmediet.class);
                intent.putExtra("USER_DATA", ownerData);
                intent.putExtra("PET_DATA", petData);
                startActivity(intent);
                finish();
            }
        });

        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(pmediet.this, pabout.class);
                intent.putExtra("USER_DATA", ownerData);
                intent.putExtra("PET_DATA", petData);
                startActivity(intent);
                finish();
            }
        });

        schedules.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(pmediet.this, pschedules.class);
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
                Intent intent = new Intent(pmediet.this, phomedashboard.class);
                intent.putExtra("USER_DATA", ownerData);
                startActivity(intent);
                finish();
            }
        });

        pets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(pmediet.this, petspage.class);
                intent.putExtra("USER_DATA", ownerData);
                intent.putExtra("IS_PET_OWNER", true);
                startActivity(intent);
                finish();
            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(pmediet.this, petprofilepage.class);
                intent.putExtra("USER_DATA", ownerData);
                startActivity(intent);
                finish();
            }
        });

        files.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(pmediet.this, consolidatedsummary.class);
                intent.putExtra("USER_DATA", ownerData);
                intent.putExtra("IS_PET_OWNER", true);
                startActivity(intent);
                finish();
            }
        });

        calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(pmediet.this, appointmentspage.class);
                intent.putExtra("USER_DATA", ownerData);
                intent.putExtra("IS_PET_OWNER", true);
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
