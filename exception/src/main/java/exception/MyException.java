package exception;

public class MyException extends Exception {
	private static final long serialVersionUID = 1L;

	public MyException() {
		super("MyException Occurs");
	}
	
	public MyException(String message) { // 사용자가 오류 메세지를 직접 줄 경우
		super(message);
	}
}
