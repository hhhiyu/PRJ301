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
public class Department {

    private int id;
    private Term term;
    private String name;
    private ArrayList<Course> courses = new ArrayList<>();

    public void setCourses(ArrayList<Course> courses) {
        this.courses = courses;
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }

    public int getId() {
        return id;
    }

    public Term getTerm() {
        return term;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTerm(Term term) {
        this.term = term;
    }

    public void setName(String name) {
        this.name = name;
    }

}
