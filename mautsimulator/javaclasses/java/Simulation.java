package java;

import java.Standortdaten.Peilsendertyp;

public class Simulation {
	
	
	public static void main (String arg[]) {
		
		Standortdaten standortdaten = new Standortdaten("DD", "GR-FJ-500", "DE", "03.11.2020", "11:00", Peilsendertyp.Durchfahrt);
	
		standortdaten.ausgeben();
		
	}
}