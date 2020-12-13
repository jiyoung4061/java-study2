package prob02;

public class Money {
	int amount; // immutable(불변)

	public Money(int amount) {
		this.amount = amount;
	}

	public Money add(Money money) {
		int res = amount + money.amount;
		Money resultOfMoney = new Money(res);
		return resultOfMoney;
	}

	public Money minus(Money money) {
		int res = amount - money.amount;
		Money resultOfMoney = new Money(res);
		return resultOfMoney;
	}

	public Money multiply(Money money) {
		int res = amount * money.amount;
		Money resultOfMoney = new Money(res);
		return resultOfMoney;
	}

	public Money devide(Money money) {
		int res = amount / money.amount;
		Money resultOfMoney = new Money(res);
		return resultOfMoney;
	}

	public boolean equals(Object object) {
		if (object instanceof Money && ((Money) object).amount == amount) {
			return true;
		}
		return false;
	}
}
