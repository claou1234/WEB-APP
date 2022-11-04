package gr.aueb.sev.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gr.aueb.sev.dao.ICourseDAO;
import gr.aueb.sev.dao.CourseDAOImpl;
import gr.aueb.sev.dto.CourseDTO;
import gr.aueb.sev.service.ICourseService;
import gr.aueb.sev.service.CourseServiceImpl;

@WebServlet("/course/insert")
public class InsertCourseController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	ICourseDAO courseDAO = new CourseDAOImpl();
	ICourseService courseServ = new CourseServiceImpl(courseDAO);
	
  
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html, charset=UTF-8");
		
		String description = request.getParameter("description").trim();
		int teacherId = Integer.parseInt(request.getParameter("teacherId").trim());
		
		CourseDTO courseDTO = new CourseDTO();
		courseDTO.setDescription(description);
		courseDTO.setTeacherId(teacherId);
		
		try {
			courseServ.insertCourse(courseDTO);
			request.setAttribute("insertedCourse", courseDTO);
			request.getRequestDispatcher("/jsps/courses/courseinserted.jsp").forward(request, response);
		} catch (SQLException e) {
			request.setAttribute("sqlError", true);
			request.getRequestDispatcher("/jsps/courses/coursesmenu.jsp").forward(request, response);
		}

	}

}
