package com.mobdeve.pawpal.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.utils.widget.ImageFilterView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.mobdeve.pawpal.Database.DBHelper;
import com.mobdeve.pawpal.Model.petOwners;
import com.mobdeve.pawpal.Model.pets;
import com.mobdeve.pawpal.R;
import com.mobdeve.pawpal.ClinicOwner.cabout;
import com.mobdeve.pawpal.PetOwner.pabout;

import java.io.File;
import java.util.List;

public class petAdapter extends RecyclerView.Adapter<petAdapter.petViewHolder> {

    private Context cxt;
    private List<pets> petsList;
    private String userType; // clinic or pet owner
    private DBHelper DB;
    private petOwners owner;

    public petAdapter(Context cxt, List<pets> petsList, DBHelper db, petOwners owner) {
        this.cxt = cxt;
        this.petsList = petsList;
        this.userType = "pet owner";
        this.DB = db;
        this.owner = owner;
    }

    public petAdapter(Context cxt, List<pets> petsList, boolean isPetOwner) {
        this.cxt = cxt;
        this.petsList = petsList;
        this.userType = isPetOwner ? "pet owner" : "clinic";
    }

    @NonNull
    @Override
    public petViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(cxt).inflate(R.layout.rv_pets, parent, false);
        return new petViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull petViewHolder holder, int position) {
        pets pet = petsList.get(position);
        long imageID = pet.getImageID();
        if(imageID > 0){
            String imagePath = DB.getImagePath(imageID);
            if(imagePath != null){
                File imgFile = new File(imagePath);
                if(imgFile.exists()){
                    Glide.with(cxt)
                            .load(imgFile)
                            .into(holder.petphoto);
                }
            }
        }

        holder.name.setText(pet.getName());
        holder.age.setText(String.valueOf(pet.getAge()) + " years old");
        holder.sex.setText(pet.getSex());
        holder.breed.setText(pet.getBreed());

        if ("clinic".equals(userType)) {
            holder.itemView.setOnClickListener(view -> {
                Intent intent = new Intent(cxt, cabout.class);

                cxt.startActivity(intent);
            });
        } else{
            //pet user
            holder.itemView.setOnClickListener(view -> {
                Intent intent = new Intent(cxt, pabout.class);
                intent.putExtra("OWNER_DATA", owner);
                intent.putExtra("PET_DATA", pet);
                cxt.startActivity(intent);
            });
        }
    }

    @Override
    public int getItemCount() {
        return petsList.size();
    }

    class petViewHolder extends RecyclerView.ViewHolder{

        ImageFilterView petphoto;
        TextView name, breed, sex, age;

        public petViewHolder(@NonNull View itemView) {
            super(itemView);
            petphoto = itemView.findViewById(R.id.iv_pet);
            name = itemView.findViewById(R.id.tv_petname);
            breed = itemView.findViewById(R.id.tv_breed);
            age = itemView.findViewById(R.id.tv_age);
            sex = itemView.findViewById(R.id.tv_sex);
        }
    }
}
