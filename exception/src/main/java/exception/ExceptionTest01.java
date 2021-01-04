package exception;

public class ExceptionTest01 {

	public static void main(String[] args) {
		int i = 10;
		
		try {
			System.out.println("come codes...");
			int k = 9999 /(i - 10);
			System.out.println("come codes2...");
		} catch(ArithmeticException ex) {
			// catch문 작성법
			// 1. 사과
			System.out.println("미안합니다. 프로그래밍이 예기치 않은 ....");
			// 2. 로그 남기기
			System.out.println(ex);
			// 3. 정상종료
			return;
		} finally {
			System.out.println("some codes3....");
		}
		System.out.println("some codes4....");
	}

}
