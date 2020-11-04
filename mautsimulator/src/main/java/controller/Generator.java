package controller;

import java.util.ArrayList;
import model.Origin;
import model.User;
import model.Vehicle;

public class Generator {
	
	
	int numberOfVehicle;
	ArrayList<Vehicle> vehicleList = new ArrayList<Vehicle>();


	public ArrayList<Vehicle> generateVehicle() {
		
			User user = new User("0001", "Mustermann", "Max", "Musterstraße", "02826", "40", "Musterstadt", "0123456789");
			Vehicle vehicle = new Vehicle(Origin.DE, "DD-KM-500", user);
			vehicleList.add(vehicle);
			
			User user2 = new User("0001", "Mustermann", "Max", "Musterstraße", "02826", "40", "Musterstadt", "0123456789");
			Vehicle vehicle2 = new Vehicle(Origin.DE, "DD-KM-505", user2);
			vehicleList.add(vehicle2);
			
			User user3 = new User("0001", "Mustermann", "Max", "Musterstraße", "02826", "40", "Musterstadt", "0123456789");
			Vehicle vehicle3 = new Vehicle(Origin.DE, "DD-KM-510", user3);
			vehicleList.add(vehicle3);
			
			return vehicleList;
	}
	

}