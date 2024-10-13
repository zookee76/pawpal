package com.example.pawpal;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class phomedashboard extends AppCompatActivity {

    private RecyclerView rvDietMedList;
    private dietmedAdapter adapter;
    private List<dietmed> dietmedList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_phomedashboard);

        // RecyclerView Handle
        rvDietMedList = findViewById(R.id.rv_medlist);
        rvDietMedList.setLayoutManager(new LinearLayoutManager(this));

        dietmedList = new ArrayList<>();
        loadDietMedications();

        adapter = new dietmedAdapter(this, dietmedList);
        rvDietMedList.setAdapter(adapter);
    }

    private void loadDietMedications(){
        dietmedList.add(new dietmed(R.drawable.fiimage, "Aspirin", "10-09-2024, 08:00 AM"));
        dietmedList.add(new dietmed(R.drawable.fiimage, "Antibiotic", "11-09-2024, 10:00 AM"));
    }
}