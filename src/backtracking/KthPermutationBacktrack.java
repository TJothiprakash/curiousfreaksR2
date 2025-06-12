package backtracking;
/*Given two integers N (1<=N<=9) and K. Find the kth permutation sequence of first N natural numbers. Return the answer in string format.

Example 1:

Input: N = 4, K = 3
Output: 1324
Explanation:
Permutations of first 4 natural numbers:
1234,1243,1324,1342,1423,1432.....
So the 3rd permutation is 1324.
Example 2:

Input: N = 3, K = 5
Output: 312
Explanation:
Permutations of first 3 natural numbers:
123,132,213,231,312,321.
So the 5th permutation is 312.
Your Task:
You don't need to read input or print anything. Your task is to complete the function kthPermutation() which takes two integers N and K as input parameters and returns a string denoting the kth permutation.

Expected Time Complexity: O(N2)
Expected Auxiliary Space: O(N)

Constraints:
1 <= N <= 9
1 <= K <= N!

Topic Tags
Related Articles
*/
public class KthPermutationBacktrack {
    static int count = 0;
    static String result = "";

    public static String kthPermutation(int N, int K) {
        count = 0;
        result = "";
        boolean[] used = new boolean[N + 1];
        backtrack(new StringBuilder(), N, K, used);
        return result;
    }

    private static void backtrack(StringBuilder current, int N, int K, boolean[] used) {
        if (current.length() == N) {
            count++;
            if (count == K) {
                result = current.toString();
            }
            return;
        }

        for (int i = 1; i <= N; i++) {
            if (!used[i]) {
                used[i] = true;
                current.append(i);
                backtrack(current, N, K, used);
                current.deleteCharAt(current.length() - 1);
                used[i] = false;

                if (!result.isEmpty()) return;  // Early exit if result found
            }
        }
    }

    // Test
    public static void main(String[] args) {
        System.out.println(kthPermutation(4, 9)); // Output: 1324
        System.out.println(kthPermutation(3, 2)); // Output: 312
    }
}
