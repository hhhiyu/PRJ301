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
import model.Term;

/**
 *
 * @author thaim
 */
public class TermDBContext extends DBContext<Term> {

    public ArrayList<Term> ListByCampus(int cid) {
        ArrayList<Term> terms = new ArrayList<>();
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            String sql = "select t.termid,t.termcode,t.termname,c.campusname\n"
                    + "from Term t\n"
                    + "JOIN Campus c ON t.campusid=c.campusid\n"
                    + "where t.campusid = ?";
            stm = connection.prepareStatement(sql);
            stm.setInt(1, cid);
            rs = stm.executeQuery();
            while (rs.next()) {
                Term t = new Term();
                t.setName(rs.getString("termname"));
                t.setCode(rs.getString("termcode"));
                t.setId(rs.getInt("id"));

                Campus c = new Campus();
                c.setId(cid);
                c.setName(rs.getString("campusname"));
                t.setCampus(c);
                terms.add(t);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TermDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
                stm.close();
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(TermDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return terms;
    }

    public ArrayList<Term> all() {
        ArrayList<Term> terms = new ArrayList<>();
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            String sql = "select termid,termcode,termname\n"
                    + "from Term ";
            stm = connection.prepareStatement(sql);
//            stm.setInt(1, cid);
            rs = stm.executeQuery();
            while (rs.next()) {
                Term t = new Term();
                t.setName(rs.getString("termname"));
                t.setCode(rs.getString("termcode"));
                t.setId(rs.getInt("termid"));

//                Campus c = new Campus();
//                c.setId(cid);
//                c.setName(rs.getString("campusname"));
//                t.setCampus(c);
                terms.add(t);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TermDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
                stm.close();
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(TermDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return terms;
    }
}
