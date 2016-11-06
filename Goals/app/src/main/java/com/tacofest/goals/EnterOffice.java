package com.tacofest.goals;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;

/**
 * Created by okorisanipe on 2016-11-02.
 */

public class EnterOffice extends AppCompatActivity {
    Spinner spinner;
    LinearLayout infoList;
    FloatingActionButton add;
    RelativeLayout lastRow;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_office);
        spinner = (Spinner)findViewById(R.id.daySpin);
        infoList = (LinearLayout) findViewById(R.id.officeInfoList);
        add = (FloatingActionButton) findViewById(R.id.addOffice);
        lastRow = (RelativeLayout)findViewById(R.id.buttons);

        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.office_info_layout, null);
        infoList.addView(rowView, infoList.getChildCount() - 1);
        lastRow.requestFocus();
    }

    protected void addOfficeLayout(View v) {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.office_info_layout, null);
        infoList.addView(rowView, infoList.getChildCount() - 1);
    }
}
