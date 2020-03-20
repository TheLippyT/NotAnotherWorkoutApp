package com.example.notanotherworkoutapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.notanotherworkoutapp.entity.ExerciseModel;
import com.example.notanotherworkoutapp.entity.UserModel;
import com.example.notanotherworkoutapp.entity.WorkoutModel;

import java.util.ArrayList;
import java.util.List;

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

    public List<UserModel> getAllUser() {
        // array of columns to fetch
        String[] columns = {
                COL_U1,
                COL_U2,
                COL_U3,
                COL_U4,
                COL_U5
        };
        // sorting orders
        String sortOrder =
                COL_U2 + " ASC";
        List<UserModel> userList = new ArrayList<UserModel>();

        SQLiteDatabase db = this.getReadableDatabase();

        // query the user table
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id,user_name,user_email,user_password FROM user ORDER BY user_name;
         */
        Cursor cursor = db.query(TABLE_USERS, //Table to query
                columns,    //columns to return
                null,        //columns for the WHERE clause
                null,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                sortOrder); //The sort order


        // Traversing through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                UserModel user = new UserModel();
                user.setUserId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COL_U1))));
                user.setFirstName(cursor.getString(cursor.getColumnIndex(COL_U2)));
                user.setLastName(cursor.getString(cursor.getColumnIndex(COL_U3)));
                user.setEmail(cursor.getString(cursor.getColumnIndex(COL_U4)));
                user.setPassword(cursor.getString(cursor.getColumnIndex(COL_U5)));
                // Adding user record to list
                userList.add(user);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        // return user list
        return userList;
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

    public List<ExerciseModel> getAllExercises() {
        // array of columns to fetch
        String[] columns = {
                COL_E1,
                COL_E2,
                COL_E3,
                COL_E4,
                COL_E5
        };
        // sorting orders
        String sortOrder =
                COL_E2 + " ASC";
        List<ExerciseModel> exerciseModelList = new ArrayList<ExerciseModel>();

        SQLiteDatabase db = this.getReadableDatabase();

        // query the user table
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id,user_name,user_email,user_password FROM user ORDER BY user_name;
         */
        Cursor cursor = db.query(TABLE_EXERCISE, //Table to query
                columns,    //columns to return
                null,        //columns for the WHERE clause
                null,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                sortOrder); //The sort order


        // Traversing through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                ExerciseModel exercise = new ExerciseModel();
                exercise.setExerciseId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COL_E1))));
                exercise.setExerciseName(cursor.getString(cursor.getColumnIndex(COL_E2)));
                exercise.setExerciseType(cursor.getString(cursor.getColumnIndex(COL_E3)));
                exercise.setExerciseDescription(cursor.getString(cursor.getColumnIndex(COL_E4)));
                exercise.setExerciseAccessory(cursor.getString(cursor.getColumnIndex(COL_E5)));
                // Adding user record to list
                exerciseModelList.add(exercise);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        // return user list
        return exerciseModelList;
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

    public List<WorkoutModel> getAllWorkouts() {
        // array of columns to fetch
        String[] columns = {
                COL_W1,
                COL_W2,
                COL_W3,
                COL_W4
        };
        // sorting orders
        String sortOrder =
                COL_W2 + " ASC";
        List<WorkoutModel> workoutModelList = new ArrayList<WorkoutModel>();

        SQLiteDatabase db = this.getReadableDatabase();

        // query the user table
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id,user_name,user_email,user_password FROM user ORDER BY user_name;
         */
        Cursor cursor = db.query(TABLE_WORKOUT, //Table to query
                columns,    //columns to return
                null,        //columns for the WHERE clause
                null,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                sortOrder); //The sort order


        // Traversing through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                WorkoutModel workout = new WorkoutModel();
                workout.setWorkoutId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COL_W1))));
                workout.setWorkoutName(cursor.getString(cursor.getColumnIndex(COL_W2)));
                workout.setUserId(cursor.getInt(cursor.getColumnIndex(COL_W3)));
                workout.setExerciseId(cursor.getInt(cursor.getColumnIndex(COL_W4)));
                // Adding user record to list
                workoutModelList.add(workout);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        // return user list
        return workoutModelList;
    }


    public boolean checkUser(String email) {

        // array of columns to fetch
        String[] columns = {
                COL_U1
        };
        SQLiteDatabase db = this.getReadableDatabase();

        // selection criteria
        String selection = COL_U4 + " = ?";

        // selection argument
        String[] selectionArgs = {email};

        // query user table with condition
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com';
         */
        Cursor cursor = db.query(TABLE_USERS, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                      //filter by row groups
                null);                      //The sort order
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();

        if (cursorCount > 0) {
            return true;
        }

        return false;
    }

    /**
     * This method to check user exist or not
     *
     * @param email
     * @param password
     * @return true/false
     */
    public boolean checkUser(String email, String password) {

        // array of columns to fetch
        String[] columns = {
                COL_U1
        };
        SQLiteDatabase db = this.getReadableDatabase();
        // selection criteria
        String selection = COL_U4 + " = ?" + " AND " + COL_U5 + " = ?";

        // selection arguments
        String[] selectionArgs = {email, password};

        // query user table with conditions
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com' AND user_password = 'qwerty';
         */
        Cursor cursor = db.query(TABLE_USERS, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                null);                      //The sort order

        int cursorCount = cursor.getCount();

        cursor.close();
        db.close();
        if (cursorCount > 0) {
            return true;
        }

        return false;
    }

}
