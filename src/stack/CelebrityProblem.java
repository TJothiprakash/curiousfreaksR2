package stack;

public class CelebrityProblem {
    // Problem:
    // Given a matrix 'mat' where mat[i][j] = 1 means person i knows person j,
    // find if there exists a celebrity who is known by everyone but knows no one.
    // Return the celebrity index or -1 if none exists.

    // Intuition:
    // Use two-step elimination:
    // Step 1: Find a candidate who might be a celebrity.
    // Step 2: Verify if the candidate satisfies celebrity conditions.

    // Dry Run:
    // For example:
    // mat = [
    //   [0, 1, 0],
    //   [0, 0, 0],
    //   [0, 1, 0]
    // ]
    //
    // Candidate initially 0
    // Check if candidate knows i:
    // candidate=0 knows 1 (mat[0][1]==1), so candidate=1
    // candidate=1 knows 2? No
    //
    // Verify candidate=1:
    // Check all others:
    // mat[1][0]==0 and mat[0][1]==1 ✔
    // mat[1][2]==0 and mat[2][1]==1 ✔
    // So 1 is celebrity.

    // Time Complexity: O(n)
    // We iterate the matrix twice: once to find candidate, once to verify.

    // Space Complexity: O(1)
    // Only variables for indices and counters are used.

    // O(n) time, O(1) space
    public static int findCelebrity(int[][] mat) {
        int n = mat.length;

        // Step 1: Identify potential celebrity
        int candidate = 0;
        for (int i = 1; i < n; i++) {
            if (mat[candidate][i] == 1) {
                candidate = i; // candidate knows i, so candidate can't be celebrity
            }
        }

        // Step 2: Verify candidate
        for (int i = 0; i < n; i++) {
            if (i != candidate) {
                // candidate should not know i, and i should know candidate
                if (mat[candidate][i] == 1 || mat[i][candidate] == 0) {
                    return -1; // candidate fails the test
                }
            }
        }

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
