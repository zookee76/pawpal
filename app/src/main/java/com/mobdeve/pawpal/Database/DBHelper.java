package com.mobdeve.pawpal.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;

import com.mobdeve.pawpal.Model.appointment;
import com.mobdeve.pawpal.Model.clinicVet;
import com.mobdeve.pawpal.Model.consolidatedrecords;
import com.mobdeve.pawpal.Model.dietmed;
import com.mobdeve.pawpal.Model.nutinstructions;
import com.mobdeve.pawpal.Model.nutritionfacts;
import com.mobdeve.pawpal.Model.nutritionplan;
import com.mobdeve.pawpal.Model.petOwners;
import com.mobdeve.pawpal.Model.pets;
import com.mobdeve.pawpal.Model.images;
import com.mobdeve.pawpal.Model.vaccination;
import com.mobdeve.pawpal.Shared.userType;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class DBHelper extends SQLiteOpenHelper {
    public static final int DB_VER = 1;
    public static final String DB_NAME = "pawpalDB";

    public static final String
            _ID = "id",
    // Tables
            TABLE_NAME_PETOWNER = "petOwner",
            TABLE_NAME_VET = "veterinarians", // clinic owner/users
            TABLE_NAME_PET = "pets",
            TABLE_IMAGES = "images",
            TABLE_VACCINATION = "vaccinations",
            TABLE_APPOINTMENTS = "appointments",
            TABLE_DIETMED = "dietmed",
            TABLE_CONSOLIDATED_RECORDS = "consolidatedRecords",
            TABLE_NUTRITION_PLANS = "nutritionPlans",
            TABLE_NUTRITION_FACTS = "nutritionFacts",
            TABLE_NUTRITION_STEPS = "nutritionSteps",

    // consolidated records columns
            COLUMN_RECORD_NO = "recordNo",
            RECORD_TITLE = "title",
            DOC_TYPE = "docType",
            DOC_DATE = "docDate",
            VETERINARIAN = "veterinarian",
            RECORD_FILE = "file",

    // PetOwner and Vet Columns
    COLUMN_FIRST_NAME = "firstName",
            COLUMN_LAST_NAME = "lastName",
            COLUMN_EMAIL = "email",
            COLUMN_PASSWORD = "password",
            COLUMN_USERNAME = "username",
            COLUMN_CONTACT_NO = "contactno",

    // Pet Owner Columns specific
    COLUMN_OWNER_DP = "owner_dp",

    // Vet Columns specific
    COLUMN_VET_DP = "vetDP",

    // Pets Columns
    COLUMN_PET_NAME ="petName",
            COLUMN_PET_BREED = "petBreed",
            COLUMN_PET_SEX = "petSex",
            COLUMN_PET_COLOR = "petColor",
            COLUMN_PET_MARKINGS = "petMarkings",
            COLUMN_PET_AGE = "petAge",
            COLUMN_PET_HEIGHT = "petHeight",
            COLUMN_PET_WEIGHT = "petWeight",
            COLUMN_PET_PHOTO = "petPhoto",
            COLUMN_PET_BIRTHDATE = "petBirthdate",
            COLUMN_PETOWNER_ID = "ownerId",
            COLUMN_VET_ID = "vetID",
    // IMAGES
    COLUMN_IMAGE_PATH = "path",
            COLUMN_USER_TYPE = "userType",
            COLUMN_OWNER_ID = "ownerID",

    // SHARED DIETMED, VACCINATION, APPOINTMENT
    COLUMN_PET_ID = "petID", // petID
            COLUMN_OWNER_NAME = "ownerName", // ownerName
            COLUMN_VET_NAME = "vetName",

    // DIETMED
    COLUMN_PET_IMG = "petImg", // imageID
            COLUMN_MED_NAME = "medName",
            COLUMN_PURPOSE = "purpose",
            COLUMN_DOSAGE = "dosage",
            COLUMN_ADMINISTRATION = "administration",
            COLUMN_FREQ_DURATION = "freqAndDuration",
            COLUMN_NOTE = "note",
            COLUMN_DATETIME = "datetime",

    // VACINATION
    // OWNER ID = COLUMN_OWNER_ID
    // VET ID = COLUMN_VET_ID
    // PETID = COLUMN_PET_ID
    COLUMN_VAX_TYPE = "vaxType",
    // petName = COLUMN_PET_NAME
    // vaxDatetime = COLUMN_DATETIME
    //COLUMN_OWNER_NAME = "ownerName",
    // vaxVet = COLUMN_VET_NAME
    COLUMN_VAX_STATUS = "vaxStatus",
    COLUMN_APP_ID = "appID",

    // APPOINTMENTS
    //APP NO = _ID
    // PETID = COLUMN_PET_ID
    // OWNER ID = COLUMN_OWNER_ID
    // VET ID = COLUMN_VET_ID
    // petName = COLUMN_PET_NAME
    //COLUMN_OWNER_NAME = "ownerName",
    COLUMN_APP_TYPE = "appType",
    // appVet = COLUMN_VET_NAME
    COLUMN_APP_STATUS = "appStatus",

    // NUTRITION PLAN
    // PET ID
    // VET ID
    COLUMN_FOOD_NAME = "diet",
    COLUMN_SERVING_SIZE = "servingSize",
    // note = COLUMN_NOTE

    // NUTRITION FACTS
    COLUMN_DIET_ID = "dietID",
    COLUMN_CALORIES = "calories",
    COLUMN_FAT = "fat",
    COLUMN_FIBER = "fiber",
    COLUMN_PROTEIN = "protein",
    COLUMN_KEY_NUTRIENTS = "keyNutrients",

    // NUTRITION INSTRUCTIONS (sTEPS)
    //COLUMN_DIET_ID = "dietID",
    COLUMN_STEP = "step";

    // CREATE STATEMENTS
    public static final String CREATE_TABLE_PET_OWNER =
            "CREATE TABLE IF NOT EXISTS " + TABLE_NAME_PETOWNER + " (" +
                    _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_FIRST_NAME + " TEXT NOT NULL, " +
                    COLUMN_LAST_NAME + " TEXT NOT NULL, " +
                    COLUMN_EMAIL + " TEXT NOT NULL, " +
                    COLUMN_PASSWORD + " TEXT NOT NULL, " +
                    COLUMN_USERNAME + " TEXT NOT NULL, " +
                    COLUMN_OWNER_DP + " INTEGER DEFAULT NULL, " +
                    COLUMN_CONTACT_NO + " TEXT NOT NULL, " +
                    "FOREIGN KEY (" + COLUMN_OWNER_DP + ") REFERENCES " + TABLE_IMAGES + " (" + _ID + "))";

    public static final String CREATE_TABLE_VET =
            "CREATE TABLE IF NOT EXISTS " + TABLE_NAME_VET + " (" +
                    _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_FIRST_NAME + " TEXT NOT NULL, " +
                    COLUMN_LAST_NAME + " TEXT NOT NULL, " +
                    COLUMN_EMAIL + " TEXT NOT NULL, " +
                    COLUMN_PASSWORD + " TEXT NOT NULL, " +
                    COLUMN_VET_DP + " INTEGER DEFAULT NULL, " +
                    COLUMN_CONTACT_NO + " TEXT NOT NULL, " +
                    "FOREIGN KEY (" + COLUMN_VET_DP + ") REFERENCES " + TABLE_IMAGES + " (" + _ID + "))";

    public static final String CREATE_TABLE_PETS =
            "CREATE TABLE IF NOT EXISTS " + TABLE_NAME_PET + " (" +
                    _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_PET_NAME + " TEXT NOT NULL, " +
                    COLUMN_PET_BREED + " TEXT NOT NULL, " +
                    COLUMN_PET_SEX + " TEXT NOT NULL, " +
                    COLUMN_PET_COLOR + " TEXT, " +
                    COLUMN_PET_MARKINGS + " TEXT, " +
                    COLUMN_PET_AGE + " INTEGER, " +
                    COLUMN_PET_HEIGHT + " REAL, " +
                    COLUMN_PET_WEIGHT + " REAL, " +
                    COLUMN_PET_BIRTHDATE + " TEXT, " +
                    COLUMN_PET_PHOTO + " INTEGER DEFAULT NULL, " +
                    COLUMN_OWNER_ID + " INTEGER NOT NULL, " +
                    COLUMN_VET_ID + " INTEGER NOT NULL, " +
                    "FOREIGN KEY (" + COLUMN_PET_PHOTO + ") REFERENCES " + TABLE_IMAGES + " (" + _ID + "), " +
                    "FOREIGN KEY (" + COLUMN_VET_ID + ") REFERENCES " + TABLE_NAME_VET + " (" + _ID + "), " +
                    "FOREIGN KEY (" + COLUMN_PETOWNER_ID + ") REFERENCES " + TABLE_NAME_PETOWNER + " (" + _ID + "))";

    public static final String CREATE_TABLE_IMAGES =
            "CREATE TABLE IF NOT EXISTS " + TABLE_IMAGES + " (" +
                    _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_IMAGE_PATH + " TEXT NOT NULL, " +
                    COLUMN_USER_TYPE + " INTEGER NOT NULL, " +
                    COLUMN_OWNER_ID + " INTEGER NOT NULL)";

    public static final String CREATE_TABLE_APPOINTMENTS =
            "CREATE TABLE IF NOT EXISTS " + TABLE_APPOINTMENTS + " (" +
                    _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_PET_ID + " INTEGER NOT NULL, " +
                    COLUMN_PET_NAME + " TEXT NOT NULL, " +
                    COLUMN_OWNER_ID + " INTEGER NOT NULL, " +
                    COLUMN_VET_ID + " INTEGER NOT NULL, " +
                    COLUMN_VET_NAME + " TEXT NOT NULL, " +
                    COLUMN_DATETIME + " TEXT NOT NULL, " +
                    COLUMN_APP_TYPE + " TEXT NOT NULL, " +
                    COLUMN_APP_STATUS + " TEXT NOT NULL, " +
                    COLUMN_OWNER_NAME + " TEXT NOT NULL, " +
                    "FOREIGN KEY (" + COLUMN_VET_ID + ") REFERENCES " + TABLE_NAME_VET + " (" + _ID + "), " +
                    "FOREIGN KEY (" + COLUMN_OWNER_ID + ") REFERENCES " + TABLE_NAME_PETOWNER + " (" + _ID + "), " +
                    "FOREIGN KEY (" + COLUMN_PET_ID + ") REFERENCES " + TABLE_NAME_PET + " (" + _ID + "))";

    public static final String CREATE_TABLE_DIETMED =
            "CREATE TABLE IF NOT EXISTS " + TABLE_DIETMED + " (" +
                    _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_PET_IMG + " INTEGER NOT NULL, " +
                    COLUMN_PET_ID + " INTEGER NOT NULL, " +
                    COLUMN_MED_NAME + " TEXT NOT NULL, " +
                    COLUMN_PURPOSE + " TEXT NOT NULL, " +
                    COLUMN_DOSAGE + " TEXT NOT NULL, " +
                    COLUMN_ADMINISTRATION + " TEXT NOT NULL, " +
                    COLUMN_FREQ_DURATION + " TEXT NOT NULL, " +
                    COLUMN_NOTE + " TEXT NOT NULL, " +
                    COLUMN_DATETIME + " TEXT NOT NULL, " +
                    "FOREIGN KEY (" + COLUMN_PET_IMG + ") REFERENCES " + TABLE_IMAGES + " (" + _ID + "), " +
                    "FOREIGN KEY (" + COLUMN_PET_ID + ") REFERENCES " + TABLE_NAME_PET + " (" + _ID + "))";

    public static final String CREATE_TABLE_VACCINATIONS =
            "CREATE TABLE IF NOT EXISTS " + TABLE_VACCINATION + " (" +
                    _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_PET_ID + " INTEGER NOT NULL, " +
                    COLUMN_OWNER_ID + " INTEGER NOT NULL, " +
                    COLUMN_VET_ID + " INTEGER NOT NULL, " +
                    COLUMN_VAX_TYPE + " TEXT NOT NULL, " +
                    COLUMN_PET_NAME + " TEXT NOT NULL, " +
                    COLUMN_OWNER_NAME + " TEXT NOT NULL, " +
                    COLUMN_VET_NAME + " TEXT NOT NULL, " +
                    COLUMN_DATETIME + " TEXT NOT NULL, " +
                    COLUMN_VAX_STATUS + " TEXT NOT NULL, " +
                    COLUMN_APP_ID + " INTEGER NOT NULL, " +
                    "FOREIGN KEY (" + COLUMN_VET_ID + ") REFERENCES " + TABLE_NAME_VET + " (" + _ID + "), " +
                    "FOREIGN KEY (" + COLUMN_APP_ID + ") REFERENCES " + TABLE_APPOINTMENTS + " (" + _ID + "), " +
                    "FOREIGN KEY (" + COLUMN_OWNER_ID + ") REFERENCES " + TABLE_NAME_PETOWNER + " (" + _ID + "), " +
                    "FOREIGN KEY (" + COLUMN_PET_ID + ") REFERENCES " + TABLE_NAME_PET + " (" + _ID + "))";


    public static final String CREATE_CONSOLIDATED_TABLE =
            "CREATE TABLE IF NOT EXISTS " + TABLE_CONSOLIDATED_RECORDS + " (" +
                    COLUMN_RECORD_NO + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    RECORD_TITLE + " TEXT NOT NULL, " +
                    DOC_TYPE + " TEXT NOT NULL, " +
                    DOC_DATE + " DATETIME NOT NULL, " +
                    VETERINARIAN + " TEXT NOT NULL, " +
                    RECORD_FILE + " TEXT NOT NULL)";

    public static final String CREATE_TABLE_NUTRITION_PLANS =
            "CREATE TABLE IF NOT EXISTS " + TABLE_NUTRITION_PLANS + " (" +
                    _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_PET_ID + " INTEGER NOT NULL, " +
                    COLUMN_VET_ID + " INTEGER NOT NULL, " +
                    COLUMN_FOOD_NAME + " TEXT NOT NULL, " +
                    COLUMN_SERVING_SIZE + " TEXT NOT NULL, " +
                    COLUMN_NOTE + " TEXT NOT NULL, " +
                    "FOREIGN KEY (" + COLUMN_PET_ID + ") REFERENCES " + TABLE_NAME_PET + " (" + _ID + "), " +
                    "FOREIGN KEY (" + COLUMN_VET_ID + ") REFERENCES " + TABLE_NAME_VET + " (" + _ID + "))";


    public static final String CREATE_TABLE_NUTRITION_FACTS =
            "CREATE TABLE IF NOT EXISTS " + TABLE_NUTRITION_FACTS + " (" +
                    _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_DIET_ID + " INTEGER NOT NULL, " +
                    COLUMN_CALORIES + " TEXT NOT NULL, " +
                    COLUMN_FAT + " TEXT NOT NULL, " +
                    COLUMN_FIBER + " TEXT NOT NULL, " +
                    COLUMN_PROTEIN + " TEXT NOT NULL, " +
                    COLUMN_KEY_NUTRIENTS + " TEXT NOT NULL, " +
                    "FOREIGN KEY (" + COLUMN_DIET_ID + ") REFERENCES " + TABLE_NUTRITION_PLANS + " (" + _ID + "))";

    public static final String CREATE_TABLE_NUTRITION_STEPS =
            "CREATE TABLE IF NOT EXISTS " + TABLE_NUTRITION_STEPS + " (" +
                    _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_DIET_ID + " INTEGER NOT NULL, " +
                    COLUMN_STEP + " TEXT NOT NULL, " +
                    "FOREIGN KEY (" + COLUMN_DIET_ID + ") REFERENCES " + TABLE_NUTRITION_PLANS + " (" + _ID + "))";


    public DBHelper(Context context){
        super(context, DB_NAME, null, DB_VER);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL("PRAGMA foreign_keys = ON;");
            db.execSQL(CREATE_TABLE_PETS);
            db.execSQL(CREATE_TABLE_PET_OWNER);
            db.execSQL(CREATE_TABLE_IMAGES);
            db.execSQL(CREATE_TABLE_VET);
            db.execSQL(CREATE_TABLE_APPOINTMENTS);
            db.execSQL(CREATE_TABLE_DIETMED);
            db.execSQL(CREATE_TABLE_VACCINATIONS);
            db.execSQL(CREATE_CONSOLIDATED_TABLE);
            db.execSQL(CREATE_TABLE_NUTRITION_PLANS);
            db.execSQL(CREATE_TABLE_NUTRITION_FACTS);
            db.execSQL(CREATE_TABLE_NUTRITION_STEPS);
            Log.d("DBHELPER", "Tables created successfully");
        } catch (Exception e) {
            Log.e("DBHELPER", "Error creating tables", e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        String DROP = "DROP TABLE IF EXISTS ";
        db.execSQL(DROP + " " + TABLE_NAME_PET);
        db.execSQL(DROP + " " + TABLE_NAME_PETOWNER);
        db.execSQL(DROP + " " + TABLE_IMAGES);
        db.execSQL(DROP + " " + TABLE_NAME_VET);
        db.execSQL(DROP + " " + TABLE_APPOINTMENTS);
        db.execSQL(DROP + " " + TABLE_DIETMED);
        db.execSQL(DROP + " " + TABLE_VACCINATION);
        db.execSQL(DROP + " " + TABLE_CONSOLIDATED_RECORDS);
        db.execSQL(DROP + " " + TABLE_NUTRITION_PLANS);
        db.execSQL(DROP + " " + TABLE_NUTRITION_FACTS);
        db.execSQL(DROP + " " + TABLE_NUTRITION_STEPS);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS petOwners");
        db.execSQL("DROP TABLE IF EXISTS clinicOwners");
        db.execSQL("DROP TABLE IF EXISTS images");
        db.execSQL("DROP TABLE IF EXISTS " +TABLE_NAME_VET);
        db.execSQL("DROP TABLE IF EXISTS " +TABLE_APPOINTMENTS);
        db.execSQL("DROP TABLE IF EXISTS " +TABLE_DIETMED);
        db.execSQL("DROP TABLE IF EXISTS " +TABLE_VACCINATION);
        db.execSQL("DROP TABLE IF EXISTS " +TABLE_CONSOLIDATED_RECORDS);
        db.execSQL("DROP TABLE IF EXISTS " +TABLE_NUTRITION_PLANS);
        db.execSQL("DROP TABLE IF EXISTS " +TABLE_NUTRITION_FACTS);
        db.execSQL("DROP TABLE IF EXISTS " +TABLE_NUTRITION_STEPS);
        // Drop other tables as needed
        onCreate(db); // Recreate tables
    }

   /*
   CRUD
    */

    // CREATE
    public synchronized long addPetOwner(petOwners owner){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_FIRST_NAME, owner.getFname());
        values.put(COLUMN_LAST_NAME, owner.getLname());
        values.put(COLUMN_EMAIL, owner.getEmail());
        values.put(COLUMN_PASSWORD, owner.getPassword());
        values.put(COLUMN_USERNAME, owner.getUsername());
        values.put(COLUMN_CONTACT_NO, owner.getContactNo());
        values.put(COLUMN_OWNER_DP, owner.getImageID());

        long rowID = db.insert(TABLE_NAME_PETOWNER, null, values);
        db.close();
        return rowID;
    }

    public synchronized long addVet(clinicVet vet){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_FIRST_NAME, vet.getFirstName());
        values.put(COLUMN_LAST_NAME, vet.getLastName());
        values.put(COLUMN_EMAIL, vet.getEmailAdd());
        values.put(COLUMN_PASSWORD, vet.getPassword());
        values.put(COLUMN_CONTACT_NO, vet.getContactNo());
        values.put(COLUMN_VET_DP, vet.getImageID());

        long rowID = db.insert(TABLE_NAME_VET, null, values);
        db.close();
        return rowID;
    }

    public synchronized long addPet(pets pet){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_PET_NAME, pet.getName());
        values.put(COLUMN_PET_BREED, pet.getBreed());
        values.put(COLUMN_PET_SEX, pet.getSex());
        values.put(COLUMN_PET_COLOR, pet.getColor());
        values.put(COLUMN_PET_MARKINGS, pet.getMarkings());
        values.put(COLUMN_PET_AGE, pet.getAge());
        values.put(COLUMN_PET_HEIGHT, pet.getHeight());
        values.put(COLUMN_PET_WEIGHT, pet.getWeight());
        values.put(COLUMN_PET_PHOTO, pet.getImageID());
        values.put(COLUMN_PET_BIRTHDATE, pet.getBirthdate().toString());
        values.put(COLUMN_OWNER_ID, pet.getOwnerID());
        values.put(COLUMN_VET_ID, pet.getVetID());

        long result = db.insert(TABLE_NAME_PET, null, values);
        db.close();
        return result;
    }

    public synchronized long addNutritionPlan(nutritionplan plan){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_PET_ID, plan.getPetID());
        values.put(COLUMN_VET_ID, plan.getVetID());
        values.put(COLUMN_FOOD_NAME, plan.getDietname());
        values.put(COLUMN_SERVING_SIZE, plan.getServingSize());
        values.put(COLUMN_NOTE, plan.getNote());

        long result = db.insert(TABLE_NUTRITION_PLANS, null, values);
        db.close();
        return result;
    }

    public synchronized long addNutritionFact(nutritionfacts fact){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_DIET_ID, fact.getNutplanID());
        values.put(COLUMN_CALORIES, fact.getCalories());
        values.put(COLUMN_FAT, fact.getFat());
        values.put(COLUMN_FIBER, fact.getFiber());
        values.put(COLUMN_PROTEIN, fact.getProtein());
        values.put(COLUMN_KEY_NUTRIENTS, fact.getKeyNutrients());


        long result = db.insert(TABLE_NUTRITION_FACTS, null, values);
        db.close();
        return result;
    }

    public synchronized long addNutritionStep(nutinstructions step){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_DIET_ID, step.getNutplanID());
        values.put(COLUMN_STEP, step.getStep());

        long result = db.insert(TABLE_NUTRITION_STEPS, null, values);
        db.close();
        return result;
    }

    public synchronized long addAppointment(appointment app){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_PET_ID, app.getPetID());
        values.put(COLUMN_OWNER_ID, app.getOwnerID());
        values.put(COLUMN_VET_ID, app.getVetID());
        values.put(COLUMN_PET_NAME, app.getPetName());
        values.put(COLUMN_OWNER_NAME, app.getOwnerName());
        values.put(COLUMN_VAX_TYPE, app.getAppType());
        values.put(COLUMN_DATETIME, app.getAppDateTime());
        values.put(COLUMN_VET_NAME, app.getAppVet());
        values.put(COLUMN_VAX_STATUS, app.getAppStatus());

        long result = db.insert(TABLE_APPOINTMENTS, null, values);
        db.close();
        return result;
    }

    public synchronized long addDietmed(dietmed dimed){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_PET_ID, dimed.getImage());
        values.put(COLUMN_PET_IMG, dimed.getPetID());
        values.put(COLUMN_MED_NAME, dimed.getMedicationName());
        values.put(COLUMN_PURPOSE, dimed.getPurpose());
        values.put(COLUMN_DOSAGE, dimed.getDosage());
        values.put(COLUMN_ADMINISTRATION, dimed.getAdministration());
        values.put(COLUMN_FREQ_DURATION, dimed.getFreq_and_duration());
        values.put(COLUMN_NOTE, dimed.getNote());
        values.put(COLUMN_DATETIME, dimed.getDatetime());

        long result = db.insert(TABLE_DIETMED, null, values);
        db.close();
        return result;
    }

    public synchronized long addVaccination(vaccination vax){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_PET_ID, vax.getPetID());
        values.put(COLUMN_PETOWNER_ID, vax.getOwnerID());
        values.put(COLUMN_VET_ID, vax.getVetID());
        values.put(COLUMN_PET_NAME, vax.getPetName());
        values.put(COLUMN_OWNER_NAME, vax.getOwnerName());
        values.put(COLUMN_APP_TYPE, vax.getVaccType());
        values.put(COLUMN_DATETIME, vax.getVaxVet());
        values.put(COLUMN_VET_NAME, vax.getVaxDateTime());
        values.put(COLUMN_APP_STATUS, vax.getVaxStatus());

        long result = db.insert(TABLE_APPOINTMENTS, null, values);
        db.close();
        return result;
    }

    public long addImage(String imagePath, long ownerID, int userType){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_IMAGE_PATH, imagePath);
        values.put(COLUMN_OWNER_ID, ownerID);
        values.put(COLUMN_USER_TYPE, userType);

        long result = db.insert(TABLE_IMAGES, null, values);
        return result;
    }


    // READ
    public boolean checkIfUserExists(String email){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        try {
            cursor = db.query(
                    TABLE_NAME_PETOWNER,
                    null,
                    COLUMN_EMAIL + " =?",
                    new String[]{email},
                    null, null, null);

            boolean exists = cursor.getCount() > 0;
            return exists;
        } finally {
            if (cursor != null) cursor.close();
            db.close();
        }
    }

    public boolean checkIfVetExists(String email){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        try {
            cursor = db.query(
                    TABLE_NAME_VET,
                    null,
                    COLUMN_EMAIL + " =?",
                    new String[]{email},
                    null, null, null);

            boolean exists = cursor.getCount() > 0;
            return exists;
        } finally {
            if (cursor != null) cursor.close();
            db.close();
        }
    }

    public List<petOwners> getPetOwners(){
        List<petOwners> petOwnersList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM "+ TABLE_NAME_PETOWNER;
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do{
                String fname = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_FIRST_NAME));
                String lname = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_LAST_NAME));
                petOwners owner = new petOwners(fname, lname);
                petOwnersList.add(owner);
            }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return petOwnersList;
    }

    public int getOwnerID(String fullname){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        int ownerID = -1;

        try {
            String[] nameParts = fullname.split(" ");
            if (nameParts.length == 2) {
                String firstName = nameParts[0];
                String lastName = nameParts[1];

                cursor = db.query(
                        TABLE_NAME_PETOWNER,
                        new String[]{_ID}, // Get the owner ID
                        COLUMN_FIRST_NAME + " =? AND " + COLUMN_LAST_NAME + " =?",
                        new String[]{firstName, lastName},
                        null, null, null
                );

                if (cursor != null && cursor.moveToFirst()) {
                    ownerID = cursor.getInt(cursor.getColumnIndexOrThrow(_ID));
                }
            }
        } catch (Exception e) {
            Log.e("DB", "Error getting owner ID", e);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            db.close();
        }

        return ownerID;
    }

    public petOwners checkLogin(String logincredential, String password){
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM " + TABLE_NAME_PETOWNER +
                " WHERE (" + COLUMN_EMAIL + " =? OR " +
                COLUMN_USERNAME + " =?) AND " +
                COLUMN_PASSWORD + " =?";
        Cursor cursor = db.rawQuery(query, new String[]{logincredential, logincredential, password});

        petOwners owner = null;
        if(cursor.moveToFirst()){
            String email = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_EMAIL));
            String username = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_USERNAME));
            String hashedpassword = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PASSWORD));
            String fname = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_FIRST_NAME));
            String lname = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_LAST_NAME));
            String contact = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CONTACT_NO));
            long imageID = cursor.getLong(cursor.getColumnIndexOrThrow(COLUMN_OWNER_DP));
            long ownerID = cursor.getLong(cursor.getColumnIndexOrThrow(_ID));

            owner = new petOwners(ownerID, imageID, fname, lname, email, hashedpassword, username, contact);
        }
        cursor.close();
        db.close();
        return owner;
    }

    public clinicVet checkVetLogin(String logincredential, String password){
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM " + TABLE_NAME_VET +
                " WHERE " + COLUMN_EMAIL + " =? AND " +
                COLUMN_PASSWORD + " =?";
        Cursor cursor = db.rawQuery(query, new String[]{logincredential, password});

        clinicVet vet = null;
        if(cursor.moveToFirst()){
            String email = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_EMAIL));
            String hashedpassword = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PASSWORD));
            String fname = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_FIRST_NAME));
            String lname = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_LAST_NAME));
            String contact = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CONTACT_NO));
            long imageID = cursor.getLong(cursor.getColumnIndexOrThrow(COLUMN_VET_DP));
            long vetID = cursor.getLong(cursor.getColumnIndexOrThrow(_ID));

            Log.d("CHECK VET ID DB", "VETID: "+vetID);
            vet = new clinicVet(vetID, imageID, fname, lname, email, hashedpassword, contact);
        }
        cursor.close();
        db.close();
        return vet;
    }

    public List<pets> getPetsByVet(long vetID){
        List<pets> petsList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT * FROM " + TABLE_NAME_PET +
                " WHERE " + COLUMN_VET_ID + " = ?";

        Cursor cursor = db.rawQuery(selectQuery, new String[]{String.valueOf(vetID)});

        if (cursor.moveToFirst()){
            do{
                long imageID = cursor.getLong(cursor.getColumnIndexOrThrow(COLUMN_PET_PHOTO));

                pets pet = new pets(
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PET_NAME)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PET_BREED)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PET_SEX)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PET_COLOR)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PET_MARKINGS)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PET_BIRTHDATE)),
                        cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_PET_AGE)),
                        cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_PET_HEIGHT)),
                        cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_PET_WEIGHT)),
                        cursor.getLong(cursor.getColumnIndexOrThrow(_ID)),
                        imageID,
                        cursor.getLong(cursor.getColumnIndexOrThrow(COLUMN_OWNER_ID)),
                        cursor.getLong(cursor.getColumnIndexOrThrow(COLUMN_VET_ID))
                );
                petsList.add(pet);
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return petsList;
    }

    public List<appointment> getPetAppointment(long petID){
        List<appointment> appointmentList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT * FROM " + TABLE_APPOINTMENTS +
                " WHERE " + COLUMN_PET_ID + " = ?";

        Cursor cursor = db.rawQuery(selectQuery, new String[]{String.valueOf(petID)});

        if (cursor.moveToFirst()){
            do{
                appointment app = new appointment(
                        cursor.getLong(cursor.getColumnIndexOrThrow(_ID)),
                        cursor.getLong(cursor.getColumnIndexOrThrow(COLUMN_PET_ID)),
                        cursor.getLong(cursor.getColumnIndexOrThrow(COLUMN_OWNER_ID)),
                        cursor.getLong(cursor.getColumnIndexOrThrow(COLUMN_VET_ID)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PET_NAME)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_OWNER_NAME)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_APP_TYPE)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DATETIME)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_VET_NAME)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_APP_STATUS))
                );
                appointmentList.add(app);
                Log.d("CHECK APPOINTMENT", " APPNO: " + app.getAppNo());
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return appointmentList;
    }

    public List<appointment> getAppointmentByPetOwner(long ownerID){
        List<appointment> appointmentList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT * FROM " + TABLE_APPOINTMENTS +
                " WHERE " + COLUMN_OWNER_ID + " = ?";

        Cursor cursor = db.rawQuery(selectQuery, new String[]{String.valueOf(ownerID)});

        if (cursor.moveToFirst()){
            do{
                appointment app = new appointment(
                        cursor.getLong(cursor.getColumnIndexOrThrow(_ID)),
                        cursor.getLong(cursor.getColumnIndexOrThrow(COLUMN_PET_ID)),
                        cursor.getLong(cursor.getColumnIndexOrThrow(COLUMN_OWNER_ID)),
                        cursor.getLong(cursor.getColumnIndexOrThrow(COLUMN_VET_ID)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PET_NAME)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_OWNER_NAME)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_APP_TYPE)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DATETIME)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_VET_NAME)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_APP_STATUS))
                );
                appointmentList.add(app);
                Log.d("CHECK APPOINTMENT", " APPNO: " + app.getAppNo());
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return appointmentList;
    }

    public List<dietmed> getPetDietmed(long petID){
        List<dietmed> dietmedList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT * FROM " + TABLE_DIETMED +
                " WHERE " + COLUMN_PET_ID + " = ?";

        Cursor cursor = db.rawQuery(selectQuery, new String[]{String.valueOf(petID)});

        if (cursor.moveToFirst()){
            do{
                dietmed dmed = new dietmed(
                        cursor.getLong(cursor.getColumnIndexOrThrow(COLUMN_PET_IMG)),
                        cursor.getLong(cursor.getColumnIndexOrThrow(_ID)),
                        cursor.getLong(cursor.getColumnIndexOrThrow(COLUMN_PET_ID)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_MED_NAME)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PURPOSE)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DOSAGE)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ADMINISTRATION)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_FREQ_DURATION)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NOTE)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DATETIME))
                );
                dietmedList.add(dmed);
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return dietmedList;
    }

    public List<vaccination> getPetVaccination(long petID){
        List<vaccination> vaccinationList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT * FROM " + TABLE_VACCINATION +
                " WHERE " + COLUMN_PET_ID + " = ?";

        Cursor cursor = db.rawQuery(selectQuery, new String[]{String.valueOf(petID)});

        if (cursor.moveToFirst()){
            do{
                vaccination vaccination = new vaccination(
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_VAX_TYPE)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PET_NAME)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_OWNER_NAME)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DATETIME)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_VET_NAME)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_VAX_STATUS)),
                        cursor.getLong(cursor.getColumnIndexOrThrow(_ID)),
                        cursor.getLong(cursor.getColumnIndexOrThrow(COLUMN_PET_ID)),
                        cursor.getLong(cursor.getColumnIndexOrThrow(COLUMN_OWNER_ID)),
                        cursor.getLong(cursor.getColumnIndexOrThrow(COLUMN_VET_ID))
                );
                vaccinationList.add(vaccination);
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return vaccinationList;
    }

    public vaccination getSpecificVaccination(long appID){
        SQLiteDatabase db = this.getReadableDatabase();
        vaccination vax = null;

        String query = "SELECT * FROM " + TABLE_VACCINATION +
                " WHERE " + COLUMN_APP_ID + " = ?";

        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(appID)});
        if (cursor != null && cursor.moveToFirst()){
            vax = new vaccination();
            vax.setVaxID(cursor.getLong(cursor.getColumnIndexOrThrow(_ID)));
            vax.setPetID(cursor.getLong(cursor.getColumnIndexOrThrow(COLUMN_PET_ID)));
            vax.setOwnerID(cursor.getLong(cursor.getColumnIndexOrThrow(COLUMN_OWNER_ID)));
            vax.setVetID(cursor.getLong(cursor.getColumnIndexOrThrow(COLUMN_VET_ID)));
            vax.setAppID(cursor.getLong(cursor.getColumnIndexOrThrow(COLUMN_APP_ID)));
            vax.setVaccType(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_VAX_TYPE)));
            vax.setPetName(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PET_NAME)));
            vax.setOwnerName(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_OWNER_NAME)));
            vax.setVaxVet(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_VET_NAME)));
            vax.setVaxDateTime(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DATETIME)));
            vax.setVaxStatus(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_VAX_STATUS)));
        }
        if (cursor != null) {
            cursor.close();
        }
        return vax;
    }

    public appointment getLatestAppByPetOwner(long ownerID){
        SQLiteDatabase db = this.getReadableDatabase();
        List<appointment> upcomingAppointments = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        String query = "SELECT * FROM " + TABLE_APPOINTMENTS +
                " INNER JOIN " + TABLE_VACCINATION +
                " ON " + TABLE_APPOINTMENTS + "." + COLUMN_DATETIME + " = " + TABLE_VACCINATION + "." + COLUMN_DATETIME +
                " AND " + TABLE_APPOINTMENTS + "." + COLUMN_OWNER_ID + " = " + TABLE_VACCINATION + "." + COLUMN_OWNER_ID +
                " WHERE " + TABLE_APPOINTMENTS + "." + COLUMN_OWNER_ID + " = ?";

        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(ownerID)});

        if (cursor != null && cursor.moveToFirst()) {
            Date currentDate = new Date();
            do {
                appointment app = new appointment();
                app.setAppNo(cursor.getLong(cursor.getColumnIndexOrThrow(_ID)));
                app.setPetID(cursor.getLong(cursor.getColumnIndexOrThrow(COLUMN_PET_ID)));
                app.setVetID(cursor.getLong(cursor.getColumnIndexOrThrow(COLUMN_VET_ID)));
                app.setOwnerID(cursor.getLong(cursor.getColumnIndexOrThrow(COLUMN_OWNER_ID)));
                app.setPetName(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PET_NAME)));
                app.setAppVet(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_VET_NAME)));
                app.setOwnerName(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_OWNER_NAME)));
                app.setAppType(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_APP_TYPE)));
                app.setAppDateTime(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DATETIME)));
                app.setAppStatus(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_APP_STATUS)));

                try {
                    Date appointmentDate = dateFormat.parse(app.getAppDateTime());
                    Log.d("CHECK APPOINTMENT", "APP DATETIME: "+ app.getAppDateTime() + "currentdate: "+currentDate);
                    if (appointmentDate != null && appointmentDate.after(currentDate)) {
                        upcomingAppointments.add(app);
                    }
                } catch (Exception e) {
                    Log.d("CHECK APPOINTMENT", "ERROR");
                    e.printStackTrace();
                }
            } while (cursor.moveToNext());
        }

        if (cursor != null) {
            cursor.close();
        }
        if (!upcomingAppointments.isEmpty()) {
            appointment earliestApp = upcomingAppointments.get(0);
            long earliestTimeStamp = 0;

            for (appointment app : upcomingAppointments) {
                try {
                    Date appointmentDate = dateFormat.parse(app.getAppDateTime());

                    if (appointmentDate != null && appointmentDate.getTime() < earliestTimeStamp || earliestTimeStamp == 0) {
                        earliestTimeStamp = appointmentDate.getTime();
                        earliestApp = app;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            Log.d("CHECK APPOINTMENTS ", "APPOINTMENT: "+ earliestApp.getAppNo());
            return earliestApp;
        } else {
            Log.d("CHECK APPOINTMENTS ", "NO APPOINTMENTS");
            return null;  // No upcoming appointments
        }
    }

    public List<pets> getAllPets(){
        List<pets> petsList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM "+ TABLE_NAME_PET;
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do{
                long imageID = cursor.getLong(cursor.getColumnIndexOrThrow(COLUMN_PET_PHOTO));

                pets pet = new pets(
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PET_NAME)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PET_BREED)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PET_SEX)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PET_COLOR)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PET_MARKINGS)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PET_BIRTHDATE)),
                        cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_PET_AGE)),
                        cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_PET_HEIGHT)),
                        cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_PET_WEIGHT)),
                        cursor.getLong(cursor.getColumnIndexOrThrow(_ID)),
                        imageID,
                        cursor.getLong(cursor.getColumnIndexOrThrow(COLUMN_OWNER_ID))
                );
                petsList.add(pet);
            }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return petsList;
    }

    public List<pets> getPetsByOwner(long ownerID){
        SQLiteDatabase db = this.getReadableDatabase();
        List<pets> pets = new ArrayList<>();

        Cursor cursor = db.query(
                TABLE_NAME_PET,
                null,
                COLUMN_OWNER_ID + " =?",
                new String[]{String.valueOf(ownerID)},
                null,
                null,
                null
        );

        if(cursor != null & cursor.moveToNext()){
            do{
                long petID = cursor.getLong(cursor.getColumnIndexOrThrow(_ID));
                String petName = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PET_NAME));
                String petBreed = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PET_BREED));
                String petSex = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PET_SEX));
                String petColor = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PET_COLOR));
                String petMarkings = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PET_MARKINGS));
                int petAge = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_PET_AGE));
                Double petHeight = cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_PET_HEIGHT));
                Double petWeight = cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_PET_WEIGHT));
                String petBirthdate = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PET_BIRTHDATE));
                long petPhotoID = cursor.getLong(cursor.getColumnIndexOrThrow(COLUMN_PET_PHOTO));

                pets pet = new pets(petName, petBreed, petSex, petColor, petMarkings, petBirthdate, petAge, petHeight, petWeight, petID, petPhotoID, ownerID);

                pets.add(pet);
            } while(cursor.moveToNext());
            cursor.close();
        }
        db.close();
        Log.d("loadPets", "Loaded " + pets.size() + " pets for owner ID " + ownerID);

        return pets;
    }

    public pets getPetByAppointment(long appID){
        SQLiteDatabase db = this.getReadableDatabase();
        List<pets> pets = new ArrayList<>();

        String query = "SELECT p.* FROM " + TABLE_NAME_PET + " p " +
                "JOIN " + TABLE_APPOINTMENTS + " a ON p." + _ID + " = a." + COLUMN_PET_ID + " " +
                "WHERE a." + _ID + " = ?";

        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(appID)});

        if (cursor != null && cursor.moveToFirst()){
            pets pet = new pets();
            pet.setID(cursor.getLong(cursor.getColumnIndexOrThrow(_ID)));
            pet.setOwnerID(cursor.getLong(cursor.getColumnIndexOrThrow(COLUMN_OWNER_ID)));
            pet.setImageID(cursor.getLong(cursor.getColumnIndexOrThrow(COLUMN_PET_PHOTO)));
            pet.setVetID(cursor.getLong(cursor.getColumnIndexOrThrow(COLUMN_VET_ID)));
            pet.setName(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PET_NAME)));
            pet.setColor(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PET_COLOR)));
            pet.setBreed(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PET_BREED)));
            pet.setSex(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PET_SEX)));
            pet.setMarkings(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PET_MARKINGS)));
            pet.setBirthdate(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PET_BIRTHDATE)));
            pet.setAge(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_PET_AGE)));
            pet.setWeight(cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_PET_WEIGHT)));
            pet.setHeight(cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_PET_HEIGHT)));

            cursor.close();
            return pet;
        }
        if (cursor != null) {
            cursor.close();
        }
        return null;
    }

    public  boolean isPetsTableEmpty(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM " +TABLE_NAME_PET, null);
        cursor.moveToFirst();
        int count = cursor.getInt(0);
        cursor.close();
        return count == 0;
    }

    public List<images> getAllImages(){
        List<images> imagesList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String[] projection = {
                _ID,
                COLUMN_IMAGE_PATH,
                COLUMN_USER_TYPE,
                COLUMN_OWNER_ID
        };

        Cursor cursor = db.query(
                TABLE_IMAGES,
                projection,
                null, null, null, null, null
        );

        if(cursor!=null && cursor.moveToFirst()){
            do{
                int id = cursor.getInt(cursor.getColumnIndexOrThrow(_ID));
                String imagePath = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_IMAGE_PATH));
                int ownerID = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_OWNER_ID));
                int typeInt = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_USER_TYPE));
                userType type = userType.values()[typeInt - 1];

                images image = new images(imagePath, ownerID, type);
                image.setId(id);
                imagesList.add(image);
            }while (cursor.moveToNext());
            cursor.close();
        }
        return  imagesList;
    }

    public List<images> getImagesbyVet(long petImgID){
        List<images> imagesList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT * FROM " + TABLE_IMAGES +
                " WHERE " + _ID + " = ?";

        Cursor cursor = db.rawQuery(selectQuery, new String[]{String.valueOf(petImgID)});

        if (cursor.moveToFirst()){
            do{
                images img = new images();
                img.setId(cursor.getInt(cursor.getColumnIndexOrThrow(_ID)));
                img.setOwnerId(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_OWNER_ID)));
                img.setImagePath(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_IMAGE_PATH)));
                int typeInt = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_USER_TYPE));
                userType type = userType.values()[typeInt - 1];
                img.setUserType(type);
                imagesList.add(img);
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return imagesList;
    }

    public String getImagePath(long imageID){
        SQLiteDatabase db = this.getReadableDatabase();
        String imagePath = null;
        Cursor cursor = db.query(TABLE_IMAGES, new String[]{COLUMN_IMAGE_PATH}, _ID + " = ?",
                new String[]{String.valueOf(imageID)},
                null, null, null);

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                imagePath = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_IMAGE_PATH));
            }
            cursor.close();
        }
        db.close();
        return imagePath;
    }

    public nutritionplan getNutritionPlanByPet(long retrievedPetID){
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM " + TABLE_NUTRITION_PLANS +
                " WHERE " + COLUMN_PET_ID + " =? ";

        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(retrievedPetID)});

        if(cursor.moveToFirst()){
            nutritionplan plan = new nutritionplan();
            plan.setDietname(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_FOOD_NAME)));
            plan.setServingSize(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_SERVING_SIZE)));
            plan.setNote(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NOTE)));
            plan.setPetID(cursor.getLong(cursor.getColumnIndexOrThrow(COLUMN_PET_ID)));
            plan.setVetID(cursor.getLong(cursor.getColumnIndexOrThrow(COLUMN_VET_ID)));
            plan.setID(cursor.getLong(cursor.getColumnIndexOrThrow(_ID)));
            /*
            String foodName = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_FOOD_NAME));
            String servingsize = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_SERVING_SIZE));
            String note = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NOTE));
            long petID = cursor.getLong(cursor.getColumnIndexOrThrow(COLUMN_PET_ID));
            long vetID = cursor.getLong(cursor.getColumnIndexOrThrow(COLUMN_VET_ID));
            long dietID = cursor.getLong(cursor.getColumnIndexOrThrow(_ID));
             */

            cursor.close();
            db.close();
            Log.d("NUTPLANCHECK", "NUTID: "+plan.getID());
            return plan;
        }else{
            cursor.close();
            db.close();
            Log.d("NUTPLANCHECK", "NO NUT PLAN");
            return null;
        }
    }

    public nutritionfacts getFactsByDiet(long dietID){
        nutritionfacts fact = new nutritionfacts();
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT * FROM " + TABLE_NUTRITION_FACTS +
                " WHERE " + COLUMN_DIET_ID + " = ?";

        Cursor cursor = db.rawQuery(selectQuery, new String[]{String.valueOf(dietID)});

        if (cursor.moveToFirst()){
            do{
                fact.setID(cursor.getLong(cursor.getColumnIndexOrThrow(_ID)));
                fact.setNutplanID(cursor.getLong(cursor.getColumnIndexOrThrow(COLUMN_DIET_ID)));
                fact.setCalories(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CALORIES)));
                fact.setFat(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_FAT)));
                fact.setFiber(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_FIBER)));
                fact.setProtein(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PROTEIN)));
                fact.setKeyNutrients(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_KEY_NUTRIENTS)));
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return fact;
    }

    public List<nutinstructions> getStepsByDiet(long dietID){
        List<nutinstructions> nutSteps = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT * FROM " + TABLE_NUTRITION_STEPS +
                " WHERE " + COLUMN_DIET_ID + " = ?";

        Cursor cursor = db.rawQuery(selectQuery, new String[]{String.valueOf(dietID)});

        if (cursor.moveToFirst()){
            do{
                nutinstructions step = new nutinstructions();
                step.setID(cursor.getLong(cursor.getColumnIndexOrThrow(_ID)));
                step.setNutplanID(cursor.getLong(cursor.getColumnIndexOrThrow(COLUMN_DIET_ID)));
                step.setStep(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_STEP)));

                nutSteps.add(step);
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return nutSteps;
    }

    public List<dietmed> getDietmedByPetOwner(long ownerID){
        List<dietmed> dietmedList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT * FROM " + TABLE_DIETMED +
                " WHERE " + COLUMN_OWNER_ID + " = ?";
        Cursor cursor = db.rawQuery(selectQuery, new String[]{String.valueOf(ownerID)});

        if (cursor.moveToFirst()){
            do{
                dietmed dmed = new dietmed();
                dmed.setPresNo(cursor.getLong(cursor.getColumnIndexOrThrow(_ID)));
                dmed.setOwnerID(cursor.getLong(cursor.getColumnIndexOrThrow(COLUMN_OWNER_ID)));
                dmed.setPetID(cursor.getLong(cursor.getColumnIndexOrThrow(COLUMN_PET_ID)));
                dmed.setImageID(cursor.getLong(cursor.getColumnIndexOrThrow(COLUMN_PET_IMG)));
                dmed.setMedicationName(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_MED_NAME)));
                dmed.setPurpose(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PURPOSE)));
                dmed.setDosage(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DOSAGE)));
                dmed.setAdministration(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ADMINISTRATION)));
                dmed.setFreq_and_duration(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_FREQ_DURATION)));
                dmed.setNote(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NOTE)));
                dmed.setDatetime(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DATETIME)));

                dietmedList.add(dmed);
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return dietmedList;
    }

    // UPDATE
    public synchronized boolean updatePetOwner(petOwners updated, long ownerID){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_FIRST_NAME, updated.getFname());
        values.put(COLUMN_LAST_NAME, updated.getLname());
        values.put(COLUMN_EMAIL, updated.getEmail());
        values.put(COLUMN_OWNER_DP, updated.getImageID());

        int rowsAffected = db.update(
                TABLE_NAME_PETOWNER,
                values,
                _ID + " = ?",
                new String[]{String.valueOf(ownerID)}
        );
        db.close();

        return rowsAffected > 0;
    }

    public synchronized  void changePetOwnerPW(petOwners owner){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_PASSWORD, owner.getPassword());
        db.update(
                TABLE_NAME_PETOWNER,
                values,
                _ID + " = ?",
                new String[]{String.valueOf(owner.getID())}
        );
        db.close();
    }

    public synchronized void updatePet(pets pet){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_PET_NAME, pet.getName());
        values.put(COLUMN_PET_BREED, pet.getBreed());
        values.put(COLUMN_PET_SEX, pet.getSex());
        values.put(COLUMN_PET_COLOR, pet.getColor());
        values.put(COLUMN_PET_MARKINGS, pet.getMarkings());
        values.put(COLUMN_PET_AGE, pet.getAge());
        values.put(COLUMN_PET_HEIGHT, pet.getHeight());
        values.put(COLUMN_PET_WEIGHT, pet.getWeight());
        values.put(COLUMN_PET_BIRTHDATE, pet.getBirthdate().toString());
        values.put(COLUMN_OWNER_ID, pet.getOwnerID());
        values.put(COLUMN_PET_PHOTO, pet.getImageID());

        db.update(
                TABLE_NAME_PET,
                values,
                _ID + " = ?",
                new String[]{String.valueOf(pet.getID())}
        );
        db.close();
    }

    public synchronized void addPetPhoto(long petID, long imageID){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_PET_PHOTO, imageID);
        db.update(
                TABLE_NAME_PET,
                values,
                _ID + " = ?",
                new String[]{String.valueOf(petID)}
        );
        db.close();
    }

    public int updateImage(long imageID, String newImagePath){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_IMAGE_PATH, newImagePath);

        return db.update(TABLE_IMAGES, values, _ID + " = ?", new String[]{String.valueOf(imageID)});

    }



    // DELETE
    public synchronized void deletePetOwner(long id){
        SQLiteDatabase db = this.getWritableDatabase();
        String clause = _ID + " = ?";
        String[] arg = {String.valueOf(id)};

        db.delete(TABLE_NAME_PETOWNER, clause, arg);
        db.close();
    }

    public synchronized  void deletePet(long id){
        SQLiteDatabase db = this.getWritableDatabase();
        String clause = _ID + " = ?";
        String[] arg = {String.valueOf(id)};

        db.delete(TABLE_NAME_PET, clause, arg);
        db.close();
    }

    public void deleteImage(long imageID) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_IMAGES, _ID + " = ?", new String[]{String.valueOf(imageID)});
    }

    public long addRecord(consolidatedrecords record) {
        SQLiteDatabase db = this.getWritableDatabase();

        // Create content values
        ContentValues values = new ContentValues();
        values.put(RECORD_TITLE, record.getTitle());
        values.put(DOC_TYPE, record.getDocType());
        values.put(DOC_DATE, record.getDocDate());
        values.put(VETERINARIAN, record.getVeterinarian());
        values.put(RECORD_FILE, record.getFile());

        // Insert the record into the database
        long recordId = db.insert(TABLE_CONSOLIDATED_RECORDS, null, values);
        db.close();

        // Return the ID of the newly inserted record (or -1 if failed)
        return recordId;
    }

    public Cursor getAllConsolidatedRecords() {
        SQLiteDatabase db = this.getReadableDatabase();

        // Query to get all records from the table
        String query = "SELECT * FROM " + TABLE_CONSOLIDATED_RECORDS;
        Cursor cursor = db.rawQuery(query, null);

        // Return the cursor containing all the records
        return cursor;
    }

    // CLOSING
    public void closeDB(){
        SQLiteDatabase db = this.getReadableDatabase();
        if(db != null && db.isOpen()){
            db.close();
        }
    }
}
