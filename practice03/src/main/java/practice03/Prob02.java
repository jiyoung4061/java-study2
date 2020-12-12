package practice03;

import java.util.Scanner;

public class Prob02 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		System.out.print("금액: ");
		int num = scanner.nextInt();
		
		int[] units = {50000,10000,5000,1000,500,100,50,10,5,1};
		
		for(int i = 0 ; i <  units.length ; i++) {
			int count = 0;
			while(units[i] <= num) {
				count++;
				num -= units[i];
			}
			System.out.println(units[i]+"원:"+count);
		}
	}

}
