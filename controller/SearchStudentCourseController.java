package gr.aueb.sev.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gr.aueb.sev.dao.IStudentCourseDAO;
import gr.aueb.sev.dao.StudentCourseDAOImpl;

import gr.aueb.sev.model.StudentCourse;
import gr.aueb.sev.service.IStudentCourseService;
import gr.aueb.sev.service.StudentCourseServiceImpl;


@WebServlet("/students/searchcourses")
public class SearchStudentCourseController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	IStudentCourseDAO studentCourseDAO = new StudentCourseDAOImpl();
	IStudentCourseService studentCourseServ = new StudentCourseServiceImpl(studentCourseDAO);
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html, charset=UTF-8");
		
		int studentId = Integer.parseInt(request.getParameter("studentId").trim());
		
		
		try {
			List<StudentCourse> studentCourses = studentCourseServ.getStudentCourseByStudentId(studentId);
			
			if(studentCourses == null) {
				request.setAttribute("studentCourseNotFound", true);
				request.getRequestDispatcher("/jsps/students/studentsmenu.jsp").forward(request, response);
				
			} else {
			    
				request.setAttribute("courses", studentCourses);
				request.getRequestDispatcher("/jsps/students/courses.jsp").forward(request, response);
			}
			
			
		} catch(SQLException e) {
			request.setAttribute("sqlError", true);
			request.getRequestDispatcher("/jsps/students/studentsmenu.jsp").forward(request, response);
			
		} 
	}

	

}
