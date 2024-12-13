package greedy;

import java.util.Arrays;
/*You are given timings of n meetings in the form of (start[i], end[i]) where start[i] is the start time of meeting i and end[i] is the finish time of meeting i. Return the maximum number of meetings that can be accommodated in a single meeting room, when only one meeting can be held in the meeting room at a particular time.

Note: The start time of one chosen meeting can't be equal to the end time of the other chosen meeting.

Examples :

Input: start[] = [1, 3, 0, 5, 8, 5], end[] =  [2, 4, 6, 7, 9, 9]
Output: 4
Explanation: Maximum four meetings can be held with given start and end timings. The meetings are - (1, 2), (3, 4), (5,7) and (8,9)
Input: start[] = [10, 12, 20], end[] = [20, 25, 30]
Output: 1
Explanation: Only one meetings can be held with given start and end timings.
Input: start[] = [1, 2], end[] = [100, 99]
Output: 1
Constraints:
1 ≤ n ≤ 105
0 ≤ start[i] < end[i] ≤ 106

To solve the problem of scheduling the maximum number of meetings in a single meeting room, we follow a greedy algorithm approach, similar to the Activity Selection Problem. The primary difference is that the start time of one meeting cannot equal the end time of another meeting.

Algorithm
Pair and sort meetings:

Pair the start and end times into a list of tuples (start[i], end[i]).
Sort the meetings based on their end times. If two meetings have the same end time, sort by their start times.
Select meetings greedily:

Keep track of the end time of the last selected meeting.
Iterate over the sorted meetings:
If the start time of the current meeting is strictly greater than the end time of the last selected meeting, select the current meeting.
Output the count of selected meetings.


*/


public class MaximumMeetings {
    public static int maxMeetings(int[] start, int[] end) {
        int n = start.length;
        // Pair start and end times into a 2D array
        int[][] meetings = new int[n][2];
        for (int i = 0; i < n; i++) {
            meetings[i][0] = start[i];
            meetings[i][1] = end[i];
        }

        // Sort meetings by end time, if equal, by start time
        Arrays.sort(meetings, (a, b) -> a[1] != b[1] ? a[1] - b[1] : a[0] - b[0]);

        // Greedily select meetings
        int count = 0;
        int lastEndTime = -1;

        for (int i = 0; i < n; i++) {
            if (meetings[i][0] > lastEndTime) { // Check if meeting can be scheduled
                count++;
                lastEndTime = meetings[i][1]; // Update the end time
            }
        }

        return count;
    }

    public static void main(String[] args) {
        int[] start1 = {1, 3, 0, 5, 8, 5};
        int[] end1 = {2, 4, 6, 7, 9, 9};
        System.out.println(maxMeetings(start1, end1)); // Output: 4

        int[] start2 = {10, 12, 20};
        int[] end2 = {20, 25, 30};
        System.out.println(maxMeetings(start2, end2)); // Output: 1

        int[] start3 = {1, 2};
        int[] end3 = {100, 99};
        System.out.println(maxMeetings(start3, end3)); // Output: 1
    }
}

