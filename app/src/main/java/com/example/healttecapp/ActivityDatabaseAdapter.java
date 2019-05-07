package com.example.healttecapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.Calendar;

public class ActivityDatabaseAdapter {

    public static String DATABASE_NAME = "stats.db";
    private static final int DATABASE_VERSION = 1;
    static final String TABLE_NAME = "activityStats";
    private static final String ID = "_id";
    private static final String TIMESTAMP = "day";
    private static final String ACTIVITY_SCORE = "act_score";
    private static final String SLEEP_SCORE = "sleep_score";
    private static final String DAYS_FOOD_SCORE = "days_food_score";
    private static final String TOTAL_POINTS = "total_points";

    static final String DATABASE_CREATE = "CREATE TABLE "
            + TABLE_NAME + " ( " + ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
            + TIMESTAMP + " TIMESTAMP NOT NULL DEFAULT current_timestamp, "
            + ACTIVITY_SCORE + " INTEGER, "
            + SLEEP_SCORE + " INTEGER, "
            + DAYS_FOOD_SCORE + " INTEGER, "
            + TOTAL_POINTS + " INTEGER "
            + ");";

    // Variable to hold the database instance
    public static SQLiteDatabase db;

    // Context of the application using the database.
    private final Context context;

    // Database open/upgrade helper
    private static DatabaseHelper dbHelper;

    public ActivityDatabaseAdapter(Context _context) {
        context = _context;
        dbHelper = new DatabaseHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Method to open the Database
    public ActivityDatabaseAdapter open() throws SQLException {
        db = dbHelper.getWritableDatabase();
        return this;
    }

    // method returns an Instance of the Database
    public SQLiteDatabase getDatabaseInstance() {
        return db;
    }

    public static void insertActivityScore(int score) {

        // Check if adding day is some as last day database
        if (checkIfDayIsSome()) {

            String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + ID + " = (SELECT MAX(" + ID + ") FROM " + TABLE_NAME + ");";

            db = dbHelper.getReadableDatabase();
            Cursor cursor = db.rawQuery(query, null);

            String asd = "";
            String id = ID + "=";

            if (cursor.moveToNext()) {
                do {
                    id += cursor.getString(0);
                    asd += cursor.getString(2);
                } while (cursor.moveToNext());
            }

            if (asd.trim().equals("null")) {
                ContentValues contentValues = new ContentValues();
                contentValues.put(ACTIVITY_SCORE, score);

                // Adding also score to total points
                addToTotalPoints(score);

                // Inserting row to a table
                db = dbHelper.getWritableDatabase();
                long result = db.update(TABLE_NAME, contentValues, id, null);
                db.close();
            }

            cursor.close();

        }

    }

    public static void insertSleepScore(int score) {

        // Check if adding day is some as last day database
        if (checkIfDayIsSome()) {

            String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + ID + " = (SELECT MAX(" + ID + ") FROM " + TABLE_NAME + ");";

            db = dbHelper.getReadableDatabase();
            Cursor cursor = db.rawQuery(query, null);

            String asd = "";
            String id = ID + "=";

            if (cursor.moveToNext()) {
                do {
                    id += cursor.getString(0);
                    asd += cursor.getString(3);
                } while (cursor.moveToNext());
            }

            if (asd.trim().equals("null")) {
                ContentValues contentValues = new ContentValues();
                contentValues.put(ACTIVITY_SCORE, score);

                // Adding also score to total points
                addToTotalPoints(score);

                // Inserting row to a table
                db = dbHelper.getWritableDatabase();
                long result = db.update(TABLE_NAME, contentValues, id, null);
                db.close();
            }

            cursor.close();

        }

    }

    public static void insertFoodScore(int score) {

        if (checkIfDayIsSome()) {

            String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + ID + " = (SELECT MAX(" + ID + ") FROM " + TABLE_NAME + ");";

            db = dbHelper.getReadableDatabase();
            Cursor cursor = db.rawQuery(query, null);

            int current = 0;
            String id = ID + "=";

            // Getting last id index and appending it to id String for update purpose
            // Getting also the current value from the last index adding score to it and
            // putting it back to table
            if (cursor.moveToNext()) {
                do {
                    id += cursor.getString(0);
                    current = cursor.getInt(4);
                } while (cursor.moveToNext());
            }

            current += score;

            // Adding also score to total points
            addToTotalPoints(score);

            // Content values for easy update back to table
            ContentValues contentValues = new ContentValues();
            contentValues.put(DAYS_FOOD_SCORE, current);

            db = dbHelper.getWritableDatabase();
            // This is the one that dose the magic
            long result = db.update(TABLE_NAME, contentValues, id, null);
            db.close();

        } else {
            System.out.println("-----------------" + score + "-------------------score");

            // Assign a value for the column
            ContentValues contentValues = new ContentValues();
            contentValues.put(DAYS_FOOD_SCORE, score);

            // Adding also score to total points
            addToTotalPoints(score);

            System.out.println("----------------" + contentValues + "-----------------contentvalues");

            // Inserting row to a table
            db = dbHelper.getWritableDatabase();
            long result = db.insert(TABLE_NAME, null, contentValues);
            db.close();
        }


    }

    // Method is used to add your points to total points column
    // so you can get it later
    public static void addToTotalPoints(int points) {

        // Check if adding day is some as last day database


        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + ID + " = (SELECT MAX(" + ID + ") FROM " + TABLE_NAME + ");";

        db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        String asd = "";
        String id = ID + "=";

        if (cursor.moveToNext()) {
            do {
                id += cursor.getString(0);
                asd += cursor.getString(5);
            } while (cursor.moveToNext());
        }

        if (asd.trim().equals("null")) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(ACTIVITY_SCORE, points);

            // Inserting row to a table
            db = dbHelper.getWritableDatabase();
            long result = db.update(TABLE_NAME, contentValues, id, null);
            db.close();
        }

        cursor.close();


    }

    // This method will compare last index from the database and get the timestamp from it
    // and then compare that timestamp to current date
    // if year, month, and day are some as today it will return true, else false
    public static Boolean checkIfDayIsSome() {

        // Getting the last day in the query
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + ID + " = (SELECT MAX(" + ID + ") FROM " + TABLE_NAME + ");";

        db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // Creating a variable to put the db date
        String first = null;

        // Getting the date from cursor
        if (cursor.moveToNext()) {
            do {
                first = cursor.getString(1);

                System.out.println("----------------" + first + "-----------------");
            } while (cursor.moveToNext());
        }

        cursor.close();

        // Making time variables from database date
        int year = Integer.parseInt(first.substring(0, 4));
        int month = Integer.parseInt(first.substring(6, 7));
        int day = Integer.parseInt(first.substring(9, 10));

        System.out.println("year: " + year + " month: " + month + " day: " + day);

        // Init calendar to compare it db.time
        Calendar curentTime = Calendar.getInstance();

        // Last but not least you check that the day is some as db.Day
        // Tip! There is a +1 on month because calendar maths start at 0 and dbMonth dose not
        return curentTime.get(Calendar.YEAR) == year && curentTime.get(Calendar.MONTH) + 1 == month && curentTime.get(Calendar.DATE) == day;

    }

}

/*
 * Big thanks to this site! Most of the Database parts and controls are from here
 * methods that have to do with inserting data and such are mine
 * https://www.freakyjolly.com/android-sqlite-example-application-insert-update-delete-truncate-operations/
 * */