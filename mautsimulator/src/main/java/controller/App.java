package controller;

import java.util.Timer;
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
		simulationszeit.setSecond(00);
		System.out.println("Startzeit: " + simulationszeit.getSimulTime());
		System.out.println("");
		simulTimer.schedule(simulationszeit, 0, 500);
		
		
		//Fahrt der Autos starten und Standortdaten sammeln
		Timer transmitterTimer = new Timer();
		TransmitterZeit transmitterZeit = new TransmitterZeit();
		System.out.println("Abfrage der Transmitterdaten startet!");
		transmitterTimer.schedule(transmitterZeit, 2000, 4000);
		
	}				
}	