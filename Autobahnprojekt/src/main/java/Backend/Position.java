package Backend;

import java.time.LocalDate;
import java.util.UUID;

public class Position {
	private String positionID;
	private LocalDate time;
	
	public Position(String positionID, LocalDate time) {
		this.positionID = positionID;
		this.time = time;
	}

	public Position(String positionID) {
		this.positionID = positionID;
	}
	
	public String getPositionID() {
		return positionID;
	}

	public LocalDate getTime() {
		return time;
	}

	
	
}
