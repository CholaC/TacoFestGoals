package com.tacofest.goals;

import java.util.*;


/**
 * Created by okorisanipe on 2016-11-02.
 */

public class Course {
    private String id;
    private float desGrade;
    private float passGrade;
    private float avgGrade;
    private float reqGrade;
    private float percentComplete;
    private Student student;
    private ArrayList<CourseWork> courseWork;
    //needs time object for lecture and office hours

    Course() {
        id = "Course";
        desGrade = -1;
        passGrade = -1;
        avgGrade = -1;
        student = new Student();
    }

    Course(String ID, float des, float pass, Student s) {
        id = ID;
        desGrade = des;
        passGrade = pass;
        avgGrade = 0;
        percentComplete = 0;
        reqGrade = 0;
        student = s;
        courseWork = new ArrayList<CourseWork>();
    }

    String getId() { return id; }
    float getDesGrade() { return desGrade; }
    float getPassGrade() { return passGrade; }
    float getAvgGrade() { return avgGrade; }
    float getReqGrade() { return reqGrade; }
    float getPercentComplete() { return percentComplete; }
    Student getStudent() { return student; }

    void setId(String ID) { id = ID; }
    void setDesGrade(float grade) { desGrade = grade; }

    private void setAvgGrade() {
        float sum = 0;
        for (int i = 0; i < courseWork.size(); i++) {
            if(courseWork.get(i).getActualGrade() > 0) {
                sum += courseWork.get(i).getActualGrade()*courseWork.get(i).getWeight();
            }
        }
        avgGrade = sum/percentComplete;
    }

    private void setPercentComplete(CourseWork c) {
        percentComplete += c.getWeight();
    }

    private void setReqGrade(CourseWork c) {
        reqGrade = (desGrade*100 - avgGrade*percentComplete)/(100 - percentComplete);
    }

    private void setPassGrade(CourseWork c) {
        passGrade = (passGrade*100 - avgGrade*percentComplete)/(100 - percentComplete);
    }

    void addCourseWork(CourseWork c) {
        courseWork.add(c);
        setPercentComplete(c);
        setAvgGrade();
        setReqGrade(c);
        setPassGrade(c);
    }
    
}
