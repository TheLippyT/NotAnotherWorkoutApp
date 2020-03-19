package com.example.notanotherworkoutapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;

import com.example.notanotherworkoutapp.entity.WorkoutModel;

import java.util.ArrayList;

public class DataBaseHelper extends SQLiteOpenHelper {

    public  static  final String DATABASE_NAME = "fitapp.db";
    private static final int DATABASE_VERSION = 1;
    public  static  final String TABLE_USERS = "users";
    public  static  final String TABLE_EXERCISE = "exercises";
    public  static  final String TABLE_WORKOUT = "workout";
    public  static  final String COL_U1 = "user_id";
    public  static  final String COL_U2 = "user_first_name";
    public  static  final String COL_U3 = "user_last_name";
    public  static  final String COL_U4 = "user_email";
    public  static  final String COL_U5 = "user_password";
    public  static  final String COL_E1 = "exercises_id";
    public  static  final String COL_E2 = "exercises_name";
    public  static  final String COL_E3 = "exercises_type";
    public  static  final String COL_E4 = "exercises_description";
    public  static  final String COL_E5 = "exercises_accessory";
    public  static  final String COL_W1 = "workout_id";
    public  static  final String COL_W2 = "workout_name";
    public  static  final String COL_W3 = "user_id";
    public  static  final String COL_W4 = "exercise_id";

    /*Create Tables in order*/
    private static final String CREATE_TABLE_USERS = "CREATE TABLE "
            + TABLE_USERS + "(" + COL_U1
            + " INTEGER PRIMARY KEY AUTOINCREMENT," + COL_U2 + " VARCHAR,"+COL_U3+" VARCHAR,"+COL_U4+" VARCHAR,"+COL_U5+" VARCHAR );";

    private static final String CREATE_TABLE_EXERCISES = "CREATE TABLE "
            + TABLE_EXERCISE + "(" + COL_E1 + " INTEGER PRIMARY KEY AUTOINCREMENT,"+ COL_E2 + " VARCHAR,"+COL_E3+" VARCHAR,"+COL_E4+" TEXT,"+COL_E5+" VARCHAR );";

    private static final String CREATE_TABLE_WORKOUTS = "CREATE TABLE "
            + TABLE_WORKOUT + "(" + COL_W1 + " INTEGER PRIMARY KEY AUTOINCREMENT,"+ COL_W2 + " VARCHAR,"+COL_W3+" INTEGER,"+COL_W4+" INTEGER );";


    public DataBaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_USERS);
        db.execSQL(CREATE_TABLE_EXERCISES);
        db.execSQL(CREATE_TABLE_WORKOUTS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS '" + TABLE_USERS + "'");
        db.execSQL("DROP TABLE IF EXISTS '" + TABLE_EXERCISE + "'");
        db.execSQL("DROP TABLE IF EXISTS '" + TABLE_WORKOUT + "'");
        onCreate(db);
    }

    public void addUser(String firstName, String lastName, String email, String password ){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues valuesFirstName = new ContentValues();
        valuesFirstName.put(COL_E2, firstName);
        // db.insert(TABLE_USER, null, values);
        long id = db.insertWithOnConflict(TABLE_USERS, null, valuesFirstName, SQLiteDatabase.CONFLICT_IGNORE);

        //adding lastName in exercise table
        ContentValues valuesLastName = new ContentValues();
        valuesLastName.put(COL_U1, id);
        valuesLastName.put(COL_U3, lastName);
        db.insert(TABLE_USERS, null, valuesLastName);

        //adding email in exercise table
        ContentValues valuesEmail = new ContentValues();
        valuesEmail.put(COL_U1, id);
        valuesEmail.put(COL_U4, email);
        db.insert(TABLE_USERS, null, valuesEmail);

        //adding password in exercise table
        ContentValues valuesPassword = new ContentValues();
        valuesPassword.put(COL_U1, id);
        valuesPassword.put(COL_U5, password);
        db.insert(TABLE_USERS, null, valuesPassword);
    }



    public void updateUser(int id, String fName,  String lName, String email, String password ) {
        SQLiteDatabase db = this.getWritableDatabase();

        // updating name in table
        ContentValues values = new ContentValues();
        values.put(COL_U2, fName);
        db.update(TABLE_USERS, values, COL_U1 + " = ?", new String[]{String.valueOf(id)});

        // updating name in users_hobby table
        ContentValues valuesType = new ContentValues();
        valuesType.put(COL_U3, lName);
        db.update(TABLE_USERS, valuesType, COL_U1 + " = ?", new String[]{String.valueOf(id)});

        // updating email in users_city table
        ContentValues valuesDescription = new ContentValues();
        valuesDescription.put(COL_U4, email);
        db.update(TABLE_USERS, valuesDescription, COL_U1 + " = ?", new String[]{String.valueOf(id)});

        // updating password in users_city table
        ContentValues valuesAccessories = new ContentValues();
        valuesAccessories.put(COL_U5, password);
        db.update(TABLE_USERS, valuesAccessories, COL_U1 + " = ?", new String[]{String.valueOf(id)});

    }
    public void delete(int id) {

        // delete row in students table based on id
        SQLiteDatabase db = this.getWritableDatabase();

        //deleting from exercise table
        db.delete(TABLE_EXERCISE, COL_E1 + " = ?", new String[]{String.valueOf(id)});

        //deleting from user table
        db.delete(TABLE_USERS, COL_U1 + " = ?", new String[]{String.valueOf(id)});

        //deleting from workout table
        db.delete(TABLE_WORKOUT, COL_W1 + " = ?", new String[]{String.valueOf(id)});

    }


    public void addWorkout(String workoutName, String userId, String exerciseId){
        SQLiteDatabase db = this.getWritableDatabase();
        //adding WORKOUT
        ContentValues valuesWorkoutName = new ContentValues();
        valuesWorkoutName.put(COL_W2, workoutName);
        // db.insert(TABLE_USER, null, values);
        long id = db.insertWithOnConflict(TABLE_WORKOUT, null, valuesWorkoutName, SQLiteDatabase.CONFLICT_IGNORE);

        //adding EXERCISE in WORKOUT table
        ContentValues valuesUserId = new ContentValues();
        valuesUserId.put(COL_W1, id);
        valuesUserId.put(COL_W3, userId);
        db.insert(TABLE_WORKOUT, null, valuesUserId);

        //adding EXERCISE in WORKOUT table
        ContentValues valuesExerciseId = new ContentValues();
        valuesExerciseId.put(COL_W1, id);
        valuesExerciseId.put(COL_W4, exerciseId);
        db.insert(TABLE_WORKOUT, null, valuesExerciseId);
    }

    public void updateWorkout(int id, String name, String userId, String exerciseId ) {
        SQLiteDatabase db = this.getWritableDatabase();

        // updating name in table
        ContentValues values = new ContentValues();
        values.put(COL_W2, name);
        db.update(TABLE_WORKOUT, values, COL_W1 + " = ?", new String[]{String.valueOf(id)});

        // updating name in users_hobby table
        ContentValues valuesUid = new ContentValues();
        valuesUid.put(COL_W3, userId);
        db.update(TABLE_WORKOUT, valuesUid, COL_W1 + " = ?", new String[]{String.valueOf(id)});

        // updating email in users_city table
        ContentValues valuesEid = new ContentValues();
        valuesEid.put(COL_W4, exerciseId);
        db.update(TABLE_WORKOUT, valuesEid, COL_W1 + " = ?", new String[]{String.valueOf(id)});

    }


}
