package fun_facts.exceptions;
// Custom Unchecked Exception
public class InsufficientBalanceException extends RuntimeException {
  public InsufficientBalanceException(String message) {
    super(message);
  }
}

