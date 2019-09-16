package kz.edu.nu.cs.se;

public class EnteringCoinsState extends State{
	public EnteringCoinsState(VendingMachine vendingMachine) {
		this.vendingMachine = vendingMachine;
	}

    public void insertCoin(int coin) {
        if (coin != 50 && coin != 100) {
        	throw new IllegalArgumentException("Can only accept 50 or 100");
        }
        this.vendingMachine.balance += coin;
        if (this.vendingMachine.balance >= 200) {
        	this.vendingMachine.setCurrentState(this.vendingMachine.paid);
        }
    }

    public int refund() {
    	int refunded = this.vendingMachine.balance;
        this.vendingMachine.balance = 0;
        this.vendingMachine.setCurrentState(this.vendingMachine.idle);
        return refunded;        
    }

    public int vend() {
    	throw new IllegalStateException("At least 200 should be inserted");
    }
}
