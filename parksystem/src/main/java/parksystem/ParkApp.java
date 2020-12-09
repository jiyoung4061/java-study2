package parksystem;

import parksystem3.IParkable;

public class ParkApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Bus c1 = new Bus();
		SportsCar c2 = new SportsCar();
		
		ParkSystem.park(c1);
		ParkSystem.park(c2);
		
	}

}
