package com.tacofest.goals;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.content.Intent;
import android.view.View;

//import static android.provider.AlarmClock.EXTRA_MESSAGE;

/**
 * Created by okorisanipe on 2016-10-15.
 */

public class CourseInfo extends AppCompatActivity {
    TextView courseInfo;
    EditText courseName, desiredGrade, passingGrade, lectureDate;
    String coursename, studentid, lecturedate;
    int desiredgrade, passinggrade;
    Button uploadPdf;
    Button proceed;
    Button finish;
    Context context = this;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_info);

        courseInfo = (TextView) findViewById(R.id.txtCourseInfo);
        courseName = (EditText) findViewById(R.id.courseName);
        desiredGrade = (EditText) findViewById(R.id.tDesiredGrade);
        passingGrade = (EditText) findViewById(R.id.tPassingGrade);
        lectureDate = (EditText) findViewById(R.id.txtlecdate);
        uploadPdf = (Button) findViewById(R.id.btnPDF);
        finish = (Button) findViewById(R.id.btnFinish);
        proceed = (Button) findViewById(R.id.btnProceed);


        Intent intent = getIntent();
        Bundle bd = intent.getExtras();
        if(bd != null)
        {
            String getId = (String) bd.get("studentId");
            courseInfo.setText(getId);
        }
        coursename = courseName.getText().toString();
        studentid = courseInfo.getText().toString();

        finish.setOnClickListener( new View.OnClickListener() {
            public void onClick(View v) {
                //cast int here
                desiredgrade = Integer.parseInt(desiredGrade.getText().toString());
                passinggrade = Integer.parseInt(passingGrade.getText().toString());

                lecturedate = lectureDate.getText().toString();
                DatabaseHelper databaseHelper = new DatabaseHelper(context);
                databaseHelper.addCourse(databaseHelper,coursename,studentid,passinggrade,desiredgrade,lecturedate);
                databaseHelper.close();

                Intent i = new Intent(CourseInfo.this, StudentProfile.class);
                i.putExtra("courseName",coursename);
                startActivity(i);
            }
        });

        proceed.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                //cast int here
                desiredgrade = Integer.parseInt(desiredGrade.getText().toString());
                passinggrade = Integer.parseInt(passingGrade.getText().toString());

                DatabaseHelper databaseHelper = new DatabaseHelper(context);
                databaseHelper.addCourse(databaseHelper,coursename,studentid,passinggrade,desiredgrade,lecturedate);
                databaseHelper.close();
                Intent i = new Intent(CourseInfo.this, EnterAssign.class);
                i.putExtra("courseName", coursename);
                i.putExtra("studentId", studentid);
                startActivity(i);
            }
        });
    }
}


