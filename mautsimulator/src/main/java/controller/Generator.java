package controller;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;

import model.Origin;
import model.TransmitterData;
import model.User;
import model.Vehicle;

import model.*;

public class Generator {
	
	Random random = new Random();
	
	public ArrayList<Vehicle> generateVehicle() {
		
		ArrayList<Vehicle> vehicleList = new ArrayList<Vehicle>();
		
		Vehicle vehicle1 = new Vehicle(Origin.D, "DD-KM-500");
		vehicleList.add(vehicle1);
			
		Vehicle vehicle2 = new Vehicle(Origin.D, "GR-KN-450");
		vehicleList.add(vehicle2);
			
		Vehicle vehicle3 = new Vehicle(Origin.D, "B-LK-560");
		vehicleList.add(vehicle3);
				
		return vehicleList;
	}
	
	public TransmitterData generateTransmitterData() {
		
		
		//random location value
		int pick = random.nextInt(Location.values().length);
		Location location = Location.values()[pick];
		
		//Origin
		int pick2 = random.nextInt(Origin.values().length);
		Origin origin = Origin.values()[pick2];
		
		
		
		TransmitterData t = new TransmitterData(location, "KM-BZ-200", origin, "20.03.2020", "13:4:20", TransmitterTyp.Durchfahrt);
		
		
		return t;
	}

	
	//get Vehicle from Database
	public ArrayList getVehicleFromDB() {
		
		//List for Vehicle from ArrayList
		ArrayList<Vehicle> vehicleList = new ArrayList<>();
		
		//Data from Database Connection
		Vehicle vehicle = new Vehicle(Origin.D, "KL-DL-200");
		vehicleList.add(vehicle);
		
		return vehicleList;
				
	}
	
	public void startGeneration() {
		//get vehicle from db -- getVehicleFromDB()
		
		//start timer 
		
		//Aller 30 sek. startet ein Auto
		/* vehicle.setActualPosition
		 * 	Zusätzliche Vehicle Attribute? -> estimated arrival | used stop (exampel: stop after 5 durchfahrten)
		 * jede Sekunde ganze ArrayList checken? nach: estimatet arrival etc.
		 * 
		 * 
		 * */
		
		/* ODER: Generiere alle 30 sekunden ein neues Vehicle und Füge es der Liste Hinzu, 
		 * Vehicle mit last postition == Abfahrt werden aus der Liste entfernt
		 * 
		 * 
		 * */
	}
		
}