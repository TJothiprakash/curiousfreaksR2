package fun_facts.exceptions;

// Bank Account Simulation
public class BankAccount {
  private double balance = 5000;

  public void withdraw(double amount) {
    if (amount > balance) {
      throw new InsufficientBalanceException("Insufficient funds. Available balance: " + balance);
    }
    balance -= amount;
    System.out.println("Withdrawal successful! Remaining balance: " + balance);
  }
}
