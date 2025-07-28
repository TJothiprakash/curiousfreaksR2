package others.exceptions;
// Custom Unchecked Exception
public class InsufficientBalanceException extends RuntimeException {
  public InsufficientBalanceException(String message) {
    super(message);
  }
}

