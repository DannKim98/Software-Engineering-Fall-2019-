package kz.edu.nu.cs.se;

public class IdleState extends State{
	
	public IdleState(VendingMachine vendingMachine) {
		this.vendingMachine = vendingMachine;
	}

    public void insertCoin(int coin) {
        if (coin != 50 && coin != 100) {
        	throw new IllegalArgumentException("Can only accept 50 or 100");
        }
        this.vendingMachine.balance += coin;
        this.vendingMachine.setCurrentState(this.vendingMachine.enteringCoins);
    }

    public int refund() {
    	int refunded = this.vendingMachine.balance;
        this.vendingMachine.balance = 0;
        return refunded;        
    }

    public int vend() {
    	throw new IllegalStateException("At least 200 should be inserted");
    }
}
