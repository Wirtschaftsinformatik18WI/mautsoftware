package Backend;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import database.DatabaseConnection;

public class Transit {


	private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy - hh:mm");
	private Position position;
	private LocalDate positionDate;

	private Position startPO;
	private Position endPO;
	private LocalDate startDate;
	private LocalDate endDate;
	private double km;
	
	private DatabaseConnection dbconnection = new DatabaseConnection();
	
	
	public Transit(Position position, LocalDate positionDate) {
		this.position = position;
		this.positionDate = LocalDate.parse(positionDate.toString(), dateTimeFormatter);
	}

	public Transit(Position startPO) {
		this.startPO = startPO;
		this.startDate = startPO.getTime();
	}

	public void filterPoint(Position point , Vehicle vehicle) {
		if(vehicle.getLastPos() == null) {
			if(point.getPositionID().substring(1, point.getPositionID().length()).equals("A")) {
				//TODO  Fehlermeldung
			}else {
				vehicle.setLastPos(point);
				vehicle.setAbsolutStartPosition(point);
				dbconnection.saveFirstPointOfTransit(vehicle);
			}
		}else if(point.getPositionID().substring(1, point.getPositionID().length()).equals("D")) {
			vehicle.setAcuallPos(vehicle.getLastPos());
			vehicle.setLastPos(point);
			getKmFromStartPOToEndPO(vehicle.getAcuallPos(), vehicle.getLastPos(), vehicle);
			//Berechung Where -> KM aus der DB holen
			
		}else if(point.getPositionID().substring(1, point.getPositionID().length()).equals("A")) {
			vehicle.setAcuallPos(vehicle.getLastPos());
			vehicle.setLastPos(point);
			vehicle.setAbsolutEndPosition(point);
			
			getKmFromStartPOToEndPO(vehicle.getAcuallPos(), vehicle.getLastPos(), vehicle);
			//Berechung Where -> KM aus der DB holen
			vehicle.addToArrayList(this);
			dbconnection.saveFullTransit(vehicle);
		}
	}
	
	private double getKmFromStartPOToEndPO(Position startPO, Position endPO, Vehicle vehicle) {
		vehicle.setKm(vehicle.getKm() + dbconnection.getKM(startPO, endPO));
		km = dbconnection.getKM(startPO, endPO);
		return km;
	}
	
	
	
	public Position getStartPO() {
		return startPO;
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
	public LocalDate getStartDate() {
		return startDate;
	}
	public void setPositionDate(LocalDate positionDate) {
		this.positionDate = positionDate;
}
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	public LocalDate getEndDate() {
		return endDate;
	}
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
	public double getKm() {
		return km;
	}
	public void setKm(double km) {
		this.km = km;
	}
	
	
	
}
