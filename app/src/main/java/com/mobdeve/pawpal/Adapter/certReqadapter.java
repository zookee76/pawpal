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

import com.mobdeve.pawpal.R;
import com.mobdeve.pawpal.Model.certRequest;
import com.mobdeve.pawpal.ClinicOwner.editCert;

import java.util.List;

public class certReqadapter extends RecyclerView.Adapter<certReqadapter.RequestViewHolder> {

    private Context context;
    private List<certRequest> requestList;

    public certReqadapter(Context context, List<certRequest> requestList) {
        this.context = context;
        this.requestList = requestList;
    }

    @NonNull
    @Override
    public RequestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.rv_clinicrequestcard, parent, false);
        return new RequestViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RequestViewHolder holder, int position) {

        certRequest request = requestList.get(position);

        holder.requestNumberText.setText("Request Number: " + request.getRequestNumber());
        holder.petNameText.setText("Pet Name: " + request.getPetName());
        holder.ownerNameText.setText("Owner Name: " + request.getOwnerName());
        holder.requestTypeText.setText("Request Type: " + request.getRequestType());
        holder.dateText.setText("Date: " + request.getDate());
        holder.statusText.setText("Status: " + request.getStatus());

        holder.editRequestButton.setOnClickListener(v -> {
            Intent intent = new Intent(context, editCert.class);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return requestList.size();
    }

    public static class RequestViewHolder extends RecyclerView.ViewHolder {

        TextView requestNumberText, petNameText, ownerNameText, requestTypeText, dateText, statusText;
        Button editRequestButton;

        public RequestViewHolder(@NonNull View itemView) {
            super(itemView);

            requestNumberText = itemView.findViewById(R.id.requestNumberText);
            petNameText = itemView.findViewById(R.id.petNameText);
            ownerNameText = itemView.findViewById(R.id.ownerNameText);
            requestTypeText = itemView.findViewById(R.id.requestTypeText);
            dateText = itemView.findViewById(R.id.dateText);
            statusText = itemView.findViewById(R.id.statusText);
            editRequestButton = itemView.findViewById(R.id.editRequestButton);
        }
    }
}
