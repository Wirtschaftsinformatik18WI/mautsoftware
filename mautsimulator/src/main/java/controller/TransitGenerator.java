package controller;

import controller.Generator;
import model.Vehicle;

public class TransitGenerator {

	public void generateTransit( ) {
		Generator gen = new Generator();
		for (Vehicle v : gen.generateVehicle()) {
			System.out.println(v.getRegistrationNumber());
		}
		
	}
}
