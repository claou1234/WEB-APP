package gr.aueb.sev.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import gr.aueb.sev.model.Student;
import static gr.aueb.sev.dao.dbutil.DBUtil.getConnection;
import static gr.aueb.sev.dao.dbutil.DBUtil.closeConnection;

public class StudentDAOImpl implements IStudentDAO {

	@Override
	public void insert(Student student) throws SQLException {
		PreparedStatement pst = null;
		Connection conn = getConnection();
		
		try {
			String sql = "INSERT INTO STUDENTS (FIRSTNAME, LASTNAME) VALUES (?, ?)";
			// +"'" + teacher.getFirstname() + "', " + "'" + teacher.getLastname() + "'";
			
			
			pst = conn.prepareStatement(sql);
			pst.setString(1, student.getFirstname());
			pst.setString(2, student.getLastname());
			
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
	public Student delete(Student student) throws SQLException {
		PreparedStatement pst = null;
		Connection conn = getConnection();
		
		try {
			String sql = "DELETE FROM STUDENTS WHERE ID = ?";
			// +"'" + teacher.getFirstname() + "', " + "'" + teacher.getLastname() + "'";
			
			
			pst = conn.prepareStatement(sql);
			pst.setInt(1, student.getId());
			
			
			int n = pst.executeUpdate();
			if (n == 0) {
				return null;
			} else {
				return student;
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
	public void update(Student oldStudent, Student newStudent) throws SQLException {
		PreparedStatement pst = null;
		Connection conn = getConnection();
		
		try {
			String sql = "UPDATE STUDENTS SET FIRSTNAME = ?, LASTNAME = ? WHERE ID = ?";
			// +"'" + teacher.getFirstname() + "', " + "'" + teacher.getLastname() + "'";
			
			
			pst = conn.prepareStatement(sql);
			pst.setString(1, newStudent.getFirstname());
			pst.setString(2, newStudent.getLastname());
			pst.setInt(3, oldStudent.getId());
			
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
	public List<Student> getStudentsByLastname(String lastname) throws SQLException {
		PreparedStatement pst = null;
		Connection conn = getConnection();
		List<Student> students = new ArrayList<>();
		ResultSet rs = null;
		
		try {
			String sql = "SELECT ID, FIRSTNAME, LASTNAME FROM STUDENTS WHERE LASTNAME LIKE ?";
			// +"'" + student.getFirstname() + "', " + "'" + student.getLastname() + "'";
			
			
			pst = conn.prepareStatement(sql);
			pst.setString(1, lastname + "%");
			
			rs = pst.executeQuery();
			
			while (rs.next()) {
				Student student = new Student();
				student.setId(rs.getInt("ID"));
				student.setFirstname(rs.getString("FIRSTNAME"));
				student.setLastname(rs.getString("LASTNAME"));
				
				students.add(student);
				
			}
			
			return (students.size() > 0) ? students : null;
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (pst != null) pst.close();
			if (conn != null) closeConnection(); 
		}
		
	}

	@Override
	public Student getStudentById(int id) throws SQLException {
		PreparedStatement pst = null;
		Connection conn = getConnection();
		ResultSet rs = null;
		Student student = null;
		
		try {
			String sql = "SELECT ID, FIRSTNAME, LASTNAME FROM STUDENTS WHERE ID = ?";
			// +"'" + student.getFirstname() + "', " + "'" + student.getLastname() + "'";
			
			
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			
			rs = pst.executeQuery();
			
			if (rs.next()) {
				student = new Student();
				student.setId(rs.getInt("ID"));
				student.setFirstname(rs.getString("FIRSTNAME"));
				student.setLastname(rs.getString("LASTNAME"));
				
			}
			return student;
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (pst != null) pst.close();
			if (conn != null) closeConnection(); 
		}
		
	}

}
