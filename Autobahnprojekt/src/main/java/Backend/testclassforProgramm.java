package Backend;

public class testclassforProgramm {

	// in dieser Klasse wird der Ablauf imitiert und soll dazu dienen, eine leitfaden für das Programm zu sein
	
	//erstellen eines Fahrzeugs wir hier simuliert - normalerweise werden die daten aus der Datenbank abgerufen
	//  oder via der Webanwendung erstellt und dan hier bereit erstellt.
	
	private Person testperson = new Person("01","Itarikon","Backerstreet " , "012765","3" , "London", "0123456789");
	
	private Vehicle testfahrzeug = new Vehicle(Origin.DE, "K 407", testperson);
	
	
	
}
