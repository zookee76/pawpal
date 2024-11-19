package com.mobdeve.pawpal.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mobdeve.pawpal.R;
import com.mobdeve.pawpal.Model.overviewentry;

import java.util.List;

public class overviewentryadapter extends RecyclerView.Adapter<overviewentryadapter.AppointmentViewHolder> {

    private Context context;
    private List<overviewentry> overviewentryList;


    public overviewentryadapter(Context context, List<overviewentry> overviewentryList) {
        this.context = context;
        this.overviewentryList = overviewentryList;
    }

    @NonNull
    @Override
    public AppointmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rv_overviewentries, parent, false);
        return new AppointmentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AppointmentViewHolder holder, int position) {
        overviewentry overviewentry = overviewentryList.get(position);

        holder.petImage.setImageResource(overviewentry.getPetImage());
        holder.timeText.setText(overviewentry.getTimeText());
        holder.petNameText.setText(overviewentry.getPetName());
        holder.ownerNameText.setText(overviewentry.getOwnerName());
        holder.appointmentTypeText.setText(overviewentry.getAppointmentType());
        holder.veterinarianText.setText(overviewentry.getVeterinarian());
    }

    @Override
    public int getItemCount() {
        return overviewentryList.size();
    }

    public static class AppointmentViewHolder extends RecyclerView.ViewHolder {

        ImageView petImage;
        TextView timeText, petNameText, ownerNameText, appointmentTypeText, veterinarianText;

        public AppointmentViewHolder(@NonNull View itemView) {
            super(itemView);
            petImage = itemView.findViewById(R.id.pet_image);
            timeText = itemView.findViewById(R.id.timeText);
            petNameText = itemView.findViewById(R.id.petNameText);
            ownerNameText = itemView.findViewById(R.id.ownerNameText);
            appointmentTypeText = itemView.findViewById(R.id.appointmentTypeText);
            veterinarianText = itemView.findViewById(R.id.veterinarianText);
        }
    }
}
