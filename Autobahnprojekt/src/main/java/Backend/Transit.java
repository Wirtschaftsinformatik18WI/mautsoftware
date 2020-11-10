package Backend;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.logging.Logger;

import database.DatabaseConnection;

/**
 * 
 * class to:
 * 		~ check all given Point 
 * 		 
 * 
 * @author luisa.thiel Mail: luisa.thiel@cideon.com
 * Company: Cideon Software & Services GmbH & Co. KG.
 * created at 04.11.2020
 */
public class Transit {

	public static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	
	private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy - hh:mm");
	private Position position;
	private LocalDate positionDate;
	
	private boolean runingTransit = false; // true -> 
	
	private Position absolutStartPosition;
	private LocalDate absolutStartTime;
	private Position absolutEndPosition;
	private LocalDate absolutEndTime; 

	private double km;
	
	private DatabaseConnection dbconnection = new DatabaseConnection();
	
	/**
	 * Constructor to create a Transit
	 * 
	 * @param position actual position of the vehicle
	 * @param positionDate Date and Time of the acual position
	 */
	public Transit(Position position, LocalDate positionDate) {
		this.position = position;
		this.positionDate = LocalDate.parse(positionDate.toString(), dateTimeFormatter);
	}

	/**
	 * filter all the given points and assigning points to a class
	 * 
	 * @param point point to assign to a specific vehicle
	 * @param vehicle vehicle to assign that point
	 */
	public void filterPoint(Position point , Vehicle vehicle) {
		/**
		 * check if there is a last Point 
		 * PS: is actually not nessesary if he had a first point -> would be checked later
		 */
		if(vehicle.getLastPos() == null) {
			/**
			 * checks if given Point is a A-X Point or a D-X Point
			 * A-X Point : "Abfahrt" = Departure
			 * D-X Point : "Durchfahrt" = Thoroughfare
			 * 
			 * 
			 * A-X is inadmissible and an e-Mail is sent to the police, since it is then a Blind sider
			 */
			if(point.getPositionID().substring(1, point.getPositionID().length()).equals("A")) {
				LOGGER.severe("Vehicle " + vehicle.getOrigin().toString() + "-" + vehicle.getRegistrationNumber()
				+ " coudn not be regristed as a driving vehicle.");
				LOGGER.severe("POLICE WILL BE INFORMED - VEHICLE IS BLIND SIDER");
				
				//TODO  EMail an de Police
				
			/**
			 * Otherwise if it's D the point, it would be set as the actual Point
			 */
			}else {
				vehicle.setAcuallPos(point);
				setAbsolutStartPosition(point);
				setAbsolutStartTime(point.getTime());
				dbconnection.saveFirstPointOfTransit(vehicle, point, 0, true);
				lFVehicle(vehicle, dbconnection.getBiggestTraficTimeFromPoint(point));
				
				LOGGER.fine("Point: " + vehicle.getAcuallPos().getPositionID() + " with Timestamp " + 
				vehicle.getAcuallPos().getTime() + " was added in Vehicle " + vehicle.getOrigin().toString() 
				+ "-" + vehicle.getRegistrationNumber() + " -> First Point of Transit");
			}
		/**
		 * If there is a actual Point, there are two options : it's the end of the travel (A-X) or only the next station (D-X)
		 * the actual point would be set to the last point and the new point would be the new actual point
		 */
		}else if(point.getPositionID().substring(1, point.getPositionID().length()).equals("D")) {
			vehicle.setLastPos(vehicle.getAcuallPos());
			vehicle.setAcuallPos(point);
						
			setKm(getKmFromStartPOToEndPO(vehicle.getAcuallPos(), vehicle.getLastPos(), vehicle));
			dbconnection.saveFirstPointOfTransit(vehicle,vehicle.getLastPos() , getKm(), true);
			dbconnection.saveFirstPointOfTransit(vehicle, vehicle.getAcuallPos(), getKm(), false);
			runingTransit = true;
			lFVehicle(vehicle, dbconnection.getBiggestTraficTimeFromPoint(vehicle.getAcuallPos()));
			
			LOGGER.fine("Point: " + vehicle.getAcuallPos().getPositionID() + " with Timestamp " + 
					vehicle.getAcuallPos().getTime() + " was added as AcuallPosition Point in Vehicle " + vehicle.getOrigin().toString() 
					+ "-" + vehicle.getRegistrationNumber() + " and Point: " + vehicle.getLastPos().getPositionID() + " with Timestamp " + 
							vehicle.getLastPos().getTime() + " is now LastPosition" );
		/**
		 * additional to the upper changing part, here do we set the absolut end Position too and write into the DB
		 */
		}else if(point.getPositionID().substring(1, point.getPositionID().length()).equals("A")) {
			vehicle.setLastPos(vehicle.getAcuallPos());
			vehicle.setAcuallPos(point);
			setAbsolutEndPosition(point);
			// get full km
			setKm(getKmFromStartPOToEndPO(vehicle.getAcuallPos(), vehicle.getLastPos(), vehicle));
			
			// create the final transit from the start point/Time and end point/time
			FinishedTransits finishedTransit = 
					new FinishedTransits(getAbsolutStartPosition(), getAbsolutStartTime(), 
							getAbsolutEndPosition(), getAbsolutEndTime(), getKm());
			vehicle.addToArrayList(finishedTransit);
			dbconnection.saveFullTransit(vehicle);
			dbconnection.deleteStartedTransit(vehicle.getLastPos(), vehicle.getAcuallPos());
			runingTransit = false;
			
			LOGGER.fine("Point: " + vehicle.getAcuallPos().getPositionID() + " with Timestamp " + 
					vehicle.getAcuallPos().getTime() + " was added as AcuallPosition Point in Vehicle " + vehicle.getOrigin().toString() 
					+ "-" + vehicle.getRegistrationNumber() + " and Point: " + vehicle.getLastPos().getPositionID() + " with Timestamp " + 
							vehicle.getLastPos().getTime() + " is now LastPosition" );
		}
	}
	/**
	 * method calculated the km of all roads on this traffic 
	 * -> additive calculating
	 * 
	 * @param startPO start Point
	 * @param endPO end point
	 * @param vehicle actual using vehicle
	 * @return full km
	 */
	private double getKmFromStartPOToEndPO(Position startPO, Position endPO, Vehicle vehicle) {
		vehicle.setKm(vehicle.getKm() + dbconnection.getKM(startPO, endPO));
		km = dbconnection.getKM(startPO, endPO);
		LOGGER.fine("Saving done for km in Vehicle: " + vehicle.getOrigin().toString() 
				+ "-" + vehicle.getRegistrationNumber());
		return km;
	}
	
