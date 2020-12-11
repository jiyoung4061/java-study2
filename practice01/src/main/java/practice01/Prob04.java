package practice01;

import java.util.Random;
import java.util.Scanner;

public class Prob04 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		boolean tmp = false;
		
		do {
			Random r = new Random();
			int k = r.nextInt(100)+1; // 숨겨진 숫자카드
			
			int start = 1; // 범위(최솟값)
			int last = 100; // 범위(최댓값)
			int turn = 1; // 도전 횟수
			boolean temp = true; // while문
			
			System.out.println("수를 결정하였습니다. 맞추어보세요");
			
			while(temp) {
				System.out.println(start + "-" + last);
				System.out.print(turn+">>");
				int guess = scanner.nextInt();
				if(guess > k) {
					System.out.println("더낮게");
					last = guess;
				} else if(guess < k) {
					System.out.println("더높게");
					start = guess;
				} else { // 정답이 맞을 경우
					temp = false;
				}
			}
			System.out.println("맞았습니다");
			System.out.print("다시하시겠습니까?");
			String answer = scanner.next();
			if( answer.equals("y") ){
				tmp = true;
			} else {
				tmp = false;
			}
		} while(tmp);
		
		scanner.close();
	}

}
