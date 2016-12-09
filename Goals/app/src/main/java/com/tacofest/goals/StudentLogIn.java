package com.tacofest.goals;

/**
 * Created by ugochiokehi on 2016-10-24.
 */
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class StudentLogIn extends AppCompatActivity {
    EditText studentName, studentID;
    Button btnsignIn, btnsignUp;
    String username, studentid;
    int login;
    Context context = this;
    Context context1 = this;
    //SQLiteDatabase db;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_login);


        studentName = (EditText) findViewById(R.id.student_name);
        studentID = (EditText) findViewById(R.id.student_id);
        btnsignIn = (Button) findViewById(R.id.btnsignin);
        btnsignUp = (Button) findViewById(R.id.btnsignup);

        btnsignUp.setOnClickListener( new View.OnClickListener() {
            public void onClick(View v) {
                username = studentName.getText().toString();
                studentid = studentID.getText().toString();
                login = 2;
                Student student = new Student(username, studentid);
                DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
                if(!(databaseHelper.studentExist(username,studentid))){
                    databaseHelper.addStudentdata(studentid,username);
                    databaseHelper.close();

                    AlertDialog.Builder builder = new AlertDialog.Builder(context1);
                    builder.setMessage(username).setTitle("Welcome!").setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int z) {
                            Student student = new Student(username, studentid);

                            Intent i = new Intent(StudentLogIn.this, StudentProfile.class);
                            i.putExtra("studentObj", student);
                            i.putExtra("studentId", studentid);
                            i.putExtra("username", username);
                            i.putExtra("login", login);
                            startActivity(i);
                        }
                    });
                    AlertDialog dailog = builder.create();
                    dailog.show();
                }
            }
        });
        btnsignIn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                username = studentName.getText().toString();
                studentid = studentID.getText().toString();
                login = 1;
                DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
                if (databaseHelper.studentExist(username, studentid)) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(context1);
                    builder.setMessage(username).setTitle("Welcome Back").setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int z) {
                            Student student = new Student(username, studentid);
                            Intent i = new Intent(StudentLogIn.this, StudentProfile.class);
                            i.putExtra("studentObj", student);
                            i.putExtra("studentId", studentid);
                            i.putExtra("username", username);
                            i.putExtra("login", login);
                            startActivity(i);
                        }
                    });
                    AlertDialog dailog = builder.create();
                    dailog.show();
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(context1);
                    builder.setMessage(username).setTitle("Who are you?").setPositiveButton("Try Again", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int z)
                        { }
                    });
                    AlertDialog dailog = builder.create();
                    dailog.show();
                }
            }
        });
    }
}
