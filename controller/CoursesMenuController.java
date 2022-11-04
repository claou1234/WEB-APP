package gr.aueb.sev.controller;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/contact")
public class CoursesMenuController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	    response.setContentType("text/html, charset=UTF-8");

	    request.getRequestDispatcher("/jsps/contactUs.jsp").forward(request, response);

	    
	}

}
