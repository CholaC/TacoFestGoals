package com.tacofest.goals;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.EditText;
import android.util.Log;
/**
 * Created by ugochiokehi on 2016-10-21.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

   // public static final String DATABASE_NAME = "goals.db";


   // public static final String STUDENT_TABLE_NAME = "Student_table";
    //public static final String STUDENT_ID = "student_id";
    //public static final String STUDENT_USERNAME = "student_username";



   // public static final String COURSE_TABLE_NAME = "course_table";
    public String Create_Query = "CREATE TABLE " + TableData.TableInfo.STUDENT_TABLE + " ( " + TableData.TableInfo.STUDENT_ID + " TEXT PRIMARY KEY, " + TableData.TableInfo.STUDENT_NAME +" TEXT); ";

    public DatabaseHelper(Context context) {
        super(context, TableData.TableInfo.DATABASE_NAME , null, 1);
        Log.e("database operation","database is opened");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Create_Query);
        Log.e("database opertion","table created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
    }

    public void addStudentdata(DatabaseHelper dbh ,String id, String username){
        SQLiteDatabase db = dbh.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TableData.TableInfo.STUDENT_ID, id);
        contentValues.put(TableData.TableInfo.STUDENT_NAME, username);
        db.insert(TableData.TableInfo.STUDENT_TABLE, null, contentValues);
        Log.e("database operation","one row entered");
    }
}
