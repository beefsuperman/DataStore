package com.kunyang.android.datastore;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.ListView;

import com.kunyang.android.datastore.StudentDbSchema.StudentTable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by 坤阳 on 2017/10/15.
 */

public class StudentLab {
    private static StudentLab sStudentLab;


    private Context mContext;
    private SQLiteDatabase mDatabase;

    public static StudentLab get(Context context){
        if (sStudentLab==null){
            sStudentLab=new StudentLab(context);
        }
        return sStudentLab;
    }

    private StudentLab(Context context){
        mContext=context.getApplicationContext();
        mDatabase=new StudentBaseHelper(mContext).getWritableDatabase();


    }

    public void addStudent(Student s){
        ContentValues values=getContentValues(s);

        mDatabase.insert(StudentTable.NAME,null,values);
    }

    public void deleteStudent(Student s){
        String uuidString= s.getId().toString();

        mDatabase.delete(StudentTable.NAME,StudentTable.Cols.UUID+"=?",new String[] {uuidString});
    }

    public List<Student> getStudents(){
        List<Student> students=new ArrayList<>();

        StudentCursorWrapper cursor=queryStudents(null,null);

        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                students.add(cursor.getStudent());
                cursor.moveToNext();
            }
        }finally {
            cursor.close();
        }

        return students;
    }

    public Student getStudent(UUID id){

        StudentCursorWrapper cursor=queryStudents(
                StudentTable.Cols.UUID+" =?",
                new String[] {id.toString()}
        );

        try {
            if (cursor.getCount()==0){
                return null;
            }

            cursor.moveToFirst();
            return cursor.getStudent();
        }finally {
            cursor.close();
        }
    }

    public void updateStudent(Student student){
        String uuidString=student.getId().toString();
        ContentValues values=getContentValues(student);

        mDatabase.update(StudentTable.NAME,values, StudentTable.Cols.UUID+" =?",
                new String[]{uuidString});
    }

    private static ContentValues getContentValues(Student student){
        ContentValues values=new ContentValues();
        values.put(StudentTable.Cols.UUID,student.getId().toString());
        values.put(StudentTable.Cols.NAME,student.getName());
        values.put(StudentTable.Cols.INFO,student.getInfo());

        return values;
    }

    private StudentCursorWrapper queryStudents(String whereClause,String[] whereArgs){
        Cursor cursor=mDatabase.query(
                StudentTable.NAME,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null
        );
        return new StudentCursorWrapper(cursor);
    }
}
