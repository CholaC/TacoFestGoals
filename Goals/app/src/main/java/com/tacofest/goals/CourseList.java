package com.tacofest.goals;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

public class CourseList extends AppCompatActivity {
    FloatingActionButton addCourse;
    TextView txtCourse;
    ListView courseList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_list);

        addCourse = (FloatingActionButton) findViewById(R.id.floatingActionButton);
        txtCourse = (TextView) findViewById(R.id.txtCourse);
        courseList = (ListView) findViewById(R.id.courseList);
    }


}
