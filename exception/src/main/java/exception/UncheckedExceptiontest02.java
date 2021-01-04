package exception;

public class UncheckedExceptiontest02 {

	// uncheckException : try-catch를 안해도 프로그래밍 실행O
	// <-> checkException : try-catch를 안하면 안되는 프로그램!
	// exception이 발생할거라는 예상을 안하고 프로그래밍을 함! -> 이때 발생한 exception은 단순한 개발자의 실수(Error)
	public static void main(String[] args) {
//		try {
			int[] a = { 0, 1, 2, 3, 4 };

			for (int i = 0; i <= a.length; i++) {
				System.out.println(a[i]);
			}
//		} catch (ArrayIndexOutOfBoundsException ex) {
//			System.out.println(ex);
//		}
	}
}
