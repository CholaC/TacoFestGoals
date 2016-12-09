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
    //start end

    public String Create_Query = "CREATE TABLE " + TableData.TableInfo.STUDENT_TABLE + " ( "
            + TableData.TableInfo.STUDENT_ID + " TEXT PRIMARY KEY, "
            + TableData.TableInfo.STUDENT_NAME +" TEXT); ";

    public String Create_Course_Query = "CREATE TABLE " + TableData.TableInfo.COURSE_TABLE + " ( "
            + TableData.TableInfo.COURSE_NAME + " TEXT, "
            + TableData.TableInfo.COURSE_STU_ID+" TEXT, "
            + TableData.TableInfo.TOTAL_PASSING_GRADE+" INTEGER, "
            + TableData.TableInfo.TOTAL_DESIRED_GRADE+" INTEGER, "
            + TableData.TableInfo.REQUIRED_DES_GRADE +" INTEGER, " //NEW*****
            + TableData.TableInfo.REQUIRED_PASS_GRADE +" INTEGER, " //NEW*****
            + TableData.TableInfo.PERCENT_COMPLETED+" INTEGER, "//****NEW
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

    public String Create_Time_Query = "CREATE TABLE " + TableData.TableInfo.OFFICEHOUR_TABLE+ " ( "
            + TableData.TableInfo.TIME_NAME  +" TEXT, "
            + TableData.TableInfo.OFF_COR_ID +" TEXT, "
            + TableData.TableInfo.OFF_CW_ID +" TEXT, "
            + TableData.TableInfo.OFF_STU_ID +" TEXT, "
            + TableData.TableInfo.MONTH+" INTEGER, "
            + TableData.TableInfo.DAY+" INTEGER, "
            + TableData.TableInfo.START_HOUR +" INTEGER, "
            + TableData.TableInfo.END_HOUR+" INTEGER, "
            + TableData.TableInfo.START_MINUTE+" INTEGER, "
            + TableData.TableInfo.END_MINUTE+" INTEGER, "
            +" FOREIGN KEY("+ TableData.TableInfo.OFF_STU_ID+") REFERENCES "+ TableData.TableInfo.STUDENT_TABLE+
            "("+ TableData.TableInfo.STUDENT_ID
            +"), FOREIGN KEY("+ TableData.TableInfo.OFF_COR_ID+") REFERENCES "+ TableData.TableInfo.COURSE_TABLE+
            "("+ TableData.TableInfo.COURSE_NAME
            +"), FOREIGN KEY("+ TableData.TableInfo.OFF_CW_ID+") REFERENCES "+ TableData.TableInfo.COURSEWORK_TABLE+
            "("+ TableData.TableInfo.COURSEWORK_NAME
            +"), PRIMARY KEY("+TableData.TableInfo.TIME_NAME+", "+ TableData.TableInfo.OFF_COR_ID+", "
            + TableData.TableInfo.OFF_STU_ID+ ", "+ TableData.TableInfo.DAY+", "+ TableData.TableInfo.MONTH+")); ";


    public static String[] ALL_KEYS =  new String[]{
            TableData.TableInfo.COURSEWORK_NAME,
            TableData.TableInfo.ACTUAL_GRADE,
            TableData.TableInfo.DESIRED_GRADE,
            TableData.TableInfo.PASSING_GRADE,
            TableData.TableInfo.WEIGHT,
            TableData.TableInfo.DUE_DATE
            };

    private static DatabaseHelper databaseHelper;

    public DatabaseHelper(Context context) {
        super(context, TableData.TableInfo.DATABASE_NAME , null, 4);
        Log.e("database operation","database is opened");
    }

    public static synchronized DatabaseHelper getInstance(Context context){
        if(databaseHelper == null){
            databaseHelper= new DatabaseHelper(context.getApplicationContext());
        }
        return databaseHelper;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Create_Query);
        db.execSQL(Create_Course_Query);
        db.execSQL(Create_CourseWork_Query);
        db.execSQL(Create_Time_Query);
        Log.e("database opertion","table created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+ TableData.TableInfo.STUDENT_TABLE);
        db.execSQL("DROP TABLE IF EXISTS "+ TableData.TableInfo.COURSE_TABLE);
        db.execSQL("DROP TABLE IF EXISTS "+ TableData.TableInfo.COURSEWORK_TABLE);
        db.execSQL("DROP TABLE IF EXISTS "+ TableData.TableInfo.OFFICEHOUR_TABLE);
        onCreate(db);
    }

    public void addStudentdata(String id, String username){
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TableData.TableInfo.STUDENT_ID, id);
        contentValues.put(TableData.TableInfo.STUDENT_NAME, username);
        db.insert(TableData.TableInfo.STUDENT_TABLE, null, contentValues);
        Log.e("database operation","one row entered");
    }

    public Cursor getStudent( String userName,String studentid){
        Cursor cr;
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        String[] columns = new String[]{ TableData.TableInfo.STUDENT_NAME,
                TableData.TableInfo.STUDENT_ID};
        String selection = "student_id =?";
        String[] selectionArgs = {userName};
        cr = db.query(true, TableData.TableInfo.STUDENT_TABLE,
                columns,selection,selectionArgs,null,null,null,null);
        if(cr != null){
            cr.moveToNext();
        }
        Log.e("database opertion","here"+ studentid);
        return cr;
    }


    public boolean studentExist(String username,String id){
        boolean result = false;
        Cursor cr;
        Log.e("studentexist","name"+username+"id"+id);
        cr = getStudent(username,id);


        if(cr == null|| cr.getCount()==0 || cr.isNull(0)){
            result = false;
            Log.e("studentexist","result"+ result);
            return result;
        }
        else{
            String stu_id = cr.getString(cr.getColumnIndex(TableData.TableInfo.STUDENT_ID));
            result = true;
            Log.e("studentexist here","result"+ result);
        }
        return result;
    }


    public void removeStudent(String studentid){
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        String selection = " student_id =?";
        String[] selectionArgs = {studentid};
        db.delete(TableData.TableInfo.STUDENT_TABLE,selection,selectionArgs);
    }

    //FUNCTION TO ADD COURSES

    public void addCourse(String courseName, String studentId, int tPassGrade, int tDesiredGrade,
                          String lectureTime){
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TableData.TableInfo.COURSE_NAME, courseName);
        contentValues.put(TableData.TableInfo.COURSE_STU_ID, studentId);
        contentValues.put(TableData.TableInfo.TOTAL_PASSING_GRADE, String.valueOf(tPassGrade));
        contentValues.put(TableData.TableInfo.TOTAL_DESIRED_GRADE, String.valueOf(tDesiredGrade));
        contentValues.put(TableData.TableInfo.REQUIRED_DES_GRADE, 0);
        contentValues.put(TableData.TableInfo.REQUIRED_PASS_GRADE, 0);
        contentValues.put(TableData.TableInfo.PERCENT_COMPLETED, 0);
        contentValues.put(TableData.TableInfo.FINAL_GRADE, 0);
        contentValues.put(TableData.TableInfo.AVERAGE_GRADE, 0);
        contentValues.put(TableData.TableInfo.LECTURE_TIME, lectureTime);
        db.insert(TableData.TableInfo.COURSE_TABLE, null, contentValues);
        Log.e("database operation","one row entered in course table ");

    }

    /*//update course table with final grade for student
    * input: course name, studentid and final grade
    * output: void*/
    public void addFinalGrade(String courseName, String studentid, int finalGrade){
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(TableData.TableInfo.FINAL_GRADE,String.valueOf(finalGrade)); //These Fields should be your String values of actual column names
        // myDB.update(TableName, cv, "_id="+id, null);
        db.update(TableData.TableInfo.COURSE_TABLE,cv,
                TableData.TableInfo.COURSE_STU_ID+" = "+studentid+" AND "+ TableData.TableInfo.COURSE_NAME+" = "+courseName,null);

    }

    /*//update course table with final grade for student
    * input: course name, studentid and final grade
    * output: void*/
    public void addRequiredDesGrade( String courseName, String studentid, int requiredDesGrade){
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(TableData.TableInfo.REQUIRED_DES_GRADE,String.valueOf(requiredDesGrade)); //These Fields should be your String values of actual column names
        // myDB.update(TableName, cv, "_id="+id, null);
        db.update(TableData.TableInfo.COURSE_TABLE,cv,
                TableData.TableInfo.COURSE_STU_ID+" = "+studentid+" AND "+ TableData.TableInfo.COURSE_NAME+" = "+courseName,null);

    }
    /*//update course table with final grade for student
    * input: course name, studentid and final grade
    * output: void*/
    public void addRequiredPassGrade(String courseName, String studentid, int requiredPassGrade){
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(TableData.TableInfo.REQUIRED_PASS_GRADE,String.valueOf(requiredPassGrade)); //These Fields should be your String values of actual column names
        // myDB.update(TableName, cv, "_id="+id, null);
        db.update(TableData.TableInfo.COURSE_TABLE,cv,
                TableData.TableInfo.COURSE_STU_ID+" = "+studentid+" AND "+ TableData.TableInfo.COURSE_NAME+" = "+courseName,null);

    }

    /*//update course table with final grade for student
    * input: course name, studentid and final grade
    * output: void*/
    public void addPercentCompleted( String courseName, String studentid, int percentComplete){
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        String where = "_id = ? AND course_stu_id = ?";
        String[] whereArgs = new String[]{courseName,studentid};
        ContentValues cv = new ContentValues();
        cv.put(TableData.TableInfo.PERCENT_COMPLETED,String.valueOf(percentComplete)); //These Fields should be your String values of actual column names
        // myDB.update(TableName, cv, "_id="+id, null);
        db.update(TableData.TableInfo.COURSE_TABLE,cv,where,whereArgs);

    }

    /*//update course table with average grade for student
    * input: course name, studentid and final grade
    * output: void*/
    public void addAverageGrade(String courseName, String studentid, int averageGrade){
        Log.e("add average grade","averagegrade "+averageGrade);
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        String where = "_id = ? AND course_stu_id = ?";
        String[] whereArgs = new String[]{courseName,studentid};
        ContentValues cv = new ContentValues();
        cv.put(TableData.TableInfo.AVERAGE_GRADE,String.valueOf(averageGrade)); //These Fields should be your String values of actual column names
        // myDB.update(TableName, cv, "_id="+id, null);
        db.update(TableData.TableInfo.COURSE_TABLE,cv,where,whereArgs);
    }

    //update course table with total passing grade
    public void addTotalPassGrade( String courseName, String studentid, int tPassGrade){
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(TableData.TableInfo.TOTAL_PASSING_GRADE,String.valueOf(tPassGrade)); //These Fields should be your String values of actual column names
        // myDB.update(TableName, cv, "_id="+id, null);
        db.update(TableData.TableInfo.COURSE_TABLE,cv,
                TableData.TableInfo.COURSE_STU_ID+" = "+studentid+" AND "+ TableData.TableInfo.COURSE_NAME+" = "+courseName,null);
    }

    //update course table with total desired grade
    public void addDesiredGrade( String courseName, String studentid, int tDesiredGrade){
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(TableData.TableInfo.TOTAL_DESIRED_GRADE,String.valueOf(tDesiredGrade)); //These Fields should be your String values of actual column names
        // myDB.update(TableName, cv, "_id="+id, null);
        db.update(TableData.TableInfo.COURSE_TABLE,cv,
                TableData.TableInfo.COURSE_STU_ID+" = "+studentid+" AND "+ TableData.TableInfo.COURSE_NAME+" = "+courseName,null);
    }

    /*get Students course list
    * input: String studentId
    * output: Cursor array*/
    public Cursor getStudentCourseList( String studentid){
        Cursor cr;
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        String[] columns = new String[]{ TableData.TableInfo.COURSE_NAME,
                                         TableData.TableInfo.TOTAL_DESIRED_GRADE,
                TableData.TableInfo.TOTAL_PASSING_GRADE};
        String selection = " course_stu_id =?";
        String[] selectionArgs = {studentid};
        cr = db.query(true, TableData.TableInfo.COURSE_TABLE,
                columns,selection,selectionArgs,null,null,null,null);
//        if(cr != null){
//            cr.moveToNext();
//        }
        Log.e("database opertion","here"+ studentid);
        return cr;
    }


    public void removeCourse(String course,String studentid){
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        String selection = " _id =? AND course_stu_id =?";
        String[] selectionArgs = {course,studentid};
        db.delete(TableData.TableInfo.COURSE_TABLE,selection,selectionArgs);
        Log.e("removeCourse","deleted "+course+"for "+studentid);
    }

    /*************************COURSEWORK TABLE***************************/
    /*get course info for student
    input: String studentId
    * output: Cursor*/
    public void addCourseWork( String courseName, String studentId,
                              String courseWorkName,String dueDate, int weight){
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TableData.TableInfo.COURSEWORK_NAME, courseWorkName);
        contentValues.put(TableData.TableInfo.COURSEWORK_COR_ID, courseName);
        contentValues.put(TableData.TableInfo.COURSEWORK_STU_ID, studentId);
        contentValues.put(TableData.TableInfo.WEIGHT, String.valueOf(weight));
        contentValues.put(TableData.TableInfo.PASSING_GRADE, 0);
        contentValues.put(TableData.TableInfo.DESIRED_GRADE, 0);
        contentValues.put(TableData.TableInfo.ACTUAL_GRADE, 0);
        contentValues.put(TableData.TableInfo.DUE_DATE, dueDate);
        db.insert(TableData.TableInfo.COURSEWORK_TABLE, null, contentValues);
        Log.e("database operation","one row entered in course table ");
    }

    //add passing grade
    public void addPassingGrade(String courseWorkName, String courseName, String studentid, int passingGrade){
        String where = "_id = ? AND coursework_stu_id = ? AND coursework_cor_id = ?";
        String[] whereArgs = new String[]{courseWorkName,studentid,courseName};
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(TableData.TableInfo.PASSING_GRADE,String.valueOf(passingGrade)); //These Fields should be your String values of actual column names
        db.update(TableData.TableInfo.COURSEWORK_TABLE,cv,where,whereArgs);
    }

    // add desired grade
    public void addDesiredGrade(String courseWorkName, String courseName, String studentid, int desiredGrade){
        String where = "_id = ? AND coursework_stu_id = ? AND coursework_cor_id = ?";
        String[] whereArgs = new String[]{courseWorkName,studentid,courseName};
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(TableData.TableInfo.DESIRED_GRADE,String.valueOf(desiredGrade)); //These Fields should be your String values of actual column names
        db.update(TableData.TableInfo.COURSEWORK_TABLE,cv,where,whereArgs);
    }

    //add actual grade
    public void addActualGrade(String courseWorkName, String courseName, String studentid, int actualGrade){
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        String where = "_id = ? AND coursework_stu_id = ? AND coursework_cor_id = ?";
        String[] whereArgs = new String[]{courseWorkName,studentid,courseName};
        cv.put(TableData.TableInfo.ACTUAL_GRADE,String.valueOf(actualGrade)); //These Fields should be your String values of actual column names
        db.update(TableData.TableInfo.COURSEWORK_TABLE,cv,where,whereArgs);
        Log.e("course work table","added actual grade"+actualGrade+" "+courseWorkName+""+courseName+" "+studentid);
    }

    /*get CourseWork list returning
    courseworkname
    actual grade
    desired grade
    passing grade
    */
    public Cursor getCourseWorkList( String courseName, String studentid){
        Cursor cr;
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        String table = "table2";
        String[] columns = {"column1", "column3"};
        String selection = "coursework_cor_id =? AND coursework_stu_id =?";
        String[] selectionArgs = {courseName,studentid};
        cr = db.query(true, TableData.TableInfo.COURSEWORK_TABLE,
               ALL_KEYS,selection,selectionArgs,null,null,null,null);
        if(cr != null){
            cr.moveToNext();
        }
        Log.e("database opertion","coursework "+courseName+" "+studentid);
        return cr;
    }
    public Cursor getCourseWork( String courseName, String studentid, String courseWork){
        Cursor cr;
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        String table = "table2";
        String[] columns = new String[]{};
        String selection = "coursework_cor_id =? AND coursework_stu_id =? AND _id";
        String[] selectionArgs = {courseName,studentid,courseWork};
        cr = db.query(true, TableData.TableInfo.COURSEWORK_TABLE,
                ALL_KEYS,selection,selectionArgs,null,null,null,null);
        if(cr != null){
            cr.moveToNext();
        }
        Log.e("database opertion","here"+courseName+" "+studentid+" "+courseWork);
        return cr;
    }
    public Cursor getTotalDesired( String courseName, String studentid){
        Cursor cr;
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        String selection = "course_id =? AND course_stu_id =?";
        String[] selectionArgs = {courseName,studentid};
        cr = db.query(true, TableData.TableInfo.COURSE_TABLE,
                new String[]{TableData.TableInfo.TOTAL_DESIRED_GRADE},selection,selectionArgs,null,null,null,null);
        if(cr != null){
            cr.moveToNext();
        }
        Log.e("database opertion","here"+courseName+" "+studentid);
        return cr;
    }
    public Cursor getTotalPassing( String courseName, String studentid){
        Cursor cr;
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        String selection = "coursework_cor_id =? AND coursework_stu_id =?";
        String[] selectionArgs = {courseName,studentid};
        cr = db.query(true, TableData.TableInfo.COURSE_TABLE,
                new String[]{TableData.TableInfo.TOTAL_PASSING_GRADE},selection,selectionArgs,null,null,null,null);
        if(cr != null){
            cr.moveToNext();
        }
        Log.e("database opertion","here"+courseName+" "+studentid);
        return cr;
    }
    public int getPercentCompleted( String courseName, String studentid){
        Cursor cr;
        int result = 0;
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        String selection = "coursework_cor_id =? AND coursework_stu_id =?";
        String[] selectionArgs = {courseName,studentid};
        cr = db.query(true, TableData.TableInfo.COURSE_TABLE,
                new String[]{TableData.TableInfo.PERCENT_COMPLETED},selection,selectionArgs,null,null,null,null);
        if(cr != null){
            //cr.moveToNext();
            result = cr.getInt(cr.getColumnIndex(TableData.TableInfo.PERCENT_COMPLETED));
        }
        Log.e("database opertion","here"+courseName+" "+studentid);
        return result;
    }

    public Cursor getCourseAt(String studentid, int position){
        Cursor cr;
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        String selection = "course_stu_id =? AND rowid =?";
        String[] selectionArgs = {studentid, String.valueOf(position)};
        cr = db.query(true, TableData.TableInfo.COURSEWORK_TABLE,
                null,selection,selectionArgs,null,null,null,null);
        if(cr != null){
            cr.moveToNext();
        }
        Log.e("database opertion","here "+studentid);
        return cr;
    }

    public Cursor getCourseWorkAt( String courseName, String studentid, int position){
        Cursor cr;
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        String selection = "coursework_cor_id =? AND coursework_stu_id =? AND rowid =?";
        String[] selectionArgs = {courseName,studentid, String.valueOf(position)};
        cr = db.query(true, TableData.TableInfo.COURSEWORK_TABLE,
                null,selection,selectionArgs,null,null,null,null);
        if(cr != null){
            cr.moveToNext();
        }
        Log.e("database opertion","here"+courseName+" "+studentid);
        return cr;
    }
    public Cursor getCourseWorkNameAt( String courseName, String studentid, int position){
        Cursor cr;
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        String selection = "coursework_cor_id =? AND coursework_stu_id =? AND rowid =?";
        String[] selectionArgs = {courseName,studentid, String.valueOf(position)};
        cr = db.query(true, TableData.TableInfo.COURSEWORK_TABLE,
                new String[]{TableData.TableInfo.COURSEWORK_NAME},selection,selectionArgs,null,null,null,null);
        if(cr != null){
            cr.moveToNext();
        }
        Log.e("database opertion","here"+courseName+" "+studentid);
        return cr;
    }

    public void removeCourseWork(String courseWork,String course,String studentid){
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        String selection = " _id =? AND coursework_cor_id AND coursework_stu_id =?";
        String[] selectionArgs = {courseWork,course,studentid};
        //db.delete(TableData.TableInfo.COURSEWORK_TABLE,selection,selectionArgs);
        db.execSQL("DELETE FROM coursework_table WHERE _id ='"+ courseWork+"' AND coursework_cor_id = '"+course+"' AND coursework_stu_id = '"+studentid+"'");
        Log.e("removeCourseWork","deleted coursework "+courseWork+"for "+studentid);
    }

    public void removeCourseWorkList(String course,String studentid){
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        String selection = "coursework_cor_id AND coursework_stu_id =?";
        String[] selectionArgs = {course,studentid};
        //db.delete(TableData.TableInfo.COURSEWORK_TABLE,selection,selectionArgs);
        db.execSQL("DELETE FROM coursework_table WHERE coursework_cor_id = '"+course+"' AND coursework_stu_id = '"+studentid+"'");
        Log.e("removeCourseWorkList","deleted coursework "+course+"for "+studentid);
    }
    /************TIME TABLE************/
    //for office hour
    public void addOfficeTime(String timeName, String studentId,
                        String courseName, int month, int day, int startHour, int endHour, int startMinute, int endMinute){
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TableData.TableInfo.TIME_NAME, timeName);
        contentValues.put(TableData.TableInfo.OFF_COR_ID, courseName);
        contentValues.put(TableData.TableInfo.OFF_STU_ID, studentId);
        contentValues.put(TableData.TableInfo.OFF_CW_ID, "");
        contentValues.put(TableData.TableInfo.MONTH, month);
        contentValues.put(TableData.TableInfo.DAY, day);
        contentValues.put(TableData.TableInfo.START_HOUR, startHour);
        contentValues.put(TableData.TableInfo.END_HOUR, endHour);
        contentValues.put(TableData.TableInfo.START_MINUTE, startMinute);
        contentValues.put(TableData.TableInfo.END_MINUTE, endMinute);
        contentValues.put(TableData.TableInfo.START_HOUR, startHour);
        db.insert(TableData.TableInfo.OFFICEHOUR_TABLE, null, contentValues);
        Log.e("database operation","one row entered in course table ");
    }

    //for office hour
    public void addOtherTime(String timeName, String studentId,
                        String courseName, String courseWork,int month, int day, int startHour, int endHour, int startMinute, int endMinute){
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TableData.TableInfo.TIME_NAME, timeName);
        contentValues.put(TableData.TableInfo.OFF_COR_ID, courseName);
        contentValues.put(TableData.TableInfo.OFF_STU_ID, studentId);
        contentValues.put(TableData.TableInfo.OFF_CW_ID, courseWork);
        contentValues.put(TableData.TableInfo.MONTH, month);
        contentValues.put(TableData.TableInfo.DAY, day);
        contentValues.put(TableData.TableInfo.START_HOUR, startHour);
        contentValues.put(TableData.TableInfo.END_HOUR, endHour);
        contentValues.put(TableData.TableInfo.START_MINUTE, startMinute);
        contentValues.put(TableData.TableInfo.END_MINUTE, endMinute);
        contentValues.put(TableData.TableInfo.START_HOUR, startHour);
        db.insert(TableData.TableInfo.OFFICEHOUR_TABLE, null, contentValues);
        Log.e("database operation","one row entered in course table ");
    }

    public Cursor getTime(String studentid){
        Cursor cr;
        int result = 0;
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        String[] fields = new String[]{TableData.TableInfo.TIME_NAME,TableData.TableInfo.START_HOUR, TableData.TableInfo.END_HOUR
                ,TableData.TableInfo.START_MINUTE, TableData.TableInfo.END_MINUTE, TableData.TableInfo.MONTH, TableData.TableInfo.DAY};
        String selection = "student_id =?";
        String[] selectionArgs = {studentid};
        cr = db.query(true, TableData.TableInfo.OFFICEHOUR_TABLE,
               fields,selection,selectionArgs,null,null,null,null);
        if(cr != null){
            cr.moveToNext();
            //result = cr.getInt(cr.getColumnIndex(TableData.TableInfo.PERCENT_COMPLETED));
        }
        Log.e("database opertion","here"+" "+studentid);
        return cr;
    }
}
