package fun_facts.exceptions;

// Step 1: Create a custom exception (Checked Exception)
class InvalidAgeException extends Exception {
    public InvalidAgeException(String message) {
        super(message);
    }
}

