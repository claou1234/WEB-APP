package gr.aueb.sev.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gr.aueb.sev.dao.ITeacherDAO;
import gr.aueb.sev.dao.TeacherDAOImpl;
import gr.aueb.sev.model.Teacher;
import gr.aueb.sev.service.ITeacherService;
import gr.aueb.sev.service.TeacherServiceImpl;

@WebServlet("/courses/")
public class ContactUsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	ITeacherDAO teacherDAO = new TeacherDAOImpl();
    ITeacherService teacherServ = new TeacherServiceImpl(teacherDAO);
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	    response.setContentType("text/html, charset=UTF-8");
        
        String lastname = "";
        
        try {
            List<Teacher> teachers = teacherServ.getTeacherByLastname(lastname);
            request.setAttribute("teachers", teachers);
            request.getRequestDispatcher("/jsps/courses/coursesmenu.jsp").forward(request, response);
            
        } catch(SQLException e) {
            request.setAttribute("sqlError", true);
            request.getRequestDispatcher("/jsps/teachers/teachersmenu.jsp").forward(request, response);
            
        } 
	    
	}

}
