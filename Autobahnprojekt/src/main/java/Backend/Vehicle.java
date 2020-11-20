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
    private ArrayList<FinishedTransits> transitList = new ArrayList<FinishedTransits>();
    private Position lastPos;
    private Position absolutStartPos;
    private Position acuallPos;
    private Position absolutEndPos;
    private String vid;
    private String TransitID;
    private String description;
    
    private User user;
    private double km = 0;
    
    public Vehicle (Origin origin, String registrationNumber, User user, String vid) {
        this.origin = origin;
        this.registrationNumber = registrationNumber;
        this.user = user;
        this.vid = vid;
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

	public String getVid() {
		return vid;
	}

	public User getUser() {
		return user;
	}

	public Position getAbsolutStartPos() {
		return absolutStartPos;
	}

	public void setAbsolutStartPos(Position absolutStartPos) {
		this.absolutStartPos = absolutStartPos;
	}

	public Position getAbsolutEndPos() {
		return absolutEndPos;
	}

	public void setAbsolutEndPos(Position absolutEndPos) {
		this.absolutEndPos = absolutEndPos;
	}

	public String getTransitID() {
		return TransitID;
	}

	public void setTransitID(String transitID) {
		TransitID = transitID;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	
    
    
    
}