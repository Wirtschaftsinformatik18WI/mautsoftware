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

import Backend.DBInputVehicleAndPoint;
import Backend.FinishedTransits;
import Backend.Origin;
import Backend.Position;
import Backend.Transit;
import Backend.User;
import Backend.Vehicle;

/**
 * 
 * class to:
 * 		~ get all Information aut of the DB
 * 		 
 * 
 * @author mariett sauer Mail: mariett.sauer@cideon.com
 * Company: Cideon Software & Services GmbH & Co. KG.
 * created at 04.11.2020
 * 
 * edit by luisa thiel Mail: luisa.thiel@cideon.com
 */
public class DatabaseConnection {

		Connection conn = null; 

		/**
		 * Contructor to create a DB - Connection
		 */
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
		
		/**
		 * function to check the actual DB connection
		 */
		protected void finalize() {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					System.err.println( e.toString() );
				}
			}
		}
		
		
		/**
		 * get all vehicle from a user by given User
		 * 
		 * @param user actual user
		 * @return ArrayList<Vehicle> from a User
		 */
		public ArrayList<Vehicle> getVehicle(User user) {
			ArrayList<Vehicle> allvehicle = new ArrayList<>();
			 Statement stmt;
			
			 /**
			  * get all vehicle by User E-Mail
			  */
			try {
				stmt = conn.createStatement();
				String queryString = "SELECT *  FROM Public.\"Vehicle\" WHERE email =  ?  ";

				PreparedStatement prepStmt = conn.prepareStatement(queryString);
				prepStmt.setObject (1, user.geteMail());
	
				ResultSet rs = prepStmt.executeQuery();
				
				/**
				 * create a vehicle object out of the given information from the DB
				 */
				while ( rs.next() ) {
					Origin origin = null;
					Vehicle vehicle = new Vehicle (origin, rs.getString("regnumber"),user, rs.getString("vid"));
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
	
			/**
			 * get all finished Transits from a vehicle
			 * 
			 * @param vehicleID vehicle ID
			 * @return ArrayList<FinishedTransits> with all finished transits from a vehicle
			 */
		public ArrayList<FinishedTransits> getfinishedTransit(String vehicleID) {
			ArrayList<FinishedTransits> allfinishedTransits = new ArrayList<>();
			 Statement stmt;
			
			 /**
			  * get all finished Transity by vehicle ID
			  */
			try {
				stmt = conn.createStatement();
				String queryString = "SELECT *  FROM Public.\"finishedTransit\" WHERE vid =  ?  ";
				PreparedStatement prepStmt = conn.prepareStatement(queryString);
				prepStmt.setObject (1, vehicleID);
				
				ResultSet rs = prepStmt.executeQuery();
				
				/**
				 * create a finished Transit by given Data and add them to the Array
				 */
				while ( rs.next() ) {
					FinishedTransits finishedTransit = new FinishedTransits(
							new Position(rs.getString("startPosition"), rs.getDate("startdate").toLocalDate(), rs.getString("description")), rs.getDate("startdate").toLocalDate(),
							new Position(rs.getString("endPosition"), rs.getDate("enddate").toLocalDate(),  rs.getString("description")), rs.getDate("enddate").toLocalDate(),
							rs.getLong("km"));
					
					allfinishedTransits.add(finishedTransit);
				}
				
				rs.close();
				stmt.close();
			} 
			catch (SQLException e) {
				System.err.println( e.toString() );
			}
			return allfinishedTransits;
		}
		
		/**
		 * Get all Data from a person by his E-Mail
		 * 
		 * @param email EMail from that User to get all User Data
		 * @return return a completed User
		 */
		public User getUserData(String email) {
			User user = null;
			 Statement stmt;
			
			 /**
			  * get Userspecific Data
			  */
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
		
		/**
		 * get km from 2 given Points out of the Prosition Overview table
		 * 
		 * @param startPO Start Point
		 * @param endPO End Point
		 * @return long of the KM from startPO to endPO
		 */
		public long getKM(Position startPO, Position endPO) {
			long km = 0;
			 Statement stmt;
			
			 /**
			  * get the KM by given points
			  */
			try {
				stmt = conn.createStatement();
				String queryString = "SELECT *  FROM Public.\"TransitOverview\" WHERE pointA =  ? AND pointB= ? ";

				PreparedStatement prepStmt = conn.prepareStatement(queryString);
				prepStmt.setObject (1, startPO);
				prepStmt.setObject (2, endPO);
				
				ResultSet rs = prepStmt.executeQuery();
				
				while ( rs.next() ) {
					km= rs.getLong("km");
				}
				
				rs.close();
				stmt.close();
			} 
			catch (SQLException e) {
				System.err.println( e.toString() );
			}
			return km;
		}

		/**
		 * get the description from a given PositionID
		 * 
		 * @param id ID from a Position
		 * @return String description from a given Position
		 */
		public String getDecriptionFromPositionID(String id) {
			String description=null;
			 Statement stmt;
			
			 /**
			  * get the description by ID
			  */
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
		/**
		 * Get a vehicle by his registration Nummer -> Origin + regristation numer
		 * 
		 * @param origin origin of that vehicle
		 * @param registrationNr regNR of the vehicle
		 * @return full vehicle mit all points
		 */
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
					v = new Vehicle(origin, registrationNr, this.getUserData(rs.getString("uid")), rs.getString("vid"));
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
		/**
		 * get all Finished Transits from a given vehicle in the given month
		 * 	
		 * @param vehicle actual vehicle
		 * @param month month under review
		 * @return ArrayList<FinishedTransits> with all Transits from that given month
		 */
		public ArrayList<FinishedTransits> getAllTransitFromVehicle(Vehicle vehicle, int month) {
			ArrayList<FinishedTransits> allfinishedTransits = new ArrayList<>();
			 Statement stmt;
			
			 /**
			  * get all finished Transity by vehicle ID
			  */
			try {
				//TODO überprüfen
				LocalDate firstDayOfGivenMonth = LocalDate.of(2020, month, 1);
				LocalDate lastDayOfGivenMonth = firstDayOfGivenMonth.withDayOfMonth(firstDayOfGivenMonth.lengthOfMonth());
				stmt = conn.createStatement();
				String queryString = "SELECT *  FROM Public.\"finishedTransit\" WHERE vid =  ? AND startdate "
						+ "BETWEEN " + firstDayOfGivenMonth.toString() +" AND " + lastDayOfGivenMonth; //TODO 
				PreparedStatement prepStmt = conn.prepareStatement(queryString);
				prepStmt.setObject (1, vehicle.getRegistrationNumber());
				
				ResultSet rs = prepStmt.executeQuery();
				
				/**
				 * create a finished Transit by given Data and add them to the Array
				 */
				while ( rs.next() ) {
					FinishedTransits finishedTransit = new FinishedTransits(
							new Position(rs.getString("startPosition"), rs.getDate("startdate").toLocalDate(), rs.getString("description")), rs.getDate("startdate").toLocalDate(),
							new Position(rs.getString("endPosition"), rs.getDate("enddate").toLocalDate(),  rs.getString("description")), rs.getDate("enddate").toLocalDate(),
							rs.getLong("km"));
					
					allfinishedTransits.add(finishedTransit);
				}
				
				rs.close();
				stmt.close();
			} 
			catch (SQLException e) {
				System.err.println( e.toString() );
			}
			return allfinishedTransits;
		}
				
		// Speichern der ersten Punkte in der Datenbank zu einem Auto und in der Tabelle TransitStation damit ich die abends auswerten kann
				//True -> first Point | False -> second Point
		/**
		 * save the points of a transit inside of a vehicle and as a startedTransit 
		 * @category !ATTANTION! Transit is finally finished when vehicle reach a A-Point, so a 2. D point could get saved too here
		 * 
		 * @param vehicle actual driving vehicle
		 * @param point point who got reached
		 * @param km driven km, can be 0 or more
		 * @param firstPoint true -> first Point | false -> second Point
		 */
		public boolean saveFirstPointOfTransit(Vehicle vehicle, Position point, double km, boolean firstPoint) {
			 Statement stmt;
			try {
				//TODO überarbeiten
//				stmt = conn.createStatement();
//				String queryString = "INSERT INTO Public.\"startedTransit\" (toid, startposition, startdate, starttransid, vid) "
//						+ "VALUES (?,?,?,?,?) ";
//				PreparedStatement prepStmt = conn.prepareStatement(queryString);
//				prepStmt.setObject (1, point.getPositionID());
//				prepStmt.setObject (2, point.getDescription());
//				prepStmt.setObject (3, point.getTime());
//				prepStmt.setObject (4, UUID.randomUUID());
//				prepStmt.setObject (5, vehicle.getVid());
//				
//				ResultSet rs = prepStmt.executeQuery();
//				
//				rs.close();
//				stmt.close();
//
//				System.out.println("Entry created successfully");
				return true;
			} 
			catch ( Exception e ) {
				System.err.println( e.toString() );
				return false;
			}
		}
				
		// Speichern eines beendeten Transits zu einem Auto
		public boolean saveFullTransit(Vehicle vehicle) {
			 Statement stmt;
			try {
//				//TODO überarbeiten
//				stmt = conn.createStatement();
//				String queryString = "INSERT INTO Public.\"startedTransit\" (toid, startposition, startdate, starttransid, vid) "
//						+ "VALUES (?,?,?,?,?) ";
//				PreparedStatement prepStmt = conn.prepareStatement(queryString);
//				prepStmt.setObject (1, UUID.randomUUID().toString());
//				prepStmt.setObject (2, vehicle.getAbsolutStartPos());
//				prepStmt.setObject (3, vehicle.getAbsolutStartPos().getTime());
//				prepStmt.setObject (4, UUID.randomUUID());
//				prepStmt.setObject (5, vehicle.getVid());
//					
//				ResultSet rs = prepStmt.executeQuery();
//				
//				rs.close();
//				stmt.close();

				System.out.println("Entry created successfully");
				return true;
			} 
			catch ( Exception e ) {
				System.err.println( e.toString() );
				return false;
			}		
		}
				
//		/s
				
		// Return bitte die höchste Zeit die zwischen den erreichbaren strecken möglich ist, ich gebe dir einen Punkt rein
				public int getBiggestTraficTimeFromPoint(Position position) {
					//get all Point where 
					
					return 2;
					
				}
				
		// set Trafic Jam Flag on DB in table vehicle
		/**
		 * set the flag TraficJam in table transitStation for that vehicle with his points
		 * 
		 * @param vehicle actual vehicle who stuck into traffic jam
		 */
		public void setTraficJamFlag(Vehicle vehicle) {
			Statement stmt;
			
			try {
				stmt = conn.createStatement();
				String queryString = "UPDATE Public.\"TransitStations\" SET TrafficJam = 1 WHERE vid = ?";
				PreparedStatement prepStmt = conn.prepareStatement(queryString);
				prepStmt.setObject (1, vehicle.getVid());
			}
			catch ( Exception e ) {
				System.err.println( e.toString() );
			}	
			
			//TODO Prüfung
			
		}
				
		/**
		 * set Flag lost in table TransitStations
		 * 
		 * @param vehicle actual vehicle who is lost in space
		 */
		public void setLostVehicleFlag(Vehicle vehicle) {
			Statement stmt;
			
			try {
				stmt = conn.createStatement();
				String queryString = "UPDATE Public.\"TransitStations\" SET lost = 1 WHERE vid = ?";
				PreparedStatement prepStmt = conn.prepareStatement(queryString);
				prepStmt.setObject (1, vehicle.getVid());
			}
			catch ( Exception e ) {
				System.err.println( e.toString() );
			}	
			
			//TODO Prüfung
		}
			
		// get all Transits with Trafic Jam Flag inside a vehicle Object
				public ArrayList<Vehicle> getAllTransitsWithTraficJamFlag() {
					ArrayList<Vehicle> transitListWithTraficJam = new ArrayList <>();
					Statement stmt;
					
					try {
						stmt = conn.createStatement();
						String queryString = "SELECT *  FROM Public.\"TransitStations\" WHERE TrafficJam = 0";
						PreparedStatement prepStmt = conn.prepareStatement(queryString);
						prepStmt.setObject (1, origin);
						prepStmt.setObject (2, registrationNr);
						
						ResultSet rs = prepStmt.executeQuery();
						
						while ( rs.next() ) { 
							v = new Vehicle(origin, registrationNr, this.getUserData(rs.getString("uid")), rs.getString("vid"));
							v.setKm(rs.getDouble("km"));
							v.setAcuallPos(new Position(rs.getString("currentPosition")));
						}
							
						rs.close();
						stmt.close();
					} 
					catch (SQLException e) {
						System.err.println( e.toString() );
					}
					
					return transitListWithTraficJam;
				}
				
		// get all Transits without Trafic Jam Frlag inside a vehicle Object
				public ArrayList<Vehicle> getAllTransitsWithoutTraficJamFlag(){
					ArrayList<Vehicle> transitListWithTraficJam = new ArrayList <>();
					return transitListWithTraficJam;
				}
				
		// get Total number of vehicle by origin - origin shall be given
				public long getTotalNumberOfVehicleByOrigin(Origin origin) {
					long dingsda = 0;
					return dingsda;
				}
				
		// get all Points who are into the arriving spot  
				//badest case... all Strings and on a Arraylist from one vehicle etc etc... REDEBEDARF F
				public ArrayList<DBInputVehicleAndPoint> getAllPointsAndVehiclesFromArrivingSpot(){
					ArrayList<DBInputVehicleAndPoint> arrivedPoints = new ArrayList<>();
					return arrivedPoints;
				}
				
		// Add Flag trafficOffender
				public void addFlagTrafficOffender(Vehicle vehicle) {
					
				}
				
		// Get all Vehicle with Traffic offender Flag
				public ArrayList<Vehicle> getAllVehicleWithTrafficOffenderFlag() {
						ArrayList<Vehicle> vehiclesWithTrafficOffenderFlag = new ArrayList <>();
						return vehiclesWithTrafficOffenderFlag;
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