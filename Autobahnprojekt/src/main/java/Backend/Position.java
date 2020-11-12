package Backend;

import java.time.LocalDate;
import java.util.UUID;

/**
 * 
 * class to:
 * 		~ create a Point with time 
 * 		 
 * 
 * @author luisa.thiel Mail: luisa.thiel@cideon.com
 * Company: Cideon Software & Services GmbH & Co. KG.
 * created at 04.11.2020
 */
public class Position {
	private String positionID;
	private LocalDate time;
	private String description;
	
	public Position(String positionID, LocalDate time, String description) {
		this.positionID = positionID;
		this.time = time;
		this.description = description;
	}
	
	/**
	 * @return return a String with the Abfahrt and Durchfahrt text
	 */
	@Override
	public String toString() {
		String description = "";
		if(positionID.substring(1, positionID.length()).equals("A")) {
			description = "Abfahrt -" + positionID.substring(1, positionID.length());
		}else {
			description = "Durchfahrt -" + positionID.substring(1, positionID.length());
		}
		return description;
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

	public String getDescription() {
		return description;
	}

	
	
}
