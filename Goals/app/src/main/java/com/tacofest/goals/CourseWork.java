package com.tacofest.goals;

/**
 * Created by okorisanipe on 2016-11-06.
 */

public class CourseWork {
    Course course;
    Student student;
    String username;
    //float passingGrade; **I think it makes sense to only have this info in Course and update it every time
    //float desiredGrade; **we add a grade
    float actualGrade;
    float weight;
    String dueDate;
    //Date dueDate ?not sure how to do this one

    CourseWork(Course c, Student s, String u, float w, String dd){
     this.course = c;
     this.student =s;
   //  this.passingGrade = pGrade;
     //this.desiredGrade = dGrade;
     this.weight = w;
     this.dueDate = dd;
    }

    void setCourse(Course c){
      this.course = c;
    }

    void setStudent(Student s){
      this.student = s;
    }

    void setUsername(String u){
      this.username = u;
    }

   // void setPassingGrade(float pGrade){
     // this.passingGrade = pGrade;
    //}

   // void setDesiredGrade(float dGrade){
     // this.desiredGrade = dGrade;
    //}

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

    String getUsername(){
      return this.username;
    }

    //float getPassingGrade(){
      //return this.passingGrade;
    //}

    //float getDesiredGrade(){
      //return this.desiredGrade;
    //}

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
        course.addCourseWork(this);
    }
}
