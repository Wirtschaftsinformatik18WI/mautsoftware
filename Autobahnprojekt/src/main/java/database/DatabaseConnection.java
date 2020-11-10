package database;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

import Backend.FinishedTransits;
import Backend.Origin;
import Backend.Position;
import Backend.Transit;
import Backend.User;
import Backend.Vehicle;

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
		
// alle Fahrezuge abrufen die zu einem Benutzer gehören - Rückgabe bitte als Arraylist oder ähnliches
		
		public ArrayList<Vehicle> getVehicle(User user) {
			ArrayList<Vehicle> allvehicle = new ArrayList<>();
			 Statement stmt;
			
			 // Fahrzeugspezifische Daten aus der Datenbank laden
			try {
				stmt = conn.createStatement();
				String queryString = "SELECT *  FROM Public.\"Vehicle\" WHERE email =  ?  ";

				PreparedStatement prepStmt = conn.prepareStatement(queryString);
				prepStmt.setObject (1, user.geteMail());
				
				ResultSet rs = prepStmt.executeQuery();
				
				while ( rs.next() ) {
					Origin origin = null;
					Vehicle vehicle = new Vehicle (origin, rs.getString("regnumber"),user);
					vehicle.setLastPos(new Position (rs.getString("lastPosition")));
					vehicle.setAcuallPos(new Position (rs.getString("currentPosition")));
					vehicle.setKm(rs.getDouble("km"));
				}
				
				rs.close();
				stmt.close();
			} 
			catch (SQLException e) {
				System.err.println( e.toString() );
			}
			return allvehicle;
		}
	
