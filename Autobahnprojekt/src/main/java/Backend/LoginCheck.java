package Backend;

import database.DatabaseConnection;
/**
 * 
 * class to:
 * 		~ check the login Data 
 * 		 
 * 
 * @author mariett.sauer Mail: mariett.sauer@cideon.com
 * Company: Cideon Software & Services GmbH & Co. KG.
 * created at 11.11.2020
 */
public class LoginCheck {
	
	/**
	 * check the given password to the given mail
	 * 
	 * @param email User email
	 * @param password User password
	 * @return true if it's the right passwort to that emali, false if not
	 */
	public boolean checkPassword (String email, String password) {
		DatabaseConnection database = new DatabaseConnection();
		if (database.doesUserExist(email)) {
			String realpassword=database.getPasswordFromEmail(email);
			if (password.equals(realpassword)) {return true;}
		}
		return false;
	}
}
