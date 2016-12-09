package com.tacofest.goals;

/**
 * Created by ugochiokehi on 2016-10-24.
 */

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class CourseProfile extends AppCompatActivity {
    Button grade;
    Button progressReport;
    Button schedule;
    TextView courseName, studentName;
    Course course;
    Student student;
    String studentId, courseId;
    Context context1 = this;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_profile);

        grade = (Button) findViewById(R.id.btngrade);
        progressReport = (Button) findViewById(R.id.btnprogressreport);
       // schedule = (Button) findViewById(R.id.btnschedule);
        courseName = (TextView) findViewById(R.id.txtCourseName);
        studentName = (TextView) findViewById(R.id.txtStudentName);

        Intent i = getIntent();
        studentId = i.getStringExtra("studentId");
        courseId = i.getStringExtra("courseName");
        student = (Student)i.getSerializableExtra("studentObj");
        course = (Course)i.getSerializableExtra("courseObj");
        courseName.setText(course.getId());
        studentName.setText(student.getId());


        grade.setOnClickListener( new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(CourseProfile.this, gradeDetiles.class);
                i.putExtra("courseObj",course);
                i.putExtra("studentObj",student);
                i.putExtra("courseName", courseId);
                i.putExtra("studentId", studentId);
                startActivity(i);
            }
        });

        progressReport.setOnClickListener( new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(CourseProfile.this, Graph.class);
//                Log.e("courseProfile progressReport","courseObj "+ course.getId()+"studentObj"+student.getId());
//                Log.e("courseProfile progressReport","course "+courseId+"student"+studentId);
                i.putExtra("courseObj",course);
                i.putExtra("studentObj",student);
                i.putExtra("courseName", courseId);
                i.putExtra("studentId", studentId);
                startActivity(i);
            }
        });
    }

    protected void grade(View v) {
        Intent i = new Intent(CourseProfile.this, gradeDetiles.class);
        i.putExtra("studentObj",student);
        i.putExtra("courseObj",course);
        i.putExtra("studentId",studentId);
        i.putExtra("courseName",courseId);
        startActivity(i);
    }

    protected void offHour(View v) {
        Intent i = new Intent(CourseProfile.this, EnterOffice.class);
        i.putExtra("studentObj",student);
        i.putExtra("courseObj",course);
        i.putExtra("studentId",studentId);
        i.putExtra("courseName",courseId);
        startActivity(i);
    }
    protected void progressReport(View v) {
        Intent i = new Intent(CourseProfile.this, Graph.class);
        i.putExtra("studentObj",student);
        i.putExtra("courseObj",course);
        i.putExtra("studentId",studentId);
        i.putExtra("courseName",courseId);
        startActivity(i);
    }
    protected void delete(View v) {
        DatabaseHelper db = DatabaseHelper.getInstance(getApplicationContext());
        Log.e("courseProfile delete","delete "+course.getId());
        db.removeCourse(course.getId(),student.getId());
        db.removeCourseWorkList(course.getId(),student.getId());
        //remove course work list
        //remove course work list
        AlertDialog.Builder builder = new AlertDialog.Builder(context1);
        builder.setMessage(courseId).setTitle("You have deleted from your course list:").setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int z) {

                Intent i = new Intent(CourseProfile.this, StudentProfile.class);
                i.putExtra("studentObj",student);
                i.putExtra("courseObj",course);
                i.putExtra("studentId",studentId);
                i.putExtra("courseName",courseId);
                i.putExtra("return",1);
                startActivity(i);
            }
        });
        AlertDialog dailog = builder.create();
        dailog.show();

//        AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
//        builder.setMessage(courseId).setTitle("You have deleted from your course list:").setPositiveButton("OK", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int z) {
//
//                Intent i = new Intent(CourseProfile.this, StudentProfile.class);
//                i.putExtra("studentObj",student);
//                i.putExtra("courseObj",course);
//                i.putExtra("studentId",studentId);
//                i.putExtra("courseName",courseId);
//                startActivity(i);
//            }
//        });
//        AlertDialog dailog = builder.create();
//        dailog.show();
    }
}
