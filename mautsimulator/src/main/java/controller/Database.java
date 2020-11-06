package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

import javax.swing.text.Position;

public class Database {
	
	Connection conn = null; 

	public void DatabaseConnection () {
		try {
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection("jdbc:postgresql://ssabautzen3.ba-bautzen.de:5433/Simulator","postgres", "postgres");
			conn.setAutoCommit(true);
		} 
		catch ( Exception e ) {
			System.err.println( e.toString() );
		}
	}
	protected void finalize() {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				System.err.println( e.toString() );
			}
		}
	}
	/*
	public boolean addFeeTest(String feename, UUID feeid, double d) {
		if (conn == null) {
			return false;
		}
		try {
			String queryString = "INSERT INTO  Public.\"Fees\" (name,id,amount)" +
					" VALUES (?, ?, ?)";
			System.out.println(queryString);

			PreparedStatement prepStmt = conn.prepareStatement(queryString);
			prepStmt.setString (1, feename);
			prepStmt.setString (2, feeid.toString());
			prepStmt.setDouble(3, 0.5);

			prepStmt.execute();
			prepStmt.close();

			System.out.println("Entry created successfully");
			return true;
		} 
		catch ( Exception e ) {
			System.err.println( e.toString() );
			return false;
		}
	}
	*/
}
