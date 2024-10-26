package com.example.pawpal;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class clinicpetsAdapter extends RecyclerView.Adapter<clinicpetsAdapter.clinicpetViewHolder> {

    public List<pets> clinicpetsList;
    public Context cxt;

    public clinicpetsAdapter(List<pets> clinicpetsList, Context cxt) {
        this.clinicpetsList = clinicpetsList;
        this.cxt = cxt;
    }

    @NonNull
    @Override
    public clinicpetViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_clinicpets, parent, false);
        return new clinicpetViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull clinicpetViewHolder holder, int position) {
        pets clinicPets = clinicpetsList.get(position);
        holder.pet.setImageResource(clinicPets.getPetphoto());
        holder.petname.setText(clinicPets.getName());

        holder.pet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(cxt, cabout.class);
                // add logic for getting and passing specific pet data
                cxt.startActivity(intent);
            }
        });

        holder.petname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(cxt, cabout.class);
                // add logic for getting and passing specific pet data
                cxt.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return clinicpetsList.size();
    }

    public class clinicpetViewHolder extends RecyclerView.ViewHolder{
        ImageView pet;
        TextView petname;

        public clinicpetViewHolder(@NonNull View itemView) {
            super(itemView);
            pet = itemView.findViewById(R.id.iv_clinicpet);
            petname = itemView.findViewById(R.id.tV_clinicpetname);
        }
    }
}
