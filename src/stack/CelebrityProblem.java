package stack;

public class CelebrityProblem {

    public static int findCelebrity(int[][] mat) {
        int n = mat.length;

        // Step 1: Identify the potential celebrity candidate
        int candidate = 0;
        for (int i = 1; i < n; i++) {
            if (mat[candidate][i] == 1) {
                candidate = i; // candidate can't be `candidate` if they know `i`
            }
        }

        // Step 2: Verify the candidate
        for (int i = 0; i < n; i++) {
            // Candidate should not know anyone, and everyone should know the candidate
            if (i != candidate) {
                if (mat[candidate][i] == 1 || mat[i][candidate] == 0) {
                    return -1; // Candidate fails the conditions, no celebrity
                }
            }
        }

        // If candidate passes all checks, return the candidate's index
        return candidate;
    }

    public static void main(String[] args) {
        int[][] mat1 = {
                {0, 1, 0},
                {0, 0, 0},
                {0, 1, 0}
        };
        System.out.println(findCelebrity(mat1)); // Output: 1

        int[][] mat2 = {
                {0, 1},
                {1, 0}
        };
        System.out.println(findCelebrity(mat2)); // Output: -1

        int[][] mat3 = {
                {0}
        };
        System.out.println(findCelebrity(mat3)); // Output: 0
    }
}
