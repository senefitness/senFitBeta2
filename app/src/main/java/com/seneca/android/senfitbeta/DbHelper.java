package com.seneca.android.senfitbeta;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by dance on 4/4/2017.
 */

public class DbHelper extends SQLiteOpenHelper {

    // Databse info
    private static final String DB_NAME = "myapp.db";
    private static final int DB_VERSION = 1;

    //Table Names
    private static final String USER_TABLE = "users";
    private static final String EXERCISE_TABLE = "exercises";
    private static  final String RIP_TABLE = "muscles";
    private static  final String IMG_TABLE = "img_table";

    // user Table Columns
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_PASS = "password";

    //exercise Table Columns
    private static final String AUTHOR = "author";
    private static final String DESCRIPTION = "description";
    private static final String NAMETYPE = "name";
    private static final String ORIGNALNAME = "originalname";
    private static final String CREATIONDATE = "creationdate";
    private static final String CATEGORY = "category";
    private static final String EXERCISE_ID = "id";



    //MUSCLE
    private static final String RIP_ID = "id";
    private static final String RIP_NAME = "rip_name";

    //IMG COLUMN
    private static  final String IMG_ID = "id";
    private static  final String LINK = "link";

    private static final String CREATE_TABLE_USERS = "CREATE TABLE " + USER_TABLE + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_EMAIL + " TEXT,"
            +COLUMN_PASS + " TEXT);";

    private static final String CREATE_TABLE_EXERCISE = "CREATE TABLE " + EXERCISE_TABLE + "("
            + EXERCISE_ID + " INTEGER PRIMARY KEY,"
            + AUTHOR + " TEXT,"
            + DESCRIPTION + " TEXT,"
            + NAMETYPE + " TEXT,"
            + ORIGNALNAME + " TEXT,"
            + CREATIONDATE + " TEXT,"
            +CATEGORY + " TEXT);";

    private static final String CREATE_TABLE_RIP = "CREATE TABLE " + RIP_TABLE + "("
            + RIP_ID + " INTEGER PRIMARY KEY,"
            +RIP_NAME + " TEXT);";

    private static final String CREATE_TABLE_IMAGES = "CREATE TABLE " + IMG_TABLE + "("
            + IMG_ID + " INTEGER PRIMARY KEY,"
            +LINK + " TEXT);";





