package com.tacofest.goals;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.view.View;
import android.content.Intent;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.Calendar;

/**
 * * Created by okorisanipe on 2016-10-24.
 */
public class EnterAssign extends AppCompatActivity {
    Calendar cal = Calendar.getInstance();
    LinearLayout infoList;
    FloatingActionButton add;
    TextView dateTv;
    Button date;
    String name, ddate, studentId, course;
    int weight;
    int count = 0;
    Course course2;
    Student student;
    Context context = this;
    Context context1 = this;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_assign);
        infoList = (LinearLayout) findViewById(R.id.enterAInfoList);
        add = (FloatingActionButton) findViewById(R.id.addALayoutButton);

        Intent i = getIntent();
        studentId = i.getStringExtra("studentId");
        course = i.getStringExtra("courseName");
        student = (Student) i.getSerializableExtra("studentObj");
        course2 = (Course) i.getSerializableExtra("courseObj");

        addCourseWorkView();
        setListenerHelper();
    }

    private void setListenerHelper() {
        RelativeLayout cworkInfo = (RelativeLayout) infoList.getChildAt(count);
        date = (Button)getDateBtn(cworkInfo);
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(EnterAssign.this, onDateSetListener, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE)).show();
            }
        });
    }

    DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int date) {
            RelativeLayout cworkInfo = (RelativeLayout) infoList.getChildAt(count);
            dateTv = (TextView)getDateText(cworkInfo);
            dateTv.setText((month+1) + "/" + date + "/" + year);
            ddate = month + "/" + date + "/" + year;
            count++;
        }
    };

    private View getDateBtn(RelativeLayout r) {
        //System.out.println("retrieving button at current count: "+count);
        int size = r.getChildCount();
        for (int i = 0; i < size; i++) {
            if (r.getChildAt(i) instanceof Button) {
                System.out.println(((Button)r.getChildAt(i)).getText().toString());
                return r.getChildAt(i);
            }
        }
        return null;
    }

    private View getDateText(RelativeLayout r) {
        System.out.println("retrieving text at current count: "+count);
        int size = r.getChildCount();
        for (int i = 0; i < size; i++) {
            if (r.getChildAt(i) instanceof TextView) {
                System.out.println(((TextView) r.getChildAt(i)).getText().toString());
                if (((TextView) r.getChildAt(i)).getText().toString().equals("MM/DD/YYYY")) {
                    System.out.println("Sup dawg");
                    return r.getChildAt(i);
                }
            }
        }
        return null;
    }

    protected void addAInfoLayout(View v) {
        addCourseWorkView();
        System.out.println("calling listenerHelper at count: " + count);
        setListenerHelper();

        RelativeLayout cworkInfo = (RelativeLayout) infoList.getChildAt(count-1);
        setVars(cworkInfo);
        add2DB(course2,course,studentId,name,ddate,weight);
        //count++;
    }

    //start office hour activity
    protected void contToOffice(View v) {
        RelativeLayout cworkInfo = (RelativeLayout) infoList.getChildAt(count-1);
        setVars(cworkInfo);
        add2DB(course2,course,studentId,name,ddate,weight);
        Intent i = new Intent(this, EnterOffice.class);
        i.putExtra("studentObj",student);
        i.putExtra("courseObj",course2);
        i.putExtra("studentId",studentId);
        i.putExtra("courseName",course);
        i.putExtra("return",1);
        startActivity(i);
    }

    protected void return2StudentProfile (View v) {
        RelativeLayout cworkInfo = (RelativeLayout) infoList.getChildAt(count-1);
        setVars(cworkInfo);
        add2DB(course2,course,studentId,name,ddate,weight);

        AlertDialog.Builder builder = new AlertDialog.Builder(context1);
        builder.setMessage(course).setTitle("You have add to your course list:").setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int z) {

                Intent i = new Intent(EnterAssign.this, StudentProfile.class);
                i.putExtra("studentObj",student);
                i.putExtra("courseObj",course2);
                i.putExtra("studentId",studentId);
                i.putExtra("courseName",course);
                i.putExtra("return",1);
                startActivity(i);
            }
        });
        AlertDialog dailog = builder.create();
        dailog.show();

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
        name = arr[0];
        //ddate = arr[0];
        weight = Integer.parseInt(arr[1]);
    }

    private void add2DB(Course cor,String course, String id, String item, String date, int weight) {
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
        databaseHelper.addCourseWork(course, id, item, date, weight);
        databaseHelper.addDesiredGrade(item, course, id, (int) cor.getTotDesGrade());
        databaseHelper.addPassingGrade(item, course, id, (int) cor.getTotPassGrade());
        System.out.println("Name: " + item);
        System.out.println("Weight: " + weight);
        databaseHelper.close();
    }

    private void addCourseWorkView() {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.assign_info_layout, null);
        infoList.addView(rowView, infoList.getChildCount() - 1);
    }
}
