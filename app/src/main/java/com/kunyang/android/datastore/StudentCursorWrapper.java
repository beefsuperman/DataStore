package com.kunyang.android.datastore;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.kunyang.android.datastore.StudentDbSchema.StudentTable;

import java.util.UUID;

/**
 * Created by 坤阳 on 2017/10/15.
 */

public class StudentCursorWrapper extends CursorWrapper {
    public StudentCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Student getStudent(){
        String uuidString=getString(getColumnIndex(StudentTable.Cols.UUID));
        String name=getString(getColumnIndex(StudentTable.Cols.NAME));
        String info=getString(getColumnIndex(StudentTable.Cols.INFO));

        Student student=new Student(UUID.fromString(uuidString));
        student.setName(name);
        student.setInfo(info);

        return student;
    }
}
