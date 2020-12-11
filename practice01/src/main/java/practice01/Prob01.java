package practice01;

import java.util.Scanner;

public class Prob01 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in); // system.in : 키보드와 연결된 IO
		System.out.print("수를 입력하세요:");
		int number = scanner.nextInt();
		System.out.println(number);
		scanner.close();
	}

}
