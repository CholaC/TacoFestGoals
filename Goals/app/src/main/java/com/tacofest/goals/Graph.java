package com.tacofest.goals;

/**
 * Created by ugochiokehi on 2016-11-23.
 */

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

public class Graph extends AppCompatActivity {
    BarChart barChart;
    TextView cor, stu;
    Student student2;
    Course course2;
    String courseId, studentId;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);
        barChart = (BarChart) findViewById(R.id.bargraph);
        cor = (TextView) findViewById(R.id.txtCourse);
        stu = (TextView) findViewById(R.id.txtStudent);


        Intent i = getIntent();
        student2 = (Student)i.getSerializableExtra("studentObj");
        course2 = (Course)i.getSerializableExtra("courseObj");
        studentId = i.getStringExtra("studentId");
        courseId = i.getStringExtra("courseName");

        cor.setText(courseId);
        stu.setText(studentId);
        Log.e("drawGroupedBarChartstrings","course "+courseId+"student"+studentId);
        Log.e("drawGroupedBarCharttextview","course "+cor.getText().toString()+"student"+stu.getText().toString());
        drawGroupedBarChart(course2.getId(),student2.getId());
        //drawGroupedBarChart();
    }

   private void drawGroupedBarChart(){
       Cursor cur;
       float[] group1 = new float[]{80f,80f};
       float[] group2 = new float[]{90f,100f};
       float[] group3 = new float[]{50f,20f};

       List<BarEntry> entriesGroup1 = new ArrayList<>();
       List<BarEntry> entriesGroup2 = new ArrayList<>();
       List<BarEntry> entriesGroup3 = new ArrayList<>();


        // fill the lists
        for(int i = 0; i < group1.length; i++) {
            entriesGroup1.add(new BarEntry(i, group1[i]));
            entriesGroup2.add(new BarEntry(i, group2[i]));
            entriesGroup3.add(new BarEntry(i, group3[i]));
        }

       BarDataSet set1 = new BarDataSet(entriesGroup1, "Group 1");
       BarDataSet set2 = new BarDataSet(entriesGroup2, "Group 2");
       BarDataSet set3 = new BarDataSet(entriesGroup3, "Group 3");

       float groupSpace = 0.06f;
       float barSpace = 0.02f; // x2 dataset
       float barWidth = 0.20f; // x2 dataset

       BarData barData = new BarData(set1,set2,set3);
       barData.setBarWidth(barWidth);
       barChart.setFitBars(true);
       barChart.setData(barData);
       barChart.groupBars(0f,groupSpace,barSpace);
       //barChart.setScaleX(-0.6f);
       //barChart.setScaleMinima(0f, 0f);
       barChart.setScaleEnabled(true);
   }
    private void drawGroupedBarChart(String c, String s){
        Cursor cur;
        float[] group1 = new float[]{30f,56f,89f,50f};
        float[] group2 = new float[]{40f,79f,39f,20f};
        float[] group3 = new float[]{50f,89f,49f,30f};

        List<BarEntry> entriesGroup1 = new ArrayList<>();
        List<BarEntry> entriesGroup2 = new ArrayList<>();
        List<BarEntry> entriesGroup3 = new ArrayList<>();
        float i = 0f;
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(getApplicationContext());
        //returns a cursor list
        Log.e("drawGroupedBarChart","course "+c+"student"+s);
        cur = databaseHelper.getCourseWorkList(c,s);
        for (cur.moveToFirst(); !cur.isAfterLast(); cur.moveToNext()) {
            // do what you need with the cursor here
            float aGrade = (float)cur.getInt(cur.getColumnIndex(TableData.TableInfo.ACTUAL_GRADE));
            float pGrade = (float)cur.getInt(cur.getColumnIndex(TableData.TableInfo.PASSING_GRADE));
            float dGrade = (float)cur.getInt(cur.getColumnIndex(TableData.TableInfo.DESIRED_GRADE));
            int weight = cur.getInt(cur.getColumnIndex(TableData.TableInfo.WEIGHT));
            String dd = cur.getString(cur.getColumnIndex(TableData.TableInfo.DUE_DATE));

            entriesGroup1.add(new BarEntry(i,  aGrade));
            entriesGroup2.add(new BarEntry(i, dGrade));
            entriesGroup3.add(new BarEntry(i, pGrade));
            i++;
            Log.e("graph.java","actu-"+aGrade+" desire-"+dGrade+" pass-"+pGrade);
        }

//        entriesGroup1.add(new BarEntry(i,  aGrade));
//        entriesGroup2.add(new BarEntry(i, dGrade));
//        entriesGroup3.add(new BarEntry(i, pGrade));
//        entriesGroup1.add(new BarEntry(i,  aGrade));
//        entriesGroup2.add(new BarEntry(i, dGrade));
//        entriesGroup3.add(new BarEntry(i, pGrade));
//        entriesGroup1.add(new BarEntry(i,  aGrade));
//        entriesGroup2.add(new BarEntry(i, dGrade));
//        entriesGroup3.add(new BarEntry(i, pGrade));
//        while (cur.moveToNext()) {
//            final String cwname = cur.getString(cur.getColumnIndex(TableData.TableInfo.COURSEWORK_NAME));
//            int aGrade = cur.getInt(cur.getColumnIndex(TableData.TableInfo.ACTUAL_GRADE));
//            int pGrade = cur.getInt(cur.getColumnIndex(TableData.TableInfo.PASSING_GRADE));
//            int dGrade = cur.getInt(cur.getColumnIndex(TableData.TableInfo.DESIRED_GRADE));
//            int weight = cur.getInt(cur.getColumnIndex(TableData.TableInfo.WEIGHT));
//            String dd = cur.getString(cur.getColumnIndex(TableData.TableInfo.DUE_DATE));
//
//            entriesGroup1.add(new BarEntry(i,  aGrade));
//            entriesGroup2.add(new BarEntry(i, dGrade));
//            entriesGroup3.add(new BarEntry(i, pGrade));
//            Log.e("graph.java","actu-"+aGrade+" desire-"+dGrade+" pass-"+pGrade);
//            i++;
//            //final CourseWork cw= new CourseWork(course,stu,cwname,pGrade,dGrade,weight,dd);
//            //course.setAvgGrade(cw);
//        }


        /*// fill the lists
        for(int i = 0; i < group1.length; i++) {
            entriesGroup1.add(new BarEntry(i, group1[i]));
            entriesGroup2.add(new BarEntry(i, group2[i]));
            entriesGroup3.add(new BarEntry(i, group3[i]));
        }*/

        BarDataSet set1 = new BarDataSet(entriesGroup1, "Group 1");
        BarDataSet set2 = new BarDataSet(entriesGroup2, "Group 2");
        BarDataSet set3 = new BarDataSet(entriesGroup3, "Group 3");

        float groupSpace = 0.06f;
        float barSpace = 0.02f; // x2 dataset
        float barWidth = 0.15f; // x2 dataset

        BarData barData = new BarData(set1,set2,set3);
        barData.setBarWidth(barWidth);
        barChart.setFitBars(true);
        barChart.setData(barData);
        barChart.groupBars(0f,groupSpace,barSpace);
        //barChart.setViewPortOffsets(0f, 0f, 0f, 0f);
       // barChart.setScaleX(-0.6f);
        //barChart.setScaleMinima(0.01f, 1f);
        barChart.setScaleEnabled(true);
    }
}
