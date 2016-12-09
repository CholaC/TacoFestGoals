package com.tacofest.goals;

import java.io.Serializable;

/**
 * Created by okorisanipe on 2016-11-02.
 */

public class Student implements Serializable{
    private String userName;
    private String id;

    public Student() {
        userName = "Person";
        id = "1009078";
    }

    public Student(String name, String ID) {
        userName = name;
        id = ID;
    }

    String getUser() { return userName; }
    String getId() { return id; }

    public void setUserName(String name) { userName = name; }
    public void setId(String ID) { id = ID; }
}
