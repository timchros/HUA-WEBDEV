package hua.dit.web.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public final class LabDb {

	private static String sql_autogen_field = "null";
	
/* 
	// FOR MARIA DB
	
	private static final String USER = "root";
	private static final String PASS = "";
	private static final String URL= "jdbc:mariadb://localhost:3306/LAB3DB";
	
	static {
        try {
        	Class.forName("org.mariadb.jdbc.Driver");
        } catch (Throwable t) {
			throw new RuntimeException("Cannot find Maria DB JDBC driver", t);
		}
	}
	
*/
	
	// FOR H2 DB 
	
	private static final String USER = "sa";
	private static final String PASS = "";
	private static final String URL= "jdbc:h2:~/test";
			
	static {
        try {
        	Class.forName("org.h2.Driver");
        	sql_autogen_field = "default";
        } catch (Throwable t) {
			throw new RuntimeException("Cannot find H2 DB JDBC driver", t);
		}
	}
		
	// We can possibly accelerate the execution of these using the same connection each time
	// The methods should remain synchronized and the connection should not close (in this approach)
	// Nevertheless we should ensure that the connection remains open ( server may close it on its own ! )
	
	private static final String INSERT_SQL = "INSERT INTO site_users VALUES (" + sql_autogen_field + ", ?, ?)";
	
	public static synchronized boolean insertUserData(String username, String passwordHash) {
		
		System.out.println("Method invoked: insertUserData(...)");
		
		try {
			// Open Connection
			final Connection conn = DriverManager.getConnection(URL, USER, PASS);
			
			// Create PreparedStatement and Set missing value 
			final PreparedStatement pst = conn.prepareStatement(INSERT_SQL);
			pst.setString(1, username);
			pst.setString(2, passwordHash);
			
			// Execute update (DELETE SQL query)
			int rows_affected = pst.executeUpdate();
			
			// Close PreparedStatement
			pst.close();
			
			// Close Connection
			conn.close();
			
			return (rows_affected == 1);
			
		} catch (Throwable t) {
			throw new RuntimeException("insertUserData(...) problem !", t);
		}	
		
	} // END OF insertUserData(..)
	
	
	private static final String GET_ALL_USERS_SQL = "SELECT * from site_users";
	
	public static synchronized List<User> getUsers() {
		
		System.out.println("Method invoked: getUsers()");
		
		try {
			final List<User> userList = new ArrayList<User>();
			
			// Open Connection
			final Connection conn = DriverManager.getConnection(URL, USER, PASS);
			
			// Create Statement and then Execute SELECT SQL query
			final Statement st = conn.createStatement();
			final ResultSet rs = st.executeQuery(GET_ALL_USERS_SQL);
			
			// Get ROWs data and store them to Java Objects
			while (rs.next()) {
				int id = rs.getInt(1);
				String username = rs.getString(2);
				String password = rs.getString(3);
				userList.add( new User(id, username, password) );
			}
			
			// Close ResultSet and Statement
			rs.close();
			st.close();
			
			// Close Connection
			conn.close();
		
			return userList;
			
		} catch (Throwable t) {
			throw new RuntimeException("getUsers() problem !", t);
		}
		
	} // END OF getUsers()
	
	
	private static final String GET_USER_SQL = "SELECT * from site_users WHERE id = ?";
	
	public static synchronized User getUser(int uid) {
		
		System.out.println("Method invoked: getUser(" + uid + ")");
		
		try {
			// Open Connection
			final Connection conn = DriverManager.getConnection(URL, USER, PASS);
			
			// Create PreparedStatement and Set missing value 
			final PreparedStatement pst = conn.prepareStatement(GET_USER_SQL);
			pst.setInt(1, uid);
			
			// Execute update (SELECT SQL query)
			final ResultSet rs = pst.executeQuery();
			
			// Get ROW data and store them to Java Objects
			User user = null;
			if (rs.next()) {
				int id = rs.getInt(1);
				String username = rs.getString(2);
				String password = rs.getString(3);
				user = new User(id, username, password);
			}
			
			// Close ResultSet and Statement
			rs.close();
			pst.close();
			
			// Close Connection
			conn.close();
		
			return user;
			
		} catch (Throwable t) {
			throw new RuntimeException("getUser(...) problem !", t);
		}
		
	} // END OF getUser(..)
	
	
	private static final String DELETE_USER_SQL = "DELETE FROM site_users WHERE id = ?";
	
	public static synchronized boolean deleteUser(int uid) {
		System.out.println("Method invoked: deleteUser(" + uid + ")");
		
		try {
			// Open Connection
			final Connection conn = DriverManager.getConnection(URL, USER, PASS);
			
			// Create PreparedStatement and Set missing value 
			final PreparedStatement pst = conn.prepareStatement(DELETE_USER_SQL);
			pst.setInt(1, uid);
			
			// Execute update (DELETE SQL query)
			int rows_affected = pst.executeUpdate();
			
			// Close PreparedStatement
			pst.close();
			
			// Close Connection
			conn.close();
			
			return (rows_affected == 1);
			
		} catch (Throwable t) {
			throw new RuntimeException("getUsers() problem !", t);
		}		
		
	} // END OF deleteUser(..)
	
}
