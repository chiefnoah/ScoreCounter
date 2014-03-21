package net.loosepixel.scorecounter;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by peder_000 on 2/2/14.
 */
public class HistoryAdapter {

    public static final String KEY_ROWID = "_id";
    public static final String KEY_DATE = "date_recorded";
    public static final String KEY_RED = "red level number";
    public static final String KEY_YELLOW = "yellow level number";
    public static final String KEY_GREEN = "green level number";
    public static final String KEY_BLUE = "blue level number";
    public static final String KEY_WHITE = "white level number";
    public static final String KEY_BLACK = "black level number";

    private static final String DATABASE_NAME = "HistoryDB";
    private static final String TABLE_HISTORY = "historyTable";
    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_CREATE = "CREATE TABLE " + TABLE_HISTORY + " (" +
            KEY_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            KEY_DATE + " TEXT NOT NULL, " +
            KEY_RED + " TEXT NOT NULL, " +
            KEY_YELLOW + " TEXT NOT NULL, " +
            KEY_GREEN + " TEXT NOT NULL, " +
            KEY_BLUE + " TEXT NOT NULL, " +
            KEY_WHITE + " TEXT NOT NULL, " +
            KEY_BLACK + " TEXT NOT NULL);";

    private DbHelper helper;
    private final Context context;
    private SQLiteDatabase database;

    private static class DbHelper extends SQLiteOpenHelper {
        public DbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);

        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(DATABASE_CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.w(DbHelper.class.getName(), "Upgrading database from version " + oldVersion +
                    " to " + newVersion + " , which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_HISTORY);
            onCreate(db);
        }
    }

    public HistoryAdapter(Context c) throws SQLException {
        context = c;
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

}

