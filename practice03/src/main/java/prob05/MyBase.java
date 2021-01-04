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
	private void noon() {
		
	}	
}
