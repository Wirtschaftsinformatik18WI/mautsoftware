package Backend;

import java.time.LocalDate;
import java.util.UUID;

public class testclassforProgramm {

	// in dieser Klasse wird der Ablauf imitiert und soll dazu dienen, eine leitfaden für das Programm zu sein
	
	//erstellen eines Fahrzeugs wir hier simuliert - normalerweise werden die daten aus der Datenbank abgerufen
	//  oder via der Webanwendung erstellt und dan hier bereit erstellt.
	
	String standort1 = "D-BZ";
	String standort2 = "D-DD";
	String standort3 = "A-B";
	
	
	private User testperson = new User("01","Itarikon","Backerstreet " , "012765","3" , "London", "0123456789");
	
	private Vehicle testfahrzeug = new Vehicle(Origin.DE, "K 407", testperson);
	
	private Position point1 = new Position(standort1, "Bautzen");
	private Position point2 = new Position(standort1, "Dresden");
	private Position point3 = new Position(standort1, "Berlin");
	private LocalDate date1 = LocalDate.now();
	
	private Transit transit1 = new Transit(point1, date1);
	
	
	
	
	
}
