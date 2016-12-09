package com.tacofest.goals;

import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.content.Intent;
import android.view.View;
import android.widget.TimePicker;

import java.util.Calendar;

//import static android.provider.AlarmClock.EXTRA_MESSAGE;

/**
 * Created by okorisanipe on 2016-10-15.
 */

public class CourseInfo extends AppCompatActivity {
    Calendar cal = Calendar.getInstance();
    RelativeLayout lectureLayout;
    TextView courseInfo;
    EditText courseName, desiredGrade, passingGrade, lectureDate;
    String  coursename, studentid, lecturedate;
    int desiredgrade, passinggrade, weekDay, startHr, startMin, endHr, endMin;

    Button proceed;
    Button finish;
    Button startTime, endTime;
    Context context = this;
    Context context1= this;
    final static Course course = new Course();;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_info);

        courseInfo = (TextView) findViewById(R.id.txtCourseInfo);
        courseName = (EditText) findViewById(R.id.courseName);
        desiredGrade = (EditText) findViewById(R.id.tDesiredGrade);
        passingGrade = (EditText) findViewById(R.id.tPassingGrade);

        finish = (Button) findViewById(R.id.btnFinish);
        proceed = (Button) findViewById(R.id.btnProceed);

        Intent intent = getIntent();
        final String getId = (String)intent.getStringExtra("studentId");
        final Student student = (Student)intent.getSerializableExtra("studentObj");
        courseInfo.setText(student.getId());

        setListenerHelper(R.id.lStartTime);
        setListenerHelper2(R.id.lEndTime);
//        Bundle bd = intent.getExtras();
//        if(bd != null)
//        {
//            final String getId = (String) bd.get("studentId");
//            final Student student = (Student)bd.get("studentObj");
//            courseInfo.setText(getId);
//        }


        finish.setOnClickListener( new View.OnClickListener() {
            public void onClick(View v) {

                //cast int here
                desiredgrade = Integer.parseInt(desiredGrade.getText().toString());
                passinggrade = Integer.parseInt(passingGrade.getText().toString());

                coursename = courseName.getText().toString();
                studentid = courseInfo.getText().toString();

                lectureLayout = (RelativeLayout)findViewById(R.id.lectureLayout);
                weekDay = getDaySpinnerInfo(lectureLayout);

                add2DB(coursename, weekDay, startHr, startMin, endHr, endMin);

                //(String ID, float des, float pass, Student s)
                final Course course = new Course(coursename,desiredgrade,passinggrade,student);
                DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
                databaseHelper.addCourse(coursename,studentid,passinggrade,desiredgrade,lecturedate);
                databaseHelper.close();

                AlertDialog.Builder builder = new AlertDialog.Builder(context1);
                builder.setMessage(coursename).setTitle("You have add to your course list:").setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int z) {

                        Intent i = new Intent(CourseInfo.this, StudentProfile.class);
                        i.putExtra("studentObj",student);
                        i.putExtra("courseObj",course);
                        i.putExtra("courseName",coursename);
                        i.putExtra("return",1);
                        startActivity(i);
                    }
                });
                AlertDialog dailog = builder.create();
                dailog.show();
            }
        });

        proceed.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                //cast int here
                desiredgrade = Integer.parseInt(desiredGrade.getText().toString());
                passinggrade = Integer.parseInt(passingGrade.getText().toString());

                coursename = courseName.getText().toString();
                studentid = courseInfo.getText().toString();

                Course course = new Course(coursename,desiredgrade,passinggrade,student);
                DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
                databaseHelper.addCourse(coursename,studentid,passinggrade,desiredgrade,lecturedate);
                databaseHelper.close();



                Intent i = new Intent(CourseInfo.this, EnterAssign.class);
                i.putExtra("studentObj",student);
                i.putExtra("courseObj",course);
                i.putExtra("courseName", coursename);
                i.putExtra("studentId", studentid);
                startActivity(i);
            }
        });
    }

    private void setListenerHelper(int ID) {
        startTime = (Button)findViewById(R.id.lStartTime);
        startTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new TimePickerDialog(CourseInfo.this, onTimeSetListener, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), true).show();
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
        endTime = (Button)findViewById(R.id.lEndTime);
        endTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new TimePickerDialog(CourseInfo.this, onTimeSetListener2, startHr, startMin, true).show();
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
        currDate.set(2017, 0, 1);
        endDate.set(2017, 4, 1);
        int startWeekDay = currDate.get(Calendar.DAY_OF_WEEK);

        //get first date
        if (startWeekDay > day)
            currDate.add(Calendar.DATE, 7%day);
        else if (startWeekDay < day)
            currDate.add(Calendar.DATE, day - startWeekDay);

        //add to the database
        while (currDate.getTimeInMillis() < endDate.getTimeInMillis()) {
            DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
            System.out.println("Adding...Name: " + coursename + ", Weekday: " + weekDay + ", Month: " + currDate.get(Calendar.MONTH) + ", Day: " + currDate.get(Calendar.DATE));
            databaseHelper.addOtherTime(coursename, studentid, coursename, null, currDate.get(Calendar.MONTH),
                    currDate.get(Calendar.DATE), startHour, endHour, startMin, endMin);
            databaseHelper.close();

            currDate.add(Calendar.DATE, 7);
        }
    }
}


