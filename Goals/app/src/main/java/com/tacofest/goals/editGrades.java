package com.tacofest.goals;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class editGrades extends AppCompatActivity {

    Button btnsaveGrade;
    EditText asGrade1, asGrade2, teGrade1, teGrade2, exGrade;
    int as1Grade, as2Grade, te1Grade, te2Grade,examGrade;
    TextView txtAss1, txtAss2, txtTest1, txtTest2, txtExam;
    String txtass1, txtass2, txttest1, txttest2, txtexam;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_grades);

        btnsaveGrade = (Button) findViewById(R.id.btnSave);
        asGrade1 = (EditText) findViewById(R.id.ass1Grade);
        asGrade2 = (EditText) findViewById(R.id.ass2Grade);
        teGrade1 = (EditText) findViewById(R.id.test1Grade);
        teGrade2 = (EditText) findViewById(R.id.test2Grade);
        exGrade =  (EditText) findViewById(R.id.examGrade);

        txtAss1 =  (TextView) findViewById(R.id.txtAssignment1);
        txtAss2 =  (TextView) findViewById(R.id.txtAssignment2);
        txtTest1 = (TextView) findViewById(R.id.txtTest1);
        txtTest2 = (TextView) findViewById(R.id.txtTest2);
        txtExam =  (TextView) findViewById(R.id.txtExam);


        Intent i = getIntent();
        final String studentid = i.getStringExtra("studentId");
        final String courseId = i.getStringExtra("courseName");

        as1Grade  = Integer.parseInt(asGrade1.getText().toString());
        as2Grade  = Integer.parseInt(asGrade2.getText().toString());
        te1Grade  = Integer.parseInt(teGrade1.getText().toString());
        te2Grade  = Integer.parseInt(teGrade2.getText().toString());
        examGrade = Integer.parseInt(exGrade.getText().toString());

        txtass1 = txtAss1.getText().toString();
        txtass2 = txtAss2.getText().toString();
        txttest1 = txtTest1.getText().toString();
        txttest2 = txtTest2.getText().toString();
        txtexam = txtExam.getText().toString();


        btnsaveGrade.setOnClickListener( new View.OnClickListener() {
            public void onClick(View v) {

                DatabaseHelper databaseHelper = new DatabaseHelper(context);
                databaseHelper.addActualGrade(databaseHelper,txtass1,courseId,studentid,as1Grade);
                databaseHelper.addActualGrade(databaseHelper,txtass2,courseId,studentid,as2Grade);
                databaseHelper.addActualGrade(databaseHelper,txttest1,courseId,studentid,te1Grade);
                databaseHelper.addActualGrade(databaseHelper,txttest2,courseId,studentid,te2Grade);
                databaseHelper.addActualGrade(databaseHelper,txtexam,courseId,studentid,examGrade);
                databaseHelper.close();

                Intent i = new Intent(editGrades.this, gradeDetiles.class);
                i.putExtra("courseName", courseId);
                i.putExtra("studentId", studentid);
                startActivity(i);
            }
        });

    }

}
