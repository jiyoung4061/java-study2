package practice02;

public class CurrencyConverter {
	private static double rate;
	
	public static void setRate(double r) {
		rate = r; //1달러가 r원
	}
	
	public static double toDollar(double won) {
		System.out.println(won+"원은 "+ won/rate +"달러 입니다.");
		return won / rate;
	}
	
	public static double toKWR(double dollar) {
		System.out.println(dollar+"달러는 "+ dollar * rate + "원 입니다.");
		return dollar * rate;
	}
	
}
