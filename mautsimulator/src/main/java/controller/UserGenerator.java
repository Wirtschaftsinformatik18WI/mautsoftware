package controller;

import java.util.ArrayList;
import model.Origin;
import model.User;
import model.Vehicle;

public class UserGenerator {
	
	int numberOfUsers;
	ArrayList<User> userList = new ArrayList<User>();


	

	public void generateUser() {
		
			User user = new User("0001", "Mustermann", "Max", "Musterstraße", "02826", "40", "Musterstadt", "0123456789");
			userList.add(user);
	}
	
	
	
	
	public ArrayList<User> getUserList() {
		return userList;
	}


	public void setUserList(ArrayList<User> userList) {
		this.userList = userList;
	}
	
	
	
	
}
