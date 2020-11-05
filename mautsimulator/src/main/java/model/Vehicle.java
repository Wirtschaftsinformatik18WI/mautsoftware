package model;

import java.util.ArrayList;

public class Vehicle {
	
	private Origin origin;
    private String registrationNumber = new String();
    private ArrayList<String> transitList = new ArrayList<>();
    private String lastPos;
    private String actuallPos;
    
    public Vehicle (Origin origin, String registrationNumber) {
        this.origin = origin;
        this.registrationNumber = registrationNumber;
    }

	public Origin getOrigin() {
		return origin;
	}

	public void setOrigin(Origin origin) {
		this.origin = origin;
	}

	public String getRegistrationNumber() {
		return registrationNumber;
	}

	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}

	public ArrayList<String> getTransitList() {
		return transitList;
	}

	public void setTransitList(ArrayList<String> transitList) {
		this.transitList = transitList;
	}

	public String getLastPos() {
		return lastPos;
	}

	public void setLastPos(String lastPos) {
		this.lastPos = lastPos;
	}

	public String getAcuallPos() {
		return actuallPos;
	}

	public void setAcuallPos(String acuallPos) {
		this.actuallPos = acuallPos;
	}

}
