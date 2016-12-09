package com.tacofest.goals;

/**
 * Created by ugochiokehi on 2016-11-14.
 */
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
public class EnterActual extends AppCompatActivity{
    EditText agrade,cwTotal;
    TextView courseWorkName;
    Button save;
    Course course;
    Student student;
    CourseWork courseWork;
    String studentId,courseName,cwName;
    Context context1 = this;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_actual);

        agrade = (EditText) findViewById(R.id.agrade);
        cwTotal = (EditText) findViewById(R.id.courseWorkTotal);
        courseWorkName = (TextView) findViewById(R.id.txtcourseWork);
        save = (Button) findViewById(R.id.btnSave);

        Intent i = getIntent();
        studentId = i.getStringExtra("studentId");
        courseName = i.getStringExtra("courseName");
        cwName = i.getStringExtra("cwName");
        course = (Course)i.getSerializableExtra("courseObj");
        student = (Student)i.getSerializableExtra("studentObj");
        courseWork = (CourseWork)i.getSerializableExtra("courseWorkObj");

        Log.e("Enter actual ","courseWork"+courseWork.getCourseWorkName());

        courseWorkName.setText(courseWork.getCourseWorkName());
        Log.e("Enter actual ","textview"+courseWorkName.getText().toString());

        save.setOnClickListener( new View.OnClickListener() {
            public void onClick(View v) {
                final int actualGrade = Integer.parseInt(agrade.getText().toString());
                int total = Integer.parseInt(cwTotal.getText().toString());
                //float realGrade = calcActGrade(courseWork.getWeight(),actualGrade,total);
                //float realGrade = (courseWork.getWeight()*actualGrade)/total;

                /*if(courseWork.getActualGrade() != 0){
                    //subtract weight from the course.getPercentComplete
                    course.resetPercentComplete(courseWork);
                    course.resetAvgGrade(courseWork);
                    //resetaverage grade
                }*/
                courseWork.setActualGrade((float) actualGrade);
                course.setAvgGrade(courseWork);
                course.setPercentComplete(courseWork);
                course.setDesGrade(courseWork);
                course.setPassGrade(courseWork);
                DatabaseHelper databaseHelper = DatabaseHelper.getInstance(getApplicationContext());
                databaseHelper.addActualGrade(cwName,courseWork.getCourse().getId(),
                        courseWork.getStudent().getId(), actualGrade);
                databaseHelper.addPercentCompleted(courseWork.getCourse().getId(),courseWork.getStudent().getId(),
                        (int) course.getPercentComplete());
                databaseHelper.addAverageGrade(courseWork.getCourse().getId(),courseWork.getStudent().getId(),
                        (int) course.getAvgGrade());

                //if ((actualGrade/total)*100 >=70) {
                if (courseWork.getActualGrade()>=courseWork.desiredGrade) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(context1);
                    builder.setMessage("WOW! nice job! keep up the good work :)").setTitle("Ruby:").setPositiveButton("Yaaay!", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int z) {
                            Intent i = new Intent(EnterActual.this, gradeDetiles.class);
                            i.putExtra("enterActual", 1);
                            i.putExtra("studentObj", student);
                            i.putExtra("courseObj", course);
                            i.putExtra("courseWorkObj", courseWork);
                            i.putExtra("courseName", course.getId());
                            i.putExtra("studentId", student.getId());
                            i.putExtra("courseWorkName", courseWork.getCourseWorkName());
                            i.putExtra("actualGrade", actualGrade);
                            //i.putExtra("actualGrade", courseWork.getActualGrade());
                            startActivity(i);
                        }
                    });
                    AlertDialog dailog = builder.create();
                    dailog.show();

                } else if (courseWork.getActualGrade() < courseWork.getDesiredGrade() && courseWork.getActualGrade() >= courseWork.getPassingGrade()) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(context1);
                    builder.setMessage("Great job! But try harder next time :)").setTitle("Ruby:").setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int z) {
                            Intent i = new Intent(EnterActual.this, gradeDetiles.class);
                            i.putExtra("enterActual", 1);
                            i.putExtra("studentObj", student);
                            i.putExtra("courseObj", course);
                            i.putExtra("courseWorkObj", courseWork);
                            i.putExtra("courseName", course.getId());
                            i.putExtra("studentId", student.getId());
                            i.putExtra("courseWorkName", courseWork.getCourseWorkName());
                            i.putExtra("actualGrade", actualGrade);
                            //i.putExtra("actualGrade", courseWork.getActualGrade());
                            startActivity(i);
                        }
                    });
                    AlertDialog dailog = builder.create();
                    dailog.show();

                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(context1);
                    builder.setMessage("oh! It seems that we need to go see someone...").setTitle("Ruby:").setPositiveButton("oops", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int z) {
                            Intent i = new Intent(EnterActual.this, gradeDetiles.class);
                            i.putExtra("enterActual", 1);
                            i.putExtra("studentObj", student);
                            i.putExtra("courseObj", course);
                            i.putExtra("courseWorkObj", courseWork);
                            i.putExtra("courseName", course.getId());
                            i.putExtra("studentId", student.getId());
                            i.putExtra("courseWorkName", courseWork.getCourseWorkName());
                            i.putExtra("actualGrade", actualGrade);
                            //i.putExtra("actualGrade", courseWork.getActualGrade());
                            startActivity(i);
                        }
                    });
                    AlertDialog dailog = builder.create();
                    dailog.show();
                }




            }
        });

    }

    protected void deleteCourseWork(View v) {
        DatabaseHelper db = DatabaseHelper.getInstance(getApplicationContext());
        Log.e("courseProfile delete","delete "+course.getId());
        db.removeCourseWork(courseWorkName.getText().toString(),course.getId(),student.getId());
        //remove course work list
        Intent i = new Intent(EnterActual.this, gradeDetiles.class);
        i.putExtra("studentObj",student);
        i.putExtra("courseObj",course);
        i.putExtra("studentId",studentId);
        i.putExtra("courseName",courseName);
        i.putExtra("enterActual",1);
        //returnFrom == 1
        i.putExtra("return",1);
        startActivity(i);

        AlertDialog.Builder builder = new AlertDialog.Builder(context1);
        builder.setMessage(cwName).setTitle("you have deleted from your course work list:")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int z) {
                Intent i = new Intent(EnterActual.this, gradeDetiles.class);
                i.putExtra("studentObj",student);
                i.putExtra("courseObj",course);
                i.putExtra("studentId",studentId);
                i.putExtra("courseName",courseName);
                //returnFrom == 1
                i.putExtra("return",1);
                startActivity(i);
            }});
        AlertDialog dailog = builder.create();
        dailog.show();
    }
    public float calcActGrade(float weight, float actGrade, int courseWorkTotal) {
        float result = (weight*actGrade)/courseWorkTotal;
        return result;
    }

}




