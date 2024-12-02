package com.mobdeve.pawpal.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;

import com.mobdeve.pawpal.Model.petOwners;
import com.mobdeve.pawpal.Model.pets;
import com.mobdeve.pawpal.Model.images;
import com.mobdeve.pawpal.Shared.userType;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {
    public static final int DB_VER = 1;
    public static final String DB_NAME = "pawpalDB";

    public static final String
            _ID = "id",
    // Tables
            TABLE_NAME_PETOWNER = "petOwner",
            TABLE_NAME_CLINICOWNER = "clinicOwner",
            TABLE_NAME_PET = "pets",
            TABLE_IMAGES = "images",

    // PetOwner Columns
            COLUMN_FIRST_NAME = "firstName",
            COLUMN_LAST_NAME = "lastName",
            COLUMN_EMAIL = "email",
            COLUMN_PASSWORD = "password",
            COLUMN_OWNER_DP = "owner_dp",
            COLUMN_USERNAME = "username",
            COLUMN_CONTACT_NO = "contactno",

    // Pets Columns
            COLUMN_PET_ID = "petID",
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
    // IMAGES
            COLUMN_IMAGE_PATH = "path",
            COLUMN_USER_TYPE = "userType",
            COLUMN_OWNER_ID = "ownerID";

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
                    "FOREIGN KEY (" + COLUMN_PET_PHOTO + ") REFERENCES " + TABLE_IMAGES + " (" + _ID + "), " +
                    "FOREIGN KEY (" + COLUMN_PETOWNER_ID + ") REFERENCES " + TABLE_NAME_PETOWNER + " (" + _ID + "))";

    public static final String CREATE_TABLE_IMAGES =
            "CREATE TABLE IF NOT EXISTS " + TABLE_IMAGES + " (" +
                    _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_IMAGE_PATH + " TEXT NOT NULL, " +
                    COLUMN_USER_TYPE + " INTEGER NOT NULL, " +
                    COLUMN_OWNER_ID + " INTEGER NOT NULL)";

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
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS petOwners");
        db.execSQL("DROP TABLE IF EXISTS clinicOwners");
        db.execSQL("DROP TABLE IF EXISTS images");
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

        long result = db.insert(TABLE_NAME_PET, null, values);
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

    // CLOSING
    public void closeDB(){
        SQLiteDatabase db = this.getReadableDatabase();
        if(db != null && db.isOpen()){
            db.close();
        }
    }
}
