package com.example.sutheres.goodhabits;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.example.sutheres.goodhabits.data.HabitContract.HabitEntry;
import com.example.sutheres.goodhabits.data.HabitDbHelper;

public class EditorActivity extends AppCompatActivity {

    private EditText mHabitEditText;
    private EditText mCategoryEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        mHabitEditText = (EditText)findViewById(R.id.habit_edit_text);
        mCategoryEditText = (EditText)findViewById(R.id.category_edit_text);
    }

    private void insertHabit() {
        String habitString = mHabitEditText.getText().toString().trim();
        String categoryString = mCategoryEditText.getText().toString().trim();

        HabitDbHelper mHabitDbHelper = new HabitDbHelper(this);

        SQLiteDatabase db = mHabitDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(HabitEntry.COLUMN_HABIT, habitString);
        values.put(HabitEntry.COLUMN_HABIT_RANKING, categoryString);

        long newRowID = db.insert(HabitEntry.TABLE_NAME, null, values);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_catalog.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.menu_editor, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        insertHabit();
        finish();
        return super.onOptionsItemSelected(item);
    }

}
