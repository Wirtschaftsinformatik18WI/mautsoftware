package Backend;

import java.util.ArrayList;

public class Vehicle {
	private Origin origin;
	private String registrationNumber = new String();
	private ArrayList<Transit> transitList = new ArrayList<>();
	private Position lastPos;
	private Position acuallPos;
	
	public Vehicle (Origin origin, String registrationNumber) {
		this.origin = origin;
		this.registrationNumber = registrationNumber;
	}

	
	
	
	
	
	public Origin getOrigin() {
		return origin;
	}

	public String getRegistrationNumber() {
		return registrationNumber;
	}

	public ArrayList<Transit> getTransitList() {
		return transitList;
	}

	public Position getLastPos() {
		return lastPos;
	}

	public Position getAcuallPos() {
		return acuallPos;
	}
	
	
	
	
}
