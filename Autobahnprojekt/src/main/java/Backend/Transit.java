package Backend;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import database.DatabaseConnection;

public class Transit {


	private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy - hh:mm");
	private Position position;
	private LocalDate positionDate;
	
	private boolean runingTransit;
	
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

	public boolean filterPoint(Position point , Vehicle vehicle) {
		if(vehicle.getLastPos() == null) {
			if(point.getPositionID().substring(1, point.getPositionID().length()).equals("A")) {
				//TODO  Fehlermeldung
			}else {
				vehicle.setLastPos(point);
				setAbsolutStartPosition(point);
				setAbsolutStartTime(point.getTime());
				dbconnection.saveFirstPointOfTransit(vehicle, point, 0, true);
				this.runingTransit = true;
			}
		}else if(point.getPositionID().substring(1, point.getPositionID().length()).equals("D")) {
			vehicle.setAcuallPos(vehicle.getLastPos());
			vehicle.setLastPos(point);
			setKm(getKmFromStartPOToEndPO(vehicle.getAcuallPos(), vehicle.getLastPos(), vehicle));
			dbconnection.saveFirstPointOfTransit(vehicle,vehicle.getLastPos() , getKm(), true);
			dbconnection.saveFirstPointOfTransit(vehicle, vehicle.getAcuallPos(), getKm(), false);
			
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
			
		}
		return this.runingTransit;
	}
	
	private double getKmFromStartPOToEndPO(Position startPO, Position endPO, Vehicle vehicle) {
		vehicle.setKm(vehicle.getKm() + dbconnection.getKM(startPO, endPO));
		km = dbconnection.getKM(startPO, endPO);
		return km;
	}
	
	
	private LocalDate getBiggestTravelTime(Position position) {
		return dbconnection.getBiggestTraficTimeFromPoint(position);
		
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
