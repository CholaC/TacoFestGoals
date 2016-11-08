package com.tacofest.goals;

import java.util.*;


/**
 * Created by okorisanipe on 2016-11-02.
 */

public class Course {
    private String id;
    private float totDesGrade;
    private float totPassGrade;
    private float avgGrade;
    private float reqDesGrade;
    private float req2pass;
    private float percentComplete;
    private Student student;
    private ArrayList<CourseWork> courseWork;
    //needs time object for lecture and office hours

    Course() {
        id = "Course";
        totDesGrade = -1;
        totPassGrade = -1;
        avgGrade = -1;
        student = new Student();
    }

    Course(String ID, float des, float pass, Student s) {
        id = ID;
        totDesGrade = des;
        totPassGrade = pass;
        avgGrade = 0;
        percentComplete = 0;
        reqDesGrade = 0;
        student = s;
        courseWork = new ArrayList<CourseWork>();
    }

    String getId() { return id; }
    float getTotDesGrade() { return totDesGrade; }
    float getTotPassGrade() { return totPassGrade; }
    float getAvgGrade() { return avgGrade; }
    float getReqDesGrade() { return reqDesGrade; }
    float getReq2pass() { return req2pass; }
    float getPercentComplete() { return percentComplete; }
    Student getStudent() { return student; }

    void setId(String ID) { id = ID; }
    void setTotDesGrade(float grade) { totDesGrade = grade; }

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
        reqDesGrade = (totDesGrade*100 - avgGrade*percentComplete)/(100 - percentComplete);
    }

    private void setTotPassGrade(CourseWork c) {
        req2pass = (totPassGrade*100 - avgGrade*percentComplete)/(100 - percentComplete);
    }

    void addCourseWork(CourseWork c) {
        courseWork.add(c);
        setPercentComplete(c);
        setAvgGrade();
        setReqGrade(c);
        setTotPassGrade(c);
    }
    
}
