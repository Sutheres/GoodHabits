package com.example.sutheres.goodhabits.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.sutheres.goodhabits.data.HabitContract.HabitEntry;

/**
 * Created by Sutheres on 1/12/2017.
 */

public class HabitDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "goodHabits.db";
    private static final int DATABASE_VERSION = 1;

    public HabitDbHelper(Context context) {super(context, DATABASE_NAME, null, DATABASE_VERSION);}

    @Override
    public void onCreate(SQLiteDatabase db) {
        // CREATE TABLE myHabits (id INTEGER PRIMARY KEY AUTOINCREMENT, habit TEXT NOT NULL);
        String SQL_CREATE_HABITS_TABLE = "CREATE TABLE " + HabitEntry.TABLE_NAME + " ("
                + HabitEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + HabitEntry.COLUMN_HABIT + " TEXT NOT NULL, "
                + HabitEntry.COLUMN_HABIT_RANKING + " INTEGER NOT NULL);";

        db.execSQL(SQL_CREATE_HABITS_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int newVersion, int oldVersion) {

    }
}
