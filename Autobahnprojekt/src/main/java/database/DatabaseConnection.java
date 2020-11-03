package database;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;

public class DatabaseConnection {

		Connection conn = null; 

		public DatabaseConnection () {
			try {
				Class.forName("org.postgresql.Driver");
				conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/MotorwayToll","postgres", "postgres");
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

		
	}