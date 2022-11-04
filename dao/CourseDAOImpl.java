package gr.aueb.sev.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import gr.aueb.sev.model.Course;
import static gr.aueb.sev.dao.dbutil.DBUtil.getConnection;
import static gr.aueb.sev.dao.dbutil.DBUtil.closeConnection;

public class CourseDAOImpl implements ICourseDAO {

	@Override
	public void insert(Course course) throws SQLException {
		PreparedStatement pst = null;
		Connection conn = getConnection();
		
		try {
			String sql = "INSERT INTO COURSES (DESCRIPTION, TEACHER_ID) VALUES (?, ?)";
			// +"'" + teacher.getFirstname() + "', " + "'" + teacher.getLastname() + "'";
			
			
			pst = conn.prepareStatement(sql);
			pst.setString(1, course.getDescription());
			pst.setInt(2, course.getTeacherId());
			
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
	public Course delete(Course course) throws SQLException {
		PreparedStatement pst = null;
		Connection conn = getConnection();
		
		try {
			String sql = "DELETE FROM COURSES WHERE ID = ?";
			// +"'" + teacher.getFirstname() + "', " + "'" + teacher.getLastname() + "'";
			
			
			pst = conn.prepareStatement(sql);
			pst.setInt(1, course.getId());
			
			
			int n = pst.executeUpdate();
			if (n == 0) {
				return null;
			} else {
				return course;
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
	public void update(Course oldCourse, Course newCourse) throws SQLException {
		PreparedStatement pst = null;
		Connection conn = getConnection();
		
		try {
			String sql = "UPDATE COURSES SET DESCRIPTION = ?, TEACHER_ID = ? WHERE ID = ?";
			// +"'" + teacher.getFirstname() + "', " + "'" + teacher.getLastname() + "'";
			
			
			pst = conn.prepareStatement(sql);
			pst.setString(1, newCourse.getDescription());
			pst.setInt(2, newCourse.getTeacherId());
			pst.setInt(3, oldCourse.getId());
			
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
	public List<Course> getCoursesByDescription(String description) throws SQLException {
		PreparedStatement pst = null;
		Connection conn = getConnection();
		List<Course> courses = new ArrayList<>();
		ResultSet rs = null;
		
		try {
			String sql = "SELECT ID, DESCRIPTION, TEACHER_ID FROM COURSES WHERE DESCRIPTION LIKE ?";
			// +"'" + student.getFirstname() + "', " + "'" + student.getLastname() + "'";
			
			
			pst = conn.prepareStatement(sql);
			pst.setString(1, description + "%");
			
			rs = pst.executeQuery();
			
			while (rs.next()) {
				Course course = new Course();
				course.setId(rs.getInt("ID"));
				course.setDescription(rs.getString("DESCRIPTION"));
				course.setTeacherId(rs.getInt("TEACHER_ID"));
				
				courses.add(course);
				
			}
			
			return (courses.size() > 0) ? courses : null;
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (pst != null) pst.close();
			if (conn != null) closeConnection(); 
		}
		
	}

	@Override
	public Course getCourseById(int id) throws SQLException {
		PreparedStatement pst = null;
		Connection conn = getConnection();
		ResultSet rs = null;
		Course course = null;
		
		try {
			String sql = "SELECT ID, DESCRIPTION, TEACHER_ID FROM COURSES WHERE ID = ?";
			// +"'" + student.getFirstname() + "', " + "'" + student.getLastname() + "'";
			
			
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			
			rs = pst.executeQuery();
			
			if (rs.next()) {
				course = new Course();
				course.setId(rs.getInt("ID"));
				course.setDescription(rs.getString("DESCRIPTION"));
				course.setTeacherId(rs.getInt("TEACHER_ID"));
				
			}
			return course;
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (pst != null) pst.close();
			if (conn != null) closeConnection(); 
		}
		
	}

}
