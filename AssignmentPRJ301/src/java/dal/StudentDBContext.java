/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Course;
import model.Department;
import model.Group;
import model.Lecturer;
import model.Room;
import model.Session;
import model.Student;
import model.Term;
import model.TimeSlot;

/**
 *
 * @author thaim
 */
public class StudentDBContext extends DBContext<Student> {

    public ArrayList<Student> get(int gid) {
        ArrayList<Student> students = new ArrayList<>();
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT s.[sid],s.sname,s.simage,g.gid\n"
                    + "                    FROM Student s\n"
                    + "                    JOIN Student_Group sg on sg.[sid]=s.[sid]\n"
                    + "                    JOIN [Group] g ON g.gid=sg.gid \n"
                    + "                    JOIN Course c ON c.cid=sg.cid\n"
                    + "                    JOIN Department d ON d.did=c.did\n"
                    + "                    JOIN Term t on t.termid=d.termid\n"
                    + "                    where sg.gid= ?";
            stm = connection.prepareStatement(sql);
            stm.setInt(1, gid);
            rs = stm.executeQuery();
            while (rs.next()) {
                Student s = new Student();
                s.setId(rs.getString("sid"));
                s.setName(rs.getString("sname"));
                s.setImage(Base64.getEncoder().encodeToString(rs.getBytes("simage")));

                Group g = new Group();
                g.setId(gid);

                ArrayList<Group> groups = new ArrayList<>();
                groups.add(g);

                s.setGroups(groups);
                students.add(s);
            }

        } catch (SQLException ex) {
            Logger.getLogger(StudentDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
                stm.close();
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(StudentDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return students;
    }

    public Student getTimeTable(String sid, Date from, Date to) {
        Student student = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT s.sid,s.sname,ses.sessionid,ses.date,ses.status,c.code\n"
                    + "	,g.gid,g.gname,c.cid,c.cname,r.rid,r.rname,term.termid,d.did,l.lid,l.lname,t.tid,t.description		\n"
                    + "FROM Student s INNER JOIN [Student_Group]  sg ON s.sid = sg.sid\n"
                    + "						INNER JOIN [Group] g ON g.gid = sg.gid\n"
                    + "						INNER JOIN [Course] c ON g.cid = c.cid\n"
                    + "						INNER JOIN [Session] ses ON g.gid = ses.gid\n"
                    + "						INNER JOIN [TimeSlot] t ON t.tid = ses.tid\n"
                    + "						INNER JOIN [Room] r ON r.rid = ses.rid\n"
                    + "						INNER JOIN [Lecturer] l ON l.lid = ses.lid\n"
                    + "						INNER JOIN [Department] d ON d.did = c.did\n"
                    + "						INNER JOIN [Term]  ON Term.termid = d.termid\n"
                    + "WHERE s.sid = ? AND ses.date >= ? AND ses.date <= ? ORDER BY s.sid,g.gid";
            stm = connection.prepareStatement(sql);
            stm.setString(1, sid);
            stm.setDate(2, from);
            stm.setDate(3, to);
            rs = stm.executeQuery();
            Group currentGroup = new Group();
            currentGroup.setId(-1);
            while (rs.next()) {
                if (student == null) {
                    student = new Student();
                    student.setId(rs.getString("sid"));
                    student.setName(rs.getString("sname"));
                }
                int gid = rs.getInt("gid");
                if (gid != currentGroup.getId()) {
                    currentGroup = new Group();
                    currentGroup.setId(rs.getInt("gid"));
                    currentGroup.setName(rs.getString("gname"));
                    Course c = new Course();
                    c.setId(rs.getInt("cid"));
                    c.setName(rs.getString("cname"));
                    c.setCode(rs.getString("code"));

                    Term term = new Term();
                    term.setId(rs.getInt("termid"));

                    Department d = new Department();
                    d.setId(rs.getInt("did"));
                    d.setTerm(term);

                    c.setDept(d);
                    currentGroup.setCourse(c);
                    student.getGroups().add(currentGroup);
                }

                Session ses = new Session();
                ses.setId(rs.getInt("sessionid"));
                ses.setDate(rs.getDate("date"));
                ses.setStatus(rs.getBoolean("status"));

                ses.setGroup(currentGroup);

                Lecturer l = new Lecturer();
                l.setId(rs.getInt("lid"));
                l.setName(rs.getString("lname"));
                ses.setLecturer(l);

                Room r = new Room();
                r.setId(rs.getInt("rid"));
                r.setName(rs.getString("rname"));
                ses.setRoom(r);

                TimeSlot t = new TimeSlot();
                t.setId(rs.getInt("tid"));
                t.setDescription(rs.getString("description"));
                ses.setSlot(t);

                currentGroup.getSessions().add(ses);

            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
                stm.close();
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(StudentDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return student;
    }
}
