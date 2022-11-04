package gr.aueb.sev.service;

import java.sql.SQLException;
import java.util.List;

import gr.aueb.sev.dto.StudentCourseDTO;
import gr.aueb.sev.model.StudentCourse;
import gr.aueb.sev.service.exceptions.StudentCourseNotFoundException;

public interface IStudentCourseService {
	
	/**
	 * Inserts a {@link StudentCourse} based on the data carried by the {@link StudentCourseDTO}
	 * 
	 * @param studentCourseDTO		DTO object that contains the data.
	 * @throws SQLException		if any error happens during SQL insert.
	 */
	void insertStudentCourse(StudentCourseDTO studentCourseDTO) throws SQLException;
	
	/**
	 * Deletes a {@link StudentCourse} based on the data carried by the {@link StudentCourseDTO}
	 * 
	 * @param studentCourseDTO					DTO object that contains the data (the studentCourse's id)
	 * @throws SQLException					if any error happens during SQL delete.
	 * @throws StudentCourseNotFoundException		if any teacher, identify by their id, not found.
	 */
	void deleteStudentCourse(StudentCourseDTO studentCourseDTO) throws SQLException, StudentCourseNotFoundException;
	
	/**
	 * Updates {@link StudentCourse} based on the data carried by new studentCourseDTO,
	 * identify by their id, carried by old StudentCourseDTO.
	 * 
	 * @param oldStudentCourseDTO		DTO object that contains the data of the studentCourse to be updated.
	 * @param newStudentCourseDTO		DTO object that carries the data of the new studentCourse.
	 * @throws SQLException		if any error happens during SQL update.
	 */
	void updateStudentCourse(StudentCourseDTO oldStudentCourseDTO, StudentCourseDTO newStudentCourseDTO) throws SQLException;
	
	
	List<StudentCourse> getStudentCourseByStudentId(int studentId) throws SQLException;
	
    List<StudentCourse> getStudentCourseByCourseId(int courseId) throws SQLException;
}
