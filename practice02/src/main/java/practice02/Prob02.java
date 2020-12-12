package practice02;

import java.util.Scanner;

public class Prob02 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		final int GOODS_COUNT = 3;		
		Goods[] arrayOfGoods = new Goods[GOODS_COUNT];
		
		for(int i = 0 ; i < GOODS_COUNT ; i++) {
			String info = scanner.nextLine();
			
			String[] infos = info.split(" ");
			
			String name = infos[0];
			int price = Integer.parseInt(infos[1]);
			int stockCount = Integer.parseInt(infos[2]);
			
			Goods goods = new Goods(name, price, stockCount);
			arrayOfGoods[i] = goods;
		}
		for(int i = 0 ; i < GOODS_COUNT ; i++) {
			System.out.println(arrayOfGoods[i].name + "(가격:"+ arrayOfGoods[i].price+"원)이 "+arrayOfGoods[i].stockCount+"개 입고 되었습니다.");
		}
		
		scanner.close();
	}

}
