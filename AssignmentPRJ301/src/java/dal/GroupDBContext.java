/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Course;
import model.Department;
import model.Group;
import model.Term;
import model.TimeSlot;

/**
 *
 * @author thaim
 */
public class GroupDBContext extends DBContext<Group> {

    public ArrayList<Group> GetByCid(int cid) {
        ArrayList<Group> groups = new ArrayList<>();
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            String sql = "select distinct g.gid,g.gname,t.termid,d.did,c.cid\n"
                    + "from [Group] g\n"
                    + "JOIN Student_Group sg ON sg.gid=g.gid\n"
                    + "JOIN Course c ON sg.cid=c.cid\n"
                    + "JOIN Department d ON c.did=d.did\n"
                    + "JOIN Term t ON t.termid=d.termid\n"
                    + "where sg.cid = ?";
            stm = connection.prepareStatement(sql);
            stm.setInt(1, cid);
            rs = stm.executeQuery();
            while (rs.next()) {
                Term t = new Term();
                t.setId(rs.getInt("termid"));
                
                Department d = new Department();
                d.setId(rs.getInt("did"));
                d.setTerm(t);
                
                Course c = new Course();
                c.setId(rs.getInt("cid"));
                c.setDept(d);
                
                Group g = new Group();
                g.setId(rs.getInt("gid"));
                g.setName(rs.getString("gname"));
                g.setCourse(c);
                groups.add(g);

            }
        } catch (SQLException ex) {
            Logger.getLogger(GroupDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(GroupDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                stm.close();
            } catch (SQLException ex) {
                Logger.getLogger(GroupDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(GroupDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return groups;

    }
}
