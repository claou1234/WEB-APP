package gr.aueb.sev.service;

import java.sql.SQLException;
import java.util.List;

import gr.aueb.sev.dto.CourseDTO;
import gr.aueb.sev.model.Course;
import gr.aueb.sev.service.exceptions.CourseNotFoundException;

public interface ICourseService {
	
	/**
	 * Inserts a {@link Course} based on the data carried by the {@link CourseDTO}
	 * 
	 * @param courseDTO		DTO object that contains the data.
	 * @throws SQLException		if any error happens during SQL insert.
	 */
	void insertCourse(CourseDTO courseDTO) throws SQLException;
	
	/**
	 * Deletes a {@link Course} based on the data carried by the {@link CourseDTO}
	 * 
	 * @param courseDTO					DTO object that contains the data (the course's id)
	 * @throws SQLException					if any error happens during SQL delete.
	 * @throws CourseNotFoundException		if any teacher, identify by their id, not found.
	 */
	void deleteCourse(CourseDTO courseDTO) throws SQLException, CourseNotFoundException;
	
	/**
	 * Updates {@link Course} based on the data carried by new courseDTO,
	 * identify by their id, carried by old CourseDTO.
	 * 
	 * @param oldCourseDTO		DTO object that contains the data of the course to be updated.
	 * @param newCourseDTO		DTO object that carries the data of the new course.
	 * @throws SQLException		if any error happens during SQL update.
	 */
	void updateCourse(CourseDTO oldCourseDTO, CourseDTO newCourseDTO) throws SQLException;
	
	/**
	 * Gets back to the caller a list of the {@link Course} objects identified by their last name.
	 * 
	 * @param description			a string object that contains the initial letters of course's description.
	 * @return					a list that contains the results of the called method or empty list if results not found.
	 * @throws SQLException		if any error happens during the SQL search.
	 */
	List<Course> getCourseByDescription(String description) throws SQLException;
	
	/**
	 * Get back the teacher identified by their id.
	 * 
	 * @param id 							Course's id.
	 * @return								The course object, otherwise null if the course.
	 * @throws SQLException					If any error happens during SQL search.
	 * @throws CourseNotFoundException		
	 */
	Course getCourseById(int id) throws SQLException, CourseNotFoundException;
}
