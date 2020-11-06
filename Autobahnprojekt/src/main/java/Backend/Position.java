package Backend;

import java.time.LocalDate;
import java.util.UUID;

public class Position {
	private String positionID;
	private String description;
	private LocalDate time;
	
	public Position(String positionID, String description, LocalDate time) {
		this.positionID = positionID;
		this.description = description;
		this.time = time;
	}

	public String getPositionID() {
		return positionID;
	}

	public String getDescription() {
		return description;
	}

	public LocalDate getTime() {
		return time;
	}

	
	
}
