package hua.dit.web.test;

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
		
		// Execute SQL statements that CREATES A TABLE
		final String SQL_01 =
			"DROP TABLE IF EXISTS `site_users`";
		Statement stmta = conn.createStatement(); 
		stmta.executeUpdate(SQL_01);
		stmta.close();
		final String SQL_02 = 
			"CREATE TABLE `site_users` (" + 
			"  id INTEGER NOT NULL AUTO_INCREMENT," + 
			"  username VARCHAR(50) NOT NULL," + 
			"  password_hash VARCHAR(256) NOT NULL," + 
			"  PRIMARY KEY (id)" + 
			")";
		Statement stmtb = conn.createStatement(); 
        stmtb.executeUpdate(SQL_02);
        stmtb.close();
        System.out.println("\nH2 DB Successfully Created !!!");
        
        // Execute an INSERT SQL Query
		final String SQL_2 = "INSERT INTO site_users VALUES(default, ?, ?)";
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
		
		// Execute another INSERT SQL (with given username and password hash)
		final String SQL_3 = 
			"INSERT INTO `site_users` VALUES ('99', 'usera', " + 
			"'ca978112ca1bbdcafac231b39a23dc4da786eff8147c4e72b9807785afee48bb')";
		conn.createStatement().executeUpdate(SQL_3);
		
		// Execute a SELECT SQL Query
		final String SQL_1 = "SELECT * from site_users";
		System.out.println("\nSQL: " + SQL_1);
		final Statement st = conn.createStatement();
		final ResultSet rs = st.executeQuery(SQL_1);
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
