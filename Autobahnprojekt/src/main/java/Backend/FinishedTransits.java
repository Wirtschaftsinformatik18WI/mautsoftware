package Backend;

import java.time.LocalDate;

/**
 * 
 * class to:
 * 		~ create a finished Transit with the main used facts 
 * 		 
 * 
 * @author luisa.thiel Mail: luisa.thiel@cideon.com
 * Company: Cideon Software & Services GmbH & Co. KG.
 * created at 04.11.2020
 */
public class FinishedTransits {

	private Position startPosition;
	private LocalDate startDate;
	
	private Position endPosition;
	private LocalDate endDate;
	
	private double km;
	
	public FinishedTransits(Position startPosition, LocalDate startDate, Position endPosition, LocalDate endDate, double km) {
		
		this.startPosition = startPosition;
		this.startDate = startDate;
		this.endPosition = endPosition;
		this.endDate = endDate;
		this.km = km;
		
	}

	/**
	 * create a String return value to build up a bill
	 * 
	 * @param tax actual or live tax
	 * @param fee actual or live fee
	 * @return a nice string of that finished Transit with startposition + time and endposition+ time and the costs of it
	 */
	public String toString(double tax, double fee) {
		String finishedTransit = "";
		finishedTransit = "Strecke von " + startPosition.getDescription() + " - " + startPosition.getTime() + " nach " +
				endPosition.getDescription() + " - " + endPosition.getTime() + " kostet " + (km*fee*(100+tax))/100 + " Euro";
		
		
		return finishedTransit;
	}
	
	public Position getStartPosition() {
		return startPosition;
	}

	public void setStartPosition(Position startPosition) {
		this.startPosition = startPosition;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public Position getEndPosition() {
		return endPosition;
	}

	public void setEndPosition(Position endPosition) {
		this.endPosition = endPosition;
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
