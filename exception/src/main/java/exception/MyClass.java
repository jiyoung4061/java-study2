package exception;

import java.io.IOException;

public class MyClass {

	public void danger() throws IOException, MyException{ // 이 함수는 IOException 예외를 던질 가능성이 있으므로 
											// 해당 함수를 사용하는 곳에서는 try-catch를 사용해 예외를 처리해라!
		System.out.println("some code1...");
		System.out.println("some code2...");
		
		if(10-5==5) {
			throw new MyException();
		}
		if(5-5==0) {
			throw new IOException();
		}
		
		
		System.out.println("some code3...");
		System.out.println("some code4...");
	}

}
