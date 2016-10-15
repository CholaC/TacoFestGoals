package com.tacofest.goals;

/**
 * Created by ugochiokehi on 2016-10-15.
 */
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
public class Student  extends AppCompatActivity {


    TextView userName;
    TextView password;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_profile);

        userName = (TextView) findViewById(R.id.userName);
        password = (TextView) findViewById(R.id.password);
    }
}
