package others;

import java.util.concurrent.atomic.AtomicInteger;



/*   Comapare and Swap method to avoid locking mechanisnm while using multithreading    */
public class Account {
    private AtomicInteger balance;

    public Account(int initialBalance) {
        balance = new AtomicInteger(initialBalance);
    }

    public int getBalance() {
        return balance.get();
    }

    public void deposit(int amount) {
        balance.getAndAdd(amount);  // Atomic addition
    }

    public void withdraw(int amount) {
        balance.getAndAdd(-amount); // Atomic subtraction
    }


    public boolean transfer(Account toAccount, int amount) {
        int currentBalance = balance.get();

        if (currentBalance < amount) {
            return false;  // Insufficient funds
        }

        // Attempt to withdraw using CAS
        boolean successfulWithdrawal = balance.compareAndSet(currentBalance, currentBalance - amount);

        if (successfulWithdrawal) {
            toAccount.deposit(amount);
        }

        return successfulWithdrawal;
    }


}