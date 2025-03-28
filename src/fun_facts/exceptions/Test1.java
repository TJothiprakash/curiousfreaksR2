package fun_facts.exceptions;

// Test the exception
public class Test1 {
  public static void main(String[] args) {
    BankAccount account = new BankAccount();
    account.withdraw(6000);  // ❌ Throws InsufficientBalanceException
  }
}
