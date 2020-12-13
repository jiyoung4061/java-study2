package practice04;

public class Rectangle extends Shape implements Resizable {
	double width;
	double height;
	
	public Rectangle(double width, double height) {
		this.width = width;
		this.height = height;
	}
	
	public double getArea() {
		return width * height;
	}
	
	public double getPerimeter() {
		return (width+height)*2;
	}
	
	public void resize(double s) {
		width *= s;
		height *= s;
	}

}
