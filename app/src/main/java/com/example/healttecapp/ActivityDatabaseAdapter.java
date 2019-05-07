package com.example.healttecapp;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.Calendar;

public class ActivityDatabaseAdapter {

    /**
     * @author Valtteri Mäntymäki
     */

    public static String DATABASE_NAME = "stats.db";
    private static final int DATABASE_VERSION = 1;

    static final String TABLE_NAME = "activityStats";
    private static final String ID = "_id";
    private static final String TIMESTAMP = "day";
    private static final String ACTIVITY_SCORE = "act_score";
    private static final String SLEEP_SCORE = "sleep_score";
    private static final String DAYS_FOOD_SCORE = "days_food_score";
    private static final String TOTAL_POINTS = "total_points";

    // Pää tietokannan luonti lause
    static final String DATABASE_CREATE = "CREATE TABLE "
            + TABLE_NAME + " ( " + ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
            + TIMESTAMP + " TIMESTAMP NOT NULL DEFAULT current_timestamp, "
            + ACTIVITY_SCORE + " INTEGER, "
            + SLEEP_SCORE + " INTEGER, "
            + DAYS_FOOD_SCORE + " INTEGER, "
            + TOTAL_POINTS + " INTEGER "
            + ");";

    // Muuttuja joka pitää tietokannan instanssia
    public static SQLiteDatabase db;

    // Ohjelman contexti joka käyttää tietokantaa
    private final Context context;

    // Tietokannan open/upgrade auttaja
    private static DatabaseHelper dbHelper;

    public ActivityDatabaseAdapter(Context _context) {
        context = _context;
        dbHelper = new DatabaseHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Avaa yhteyden tietokantaan
    public ActivityDatabaseAdapter open() throws SQLException {
        db = dbHelper.getWritableDatabase();
        return this;
    }

    // Palauttaa tietokannan instancin
    public SQLiteDatabase getDatabaseInstance() {
        return db;
    }


    /**
     * Asettaa sille annetun tiedon kantaan kohtaan act_score.
     * @param score pistemäärä joka halutaan tallentaa. 1 - 5 väliltä
     */
    public static void insertActivityScore(int score) {

        // Check if adding day is some as last day database
        if (checkIfDayIsSome()) {

            score = score * 2;

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

    /**
     * Asettaa sille annetun intin kantaan sleep_score kohtaan
     * @param score pistemäärä joka halutaan tallentaa. 1 - 5 väliltä
     */
    public static void insertSleepScore(int score) {

        // Check if adding day is some as last day database
        if (checkIfDayIsSome()) {

            score = score * 3;

            System.out.println("----------------" + score + "-----------------SCORE!!!");

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

            System.out.println("----------------" + asd + "-----------------ASD!!!");

            if (asd.trim().equals("null")) {
                ContentValues contentValues = new ContentValues();
                contentValues.put(SLEEP_SCORE, score);

                System.out.println("----------------" + "-----------------hei olemme oikeassa paikassa");

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

    /**
     Asettaa sille annetun intin kantaan sleep_score kohtaan
     * @param score pistemäärä joka halutaan tallentaa. 1 - 5 väliltä
     */
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


    /**
     * Kutsutaan aktiivisuutta, nukkumista ja ruoka pisteitä lisätessä. Lisää jokaisesta saamansa
     * pisteet tietokantaan kohtaan total_points.
     * @param points pisteet jotka tallennetaan kantaan
     */
    public static void addToTotalPoints(int points) {

        // Check if adding day is some as last day database
        if(checkIfDayIsSome()) {


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
                contentValues.put(TOTAL_POINTS, points);

                // Inserting row to a table
                db = dbHelper.getWritableDatabase();
                long result = db.update(TABLE_NAME, contentValues, id, null);
                db.close();
            }else {

                int curentTotallPoints = Integer.parseInt(asd);
                curentTotallPoints += points;

                ContentValues contentValues = new ContentValues();
                contentValues.put(TOTAL_POINTS, curentTotallPoints);

                db = dbHelper.getWritableDatabase();
                long result = db.update(TABLE_NAME, contentValues, id, null);
                db.close();
            }

            cursor.close();
        }


    }

    // This method will compare last index from the database and get the timestamp from it
    // and then compare that timestamp to current date
    // if year, month, and day are some as today it will return true, else false

    /**
     * Vertaa tietokannasta viimmeisenä olevaa päivä arvoa tähän päivään. Jos päivä on on sama
     * palauttaa true muuten false. Metodin tarkoitus on varmistaa että kantaan voi yhdelle
     * riville lisätä vain kerran päivässä asioita. Paitsi days_food_score:a
     * @return true jos tämäpäivä = kannan viimmeinen päivä / false jos näin ei ole
     */
    public static Boolean checkIfDayIsSome() {

        // Haetaan kannasta vika päivä
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + ID + " = (SELECT MAX(" + ID + ") FROM " + TABLE_NAME + ");";

        db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // Luodaan muuttuja johon tallennetaan kannan päivä
        String first = null;

        // Käydään kursor läpi että saadaan haluttu arvo
        if (cursor.moveToNext()) {
            do {
                first = cursor.getString(1);

                System.out.println("----------------" + first + "-----------------");
            } while (cursor.moveToNext());
        }

        cursor.close();

        // Tehdään muttujat tietokannan ajan vertausta varten
        int year = Integer.parseInt(first.substring(0, 4));
        int month = Integer.parseInt(first.substring(6, 7));
        int day = Integer.parseInt(first.substring(9, 10));

        System.out.println("year: " + year + " month: " + month + " day: " + day);

        // Luodaan kalenteri josta saadaan tämän päivän tiedot
        Calendar curentTime = Calendar.getInstance();

        // Ja lopulta verrataan tätäpäivää tietokannan päivään
        // Vinkki! Kalenterissa on + 1 kuukauden kohfalla koska kalenteri alkaa 0. (tulee error)
        return curentTime.get(Calendar.YEAR) == year && (curentTime.get(Calendar.MONTH) + 1) == month && curentTime.get(Calendar.DATE) == day;

    }

    /**
     * Hakee kannasta viimmeisimmän päivän kokonais pisteet
     * @return vimmeisimmän total_pointsin
     */
    public static int getTotallPoints(){
        int totallPoints = 0;
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + ID + " = (SELECT MAX(" + ID + ") FROM " + TABLE_NAME + ");";

        db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToNext()) {
            do {
                totallPoints = cursor.getInt(5);
            } while (cursor.moveToNext());
        }

        return totallPoints;
    }

    /**
     * Hakee kannasta kaikki total_points:sit ja palauttaa ne Arraylistana
     * @return total_pointsit arraylistana
     */
    public static ArrayList getAllTotalPoints(){

        ArrayList totallPoints = new ArrayList();
        String query = "SELECT * FROM " + TABLE_NAME;

        db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToNext()){
            do {
                totallPoints.add(cursor.getInt(5));
            }while (cursor.moveToNext());
        }

        return totallPoints;

    }

}

/* Iso kiitos tälle sivustolle. Sain paljon hyviä vinkkejä miten kanta luodaan yms.
 * Osa koodista on suoraan sivulta mutta suurin osa metodeista on omia
 * https://www.freakyjolly.com/android-sqlite-example-application-insert-update-delete-truncate-operations/
 * */