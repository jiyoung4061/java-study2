package prob01;

import java.util.Scanner;

public class Prob01 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner( System.in );
		System.out.print( "문자열을 입력하세요:" );
		
		String str = scanner.next();
		
		/*  이곳에 프로그램을 완성하십시오. */
		for(int i = 1 ; i <= str.length(); i++) {
			for(int j = 0 ; j < i ; j++) {
				System.out.print(str.charAt(j));
			}
			System.out.println();
		}
	}

}
