package Backend;

/**
 * 
 * class to:
 * 		~ create a User 
 * 		~ edit a user 
 * 
 * @author luisa.thiel Mail: luisa.thiel@cideon.com
 * Company: Cideon Software & Services GmbH & Co. KG.
 * created at 04.11.2020
 */
public class User {
	private String name;
	private String surname;
	private String street;
	private String postcode;
	private String hnumber;
	private String city;
	private String telephone;
	private String eMail;
	private boolean firma;
	private String password;
	private String country;
	
	
	public User (String eMail, String name, String surname, String street, String postcode,
			String hnumber, String city, String telephone, String password, String country) {
		
		this.eMail = eMail;
		this.name = name;
		this.surname = surname;
		this.street = street;
		this.postcode = postcode;
		this.hnumber = hnumber;
		this.city = city;
		this.telephone = telephone;
		this.firma = false;
		this.password = password;
		this.country = country;
		}
	public User (String eMail, String name, String street, String postcode,
			String hnumber, String city, String telephone, String password, String country) {
		this.eMail = eMail;
		this.name = name;
		this.street = street;
		this.postcode = postcode;
		this.hnumber = hnumber;
		this.city = city;
		this.telephone = telephone;
		this.firma = true;
		this.password = password;
		this.country = country;
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

	public String geteMail() {
		return eMail;
	}
	public void seteMail(String eMail) {
		this.eMail = eMail;
	}
	public String getPasswort() {
		return password;
	}
	public void setPasswort(String passwort) {
		this.password = passwort;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	
	
}
