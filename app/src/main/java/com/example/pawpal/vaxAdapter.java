package com.example.pawpal;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class vaxAdapter extends RecyclerView.Adapter<vaxAdapter.VaxViewHolder> {

    public List<vaccination> vaccinationList;
    public Context cxt;

    public vaxAdapter(Context cxt, List<vaccination> vaccinationList) {
        this.vaccinationList = vaccinationList;
        this.cxt = cxt;
    }

    @NonNull
    @Override
    public VaxViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_vaccination, parent, false);
        return new VaxViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VaxViewHolder holder, int position) {
        vaccination Vaccination = vaccinationList.get(position);
        holder.vaxType.setText(Vaccination.getVaccType());
        holder.petName.setText(Vaccination.getPetName());
        holder.ownerName.setText(Vaccination.getOwnerName());
        holder.datetime.setText(Vaccination.getVaxDateTime());
        holder.vet.setText(Vaccination.getVaxVet());
        holder.status.setText(Vaccination.getVaxStatus());

        holder.seeDeets.setOnClickListener(view -> {
            Intent intent = new Intent(cxt, pschedules.class);
            cxt.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return vaccinationList.size();
    }

    public class VaxViewHolder extends RecyclerView.ViewHolder{
        TextView vaxType, petName, ownerName, datetime, vet, status;
        Button seeDeets;

        public VaxViewHolder(@NonNull View itemView) {
            super(itemView);
            vaxType = itemView.findViewById(R.id.tv_vacctype);
            petName = itemView.findViewById(R.id.tv_vaxpet);
            ownerName = itemView.findViewById(R.id.tv_vaxowner);
            datetime = itemView.findViewById(R.id.tv_vaxdatetime);
            vet = itemView.findViewById(R.id.tv_vaxvet);
            status = itemView.findViewById(R.id.tv_vaxstatus);
            seeDeets = itemView.findViewById(R.id.btn_seedeets);
        }
    }
}
