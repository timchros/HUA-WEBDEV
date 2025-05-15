package hua.dit.web.db.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.util.Random;

import hua.dit.web.Util;

public class TestH2DB {

	private static final String USER = "sa";
	private static final String PASS = "";
	private static final String URL= "jdbc:h2:~/test";
	
	// Number of rows inserted
	private static final int n = 10;
	
	/***
	 * Creates an <b>H2</b> DB with a predefined number of rows.
	 * <ul>
	 * 		<li><b>Open Connection</b> to H2 DB</li>
	 * 		<li>DROP TABLE <code>site_users</code> (if already exists)</li>
	 * 		<li><b>CREATE</b> TABLE <code>site_users</code></li>
	 * 		<li><b>ADD</b> a few different records, using an <code>INSERT SQL</code> statement</li>
	 * 		<li><b>GET</b> existing records, using a <code>SELECT SQL</code> query</li>
	 * 		<li><b>Close Connection</b> to H2 DB</li>
	 * </ul>
	 * @throws Exception in case of a problem (i.e., interaction with the DB)
	 */
	public static void main(String[] args) throws Exception {
		System.out.println(">> TestH2DB - main() - START - " + new Date());
		System.out.println();
		
		// Ensure JDBC Driver (JAR) exist in your CLASSPATH and properly Loaded
		// https://www.h2database.com/html/download.html
		Class.forName("org.h2.Driver");
		System.out.println("H2 DB JDBC driver found !");
		
		// Open Connection
		final Connection conn = DriverManager.getConnection(URL, USER, PASS);
		System.out.println("\nConnection successfully opened ...");
		
		// Execute SQL statements that 
		// DROP TABLE (if exists) and then CREATE TABLE
		// Note that we need 64 characters for Hash Value
		final String SQL_1 =
			"DROP TABLE IF EXISTS `site_users`";
		Statement stmta = conn.createStatement(); 
		stmta.executeUpdate(SQL_1);
		stmta.close();
		final String SQL_2 = 
			"CREATE TABLE `site_users` (" + 
			"  id INTEGER NOT NULL AUTO_INCREMENT," + 
			"  username VARCHAR(50) NOT NULL," + 
			"  password_hash VARCHAR(64) NOT NULL," + 
			"  PRIMARY KEY (id)" + 
			")";
		Statement stmtb = conn.createStatement(); 
        stmtb.executeUpdate(SQL_2);
        stmtb.close();
        System.out.println("\nH2 DB Successfully Created !!!\n");
        
        // ADD n=10 DIFFERENT USERS
        int rows_affected = 0;
        for (int i = 0; i < n; i++) {
	        // Execute an INSERT SQL Query
        	// Both username and password are random
        	// In fact, the password is the random number added in the "user" string
			final String SQL_3 = "INSERT INTO site_users VALUES(default, ?, ?)";
			System.out.println("SQL: " + SQL_3);
			int intValue = new Random().nextInt(0, Integer.MAX_VALUE);
			final String username = "user" + intValue;
			final String password = "" + intValue;
			final String password_hash = Util.getHash256(password);
			System.out.println(" * DATA -> username: " + username + " , password_hash: " + password_hash);
			final PreparedStatement pst = conn.prepareStatement(SQL_3);
			pst.setString(1, username);
			pst.setString(2, password_hash);
			rows_affected += pst.executeUpdate();
			
			pst.close();
        }
        System.out.println("\nTotal Number of rows affected: " + rows_affected);
		
		// Execute a SELECT SQL Query
		final String SQL_4 = "SELECT * from site_users";
		System.out.println("\nSQL: " + SQL_4);
		final Statement st = conn.createStatement();
		final ResultSet rs = st.executeQuery(SQL_4);
		while (rs.next()) {
			int id = rs.getInt(1);
			String uname = rs.getString(2);
			String upass_hash = rs.getString(3);
			System.out.println(" - " + id + " : " + uname + " : " + upass_hash);
		}
		rs.close();
		st.close();
		
		// Close Connection
		conn.close();
		System.out.println("\nConnection successfully closed !");
		
		System.out.println();
		System.out.println(">> TestH2DB - main() - END - " + new Date());
	}
	
}
