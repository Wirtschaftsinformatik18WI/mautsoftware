package Backend;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.logging.Logger;

import database.DatabaseConnection;

/**
 * 
 * Main class to:
 * 		~ check all given Point 
 * 		~ create a User 
 * 		~ Edit a User
 * 		~ create a police report of treffic offender
 * 		~ create a monthly report for user of there transits 
 * 
 * @author luisa.thiel Mail: luisa.thiel@cideon.com
 * Company: Cideon Software & Services GmbH & Co. KG.
 * created at 04.11.2020
 *
 */

//TODO need to change that stupid name -.-

public class MainMethodes {
	
	Vehicle vehicle;
	Position point;
	
	public static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	DatabaseConnection con; //TODO would fail
	
	/**
	 * main method to iterate throw the vehicles and points
	 * 
	 */
	private void startProcess() {
		
		long time = 1;
		
		while(time >0) {
			if (time % 5 == 0) {
				ArrayList<Vehicle> dbInputVehicleAndPoint 
				= con.getAllPointsAndVehiclesFromArrivingSpot();
				for (Vehicle dbInput : dbInputVehicleAndPoint) {
					this.vehicle = dbInput;
					
					/**
					 * outsourced method  thread, so it would be possible to do a better performance
					 */
					CompletableFuture<Transit> filterPointFuture = CompletableFuture.supplyAsync(() -> 
					{
						Transit transit1 = new Transit(point, point.getTime());
						transit1.filterPoint(point, vehicle);
						return transit1;
					});
					
					try {
						Transit transit = filterPointFuture.get();
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
				
				/**
				 * Organize all traffic jams to a Arraylist 
				 */
				for(Vehicle vehicle : dbInputTraficJamTrafics) {
					for (int i = 0; i<=zaehler; i++) {
						/**
						 * check if vehicle Position are the same as a other position of the dbInputTraficJamTrafics array
						 */
						if(vehicle.getAcuallPos().equals(dbInputTraficJamTrafics.get(i+1).getAcuallPos()) &&
								vehicle.getLastPos().equals(dbInputTraficJamTrafics.get(i+1).getLastPos())){
							/**
							 * check for the right span of time and change to the lower Time
							 */
							if(vehicle.getAcuallPos().getTime().isBefore(dbInputTraficJamTrafics.get(i+1).getAcuallPos().getTime())) {
							}else {
								vehicle.setAcuallPos(dbInputTraficJamTrafics.get(i+1).getAcuallPos());
							}
							/**
							 * check for the right span of time and change to the higher Time
							 */
							if(vehicle.getLastPos().getTime().isAfter(dbInputTraficJamTrafics.get(i+1).getLastPos().getTime())) {
								//spätere Zeit in der gegebenen
							}else {
								vehicle.setLastPos(dbInputTraficJamTrafics.get(i+1).getLastPos());
							}
							/**
							 * add that vehicle to the allTraficJamRouts
							 */
							if(allTraficJamRouts.isEmpty()) {
								allTraficJamRouts.add(vehicle);
							}else {
								/**
								 * check existing list to avoid duplicate values
								 */
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
				/**
				 * check all vehicle which have not been in a traffic jam for traffic offenders
				 */
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
	 * @param user actual User who would like to get his bill
	 * @param month month who are the user looking for
	 */
	
	public String createBillOfAMonth(User user, int month) {
		
		ArrayList<FinishedTransits> alltransit = new ArrayList<>();
		ArrayList<Vehicle> allvehicleFromAUser = new ArrayList<>();
		String allTransitsForBill = "All Transits of " + month + "/n";
		double fee = con.getFee();
		double tax = con.getTax();
		
		allvehicleFromAUser.addAll(con.getVehicle(user));
		
		for(Vehicle userVehicle : allvehicleFromAUser ) {
			alltransit.addAll(con.getAllTransitFromVehicle(userVehicle, month));
		}
		for(FinishedTransits finishedTransit : alltransit) {
			allTransitsForBill = allTransitsForBill + finishedTransit.toString(tax, fee) + " Price: " + "/n";
		}
		
		return allTransitsForBill;
		//TODO send via Mail to User
				
	}
	
	public ArrayList<StatisticView> getTotalNumberOfVehicleByOrigin(){
		ArrayList<StatisticView> totalNumberOfVehicleByOrigin = new ArrayList<>();
		Origin bsporigin = Origin.CH;
		ArrayList<Origin> allOrigins = bsporigin.getAllOrigins();
		
		CompletableFuture<ArrayList<StatisticView>> numberofVehicleByOriginFuture = CompletableFuture.supplyAsync(() -> 
		{
			ArrayList<StatisticView> acualList = new ArrayList<>();
			
			for(Origin origin : allOrigins) {
				long number = con.getTotalNumberOfVehicleByOrigin(origin);
				StatisticView sV = new StatisticView(origin, number);
				acualList.add(sV);
			}
			return acualList;
			
		});
				
		
		try {
			totalNumberOfVehicleByOrigin = numberofVehicleByOriginFuture.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return totalNumberOfVehicleByOrigin;
	}
	
	
	
}
