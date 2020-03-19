package com.example.notanotherworkoutapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.notanotherworkoutapp.entity.WorkoutModel;

import java.util.ArrayList;

public class DataBaseHelper extends SQLiteOpenHelper {

    public  static  final String DATABASE_NAME = "fitapp.db";
    private static final int DATABASE_VERSION = 1;
    public  static  final String TABLE_WORKOUTS = "workouts";
    public  static  final String TABLE_WORKOUTS_EXERCISE = "workouts_exercise";
    public  static  final String TABLE_EXERCISE= "exercises";
    public  static  final String COL_W1 = "workouts_id";
    public  static  final String COL_W2 = "workouts_name";
    public  static  final String COL_WE1 = "workouts_exercise_id";
    public  static  final String COL_WE2 = "workouts_id";
    public  static  final String COL_WE3 = "exercises_id";
    public  static  final String COL_E1 = "exercises_id";
    public  static  final String COL_E2 = "exercises_name";
    public  static  final String COL_E3 = "exercises_type";
    public  static  final String COL_E4 = "exercises_description";
    public  static  final String COL_E5 = "exercises_accessory";

    /*Create Tables in order*/
    private static final String CREATE_TABLE_WORKOUTS = "CREATE TABLE "
            + TABLE_WORKOUTS + "(" + COL_W1
            + " INTEGER PRIMARY KEY AUTOINCREMENT," + COL_W2 + " VARCHAR );";

    private static final String CREATE_TABLE_EXERCISES = "CREATE TABLE "
            + TABLE_EXERCISE + "(" + COL_E1 + " INTEGER PRIMARY KEY AUTOINCREMENT,"+ COL_E2 + " VARCHAR,"+COL_E3+" VARCHAR,"+COL_E4+" TEXT,"+COL_E5+" VARCHAR );";

    private static final String CREATE_TABLE_WORKOUTS_EXERCISES = "CREATE TABLE "
            + TABLE_WORKOUTS_EXERCISE + "(" + COL_WE1 + " INTEGER PRIMARY KEY AUTOINCREMENT,"+ COL_WE2 + " INTEGER,"+COL_WE3+" INTEGER );";

