package com.tacofest.goals;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.ActivityCompat;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.view.View;
import android.widget.TextView;
import android.content.Intent;
import android.widget.LinearLayout;
import java.lang.Integer;

import static com.tacofest.goals.R.layout.activity_course_info;
//import static android.provider.AlarmClock.EXTRA_MESSAGE;

/**
 * Created by okorisanipe on 2016-10-24.
 */

public class EnterAssign extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_assign);

        /*RelativeLayout r = new RelativeLayout(this);
        EditText nameBox = new EditText(this);
        EditText dateBox = new EditText(this);
        EditText weightBox = new EditText(this);

        RelativeLayout.LayoutParams layoutDim = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                                                                                RelativeLayout.LayoutParams.MATCH_PARENT);
        r.setLayoutParams(layoutDim);*/


        LinearLayout lin = (LinearLayout)findViewById(R.id.lin);
        Intent intent  = getIntent();
        String num = "";
        Bundle bd = intent.getExtras();
        if(bd != null) {
            num = (String) bd.get("num");
        }

        for (int i = 0; i < Integer.parseInt(num); i++) {
            //getLayoutInflater().inflate(R.layout.assign_info_layout, lin);
            RelativeLayout aInfo = (RelativeLayout)findViewById(R.id.aInfo);
            aInfo.setId(i);
            lin.addView(aInfo);
        }
    }
}
