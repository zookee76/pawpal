package com.mobdeve.pawpal.Database;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.mobdeve.pawpal.IntentKeys;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "PawPalDB";
    private static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String petOwnerTable = "CREATE TABLE IF NOT EXISTS petOwners (" +
                "ID INTEGER PRIMARY KEY," +
                IntentKeys.FIRST_NAME + " TEXT NOT NULL," +
                IntentKeys.LAST_NAME + " TEXT NOT NULL," +
                IntentKeys.EMAIL + " TEXT NOT NULL," +
                IntentKeys.PASSWORD + " TEXT NOT NULL," +
                IntentKeys.PET_DP + " BLOB" +
                ")";

        String clinicOwnerTable = "CREATE TABLE clinicOwners (" +
                "ID INTEGER PRIMARY KEY," +
                IntentKeys.FIRST_NAME + " TEXT NOT NULL," +
                IntentKeys.LAST_NAME + " TEXT NOT NULL," +
                IntentKeys.EMAIL + " TEXT NOT NULL," +
                IntentKeys.PASSWORD + " TEXT NOT NULL" +
                ")";

        String petsTable = "CREATE TABLE pets (" +
                "ID INTEGER PRIMARY KEY," +
                IntentKeys.PET_NAME + " TEXT NOT NULL," +
                IntentKeys.PET_BREED + " TEXT NOT NULL," +
                IntentKeys.PET_SEX + " TEXT CHECK (sex IN ('M', 'F'))," +
                IntentKeys.PET_AGE + " INTEGER," +
                IntentKeys.PET_PROFILE + " TEXT NOT NULL," +
                IntentKeys.PET_BIRTHDATE + " DATE," +
                IntentKeys.PET_HEIGHT + " DOUBLE NOT NULL," +
                IntentKeys.PET_WEIGHT + " DOUBLE NOT NULL," +
                IntentKeys.PET_COLOR + " TEXT NOT NULL," +
                IntentKeys.PET_MARKINGS + " TEXT," +
                IntentKeys.PET_OWNER_ID + " INTEGER NOT NULL," +
                "FOREIGN KEY ("+IntentKeys.PET_OWNER_ID + ") REFERENCES petOwners(ID) ON DELETE CASCADE)";

        String petDetailsTable = "CREATE TABLE petdetails (" +
                "petID INTEGER PRIMARY KEY," +
                "description TEXT," +
                "FOREIGN KEY (petID) REFERENCES pets(petID))";

        String appointmentsTable = "CREATE TABLE appointments (" +
                "appNo INTEGER PRIMARY KEY," +
                "petName TEXT NOT NULL," +
                "ownerName TEXT NOT NULL," +
                "type TEXT," +
                "datetime DATETIME," +
                "vet TEXT," +
                "status TEXT CHECK (status IN ('Completed', 'Scheduled')))";

        String medicalDocTable = "CREATE TABLE medicaldoc (" +
                "docuNo INTEGER PRIMARY KEY," +
                "title TEXT NOT NULL," +
                "type TEXT NOT NULL," +
                "vet TEXT," +
                "datetime DATETIME," +
                "fileName TEXT)";

        String consolidatedRecordsTable = "CREATE TABLE consolidatedrecords (" +
                "recordNo INTEGER PRIMARY KEY," +
                "title TEXT NOT NULL," +
                "docType TEXT NOT NULL," +
                "docDate DATETIME," +
                "veterinarian TEXT," +
                "fileName TEXT)";

        String medicationTable = "CREATE TABLE medication (" +
                "medicationNo INTEGER PRIMARY KEY," +
                "prescriptionNo INTEGER NOT NULL," +
                "prescriptionDatetime DATETIME NOT NULL," +
                "medicationName TEXT NOT NULL," +
                "purpose TEXT NOT NULL," +
                "administration TEXT NOT NULL," +
                "frequency TEXT NOT NULL," +
                "note TEXT)";

        String dietTable = "CREATE TABLE diet (" +
                "dietNo INTEGER PRIMARY KEY," +
                "dietFood TEXT NOT NULL," +
                "nutritionFacts TEXT," +
                "instructions TEXT NOT NULL," +
                "note TEXT)";

        String vaccinationTable = "CREATE TABLE vaccination (" +
                "vaccNo INTEGER PRIMARY KEY," +
                "petName TEXT NOT NULL," +
                "ownerName TEXT NOT NULL," +
                "type TEXT NOT NULL," +
                "datetime DATETIME NOT NULL," +
                "vet TEXT," +
                "status TEXT CHECK (status IN ('Completed', 'Scheduled')))";

        String certRequestTable = "CREATE TABLE certRequest (" +
                "requestNo INTEGER PRIMARY KEY," +
                "petName TEXT NOT NULL," +
                "ownerName TEXT NOT NULL," +
                "type TEXT NOT NULL," +
                "datetime DATETIME NOT NULL," +
                "vet TEXT," +
                "fileName TEXT)";

        String petDocumentsTable = "CREATE TABLE petDocuments (" +
                "petID INTEGER NOT NULL," +
                "recordNo INTEGER NOT NULL," +
                "FOREIGN KEY (petID) REFERENCES pets(petID) ON DELETE CASCADE," +
                "FOREIGN KEY (recordNo) REFERENCES consolidatedrecords(recordNo) ON DELETE CASCADE," +
                "PRIMARY KEY (petID, recordNo))";

        String petMedicationsTable = "CREATE TABLE petMedications (" +
                "petID INTEGER NOT NULL," +
                "medicationNo INTEGER NOT NULL," +
                "FOREIGN KEY (petID) REFERENCES pets(petID) ON DELETE CASCADE," +
                "FOREIGN KEY (medicationNo) REFERENCES medication(medicationNo) ON DELETE CASCADE," +
                "PRIMARY KEY (petID, medicationNo))";

        String petAppointmentsTable = "CREATE TABLE petAppointments (" +
                "petID INTEGER NOT NULL," +
                "appNo INTEGER NOT NULL," +
                "FOREIGN KEY (petID) REFERENCES pets(petID) ON DELETE CASCADE," +
                "FOREIGN KEY (appNo) REFERENCES appointments(appNo) ON DELETE CASCADE," +
                "PRIMARY KEY (petID, appNo))";

        String petVaccinationsTable = "CREATE TABLE petVaccinations (" +
                "petID INTEGER NOT NULL," +
                "vaccNo INTEGER NOT NULL," +
                "FOREIGN KEY (petID) REFERENCES pets(petID) ON DELETE CASCADE," +
                "FOREIGN KEY (vaccNo) REFERENCES vaccination(vaccNo) ON DELETE CASCADE," +
                "PRIMARY KEY (petID, vaccNo))";

        String petMedicalDocsTable = "CREATE TABLE petMedicalDocs (" +
                "petID INTEGER NOT NULL," +
                "docuNo INTEGER NOT NULL," +
                "FOREIGN KEY (petID) REFERENCES pets(petID) ON DELETE CASCADE," +
                "FOREIGN KEY (docuNo) REFERENCES medicaldoc(docuNo) ON DELETE CASCADE," +
                "PRIMARY KEY (petID, docuNo))";

        sqLiteDatabase.execSQL(petOwnerTable);
        sqLiteDatabase.execSQL(clinicOwnerTable);
        sqLiteDatabase.execSQL(petsTable);
        sqLiteDatabase.execSQL(petDetailsTable);
        sqLiteDatabase.execSQL(appointmentsTable);
        sqLiteDatabase.execSQL(medicalDocTable);
        sqLiteDatabase.execSQL(consolidatedRecordsTable);
        sqLiteDatabase.execSQL(medicationTable);
        sqLiteDatabase.execSQL(dietTable);
        sqLiteDatabase.execSQL(vaccinationTable);
        sqLiteDatabase.execSQL(certRequestTable);
        sqLiteDatabase.execSQL(petDocumentsTable);
        sqLiteDatabase.execSQL(petMedicationsTable);
        sqLiteDatabase.execSQL(petAppointmentsTable);
        sqLiteDatabase.execSQL(petVaccinationsTable);
        sqLiteDatabase.execSQL(petMedicalDocsTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS petOwners");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS pets");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS petdetails");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS appointments");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS medicaldoc");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS consolidatedrecords");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS medication");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS diet");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS vaccination");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS certRequest");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS petDocuments");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS petMedications");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS petAppointments");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS petVaccinations");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS petMedicalDocs");

        onCreate(sqLiteDatabase);
    }
}
