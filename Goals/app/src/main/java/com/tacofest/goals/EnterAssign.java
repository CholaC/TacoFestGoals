package com.tacofest.goals;

import android.app.ActionBar;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.ActivityCompat;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.view.View;
import android.widget.TextView;
import android.content.Intent;
import android.widget.LinearLayout;
import java.lang.Integer;

/**
 * Created by okorisanipe on 2016-10-24.
 */

public class EnterAssign extends AppCompatActivity {
    LinearLayout infoList;
    FloatingActionButton add;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_assign);
        infoList = (LinearLayout) findViewById(R.id.enterAInfoList);
        add = (FloatingActionButton) findViewById(R.id.addALayoutButton);
    }

    protected void addAInfoLayout(View v) {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.assign_info_layout, null);
        infoList.addView(rowView, infoList.getChildCount() - 1);
    }

    protected void contToOffice(View v) {
        Intent i = new Intent(this, EnterAssign.class);
        startActivity(i);
    }
}

