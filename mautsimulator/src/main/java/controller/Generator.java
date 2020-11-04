package controller;

import java.util.ArrayList;
import model.Origin;
import model.User;
import model.Vehicle;

public class Generator {
	
	
	int numberOfVehicle;
	ArrayList<Vehicle> vehicleList = new ArrayList<Vehicle>();


	public void generateVehicle() {
		
			User user = new User("0001", "Mustermann", "Max", "Musterstraße", "02826", "40", "Musterstadt", "0123456789");
			Vehicle vehicle = new Vehicle(Origin.DE, "DD-KM-500", user);
			vehicleList.add(vehicle);
	}
	

}