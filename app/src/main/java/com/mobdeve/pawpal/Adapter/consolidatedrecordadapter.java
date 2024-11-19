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

import com.mobdeve.pawpal.Model.consolidatedrecords;
import com.mobdeve.pawpal.R;
import com.mobdeve.pawpal.ClinicOwner.cmedicaldocs;
import com.mobdeve.pawpal.PetOwner.pmedicaldocs;

import java.util.List;

public class consolidatedrecordadapter extends RecyclerView.Adapter<consolidatedrecordadapter.consolidatedRecordViewHolder> {

    private Context context;
    private List<consolidatedrecords> recordsList;
    private String userType;

    public consolidatedrecordadapter(Context context, List<consolidatedrecords> recordsList) {
        this.context = context;
        this.recordsList = recordsList;
    }

    public consolidatedrecordadapter(Context context, List<consolidatedrecords> recordsList, boolean isPetOwner) {
        this.context = context;
        this.recordsList = recordsList;
        this.userType = isPetOwner ? "pet owner" : "clinic";
    }

    @NonNull
    @Override
    public consolidatedRecordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rv_consolrecordscard, parent, false);
        return new consolidatedRecordViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull consolidatedRecordViewHolder holder, int position) {
        consolidatedrecords consolidatedrecord = recordsList.get(position);

        holder.titleText.setText(consolidatedrecord.getTitle());
        holder.docTypeText.setText(consolidatedrecord.getDocType());
        holder.docDateText.setText(consolidatedrecord.getDocDate());
        holder.veterinarianText.setText(consolidatedrecord.getVeterinarian());
        holder.fileText.setText(consolidatedrecord.getFile());

        if ("clinic".equals(userType)){
            holder.seeDetailsButton.setOnClickListener(v -> {
                // Handle button click (e.g., open details page, etc.)
                Intent intent = new Intent(context, cmedicaldocs.class);
                // Insert logic for handling of data
                context.startActivity(intent);
            });
        }
        else{
            holder.seeDetailsButton.setOnClickListener(v -> {
                // Handle button click (e.g., open details page, etc.)
                Intent intent = new Intent(context, pmedicaldocs.class);
                // Insert logic for handling of data
                context.startActivity(intent);
            });
        }
    }

    @Override
    public int getItemCount() {
        return recordsList.size();
    }

    public static class consolidatedRecordViewHolder extends RecyclerView.ViewHolder {

        TextView titleText, docTypeText, docDateText, veterinarianText, fileText;
        Button seeDetailsButton;

        public consolidatedRecordViewHolder(@NonNull View itemView) {
            super(itemView);

            titleText = itemView.findViewById(R.id.consolrecordtitle);
            docTypeText = itemView.findViewById(R.id.doctypetext);
            docDateText = itemView.findViewById(R.id.docdatetext);
            veterinarianText = itemView.findViewById(R.id.veterinarianText);
            fileText = itemView.findViewById(R.id.docfile);
            seeDetailsButton = itemView.findViewById(R.id.see_details);
        }
    }
}