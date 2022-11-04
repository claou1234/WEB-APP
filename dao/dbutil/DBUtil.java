package gr.aueb.sev.dao.dbutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
	
	private static Connection conn;
	
	/**
	 * No instances of this class should be available
	 */
	private DBUtil() {
		
	}
	
	public static Connection getConnection() throws SQLException{
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			String url = "jdbc:mysql://localhost:3306/FinalTeachersDB?serverTimezone=UTC";
			String username = "root";
			String password = "claou1312";
			
			conn = DriverManager.getConnection(url, username, password);
			return conn;
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (Exception e) {
			return null;
		}
	}
	
	public static void closeConnection() throws SQLException {
		conn.close();
	}

}
