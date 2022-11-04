package gr.aueb.sev.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import gr.aueb.sev.model.Teacher;
import static gr.aueb.sev.dao.dbutil.DBUtil.getConnection;
import static gr.aueb.sev.dao.dbutil.DBUtil.closeConnection;

public class TeacherDAOImpl implements ITeacherDAO {

	@Override
	public void insert(Teacher teacher) throws SQLException {
		PreparedStatement pst = null;
		Connection conn = getConnection();
		
		try {
			String sql = "INSERT INTO TEACHERS (FIRSTNAME, LASTNAME) VALUES (?, ?)";
			// +"'" + teacher.getFirstname() + "', " + "'" + teacher.getLastname() + "'";
			
			
			pst = conn.prepareStatement(sql);
			pst.setString(1, teacher.getFirstname());
			pst.setString(2, teacher.getLastname());
			
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
	public Teacher delete(Teacher teacher) throws SQLException {
		PreparedStatement pst = null;
		Connection conn = getConnection();
		
		try {
			String sql = "DELETE FROM TEACHERS WHERE ID = ?";
			// +"'" + teacher.getFirstname() + "', " + "'" + teacher.getLastname() + "'";
			
			
			pst = conn.prepareStatement(sql);
			pst.setInt(1, teacher.getId());
			
			
			int n = pst.executeUpdate();
			if (n == 0) {
				return null;
			} else {
				return teacher;
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
	public void update(Teacher oldTeacher, Teacher newTeacher) throws SQLException {
		PreparedStatement pst = null;
		Connection conn = getConnection();
		
		try {
			String sql = "UPDATE TEACHERS SET FIRSTNAME = ?, LASTNAME = ? WHERE ID = ?";
			// +"'" + teacher.getFirstname() + "', " + "'" + teacher.getLastname() + "'";
			
			
			pst = conn.prepareStatement(sql);
			pst.setString(1, newTeacher.getFirstname());
			pst.setString(2, newTeacher.getLastname());
			pst.setInt(3, oldTeacher.getId());
			
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
	public List<Teacher> getTeachersByLastname(String lastname) throws SQLException {
		PreparedStatement pst = null;
		Connection conn = getConnection();
		List<Teacher> teachers = new ArrayList<>();
		ResultSet rs = null;
		
		try {
			String sql = "SELECT ID, FIRSTNAME, LASTNAME FROM TEACHERS WHERE LASTNAME LIKE ?";
			// +"'" + teacher.getFirstname() + "', " + "'" + teacher.getLastname() + "'";
			
			
			pst = conn.prepareStatement(sql);
			pst.setString(1, lastname + "%");
			
			rs = pst.executeQuery();
			
			while (rs.next()) {
				Teacher teacher = new Teacher();
				teacher.setId(rs.getInt("ID"));
				teacher.setFirstname(rs.getString("FIRSTNAME"));
				teacher.setLastname(rs.getString("LASTNAME"));
				
				teachers.add(teacher);
				
			}
			
			return (teachers.size() > 0) ? teachers : null;
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (pst != null) pst.close();
			if (conn != null) closeConnection(); 
		}
		
	}

	@Override
	public Teacher getTeacherById(int id) throws SQLException {
		PreparedStatement pst = null;
		Connection conn = getConnection();
		ResultSet rs = null;
		Teacher teacher = null;
		
		try {
			String sql = "SELECT ID, FIRSTNAME, LASTNAME FROM TEACHERS WHERE ID = ?";
			// +"'" + teacher.getFirstname() + "', " + "'" + teacher.getLastname() + "'";
			
			
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			
			rs = pst.executeQuery();
			
			if (rs.next()) {
				teacher = new Teacher();
				teacher.setId(rs.getInt("ID"));
				teacher.setFirstname(rs.getString("FIRSTNAME"));
				teacher.setLastname(rs.getString("LASTNAME"));
				
			}
			return teacher;
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (pst != null) pst.close();
			if (conn != null) closeConnection(); 
		}
		
	}

}
