package controller;

import java.util.Timer;
import model.DrivingTime;
import model.Vehicle;

public class App {

	public static void main(String[] args) {
		
		System.out.println("Simulation startet");
		System.out.println("");
		System.out.println("Folgende Autos werden generiert:");
		System.out.println("");
		
		Generator gen = new Generator();
		for (Vehicle v : gen.generateVehicle()) {
			System.out.println("Herkunft: " + v.getOrigin() + " | " + "Kennzeichen: " + v.getRegistrationNumber());
		}
		
		System.out.println("");
		
		//Simulationszeit starten
		Timer simulTimer = new Timer();
		Simulationszeit simulationszeit = new Simulationszeit();
		simulationszeit.setHour(12);
		simulationszeit.setMinute(00);
		simulationszeit.setSecond(45);
		System.out.println("Startzeit: " + simulationszeit.getSimulTime());
		simulTimer.schedule(simulationszeit, 0, 500);
		
		
		System.out.println("");
		System.out.println("Das erste Auto mit dem Kennzeichen: " + gen.generateVehicle().get(0).getRegistrationNumber() + " fährt um " + simulationszeit.getSimulTime() + " los!");
		System.out.println("");
		
		
				
		Timer timer = new Timer();
		
		
		DrivingTime time = new DrivingTime();
		Task task = new Task();
		
		
		timer.schedule(task, 3000, 1000);
		task.cancel();
		
		
		
		

	}				
}	