package practice05;

public class Account {
	String accountNo;
	int balance;
	
	public Account(String accountNo) {
		this.accountNo = accountNo;
		balance = 0;
		System.out.println(this.accountNo+" 계좌가 신설되었습니다.");
	}

	public String getAccNo() {
		// TODO Auto-generated method stub
		return accountNo;
	}

	public String getBalance() {
		// TODO Auto-generated method stub
		return String.valueOf(balance);
	}

	public void withdraw(int withdraw) { //출금
		// TODO Auto-generated method stub
		System.out.println(accountNo+"계좌에 "+withdraw+"만원이 출금되었습니다.");
		balance -= withdraw;
	}
	
	public void deposit(int deposit) { //입금
		// TODO Auto-generated method stub
		System.out.println(accountNo+"계좌에 "+deposit+"만원이 입금되었습니다.");
		balance += deposit;
	}
}
