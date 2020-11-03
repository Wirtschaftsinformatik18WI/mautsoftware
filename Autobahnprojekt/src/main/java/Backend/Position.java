package Backend;

public class Position {
	private String positionID;
	private String description;
	
	public Position(String positionID, String description) {
		this.positionID = positionID;
		this.description = description;
	}

	public String getPositionID() {
		return positionID;
	}

	public String getDescription() {
		return description;
	}
	
	
}
