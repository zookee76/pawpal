package com.mobdeve.pawpal.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mobdeve.pawpal.Model.petOwners;
import com.mobdeve.pawpal.R;
import com.mobdeve.pawpal.Shared.petspage;

import java.util.List;

public class childPetOwnerAdapter extends RecyclerView.Adapter<childPetOwnerAdapter.ChildViewHolder> {
    private Context cxt;
    private List<petOwners> petOwnersList;

    public childPetOwnerAdapter(List<petOwners> petOwnersList, Context cxt) {
        this.petOwnersList = petOwnersList;
        this.cxt = cxt;
    }

    @NonNull
    @Override
    public ChildViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_alphaowner, parent, false);
        return new ChildViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChildViewHolder holder, int position) {
        petOwners owners = petOwnersList.get(position);
        holder.ownerName.setText(owners.getFname());

        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(cxt, petspage.class);
            intent.putExtra("IS_PET_OWNER", false);
            intent.putExtra("PETS_PAGE_TITLE", "List of Pets");
            cxt.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return petOwnersList.size();
    }

    public class ChildViewHolder extends RecyclerView.ViewHolder{
        TextView ownerName;


        public ChildViewHolder(@NonNull View itemView) {
            super(itemView);
            ownerName = itemView.findViewById(R.id.tv_petownernames);
        }
    }
}
