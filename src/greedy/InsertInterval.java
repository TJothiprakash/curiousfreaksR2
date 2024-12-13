package greedy;
/*
You are given an array of non-overlapping intervals intervals where intervals[i] = [starti, endi] represent the start and the end of the ith interval and intervals is sorted in ascending order by starti. You are also given an interval newInterval = [start, end] that represents the start and end of another interval.

Insert newInterval into intervals such that intervals is still sorted in ascending order by starti and intervals still does not have any overlapping intervals (merge overlapping intervals if necessary).

Return intervals after the insertion.

Note that you don't need to modify intervals in-place. You can make a new array and return it.



Example 1:

Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
Output: [[1,5],[6,9]]
Example 2:

Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
Output: [[1,2],[3,10],[12,16]]
Explanation: Because the new interval [4,8] overlaps with [3,5],[6,7],[8,10].


Constraints:

0 <= intervals.length <= 104
intervals[i].length == 2
0 <= starti <= endi <= 105
intervals is sorted by starti in ascending order.
newInterval.length == 2
0 <= start <= end <= 105
To solve this problem, the approach involves three major steps:

Inserting the new interval: Since the input array is already sorted by the start time, we need to determine the appropriate position for the new interval by comparing its start time with the intervals in the given list.
Merging overlapping intervals: Once the new interval is inserted, we need to merge any overlapping intervals. This is done by comparing the start and end times of intervals.
Returning the result: After merging the intervals, we return the updated list.
Approach:
Insert the new interval:
Traverse through the existing intervals to find the correct position for the new interval based on its start time.
If the new interval starts before an existing interval, we insert it before that interval.
Merge overlapping intervals:
After inserting the new interval, iterate through the list of intervals and merge any intervals that overlap. Two intervals overlap if the start of the second interval is less than or equal to the end of the first interval.
If an interval does not overlap, simply add it to the result list.
Edge cases:
Handle cases where the new interval does not overlap with any existing intervals.
Handle cases where there are no existing intervals (i.e., intervals is empty).
*/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InsertInterval {
    public static int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> result = new ArrayList<>();

        // Step 1: Add all intervals ending before the new interval starts
        int i = 0;
        while (i < intervals.length && intervals[i][1] < newInterval[0]) {
            result.add(intervals[i]);
            i++;
        }

        // Step 2: Merge overlapping intervals
        while (i < intervals.length && intervals[i][0] <= newInterval[1]) {
            newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
            newInterval[1] = Math.max(newInterval[1], intervals[i][1]);
            i++;
        }
        result.add(newInterval);

        // Step 3: Add all the remaining intervals
        while (i < intervals.length) {
            result.add(intervals[i]);
            i++;
        }

        // Convert result list to an array and return it
        return result.toArray(new int[result.size()][]);
    }

    public static void main(String[] args) {
        // Test cases
        int[][] intervals1 = {{1, 3}, {6, 9}};
        int[] newInterval1 = {2, 5};
        System.out.println(Arrays.deepToString(insert(intervals1, newInterval1))); // Output: [[1, 5], [6, 9]]

        int[][] intervals2 = {{1, 2}, {3, 5}, {6, 7}, {8, 10}, {12, 16}};
        int[] newInterval2 = {4, 8};
        System.out.println(Arrays.deepToString(insert(intervals2, newInterval2))); // Output: [[1, 2], [3, 10], [12, 16]]
    }
}
