package Backend;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Logger;

import database.DatabaseConnection;

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
	
	
	public Transit(Position position, LocalDate positionDate) {
		this.position = position;
		this.positionDate = LocalDate.parse(positionDate.toString(), dateTimeFormatter);
	}

//	public Transit(Position startPO) {
//		this.startPO = startPO;
//	}

	public void filterPoint(Position point , Vehicle vehicle) {
		if(vehicle.getLastPos() == null) {
			if(point.getPositionID().substring(1, point.getPositionID().length()).equals("A")) {
				LOGGER.severe("Vehicle " + vehicle.getOrigin().toString() + "-" + vehicle.getRegistrationNumber()
				+ " coudn not be regristed as a driving vehicle.");
				LOGGER.severe("POLICE WILL BE INFORMED - VEHICLE IS BLIND SIDER");
				
				//TODO  Fehlermeldung
			}else {
				vehicle.setLastPos(point);
				setAbsolutStartPosition(point);
				setAbsolutStartTime(point.getTime());
				dbconnection.saveFirstPointOfTransit(vehicle, point, 0, true);
				lFVehicle(vehicle, dbconnection.getBiggestTraficTimeFromPoint(point));
			}
		}else if(point.getPositionID().substring(1, point.getPositionID().length()).equals("D")) {
			vehicle.setAcuallPos(vehicle.getLastPos());
			vehicle.setLastPos(point);
			setKm(getKmFromStartPOToEndPO(vehicle.getAcuallPos(), vehicle.getLastPos(), vehicle));
			dbconnection.saveFirstPointOfTransit(vehicle,vehicle.getLastPos() , getKm(), true);
			dbconnection.saveFirstPointOfTransit(vehicle, vehicle.getAcuallPos(), getKm(), false);
			runingTransit = true;
			lFVehicle(vehicle, dbconnection.getBiggestTraficTimeFromPoint(vehicle.getAcuallPos()));
			
			
		}else if(point.getPositionID().substring(1, point.getPositionID().length()).equals("A")) {
			vehicle.setAcuallPos(vehicle.getLastPos());
			vehicle.setLastPos(point);
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
			
		}
	}
	
	private double getKmFromStartPOToEndPO(Position startPO, Position endPO, Vehicle vehicle) {
		vehicle.setKm(vehicle.getKm() + dbconnection.getKM(startPO, endPO));
		km = dbconnection.getKM(startPO, endPO);
		return km;
	}
	
	
	private int getBiggestTravelTime(Position position) {
		return dbconnection.getBiggestTraficTimeFromPoint(position);
	}
	
	private boolean lFVehicle(Vehicle vehicle, int minutes) {
		
		CompletableFuture<Boolean> filterPointFuture = CompletableFuture.supplyAsync(() -> 
		{
			Position point1 = vehicle.getAcuallPos();
			try {
				Thread.sleep(minutes * 60);
				if(point1.equals(vehicle.getAcuallPos())) {
					dbconnection.setTraficJamFlag(vehicle);
					return false;
				}
			}catch (InterruptedException e) {
            	e.printStackTrace();				
			}
			return runingTransit;
		});
		
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
