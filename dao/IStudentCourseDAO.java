package gr.aueb.sev.dao;

import java.sql.SQLException;
import java.util.List;

import gr.aueb.sev.model.StudentCourse;

public interface IStudentCourseDAO {
	void insert(StudentCourse studentCourse) throws SQLException;
	StudentCourse delete(StudentCourse studentCourse) throws SQLException;
	void update(StudentCourse oldStudentCourse, StudentCourse newStudentCourse) throws SQLException;
	List<StudentCourse> getStudentCoursesByStudentId(int studentId) throws SQLException;
	List<StudentCourse> getStudentCoursesByCourseId(int courseId) throws SQLException;

}
