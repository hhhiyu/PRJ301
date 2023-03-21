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
import model.Department;
import model.Term;

/**
 *
 * @author thaim
 */
public class DeptDBContext extends DBContext<Department> {

    public ArrayList<Department> all(int tid) {
        ArrayList<Department> depts = new ArrayList<>();
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            String sql = "select did,dname,termid\n"
                    + "from Department\n"
                    + "where termid=?";
            stm = connection.prepareStatement(sql);
            stm.setInt(1, tid);
            rs = stm.executeQuery();
            while (rs.next()) {
                Department d = new Department();
                d.setId(rs.getInt("did"));
                d.setName(rs.getString("dname"));
                Term t = new Term();
                t.setId(rs.getInt("termid"));
                d.setTerm(t);
                depts.add(d);
            }

        } catch (SQLException ex) {
            Logger.getLogger(DeptDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
                stm.close();
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(DeptDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return depts;
    }

}