    public DbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_USERS);
        db.execSQL(CREATE_TABLE_EXERCISE);
        db.execSQL(CREATE_TABLE_RIP);
        db.execSQL(CREATE_TABLE_IMAGES);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS" + USER_TABLE);
        db.execSQL("DROP TABLE IF EXISTS" + EXERCISE_TABLE);
        db.execSQL("DROP TABLE IF EXISTS" + RIP_TABLE);
        db.execSQL("DROP TABLE IF EXISTS" + IMG_TABLE);

        onCreate(db);
    }

    public void addUsers(String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_EMAIL, email);
        values.put(COLUMN_PASS, password);

        long id = db.insert(USER_TABLE, null, values);
        db.close();
    }

    public boolean getUser(String email, String pass) {
        String selectQuery = "select * from " + USER_TABLE + " where " +
                COLUMN_EMAIL + " = " + "'" + email + "'" + " and " + COLUMN_PASS + " = " + "'" + pass + "'";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();

        if (cursor.getCount() > 0) {
            return true;
        }
        cursor.close();
        db.close();
        return false;
    }

    public boolean checkEmailExists(String email) {
        String query = "Select * from " + USER_TABLE + " where email like '" + email + "'";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();

        if (cursor.getCount() > 0) {
            return true;
        } else {
            return false;
        }
    }




    public void insertExercise(String aut,String des,String name,String ogName,String date,String cat,int id){
        Log.d("INSERT EXDB", "Inserting exercise...");


        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(AUTHOR,aut);
        values.put(DESCRIPTION,des);
        values.put(NAMETYPE,name);
        values.put(ORIGNALNAME,ogName);
        values.put(CREATIONDATE,date);
        values.put(CATEGORY,cat);
        values.put(EXERCISE_ID,id);

        long info = db.insert(EXERCISE_TABLE, null, values);
    }

    public void insertMuscles(int id,String name){
        Log.d("INSERT DB", "Inserting muscles...");

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(RIP_ID,id);
        values.put(RIP_NAME,name);
        long info = db.insert(RIP_TABLE, null, values);

    }

    public void insertImg(int id, String link){
        Log.d("INSERT IMGDB", "Inserting images...");

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(IMG_ID,id);
        values.put(LINK,link);
        long info = db.insert(RIP_TABLE, null, values);
    }


    //GETING FROM DB

    public ArrayList<Exercise> getExercises(){
        Log.d("SELECT EXDB", "Getinging exercise");

        String fetchdata ="select * from " + EXERCISE_TABLE+ " ORDER BY "+ NAMETYPE+" ASC";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(fetchdata, null);
        cursor.moveToFirst();

        if(cursor == null){
            Log.d("exiting", "NOTHING");
        }

        ArrayList<Exercise> temp = new ArrayList<Exercise>();

        cursor.moveToFirst();
        do {
            Exercise ex = new Exercise();
            ex.setId(cursor.getInt(cursor.getColumnIndex(EXERCISE_ID)));
            ex.setLicense_author(cursor.getString(cursor.getColumnIndex(AUTHOR)));
            ex.setDescription(cursor.getString(cursor.getColumnIndex(DESCRIPTION)));
            ex.setName(cursor.getString(cursor.getColumnIndex(NAMETYPE)));
            ex.setName_original(cursor.getString(cursor.getColumnIndex(ORIGNALNAME)));
            ex.setCreation_date(cursor.getString(cursor.getColumnIndex(CREATIONDATE)));
            ex.setCategory(cursor.getString(cursor.getColumnIndex(CATEGORY)));

            temp.add(ex);
        }while(cursor.moveToNext());


        cursor.close();
        getReadableDatabase().close();
        return temp;

    }

    public ArrayList<Exercise> getExByMuscles(int num){
        Log.d("SELECT EXDB", "Getinging exercise By id");

        String selectQuery = "SELECT * FROM exercises WHERE id LIKE '%" + num + "%' ORDER BY "+ EXERCISE_ID+" ASC";




        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();

        if(cursor == null){
            Log.d("exiting", "NOTHING");
        }

        if(cursor.getCount() == 0) {
            Log.d("NOTHING", "0 nothing");
        }

        ArrayList<Exercise> temp = new ArrayList<Exercise>();
        cursor.moveToFirst();
        do {
            Exercise ex = new Exercise();
            ex.setId(cursor.getInt(cursor.getColumnIndex(EXERCISE_ID)));
            ex.setLicense_author(cursor.getString(cursor.getColumnIndex(AUTHOR)));
            ex.setDescription(cursor.getString(cursor.getColumnIndex(DESCRIPTION)));
            ex.setName(cursor.getString(cursor.getColumnIndex(NAMETYPE)));
            ex.setName_original(cursor.getString(cursor.getColumnIndex(ORIGNALNAME)));
            ex.setCreation_date(cursor.getString(cursor.getColumnIndex(CREATIONDATE)));
            ex.setCategory(cursor.getString(cursor.getColumnIndex(CATEGORY)));

            temp.add(ex);
        }while(cursor.moveToNext());

        cursor.close();
        getReadableDatabase().close();

        return temp;

    }

    public ArrayList<Muscle> getMuscles(){
        Log.d("MUSCLE_GET", "Geting muscles");

        String fetchMus ="select * from " + RIP_TABLE+ " ORDER BY "+ RIP_NAME+" ASC";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(fetchMus, null);
        cursor.moveToFirst();

        if(cursor == null){
            Log.d("exiting", "NOTHING");
        }
        if(cursor.getCount() == 0) {
            Log.d("NOTHING", "0 nothing");
        }

        ArrayList<Muscle> temp = new ArrayList<Muscle>();

        cursor.moveToFirst();
        do {
            Muscle ex = new Muscle();
             ex.setId(cursor.getInt(cursor.getColumnIndex(RIP_ID)));
            ex.setName(cursor.getString(cursor.getColumnIndex(RIP_NAME)));

            temp.add(ex);
        }while(cursor.moveToNext());



        cursor.close();
        getReadableDatabase().close();
        return temp;

    }

}
