package Backend;

import java.util.ArrayList;


/**
 * 
 * class to:
 * 		~ create a vehicle with points 
 * 		 
 * 
 * @author luisa.thiel Mail: luisa.thiel@cideon.com
 * Company: Cideon Software & Services GmbH & Co. KG.
 * created at 04.11.2020
 */
public class Vehicle {
    private Origin origin;
    private String registrationNumber = new String();
    private ArrayList<FinishedTransits> transitList = new ArrayList<>();
    private Position lastPos;
    private Position acuallPos;
    
    private User user;
    private double km = 0;
    
    public Vehicle (Origin origin, String registrationNumber, User user) {
        this.origin = origin;
        this.registrationNumber = registrationNumber;
        this.user = user;
    }

    /**
     * method created a string of the vehicle
     * 
     * @return return of a String from that vehicle with RegNR + Orign + the points and time
     */
    @Override
    public String toString() {
    	String vehicleString = "RegristationNR: " + origin.toString() + "-" + registrationNumber + " | Traffic: From " + 
    			lastPos.getPositionID() + " : " + lastPos.getTime() + " To " + acuallPos.getPositionID() + " : " + acuallPos.getTime();
    	
    	return vehicleString;
    }
    
    
    public void addToArrayList(FinishedTransits transit) {
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

	public ArrayList<FinishedTransits> getTransitList() {
		return transitList;
	}

	public void setTransitList(ArrayList<FinishedTransits> transitList) {
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

	public double getKm() {
		return km;
	}

	public void setKm(double km) {
		this.km = km;
	}

    
    
    
}