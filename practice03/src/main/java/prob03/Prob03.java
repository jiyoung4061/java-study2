package prob03;

public class Prob03 {


	public static void main(String args[]) {
		
		char[] array1 = reverse("Hello World");
		printCharArray(array1);
		
		char[] array2 = reverse("Java Programming!");
		printCharArray(array2);
		
	}
	
	public static char[] reverse(String str){
		char[] result = new char[str.length()];
		for(int i = 0, j = str.length()-1 ; i < result.length ; i++, j--) {
			result[i] = str.charAt(j);
		}
		return result;
	}
	
	public static void printCharArray(char[] array){
		for(int i = 0; i < array.length ; i++) {
			System.out.print(array[i]);
		}
		System.out.println();
	}
}
