package controller;

import java.util.ArrayList;

import model.User;

public class UserGenerator {
	
	//Data from Database
	public ArrayList generateUser(int count) {
		
		ArrayList<User> userList= new ArrayList<User>();
		
		for(int i=0;i<=count;i++) {
			
			//Daten aus der Datenbank ziehen
			
			User user = new User("02134", "Heinz", "Street Str.", "02393", "12", "Stadt", "023455235");
			
			userList.add(user);
		}
		return userList;
			
	}
	
	public ArrayList randomUser() {
		ArrayList<User> userList= new ArrayList<User>();
		
		User user1 = new User("02134", "Heinz", "Street Str.", "02393", "12", "Stadt", "023455235");
		userList.add(user1);
		User user2 = new User("02135", "Heinz1", "Street Str.", "02393", "15", "Stadt", "023455235");
		userList.add(user2);
		User user3 = new User("02137", "Heinz2", "Street Str.", "02393", "20", "Stadt", "023455235");
		userList.add(user3);
		
		return userList;
	}
	
}
