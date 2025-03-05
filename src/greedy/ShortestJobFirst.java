package greedy;
/*Geek is a software engineer. He is assigned with the task of calculating average waiting time of all the processes by following shortest job first policy.

The shortest job first (SJF) or shortest job next, is a scheduling policy that selects the waiting process with the smallest execution time to execute next.

Given an array of integers bt of size n. Array bt denotes the burst time of each process. Calculate the average waiting time of all the processes and return the nearest integer which is smaller or equal to the output.

Note: Consider all process are available at time 0.

Example 1:

Input:
n = 5
bt = [4,3,7,1,2]
Output: 4
Explanation: After sorting burst times by shortest job policy, calculated average waiting time is 4.
Example 2:

Input:
n = 4
arr = [1,2,3,4]
Output: 2
Explanation: After sorting burst times by shortest job policy, calculated average waiting time is 2.
Your Task:
You don't need to read input or print anything. Your task is to complete the function solve() which takes bt[] as input parameter and returns the average waiting time of all the processes.

Expected Time Complexity: O(nlog(n))
Expected Auxiliary Space: O(1)

Constraints:
1 <= n <= 105
1 <= arr[i] <= 105
Sorting: We first sort the burst times bt[] in ascending order to follow the SJF policy.
Waiting Time Calculation: We calculate the waiting time of each process by summing up the burst times of all the processes that were executed before it.
Average Waiting Time: After calculating the total waiting time for all processes, we calculate the average by dividing the total waiting time by the number of processes. We use integer division (/) to get the largest integer smaller than or equal to the result.
Example Walkthrough:
Example 1:
For the input [4, 3, 7, 1, 2]:

Sort the burst times: [1, 2, 3, 4, 7]
Calculate waiting times: 0, 1, 3, 6, 10
Total waiting time: 0 + 1 + 3 + 6 + 10 = 20
Average waiting time: 20 / 5 = 4
Example 2:
For the input [1, 2, 3, 4]:

Sort the burst times: [1, 2, 3, 4]
Calculate waiting times: 0, 1, 3, 6
Total waiting time: 0 + 1 + 3 + 6 = 10
Average waiting time: 10 / 4 = 2
Time Complexity:
O(n log n) due to the sorting of the burst times.
O(n) for calculating the waiting times.
Space Complexity:
O(1), as we are using a constant amount of extra space.*/

import java.util.Arrays;

public class ShortestJobFirst {

    public static int solve(int[] bt) {
        int n = bt.length;

        // Step 1: Sort the burst times to follow Shortest Job First policy
        Arrays.sort(bt);

        // Step 2: Calculate the waiting times
        int totalWaitingTime = 0;
        int currentWaitingTime = 0;

        for (int i = 1; i < n; i++) {
            currentWaitingTime += bt[i - 1];  // Add the burst time of the previous process
            totalWaitingTime += currentWaitingTime;  // Add to total waiting time
        }

        // Step 3: Calculate the average waiting time
        // Use integer division to get the floor

        return totalWaitingTime / n;
    }

    public static void main(String[] args) {
        // Test cases
        System.out.println(solve(new int[]{4, 3, 7, 1, 2}));  // Output: 4
        System.out.println(solve(new int[]{1, 2, 3, 4}));  // Output: 2
    }
}
