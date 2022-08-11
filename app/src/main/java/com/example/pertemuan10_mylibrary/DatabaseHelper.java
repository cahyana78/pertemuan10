package com.example.pertemuan10_mylibrary;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "student_database";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_STUDENTS = "student";
    private static final String KEY_ID = "id";
    private static final String KEY_FIRSTNAME = "name";

    private static final String CREATE_TABLE_STUDENTS = "CREATE TABLE "
            + TABLE_STUDENTS + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + KEY_FIRSTNAME + " TEXT );";


    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.d("table", CREATE_TABLE_STUDENTS);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE_STUDENTS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS '" + TABLE_STUDENTS + "'" );
        onCreate(sqLiteDatabase);
    }

    public long addStudentDetail(String student){
        SQLiteDatabase db = this.getWritableDatabase();
        //buat content values
        ContentValues values = new ContentValues();
        values.put(KEY_FIRSTNAME, student);
        //insert ke table db
        long insert = db.insert(TABLE_STUDENTS, null, values);

        return insert;
    }

    @SuppressLint("Range")
    public ArrayList<String> getAllStudents(){
        ArrayList<String> studentsList = new ArrayList<>();
        String name = "";

        String selectQuery = "SELECT * FROM " + TABLE_STUDENTS;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if(cursor.moveToFirst()){
            do{
                name = cursor.getString(cursor.getColumnIndex(KEY_FIRSTNAME));
                //tambahkan data ke array list
                studentsList.add(name);
            }while(cursor.moveToNext());
            Log.d("array", studentsList.toString());
        }
        return studentsList;
    }
}
