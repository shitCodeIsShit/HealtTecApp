package com.example.healttecapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import static com.example.healttecapp.ActivityDatabaseAdapter.TABLE_NAME;

public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }


    /**
     * Luo tietokannan tietokannan luontilauseella jonka se saa ActivituAdabter luokasta
     * lisää myös yhden nolla arvon luotuun tauluun ettei taulu ole kokonaan tyhjä
     * @param db tietokanta jonka avulla luodaan kanta
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(ActivityDatabaseAdapter.DATABASE_CREATE);
            db.execSQL("INSERT INTO activityStats (days_food_score) VALUES (0);");

        }catch(Exception er){
            Log.e("Error","exception");

        }
    }

    /**
     * Poistaa vanhan tietokannan ja luo uuden
     * @param db nykyinen kanta
     * @param oldVersion vanhankannan numero
     * @param newVersion uuden kannan numero
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            db.execSQL("DROP TABLE IF EXISTS " + "activityStats");
            // Create a new one.
            onCreate(db);
        }
    }
}
