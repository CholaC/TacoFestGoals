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
        public static final String STUDENT_NAME     =  "student_name";
        public static final String STUDENT_ID       =  "student_id";
        public static final String DATABASE_NAME    =  "goals.db";
        public static final String STUDENT_TABLE    =  "student_table";

        //course  table
        public static final String COURSE_TABLE         =  "course_table";
        public static final String COURSE_NAME          =  "_id";
        public static final String COURSE_STU_ID        =  "course_stu_id";
        public static final String TOTAL_PASSING_GRADE  =  "total_passing_grade";
        public static final String TOTAL_DESIRED_GRADE  =  "total_desired_grade";
        public static final String REQUIRED_DES_GRADE   =  "required_des_grade";
        public static final String REQUIRED_PASS_GRADE  =  "required_pass_grade";
        public static final String PERCENT_COMPLETED    =  "percent_completed";
        public static final String FINAL_GRADE          =  "final_grade";
        public static final String AVERAGE_GRADE        =  "average_grade";
        public static final String LECTURE_TIME         =  "lecture_time";



        public static final String COURSEWORK_TABLE    =  "coursework_table";
        public static final String COURSEWORK_NAME     =  "_id";
        public static final String COURSEWORK_COR_ID   =  "coursework_cor_id";
        public static final String COURSEWORK_STU_ID   =  "coursework_stu_id";
        public static final String WEIGHT              =  "weight";
        public static final String PASSING_GRADE       =  "passing_grade";
        public static final String DESIRED_GRADE       =  "desired_grade";
        public static final String ACTUAL_GRADE        =  "actual_grade";
        public static final String DUE_DATE            =  "due_date";

        public static final String OFFICEHOUR_TABLE    =  "officehour_table";
        public static final String TIME_NAME           =  "_id";
        public static final String OFF_CW_ID           =  "coursework";
        public static final String OFF_COR_ID          =  "course";
        public static final String OFF_STU_ID          =  "student_id";
        public static final String START_HOUR          =  "start_hour";
        public static final String END_HOUR            =  "end_hour";
        public static final String START_MINUTE        =  "start_minute";
        public static final String END_MINUTE          =  "end_minute";
        public static final String MONTH               =  "month";
        public static final String DAY                 =  "day";


    }

}
