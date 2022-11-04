package gr.aueb.sev.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gr.aueb.sev.dao.ITeacherDAO;
import gr.aueb.sev.dao.TeacherDAOImpl;
import gr.aueb.sev.dto.TeacherDTO;
import gr.aueb.sev.service.ITeacherService;
import gr.aueb.sev.service.TeacherServiceImpl;
import gr.aueb.sev.service.exceptions.TeacherNotFoundException;

@WebServlet("/teachers/delete")
public class DeleteTeacherController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
    
	ITeacherDAO teacherDAO = new TeacherDAOImpl();
	ITeacherService teacherServ = new TeacherServiceImpl(teacherDAO);
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html, charset=UTF-8");
		
		int id = Integer.parseInt(request.getParameter("id").trim());
		String firstname = request.getParameter("firstname").trim();
		String lastname = request.getParameter("lastname").trim();
		
		// Validation
		
		TeacherDTO teacherDTO = new TeacherDTO();
		teacherDTO.setId(id);
		teacherDTO.setFirstname(firstname);
		teacherDTO.setLastname(lastname);
		
		try {
			teacherServ.deleteTeacher(teacherDTO);
			
			request.setAttribute("teacher", teacherDTO);
			request.getRequestDispatcher("/jsps/teachers/teacherdeleted.jsp").forward(request, response);
			
		} catch(SQLException e) {
			request.setAttribute("deleteAPIError", true);
			request.getRequestDispatcher("/jsps/teachers/teachers.jsp").forward(request, response);
			
		} catch(TeacherNotFoundException e) {
			request.setAttribute("dteacherNotFound", true);
			request.getRequestDispatcher("/jsps/teachers/teachers.jsp").forward(request, response);
		}
	}

}
