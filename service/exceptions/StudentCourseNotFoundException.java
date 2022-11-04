package gr.aueb.sev.service.exceptions;

import gr.aueb.sev.model.StudentCourse;

public class StudentCourseNotFoundException extends Exception{
	
	private static final long serialVersionUID = 1L;
	
	public StudentCourseNotFoundException(StudentCourse studentCourse) {
		super("Student with id = " + studentCourse.getStudentId() + " was not found");
	}

}
