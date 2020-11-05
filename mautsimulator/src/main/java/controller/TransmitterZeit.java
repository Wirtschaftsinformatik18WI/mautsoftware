package controller;

import java.util.TimerTask;
import model.TransmitterData;

public class TransmitterZeit extends TimerTask{

	@Override
	public void run() {
		
		TransmitterData data = new TransmitterData();
		Simulationszeit simulationszeit = new Simulationszeit();
		Generator gen = new Generator();
		
		String zeit = simulationszeit.getSimulTime();
		
		//Zeitdaten
		data.setTime(simulationszeit.getSimulTime());
		
		//KFZ Daten
		data.setRegistrationNumber(gen.generateVehicle().get(0).getRegistrationNumber());
		data.setOrigin(gen.generateVehicle().get(0).getOrigin());
		
		//Peilsenderdaten
		//data.setLocation(location);
		//data.setTyp(typ);
		

		//Ausgabe
		System.out.println("------------------------------");
		System.out.println("Auto passiert Mautsäule");
		System.out.println("Standortdaten:");
		System.out.println("Location:");
		System.out.println("Zeit: " + simulationszeit.getSimulTime());
		System.out.println("Datum: " + data.getDate());
		System.out.println("Kennzeichen: " + data.getRegistrationNumber());
		System.out.println("Herkunftsland: " + data.getOrigin());
		System.out.println("Peilsendertyp: ");
		System.out.println("------------------------------");
		
		
		/*
		//Test ob eine Abfahrt passiert wurde
		if(data.getTyp().equals(TransmitterTyp.Abfahrt)){
			System.out.println("Auto abgefahren!");
			//Task canceln
		}
		*/
				
		
	}
}