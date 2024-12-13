package greedy;


/*Given some activities with their start and end day given in array start[] and end[]. Select the maximum number of activities that can be performed by a single person, assuming that a person can only work on a single activity at a given day.
Note : Duration of the activity includes both starting and ending day.

Examples:

Input: start[] = [2, 1], end[] = [2, 2]
Output: 1
Explanation: A person can perform only one of the given activities.
Input: start[] = [1, 3, 2, 5], end[] = [2, 4, 3, 6]
Output: 3
Explanation: A person can perform activities 1, 2 and 4.
Input: start[] = [1, 3, 0, 5, 8, 5], end[] = [2, 4, 6, 7, 9, 9]
Output: 4
Explanation: A person can perform activities 0, 1, 3, and 4.
Constraints:
1 ≤ start.size() = end.size() ≤ 2*105
1 ≤ start[i] ≤ end[i] ≤ 109

lgorithm
Sort the activities:

Pair the start and end times of activities into tuples.
Sort these tuples by the end time of the activities.
Select activities greedily:

Start with the first activity (the one that ends the earliest).
For each subsequent activity, check if its start time is greater than or equal to the end time of the last selected activity.
If yes, select the activity.
Output the count of selected activities.


*/

import java.util.Arrays;
import java.util.Comparator;

public class ActivitySelection {
    public static int maxActivities(int[] start, int[] end) {
        int n = start.length;
        // Pair start and end times into a 2D array
        int[][] activities = new int[n][2];
        for (int i = 0; i < n; i++) {
            activities[i][0] = start[i];
            activities[i][1] = end[i];
        }

        // Sort activities by their end times
        Arrays.sort(activities, Comparator.comparingInt(a -> a[1]));

        // Greedily select activities
        int count = 0;
        int lastEndTime = -1;

        for (int i = 0; i < n; i++) {
            if (activities[i][0] > lastEndTime) { // If activity does not overlap
                count++;
                lastEndTime = activities[i][1]; // Update the end time
            }
        }

        return count;
    }

    public static void main(String[] args) {
        int[] start1 = {2, 1};
        int[] end1 = {2, 2};
        System.out.println(maxActivities(start1, end1)); // Output: 1

        int[] start2 = {1, 3, 2, 5};
        int[] end2 = {2, 4, 3, 6};
        System.out.println(maxActivities(start2, end2)); // Output: 3

        int[] start3 = {1, 3, 0, 5, 8, 5};
        int[] end3 = {2, 4, 6, 7, 9, 9};
        System.out.println(maxActivities(start3, end3)); // Output: 4
    }
}
