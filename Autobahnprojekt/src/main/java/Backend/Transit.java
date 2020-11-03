package Backend;

import java.util.Date;

public class Transit {

	private Position startPO;
	private Position endPO;
	private Date startDate;
	private Date endDate;
	private double km;
	
	
	
	
	public Transit(Position startPO, Date startDate) {
		this.startPO = startPO;
		this.startDate = startDate;
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
