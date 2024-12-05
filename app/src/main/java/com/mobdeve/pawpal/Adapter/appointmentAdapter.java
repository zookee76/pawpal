package com.mobdeve.pawpal.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mobdeve.pawpal.Database.DBHelper;
import com.mobdeve.pawpal.Model.clinicVet;
import com.mobdeve.pawpal.Model.petOwners;
import com.mobdeve.pawpal.Model.pets;
import com.mobdeve.pawpal.R;
import com.mobdeve.pawpal.Model.appointment;
import com.mobdeve.pawpal.ClinicOwner.cschedules;
import com.mobdeve.pawpal.PetOwner.pschedules;

import java.util.ArrayList;
import java.util.List;

public class appointmentAdapter extends RecyclerView.Adapter<appointmentAdapter.AppViewHolder> {

    public List<appointment> appointmentList, filteredList;
    public Context cxt;
    private  String userType;
    private DBHelper DB;
    private int appointmentNumber = 1;
    private petOwners petowner;
    private clinicVet vet;

    public appointmentAdapter(Context cxt, List<appointment> appointmentList, DBHelper DB, petOwners owner) {
        this.cxt = cxt;
        this.appointmentList = appointmentList;
        this.filteredList = new ArrayList<>(appointmentList);
        this.DB  = DB;
        this.petowner = owner;
    }

    public appointmentAdapter(Context cxt, List<appointment> appointmentList, boolean isPetOwner, DBHelper DB, clinicVet vet) {
        this.cxt = cxt;
        this.appointmentList = appointmentList;
        this.filteredList = new ArrayList<>(appointmentList);
        this.vet = vet;
        this.DB = DB;
        this.userType = isPetOwner ? "pet owner" : "clinic";
    }

    @NonNull
    @Override
    public AppViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_appointments, parent, false);
        return new AppViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AppViewHolder holder, int position) {
        appointment Appointment = filteredList.get(position);
        String formattedAppNo = String.format("%04d", Appointment.getAppNo());
        holder.appNo.setText("Appointment Number: " + formattedAppNo);
        holder.petName.setText(Appointment.getPetName());
        holder.ownerName.setText(Appointment.getOwnerName());
        holder.appType.setText(Appointment.getAppType());
        holder.datetime.setText(Appointment.getAppDateTime());
        holder.vet.setText(Appointment.getAppVet());
        holder.status.setText(Appointment.getAppStatus());

        if("clinic".equals(userType)){
            holder.seeDeets.setOnClickListener(view -> {
                long appID = Appointment.getAppNo();
                pets pet = DB.getPetByAppointment(appID);
                Intent intent = new Intent(cxt, cschedules.class);
                intent.putExtra("PET_DATA", pet);
                intent.putExtra("USER_DATA", vet);
                cxt.startActivity(intent);
            });
        }else{
            holder.seeDeets.setOnClickListener(view -> {
                long appID = Appointment.getAppNo();
                pets pet = DB.getPetByAppointment(appID);

                Intent intent = new Intent(cxt, pschedules.class);
                intent.putExtra("USER_DATA", petowner);
                intent.putExtra("PET_DATA", pet);
                cxt.startActivity(intent);
            });
        }
    }

    @Override
    public int getItemCount() {
        return filteredList.size();
    }

    public class AppViewHolder extends RecyclerView.ViewHolder{
        TextView appNo, petName, ownerName, appType, datetime, vet, status;
        Button seeDeets;

        public AppViewHolder(@NonNull View itemView) {
            super(itemView);
            appNo = itemView.findViewById(R.id.tv_appointmentno);
            petName = itemView.findViewById(R.id.tv_inpet);
            ownerName = itemView.findViewById(R.id.tv_inowner);
            appType = itemView.findViewById(R.id.tv_inapptype);
            datetime = itemView.findViewById(R.id.tv_indatetime);
            vet = itemView.findViewById(R.id.tv_invet);
            status = itemView.findViewById(R.id.tv_instatus);
            seeDeets = itemView.findViewById(R.id.btn_seedeets);
        }
    }

    public void filter (String query){
        filteredList.clear();
        if(query.isEmpty()){
            filteredList.addAll(appointmentList);
        }else{
            for(appointment app: appointmentList){
                if(app.getAppType().toLowerCase().contains(query.toLowerCase()) ||
                app.getPetName().toLowerCase().contains(query.toLowerCase())){
                    filteredList.add(app);
                }
            }
        }
        notifyDataSetChanged();
    }
}
