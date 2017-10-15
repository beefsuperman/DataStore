package com.kunyang.android.datastore;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v4.app.NavUtils;

import com.kunyang.android.datastore.StudentDbSchema.StudentTable;

/**
 * Created by 坤阳 on 2017/10/15.
 */

public class StudentBaseHelper extends SQLiteOpenHelper {

    private static final int VERSION=1;
    private static final String DATABASE_NAME="studentBase.db";

    public StudentBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table "+ StudentTable.NAME+ "(" +
        " _id integer primary key autoincrement, " +
                        StudentTable.Cols.UUID+", " +
                        StudentTable.Cols.NAME+", " +
                        StudentTable.Cols.INFO+
                        ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
