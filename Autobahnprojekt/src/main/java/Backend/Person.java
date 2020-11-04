package Backend;

public class Person {
	private String name;
	private String surname;
	private String ID;
	private String street;
	private String postcode;
	private String hnumber;
	private String city;
	private String telephone;
	private boolean firma;
	
	
	public Person (String ID, String name, String surname, String street, String postcode,
			String hnumber, String city, String telephone) {
		
		this.ID = ID;
		this.name = name;
		this.surname = surname;
		this.street = street;
		this.postcode = postcode;
		this.hnumber = hnumber;
		this.city = city;
		this.telephone = telephone;
		this.firma = false;
		}
	public Person (String ID, String name, String street, String postcode,
			String hnumber, String city, String telephone) {
		this.ID = ID;
		this.name = name;
		this.street = street;
		this.postcode = postcode;
		this.hnumber = hnumber;
		this.city = city;
		this.telephone = telephone;
		this.firma = true;
	}
	
	
	
	
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getPostcode() {
		return postcode;
	}
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	public String getHnumber() {
		return hnumber;
	}
	public void setHnumber(String hnumber) {
		this.hnumber = hnumber;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public boolean isFirma() {
		return firma;
	}
	public void setFirma(boolean firma) {
		this.firma = firma;
	}
	public String getID() {
		return ID;
	}
	
	
}
