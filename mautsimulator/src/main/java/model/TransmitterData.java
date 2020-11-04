package model;


public class TransmitterData {
	
	private String location;
	private String registrationNumber;
	private String origin;
	private String date;
	private String time;
	private TransmitterTyp typ;
	
	
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getRegistrationNumber() {
		return registrationNumber;
	}
	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}
	public String getOrigin() {
		return origin;
	}
	public void setOrigin(String origin) {
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
