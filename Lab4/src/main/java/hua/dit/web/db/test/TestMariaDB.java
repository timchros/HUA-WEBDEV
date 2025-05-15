package hua.dit.web.db.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.util.Random;

import hua.dit.web.Util;

public class TestMariaDB {

	// Ensure that this DB already exists ! 
	private static final String USER = "root";
	private static final String PASS = "";
	private static final String URL= "jdbc:mariadb://localhost:3306/LAB3DB";
	
	// Number of rows inserted
	private static final int n = 10;
	
	/***
	 * Interacts with an existing <b>MariaDB</b>. Every time, <i>new records</i> are inserted !
	 * <ul>
	 * 		<li><b>Open Connection</b> to Maria DB</li>
	 * 		<li><b>ADD</b> a few different records, using an <code>INSERT SQL</code> statement</li>
	 * 		<li><b>GET</b> existing records, using a <code>SELECT SQL</code> query</li>
	 * 		<li><b>Close Connection</b> to Maria DB</li>
	 * </ul>
	 * @throws Exception in case of a problem (i.e., interaction with the DB)
	 */
	public static void main(String[] args) throws Exception {
		System.out.println(">> TestMariaDB - main() - START - " + new Date());
		System.out.println();
		
		// Ensure JDBC Driver (JAR) exist in your CLASSPATH and properly Loaded
		Class.forName("org.mariadb.jdbc.Driver");
		System.out.println("Maria DB JDBC driver found !");
		
		// Open Connection - Ensure that the RDBMS is up and running !
		final Connection conn = DriverManager.getConnection(URL, USER, PASS);
		System.out.println("\nConnection successfully opened ...");

        // ADD n=10 DIFFERENT USERS
        int rows_affected = 0;
        for (int i = 0; i < n; i++) {
	        // Execute an INSERT SQL Query
        	// Both username and password are random
        	// In fact, the password is the random number added in the "user" string
			final String SQL_1 = "INSERT INTO site_users VALUES(null, ?, ?)";
			System.out.println("SQL: " + SQL_1);
			int intValue = new Random().nextInt(0, Integer.MAX_VALUE);
			final String username = "user" + intValue;
			final String password = "" + intValue;
			final String password_hash = Util.getHash256(password);
			System.out.println(" * DATA -> username: " + username + " , password_hash: " + password_hash);
			final PreparedStatement pst = conn.prepareStatement(SQL_1);
			pst.setString(1, username);
			pst.setString(2, password_hash);
			rows_affected += pst.executeUpdate();
			
			pst.close();
        }
        System.out.println("\nTotal Number of rows affected: " + rows_affected);
		
		// Execute a SELECT SQL Query
		final String SQL_2 = "SELECT * from site_users";
		System.out.println("\nSQL: " + SQL_2);
		final Statement st = conn.createStatement();
		final ResultSet rs = st.executeQuery(SQL_2);
		while (rs.next()) {
			int id = rs.getInt(1);
			String username = rs.getString(2);
			String password_hash = rs.getString(3);
			System.out.println(" - " + id + " : " + username + " : " + password_hash);
		}
		rs.close();
		st.close();
		
		// Close Connection
		conn.close();
		System.out.println("\nConnection successfully closed !");
		
		System.out.println();
		System.out.println(">> TestMariaDB - main() - END - " + new Date());
	}

}
