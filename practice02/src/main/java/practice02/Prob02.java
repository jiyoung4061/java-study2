package practice02;

import java.util.Scanner;

public class Prob02 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		final int GOODS_COUNT = 1;
		
		Goods[] goods = new Goods[GOODS_COUNT];
		
		Scanner scanner = new Scanner(System.in);
		
		for(int i = 0 ; i < GOODS_COUNT ; i++) {
			String info = scanner.nextLine();
			
			String[] infos = info.split(" ");
			
			String name = infos[0];
			int price = Integer.parseInt(infos[1]);
			int stockCount = Integer.parseInt(infos[2]);
			
			
		}
		for(int i = 0 ; i < GOODS_COUNT ; i++) {
			
		}
		
		scanner.close();
	}

}
