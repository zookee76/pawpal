package com.example.pawpal.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pawpal.R;
import com.example.pawpal.Model.appointment;
import com.example.pawpal.ClinicOwner.cschedules;
import com.example.pawpal.PetOwner.pschedules;

import java.util.List;

public class appointmentAdapter extends RecyclerView.Adapter<appointmentAdapter.AppViewHolder> {

    public List<appointment> appointmentList;
    public Context cxt;
    private  String userType;

    public appointmentAdapter(Context cxt, List<appointment> appointmentList) {
        this.cxt = cxt;
        this.appointmentList = appointmentList;
    }

    public appointmentAdapter(Context cxt, List<appointment> appointmentList, boolean isPetOwner) {
        this.cxt = cxt;
        this.appointmentList = appointmentList;
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
        appointment Appointment = appointmentList.get(position);
        holder.appNo.setText("Appointment Number: " + Appointment.getAppNo());
        holder.petName.setText(Appointment.getPetName());
        holder.ownerName.setText(Appointment.getOwnerName());
        holder.appType.setText(Appointment.getAppType());
        holder.datetime.setText(Appointment.getAppDateTime());
        holder.vet.setText(Appointment.getAppVet());
        holder.status.setText(Appointment.getAppStatus());

        if("clinic".equals(userType)){
            holder.seeDeets.setOnClickListener(view -> {
                Intent intent = new Intent(cxt, cschedules.class);
                cxt.startActivity(intent);
            });
        }else{
            holder.seeDeets.setOnClickListener(view -> {
                Intent intent = new Intent(cxt, pschedules.class);
                cxt.startActivity(intent);
            });
        }
    }

    @Override
    public int getItemCount() {
        return appointmentList.size();
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
}
