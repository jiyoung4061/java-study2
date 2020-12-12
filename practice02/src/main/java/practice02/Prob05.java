package practice02;

import java.util.Scanner;

public class Prob05 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("두 정수와 연산자를 입력하시오 >>");
		String line = scanner.nextLine();
		String[] tokens = line.split(" ");
		
		int lValue = Integer.parseInt(tokens[0]);
		int rValue = Integer.parseInt(tokens[2]);
		String operator = tokens[1];
		int result=0;
		
		switch(operator) {
			case "+": {
				Add add = new Add();
				add.setValue(lValue, rValue);
				result = add.calculate();
				break;
			}
			case "-": {
				Sub sub = new Sub();
				sub.setValue(lValue, rValue);
				result = sub.calculate();
				break;
			}
			case "*": {
				Mul mul = new Mul();
				mul.setValue(lValue, rValue);
				result = mul.calculate();
				break;
			}
			case "/": {
				Div div = new Div();
				div.setValue(lValue, rValue);
				result = div.calculate();
				break;
			}
			default : {
				System.out.println("지원하지 않는 계산입니다.");
			}
		}
		System.out.println(">> "+result);
		System.out.println(">> quit");
		scanner.close();
	}

}
