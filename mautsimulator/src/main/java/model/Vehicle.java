package model;

import java.util.ArrayList;

public class Vehicle {
	
	private Origin origin;
    private String registrationNumber = new String();
    private ArrayList<String> transitList = new ArrayList<>();
    private Position lastPos;
    private Position acuallPos;
    private User user;
    
    public Vehicle (Origin origin, String registrationNumber, User user) {
        this.origin = origin;
        this.registrationNumber = registrationNumber;
        this.user = user;
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

	public Position getLastPos() {
		return lastPos;
	}

	public void setLastPos(Position lastPos) {
		this.lastPos = lastPos;
	}

	public Position getAcuallPos() {
		return acuallPos;
	}

	public void setAcuallPos(Position acuallPos) {
		this.acuallPos = acuallPos;
	}

}
