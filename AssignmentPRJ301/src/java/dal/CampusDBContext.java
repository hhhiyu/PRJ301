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

/**
 *
 * @author thaim
 */
public class CampusDBContext extends DBContext<Campus> {

    public ArrayList<Campus> all() {
        ArrayList<Campus> campus = new ArrayList<>();
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT campusid,campusname FROM Campus";
            stm = connection.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                Campus c = new Campus();
                c.setId(rs.getInt("campusid"));
                c.setName(rs.getString("campusname"));
                campus.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CampusDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
                stm.close();
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(CampusDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return campus;
    }
}
