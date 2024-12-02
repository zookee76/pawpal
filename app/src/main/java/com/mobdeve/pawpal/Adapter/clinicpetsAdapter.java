package com.mobdeve.pawpal.Adapter;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.mobdeve.pawpal.Database.DBHelper;
import com.mobdeve.pawpal.Model.pets;
import com.mobdeve.pawpal.Model.images;
import com.mobdeve.pawpal.R;
import com.mobdeve.pawpal.ClinicOwner.cabout;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class clinicpetsAdapter extends RecyclerView.Adapter<clinicpetsAdapter.clinicpetViewHolder> {

    public List<pets> clinicpetsList;
    public Context cxt;
    private DBHelper db;
    private List<images> imagesList;

    public clinicpetsAdapter(List<pets> clinicpetsList, Context cxt, DBHelper db, List<images> images) {
        this.clinicpetsList = clinicpetsList;
        this.cxt = cxt;
        this.db = db;
        this.imagesList = images;
    }

    @NonNull
    @Override
    public clinicpetViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_clinicpets, parent, false);
        return new clinicpetViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull clinicpetViewHolder holder, int position) {
        if(position >= 0 && position <clinicpetsList.size()){
            pets clinicPets = clinicpetsList.get(position);

            Log.d("Adapter", "clinicpetsList size: " + clinicpetsList.size());
            Log.d("Adapter", "imagesList size: " + imagesList.size());

            long imageID = clinicPets.getImageID();
            String imagePath = db.getImagePath(imageID);

            if(imagePath != null){
                File imgFile = new File(imagePath);
                if(imgFile.exists()){
                    Glide.with(cxt)
                            .load(imgFile)
                            .into(holder.pet);
                }
            }

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
        }else{
            Log.e("onBindViewHolder", "Position " + position + " is out of bounds for either clinicpetsList or imagesList");
        }
    }

    @Override
    public int getItemCount() {
        return clinicpetsList.size();
    }

    public void updateLists(List<pets> petsList, List<images> imagesList){
        if(!(petsList.size()==imagesList.size())){
            petsList.clear();
            imagesList.clear();

            List<pets> newPetList = db.getAllPets();
            List<images> newImageList = db.getAllImages();

            Log.d("NEWLISTS", "PETS: "+ newPetList.size() + " IMAGES: " + newImageList.size());

            petsList.addAll(newPetList);
            imagesList.addAll(newImageList);

            notifyDataSetChanged();
        }

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
