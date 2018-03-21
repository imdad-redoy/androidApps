package com.example.risalat.slidingtabsusingviewpager;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.risalat.slidingtabsusingviewpager.CalContract.*;


public class CalDBHelper  extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="callist.db";
    public static final int DATABASE_VERSION=1;

    public CalDBHelper(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE__CALLIST_TABLE="CREATE TABLE " +
                CalEntry.TABLE_NAME + " (" +
                CalEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                CalEntry.COLUMN_NAME + " TEXT NOT NULL, "  +
                CalEntry.COLUMN_TIMESTAMP + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                ");";
        db.execSQL(SQL_CREATE__CALLIST_TABLE);

        final String SQL_CREATE__CALLIST_TABLE_S="CREATE TABLE " +
                CalEntry.TABLE_NAME_S + " (" +
                CalEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                CalEntry.COLUMN_NAME_S + " TEXT NOT NULL, "  +
                CalEntry.COLUMN_TIMESTAMP_S + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                ");";
        db.execSQL(SQL_CREATE__CALLIST_TABLE_S);

        final String SQL_CREATE__CALLIST_TABLE_M="CREATE TABLE " +
                CalEntry.TABLE_NAME_M + " (" +
                CalEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                CalEntry.COLUMN_NAME_M + " TEXT NOT NULL, "  +
                CalEntry.COLUMN_TIMESTAMP_M + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                ");";

        db.execSQL(SQL_CREATE__CALLIST_TABLE_M);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + CalEntry.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + CalEntry.TABLE_NAME_S);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + CalEntry.TABLE_NAME_M);

        onCreate(sqLiteDatabase);

    }
}


