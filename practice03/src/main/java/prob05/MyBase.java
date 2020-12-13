package prob05;

public class MyBase extends Base{
	public void service(String state){
		if(state.equals("낮"))
			day();
		else if(state.equals("밤"))
			super.night();
		else
			noon();
			
	}
	
	public void day(){
		System.out.println("낮에는 열심히 일하자!");
	}
	public void noon() {
		System.out.println("오후도 낮과 마찬가지로 일해야합니다.");
	}
	
	
}
