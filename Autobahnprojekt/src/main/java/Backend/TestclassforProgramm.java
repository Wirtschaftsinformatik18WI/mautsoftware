package Backend;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;
import java.util.logging.Logger;

import database.DatabaseConnection;

public class TestclassforProgramm {

	// in dieser Klasse wird der Ablauf imitiert und soll dazu dienen, eine leitfaden für das Programm zu sein
	
	//erstellen eines Fahrzeugs wir hier simuliert - normalerweise werden die daten aus der Datenbank abgerufen
	//  oder via der Webanwendung erstellt und dan hier bereit erstellt.
	
	
	public static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
//	LOGGER.setLevel(Level.ALL);


	String standort1 = "D-BZ";
	String standort2 = "D-DD";
	String standort3 = "A-B";
	
	Origin origin = Origin.D;
	String registrationNr = "K407";
	DatabaseConnection con; //TODO would fail
		
	
	private Position point1 = new Position(standort1, LocalDate.now());
	private Position point2 = new Position(standort1, LocalDate.now().plusDays(1));
	private Position point3 = new Position(standort1, LocalDate.now().plusDays(2));

	private User user = new User("yahoo.com", "Luisa", "Thiel", "Dresdner Strasse","01877" , "3", "03591530636", "password", "Deutschland");
	
	
	LocalDate monatsbetrachtung = LocalDate.now();
	
	
	
	// create private Transit transit1 = new Transit();
	
	
	
	private void createAProcess() {
		
		long time = 1;
		
		while(time >0) {
			if (time % 5 == 0) {
				ArrayList<DBInputVehicleAndPoint> dbInputVehicleAndPoint 
				= con.getAllPointsAndVehiclesFromArrivingSpot();
				for (DBInputVehicleAndPoint dbInput : dbInputVehicleAndPoint) {
					Vehicle vehicle = dbInput.getVehicle();
					Position point = dbInput.getPosition();
					Transit transit = new Transit(point, point.getTime());
					
					CompletableFuture<Transit> filterPointFuture = CompletableFuture.supplyAsync(() -> 
					{
						transit.filterPoint(point, vehicle);
						return transit;
					});
				}
				
			}
			try {
				Thread.sleep(60);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				LOGGER.severe("Could not ");
				e.printStackTrace();
			}
			time++;
		}
		
		
		
		
		
		
		// aufbau des Use-Case der Annahme von Punkten eines Fahrzeugs und speichern dieser Punkte in der Datenbank
		
		// includes the function to filter traffic jam 
		
		Vehicle testfahrzeug = con.getVehicleByRegistrationNr(origin, registrationNr);
		Transit transit = new Transit(point1, LocalDate.now());
		
		
		transit.filterPoint(point1, testfahrzeug);
		transit.filterPoint(point2, testfahrzeug);
		transit.filterPoint(point3, testfahrzeug);
		
		//Promise lösung
//		while(transit.getAbsolutEndPosition()== null 
//				|| LocalDate.now().equals(transit.getAbsolutStartTime().plusDays(2))) {
			
			
//		}
		
		//Aufruf des CF mit name.get();
		
		
		System.out.println(testfahrzeug.getKm()); // es sollten 256Km sein
		
		
		
		
		
		// Rechnung aufbauen und Daten liefern
		
		//Vehicle[] allvehicle = con.getAllVehicleFromUser(user);
		ArrayList<FinishedTransits> alltransit = new ArrayList<>();
		ArrayList<Vehicle> allvehicle = new ArrayList<>();
		
		allvehicle.addAll(con.getVehicle(user));
		
		for(Vehicle v : allvehicle ) {
			alltransit.addAll(con.getAllTransitFromVehicle(v,monatsbetrachtung));
		}
		
		//TODO LF: fancy function to build up a bill and send via Mail
		
		
		
		// 
		
		
		
	}
	
	
}
