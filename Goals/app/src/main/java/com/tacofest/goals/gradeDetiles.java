package com.tacofest.goals;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class gradeDetiles extends AppCompatActivity {

    FloatingActionButton btneditGrades;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grade_detiles);

        btneditGrades = (FloatingActionButton) findViewById(R.id.fbtnEditGrades);

        Intent i = getIntent();
        final String studentid = i.getStringExtra("studentId");
        final String courseId = i.getStringExtra("courseName");

        btneditGrades.setOnClickListener( new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(gradeDetiles.this, editGrades.class);
                i.putExtra("courseName", courseId);
                i.putExtra("studentId", studentid);

                startActivity(i);
            }
        });


    }

}
