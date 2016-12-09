package com.tacofest.goals;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.util.Log;
import android.view.View;import android.view.LayoutInflater;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Button;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.content.Context;
import android.widget.RelativeLayout;
import android.widget.LinearLayout;
import android.text.TextWatcher;


public class gradeDetiles extends AppCompatActivity {
    FloatingActionButton btneditGrades;
    Context context = this;
    TextView courseWorkName, desiredGrade, passingGrade;
    TextView tPassGrade, tDesiredGrade, random;
    EditText actualGrade;
    LinearLayout infoList;
    ListView courseWorkList;
    Course course;
    Button btnsave;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grade_detiles);

        btneditGrades = (FloatingActionButton) findViewById(R.id.fbtnEditGrades);
        btnsave = (Button) findViewById(R.id.btnsave);
        courseWorkList = (ListView) findViewById(R.id.courseWorkList);
        courseWorkName = (TextView) findViewById(R.id.txtCourseName);
        actualGrade = (EditText) findViewById(R.id.actualGrade);
        desiredGrade = (TextView) findViewById(R.id.txtDesiredGrade);
        passingGrade = (TextView) findViewById(R.id.txtPassingGrade);

        tDesiredGrade = (TextView) findViewById(R.id.txtTdesiredGrade);
        tPassGrade = (TextView) findViewById(R.id.txtTpassGrade);
        //random = (TextView) findViewById(R.id.textView3);
        Intent i = getIntent();
        final String studentid = i.getStringExtra("studentId");
        final String courseId = i.getStringExtra("courseName");
        final String courseWorkName = i.getStringExtra("courseWorkName");
        final float actualGrade = i.getIntExtra("actualGrade",-1);
        final Course course = (Course)i.getSerializableExtra("courseObj");
        final Student student = (Student)i.getSerializableExtra("studentObj");
        final CourseWork courseWork = (CourseWork)i.getSerializableExtra("courseWork");
        int enterActual = i.getIntExtra("enterActual",-1);
        btnsave.setText(course.getId());

        if(enterActual == 1){
            populateAgain(student,course,courseWork,courseWorkList);
        }
        else {
            populate(student, course, courseWorkList);
        }

        btneditGrades.setOnClickListener( new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(gradeDetiles.this, EnterAssign.class);
                i.putExtra("courseName", courseId);
                i.putExtra("studentId", studentid);
                i.putExtra("studentObj",student);
                i.putExtra("courseObj",course);
                startActivity(i);
            }
        });
        btnsave.setOnClickListener( new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(gradeDetiles.this, CourseProfile.class);
                i.putExtra("courseName", courseId);
                i.putExtra("studentId", studentid);
                i.putExtra("courseObj",course);
                i.putExtra("studentObj",student);
                i.putExtra("courseName", course.getId());
                i.putExtra("studentId", student.getId());
                startActivity(i);
            }
        });
    }
    public void populate(final Student stu, final Course course, ListView courseList) {
        Cursor cursor;
        cursor = DatabaseHelper.getInstance(getApplicationContext()).getCourseWorkList(course.getId(),stu.getId());
        String[] fromFieldName = new String[]{TableData.TableInfo.COURSEWORK_NAME,
                TableData.TableInfo.ACTUAL_GRADE,
                TableData.TableInfo.DESIRED_GRADE, TableData.TableInfo.PASSING_GRADE};
        int[] toViewID = new int[]{R.id.txtCourseWorkName,R.id.actualGrade,R.id.txtDesiredGrade,R.id.txtPassingGrade};
       final SimpleCursorAdapter cursorAdapter = new SimpleCursorAdapter(getBaseContext(), R.layout.activity_edit_grades, cursor, fromFieldName, toViewID,0);
        courseList.setAdapter(cursorAdapter);
        courseWorkList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(gradeDetiles.this,EnterActual.class);
                String cwname = cursorAdapter.getCursor().getString(cursorAdapter.getCursor().getColumnIndex(TableData.TableInfo.COURSEWORK_NAME));
                int pGrade = cursorAdapter.getCursor().getInt(cursorAdapter.getCursor().getColumnIndex(TableData.TableInfo.PASSING_GRADE));
                int dGrade = cursorAdapter.getCursor().getInt(cursorAdapter.getCursor().getColumnIndex(TableData.TableInfo.DESIRED_GRADE));
                int weight = cursorAdapter.getCursor().getInt(cursorAdapter.getCursor().getColumnIndex(TableData.TableInfo.WEIGHT));
                String dd = cursorAdapter.getCursor().getString(cursorAdapter.getCursor().getColumnIndex(TableData.TableInfo.DUE_DATE));

                Log.e("populate","coursework name"+cwname);
                Log.e("populate","coursework p "+pGrade);
                Log.e("populate","coursework d "+dGrade);
                Log.e("populate","coursework w "+weight);
                Log.e("populate","coursework dat"+dd);
                Log.e("populate","coursework cor "+course.getId());
                Log.e("populate","coursework stu "+stu.getId());

                CourseWork courseWork = new CourseWork(course,stu,cwname,(float) pGrade,(float) dGrade,(float) weight,dd);

                Log.e("populate","coursework obj"+courseWork.getCourseWorkName());
               // Log.e("populate","coursework name"+cwname);

                i.putExtra("courseObj",course);
                i.putExtra("studentObj",stu);
                i.putExtra("courseWorkObj",courseWork);
                i.putExtra("courseName", course.getId());
                i.putExtra("studentId", stu.getId());
                i.putExtra("cwName",cwname);
                startActivity(i);
            }
        });

    }
    public void populateAgain(final Student stu, final Course course, final CourseWork courseWork, ListView courseList) {
        Cursor cur;
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(getApplicationContext());
        cur = databaseHelper.getCourseWorkList(course.getId(),stu.getId());
        while (cur.moveToNext()) {
            //public void addPassingGrade(String courseWorkName, String courseName, String studentid, int passingGrade){
            final String cwname = cur.getString(cur.getColumnIndex(TableData.TableInfo.COURSEWORK_NAME));
            int pGrade = cur.getInt(cur.getColumnIndex(TableData.TableInfo.PASSING_GRADE));
            int dGrade = cur.getInt(cur.getColumnIndex(TableData.TableInfo.DESIRED_GRADE));
            int weight = cur.getInt(cur.getColumnIndex(TableData.TableInfo.WEIGHT));
            String dd = cur.getString(cur.getColumnIndex(TableData.TableInfo.DUE_DATE));

            final CourseWork cw= new CourseWork(course,stu,cwname,pGrade,dGrade,weight,dd);
            //course.setAvgGrade(cw);

            databaseHelper.addDesiredGrade(cur.getString(cur.getColumnIndex(TableData.TableInfo.COURSEWORK_NAME)),
                    course.getId(),stu.getId(),(int)course.getReqDesGrade());
            databaseHelper.addPassingGrade(cur.getString(cur.getColumnIndex(TableData.TableInfo.COURSEWORK_NAME)),
                    course.getId(),stu.getId(),(int)course.getReq2pass());
        }

        Cursor cursor;
        cursor = DatabaseHelper.getInstance(getApplicationContext()).getCourseWorkList(course.getId(),stu.getId());
        String[] fromFieldName = new String[]{TableData.TableInfo.COURSEWORK_NAME,
                TableData.TableInfo.ACTUAL_GRADE,
                TableData.TableInfo.DESIRED_GRADE, TableData.TableInfo.PASSING_GRADE};
        int[] toViewID = new int[]{R.id.txtCourseWorkName,R.id.actualGrade,R.id.txtDesiredGrade,R.id.txtPassingGrade};
        final SimpleCursorAdapter cursorAdapter = new SimpleCursorAdapter(getBaseContext(), R.layout.activity_edit_grades, cursor, fromFieldName, toViewID,0);
        courseList.setAdapter(cursorAdapter);
        courseWorkList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(gradeDetiles.this,EnterActual.class);
                final String cwname = cursorAdapter.getCursor().getString(cursorAdapter.getCursor().getColumnIndex(TableData.TableInfo.COURSEWORK_NAME));
                int aGrade = cursorAdapter.getCursor().getInt(cursorAdapter.getCursor().getColumnIndex(TableData.TableInfo.ACTUAL_GRADE));
                int pGrade = cursorAdapter.getCursor().getInt(cursorAdapter.getCursor().getColumnIndex(TableData.TableInfo.PASSING_GRADE));
                int dGrade = cursorAdapter.getCursor().getInt(cursorAdapter.getCursor().getColumnIndex(TableData.TableInfo.DESIRED_GRADE));
                int weight = cursorAdapter.getCursor().getInt(cursorAdapter.getCursor().getColumnIndex(TableData.TableInfo.WEIGHT));
                String dd = cursorAdapter.getCursor().getString(cursorAdapter.getCursor().getColumnIndex(TableData.TableInfo.DUE_DATE));

                final CourseWork courseWork = new CourseWork(course,stu,cwname,pGrade,dGrade,weight,dd);
                i.putExtra("courseObj",course);
                i.putExtra("studentObj",stu);
                i.putExtra("courseWorkObj",courseWork);
                i.putExtra("courseName", course.getId());
                i.putExtra("studentId", stu.getId());
                i.putExtra("cwName",cwname);
                startActivity(i);
            }
        });
    }
}
