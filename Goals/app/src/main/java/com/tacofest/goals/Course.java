package com.tacofest.goals;

/**
 * Created by okorisanipe on 2016-11-02.
 */

public class Course {
    private String id;
    private int desGrade;
    private int passGrade;
    private int avgGrade;
    private Student student;
    //needs time object for lecture and office hours

    Course() {
        id = "Course";
        desGrade = -1;
        passGrade = -1;
        avgGrade = -1;
        student = new Student();
    }

    Course(String ID, int des, int pass, Student s) {
        id = ID;
        desGrade = des;
        passGrade = pass;
        student = s;
    }

    String getId() { return id; }
    int getDesGrade() { return desGrade; }
    int getPassGrade() { return passGrade; }
    int getAvgGrade() { return avgGrade; }
    Student getStudent() { return student; }

    void setId(String ID) { id = ID; }
    void setDesGrade(int grade) { desGrade = grade; }
    void setAvgGrade(int grade) { avgGrade = grade; }
}
