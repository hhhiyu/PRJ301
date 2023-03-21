/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Date;

/**
 *
 * @author thaim
 */
public class Session {

    private int id;
    private Group group;
    private Room room;
    private TimeSlot slot;
    private Lecturer lecturer;
    private Date date;
    private boolean status;

    public Group getGroup() {
        return group;
    }

    public Room getRoom() {
        return room;
    }

    public TimeSlot getSlot() {
        return slot;
    }

    public Lecturer getLecturer() {
        return lecturer;
    }

    public Date getDate() {
        return date;
    }

    public boolean isStatus() {
        return status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public void setSlot(TimeSlot slot) {
        this.slot = slot;
    }

    public void setLecturer(Lecturer lecturer) {
        this.lecturer = lecturer;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

}
