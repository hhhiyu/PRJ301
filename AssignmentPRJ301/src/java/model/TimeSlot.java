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
public class TimeSlot {
    private int id;
    private String description;
    private ArrayList<Session> sessions = new ArrayList<>();

    public void setId(int id) {
        this.id = id;
    }



    public void setSessions(ArrayList<Session> sessions) {
        this.sessions = sessions;
    }

    public int getId() {
        return id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }


    public ArrayList<Session> getSessions() {
        return sessions;
    }
    
}
