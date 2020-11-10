package Backend;

public class DBInputVehicleAndPoint {

	Vehicle vehicle;
	Position position;
	
	public void DBInputVehicleAndPoint(Vehicle vehicle, Position position) {
		
		this.position = position;
		this.vehicle = vehicle;
		
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public Position getPosition() {
		return position;
	}
	
	
	
}
