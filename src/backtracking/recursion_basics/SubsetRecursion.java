package backtracking.recursion_basics;

public class SubsetRecursion {
    public static void main(String[] args) {
        String input = "abcd";
        generateSubsets(input, 0, "");
    }

    static void generateSubsets(String input, int index, String current) {
        System.out.println("Call -> index: " + index + ", current: " + current);

        if (index == input.length()) {
            System.out.println("Subset: " + current);
            return;
        }

        // Include current character
        generateSubsets(input, index + 1, current + input.charAt(index));

        // Exclude current character
        generateSubsets(input, index + 1, current);
    }
}