    public DataBaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_WORKOUTS);
        db.execSQL(CREATE_TABLE_EXERCISES);
        db.execSQL(CREATE_TABLE_WORKOUTS_EXERCISES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS '" + TABLE_WORKOUTS + "'");
        db.execSQL("DROP TABLE IF EXISTS '" + TABLE_EXERCISE + "'");
        db.execSQL("DROP TABLE IF EXISTS '" + TABLE_WORKOUTS_EXERCISE + "'");
        onCreate(db);
    }

    public void addExercise(String name, String type, String description, String accessories ){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_E2, name);
        // db.insert(TABLE_USER, null, values);
        long id = db.insertWithOnConflict(TABLE_EXERCISE, null, values, SQLiteDatabase.CONFLICT_IGNORE);

        //adding type in exercise table
        ContentValues valuesType = new ContentValues();
        valuesType.put(COL_E1, id);
        valuesType.put(COL_E3, type);
        db.insert(TABLE_EXERCISE, null, valuesType);

        //adding description in exercise table
        ContentValues valuesDescription = new ContentValues();
        valuesDescription.put(COL_E1, id);
        valuesDescription.put(COL_E4, description);
        db.insert(TABLE_EXERCISE, null, valuesDescription);

        //adding accessories in exercise table
        ContentValues valuesAccessories = new ContentValues();
        valuesAccessories.put(COL_E1, id);
        valuesAccessories.put(COL_E5, accessories);
        db.insert(TABLE_EXERCISE, null, valuesAccessories);
    }



    public ArrayList<WorkoutModel> getAllWorkouts() {
        ArrayList<WorkoutModel> userModelArrayList = new ArrayList<WorkoutModel>();

        String selectQuery = "SELECT  * FROM " + TABLE_WORKOUTS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                WorkoutModel workoutModel = new WorkoutModel();
                workoutModel.setWorkoutsId(c.getInt(c.getColumnIndex(COL_W1)));
                workoutModel.setWorkoutName(c.getString(c.getColumnIndex(COL_W2)));

                //getting user hobby where id = id from user_hobby table
                String selectExerciseQuery = "SELECT  * FROM " + TABLE_EXERCISE +" WHERE "+COL_WE1+" = "+ workoutModel.getWorkoutsId();
                Log.d("oppp",selectExerciseQuery);
                //SQLiteDatabase dbhobby = this.getReadableDatabase();
                Cursor cExercise = db.rawQuery(selectExerciseQuery, null);

                if (cExercise.moveToFirst()) {
                    do {
                        workoutModel.setExerciseName(cExercise.getString(cExercise.getColumnIndex(COL_E2)));
                    } while (cExercise.moveToNext());
                }

                //getting workout exercise where id = id from workout_exercise table
                String selectWorkoutQuery = "SELECT  * FROM " + TABLE_WORKOUTS_EXERCISE+" WHERE "+COL_WE1+" = "+ workoutModel.getWorkout_exerciseId();;
                //SQLiteDatabase dbCity = this.getReadableDatabase();
                Cursor cWorkoutExericse = db.rawQuery(selectWorkoutQuery, null);

                if (cWorkoutExericse.moveToFirst()) {
                    do {
                        workoutModel.setWorkoutName(cWorkoutExericse.getString(cWorkoutExericse.getColumnIndex(COL_WE1)));
                    } while (cWorkoutExericse.moveToNext());
                }

                // adding to Students list
                userModelArrayList.add(workoutModel);
            } while (c.moveToNext());
        }
        return userModelArrayList;
    }


    public void updateExercise(int id, String name,  String type, String description, String accessories ) {
        SQLiteDatabase db = this.getWritableDatabase();

        // updating name in table
        ContentValues values = new ContentValues();
        values.put(COL_E2, name);
        db.update(TABLE_EXERCISE, values, COL_E1 + " = ?", new String[]{String.valueOf(id)});

        // updating TYPE in users_hobby table
        ContentValues valuesType = new ContentValues();
        valuesType.put(COL_E3, type);
        db.update(TABLE_EXERCISE, valuesType, COL_E1 + " = ?", new String[]{String.valueOf(id)});

        // updating description in users_city table
        ContentValues valuesDescription = new ContentValues();
        valuesDescription.put(COL_E4, description);
        db.update(TABLE_EXERCISE, valuesDescription, COL_E1 + " = ?", new String[]{String.valueOf(id)});

        // updating description in users_city table
        ContentValues valuesAccessories = new ContentValues();
        valuesAccessories.put(COL_E5, accessories);
        db.update(TABLE_EXERCISE, valuesAccessories, COL_E1 + " = ?", new String[]{String.valueOf(id)});

    }
    public void delete(int id) {

        // delete row in students table based on id
        SQLiteDatabase db = this.getWritableDatabase();

        //deleting from users table
        db.delete(TABLE_EXERCISE, COL_E1 + " = ?",new String[]{String.valueOf(id)});

        //deleting from users_hobby table
        db.delete(TABLE_WORKOUTS, COL_W1 + " = ?", new String[]{String.valueOf(id)});

        //deleting from users_city table
        db.delete(TABLE_WORKOUTS_EXERCISE, COL_WE1 + " = ?",new String[]{String.valueOf(id)});
    }

    public void addWorkout(String workoutName) {
        SQLiteDatabase db = this.getWritableDatabase();
        //adding workout in table
        ContentValues valuesWorkoutName = new ContentValues();
        valuesWorkoutName.put(COL_E2, workoutName);
        // db.insert(TABLE_USER, null, values);
        long id = db.insertWithOnConflict(TABLE_WORKOUTS, null, valuesWorkoutName, SQLiteDatabase.CONFLICT_IGNORE);

    }

    public void addExerciseToWorkout(int workoutId, int exerciseId){
        SQLiteDatabase db = this.getWritableDatabase();
        //adding WORKOUT
        ContentValues valuesWorkoutId = new ContentValues();
        valuesWorkoutId.put(COL_WE2, workoutId);
        // db.insert(TABLE_USER, null, values);
        long id = db.insertWithOnConflict(TABLE_WORKOUTS_EXERCISE, null, valuesWorkoutId, SQLiteDatabase.CONFLICT_IGNORE);

        //adding EXERCISE in WORKOUT table
        ContentValues valuesExerciseId = new ContentValues();
        valuesExerciseId.put(COL_WE1, id);
        valuesExerciseId.put(COL_WE3, exerciseId);
        db.insert(TABLE_WORKOUTS_EXERCISE, null, valuesExerciseId);
    }


}
