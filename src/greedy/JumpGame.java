package greedy;

/*Given an positive integer N and a list of N integers A[]. Each element in the array denotes the maximum length of jump you can cover. Find out if you can make it to the last index if you start at the first index of the list.


Example 1:

Input:
N = 6
A[] = {1, 2, 0, 3, 0, 0}
Output:
1
Explanation:
Jump 1 step from first index to
second index. Then jump 2 steps to reach
4th index, and now jump 2 steps to reach
the end.
Example 2:

Input:
N = 3
A[] =  {1, 0, 2}
Output:
0
Explanation:
You can't reach the end of the array.

Your Task:
You don't need to read input or print anything. Your task is to complete the function canReach() which takes a Integer N and a list A of size N as input and returns 1 if the end of the array is reachable, else return 0.


Expected Time Complexity: O(N)
Expected Auxiliary Space: O(1)

Approach:
Start at the first index (index 0).
Traverse through the array:
If at any point maxReach becomes greater than or equal to the last index, we can reach the end, so return 1.
Update maxReach as the maximum of its current value and the sum of the current index and the value at that index (i + A[i]), which represents how far we can jump from the current index.
If we reach an index where we cannot move forward (i.e., maxReach is the same as the current index and we haven't reached the last index), return 0.
Code Im*/
public class JumpGame {
    public static int canReach(int N, int[] A) {
        int maxReach = 0; // Tracks the farthest index we can reach

        for (int i = 0; i < N; i++) {
            // If the current index is beyond the farthest reachable index, return 0
            if (i > maxReach) {
                return 0;
            }
            // Update the farthest index we can reach
            maxReach = Math.max(maxReach, i + A[i]);
            // If we can reach the last index, return 1
            if (maxReach >= N - 1) {
                return 1;
            }
        }

        // In case the loop ends and we didn't reach the last index
        return 0;
    }

    public static void main(String[] args) {
        // Test cases
        int[] A1 = {1, 2, 0, 3, 0, 0};
        System.out.println(canReach(6, A1)); // Output: 1

        int[] A2 = {1, 0, 2};
        System.out.println(canReach(3, A2)); // Output: 0
    }
}
