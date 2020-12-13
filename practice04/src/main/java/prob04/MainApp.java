package prob04;

public class MainApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			Stack stack = new MyStack(-1);
			
			System.out.println("==================================");
			
			stack = new MyStack(3);
			stack.push("Hello");
			stack.push("World");
			stack.push("!!!");
			stack.push("java");
			stack.push(".");
			
			while(!stack.isEmpty()) {
				System.out.println(stack.pop());
			}
			
			System.out.println("======================================");
			
			stack = new MyStack(3);
			stack.push("Hello");
			
			System.out.println(stack.pop());
			System.out.println(stack.pop());
			
		} catch(Exception ex) {
			System.out.println(ex.toString());
		}
	}

}
