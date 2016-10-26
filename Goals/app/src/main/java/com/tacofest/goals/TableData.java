package com.tacofest.goals;

import android.provider.BaseColumns;

/**
 * Created by ugochiokehi on 2016-10-26.
 */

public class TableData {
    public TableData(){

    }
    public static abstract class TableInfo implements BaseColumns{
        //student table
        public static final String STUDENT_NAME =  "student_name";
        public static final String STUDENT_ID =  "student_id";
        public static final String DATABASE_NAME =  "goals.db";
        public static final String STUDENT_TABLE =  "student_table";

        //public static final String STUDENT_NAME =  "student_id";
        public static final String COURSE_NAME =  "course_name";
        public static final String COURSE_TABLE =  "course_table";





    }

}
