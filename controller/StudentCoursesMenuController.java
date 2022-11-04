package gr.aueb.sev.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gr.aueb.sev.dao.CourseDAOImpl;
import gr.aueb.sev.dao.ICourseDAO;
import gr.aueb.sev.dao.IStudentCourseDAO;
import gr.aueb.sev.dao.IStudentDAO;
import gr.aueb.sev.dao.ITeacherDAO;
import gr.aueb.sev.dao.StudentCourseDAOImpl;
import gr.aueb.sev.dao.StudentDAOImpl;
import gr.aueb.sev.dao.TeacherDAOImpl;
import gr.aueb.sev.model.Course;
import gr.aueb.sev.model.Teacher;
import gr.aueb.sev.service.CourseServiceImpl;
import gr.aueb.sev.service.ICourseService;
import gr.aueb.sev.service.IStudentCourseService;
import gr.aueb.sev.service.IStudentService;
import gr.aueb.sev.service.ITeacherService;
import gr.aueb.sev.service.StudentCourseServiceImpl;
import gr.aueb.sev.service.StudentServiceImpl;
import gr.aueb.sev.service.TeacherServiceImpl;
import gr.aueb.sev.service.exceptions.TeacherNotFoundException;

@WebServlet("/students/courses")
public class StudentCoursesMenuController extends HttpServlet {
    private static final long serialVersionUID = 1L;
       
    ICourseDAO courseDAO = new CourseDAOImpl();
    ICourseService courseServ = new CourseServiceImpl(courseDAO);
    
    IStudentCourseDAO studentCourseDAO = new StudentCourseDAOImpl();
    IStudentCourseService studentCourseServ = new StudentCourseServiceImpl(studentCourseDAO);
    
    ITeacherDAO teacherDAO = new TeacherDAOImpl();
    ITeacherService teacherServ = new TeacherServiceImpl(teacherDAO);
    
    IStudentDAO studentDAO = new StudentDAOImpl();
    IStudentService studentServ = new StudentServiceImpl(studentDAO);
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        response.setContentType("text/html, charset=UTF-8");
        int studentId = Integer.parseInt(request.getParameter("studentId").trim());
        
        try {
            List<Course> courses = courseServ.getCourseByDescription("");
            List<Teacher> teachers = new ArrayList<>();
            List<Integer> status = new ArrayList<>();
            
            if(courses == null) {
                request.setAttribute("courseNotFound", true);
                request.getRequestDispatcher("/jsps/students/students.jsp").forward(request, response);
                
            } else {
                for(Course course : courses) { 
                    try {
                        teachers.add(teacherServ.getTeacherById(course.getTeacherId()));
                        if((studentCourseServ.getStudentCourseByStudentId(studentId) != null) && (studentCourseServ.getStudentCourseByCourseId(course.getId()) != null)) {
                            status.add(0);
                            }
                        else {
                            status.add(1);
                        }
                    } catch (TeacherNotFoundException e) {
                        e.printStackTrace();
                    }
                    

                }
                request.setAttribute("status", status);
                request.setAttribute("studentId", studentId);
                request.setAttribute("teachers", teachers);
                request.setAttribute("courses", courses);
                request.getRequestDispatcher("/jsps/students/courses.jsp").forward(request, response);
            }
            
            
        } catch(SQLException e) {
            request.setAttribute("sqlError", true);
            request.getRequestDispatcher("/jsps/students/courses.jsp").forward(request, response);
            
        } 
    }

}
