/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author thaim
 */
public class Course {

    private int id;
    private String name;
    private Department dept;
    private String code;
    private ArrayList<Group> groups = new ArrayList<>();

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Department getDept() {
        return dept;
    }

    public String getCode() {
        return code;
    }

    public ArrayList<Group> getGroups() {
        return groups;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDept(Department dept) {
        this.dept = dept;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setGroups(ArrayList<Group> groups) {
        this.groups = groups;
    }

  
}
