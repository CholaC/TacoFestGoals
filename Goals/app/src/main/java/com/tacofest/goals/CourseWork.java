package com.tacofest.goals;

import java.io.Serializable;

/**
 * Created by okorisanipe on 2016-11-06.
 */

public class CourseWork implements Serializable{
    Course course;
    Student student;
    String courseWorkName;
    float passingGrade;
    float desiredGrade;
    float actualGrade;
    float weight;
    String dueDate;


    CourseWork(Course c, Student s, String cwn,float pGrade, float dGrade, float w, String dd){
     this.course = c;
     this.student =s;
        this.courseWorkName = cwn;
     this.passingGrade = pGrade;
     this.desiredGrade = dGrade;
     this.weight = w;
     this.dueDate = dd;
    }


    void setCourse(Course c){
      this.course = c;
    }

    void setStudent(Student s){
      this.student = s;
    }

    void setCourseWorkName(String u){
      this.courseWorkName = u;
    }

    void setPassingGrade(float pGrade){
      this.passingGrade = pGrade;
    }

    void setDesiredGrade(float dGrade){
      this.desiredGrade = dGrade;
    }

    void setWeight(float w){
      this.weight = w;
    }

    void setDueDate(String d){
      this.dueDate = d;
    }

    Course getCourse(){
      return this.course;
    }

    Student getStudent(){
      return this.student;
    }

    String getCourseWorkName(){
      return this.courseWorkName;
    }

    float getPassingGrade(){
      return this.passingGrade;
    }

    float getDesiredGrade(){
      return this.desiredGrade;
    }

    float getActualGrade() {
        return actualGrade;
    }


    float getWeight(){
      return this.weight;
    }

    String getDueDate(){
      return this.dueDate;
    }

    public void setActualGrade(float actualGrade) {
        this.actualGrade = actualGrade;
       // course.addCourseWork(this);
    }
}
