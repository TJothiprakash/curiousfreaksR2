package greedy;
/*You are given three arrays: id, deadline, and profit, where each job is associated with an ID,
a deadline, and a profit. Each job takes 1 unit of time to complete,
 and only one job can be scheduled at a time. You will earn the profit
  associated with a job only if it is completed by its deadline.

Your task is to find:

The maximum number of jobs that can be completed within their deadlines.
The total maximum profit earned by completing those jobs.
Examples :

Input: id = [1, 2, 3, 4], deadline = [4, 1, 1, 1], profit = [20, 1, 40, 30]
Output: [2, 60]
Explanation: Job1 and Job3 can be done with maximum profit of 60 (20+40).
Input: id = [1, 2, 3, 4, 5], deadline = [2, 1, 2, 1, 1], profit = [100, 19, 27, 25, 15]
Output: [2, 127]
Explanation: Job1 and Job3 can be done with maximum profit of 127 (100+27).
Input: id = [1, 2, 3, 4], deadline = [3, 1, 2, 2], profit = [50, 10, 20, 30]
Output: [2, 100]
Explanation: Job1, Job3 and Job4 can be completed with a maximum profit of 100 (50 + 20 + 30).
Constraints:
1 <=  id.size() == deadline.size() == profit.size() <= 105
1 <= id[i], deadline[i] <= id.size()
1 <= profit <= 500


Greedy Approach:

Sort jobs by profit in descending order, as we want to prioritize jobs with higher profits.
Use a disjoint-set approach (or greedy scheduling) to assign jobs to available time slots, ensuring that each job completes by its deadline.
A job can only be scheduled if there's a free time slot before or at its deadline.
Steps:

Sort the jobs based on profit in descending order.
Maintain an array of available time slots. Each time slot is initially available.
For each job, check if a slot is available for the job within its deadline.
If a slot is available, schedule the job and mark that time slot as occupied.
Count the number of jobs completed and sum the profits.
Greedy Strategy:

Start by sorting jobs based on profit.
For each job, try to find the latest available time slot before or at the job's deadline.
If you find such a slot, schedule the job, and accumulate the profit.
Algorithm:
Sort jobs in descending order of profit.
Iterate through the jobs and for each job:
Try to schedule it in the latest available slot before its deadline.
If a slot is found, mark it as occupied and add the profit.
Keep track of the total number of jobs scheduled and their combined profit.

*/

import java.util.Arrays;

public class JobScheduling {

    public static int[] jobScheduling(int[] id, int[] deadline, int[] profit) {
        int n = id.length;

        // Step 1: Create an array of jobs and sort it by profit in descending order
        Job[] jobs = new Job[n];
        for (int i = 0; i < n; i++) {
            jobs[i] = new Job(id[i], deadline[i], profit[i]);
        }

        // Sort jobs by profit in descending order
        Arrays.sort(jobs, (a, b) -> b.profit - a.profit);

        // Step 2: Find the maximum deadline to determine the number of available slots
        int maxDeadline = 0;
        for (Job job : jobs) {
            maxDeadline = Math.max(maxDeadline, job.deadline);
        }

        // Step 3: Create an array to track which time slots are occupied
        boolean[] slots = new boolean[maxDeadline + 1]; // Slot index from 1 to maxDeadline
        Arrays.fill(slots, false); // Initially all slots are free

        int totalProfit = 0;
        int jobCount = 0;

        // Step 4: Try to schedule each job
        for (Job job : jobs) {
            // Find a free slot for the current job, starting from its deadline
            for (int t = job.deadline; t >= 1; t--) {
                if (!slots[t]) { // Slot is free
                    slots[t] = true; // Mark the slot as occupied
                    totalProfit += job.profit; // Add the profit
                    jobCount++; // Increment the job count
                    break; // No need to check for other slots for this job
                }
            }
        }

        // Step 5: Return the results
        return new int[]{jobCount, totalProfit};
    }

    public static void main(String[] args) {
        int[] id1 = {1, 2, 3, 4};
        int[] deadline1 = {4, 1, 1, 1};
        int[] profit1 = {20, 1, 40, 30};
        int[] result1 = jobScheduling(id1, deadline1, profit1);
        System.out.println("Max jobs: " + result1[0] + ", Max profit: " + result1[1]);

        int[] id2 = {1, 2, 3, 4, 5};
        int[] deadline2 = {2, 1, 2, 1, 1};
        int[] profit2 = {100, 19, 27, 25, 15};
        int[] result2 = jobScheduling(id2, deadline2, profit2);
        System.out.println("Max jobs: " + result2[0] + ", Max profit: " + result2[1]);

        int[] id3 = {1, 2, 3, 4};
        int[] deadline3 = {3, 1, 2, 2};
        int[] profit3 = {50, 10, 20, 30};
        int[] result3 = jobScheduling(id3, deadline3, profit3);
        System.out.println("Max jobs: " + result3[0] + ", Max profit: " + result3[1]);
    }

    static class Job {
        int id, deadline, profit;

        Job(int id, int deadline, int profit) {
            this.id = id;
            this.deadline = deadline;
            this.profit = profit;
        }
    }
}
