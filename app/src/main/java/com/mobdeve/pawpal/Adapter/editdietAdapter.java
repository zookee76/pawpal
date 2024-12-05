package com.mobdeve.pawpal.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mobdeve.pawpal.Database.DBHelper;
import com.mobdeve.pawpal.Model.dietmed;
import com.mobdeve.pawpal.R;

import java.util.List;

public class editdietAdapter extends RecyclerView.Adapter<editdietAdapter.editVH> {
    private Context cxt;
    private List<dietmed> dietmedList;
    private DBHelper DB;

    public editdietAdapter(Context cxt, List<dietmed> dietmedList, DBHelper DB) {
        this.cxt = cxt;
        this.dietmedList = dietmedList;
        this.DB = DB;
    }

    @NonNull
    @Override
    public editVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(cxt).inflate(R.layout.rv_editdietmed, parent, false);
        return new editdietAdapter.editVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull editVH holder, int position) {
        dietmed item = dietmedList.get(position);
        long dietID = item.getPresNo();

        // Retrieve inputs from the EditText fields
        String medNameInput = holder.medName.getText().toString();
        String purposeInput = holder.purpose.getText().toString();
        String dosageInput = holder.dosage.getText().toString();
        String administrationInput = holder.administration.getText().toString();
        String freqDurInput = holder.freqdur.getText().toString();
        String noteInput = holder.note.getText().toString();

        holder.save.setOnClickListener(v -> {
            // Log the values or perform necessary actions
            Log.d("RecyclerViewInput", "MedName: " + medNameInput);
            Log.d("RecyclerViewInput", "Purpose: " + purposeInput);
            Log.d("RecyclerViewInput", "Dosage: " + dosageInput);
            Log.d("RecyclerViewInput", "Administration: " + administrationInput);
            Log.d("RecyclerViewInput", "FreqDur: " + freqDurInput);
            Log.d("RecyclerViewInput", "Note: " + noteInput);


            item.setMedicationName(medNameInput);
            item.setPurpose(purposeInput);
            item.setDosage(dosageInput);
            item.setAdministration(administrationInput);
            item.setFreq_and_duration(freqDurInput);
            item.setNote(noteInput);

            notifyItemChanged(position);
            boolean isUpdated = DB.updateDietMed(item);
            if (isUpdated) {
                Toast.makeText(cxt, "Record updated successfully!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(cxt, "Failed to update record!", Toast.LENGTH_SHORT).show();
            }
            notifyItemChanged(position);
        });


    }

    @Override
    public int getItemCount() {
        return dietmedList.size();
    }

    class editVH extends RecyclerView.ViewHolder{
        EditText  medName, purpose, dosage, administration, freqdur, note;
        TextView presTitle, presDate;
        Button save;
        public editVH(@NonNull View itemView) {
            super(itemView);
            presTitle = itemView.findViewById(R.id.presTitle);
            presDate = itemView.findViewById(R.id.presDate);
            medName = itemView.findViewById(R.id.text_medname);
            purpose = itemView.findViewById(R.id.text_purpose);
            dosage = itemView.findViewById(R.id.text_dosage);
            administration = itemView.findViewById(R.id.text_administration);
            freqdur = itemView.findViewById(R.id.text_freqdur);
            note = itemView.findViewById(R.id.text_notes);
            save = itemView.findViewById(R.id.btn_saveprofile);
        }
    }
}
