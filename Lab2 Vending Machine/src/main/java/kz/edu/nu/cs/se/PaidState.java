package kz.edu.nu.cs.se;

public class PaidState extends State{
	public PaidState(VendingMachine vendingMachine) {
		this.vendingMachine = vendingMachine;
	}

    public void insertCoin(int coin) {
        if (coin != 50 && coin != 100) {
        	throw new IllegalArgumentException("Can only accept 50 or 100");
        }
        this.vendingMachine.balance += coin;
    }

    public int refund() {
    	int refunded = this.vendingMachine.balance;
        this.vendingMachine.balance = 0;
        this.vendingMachine.setCurrentState(this.vendingMachine.idle);
        return refunded;        
    }

    public int vend() {
    	int refunded = this.vendingMachine.balance - 200;
        this.vendingMachine.balance = 0;
        this.vendingMachine.setCurrentState(this.vendingMachine.idle);
        return refunded;
    }
}
