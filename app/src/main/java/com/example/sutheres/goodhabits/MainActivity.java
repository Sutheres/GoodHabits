package com.example.sutheres.goodhabits;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.sutheres.goodhabits.data.HabitContract.HabitEntry;
import com.example.sutheres.goodhabits.data.HabitDbHelper;

public class MainActivity extends AppCompatActivity {

    /** Database helper that will provide us access to the databse */
    private HabitDbHelper mHabitDbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton fab = (FloatingActionButton)findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, EditorActivity.class);
                startActivity(intent);
            }
        });

        mHabitDbHelper = new HabitDbHelper(this);
        displayDatabaseInfo();
    }

    private Cursor displayDatabaseInfo() {
        // Gets the database in write mode
        SQLiteDatabase db = mHabitDbHelper.getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // the app will actually use after this query
        String[] projection = {
                HabitEntry._ID,
                HabitEntry.COLUMN_HABIT,
                HabitEntry.COLUMN_HABIT_RANKING
        };

        Cursor cursor = db.query(HabitEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null);

        TextView displayTextView = (TextView)findViewById(R.id.text_view_habit);
        try {
            /**
             * Create a header in the TextView that looks like this:
             *
             * The myHabits table contains <number of rows in Cursor> habits.
             * _id - habit
             *
             * In the while loop below, iterate through the rows of the cursor and display
             * the information from each column in this order.
             */
            displayTextView.setText("The myHabits table contains " + cursor.getCount() + " habits.\n\n");
            displayTextView.append(HabitEntry._ID + " - " + HabitEntry.COLUMN_HABIT + " - " + HabitEntry.COLUMN_HABIT_RANKING);

            // Figure out the index of each column
            int idColumnIndex = cursor.getColumnIndex(HabitEntry._ID);
            int habitColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_HABIT);
            int rankingColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_HABIT_RANKING);

            // Iterate through all the returned rows in the cursor
            while (cursor.moveToNext()) {
                // Use that current index to extract the String or INt value of the habit
                // at the current row the cursor is on.
                int currentID = cursor.getInt(idColumnIndex);
                String currentHabit = cursor.getString(habitColumnIndex);
                int currentRanking = cursor.getInt(rankingColumnIndex);

                // Display the values from each column of the current row in the cursor in the TextView
                displayTextView.append("\n" + currentID + " - " + currentHabit + " - " + currentRanking);
            }

        } finally {
            // Always close the cursor when you're done reading from it. This releases all its
            // resources and makes it invalid.
            cursor.close();
        }

        return cursor;
    }

    private void insertHabit() {
        // Gets the database in write mode
        SQLiteDatabase db = mHabitDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(HabitEntry.COLUMN_HABIT, "Coding everyday!");
        values.put(HabitEntry.COLUMN_HABIT_RANKING, 1);

        long newRowId = db.insert(HabitEntry.TABLE_NAME, null, values);
    }

    private void deleteAll() {
        SQLiteDatabase db = mHabitDbHelper.getWritableDatabase();

        db.execSQL("DELETE FROM " + HabitEntry.TABLE_NAME);
    }

    @Override
    protected void onStart() {
        super.onStart();
        displayDatabaseInfo();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_catalog.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            // Respond to a click on the "Insert dummy data" menu option
            case R.id.action_insert_dummy_data:
                insertHabit();
                displayDatabaseInfo();
                return true;
            // Respond to a click on the "Delete all entries" menu option
            case R.id.action_delete_all_entries:
                // Do nothing for now
                deleteAll();
                displayDatabaseInfo();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
