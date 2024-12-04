package com.mobdeve.pawpal.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.utils.widget.ImageFilterView;
import androidx.recyclerview.widget.RecyclerView;

import com.mobdeve.pawpal.Model.dietmed;
import com.mobdeve.pawpal.Model.nutinstructions;
import com.mobdeve.pawpal.R;

import java.util.List;

public class dietinstructionsAdapter extends RecyclerView.Adapter<dietinstructionsAdapter.instructionsVH> {

    private Context cxt;
    private List<nutinstructions> instructionsList;

    public dietinstructionsAdapter(Context cxt, List<nutinstructions> instructionsList) {
        this.cxt = cxt;
        this.instructionsList = instructionsList;
    }

    @NonNull
    @Override
    public instructionsVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(cxt).inflate(R.layout.rv_instructions, parent, false);
        return new dietinstructionsAdapter.instructionsVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull instructionsVH holder, int position) {
        nutinstructions step = instructionsList.get(position);
        String numberedStep = (position + 1) + ". " + step.getStep();
        holder.step.setText(numberedStep);
    }

    @Override
    public int getItemCount() {
        return instructionsList.size();
    }

    class instructionsVH extends RecyclerView.ViewHolder{
        TextView step;

        public instructionsVH(@NonNull View itemView) {
            super(itemView);
            step = itemView.findViewById(R.id.step);
        }
    }
}
