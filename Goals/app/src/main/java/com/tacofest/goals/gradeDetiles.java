package com.tacofest.goals;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class gradeDetiles extends AppCompatActivity {

    FloatingActionButton btneditGrades;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grade_detiles);

        btneditGrades = (FloatingActionButton) findViewById(R.id.fbtnEditGrades);

        btneditGrades.setOnClickListener( new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(gradeDetiles.this, editGrades.class);
                startActivity(i);
            }
        });


    }

}
