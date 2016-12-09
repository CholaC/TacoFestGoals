package com.tacofest.goals;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.database.Cursor;
import android.widget.SimpleCursorAdapter;
public class StudentProfile extends AppCompatActivity {
    FloatingActionButton addCourse;
    TextView txtCourse;
    ListView courseList;
    Button test;
    String[] values = {" "};
    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_profile);

        final TextView txtUsername = (TextView) findViewById(R.id.txtUsername);
        final TextView txtStudentID = (TextView) findViewById(R.id.txtStudentID);

        Intent i = getIntent();
        id = i.getStringExtra("studentId");
        String username = i.getStringExtra("username");
        String newCourse = i.getStringExtra("courseName");
        int login = i.getIntExtra("login",-1);
        int returnFrom = i.getIntExtra("return",-1);
        final Student student = (Student)i.getSerializableExtra("studentObj");

        txtUsername.setText(username);
        txtStudentID.setText(student.getId());

        addCourse = (FloatingActionButton) findViewById(R.id.floatingActionButton);
        txtCourse = (TextView) findViewById(R.id.txtCourse);
        courseList = (ListView) findViewById(R.id.courseList);
        //test = (Button) findViewById(R.id.btnTest);

        if(login == 1 || returnFrom == 1) {
            populate(id, courseList,student);
        }
        else {
            final CourseListArrayAdapter adapter = new CourseListArrayAdapter(this,values);
            courseList.setAdapter(adapter);
            courseList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String newid = txtStudentID.getText().toString();
                    Intent i = new Intent(StudentProfile.this,CourseProfile.class);
                    i.putExtra("studentObj",student);
                    i.putExtra("courseName",values[position]);
                    i.putExtra("studentId", newid);
                    startActivity(i);
                }
            });
        }

        addCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(StudentProfile.this,CourseInfo.class);
                i.putExtra("studentObj",student);
                i.putExtra("studentId", id);
                startActivity(i);
            }
        });
    }
    protected void viewSchedOnClick (View v) {
        Intent i = new Intent(StudentProfile.this, BasicActivity.class);
      //  i.putExtra("studentObj",student);
        i.putExtra("studentId",id);
        startActivity(i);
    }

    public void populate(final String stuId, ListView list, final Student student) {
        Cursor cursor;
        cursor = DatabaseHelper.getInstance(getApplicationContext()).getStudentCourseList(student.getId());
        String[] fromFieldName = new String[]{TableData.TableInfo.COURSE_NAME};
        int[] toViewID = new int[]{R.id.txtCourseName};
        final SimpleCursorAdapter cursorAdapter = new SimpleCursorAdapter(getBaseContext(), R.layout.course_list_item, cursor, fromFieldName, toViewID);

        list.setAdapter(cursorAdapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //cursor.getString(cursor.getColumnIndex(TableData.TableInfo.COURSEWORK_NAME));
                String cname = cursorAdapter.getCursor().getString(cursorAdapter.getCursor().getColumnIndex(TableData.TableInfo.COURSE_NAME));
                int tdes = cursorAdapter.getCursor().getInt(cursorAdapter.getCursor().getColumnIndex(TableData.TableInfo.TOTAL_DESIRED_GRADE));
                int tpass = cursorAdapter.getCursor().getInt(cursorAdapter.getCursor().getColumnIndex(TableData.TableInfo.TOTAL_PASSING_GRADE));

                Course course = new Course(cname,tdes, tpass, student);

                Intent i = new Intent(StudentProfile.this, CourseProfile.class);
                i.putExtra("courseObj",course);
                i.putExtra("studentObj",student);
                i.putExtra("courseName", cname);
                i.putExtra("studentId",stuId);
                startActivity(i);
            }
        });

    }


}
