package com.mobdeve.pawpal.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.utils.widget.ImageFilterView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.mobdeve.pawpal.Database.DBHelper;
import com.mobdeve.pawpal.Model.dietmed;
import com.mobdeve.pawpal.R;

import java.io.File;
import java.util.List;

public class dietmedAdapter extends RecyclerView.Adapter<dietmedAdapter.dietmedViewHolder> {

    private Context cxt;
    private List<dietmed> dietmedList;
    private DBHelper DB;
    public dietmedAdapter(Context cxt, List<dietmed> dietmedList, DBHelper DB) {
        this.cxt = cxt;
        this.dietmedList = dietmedList;
        this.DB = DB;
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
        long imageID = dietmeds.getImageID();
        Log.d("IMAGE ID ON DIETMED", "IMAGE ID: " + imageID);
        if(imageID > 0){
            String imagePath = DB.getImagePath(imageID);
            if(imagePath != null){
                File imgFile = new File(imagePath);
                if(imgFile.exists()){
                    Glide.with(cxt)
                            .load(imgFile)
                            .into(holder.dietmedpic);
                }
            }
        }
        holder.dietmednotes.setText(dietmeds.getMedicationName());
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
