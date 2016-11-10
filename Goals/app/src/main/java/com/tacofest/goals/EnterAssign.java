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
    import android.content.Intent;
    import android.widget.LinearLayout;

    /**
     * * Created by okorisanipe on 2016-10-24.
   */
    public class EnterAssign extends AppCompatActivity {
        LinearLayout infoList;
        FloatingActionButton add;
        DatabaseHelper dataHelper;
        String name, ddate, studentId, course;
        int count,weight;
        Context context;
     
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_enter_assign);
            infoList = (LinearLayout) findViewById(R.id.enterAInfoList);
            add = (FloatingActionButton) findViewById(R.id.addALayoutButton);

            Intent i = getIntent();
            studentId = i.getStringExtra("studentId");
            course = i.getStringExtra("courseName");

            LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View rowView = inflater.inflate(R.layout.assign_info_layout, null);
            infoList.addView(rowView, infoList.getChildCount() - 1);
        }
     
        protected void addAInfoLayout(View v) {
            LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View rowView = inflater.inflate(R.layout.assign_info_layout, null);
            infoList.addView(rowView, infoList.getChildCount() - 1);
         
            RelativeLayout cworkInfo = (RelativeLayout) infoList.getChildAt(count);
            setVars(cworkInfo);
         
            DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
            databaseHelper.addCourseWork(course,studentId,name,ddate,weight);
            databaseHelper.close();
            count++;
        }
     
        protected void contToOffice(View v) {
            Intent i = new Intent(this, EnterOffice.class);
            startActivity(i);
        }
     
        private void setVars(RelativeLayout c) {
            int size = c.getChildCount();
            int j = 0;
            String arr[] = new String[size];
            for (int i = 0; i < size; i++) {
                if (c.getChildAt(i) instanceof EditText) {
                    arr[j] = ((EditText) c.getChildAt(i)).getText().toString();
                    j++;
                }
            }
            name = arr[2];
            ddate = arr[0];
            weight = Integer.parseInt(arr[1]);
        }
    }
 