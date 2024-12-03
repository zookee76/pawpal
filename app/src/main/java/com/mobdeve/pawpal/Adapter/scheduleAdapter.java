package com.mobdeve.pawpal.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mobdeve.pawpal.Database.DBHelper;
import com.mobdeve.pawpal.Model.appointment;
import com.mobdeve.pawpal.R;

import java.util.List;

public class scheduleAdapter extends RecyclerView.Adapter<scheduleAdapter.scheduleVH>{

    private Context cxt;
    private List<appointment> appointmentList;
    private DBHelper DB;

    public scheduleAdapter(Context cxt, List<appointment> appointmentList, DBHelper DB) {
        this.cxt = cxt;
        this.appointmentList = appointmentList;
        this.DB = DB;
    }

    @NonNull
    @Override
    public scheduleVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(cxt).inflate(R.layout.rv_profileappointments, parent, false);
        return new scheduleAdapter.scheduleVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull scheduleVH holder, int position) {
        appointment app = appointmentList.get(position);

        holder.schedTitle.setText("Appointment Number: " + app.getAppNo());
        holder.appType.setText("Appointment Type: " + app.getAppType());
        holder.datetime.setText("Date and Time: " + app.getAppDateTime());
        holder.veterinarian.setText("Veterinarian: " + app.getAppVet());
        holder.status.setText("Status: " + app.getAppStatus());
    }

    @Override
    public int getItemCount() {
        return appointmentList.size();
    }

    class scheduleVH extends RecyclerView.ViewHolder{
        TextView schedTitle, appType, datetime, veterinarian, status;

        public scheduleVH(@NonNull View itemView) {
            super(itemView);
            schedTitle = itemView.findViewById(R.id.scheduleTitle);
            appType = itemView.findViewById(R.id.appointmenttype);
            datetime = itemView.findViewById(R.id.dateandtime);
            veterinarian = itemView.findViewById(R.id.veterinarian);
            status = itemView.findViewById(R.id.status);
        }
    }
}
