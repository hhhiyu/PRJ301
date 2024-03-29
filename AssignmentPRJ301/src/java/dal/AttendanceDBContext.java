/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import dal.DBContext;
import dal.DeptDBContext;
import dal.StudentDBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Department;
import model.Student;
import model.Attendance;

/**
 *
 * @author sonnt
 */
public class AttendanceDBContext extends DBContext<Attendance> {

    public void updateAtts(ArrayList<Attendance> atts, int sessionid) {
        ArrayList<PreparedStatement> stms = new ArrayList<>();
        try {
            connection.setAutoCommit(false);
            //UPDATE Session Record
            String sql_update_session = "UPDATE Session SET status = 1 WHERE sessionid = ?";
            PreparedStatement stm_update_session = connection.prepareStatement(sql_update_session);
            stm_update_session.setInt(1, sessionid);
            stm_update_session.executeUpdate();
            stms.add(stm_update_session);

            //PROCESS Attendace records
            for (Attendance att : atts) {
                if (att.getId() == 0) //INSERT
                {
                    String sql_insert_att = "INSERT INTO [Attendance]\n"
                            + "           ([sid]\n"
                            + "           ,[sessionid]\n"
                            + "           ,[status]\n"
                            + "           ,[description]\n"
                            + "           ,[recordtime])\n"
                            + "     VALUES\n"
                            + "           (?\n"
                            + "           ,?\n"
                            + "           ,?\n"
                            + "           ,?\n"
                            + "           ,?)";
                    PreparedStatement stm_insert_att = connection.prepareStatement(sql_insert_att);
                    stm_insert_att.setString(1, att.getStudent().getId());
                    stm_insert_att.setInt(2, sessionid);
                    stm_insert_att.setBoolean(3, att.isStatus());
                    stm_insert_att.setString(4, att.getDescription());
                    stm_insert_att.setTimestamp(5, att.getRecord());
                    stm_insert_att.executeUpdate();
                    stms.add(stm_insert_att);

                } else //UPDATE
                {
                    String sql_update_att = "UPDATE Attendance SET status = ?,description = ?,recordtime = ? WHERE aid = ?";
                    PreparedStatement stm_update_att = connection.prepareStatement(sql_update_att);
                    stm_update_att.setBoolean(1, att.isStatus());
                    stm_update_att.setString(2, att.getDescription());
                    stm_update_att.setTimestamp(3, att.getRecord());
                    stm_update_att.setInt(4, att.getId());
                    stm_update_att.executeUpdate();
                    stms.add(stm_update_att);
                }
            }

            connection.commit();
        } catch (SQLException ex) {

            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(AttendanceDBContext.class.getName()).log(Level.SEVERE, null, ex1);
            }
            Logger.getLogger(AttendanceDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(AttendanceDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
            for (PreparedStatement stm : stms) {
                try {
                    stm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(AttendanceDBContext.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(AttendanceDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public ArrayList<Attendance> getAttsBySessionID(int sessionid) {
        ArrayList<Attendance> atts = new ArrayList<>();
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT \n"
                    + "	s.sid,s.sname,s.simage\n"
                    + "	,ISNULL(a.status,0) as [status], ISNULL(a.description,'') as [description],a.recordtime,a.aid\n"
                    + "FROM Student s INNER JOIN Student_Group sg ON sg.sid = s.sid\n"
                    + "						INNER JOIN [Group] g ON g.gid = sg.gid\n"
                    + "						INNER JOIN [Session] ses ON ses.gid = g.gid\n"
                    + "						LEFT JOIN [Attendance] a \n"
                    + "						ON a.sid = s.sid AND a.sessionid = ses.sessionid\n"
                    + "						WHERE ses.sessionid = ?";
            stm = connection.prepareStatement(sql);
            stm.setInt(1, sessionid);
            rs = stm.executeQuery();
            while (rs.next()) {
                Attendance a = new Attendance();
                a.setId(rs.getInt("aid"));
                a.setStatus(rs.getBoolean("status"));
                a.setDescription(rs.getString("description"));
                Student s = new Student();
                s.setId(rs.getString("sid"));
                s.setName(rs.getString("sname"));
                s.setImage(Base64.getEncoder().encodeToString(rs.getBytes("simage")));
                a.setStudent(s);
                a.setRecord(rs.getTimestamp("recordtime"));
                atts.add(a);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AttendanceDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(AttendanceDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                stm.close();
            } catch (SQLException ex) {
                Logger.getLogger(AttendanceDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(AttendanceDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return atts;
    }

}
