package fun_facts.exceptions;
// Step 3: Handle it in main()
class Test {
    public static void main(String[] args) {
        VotingSystem votingSystem = new VotingSystem();
        try {
            votingSystem.checkEligibility(19);  // ❌ Throws InvalidAgeException
        } catch (InvalidAgeException e) {
            System.out.println("Caught Exception: " + e.getMessage());
        }
    }
}
