package com.example.pawpal.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.utils.widget.ImageFilterView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pawpal.Model.dietmed;
import com.example.pawpal.R;

import java.util.List;

public class dietmedAdapter extends RecyclerView.Adapter<dietmedAdapter.dietmedViewHolder> {

    private Context cxt;
    private List<dietmed> dietmedList;

    public dietmedAdapter(Context cxt, List<dietmed> dietmedList) {
        this.cxt = cxt;
        this.dietmedList = dietmedList;
    }

    @NonNull
    @Override
    public dietmedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(cxt).inflate(R.layout.rv_medlist, parent, false);
        return new dietmedViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull dietmedViewHolder holder, int position) {
        dietmed dietmeds = dietmedList.get(position);
        holder.dietmedpic.setImageResource(dietmeds.getImage());
        holder.dietmednotes.setText(dietmeds.getNote());
        holder.dietmedtimedate.setText(dietmeds.getDatetime());
    }

    @Override
    public int getItemCount() {
        return dietmedList.size();
    }

    class dietmedViewHolder extends RecyclerView.ViewHolder{

        ImageFilterView dietmedpic;
        TextView dietmednotes, dietmedtimedate;

        public dietmedViewHolder(@NonNull View itemView) {
            super(itemView);
            dietmedpic = itemView.findViewById(R.id.iv_medpic);
            dietmednotes = itemView.findViewById(R.id.tv_mednotes);
            dietmedtimedate = itemView.findViewById(R.id.tv_meddatetime);
        }
    }
}
