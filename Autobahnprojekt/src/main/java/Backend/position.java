package Backend;

public class position {
	private String positionID;
	private String description;
	
	public position(String positionID, String description) {
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
