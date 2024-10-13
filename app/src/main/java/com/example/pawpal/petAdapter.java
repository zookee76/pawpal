package com.example.pawpal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.utils.widget.ImageFilterView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class petAdapter extends RecyclerView.Adapter<petAdapter.petViewHolder> {

    private Context cxt;
    private List<pets> petsList;

    public petAdapter(Context cxt, List<pets> petsList) {
        this.cxt = cxt;
        this.petsList = petsList;
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
        holder.petphoto.setImageResource(pet.getPetphoto());
        holder.name.setText(pet.getName());
        holder.age.setText(String.valueOf(pet.getAge()) + " years old");
        holder.sex.setText(pet.getSex());
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
