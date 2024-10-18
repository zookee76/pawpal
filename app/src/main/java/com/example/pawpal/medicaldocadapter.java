package com.example.pawpal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.utils.widget.ImageFilterView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class medicaldocadapter extends RecyclerView.Adapter<medicaldocadapter.medicaldocViewHolder> {

    private Context cxt;
    private List<medicaldoc> medicaldocList;

    public medicaldocadapter(Context cxt, List<medicaldoc> medicaldocList) {
        this.cxt = cxt;
        this.medicaldocList = medicaldocList;
    }

    @NonNull
    public medicaldocViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(cxt).inflate(R.layout.rv_medicaldoccard, parent, false);

        return new medicaldocViewHolder(view);
    }

    public void onBindViewHolder(@NonNull medicaldocViewHolder holder, int position) {
        medicaldoc medicaldocs = medicaldocList.get(position);
        holder.meddoctitle.setText(medicaldocs.getTitle());
        holder.meddoctype.setText(medicaldocs.getType());
        holder.meddocdate.setText(medicaldocs.getDate());
        holder.meddocvet.setText(medicaldocs.getVeterinarian());
        holder.meddocfile.setText(medicaldocs.getFileName());

        holder.meddocdl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    public int getItemCount() {
        return medicaldocList.size();
    }

    class medicaldocViewHolder extends RecyclerView.ViewHolder{

        TextView meddoctitle;
        TextView meddoctype;
        TextView meddocdate;
        TextView meddocvet;
        TextView meddocfile;
        Button meddocdl;

        public medicaldocViewHolder(View itemView) {
            super(itemView);

            meddoctitle = itemView.findViewById(R.id.meddoctitle);
            meddoctype = itemView.findViewById(R.id.meddoctype);
            meddocdate = itemView.findViewById(R.id.meddocdate);
            meddocvet = itemView.findViewById(R.id.meddocvet);
            meddocfile = itemView.findViewById(R.id.meddocfile);
            meddocdl = itemView.findViewById(R.id.meddocdl);
        }
    }
}
