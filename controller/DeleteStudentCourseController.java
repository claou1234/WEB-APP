package gr.aueb.sev.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gr.aueb.sev.dao.IStudentCourseDAO;
import gr.aueb.sev.dao.StudentCourseDAOImpl;
import gr.aueb.sev.dto.StudentCourseDTO;
import gr.aueb.sev.service.IStudentCourseService;
import gr.aueb.sev.service.StudentCourseServiceImpl;
import gr.aueb.sev.service.exceptions.StudentCourseNotFoundException;

@WebServlet("/students/deletecourse")
public class DeleteStudentCourseController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
    
	IStudentCourseDAO studentCourseDAO = new StudentCourseDAOImpl();
	IStudentCourseService studentCourseServ = new StudentCourseServiceImpl(studentCourseDAO);
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html, charset=UTF-8");
		
		int studentId = Integer.parseInt(request.getParameter("studentId").trim());
		int courseId = Integer.parseInt(request.getParameter("courseId").trim());
		
		// Validation
		
		StudentCourseDTO studentCourseDTO = new StudentCourseDTO();
		studentCourseDTO.setStudentId(studentId);
		studentCourseDTO.setCourseId(courseId);
		
		try {
			studentCourseServ.deleteStudentCourse(studentCourseDTO);
			
			request.setAttribute("courses", studentCourseDTO);
			request.getRequestDispatcher("/jsps/students/coursedeleted.jsp").forward(request, response);
			
		} catch(SQLException e) {
			request.setAttribute("deleteAPIError", true);
			request.getRequestDispatcher("/jsps/students/courses.jsp").forward(request, response);
			
		} catch(StudentCourseNotFoundException e) {
			request.setAttribute("coursesNotFound", true);
			request.getRequestDispatcher("/jsps/students/courses.jsp").forward(request, response);
		}
	}

}
