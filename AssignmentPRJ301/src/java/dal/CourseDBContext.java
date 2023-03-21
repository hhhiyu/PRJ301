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
import model.Campus;
import model.Course;
import model.Department;
import model.Term;

/**
 *
 * @author thaim
 */
public class CourseDBContext extends DBContext<Course> {

    public ArrayList<Course> ListByDept(int did) {
        ArrayList<Course> courses = new ArrayList<>();
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            String sql = "Select c.cid,c.code,c.cname,d.did,d.dname,d.termid\n"
                    + "From Course c\n"
                    + "Join Department d on c.did=d.did\n"
                    + "where c.did = ?";
            stm = connection.prepareStatement(sql);
            stm.setInt(1, did);
            rs = stm.executeQuery();
            while (rs.next()) {
                Course c = new Course();
                c.setId(rs.getInt("cid"));
                c.setCode(rs.getString("code"));
                c.setName(rs.getString("cname"));
                
                Term t = new Term();
                t.setId(rs.getInt("termid"));
                
                Department d = new Department();
                d.setId(rs.getInt("did"));
                d.setName(rs.getString("dname"));
                d.setTerm(t);
                
                c.setDept(d);
                courses.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CourseDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
                stm.close();
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(CourseDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return courses;
    }
}
