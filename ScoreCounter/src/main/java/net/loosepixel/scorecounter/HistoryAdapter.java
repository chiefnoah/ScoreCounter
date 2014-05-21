/*
 * Copyright (c) 2014. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package net.loosepixel.scorecounter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by peder_000 on 2/2/14.
 */
public class HistoryAdapter {

    /*
    Column Names
     */
    public static final String _id = "_id";
    public static final String KEY_DATE = "date_recorded";
    public static final String KEY_NUMBER = "number_recorded";
    public static final String KEY_WEIGHT = "weight_recorded";
    public static final String KEY_TOTAL = "total_recorded";
    public static String columns[] = {_id, KEY_DATE, KEY_WEIGHT, KEY_NUMBER, KEY_TOTAL};
    /*
    Database Schema
     */
    private static final String DATABASE_NAME = "history.db";
    //Changed the data stored by the database so this is useless
    /*
    public static final String KEY_RED = "red_number";
    public static final String KEY_YELLOW = "yellow_number";
    public static final String KEY_GREEN = "green_number";
    public static final String KEY_BLUE = "blue_number";
    public static final String KEY_WHITE = "white_number";
    public static final String KEY_BLACK = "black_number";
    */
    private static final String TABLE_HISTORY = "historyTable";
    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_HISTORY + " (" +
            _id + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            KEY_DATE + " TEXT NOT NULL, " +
            KEY_NUMBER + " TEXT NOT NULL, " +
            KEY_WEIGHT + " TEXT NOT NULL, " +
            KEY_TOTAL + " TEXT NOT NULL);";
    //Also useless because of the change
    /*
    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_HISTORY + " (" +
            _id + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            KEY_DATE + " TEXT NOT NULL, " +
            KEY_RED + " TEXT NOT NULL, " +
            KEY_YELLOW + " TEXT NOT NULL, " +
            KEY_GREEN + " TEXT NOT NULL, " +
            KEY_BLUE + " TEXT NOT NULL, " +
            KEY_WHITE + " TEXT NOT NULL, " +
            KEY_BLACK + " TEXT NOT NULL);";
            */
    private static final int DATABASE_VERSION = 10;
    //I'm making this into a singleton class
    //This is EXTREMELY bad coding practice but it SHOULD work
    private static HistoryAdapter thisInstance;
    private final Context context;
    private DbHelper helper;
    private SQLiteDatabase database;

    public HistoryAdapter(Context c) throws SQLException {
        context = c;
        //open();
    }

    public static HistoryAdapter getInstance(Context c) {
        if (thisInstance == null) {
            thisInstance = new HistoryAdapter(c);
        }
        return thisInstance;
    }
    public HistoryAdapter open() {
        helper = new DbHelper(context);
        database = helper.getWritableDatabase();
        return this;
    }

    public void close() {
        helper.close();
    }

    //Removed because database data changed
    /*
    public long insert(String date, int red, int yellow, int green, int blue, int white, int black) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_DATE, date);
        contentValues.put(KEY_RED, Integer.toString(red));
        contentValues.put(KEY_YELLOW, Integer.toString(yellow));
        contentValues.put(KEY_GREEN, Integer.toString(green));
        contentValues.put(KEY_BLUE, Integer.toString(blue));
        contentValues.put(KEY_WHITE, Integer.toString(white));
        contentValues.put(KEY_BLACK, Integer.toString(black));

        long id = database.insert(TABLE_HISTORY, null, contentValues);
        Log.v("DATABASE", "INSERTING DATA INTO DATABASE ID = " + id);
        return id;
    }
    */

    public long insert(String date, int number, int weight, int total) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_DATE, date);
        contentValues.put(KEY_NUMBER, Integer.toString(number));
        contentValues.put(KEY_WEIGHT, Integer.toString(weight));
        contentValues.put(KEY_TOTAL, Integer.toString(total));

        long id = database.insert(TABLE_HISTORY, null, contentValues);
        Log.v("DATABASE", "INSERTING DATA INTO DATABASE ID = " + id);
        return id;
    }

    public void clearHistory() {
        //database.execSQL("DROP TABLE IF EXISTS " + TABLE_HISTORY);
        context.deleteDatabase(DATABASE_NAME);
        Toast.makeText(context, "Clearing database", Toast.LENGTH_SHORT).show();
    }

    public Cursor getDataCursor() {

        //  try {
        return database.query(TABLE_HISTORY, columns, null, null, null, null, null);//KEY_DATE + " DESC");
        //  } catch (SQLException e) {
        //e.printStackTrace();
        //     Log.e("ERROR", "Failed to query SQL Database");
        //    return null;
        // }

    }


    private static class DbHelper extends SQLiteOpenHelper {
        public DbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);

        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            try {
                db.execSQL(CREATE_TABLE);
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

        @Override
        public void onOpen(SQLiteDatabase db) {

        }


        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.w(DbHelper.class.getName(), "Upgrading database from version " + oldVersion +
                    " to " + newVersion + " , which will destroy all old data. Sorry :(");
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_HISTORY);
            onCreate(db);
        }
    }
}
