package gr.aueb.sev.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gr.aueb.sev.dao.IStudentDAO;
import gr.aueb.sev.dao.StudentDAOImpl;
import gr.aueb.sev.dto.StudentDTO;
import gr.aueb.sev.service.IStudentService;
import gr.aueb.sev.service.StudentServiceImpl;

@WebServlet("/students/insert")
public class InsertStudentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	IStudentDAO teacherDAO = new StudentDAOImpl();
	IStudentService teacherServ = new StudentServiceImpl(teacherDAO);
	
  
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html, charset=UTF-8");
		
		String firstname = request.getParameter("firstname").trim();
		String lastname = request.getParameter("lastname").trim();
		
		StudentDTO teacherDTO = new StudentDTO();
		teacherDTO.setFirstname(firstname);
		teacherDTO.setLastname(lastname);
		
		try {
			teacherServ.insertStudent(teacherDTO);
			request.setAttribute("insertedStudent", teacherDTO);
			request.getRequestDispatcher("/jsps/students/studentinserted.jsp").forward(request, response);
		} catch (SQLException e) {
			request.setAttribute("sqlError", true);
			request.getRequestDispatcher("/jsps/students/studentsmenu.jsp").forward(request, response);
		}

	}

}
