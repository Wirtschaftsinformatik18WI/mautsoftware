package Backend;

import java.util.ArrayList;

public class vehicle {
	private origin origin;
	private String registrationNumber = new String();
	private ArrayList<String> transitList = new ArrayList<>();
	private position lastPos;
	private position acuallPos;
	
	public vehicle (origin origin, String registrationNumber) {
		this.origin = origin;
		this.registrationNumber = registrationNumber;
	}
	
}
