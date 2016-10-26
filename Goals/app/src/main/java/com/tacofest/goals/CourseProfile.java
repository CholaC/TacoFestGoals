package com.tacofest.goals;

/**
 * Created by ugochiokehi on 2016-10-24.
 */

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
public class CourseProfile extends AppCompatActivity {
    Button grade;
    Button progressReport;
    Button schedule;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_profile);

        grade = (Button) findViewById(R.id.btngrade);
        progressReport = (Button) findViewById(R.id.btnprogressreport);
        schedule = (Button) findViewById(R.id.btnschedule);

        grade.setOnClickListener( new View.OnClickListener() {
                                          public void onClick(View v) {
                                              //myDb.insertStudentdata(studentID.getText().toString(),
                                              // studentName.getText().toString());
                                              Intent i = new Intent(CourseProfile.this, gradeDetiles.class);
                                              startActivity(i);
                                          }
                                      }
        );
    }
}
