package com.example.pawpal;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class phomedashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_phomedashboard);

        // RecyclerView Handle
        /*
        RecyclerView medlist = findViewById(R.id.rv_medlist);

        List<DietaryMedItem> medItem = new ArrayList<>();
        medItem.add(new DietaryMedItem(R.drawable.fiimage, "Add 1 teaspoon of Coconut Oil to food for skin health.nDosage: Once daily.", "Date: 2024-10-02 | Time: 8:00 AM"));

        DietaryMedAdapter adapter = new DietaryMedAdapter(this, medItem);
        medlist.setLayoutManager(new LinearLayoutManager(this));
        medlist.setAdapter(adapter);

         */
    }
}