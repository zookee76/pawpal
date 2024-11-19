package com.mobdeve.pawpal.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.content.Context;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mobdeve.pawpal.Model.petOwners;
import com.mobdeve.pawpal.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class petOwnerAdapter extends RecyclerView.Adapter<petOwnerAdapter.PetOwnerViewHolder> {

    public Context cxt;
    public List<petOwners> petOwnersList;
    private Map<String, List<petOwners>> groupedOwners;
    private List<String> firstLetters;

    public petOwnerAdapter(Context cxt, List<petOwners> petOwnersList, Map<String, List<petOwners>> groupedOwners, List<String> firstLetters) {
        this.cxt = cxt;
        this.petOwnersList = petOwnersList;
        this.groupedOwners = groupedOwners;
        this.firstLetters = firstLetters;
    }

    @NonNull
    @Override
    public PetOwnerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rv_petowners, parent, false); // Change to your parent RecyclerView layout
        return new PetOwnerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PetOwnerViewHolder holder, int position) {
        String letter = firstLetters.get(position);
        holder.tvLetter.setText(letter);

        List<petOwners> ownersWSameLetter = groupedOwners.get(letter);
        childPetOwnerAdapter childAdapter = new childPetOwnerAdapter(ownersWSameLetter, cxt);
        holder.alphaOwner.setLayoutManager(new LinearLayoutManager(cxt));
        holder.alphaOwner.setAdapter(childAdapter);
    }

    @Override
    public int getItemCount() {
        return firstLetters.size();
    }

    public class PetOwnerViewHolder extends RecyclerView.ViewHolder{
        TextView tvLetter;
        RecyclerView alphaOwner;

        public PetOwnerViewHolder(@NonNull View itemView) {
            super(itemView);
            tvLetter = itemView.findViewById(R.id.tv_letter);
            alphaOwner = itemView.findViewById(R.id.rv_alphaowner);
        }
    }

    public void groupOwners(){
        for(petOwners owner: petOwnersList){
            String firstletter = owner.getName().substring(0,1).toUpperCase();
            if(!groupedOwners.containsKey(firstletter)){
                groupedOwners.put(firstletter,  new ArrayList<>());
                firstLetters.add(firstletter);
            }
            groupedOwners.get(firstletter).add(owner);
        }
        Collections.sort(firstLetters);
    }

}
