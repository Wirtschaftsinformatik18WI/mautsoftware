package controller;

import java.util.Calendar;
import java.util.Timer;
import model.Vehicle;
import model.Zeit;

public class App {

	public static void main(String[] args) {
		
		
		System.out.println("Simulation startet");
		System.out.println("");
		System.out.println("Folgende Autos werden generiert:");
		
		Generator gen = new Generator();
		for (Vehicle v : gen.generateVehicle()) {
			System.out.println("Herkunft: " + v.getOrigin() + " | " + "Kennzeichen: " + v.getRegistrationNumber());
		}
		
		System.out.println("");
		Zeit zeit = new Zeit();
		System.out.println("Das erste Auto mit dem Kennzeichen: " + gen.generateVehicle().get(0).getRegistrationNumber() + " fährt um " + zeit.getAktualTime() + " los!");
	}
	
	
}
		
		
	
		
		/*Timer timer = new Timer();
		
		timer.schedule(new Task(), 2000, 5000);*/
		