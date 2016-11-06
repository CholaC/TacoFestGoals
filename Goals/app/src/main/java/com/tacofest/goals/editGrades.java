package com.tacofest.goals;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class editGrades extends AppCompatActivity {

    Button btnsaveGrade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_grades);


        btnsaveGrade = (Button) findViewById(R.id.btnSave);

        btnsaveGrade.setOnClickListener( new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(editGrades.this, gradeDetiles.class);
                startActivity(i);
            }
        });

    }

}
