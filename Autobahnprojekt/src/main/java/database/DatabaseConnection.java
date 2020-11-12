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
		
		/**
		 * save the first Point of a vehicle
		 * 
		 * @param vehicle actual vehicle
		 * @param point first point of that vehicle
		 */
		public void saveFirstPointTransit(Vehicle vehicle, Position point) {
			Statement stmt;
			try {
				//TODO überarbeiten
				stmt = conn.createStatement();
				String queryString = "INSERT INTO Public.\"startedTransit\" (toid, startposition, startdate, starttransid, vid) "
						+ "VALUES (?,?,?,?,?) ";
				
				PreparedStatement prepStmt = conn.prepareStatement(queryString);
				prepStmt.setObject (1, point.getPositionID());
				prepStmt.setObject (2, point.getDescription());
				prepStmt.setObject (3, point.getTime());
				prepStmt.setObject (4, UUID.randomUUID());
				prepStmt.setObject (5, vehicle.getVid());
				
				ResultSet rs = prepStmt.executeQuery();
				
				rs.close();
				stmt.close();

				System.out.println("Entry created successfully");
				
			} 
			catch ( Exception e ) {
				System.err.println( e.toString() );
				
			}
		}
		/**
		 * Save the 2 Point to save the Transit
		 * 
		 * @param vehicle
		 * @param point1
		 * @param point2
		 * @param km
		 * @return
		 */
		public String savePointsAsFirstAbsolvedTransit(Vehicle vehicle, Position point1 ,Position point2, double km) {
			
			Statement stmt;
			String transitID = UUID.randomUUID().toString();
			String queryString = "INSERT INTO Public.\"TransitStration\" ( TransitID, Point1, Pont1Time, Point2, Point2Time, vid, km) "
					+ "VALUES (?,?,?,?,?,?,?) ";
			try {
				//TODO überarbeiten
				stmt = conn.createStatement();
				PreparedStatement prepStmt = conn.prepareStatement(queryString);
				prepStmt.setObject (1, transitID);
				prepStmt.setObject (2, point1.getDescription());
				prepStmt.setObject (3, point1.getTime());
				prepStmt.setObject (4, point2.getDescription());
				prepStmt.setObject (5, point2.getTime());
				prepStmt.setObject (6, vehicle.getVid());
				prepStmt.setObject (7, km);
				
				
				ResultSet rs = prepStmt.executeQuery();
				
				rs.close();
				stmt.close();

				System.out.println("Entry created successfully");
				
			} 
			catch ( Exception e ) {
				System.err.println( e.toString() );
			}
			return transitID;
		}
		
		
		/**
		 * save the points of a transit inside of a vehicle and as a startedTransit 
		 * @category !ATTANTION! Transit is finally finished when vehicle reach a A-Point, so a 2. D point could get saved too here
		 * 
		 * @param vehicle actual driving vehicle
		 * @param point point who got reached
		 * @param km driven km, can be 0 or more
		 * @param firstPoint true -> lastPosition | false -> actualPosition
		 */
		public void saveAcualPointOfTransit(String TransitID, Position point, double km) {
			 Statement stmt;
			 String queryString = "";
			try {
				//TODO überarbeiten
				stmt = conn.createStatement();
				queryString = "UPDATE Public.\"TransitStration\" SET ( Point2, Point2Time,km) "
							+ "VALUES (?,?,?) WHERE Transit ID = ? ";
				
				PreparedStatement prepStmt = conn.prepareStatement(queryString);
				prepStmt.setObject (1, point.getPositionID());
				prepStmt.setObject (2, point.getTime());
				prepStmt.setObject (3, km);
				prepStmt.setObject (4, TransitID);
				
				ResultSet rs = prepStmt.executeQuery();
				
				rs.close();
				stmt.close();

				System.out.println("Entry created successfully");
			} 
			catch ( Exception e ) {
				System.err.println( e.toString() );
			}
		}
		
		public void saveLastPointOfTransit(String TransitID, Position point) {
			 Statement stmt;
			 String queryString = "";
			try {
				//TODO überarbeiten
				stmt = conn.createStatement();
				queryString = "UPDATE Public.\"TransitStration\" SET ( Point1, Pont1Time)  "
						+ "VALUES (?,?) WHERE Transit ID = ?";
				
				PreparedStatement prepStmt = conn.prepareStatement(queryString);
				prepStmt.setObject (1, point.getPositionID());
				prepStmt.setObject (2, point.getTime());
				prepStmt.setObject (3, TransitID);
				
				ResultSet rs = prepStmt.executeQuery();
				
				rs.close();
				stmt.close();

				System.out.println("Entry created successfully");
			} 
			catch ( Exception e ) {
				System.err.println( e.toString() );
			}
		}
		
				
		// Speichern eines beendeten Transits zu einem Auto
		/**
		 * function save a finished Transit into Table finishedTransit
		 * 
		 * @param vehicle finished Transit of that vehicle
		 * @return true if successful , false if not
		 */
		public boolean saveFullTransit(Vehicle vehicle) {
			 Statement stmt;
			try {
				//TODO überprüfen
				stmt = conn.createStatement();
				String queryString = "INSERT INTO Public.\"finishedTransit\" (ftransid, description, vid, startposition, endposition, startdate, enddate, km) " //toid fehlt aktuell
						+ "VALUES (?,?,?,?,?,?,?,?) ";
				PreparedStatement prepStmt = conn.prepareStatement(queryString);
				prepStmt.setObject (1, UUID.randomUUID().toString());
				prepStmt.setObject (2, vehicle.getRegistrationNumber());
				prepStmt.setObject (3, vehicle.getVid());
				prepStmt.setObject (4, vehicle.getAbsolutStartPos());
				prepStmt.setObject (5, vehicle.getAbsolutEndPos());
				prepStmt.setObject (6, vehicle.getAbsolutStartPos().getTime());
				prepStmt.setObject (7, vehicle.getAbsolutEndPos().getTime());
				prepStmt.setObject (8, vehicle.getKm());
				
					
				ResultSet rs = prepStmt.executeQuery();
				
				rs.close();
				stmt.close();

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
		/**
		 * calculates the biggest time of all available points next to the given point
		 * 
		 * @param position actual given point
		 * @return biggest Time who can take to another point
		 */
		public int getBiggestTraficTimeFromPoint(Position position) {
			int biggestTrafficTime = 0;
			Statement stmt;
			try {
				stmt = conn.createStatement();
				String queryString = "SELECT time  FROM Public.\"TransitOverview\" WHERE pointA =  ? ";
				PreparedStatement prepStmt = conn.prepareStatement(queryString);
				prepStmt.setObject (1, position.getDescription());
			
				ResultSet rs = prepStmt.executeQuery();
					
				while ( rs.next() ) {
					if(biggestTrafficTime<rs.getInt("time")) {
						biggestTrafficTime = rs.getInt("time");
					}
				}
						
				rs.close();
				stmt.close();
			} 
			catch (SQLException e) {
					System.err.println( e.toString() );
			}
			return biggestTrafficTime;
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
			
		/**
		 * get alltransits as a vehicle who have the TrafficJamFlag
		 * 
		 * @return returns Transits as Vehicles 
		 */
		public ArrayList<Vehicle> getAllTransitsWithTraficJamFlag() {
			ArrayList<Vehicle> transitListWithTraficJam = new ArrayList <>();
			Statement stmt;
			
			try {
				stmt = conn.createStatement();
				String queryString = "SELECT *  FROM Public.\"TransitStations\" WHERE TrafficJam = 1";
				PreparedStatement prepStmt = conn.prepareStatement(queryString);
				
				ResultSet rs = prepStmt.executeQuery();
				
				while ( rs.next() ) { 
					Vehicle v = getVehicleByVID(rs.getString("vid"));
					v.setAcuallPos(new Position(rs.getString("Point1"), rs.getDate("Point1Time").toLocalDate(), rs.getString("Point1")));
					v.setLastPos(new Position(rs.getString("Point2"), rs.getDate("Point2Time").toLocalDate(), rs.getString("Point2")));
				}
					
				rs.close();
				stmt.close();
			} 
			catch (SQLException e) {
				System.err.println( e.toString() );
			}
			
			return transitListWithTraficJam;
		}
				
		/**
		 * get a Vehicle by his vid
		 * 
		 * @param vID ID from that vehicle
		 * @return a vehicle 
		 */
		public Vehicle getVehicleByVID(String vID) {
			Vehicle vehicle = null;
			 Statement stmt;
				
			 /**
			  * get all vehicle by User E-Mail
			  */
			try {
				stmt = conn.createStatement();
				String queryString = "SELECT *  FROM Public.\"Vehicle\" WHERE vid =  ?  ";

				PreparedStatement prepStmt = conn.prepareStatement(queryString);
				prepStmt.setObject (1, vID);
	
				ResultSet rs = prepStmt.executeQuery();
				
				/**
				 * create a vehicle object out of the given information from the DB
				 */
				
				Origin origin = null;
				vehicle = new Vehicle (origin, rs.getString("regnumber"),getUserData(rs.getString("uid")), rs.getString("vid"));
				vehicle.setLastPos(new Position (rs.getString("lastPosition")));
				vehicle.setAcuallPos(new Position (rs.getString("currentPosition")));
				vehicle.setKm(rs.getDouble("km"));
				
				rs.close();
				stmt.close();
			} 
			catch (SQLException e) {
				System.err.println( e.toString() );
			}
			
			
			return vehicle;
		}
		
				
		// get all Transits without Trafic Jam Frlag inside a vehicle Object
		/**
		 * get alltransits as a vehicle who dont have the TrafficJamFlag
		 * 
		 * @return returns Transits as Vehicles 
		 */
		public ArrayList<Vehicle> getAllTransitsWithoutTraficJamFlag(){
			ArrayList<Vehicle> transitListWithoutTraficJam = new ArrayList <>();
			Statement stmt;
			
			try {
				stmt = conn.createStatement();
				String queryString = "SELECT *  FROM Public.\"TransitStations\" WHERE TrafficJam = 0";
				PreparedStatement prepStmt = conn.prepareStatement(queryString);
						
				ResultSet rs = prepStmt.executeQuery();
					
				while ( rs.next() ) { 
					Vehicle v = getVehicleByVID(rs.getString("vid"));
					v.setAcuallPos(new Position(rs.getString("Point1"), rs.getDate("Point1Time").toLocalDate(), rs.getString("Point1")));
					v.setLastPos(new Position(rs.getString("Point2"), rs.getDate("Point2Time").toLocalDate(), rs.getString("Point2")));
					transitListWithoutTraficJam.add(v);
				}
					
				rs.close();
				stmt.close();
			} 
			catch (SQLException e) {
				System.err.println( e.toString() );
			}
					
			return transitListWithoutTraficJam;
		}
				
		/**
		 * calculates the total number of vehicle from a given origin
		 * 
		 * @param origin actually origin
		 * @return return count of that vehicles from that origin
		 */
		public long getTotalNumberOfVehicleByOrigin(Origin origin) {
			long originnumber = 0;
			Statement stmt;
			try {
				stmt = conn.createStatement();
				String queryString = "SELECT *  FROM Public.\"Vehicle\" WHERE cid = ?";
				
				PreparedStatement prepStmt = conn.prepareStatement(queryString);
				prepStmt.setObject (1, origin.toString());
				ResultSet rs = prepStmt.executeQuery();
						
				while ( rs.next() ) { 
					originnumber++;
				}
				rs.close();
				stmt.close();
			} 
			catch (SQLException e) {
				System.err.println( e.toString() );
			}		
			return originnumber;
		}
				
		// get all Points who are into the arriving spot  
				//badest case... all Strings and on a Arraylist from one vehicle etc etc... REDEBEDARF F
		/**
		 * get all Point from Simulation as a Vehicle
		 * 
		 * @return a ArrayList<Vehicle> with Vehicles and there points
		 */
		public ArrayList<Vehicle> getAllPointsAndVehiclesFromArrivingSpot(){
			ArrayList<Vehicle> arrivedPoints = new ArrayList<>();
			 Statement stmt;
			
			try {
				stmt = conn.createStatement();
				String queryString = "SELECT *  FROM Public.\"transmitterData\"";

				PreparedStatement prepStmt = conn.prepareStatement(queryString);
	
				ResultSet rs = prepStmt.executeQuery();
				
				/**
				 * create a vehicle object out of the given information from the DB
				 */
				while ( rs.next() ) {
					Origin origin = null;
					Vehicle vehicle = new Vehicle (origin, rs.getString("regnumber"),
							getUserData(getUserIDByVehicle(rs.getString("vid"))), rs.getString("vid"));
					if(!rs.getString("lastPosition").isEmpty()) {
					vehicle.setLastPos(new Position (rs.getString("lastPosition")));}
					vehicle.setAcuallPos(new Position (rs.getString("currentPosition")));
					vehicle.setKm(rs.getDouble("km"));
				}
				
				rs.close();
				stmt.close();
			} 
			catch (SQLException e) {
				System.err.println( e.toString() );
			}					
					
			return arrivedPoints;
		}
		
		/**
		 * Get a User ID by a Vehicle ID
		 * 
		 * @param vID Vehicle ID
		 * @return return the User ID as a String
		 */
		public String getUserIDByVehicle(String vID) {
			String userID = "";
			Statement stmt;
			
			try {
				stmt = conn.createStatement();
				String queryString = "SELECT uid FROM Public.\"Vehicle\" WHERE vid = ?";

				PreparedStatement prepStmt = conn.prepareStatement(queryString);
				prepStmt.setObject (1, vID);
	
				ResultSet rs = prepStmt.executeQuery();
				
				userID = rs.getString("uid");				
				
				rs.close();
				stmt.close();
			} 
			catch (SQLException e) {
				System.err.println( e.toString() );
			}
			
			return userID;
			
		}
		
		// Add Flag trafficOffender
		/**
		 * add Flag TrafficOffender to a vehicle in TransitStations
		 * 
		 * @param vehicle specific vehicle
		 */
		public void addFlagTrafficOffender(Vehicle vehicle) {
			Statement stmt;
			
			try {
				stmt = conn.createStatement();
				String queryString = "UPDATE Public.\"TransitStations\" SET TrafficOffender = 1 WHERE vid = ?";
				PreparedStatement prepStmt = conn.prepareStatement(queryString);
				prepStmt.setObject (1, vehicle.getVid());
			}
			catch ( Exception e ) {
				System.err.println( e.toString() );
			}	
		}
				
		// Get all Vehicle with Traffic offender Flag
		/**
		 * get all Vehicle with the TrafficOffender Flag
		 * 
		 * @return ArrayList<Vehicle> with all vehicle who got the TrafficOffender Flag
		 */
		public ArrayList<Vehicle> getAllVehicleWithTrafficOffenderFlag() {
			ArrayList<Vehicle> vehiclesWithTrafficOffenderFlag = new ArrayList <>();
			Statement stmt;
			
			try {
				stmt = conn.createStatement();
				String queryString = "SELECT *  FROM Public.\"TransitStations\" WHERE TrafficJam = 0";
				PreparedStatement prepStmt = conn.prepareStatement(queryString);
						
				ResultSet rs = prepStmt.executeQuery();
					
				while ( rs.next() ) { 
					Vehicle v = getVehicleByVID(rs.getString("vid"));
					v.setAcuallPos(new Position(rs.getString("Point1"), rs.getDate("Point1Time").toLocalDate(), rs.getString("Point1")));
					v.setLastPos(new Position(rs.getString("Point2"), rs.getDate("Point2Time").toLocalDate(), rs.getString("Point2")));
					vehiclesWithTrafficOffenderFlag.add(v);
				}
					
				rs.close();
				stmt.close();
			} 
			catch (SQLException e) {
				System.err.println( e.toString() );
			}
					
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
				
				// Passwort und Nutzername holen
				public String getPasswordFromEmail(String email) {
					String password=null;
					 Statement stmt;
					
					 // Userspezifische Daten aus der Datenbank laden
					try {
						stmt = conn.createStatement();
						String queryString = "SELECT *  FROM Public.\"User\" WHERE email =  ?  ";

						PreparedStatement prepStmt = conn.prepareStatement(queryString);
						prepStmt.setObject (1, email);
						
						ResultSet rs = prepStmt.executeQuery();
						
						while ( rs.next() ) {
							password= rs.getString("password");
						}
						
						rs.close();
						stmt.close();
					} 
					catch (SQLException e) {
						System.err.println( e.toString() );
					}
					return password;
				}
	//ist User in DB?
				public boolean doesUserExist(String email) {
					 Statement stmt;
					 boolean doesExist = false;
					
					 // Userspezifische Daten aus der Datenbank laden
					try {
						stmt = conn.createStatement();
						String queryString = "SELECT *  FROM Public.\"User\" WHERE email =  ?  ";

						PreparedStatement prepStmt = conn.prepareStatement(queryString);
						prepStmt.setObject (1, email);
						
						ResultSet rs = prepStmt.executeQuery();
						 doesExist = rs.next();
						
						rs.close();
						stmt.close();
					} 
					catch (SQLException e) {
						System.err.println( e.toString() );
					}
					return doesExist;
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