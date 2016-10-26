package com.tacofest.goals;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
    EditText courseName;
    EditText numAss;
    EditText numTest;
    TextView title;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_info);

        courseName = (EditText) findViewById(R.id.courseName);
        numAss = (EditText) findViewById(R.id.numAss);
        numTest = (EditText) findViewById(R.id.numTest);
        title = (TextView) findViewById(R.id.title);
    }

    protected void openAssignPage(View v) {
        Intent i = new Intent(this, EnterAssign.class);
        String num = numAss.getText().toString();
        i.putExtra("num", num);
        startActivity(i);
    }

}
