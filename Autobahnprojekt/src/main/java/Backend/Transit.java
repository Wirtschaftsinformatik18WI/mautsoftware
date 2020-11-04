package Backend;

import java.util.Date;

import database.DatabaseConnection;

public class Transit {

	private Position startPO;
	private Position endPO;
	private Date startDate;
	private Date endDate;
	private double km;
	
	private DatabaseConnection dbconnection = new DatabaseConnection();
	
	public Transit(Position startPO, Date startDate) {
		this.startPO = startPO;
		this.startDate = startDate;
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

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public double getKm() {
		return km;
	}

	public void setKm(double km) {
		this.km = km;
	}
	
	
	
}
