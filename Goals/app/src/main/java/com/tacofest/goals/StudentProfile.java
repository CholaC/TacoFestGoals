package com.tacofest.goals;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

public class StudentProfile extends AppCompatActivity {
    FloatingActionButton addCourse;
    TextView txtCourse;
    ListView courseList;
    String[] values = {"COMP5678","COMP1234","COMP2345","COMP3456","COMP4567"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_profile);

        final TextView txtUsername = (TextView) findViewById(R.id.txtUsername);
        final TextView txtStudentID = (TextView) findViewById(R.id.txtStudentID);

        Intent i = getIntent();
        final String studentId = i.getStringExtra("studentId");
        String username = i.getStringExtra("username");
        String newCourse = i.getStringExtra("courseName");


        txtUsername.setText(username);
        txtStudentID.setText(studentId);

        addCourse = (FloatingActionButton) findViewById(R.id.floatingActionButton);
        txtCourse = (TextView) findViewById(R.id.txtCourse);
        courseList = (ListView) findViewById(R.id.courseList);
        final CourseListArrayAdapter adapter = new CourseListArrayAdapter(this,values);
        courseList.setAdapter(adapter);
        courseList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(StudentProfile.this,CourseProfile.class);
                i.putExtra("studentId",studentId);
                i.putExtra("courseName",values[position]);
                startActivity(i);
            }
        });

        addCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(StudentProfile.this,CourseInfo.class);
                i.putExtra("studentId",studentId);
                startActivity(i);
            }
        });
    }


}
