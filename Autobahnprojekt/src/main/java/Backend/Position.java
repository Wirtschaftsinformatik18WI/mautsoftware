package Backend;

import java.util.UUID;

public class Position {
	private UUID positionID;
	private String description;
	
	public Position(UUID positionID, String description) {
		this.positionID = positionID;
		this.description = description;
	}

	public UUID getPositionID() {
		return positionID;
	}

	public String getDescription() {
		return description;
	}
	
	
}
