package net.loosepixel.scorecounter;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by peder_000 on 2/2/14.
 */
public class HistoryAdapter {

    /*
    Column Names
     */
    public static final String KEY_ROWID = "_id";
    public static final String KEY_DATE = "date_recorded";
    public static final String KEY_RED = "red_number";
    public static final String KEY_YELLOW = "yellow_number";
    public static final String KEY_GREEN = "green_number";
    public static final String KEY_BLUE = "blue_number";
    public static final String KEY_WHITE = "white_number";
    public static final String KEY_BLACK = "black_number";

    /*
    Database Schema
     */
    private static final String DATABASE_NAME = "history.db";
    private static final String TABLE_HISTORY = "historyTable";
    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_HISTORY + " (" +
            KEY_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            KEY_DATE + " TEXT NOT NULL, " +
            KEY_RED + " TEXT NOT NULL, " +
            KEY_YELLOW + " TEXT NOT NULL, " +
            KEY_GREEN + " TEXT NOT NULL, " +
            KEY_BLUE + " TEXT NOT NULL, " +
            KEY_WHITE + " TEXT NOT NULL, " +
            KEY_BLACK + " TEXT NOT NULL);";
    private static final int DATABASE_VERSION = 3;
    private final Context context;
    private DbHelper helper;
    private SQLiteDatabase database;

    public HistoryAdapter(Context c) throws SQLException {
        context = c;
        open();
    }

    public HistoryAdapter open() {
        helper = new DbHelper(context);
        //database = helper.getReadableDatabase(); //uncomment this and comment out the "getWritableDatabase() line to make this read ONLY
        database = helper.getWritableDatabase();
        return this;
    }

    public void close() {
        helper.close();
    }

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

    public void clearHistory() {
        database.execSQL("DROP TABLE IF EXISTS " + TABLE_HISTORY);
        //TODO: Add toast notification to notify user that the history has been cleared.
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
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.w(DbHelper.class.getName(), "Upgrading database from version " + oldVersion +
                    " to " + newVersion + " , which will destroy all old data. Sorry :(");
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_HISTORY);
            onCreate(db);
        }
    }
}
