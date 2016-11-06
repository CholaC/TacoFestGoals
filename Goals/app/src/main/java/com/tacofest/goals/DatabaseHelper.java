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

    /*//update course table with final grade for student
    * input: course name, studentid and final grade
    * output: void*/
    public void addFinalGrade(DatabaseHelper dbh, String courseName, String studentid, int finalGrade){
        SQLiteDatabase db = dbh.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(TableData.TableInfo.FINAL_GRADE,String.valueOf(finalGrade)); //These Fields should be your String values of actual column names
       // myDB.update(TableName, cv, "_id="+id, null);
        db.update(TableData.TableInfo.COURSE_TABLE,cv,
                TableData.TableInfo.COURSE_STU_ID+" = "+studentid+" AND "+ TableData.TableInfo.COURSE_NAME+" = "+courseName,null);

    }


    /*//update course table with average grade for student
    * input: course name, studentid and final grade
    * output: void*/
    public void addAverageGrade(DatabaseHelper dbh, String courseName, String studentid, int averageGrade){
        SQLiteDatabase db = dbh.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(TableData.TableInfo.AVERAGE_GRADE,String.valueOf(averageGrade)); //These Fields should be your String values of actual column names
        // myDB.update(TableName, cv, "_id="+id, null);
        db.update(TableData.TableInfo.COURSE_TABLE,cv,
                TableData.TableInfo.COURSE_STU_ID+" = "+studentid+" AND "+ TableData.TableInfo.COURSE_NAME+" = "+courseName,null);
    }

    //update course table with total passing grade
    public void addTotalPassGrade(DatabaseHelper dbh, String courseName, String studentid, int tPassGrade){
        SQLiteDatabase db = dbh.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(TableData.TableInfo.TOTAL_PASSING_GRADE,String.valueOf(tPassGrade)); //These Fields should be your String values of actual column names
        // myDB.update(TableName, cv, "_id="+id, null);
        db.update(TableData.TableInfo.COURSE_TABLE,cv,
                TableData.TableInfo.COURSE_STU_ID+" = "+studentid+" AND "+ TableData.TableInfo.COURSE_NAME+" = "+courseName,null);
    }

    //update course table with total desired grade
    public void addDesiredGrade(DatabaseHelper dbh, String courseName, String studentid, int tDesiredGrade){
        SQLiteDatabase db = dbh.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(TableData.TableInfo.TOTAL_DESIRED_GRADE,String.valueOf(tDesiredGrade)); //These Fields should be your String values of actual column names
        // myDB.update(TableName, cv, "_id="+id, null);
        db.update(TableData.TableInfo.COURSE_TABLE,cv,
                TableData.TableInfo.COURSE_STU_ID+" = "+studentid+" AND "+ TableData.TableInfo.COURSE_NAME+" = "+courseName,null);
    }

    /*get Students course list
    * input: String studentId
    * output: Cursor array*/
    public Cursor getStudentCourseList(DatabaseHelper dbh, String studentid){
        Cursor cr;
        SQLiteDatabase db = dbh.getWritableDatabase();
        cr = db.query(TableData.TableInfo.COURSE_TABLE, new String[]{TableData.TableInfo.COURSE_NAME},
                TableData.TableInfo.COURSE_STU_ID+ "="+ studentid,null, null, null, null, null);
        return cr;
    }
    /*************************COURSEWORK TABLE***************************/
    /*get course info for student
    input: String studentId
    * output: Cursor*/
    public void addCourseWork(DatabaseHelper dbh, String courseName, String studentId,
                              String courseWorkName,String dueDate, int weight){
        SQLiteDatabase db = dbh.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TableData.TableInfo.COURSEWORK_NAME, courseWorkName);
        contentValues.put(TableData.TableInfo.COURSEWORK_COR_ID, courseName);
        contentValues.put(TableData.TableInfo.COURSEWORK_STU_ID, studentId);
        contentValues.put(TableData.TableInfo.WEIGHT, String.valueOf(weight));
        contentValues.put(TableData.TableInfo.PASSING_GRADE, 0);
        contentValues.put(TableData.TableInfo.DESIRED_GRADE, 0);
        contentValues.put(TableData.TableInfo.ACTUAL_GRADE, 0);
        contentValues.put(TableData.TableInfo.LECTURE_TIME, dueDate);
        db.insert(TableData.TableInfo.COURSEWORK_TABLE, null, contentValues);
        Log.e("database operation","one row entered in course table ");
    }

    //add passing grade
    public void addPassingGrade(DatabaseHelper dbh,String courseWorkName, String courseName, String studentid, int passingGrade){
        SQLiteDatabase db = dbh.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(TableData.TableInfo.PASSING_GRADE,String.valueOf(passingGrade)); //These Fields should be your String values of actual column names
        db.update(TableData.TableInfo.COURSEWORK_TABLE,cv,
                TableData.TableInfo.COURSEWORK_NAME+" = "+courseWorkName+" AND "+
                        TableData.TableInfo.COURSEWORK_STU_ID+" = "+studentid+" AND "+
                        TableData.TableInfo.COURSEWORK_COR_ID+" = "+courseName,null);
    }

    // add desired grade
    public void addDesiredGrade(DatabaseHelper dbh,String courseWorkName, String courseName, String studentid, int desiredGrade){
        SQLiteDatabase db = dbh.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(TableData.TableInfo.DESIRED_GRADE,String.valueOf(desiredGrade)); //These Fields should be your String values of actual column names
        db.update(TableData.TableInfo.COURSEWORK_TABLE,cv,
                TableData.TableInfo.COURSEWORK_NAME+" = "+courseWorkName+" AND "+
                        TableData.TableInfo.COURSEWORK_STU_ID+" = "+studentid+" AND "+
                        TableData.TableInfo.COURSEWORK_COR_ID+" = "+courseName,null);
    }

    //add actual grade
    public void addActualGrade(DatabaseHelper dbh,String courseWorkName, String courseName, String studentid, int actualGrade){
        SQLiteDatabase db = dbh.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(TableData.TableInfo.ACTUAL_GRADE,String.valueOf(actualGrade)); //These Fields should be your String values of actual column names
        db.update(TableData.TableInfo.COURSEWORK_TABLE,cv,
                TableData.TableInfo.COURSEWORK_NAME+" = "+courseWorkName+" AND "+
                        TableData.TableInfo.COURSEWORK_STU_ID+" = "+studentid+" AND "+
                        TableData.TableInfo.COURSEWORK_COR_ID+" = "+courseName,null);
    }

    /*get CourseWork list returning
    courseworkname
    actual grade
    desired grade
    passing grade
    */
    public Cursor getCourseWork(DatabaseHelper dbh, String courseName, String studentid){
        Cursor cr;
        SQLiteDatabase db = dbh.getWritableDatabase();
        cr = db.query(TableData.TableInfo.COURSEWORK_TABLE, new String[]{TableData.TableInfo.COURSEWORK_NAME,
                        TableData.TableInfo.ACTUAL_GRADE, TableData.TableInfo.DESIRED_GRADE, TableData.TableInfo.PASSING_GRADE},
                TableData.TableInfo.COURSEWORK_COR_ID+ " = "+ courseName+" AND "+
                TableData.TableInfo.COURSEWORK_STU_ID+ " = "+ studentid
                ,null, null, null, null, null);
        return cr;
    }
}
