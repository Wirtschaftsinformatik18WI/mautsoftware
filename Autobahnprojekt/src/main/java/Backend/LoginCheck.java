package Backend;

import database.DatabaseConnection;

public class LoginCheck {
	
	public boolean checkPassword (String email, String password) {
		DatabaseConnection database = new DatabaseConnection();
		if (database.doesUserExist(email)) {
			String realpassword=database.getPasswordFromEmail(email);
			if (password.equals(realpassword)) {return true;}
		}
		return false;
	}
}
