package gr.aueb.sev.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import gr.aueb.sev.model.StudentCourse;
import static gr.aueb.sev.dao.dbutil.DBUtil.getConnection;
import static gr.aueb.sev.dao.dbutil.DBUtil.closeConnection;

public class StudentCourseDAOImpl implements IStudentCourseDAO {

	@Override
	public void insert(StudentCourse studentCourse) throws SQLException {
		PreparedStatement pst = null;
		Connection conn = getConnection();
		
		try {
			String sql = "INSERT INTO STUDENTS_COURSES (STUDENT_ID, COURSE_ID) VALUES (?, ?)";
			// +"'" + teacher.getFirstname() + "', " + "'" + teacher.getLastname() + "'";
			
			
			pst = conn.prepareStatement(sql);
			pst.setInt(1, studentCourse.getStudentId());
			pst.setInt(2, studentCourse.getCourseId());
			
			pst.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (pst != null) pst.close();
			if (conn != null) closeConnection(); 
		}
	}

	@Override
	public StudentCourse delete(StudentCourse studentCourse) throws SQLException {
		PreparedStatement pst = null;
		Connection conn = getConnection();
		
		try {
			String sql = "DELETE FROM STUDENTS_COURSES WHERE STUDENT_ID = ? AND COURSE_ID = ?";
			// +"'" + teacher.getFirstname() + "', " + "'" + teacher.getLastname() + "'";
			
			
			pst = conn.prepareStatement(sql);
			pst.setInt(1, studentCourse.getStudentId());
			pst.setInt(2, studentCourse.getCourseId());
			
			
			int n = pst.executeUpdate();
			if (n == 0) {
				return null;
			} else {
				return studentCourse;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
			
		} finally {
			if (pst != null) pst.close();
			if (conn != null) closeConnection(); 
			
		}
		
	}

	@Override
	public void update(StudentCourse oldStudentCourse, StudentCourse newStudentCourse) throws SQLException {
		PreparedStatement pst = null;
		Connection conn = getConnection();
		
		try {
			String sql = "UPDATE STUDENTS_COURSES SET STUDENT_ID = ?, COURSE_ID = ? WHERE STUDENT_ID = ? AND COURSE_ID = ?";
			// +"'" + teacher.getFirstname() + "', " + "'" + teacher.getLastname() + "'";
			
			
			pst = conn.prepareStatement(sql);
			pst.setInt(1, newStudentCourse.getStudentId());
			pst.setInt(2, newStudentCourse.getCourseId());
			pst.setInt(3, oldStudentCourse.getStudentId());
			pst.setInt(4, oldStudentCourse.getCourseId());
			
			pst.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (pst != null) pst.close();
			if (conn != null) closeConnection(); 
		}
		
	}

	@Override
	public List<StudentCourse> getStudentCoursesByStudentId(int studentId) throws SQLException {
		PreparedStatement pst = null;
		Connection conn = getConnection();
		List<StudentCourse> studentCourses = new ArrayList<>();
		ResultSet rs = null;
		
		try {
			String sql = "SELECT STUDENT_ID, COURSE_ID FROM STUDENTS_COURSES WHERE STUDENT_ID = ?";
			// +"'" + teacher.getFirstname() + "', " + "'" + teacher.getLastname() + "'";
			
			
			pst = conn.prepareStatement(sql);
			pst.setInt(1, studentId);
			
			rs = pst.executeQuery();
			
			while (rs.next()) {
				StudentCourse studentCourse = new StudentCourse();
				studentCourse.setStudentId(rs.getInt("STUDENT_ID"));
				studentCourse.setCourseId(rs.getInt("COURSE_ID"));
				
				studentCourses.add(studentCourse);
				
			}
			
			return (studentCourses.size() > 0) ? studentCourses : null;
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (pst != null) pst.close();
			if (conn != null) closeConnection(); 
		}
		
	}

	@Override
	public List<StudentCourse> getStudentCoursesByCourseId(int courseId) throws SQLException {
        PreparedStatement pst = null;
        Connection conn = getConnection();
        List<StudentCourse> studentCourses = new ArrayList<>();
        ResultSet rs = null;
        
        try {
            String sql = "SELECT STUDENT_ID, COURSE_ID FROM STUDENTS_COURSES WHERE COURSE_ID = ?";
            // +"'" + teacher.getFirstname() + "', " + "'" + teacher.getLastname() + "'";
            
            
            pst = conn.prepareStatement(sql);
            pst.setInt(1, courseId);
            
            rs = pst.executeQuery();
            
            while (rs.next()) {
                StudentCourse studentCourse = new StudentCourse();
                studentCourse.setStudentId(rs.getInt("STUDENT_ID"));
                studentCourse.setCourseId(rs.getInt("COURSE_ID"));
                
                studentCourses.add(studentCourse);
                
            }
            
            return (studentCourses.size() > 0) ? studentCourses : null;
            
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (pst != null) pst.close();
            if (conn != null) closeConnection(); 
        }
        
    }

}
