package heaps;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

/*There are n workers. You are given two integer arrays quality and wage where quality[i] is the quality of the ith worker and wage[i] is the minimum wage expectation for the ith worker.

We want to hire exactly k workers to form a paid group. To hire a group of k workers, we must pay them according to the following rules:

Every worker in the paid group must be paid at least their minimum wage expectation.
In the group, each worker's pay must be directly proportional to their quality. This means if a worker’s quality is double that of another worker in the group, then they must be paid twice as much as the other worker.
Given the integer k, return the least amount of money needed to form a paid group satisfying the above conditions. Answers within 10-5 of the actual answer will be accepted.



Example 1:

Input: quality = [10,20,5], wage = [70,50,30], k = 2
Output: 105.00000
Explanation: We pay 70 to 0th worker and 35 to 2nd worker.
Example 2:

Input: quality = [3,1,10,10,1], wage = [4,8,2,2,7], k = 3
Output: 30.66667
Explanation: We pay 4 to 0th worker, 13.33333 to 2nd and 3rd workers separately.


Constraints:

n == quality.length == wage.length
1 <= k <= n <= 104
1 <= quality[i], wage[i] <= 104
Seen this question in a real interview before?
1/5
Yes
No
Accepted
145.3K


Problem Breakdown:
Worker's Wage Calculation:
If a worker’s quality is double that of another worker, then their pay should be double as well, as stated in the problem.
The key observation here is that the pay for each worker can be proportional to their quality, and it must satisfy the condition that every worker is paid at least their expected wage.
Proportional Pay:
We can express the wage of a worker as wage[i] = proportion * quality[i], where proportion is a factor applied uniformly to all workers in the selected group. For each group of k workers, we need to find the minimum possible proportion that satisfies their wage expectations.
Approach:
Sort by Wage-to-Quality Ratio:

The critical observation is that the wage-to-quality ratio is the same for all workers in a valid group. So, we will calculate the ratio for each worker, which is wage[i] / quality[i].
We will sort the workers based on this ratio. This ensures that for any subset of workers chosen, they all have a similar ratio, which guarantees that we can scale their wages according to their quality.
Minimizing Total Cost:

Once the workers are sorted by their wage-to-quality ratio, we need to pick the best k workers.
The cost to hire k workers can be calculated as:
total_cost = proportion * sum_of_quality_of_k_workers,
where proportion is determined by the smallest ratio in the group.
Sliding Window Approach:

After sorting the workers by their wage-to-quality ratio, use a sliding window of size k to compute the total cost of hiring any k workers. The optimal solution will be the one with the least cost.
Steps:
Calculate the ratio wage[i] / quality[i] for each worker.
Sort the workers based on this ratio.
For each group of k consecutive workers (as they share the same ratio), calculate the total cost by multiplying the sum of the k smallest qualities by the ratio of the current group.
Return the minimum cost.
Java Code I*/
public class MinimumCostHiringKWorkers {

    public static void main(String[] args) {
        MinimumCostHiringKWorkers solution = new MinimumCostHiringKWorkers();

        int[] quality1 = {10, 20, 5};
        int[] wage1 = {70, 50, 30};
        int k1 = 2;
        System.out.println(solution.mincostToHireWorkers(quality1, wage1, k1));  // Output: 105.0

        int[] quality2 = {3, 1, 10, 10, 1};
        int[] wage2 = {4, 8, 2, 2, 7};
        int k2 = 3;
        System.out.println(solution.mincostToHireWorkers(quality2, wage2, k2));  // Output: 30.66667
    }

    public double mincostToHireWorkers(int[] quality, int[] wage, int k) {
        int n = quality.length;

        // Create a list of workers with their (wage-to-quality ratio, quality)
        List<int[]> workers = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            workers.add(new int[]{wage[i], quality[i]});
        }

        // Sort the workers by their wage-to-quality ratio
        workers.sort((a, b) -> Integer.compare(a[0] * b[1], a[1] * b[0]));

        // Priority queue to maintain the k smallest qualities
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

        // Variable to track the sum of the k smallest qualities
        long qualitySum = 0;
        double minCost = Double.MAX_VALUE;

        // Iterate through the workers
        for (int i = 0; i < n; i++) {
            int wageAtCurrent = workers.get(i)[0];
            int qualityAtCurrent = workers.get(i)[1];

            // Add the current quality to the sum and the priority queue
            pq.offer(qualityAtCurrent);
            qualitySum += qualityAtCurrent;

            // If we have more than k workers, remove the largest quality
            if (pq.size() > k) {
                qualitySum -= pq.poll();
            }

            // If we have exactly k workers, calculate the cost
            if (pq.size() == k) {
                double cost = wageAtCurrent * 1.0 * qualitySum / qualityAtCurrent;
                minCost = Math.min(minCost, cost);
            }
        }

        return minCost;
    }
}

