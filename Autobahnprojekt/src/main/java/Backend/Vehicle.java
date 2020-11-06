package Backend;

import java.util.ArrayList;

public class Vehicle {
    private Origin origin;
    private String registrationNumber = new String();
    private ArrayList<Transit> transitList = new ArrayList<>();
    private Position lastPos;
    private Position acuallPos;
    private Position absolutStartPosition;
    private Position absolutEndPosition;
    private User user;
    private double km = 0;
    
    public Vehicle (Origin origin, String registrationNumber, User user) {
        this.origin = origin;
        this.registrationNumber = registrationNumber;
        this.user = user;
    }

    
    
    
    public void addToArrayList(Transit transit) {
    	transitList.add(transit);
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

	public ArrayList<Transit> getTransitList() {
		return transitList;
	}

	public void setTransitList(ArrayList<Transit> transitList) {
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






	public Position getAbsolutStartPosition() {
		return absolutStartPosition;
	}






	public void setAbsolutStartPosition(Position absolutStartPosition) {
		this.absolutStartPosition = absolutStartPosition;
	}






	public Position getAbsolutEndPosition() {
		return absolutEndPosition;
	}






	public void setAbsolutEndPosition(Position absolutEndPosition) {
		this.absolutEndPosition = absolutEndPosition;
	}






	public double getKm() {
		return km;
	}






	public void setKm(double km) {
		this.km = km;
	}

    
    
    
}