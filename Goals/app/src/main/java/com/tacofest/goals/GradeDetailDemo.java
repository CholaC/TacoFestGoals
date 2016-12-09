package com.tacofest.goals;

/**
 * Created by ugochiokehi on 2016-11-13.
 */
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;import android.view.LayoutInflater;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.content.Context;
import android.widget.RelativeLayout;
import android.widget.LinearLayout;

public class GradeDetailDemo extends AppCompatActivity{
    TextView actualAss1, actualAss2,actualMidterm,actualFinal;
    TextView desiredAss1, desiredAss2,desiredMidterm,desiredFinal;;
    TextView passingAss1, passingAss2,passingMidterm,passingFinal;;
    TextView totalDesired;
    TextView totalPass;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grade_detail_demo);

        actualAss1 = (TextView) findViewById(R.id.txtActAss1);
        actualAss2 = (TextView) findViewById(R.id.txtActAss2);
        actualMidterm = (TextView) findViewById(R.id.txtActMidterm);
        actualFinal = (TextView) findViewById(R.id.txtActFinal);

        desiredAss1 = (TextView) findViewById(R.id.txtDesAss1);
        desiredAss2 = (TextView) findViewById(R.id.txtDesAss1);
        desiredMidterm = (TextView) findViewById(R.id.txtDesMidterm);
        desiredFinal = (TextView) findViewById(R.id.txtDesFinal);



    }
}
