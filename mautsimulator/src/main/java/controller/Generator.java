package controller;

import java.util.ArrayList;
import java.util.Random;

import model.Origin;
import model.User;
import model.Vehicle;


public class Generator {
	
	public ArrayList<Vehicle> generateVehicle() {
		
		ArrayList<Vehicle> vehicleList = new ArrayList<Vehicle>();
		
		User user1 = new User("0001", "Mustermann", "Max", "Musterstraße", "02826", "40", "Musterstadt", "0123456789");
		Vehicle vehicle1 = new Vehicle(Origin.D, "DD-KM-500", user1);
		vehicleList.add(vehicle1);
			
		User user2 = new User("0002", "Müller", "Paul", "Blumenweg", "03056", "32", "Berlin", "0123456789");
		Vehicle vehicle2 = new Vehicle(Origin.D, "GR-KN-450", user2);
		vehicleList.add(vehicle2);
			
		User user3 = new User("0003", "Hempel", "Jens", "Fuchsweg", "04826", "6", "Löbau", "0123456789");
		Vehicle vehicle3 = new Vehicle(Origin.D, "B-LK-560", user3);
		vehicleList.add(vehicle3);
				
		return vehicleList;
	}
	
	//Generator Generiert Transmitterdaten unter simulierter Zeit. Vehicle Datena aus deren Datenbank.

		
}