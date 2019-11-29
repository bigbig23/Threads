package demo1;

public class Account {
	
	private int balance =10000;
	
	public void diposite(int amount) {
		balance+=amount;
	}
	
	public void withdraw(int amount) {
		balance-=amount;
	}
	
	public static void transfer(Account account1, Account account2, int amount) {
		account1.withdraw(amount);
		account2.diposite(amount);
	}
	
	public int getBalance() {
		return balance;
	}

}
