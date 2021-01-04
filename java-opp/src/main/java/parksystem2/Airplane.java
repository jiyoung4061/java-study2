package parksystem2;

import parksystem3.IParkable;

public class Airplane implements IParkable {

	@Override
	public void park() {
		System.out.println("비행기를 주차하였습니다.");
	}
	
}
