package parksystem2;

import parksystem3.IParkable;

public class ParkApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Car c= new Bus();
		ParkSystem2.park(c);	

		c = new SportsCar();
		ParkSystem2.park(c);	

		c = new Taxi();
		ParkSystem2.park(c);
		
		ParkSystem2.park(c);	
		
	}

}
