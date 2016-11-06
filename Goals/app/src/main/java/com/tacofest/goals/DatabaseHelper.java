package com.tacofest.goals;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.EditText;
import android.util.Log;
/**
 * Created by ugochiokehi on 2016-10-21.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    public String Create_Query = "CREATE TABLE " + TableData.TableInfo.STUDENT_TABLE + " ( "
            + TableData.TableInfo.STUDENT_ID + " TEXT PRIMARY KEY, "
            + TableData.TableInfo.STUDENT_NAME +" TEXT); ";

        public String Create_Course_Query = "CREATE TABLE " + TableData.TableInfo.COURSE_TABLE + " ( "
                + TableData.TableInfo.COURSE_NAME + " TEXT, "
                + TableData.TableInfo.COURSE_STU_ID+" TEXT, "
                + TableData.TableInfo.TOTAL_PASSING_GRADE+" INTEGER, "
                + TableData.TableInfo.TOTAL_DESIRED_GRADE+" INTEGER, "
                + TableData.TableInfo.FINAL_GRADE+" INTEGER, "
                + TableData.TableInfo.AVERAGE_GRADE+" INTEGER, "
                + TableData.TableInfo.LECTURE_TIME+" TEXT, "
                + "FOREIGN KEY("+ TableData.TableInfo.COURSE_STU_ID+") REFERENCES "+ TableData.TableInfo.STUDENT_TABLE+
                "("+ TableData.TableInfo.STUDENT_ID
                +"), PRIMARY KEY("+ TableData.TableInfo.COURSE_NAME+", "+ TableData.TableInfo.COURSE_STU_ID+")); ";

    public String Create_CourseWork_Query = "CREATE TABLE " + TableData.TableInfo.COURSEWORK_TABLE+ " ( "
            + TableData.TableInfo.COURSEWORK_NAME +" TEXT, "
            + TableData.TableInfo.COURSEWORK_COR_ID +" TEXT, "
            + TableData.TableInfo.COURSEWORK_STU_ID +" TEXT, "
            + TableData.TableInfo.WEIGHT  +" INTEGER, "
            + TableData.TableInfo.PASSING_GRADE +" INTEGER, "
            + TableData.TableInfo.DESIRED_GRADE +" INTEGER, "
            + TableData.TableInfo.ACTUAL_GRADE +" INTEGER, "
            + TableData.TableInfo.DUE_DATE +" TEXT,"
            +" FOREIGN KEY("+ TableData.TableInfo.COURSEWORK_STU_ID+") REFERENCES "+ TableData.TableInfo.STUDENT_TABLE+
            "("+ TableData.TableInfo.STUDENT_ID
            +"), FOREIGN KEY("+ TableData.TableInfo.COURSEWORK_COR_ID+") REFERENCES "+ TableData.TableInfo.COURSE_TABLE+
            "("+ TableData.TableInfo.COURSE_NAME
            +"), PRIMARY KEY("+ TableData.TableInfo.COURSEWORK_NAME+", "+ TableData.TableInfo.COURSEWORK_COR_ID+", "+ TableData.TableInfo.COURSEWORK_STU_ID+")); ";



    public DatabaseHelper(Context context) {
        super(context, TableData.TableInfo.DATABASE_NAME , null, 3);
        Log.e("database operation","database is opened");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Create_Query);
        db.execSQL(Create_Course_Query);
        db.execSQL(Create_CourseWork_Query);
        Log.e("database opertion","table created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+ TableData.TableInfo.STUDENT_TABLE);
        db.execSQL("DROP TABLE IF EXISTS "+ TableData.TableInfo.COURSE_TABLE);
        db.execSQL("DROP TABLE IF EXISTS "+ TableData.TableInfo.COURSEWORK_TABLE);
        onCreate(db);
    }

    public void addStudentdata(DatabaseHelper dbh ,String id, String username){
        SQLiteDatabase db = dbh.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TableData.TableInfo.STUDENT_ID, id);
        contentValues.put(TableData.TableInfo.STUDENT_NAME, username);
        db.insert(TableData.TableInfo.STUDENT_TABLE, null, contentValues);
        Log.e("database operation","one row entered");
    }

    public void checkStudentdata(DatabaseHelper dbh ,String id, String username){
        SQLiteDatabase db = dbh.getWritableDatabase();
        String Check_Student_Query = "SELECT * FROM "+ TableData.TableInfo.STUDENT_TABLE +"WHERE " +
                TableData.TableInfo.STUDENT_ID + " IS '"+ id +"' AND " +
                TableData.TableInfo.STUDENT_NAME+" IS '"+ username+"';";
    }
    //FUNCTION TO ADD COURSES

    public void addCourse(DatabaseHelper dbh, String courseName, String studentId, int tPassGrade, int tDesiredGrade,
                          String lectureTime){
        SQLiteDatabase db = dbh.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TableData.TableInfo.COURSE_NAME, courseName);
        contentValues.put(TableData.TableInfo.COURSE_STU_ID, studentId);
        contentValues.put(TableData.TableInfo.TOTAL_PASSING_GRADE, String.valueOf(tPassGrade));
        contentValues.put(TableData.TableInfo.TOTAL_DESIRED_GRADE, String.valueOf(tDesiredGrade));
        contentValues.put(TableData.TableInfo.FINAL_GRADE, 0);
        contentValues.put(TableData.TableInfo.AVERAGE_GRADE, 0);
        contentValues.put(TableData.TableInfo.LECTURE_TIME, lectureTime);
        db.insert(TableData.TableInfo.COURSE_TABLE, null, contentValues);
        Log.e("database operation","one row entered in course table ");
    }


}
