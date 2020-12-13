package prob04;

import java.util.ArrayList;

public class MyStack implements Stack{
	ArrayList<String> list = null;
	
	public MyStack(int size) {
		try {			
			list =  new ArrayList<String>(size);
		} catch(RuntimeException e) {
			System.out.println(e.toString()+"size가 1이상이어야 합니다.");
		}
	}

	@Override
	public void push(String str) {
		// TODO Auto-generated method stub
		list.add(str);
	}

	@Override
	public String pop() {
		// TODO Auto-generated method stub
		if(list.size() == 0)
			return null;
		return list.remove(list.size()-1);
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		if(list.size()==0)
			return true;
		return false;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return list.size();
	}

}
