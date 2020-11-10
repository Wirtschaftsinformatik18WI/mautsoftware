package Backend;

import java.time.LocalDate;

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

	@Override
	public String toString() {
		String finishedTransit = "";
		
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
