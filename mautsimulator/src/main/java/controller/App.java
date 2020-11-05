package controller;

import java.util.Timer;
import model.DrivingTime;
import model.Vehicle;
import model.Zeit;

public class App {

	public static void main(String[] args) {
		
		/*System.out.println("Simulation startet");
		System.out.println("");
		System.out.println("Folgende Autos werden generiert:");
		
		Generator gen = new Generator();
		for (Vehicle v : gen.generateVehicle()) {
			System.out.println("Herkunft: " + v.getOrigin() + " | " + "Kennzeichen: " + v.getRegistrationNumber());
		}
		
		System.out.println("");
		
		Zeit zeit = new Zeit();
		System.out.println("Das erste Auto mit dem Kennzeichen: " + gen.generateVehicle().get(0).getRegistrationNumber() + " fährt um " + zeit.getAktualTime() + " los!");
		System.out.println("");
		
		
				
		Timer timer = new Timer();
		
		
		DrivingTime time = new DrivingTime();
		Task task = new Task();
		
		
		timer.schedule(task, 3000, 1000);
		task.cancel();*/
		
		
		
		//Simulationszeit
		Timer simulTimer = new Timer();
		Simulationszeit simulationszeit = new Simulationszeit();
		simulationszeit.setHour(12);
		simulationszeit.setMinute(59);
		simulationszeit.setSecond(45);
		simulTimer.schedule(simulationszeit, 0, 10);

	}				
}	