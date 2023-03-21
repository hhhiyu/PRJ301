/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.student;

import controller.lecturer.*;
import dal.CourseDBContext;
import dal.DeptDBContext;
import dal.GroupDBContext;
import dal.StudentDBContext;
import dal.TermDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import model.Course;
import model.Department;
import model.Group;
import model.Student;
import model.Term;
import model.User;

/**
 *
 * @author thaim
 */
@WebServlet(name = "ListByDept", urlPatterns = {"/course/list"})
public class ListByDept extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        User user = (User) request.getSession().getAttribute("user");
        ArrayList<Term> terms = new ArrayList<>();
        TermDBContext termDB = new TermDBContext();
//        terms = termDB.ListByCampus(1);
        terms = termDB.all();
        request.setAttribute("terms", terms);

        if (request.getParameter("tid") != null) {
            int tid = Integer.parseInt(request.getParameter("tid"));
            ArrayList<Department> depts = new ArrayList<>();
            DeptDBContext deptDB = new DeptDBContext();
            depts = deptDB.all(tid);
            request.setAttribute("depts", depts);
            request.setAttribute("tid_chosen", tid);

            if (request.getParameter("did") != null) {
                int did = Integer.parseInt(request.getParameter("did"));
                CourseDBContext courseDB = new CourseDBContext();
                ArrayList<Course> courses = new ArrayList<>();
                courses = courseDB.ListByDept(did);
                request.setAttribute("courses", courses);
                request.setAttribute("did_chosen", did);

                if (request.getParameter("cid") != null) {
                    int cid = Integer.parseInt(request.getParameter("cid"));
                    GroupDBContext grDB = new GroupDBContext();
                    ArrayList<Group> groups = new ArrayList<>();
                    groups = grDB.GetByCid(cid);
                    request.setAttribute("groups", groups);
                    request.setAttribute("cid_chosen", cid);

                    if (request.getParameter("gid") != null) {
                        int gid = Integer.parseInt(request.getParameter("gid"));
                        StudentDBContext stuDB = new StudentDBContext();
                        ArrayList<Student> students = new ArrayList<>();
                        students = stuDB.get(gid);
                        request.setAttribute("students", students);
                        
                        request.setAttribute("gid_chosen", gid);
                    }
                }
            }
        }
        request.getRequestDispatcher("../view/student/groupcourse.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("../view/student/groupcourse.jsp");

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
