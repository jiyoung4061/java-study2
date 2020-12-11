package object_ex01;

public class Ex01 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s1 = "Hello";
		String s2 = new String("Hello");
		
		System.out.println(s1 == s2); // 객체 동일성
		System.out.println(s1.equals(s2)); // 객체의 동질성(내용비교)
		
		System.out.println("====================================");
		
		String s3 = "Hello";
		String s4 = "Hello";
		System.out.println(s3 == s4); // 객체 동일성
		System.out.println(s3.equals(s4));
		
		System.out.println("====================================");
		
		String s = "Hello"+ " " + "World";
	}

}
