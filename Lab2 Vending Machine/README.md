# Vending-Machine-SE-lab2
Software Engineering Fall2019 Lab2

VendingMachine is the main context class.
There are 3 states: idle, entering coins, and paid.
There are 3 actions: insert coin, refund, vend.
The vending machine should always begin in the idle state
with a balance of 0.
The vending machine should only accept coins with value 50
or 100. Any other amount should result raise an
IllegalArgumentException.
insertCoin() causes the machine to enter the entering coins
state.
The vending machine should enter the paid state when a
balance of 200 or greater accrues (the vended item costs 200).
To vend an item call the vend() method.
The value returned by vend() is equal to the surplus balance.
Both vend() and refund() should return the machine to the
idle state with a balance of 0.
The balance should accumulate until either vend() or
refund() is called.
The complete expected behavior is documented in the JUnit
tests.
The classes also contain information in javadoc comments.
