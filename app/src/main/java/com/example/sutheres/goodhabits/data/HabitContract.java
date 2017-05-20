package com.example.sutheres.goodhabits.data;

import android.provider.BaseColumns;

/**
 * Created by Sutheres on 1/12/2017.
 */

public final class HabitContract {

    private HabitContract(){}

    public static final class HabitEntry implements BaseColumns {
        public static final String TABLE_NAME = "myHabits";

        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_HABIT = "habits";
        public static final String COLUMN_HABIT_RANKING = "ranking";
    }
}
