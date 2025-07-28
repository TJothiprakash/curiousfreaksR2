package others.exceptions;

// Step 2: Throw it inside a method
public class VotingSystem {
    public void checkEligibility(int age) throws InvalidAgeException {
        if (age < 18) {
            throw new InvalidAgeException("Age must be 18 or above to vote.");
        }
        System.out.println("You are eligible to vote!");
    }
}
