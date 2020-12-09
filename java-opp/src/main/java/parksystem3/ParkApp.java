package parksystem3;

import parksystem3.IParkable;

public class ParkApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		IParkable parkable= new Bus();
		ParkSystem3.park(parkable);
		
		parkable = new SportsCar();
		ParkSystem3.park(parkable);
		
		parkable = new Taxi();
		ParkSystem3.park(parkable);

//		c = new Airplane(c);
		ParkSystem3.park(parkable);
		
	}

}
