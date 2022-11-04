package gr.aueb.sev.service.exceptions;

import gr.aueb.sev.model.Student;

public class StudentNotFoundException extends Exception{
	
	private static final long serialVersionUID = 1L;
	
	public StudentNotFoundException(Student student) {
		super("Student with id = " + student.getId() + " was not found");
	}

}
