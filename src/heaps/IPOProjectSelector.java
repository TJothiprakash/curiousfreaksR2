package heaps;

import java.util.*;

/*Suppose LeetCode will start its IPO soon. In order to sell a good price of its shares to Venture Capital, LeetCode would like to work on some projects to increase its capital before the IPO. Since it has limited resources, it can only finish at most k distinct projects before the IPO. Help LeetCode design the best way to maximize its total capital after finishing at most k distinct projects.

You are given n projects where the ith project has a pure profit profits[i] and a minimum capital of capital[i] is needed to start it.

Initially, you have w capital. When you finish a project, you will obtain its pure profit and the profit will be added to your total capital.

Pick a list of at most k distinct projects from given projects to maximize your final capital, and return the final maximized capital.

The answer is guaranteed to fit in a 32-bit signed integer.



Example 1:

Input: k = 2, w = 0, profits = [1,2,3], capital = [0,1,1]
Output: 4
Explanation: Since your initial capital is 0, you can only start the project indexed 0.
After finishing it you will obtain profit 1 and your capital becomes 1.
With capital 1, you can either start the project indexed 1 or the project indexed 2.
Since you can choose at most 2 projects, you need to finish the project indexed 2 to get the maximum capital.
Therefore, output the final maximized capital, which is 0 + 1 + 3 = 4.
Example 2:

Input: k = 3, w = 0, profits = [1,2,3], capital = [0,1,2]
Output: 6


Constraints:

1 <= k <= 105
0 <= w <= 109
n == profits.length
n == capital.length
1 <= n <= 105
0 <= profits[i] <= 104
0 <= capital[i] <= 109

✅ Greedy Idea + Max Heap
At every step, always:

Pick the most profitable project that you can afford with your current capital w.

🔧 Algorithm Steps:
Pair up the projects as (capital, profit) and sort them by capital required.

Use a max-heap (priority queue) to store the profits of all projects that can be afforded at current capital w.

Repeat at most k times:

Push all profits into the max heap for which capital[i] <= w.

If the heap is empty → break (no affordable project).

Else, pick the most profitable project (top of max heap) → add its profit to w.

*/
public class IPOProjectSelector {

    static class Project {
        int capital, profit;

        Project(int c, int p) {
            capital = c;
            profit = p;
        }
    }

    public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
        int n = profits.length;
        List<Project> projects = new ArrayList<>();

        // Step 1: Pair and sort projects by capital required
        for (int i = 0; i < n; i++) {
            projects.add(new Project(capital[i], profits[i]));
        }

        projects.sort(Comparator.comparingInt(a -> a.capital));

        // Step 2: Max Heap to store profits of affordable projects
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        int i = 0;

        while (k-- > 0) {
            // Add all affordable projects' profits to maxHeap
            while (i < n && projects.get(i).capital <= w) {
                maxHeap.add(projects.get(i).profit);
                i++;
            }

            if (maxHeap.isEmpty()) break;  // No project can be picked now

            // Pick the most profitable one
            w += maxHeap.poll();
        }

        return w;
    }

    // Driver
    public static void main(String[] args) {
        IPOProjectSelector ipo = new IPOProjectSelector();

        System.out.println(ipo.findMaximizedCapital(2, 0, new int[]{1, 2, 3}, new int[]{0, 1, 1})); // 4
        System.out.println(ipo.findMaximizedCapital(3, 0, new int[]{1, 2, 3}, new int[]{0, 1, 2})); // 6
    }
}