// Alle Strecken abrufen die zu einem Fahrzeug gehören - Rückgabe bitte als Arraylist oder ähnliches
		
			public ArrayList<Transit> getfinishedTransit(String vehicleID) {
				ArrayList<Transit> allfinishedTransits = new ArrayList<>();
				 Statement stmt;
				
				 // Fahrzeugspezifische Streckendaten aus der Datenbank laden
				try {
					stmt = conn.createStatement();
					String queryString = "SELECT *  FROM Public.\"finishedTransit\" WHERE vid =  ?  ";

					PreparedStatement prepStmt = conn.prepareStatement(queryString);
					prepStmt.setObject (1, vehicleID);
					
					ResultSet rs = prepStmt.executeQuery();
					
					while ( rs.next() ) {
						Transit transit = new Transit(new Position(java.util.UUID.fromString(rs.getString("startPosition")), this.getDecriptionFromPositionID(rs.getString("startposition"))),rs.getDate("startdate"));
						transit.setEndDate(rs.getDate("enddate"));
						//transit.setKm(rs.getString("km"));
						transit.set(new Position(rs.getString("toid"));
					}
					
					rs.close();
					stmt.close();
				} 
				catch (SQLException e) {
					System.err.println( e.toString() );
				}
				return allfinishedTransits;
			}
		
// Get alle Daten von einer Person - Rückgabe einer Person bitte
		
		public User getUserData(String email) {
			User user = null;
			 Statement stmt;
			
			 // Userspezifische Daten aus der Datenbank laden
			try {
				stmt = conn.createStatement();
				String queryString = "SELECT *  FROM Public.\"User\" WHERE email =  ?  ";

				PreparedStatement prepStmt = conn.prepareStatement(queryString);
				prepStmt.setObject (1, email);
				
				ResultSet rs = prepStmt.executeQuery();
				
				while ( rs.next() ) {
					user = new User(email ,rs.getString("name"),rs.getString("surname"),rs.getString("street"),rs.getString("postcode"),rs.getString("hnumber"),rs.getString("city"),rs.getString("telephone"), rs.getString("password"), rs.getString("country"));
					user.seteMail(email);
					user.setFirma(rs.getBoolean("iscompany"));
				}
				
				rs.close();
				stmt.close();
			} 
			catch (SQLException e) {
				System.err.println( e.toString() );
			}
			return user;
		}
		
// get km aus zwei gegebenen Punkten
		public double getKM(Position startPO, Position endPO) {
			/*double km;
			km = 250.0;
			return km;
			*/
			double km = 0;
			 Statement stmt;
			
			 // Userspezifische Daten aus der Datenbank laden
			try {
				stmt = conn.createStatement();
				String queryString = "SELECT *  FROM Public.\"TransitOverview\" WHERE pointA =  ? AND pointB= ? ";

				PreparedStatement prepStmt = conn.prepareStatement(queryString);
				prepStmt.setObject (1, startPO);
				prepStmt.setObject (2, endPO);
				
				ResultSet rs = prepStmt.executeQuery();
				
				while ( rs.next() ) {
					km= rs.getDouble("km");
				}
				
				rs.close();
				stmt.close();
			} 
			catch (SQLException e) {
				System.err.println( e.toString() );
			}
			return km;
		}
		
//get description from position id
public String getDecriptionFromPositionID(String id) {
			String description=null;
			 Statement stmt;
			
			try {
				stmt = conn.createStatement();
				String queryString = "SELECT *  FROM Public.\"PositionOverview\" WHERE poid =  ?  ";

				PreparedStatement prepStmt = conn.prepareStatement(queryString);
				prepStmt.setObject (1, id);
				
				ResultSet rs = prepStmt.executeQuery();
				
				while ( rs.next() ) {
					description = rs.getString("description");
				}
				
				rs.close();
				stmt.close();
			} 
			catch (SQLException e) {
				System.err.println( e.toString() );
			}
			return description;
		}

		// Ein Fahrzeug mit dem Kennzeichen holen und zurückgeben
				public Vehicle getVehicleByRegistrationNr(Origin origin, String registrationNr) {
					Vehicle v = null;
					 Statement stmt;
					
					try {
						stmt = conn.createStatement();
						String queryString = "SELECT *  FROM Public.\"Vehicle\" WHERE regnumber =  ? AND cid = ? ";

						PreparedStatement prepStmt = conn.prepareStatement(queryString);
						prepStmt.setObject (1, origin);
						prepStmt.setObject (2, registrationNr);
						
						ResultSet rs = prepStmt.executeQuery();
						
						while ( rs.next() ) { 
							v = new Vehicle(origin, registrationNr, this.getUserData(rs.getString("uid")));
							v.setKm(rs.getDouble("km"));
							v.setAcuallPos(new Position(rs.getString("currentPosition")));
						}
						
						rs.close();
						stmt.close();
					} 
					catch (SQLException e) {
						System.err.println( e.toString() );
					}
					return v;
				}
			
		// Alle Strecken abrufen die zu einem Fahrezug gehören - Rückgabe bitte als Arraylist oder ähnliches
				
				public ArrayList<FinishedTransits> getAllTransitFromVehicle(Vehicle vehicle, LocalDate juncture) {
					ArrayList<FinishedTransits> transitlist = new ArrayList<>();
					return transitlist;
				}
				
		// Speichern der ersten Punkte in der Datenbank zu einem Auto
				//True -> first Point | False -> second Point
				public void saveFirstPointOfTransit(Vehicle vehicle, Position point, double km, boolean firstPoint) {
					
				}
				
		// change firste Point of a vehicle
				
				public void changeFirstPointOfTransit(Vehicle vehicle, Position lastPosition,Position acualPosition, double km) {
					
				}
				
		// Speichern eines beendeten Transits zu einem Auto
				public void saveFullTransit(Vehicle vehicle) {
					
				}
				
		// Delete started Transit
				
				public void deleteStartedTransit(Position lastPosition,Position acualPosition) {
					
				}
				
				
		// Return bitte die höchste Zeit die zwischen den erreichbaren strecken möglich ist, ich gebe dir einen Punkt rein
				public LocalDate getBiggestTraficTimeFromPoint(Position position) {
					
					return LocalDate.now();
					
				}
				
		// get all Points who are into the arriving spot  
				//badest case... all Strings and on a Arraylist from one vehicle etc etc... REDEBEDARF F
				public ArrayList<String> getAllPointsAndVehiclesFromArrivingSpot(){
					ArrayList<String> arrivedPoints = new ArrayList<>();
					return arrivedPoints;
				}
				
				
		// Neuen Nutzer erstellen
				
				public boolean createNewUser(User user) {
					if (conn == null) {
						return false;
					}
					try {
						String queryString = "INSERT INTO  Public.\"User\" (name,surname,street, postcode, hnumber, city, telephone, iscompany, email, password)" +
								" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
						System.out.println(queryString);

						PreparedStatement prepStmt = conn.prepareStatement(queryString);
						prepStmt.setString (1, user.getName());
						prepStmt.setString (2, user.getSurname());
						prepStmt.setString (3, user.getStreet());
						prepStmt.setString (4, user.getPostcode());
						prepStmt.setString (5, user.getHnumber());
						prepStmt.setString (6, user.getCity());
						prepStmt.setString (7, user.getTelephone());
						prepStmt.setBoolean(8, user.isFirma());
						prepStmt.setString (9, user.geteMail());
						prepStmt.setString (10, user.getPassword()); 

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
				
		// die Gebühren holen
				public double getFee() {
					double fee = 0;
					 Statement stmt;
					
					 // Gebühren-Daten aus der Datenbank laden
					try {
						stmt = conn.createStatement();
						String queryString = "SELECT *  FROM Public.\"Fees\" WHERE description =  ?  ";

						PreparedStatement prepStmt = conn.prepareStatement(queryString);
						prepStmt.setObject (1, "basic");
						
						ResultSet rs = prepStmt.executeQuery();
						
						while ( rs.next() ) {
							fee= rs.getDouble("fee");
						}
						
						rs.close();
						stmt.close();
					} 
					catch (SQLException e) {
						System.err.println( e.toString() );
					}
					return fee;
				}		
				
		//die Steuer holen
				public double getTax() {
					double tax = 0;
					 Statement stmt;
					
					 // Steuer-Daten aus der Datenbank laden
					try {
						stmt = conn.createStatement();
						String queryString = "SELECT *  FROM Public.\"Tax\" WHERE description =  ?  ";

						PreparedStatement prepStmt = conn.prepareStatement(queryString);
						prepStmt.setObject (1, "basictax");
						
						ResultSet rs = prepStmt.executeQuery();
						
						while ( rs.next() ) {
							tax= rs.getDouble("amount");
						}
						
						rs.close();
						stmt.close();
					} 
					catch (SQLException e) {
						System.err.println( e.toString() );
					}
					return tax;
				}
				
		//die Gebühren für Live holen
				public double getLiveFee() {
					double livefee = 0;
					 Statement stmt;
					
					 // Gebühren- Daten aus der Datenbank laden
					try {
						stmt = conn.createStatement();
						String queryString = "SELECT *  FROM Public.\"Fees\" WHERE description =  ?  ";

						PreparedStatement prepStmt = conn.prepareStatement(queryString);
						prepStmt.setObject (1, "livefee");
						
						ResultSet rs = prepStmt.executeQuery();
						
						while ( rs.next() ) {
							livefee= rs.getDouble("fee");
						}
						
						rs.close();
						stmt.close();
					} 
					catch (SQLException e) {
						System.err.println( e.toString() );
					}
					return livefee;
				}		
		
//Testfunktion:________________________________________________________________________________
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
//_____________________________________________________________________________________________

		
	}