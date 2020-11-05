package model;


public class TransmitterData {
	
	private Location location;
	private String registrationNumber;
	private Origin origin;
	private String date;
	private String time;
	private TransmitterTyp typ;
	
	
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}
	public String getRegistrationNumber() {
		return registrationNumber;
	}
	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}
	public Origin getOrigin() {
		return origin;
	}
	public void setOrigin(Origin origin) {
		this.origin = origin;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public TransmitterTyp getTyp() {
		return typ;
	}
	public void setTyp(TransmitterTyp typ) {
		this.typ = typ;
	}

}
