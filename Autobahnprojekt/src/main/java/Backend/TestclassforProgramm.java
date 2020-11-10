package Backend;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Supplier;
import java.util.logging.Logger;

import database.DatabaseConnection;

public class TestclassforProgramm {
	
	Vehicle vehicle;
	Position point;

	// in dieser Klasse wird der Ablauf imitiert und soll dazu dienen, eine leitfaden für das Programm zu sein
	
	//erstellen eines Fahrzeugs wir hier simuliert - normalerweise werden die daten aus der Datenbank abgerufen
	//  oder via der Webanwendung erstellt und dan hier bereit erstellt.
	
	
	public static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
//	LOGGER.setLevel(Level.ALL);

	DatabaseConnection con; //TODO would fail
	
	
	
	// create private Transit transit1 = new Transit();
	
	
	
	private void createAProcess() {
		
		long time = 1;
		
		while(time >0) {
			if (time % 5 == 0) {
				ArrayList<DBInputVehicleAndPoint> dbInputVehicleAndPoint 
				= con.getAllPointsAndVehiclesFromArrivingSpot();
				for (DBInputVehicleAndPoint dbInput : dbInputVehicleAndPoint) {
					this.vehicle = dbInput.getVehicle();
					this.point = dbInput.getPosition();
					Transit transit = new Transit(point, point.getTime());
					
					CompletableFuture<Transit> filterPointFuture = CompletableFuture.supplyAsync(() -> 
					{
						Transit transit1 = new Transit(point, point.getTime());
						transit1.filterPoint(point, vehicle);
						return transit1;
					});
					
					try {
						transit = filterPointFuture.get();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (ExecutionException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			}
			/**
			 * Every Day *
			 * 
			 * Checks all Traffic Jams and sollect them to iterate throw the non traffic jam vehicle to
			 * get the traffic offender
			 */
			if(time % 1440 == 0) { 
				ArrayList<Vehicle> dbInputTraficJamTrafics = con.getAllTransitsWithTraficJamFlag();
				int zaehler = 0;
				ArrayList<Vehicle> allTraficJamRouts = new ArrayList<>();
				ArrayList<Vehicle> allOtherTraficsWithoutJam = new ArrayList<>();
//				ArrayList<Vehicle>
				
				for(Vehicle vehicle : dbInputTraficJamTrafics) {
					for (int i = 0; i<=zaehler; i++) {
						if(vehicle.getAcuallPos().equals(dbInputTraficJamTrafics.get(i+1).getAcuallPos()) &&
								vehicle.getLastPos().equals(dbInputTraficJamTrafics.get(i+1).getLastPos())){
//							Wenn beide den gleichen anfangs und endpunkt haben wir der punkt in die 
//							tabelle reingenommen
							if(vehicle.getAcuallPos().getTime().isBefore(dbInputTraficJamTrafics.get(i+1).getAcuallPos().getTime())) {
								//ehere Zeit in der gegeben 
							}else {
								vehicle.setAcuallPos(dbInputTraficJamTrafics.get(i+1).getAcuallPos());
							}
							if(vehicle.getLastPos().getTime().isAfter(dbInputTraficJamTrafics.get(i+1).getLastPos().getTime())) {
								//spätere Zeit in der gegebenen
							}else {
								vehicle.setLastPos(dbInputTraficJamTrafics.get(i+1).getLastPos());
							}
							if(allTraficJamRouts.isEmpty()) {
								allTraficJamRouts.add(vehicle);
							}else {
								for(Vehicle StuckedVehicle : allTraficJamRouts) {
									if(vehicle.getAcuallPos().equals(StuckedVehicle.getAcuallPos()) &&
											vehicle.getLastPos().equals(StuckedVehicle.getLastPos())){
										StuckedVehicle.setAcuallPos(vehicle.getAcuallPos());
										StuckedVehicle.setLastPos(vehicle.getLastPos());
									}else {
										allTraficJamRouts.add(vehicle);
									}
								}
							}
						}
						
					}
					zaehler++;
				}
				
				// Hier muss die Prüfung aller anderen Fahrzeuge erfolgen, ob diese Verkehrssünder sind
				allOtherTraficsWithoutJam.addAll(con.getAllTransitsWithoutTraficJamFlag());
				for(Vehicle noJamVehicle : allOtherTraficsWithoutJam) {
					for(Vehicle jamVehicles : allTraficJamRouts) {
						if(noJamVehicle.getAcuallPos().getPositionID().equals(jamVehicles.getAcuallPos().getPositionID()) &&
								noJamVehicle.getLastPos().getPositionID().equals(jamVehicles.getLastPos().getPositionID())) {
							if((noJamVehicle.getLastPos().getTime().isEqual(jamVehicles.getLastPos().getTime())|| 
									noJamVehicle.getLastPos().getTime().isAfter(jamVehicles.getLastPos().getTime())) && (
									noJamVehicle.getAcuallPos().getTime().isEqual(jamVehicles.getAcuallPos().getTime()) || 
									noJamVehicle.getAcuallPos().getTime().isBefore(jamVehicles.getAcuallPos().getTime()))){
										con.addFlagTrafficOffender(noJamVehicle);
									}
						}
					}
				}
				
			}
			
			/**
			 * Every Week *
			 * 
			 * Get all traffic offender to sent a Report-Mail to police to check if that vehicle was allowed to 
			 * pass the traffic jam
			 * 
			 */
			if(time % 10080 == 0) { 
				ArrayList<Vehicle> vehicleWithTrafficOffenderFlag = con.getAllVehicleWithTrafficOffenderFlag();
				String policeReport = "The possible traffic offender: /n";
				int number = 1;
				for(Vehicle vehicleTrafficOffender : vehicleWithTrafficOffenderFlag) {
					policeReport = policeReport + number + vehicleTrafficOffender.toString()+"/n";
				}
				
				//TODO Mail to police
				
			try {
				Thread.sleep(1000*60); // 1 min Thread sleep
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				LOGGER.severe("Could not pause Thread");
				e.printStackTrace();
			}
			
			}
			
			
			time++;
		}
		
	}
	
	/**
	 * method create a Bill for all vehicle of a given User for a given month
	 * 
	 * @param user acuall User who would like to get his bill
	 * @param month month who are the user looking for
	 */
	
	public void createBillOfAMonth(User user, int month) {
		
		ArrayList<FinishedTransits> alltransit = new ArrayList<>();
		ArrayList<Vehicle> allvehicleFromAUser = new ArrayList<>();
		String allTransitsForBill = "All Transits of " + month + "/n";
		
		allvehicleFromAUser.addAll(con.getVehicle(user));
		for(Vehicle userVehicle : allvehicleFromAUser ) {
			alltransit.addAll(con.getAllTransitFromVehicle(userVehicle, month));
		}
		for(FinishedTransits finishedTransit : alltransit) {
			allTransitsForBill = allTransitsForBill + finishedTransit.toString() + "/n";
		}
		
		
		//TODO send via Mail to User
				
		
	}
	
	
}
