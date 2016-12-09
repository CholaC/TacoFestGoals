package com.tacofest.goals;

import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TimePicker;

import java.util.Calendar;

/**
 * Created by okorisanipe on 2016-11-02.
 */

public class EnterOffice extends AppCompatActivity {
    Calendar cal = Calendar.getInstance();
    Spinner spinner;
    String studentId, course, name;
    int startHr, startMin, endHr, endMin, weekDay;
    Course course2;
    Student student;
    LinearLayout infoList;
    FloatingActionButton add;
    Button startTime, endTime;
    Context context = this;
    Context context1 = this;
    int count = 0;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_office);
        spinner = (Spinner)findViewById(R.id.daySpin);
        infoList = (LinearLayout) findViewById(R.id.officeInfoList);
        add = (FloatingActionButton) findViewById(R.id.addOffice);

        Intent i = getIntent();
        studentId = i.getStringExtra("studentId");
        course = i.getStringExtra("courseName");
        student = (Student) i.getSerializableExtra("studentObj");
        course2 = (Course) i.getSerializableExtra("courseObj");

        addOfficeView();
        setListenerHelper(R.id.startTimeBtn);
        setListenerHelper2(R.id.endTimeBtn);
        count++;
    }

    protected void addOfficeLayout(View v) {
        RelativeLayout oHrInfo = (RelativeLayout) infoList.getChildAt(count-1);
        addOfficeView();
        setListenerHelper(R.id.startTimeBtn);
        setListenerHelper2(R.id.endTimeBtn);
        weekDay = getDaySpinnerInfo(oHrInfo);
        name = ((EditText)getView(oHrInfo)).getText().toString();
        add2DB(name, weekDay, startHr, startMin, endHr, endMin);
        count++;
    }

    //clickListener to go back to student profile
    protected void return2StudentProfile(View v) {
        RelativeLayout oHrInfo = (RelativeLayout) infoList.getChildAt(count-1);
        weekDay = getDaySpinnerInfo(oHrInfo);
        name = ((EditText)getView(oHrInfo)).getText().toString();
        add2DB(name, weekDay, startHr, startMin, endHr, endMin);

        AlertDialog.Builder builder = new AlertDialog.Builder(context1);
        builder.setMessage(course).setTitle("You have add to your course list:").setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int z) {

                Intent i = new Intent(EnterOffice.this, StudentProfile.class);
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

    private void addOfficeView() {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.office_info_layout, null);
        infoList.addView(rowView, infoList.getChildCount() - 1);
    }

    private void setListenerHelper(int ID) {
        RelativeLayout oHrInfo = (RelativeLayout) infoList.getChildAt(count);
        startTime = (Button) getView(oHrInfo, ID);
        startTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new TimePickerDialog(EnterOffice.this, onTimeSetListener, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), true).show();
            }
        });
    }

    TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hour, int minute) {
            startTime.setText(String.format("%d:%02d", hour, minute));
            startHr = hour;
            startMin = minute;
        }
    };

    private void setListenerHelper2(int ID) {
        RelativeLayout oHrInfo = (RelativeLayout) infoList.getChildAt(count);
        endTime = (Button) getView(oHrInfo, ID);
        endTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new TimePickerDialog(EnterOffice.this, onTimeSetListener2, startHr, startMin, true).show();
            }
        });
    }

    TimePickerDialog.OnTimeSetListener onTimeSetListener2 = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hour, int minute) {
            endTime.setText(String.format("%d:%02d", hour, minute));
            endHr = hour;
            endMin = minute;
        }
    };

    private View getView(RelativeLayout r, int ID) {
        int size = r.getChildCount();
        System.out.println("Number of children: " + size);
        System.out.println("At count: " + count);

        for (int i = 0; i < size; i++) {
            if (r.getChildAt(i).getId() == ID) {
                System.out.println("Here!");
                if(r.getChildAt(i) instanceof EditText)
                    System.out.println("Hey "+((EditText)r.getChildAt(i)).getText().toString());
                return r.getChildAt(i);
            }
        }
        return null;
    }

    private View getView(RelativeLayout r) {
        int size = r.getChildCount();
        System.out.println("Number of children: " + size);
        System.out.println("At count: " + count);

        for (int i = 0; i < size; i++) {
            if (r.getChildAt(i) instanceof EditText) {
                System.out.println("Here!");
                return r.getChildAt(i);
            }
        }
        return null;
    }

    //get the day selected from the spinner
    private int getDaySpinnerInfo(RelativeLayout r) {
        int size = r.getChildCount();
        Spinner spin = null;
        int day = 0;
        for (int i = 0; i < size; i++) {
            if (r.getChildAt(i) instanceof Spinner) {
                spin = (Spinner) r.getChildAt(i);
                break;
            }
        }
        if (spin == null)
            return day;
        String dayText = spin.getSelectedItem().toString();

        switch (dayText) {
            case "Sunday": day = 1;
                break;
            case "Monday": day = 2;
                break;
            case "Tuesday": day = 3;
                break;
            case "Wednesday": day = 4;
                break;
            case "Thursday": day = 5;
                break;
            case "Friday": day = 6;
                break;
            case "Saturday": day = 7;
                break;
            default: day = 0;
                break;
        }
        return day;
    }

    private void add2DB(String title, int day, int startHour, int startMin, int endHour, int endMin) {
        Calendar currDate = Calendar.getInstance();
        Calendar endDate = Calendar.getInstance();
        endDate.set(2017, 1, 1);
        int startWeekDay = currDate.get(Calendar.DAY_OF_WEEK);

        //get first date
        if (startWeekDay > day)
            currDate.add(Calendar.DATE, 7%day);
        else if (startWeekDay < day)
            currDate.add(Calendar.DATE, day - startWeekDay);

        //add to the database
        while (currDate.getTimeInMillis() < endDate.getTimeInMillis()) {
            DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
            System.out.println("Adding...Name: " + name + ", Weekday: " + weekDay + ", Month: " + currDate.get(Calendar.MONTH) + ", Day: " + currDate.get(Calendar.DATE));
            databaseHelper.addOtherTime(title, studentId, course, null, currDate.get(Calendar.MONTH),
                    currDate.get(Calendar.DATE), startHour, endHour, startMin, endMin);
            currDate.add(Calendar.DATE, 7);
        }
    }


}
