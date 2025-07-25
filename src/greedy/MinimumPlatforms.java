package greedy;

/*You are given the arrival times arr[] and departure times dep[] of all trains that arrive at a railway station on the same day. Your task is to determine the minimum number of platforms required at the station to ensure that no train is kept waiting.

At any given time, the same platform cannot be used for both the arrival of one train and
 the departure of another. Therefore, when two trains arrive at the same time, or when one arrives before another departs, additional platforms are required to accommodate both trains.

Examples:

Input: arr[] = [900, 940, 950, 1100, 1500, 1800], dep[] = [910, 1200, 1120, 1130, 1900, 2000]
Output: 3
Explanation: There are three trains during the time 9:40 to 12:00. So we need a minimum of 3 platforms.
Input: arr[] = [900, 1235, 1100], dep[] = [1000, 1240, 1200]
Output: 1
Explanation: All train times are mutually exclusive. So we need only one platform
Input: arr[] = [1000, 935, 1100], dep[] = [1200, 1240, 1130]
Output: 3
Explanation: All 3 trains have to be there from 11:00 to 11:30
Constraints:
1≤ number of trains ≤ 50000
0000 ≤ arr[i] ≤ dep[i] ≤ 2359
Note: Time intervals are in the 24-hour format(HHMM) ,
where the first two characters represent hour (between 00 to 23 ) and
the last two characters represent minutes (this will be <= 59 and >= 0).*/


/*This problem is a variation of the Interval Scheduling Problem,
but instead of selecting non-overlapping intervals, the goal is to calculate the maximum number
of overlapping intervals at any point in time. Here's how to solve it:

Approach:
Sort Events:

Treat arrivals as starting events and departures as ending events.
Sort both arrival and departure arrays independently.
Traverse Sorted Events:

Use two pointers, one for arr[] (arrival times) and another for dep[] (departure times).
Count the number of platforms needed at any point by incrementing the platform count for
 arrivals and decrementing it for departures.
Maintain Maximum Platforms:

Track the maximum number of platforms required during traversal.
Steps:
Sort both arr[] and dep[].
Use two pointers:
i traverses the arr[].
j traverses the dep[].
If arr[i] <= dep[j], increment platform count (i++).
Else, decrement platform count (j++).
Update the maximum platforms required after each change.
*/
import java.util.Arrays;

public class MinimumPlatforms {
    public static int findPlatform(int[] arr, int[] dep) {
        // Sort arrival and departure times
        Arrays.sort(arr);
        Arrays.sort(dep);

        int n = arr.length;
        int platforms = 0, maxPlatforms = 0;
        int i = 0, j = 0;

        // Traverse through sorted arrays
        while (i < n && j < n) {
            // If a train arrives before the previous one departs
            if (arr[i] <= dep[j]) {
                platforms++; // Increment platform count
                i++; // Move to the next arrival
            } else {
                platforms--; // Decrement platform count
                j++; // Move to the next departure
            }
            maxPlatforms = Math.max(maxPlatforms, platforms); // Update maximum platforms
        }

        return maxPlatforms;
    }

    public static void main(String[] args) {
        int[] arr = {900, 940, 950, 1100, 1500, 1800};
        int[] dep = {910, 1200, 1120, 1130, 1900, 2000};

        System.out.println("Minimum Platforms Required = " + findPlatform(arr, dep));
    }
}
