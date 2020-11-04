package Backend;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import database.DatabaseConnection;

public class Transit {

	private Position startPO;
	private Position endPO;
	private LocalDate startDate;
	private LocalDate endDate;
	private double km;
	private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy - hh:mm");
	private Position absolutStartPosition;
	private Position absolutEndPosition;
	private DatabaseConnection dbconnection = new DatabaseConnection();
	
	public Transit(Position startPO, LocalDate startDate) {
		this.startPO = startPO;
		this.startDate = LocalDate.parse(startDate.toString(), dateTimeFormatter);
	}

	private void filterPoint(Position point) {
		if(startPO == null) {
			if(point.getPositionID().substring(1, point.getPositionID().length()).equals("A")) {
				//TODO  Fehlermeldung
			}else {
				this.startPO = point;
				this.absolutStartPosition = point;
			} 
		}else if(point.getPositionID().substring(1, point.getPositionID().length()).equals("D")) {
			this.endPO = this.startPO;
			this.startPO = point;
			//Berechung Where -> KM aus der DB holen
			
		}else if(point.getPositionID().substring(1, point.getPositionID().length()).equals("A")) {
			this.endPO = this.startPO;
			this.startPO = point;
			this.absolutEndPosition = point;
			//Berechung Where -> KM aus der DB holen
		}
	}
	
	private void addStartPoint(Position startpoint) {
		setStartPO(startpoint);
	}
	
	private void addEndPoint(Position endpoint) {
		setEndPO(endpoint);
	}
	
	private double getKmFromStartPOToEndPO(Position startPO, Position endPO) {
		
		km = dbconnection.getKM(startPO, endPO);
		return km;
	}
	
	
	
	public Position getStartPO() {
		return startPO;
	}

	public void setStartPO(Position startPO) {
		this.startPO = startPO;
	}

	public Position getEndPO() {
		return endPO;
	}

	public void setEndPO(Position endPO) {
		this.endPO = endPO;
	}

	public LocalDate getStartDate() {
		return startDate;
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
