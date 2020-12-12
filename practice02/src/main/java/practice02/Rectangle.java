package practice02;

public class Rectangle {
	int x1, y1, x2, y2;
	public Rectangle() {
		
	}
	public Rectangle(int x1, int y1, int x2, int y2){
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
	}
	
	public void set(int x1, int y1, int x2, int y2) {
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;		
	}
	
	public int square() {
		int size = 0;
		
		int width = Math.abs(x1-x2);
		int height = Math.abs(y1-y2);
		size = width * height;
		
		return size;
	}
}
