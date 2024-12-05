package com.mobdeve.pawpal.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mobdeve.pawpal.ClinicOwner.cabout;
import com.mobdeve.pawpal.ClinicOwner.editclinicabout;
import com.mobdeve.pawpal.Database.DBHelper;
import com.mobdeve.pawpal.Model.appointment;
import com.mobdeve.pawpal.Model.clinicVet;
import com.mobdeve.pawpal.Model.pets;
import com.mobdeve.pawpal.R;

import java.util.List;


public class editscheduleAdapter extends RecyclerView.Adapter<editscheduleAdapter.scheduleVH> {
    private Context cxt;
    private List<appointment> appointmentList;
    private DBHelper DB;

    public editscheduleAdapter(Context cxt, List<appointment> appointmentList, DBHelper DB) {
        this.cxt = cxt;
        this.appointmentList = appointmentList;
        this.DB = DB;
    }

    @NonNull
    @Override
    public scheduleVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(cxt).inflate(R.layout.rv_editschedule, parent, false);
        return new editscheduleAdapter.scheduleVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull scheduleVH holder, int position) {
        appointment app = appointmentList.get(position);
        long appID = app.getAppNo();

        pets pet = DB.getPetByAppointment(appID);
        clinicVet vet = DB.getVetByAppointment(appID);

        String petName = app.getPetName(),
                ownerName = app.getOwnerName(),
                appType = app.getAppType(),
                appdatetime = app.getAppDateTime(),
                appvet = app.getAppVet(),
                appstatus = app.getAppStatus();

        long appNo = app.getAppNo();

        holder.appNo.setText(String.valueOf(appNo));
        holder.petName.setText(petName);
        holder.ownerName.setText(ownerName);
        holder.datetime.setText(appdatetime);
        holder.appType.setText(appType);
        holder.veterinarian.setText(appvet);
        holder.status.setText(appstatus);

        if(appstatus.equals("Completed")){
            holder.markAsComplete.setClickable(false);
            holder.markAsComplete.setText("Complete");
        }
        else{
            holder.markAsComplete.setClickable(true);
            holder.markAsComplete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String value = "Completed";
                    long appID = app.getAppNo();
                    Boolean isUpdated = DB.updateAppStatus(appID, value);
                    if(isUpdated){
                        Toast.makeText(cxt, "Pet updated successfully.", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(cxt, ((Activity) cxt).getClass());
                        intent.putExtra("PET_DATA", pet);
                        intent.putExtra("VET_DATA", vet);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                        cxt.startActivity(intent);
                        ((Activity) cxt).finish();
                    }else{
                        Toast.makeText(cxt, "Failed to update appointment.", Toast.LENGTH_SHORT).show();
                        Log.d("CHECKEDIT schedule", "FAILED TO UPDATE");
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return appointmentList.size();
    }

    class scheduleVH extends RecyclerView.ViewHolder{
        TextView appNo, petName, ownerName, appType, datetime, veterinarian, status;
        Button markAsComplete;
        public scheduleVH(@NonNull View itemView) {
            super(itemView);
            appNo = itemView.findViewById(R.id.tv_appointmentno);
            petName = itemView.findViewById(R.id.tv_inpet);
            ownerName = itemView.findViewById(R.id.tv_inowner);
            appType = itemView.findViewById(R.id.tv_inapptype);
            datetime = itemView.findViewById(R.id.tv_indatetime);
            veterinarian = itemView.findViewById(R.id.tv_invet);
            status = itemView.findViewById(R.id.tv_instatus);
            markAsComplete = itemView.findViewById(R.id.btn_seedeets);
        }
    }
}
