package greedy;

/*Given an infinite supply of each denomination of Indian currency
 { 1, 2, 5, 10, 20, 50, 100, 200, 500, 2000 } and a target value N.
Find the minimum number of coins and/or notes needed to make the change for Rs N.
You must return the list containing the value of coins required.


Example 1:

Input: N = 43
Output: 20 20 2 1
Explaination:
Minimum number of coins and notes needed
to make 43.

Example 2:

Input: N = 1000
Output: 500 500
Explaination: minimum possible notes
is 2 notes of 500.

Your Task:
You do not need to read input or print anything. Your task is to complete the function minPartition() which takes the value N as input parameter and returns a list of integers in decreasing order.


Expected Time Complexity: O(N)
Expected Auxiliary Space: O(N)

*/

/*To solve this problem, we need to determine the minimum number of coins and/or notes required to form a given amount
𝑁
N using the denominations available in Indian currency. We can approach this problem using a greedy algorithm, where we always pick the largest denomination that is less than or equal to the remaining amount and subtract it from the target.

Steps:
Start with the largest denomination and see how many times it fits into
𝑁
N.
Subtract the value of those coins/notes from
𝑁
N and repeat the process with the remaining amount.
Continue this process until
𝑁
N becomes 0.
The result should be the denominations in decreasing order.
Approach:
Sort the denominations in decreasing order so that we always pick the largest denomination first.
Iterate through the denominations and at each step, determine how many coins/notes of that denomination we can use. Subtract the total value of those coins/notes from
𝑁
N.
Keep track of the coins/notes used, and once
𝑁
N becomes 0, return the list of coins/notes used.*/

import java.util.ArrayList;
import java.util.List;

public class MinPartition {
    public List<Integer> minPartition(int N) {
        // List of available denominations in descending order
        int[] denominations = {2000, 500, 200, 100, 50, 20, 10, 5, 2, 1};

        List<Integer> result = new ArrayList<>();

        // Iterate over each denomination
        for (int denom : denominations) {
            // While N is greater than or equal to the current denomination
            while (N >= denom) {
                result.add(denom); // Add the denomination to result
                N -= denom; // Subtract the denomination value from N
            }
        }

        return result;
    }

    public static void main(String[] args) {
        MinPartition minPartition = new MinPartition();
        List<Integer> result = minPartition.minPartition(2123);
        System.out.println(result);

    }
}
