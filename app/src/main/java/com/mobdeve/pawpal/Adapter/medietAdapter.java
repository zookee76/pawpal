package com.mobdeve.pawpal.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mobdeve.pawpal.Database.DBHelper;
import com.mobdeve.pawpal.Model.dietmed;
import com.mobdeve.pawpal.R;
import java.util.List;

public class medietAdapter extends RecyclerView.Adapter<medietAdapter.medietViewHolder> {

    private Context cxt;
    private List<dietmed> dietmedList;
    private DBHelper DB;

    public medietAdapter(Context cxt, List<dietmed> dietmedList,  DBHelper db) {
        this.cxt = cxt;
        this.dietmedList = dietmedList;
        this.DB = db;
    }

    @NonNull
    @Override
    public medietViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(cxt).inflate(R.layout.rv_mediet, parent, false);
        return new medietViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull medietViewHolder holder, int position) {
        dietmed dietmed = dietmedList.get(position);

        holder.presTitle.setText("Prescription Record Number: " + dietmed.getPresNo());
        holder.presDate.setText("Prescription Date: " + dietmed.getDatetime());
        holder.medName.setText("Medication Name: " + dietmed.getMedicationName());
        holder.purpose.setText("Purpose: " + dietmed.getPurpose());
        holder.dosage.setText("Dosage: " + dietmed.getDosage());
        holder.administration.setText("Administration: " + dietmed.getAdministration());
        holder.freqdur.setText("Frequency & Duration: " + dietmed.getFreq_and_duration());
        holder.note.setText("Notes: " + dietmed.getNote());
    }

    @Override
    public int getItemCount() {
        return dietmedList.size();
    }

    class medietViewHolder extends RecyclerView.ViewHolder{
        TextView presTitle, presDate, medName, purpose, dosage, administration, freqdur, note;

        public medietViewHolder(@NonNull View itemView) {
            super(itemView);
            presTitle = itemView.findViewById(R.id.presTitle);
            presDate = itemView.findViewById(R.id.presDate);
            medName = itemView.findViewById(R.id.medname1);
            purpose = itemView.findViewById(R.id.medpurpose1);
            dosage = itemView.findViewById(R.id.meddosage1);
            administration = itemView.findViewById(R.id.medadministration1);
            freqdur = itemView.findViewById(R.id.medfrequencyduration1);
            note = itemView.findViewById(R.id.mednote1);
        }
    }
}
