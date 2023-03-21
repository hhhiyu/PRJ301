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
public class Term {

    private int id;
    private String code;
    private String name;
    private Campus campus;
    private ArrayList<Department> depts = new ArrayList<>();

    public int getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public Campus getCampus() {
        return campus;
    }

    public ArrayList<Department> getDepts() {
        return depts;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCampus(Campus campus) {
        this.campus = campus;
    }

    public void setDepts(ArrayList<Department> depts) {
        this.depts = depts;
    }
    
}
