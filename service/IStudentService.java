package gr.aueb.sev.service;

import java.sql.SQLException;
import java.util.List;

import gr.aueb.sev.dto.StudentDTO;
import gr.aueb.sev.model.Student;
import gr.aueb.sev.service.exceptions.StudentNotFoundException;

public interface IStudentService {
	
	/**
	 * Inserts a {@link Student} based on the data carried by the {@link StudentDTO}
	 * 
	 * @param studentDTO		DTO object that contains the data.
	 * @throws SQLException		if any error happens during SQL insert.
	 */
	void insertStudent(StudentDTO studentDTO) throws SQLException;
	
	/**
	 * Deletes a {@link Student} based on the data carried by the {@link StudentDTO}
	 * 
	 * @param studentDTO					DTO object that contains the data (the student's id)
	 * @throws SQLException					if any error happens during SQL delete.
	 * @throws StudentNotFoundException		if any teacher, identify by their id, not found.
	 */
	void deleteStudent(StudentDTO studentDTO) throws SQLException, StudentNotFoundException;
	
	/**
	 * Updates {@link Student} based on the data carried by new studentDTO,
	 * identify by their id, carried by old StudentDTO.
	 * 
	 * @param oldStudentDTO		DTO object that contains the data of the student to be updated.
	 * @param newStudentDTO		DTO object that carries the data of the new student.
	 * @throws SQLException		if any error happens during SQL update.
	 */
	void updateStudent(StudentDTO oldStudentDTO, StudentDTO newStudentDTO) throws SQLException;
	
	/**
	 * Gets back to the caller a list of the {@link Student} objects identified by their last name.
	 * 
	 * @param lastname			a string object that contains the initial letters of student's last name.
	 * @return					a list that contains the results of the called method or empty list if results not found.
	 * @throws SQLException		if any error happens during the SQL search.
	 */
	List<Student> getStudentByLastname(String lastname) throws SQLException;
	
	/**
	 * Get back the teacher identified by their id.
	 * 
	 * @param id 							Student's id.
	 * @return								The student object, otherwise null if the student.
	 * @throws SQLException					If any error happens during SQL search.
	 * @throws StudentNotFoundException		
	 */
	Student getStudentById(int id) throws SQLException, StudentNotFoundException;
}
