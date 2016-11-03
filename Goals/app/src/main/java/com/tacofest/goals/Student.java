package com.tacofest.goals;

/**
 * Created by okorisanipe on 2016-11-02.
 */

public class Student {
    private String userName;
    private int id;

    public Student() {
        userName = "Person";
        id = -1;
    }

    public Student(String name, int ID) {
        userName = name;
        id = ID;
    }

    String getUser() { return userName; }
    int getId() { return id; }

    public void setUserName(String name) { userName = name; }
    public void setId(int ID) { id = ID; }
}
