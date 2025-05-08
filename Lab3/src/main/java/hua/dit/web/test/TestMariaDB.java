package hua.dit.web.test;

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
	
	public static void main(String[] args) throws Exception {
		System.out.println(">> TestMariaDB - main() - START - " + new Date());
		System.out.println();
		
		// Ensure JDBC Driver (JAR) exist in your CLASSPATH and properly Loaded
		Class.forName("org.mariadb.jdbc.Driver");
		System.out.println("Maria DB JDBC driver found !");
		
		// Open Connection - Ensure that the RDBMS is up and running !
		final Connection conn = DriverManager.getConnection(URL, USER, PASS);
		System.out.println("\nConnection successfully opened ...");
		
		// Execute a SELECT SQL Query
		final String SQL_1 = "SELECT * from site_users";
		System.out.println("\nSQL: " + SQL_1);
		final Statement st = conn.createStatement();
		final ResultSet rs = st.executeQuery(SQL_1);
		while (rs.next()) {
			int id = rs.getInt(1);
			String username = rs.getString(2);
			String password_hash = rs.getString(3);
			System.out.println(" - " + id + " : " + username + " : " + password_hash);
		}
		rs.close();
		st.close();
		
		// Execute an INSERT SQL Query
		final String SQL_2 = "INSERT INTO site_users VALUES(null, ?, ?)";
		System.out.println("\nSQL: " + SQL_2);
		int intValue = new Random().nextInt(0, Integer.MAX_VALUE);
		final String username = "user" + intValue;
		final String password = "" + intValue;
		final String password_hash = Util.getHash256(password);
		System.out.println(" * DATA -> username: " + username + " , password_hash: " + password_hash);
		final PreparedStatement pst = conn.prepareStatement(SQL_2);
		pst.setString(1, username);
		pst.setString(2, password_hash);
		int rows_affected = pst.executeUpdate();
		System.out.println("\nNumber of rows affected: " + rows_affected);
		pst.close();
		
		// Close Connection
		conn.close();
		System.out.println("\nConnection successfully closed !");
		
		System.out.println();
		System.out.println(">> TestMariaDB - main() - END - " + new Date());
	}

}