	/**
	 * method gets the biggest Travel time of all available routes from a given point
	 * 
	 * @param position used position
	 * @return minutes as an int
	 */
	private int getBiggestTravelTime(Position position) {
		return dbconnection.getBiggestTraficTimeFromPoint(position);
	}
	
	/**
	 * the method sets a time using the given time. when the time has
	 * expired, the given point would be compared with the acutalPos.
	 * If the point is the same, the flag "TrafficJam" is set
	 * 
	 * @param vehicle actual vehicle
	 * @param minutes biggest value for the possible transits 
	 * @return boolean if in jam or not
	 */
	private boolean lFVehicle(Vehicle vehicle, int minutes) {
		
		CompletableFuture<Boolean> filterPointFuture = CompletableFuture.supplyAsync(() -> 
		{
			Position point1 = vehicle.getAcuallPos();
			try {
				Thread.sleep(minutes * 60);
				if(point1.equals(vehicle.getAcuallPos())) {
					dbconnection.setTraficJamFlag(vehicle);
					//TODO Counter for Vehicle LOST
					return false;
				}
			}catch (InterruptedException e) {
            	e.printStackTrace();				
			}
			return runingTransit;
		});
		
		try {
			runingTransit = filterPointFuture.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return runingTransit;
	}
	
	
	public Position getPosition() {
		return position;
	}
	public void setPosition(Position position) {
		this.position = position;
	}
	public LocalDate getPositionDate() {
		return positionDate;
	}

	public void setPositionDate(LocalDate positionDate) {
		this.positionDate = positionDate;
}

	public double getKm() {
		return km;
	}
	public void setKm(double km) {
		this.km = km;
	}

	public Position getAbsolutStartPosition() {
		return absolutStartPosition;
	}

	public void setAbsolutStartPosition(Position absolutStartPosition) {
		this.absolutStartPosition = absolutStartPosition;
	}

	public Position getAbsolutEndPosition() {
		return absolutEndPosition;
	}

	public void setAbsolutEndPosition(Position absolutEndPosition) {
		this.absolutEndPosition = absolutEndPosition;
	}

	public LocalDate getAbsolutStartTime() {
		return absolutStartTime;
	}

	public void setAbsolutStartTime(LocalDate absolutStartTime) {
		this.absolutStartTime = absolutStartTime;
	}

	public LocalDate getAbsolutEndTime() {
		return absolutEndTime;
	}

	public void setAbsolutEndTime(LocalDate absolutEndTime) {
		this.absolutEndTime = absolutEndTime;
	}
	
	
	
}
