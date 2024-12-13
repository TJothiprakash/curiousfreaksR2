package greedy;
/*Given an array of intervals intervals where intervals[i] = [starti, endi], return the minimum number of intervals you need to remove to make the rest of the intervals non-overlapping.

Note that intervals which only touch at a point are non-overlapping. For example, [1, 2] and [2, 3] are non-overlapping.



Example 1:

Input: intervals = [[1,2],[2,3],[3,4],[1,3]]
Output: 1
Explanation: [1,3] can be removed and the rest of the intervals are non-overlapping.
Example 2:

Input: intervals = [[1,2],[1,2],[1,2]]
Output: 2
Explanation: You need to remove two [1,2] to make the rest of the intervals non-overlapping.
Example 3:

Input: intervals = [[1,2],[2,3]]
Output: 0
Explanation: You don't need to remove any of the intervals since they're already non-overlapping.


Constraints:

1 <= intervals.length <= 105
intervals[i].length == 2
-5 * 104 <= starti < endi <= 5 * 104

Approach:
Sort the intervals by their end times:

The key observation here is that if you sort intervals by their end times, it becomes easier to find intervals that overlap and remove the ones that cause conflicts. This is because, by considering intervals that finish earlier, we can leave room for the next interval, which minimizes the need for removal.
Iterate through the sorted intervals and count overlapping intervals:

Keep track of the end of the last selected interval.
If the start time of the current interval is less than the end of the last selected interval, it means they overlap, and we need to remove the current interval.
If the start time of the current interval is greater than or equal to the end of the last selected interval, we can select this interval and update the end to be the end of the current interval.
Return the count of removed intervals:

At the end of the iteration, the total number of removed intervals will be the difference between the total intervals and the number of intervals that were selected (non-overlapping).
*/

import java.util.Arrays;

public class NonOverlappingIntervals {
    public static int eraseOverlapIntervals(int[][] intervals) {
        if (intervals.length == 0) return 0;

        // Step 1: Sort intervals by their end time
        Arrays.sort(intervals, (a, b) -> a[1] - b[1]);

        int count = 0;
        int end = intervals[0][1];

        // Step 2: Iterate through intervals
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] < end) {
                // Overlap, so we need to remove this interval
                count++;
            } else {
                // No overlap, update the end time
                end = intervals[i][1];
            }
        }

        return count;
    }

    public static void main(String[] args) {
        // Test cases
        int[][] intervals1 = {{1, 2}, {2, 3}, {3, 4}, {1, 3}};
        System.out.println(eraseOverlapIntervals(intervals1)); // Output: 1

        int[][] intervals2 = {{1, 2}, {1, 2}, {1, 2}};
        System.out.println(eraseOverlapIntervals(intervals2)); // Output: 2

        int[][] intervals3 = {{1, 2}, {2, 3}};
        System.out.println(eraseOverlapIntervals(intervals3)); // Output: 0
    }
}
