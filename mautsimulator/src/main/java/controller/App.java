package controller;

import controller.UserGenerator;
import controller.VehicleGenerator;

public class App {

	public static void main(String[] args) {
		
		UserGenerator user = new UserGenerator();
		
		System.out.println(user.randomUser());
	}

}
