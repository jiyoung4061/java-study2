package practice01;

public class Prob03 {
	public static void main(String[] args) {
		int index;
		for(int i = 1 ; i< 100 ; i++) {
			String s = String.valueOf(i);
			if(i < 10) {
				index = 0;
			} else {
				index = 1;
			}
			if(s.charAt(index)%3 == 0 && s.charAt(index)!= '0') {
				System.out.println(s+"짝");
			}
		}
	} 
}
