package parksystem2;

import parksystem3.IParkable;

public abstract class Car implements IParkable{
	private int accelate = 0;
	public void brake() {
		System.out.println("브레이크 밟았습니다");
	}
	public abstract void park();
}
