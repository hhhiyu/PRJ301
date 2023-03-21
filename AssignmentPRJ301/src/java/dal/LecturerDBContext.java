/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import dal.DBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Course;
import model.Department;
import model.Group;
import model.Lecturer;
import model.Room;
import model.Session;
import model.Term;
import model.TimeSlot;

/**
 *
 * @author thaim
 */
public class LecturerDBContext extends DBContext<Lecturer> {
    
    public ArrayList<Session> getSessions(int lid) {
        String sql = "SELECT ses.sessionid,ses.date,ses.status,g.gid,g.gname,c.cid,c.cname,c.code,r.rid,r.rname,t.tid,t.description, term.termid,d.did\n"
                + "                FROM [Session] ses INNER JOIN [Group] g ON ses.gid = g.gid\n"
                + "                				INNER JOIN Course c ON c.cid = g.cid\n"
                + "                				INNER JOIN Room r ON r.rid = ses.rid\n"
                + "                			INNER JOIN TimeSlot t ON t.tid = ses.tid\n"
                + "							INNER JOIN Department d on d.did=c.did\n"
                + "							INNER JOIN Term ON Term.termid=d.termid\n"
                + "                WHERE ses.lid = ?";
        ArrayList<Session> sessions = new ArrayList<>();
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, lid);
            rs = stm.executeQuery();
            while (rs.next()) {
                Session session = new Session();
                session.setDate(rs.getDate("date"));
                session.setStatus(rs.getBoolean("status"));
                session.setId(rs.getInt("sessionid"));
                
                Term term = new Term();
                term.setId(rs.getInt("termid"));
                
                Department d = new Department();
                d.setId(rs.getInt("did"));
                
                Group g = new Group();
                g.setId(rs.getInt("gid"));
                g.setName(rs.getString("gname"));
                
                
                Course c = new Course();
                c.setId(rs.getInt("cid"));
                c.setName(rs.getString("cname"));
                c.setCode(rs.getString("code"));
                
                d.setTerm(term);
                c.setDept(d);
                g.setCourse(c);
                session.setGroup(g);
                Room r = new Room();
                r.setId(rs.getInt("rid"));
                r.setName(rs.getString("rname"));
                session.setRoom(r);
                
                TimeSlot t = new TimeSlot();
                t.setId(rs.getInt("tid"));
                t.setDescription(rs.getString("description"));
                session.setSlot(t);
                
                sessions.add(session);
            }
        } catch (SQLException ex) {
            Logger.getLogger(LecturerDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(LecturerDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            try {
                stm.close();
            } catch (SQLException ex) {
                Logger.getLogger(LecturerDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(LecturerDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        System.out.println(sessions.size());
        return sessions;
    }
}
