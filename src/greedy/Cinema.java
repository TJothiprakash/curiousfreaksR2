package greedy;
/*A cinema has n rows of seats, numbered from 1 to n and there are ten seats in each row, labelled from 1 to 10 as shown in the figure above.

Given the array reservedSeats containing the numbers of seats already reserved, for example, reservedSeats[i] = [3,8] means the seat located in row 3 and labelled with 8 is already reserved.

Return the maximum number of four-person groups you can assign on the cinema seats. A four-person group occupies four adjacent seats in one single row. Seats across an aisle (such as [3,3] and [3,4]) are not considered to be adjacent, but there is an exceptional case on which an aisle split a four-person group, in that case, the aisle split a four-person group in the middle, which means to have two people on each side.



Example 1:



Input: n = 3, reservedSeats = [[1,2],[1,3],[1,8],[2,6],[3,1],[3,10]]
Output: 4
Explanation: The figure above shows the optimal allocation for four groups, where seats mark with blue are already reserved and contiguous seats mark with orange are for one group.
Example 2:

Input: n = 2, reservedSeats = [[2,1],[1,8],[2,6]]
Output: 2
Example 3:

Input: n = 4, reservedSeats = [[4,3],[1,4],[4,6],[1,7]]
Output: 4


Constraints:

1 <= n <= 10^9
1 <= reservedSeats.length <= min(10*n, 10^4)
reservedSeats[i].length == 2
1 <= reservedSeats[i][0] <= n
1 <= reservedSeats[i][1] <= 10
All reservedSeats[i] are distinct.
Key Points to Consider:
The Cinema Layout: Each row has 10 seats, which can be divided into two parts:

Left side: Seats 1 to 5 (indices 1, 2, 3, 4, 5)
Right side: Seats 6 to 10 (indices 6, 7, 8, 9, 10)
Aisle Impact: The aisle splits the row between seats 5 and 6. If there are no reserved seats between seats 5 and 6, you can split a group of 4 into two halves, with two people on the left side and two on the right side.

Reservation Constraints: The reserved seats are provided as pairs [row, seat_number], where each seat is marked as unavailable for the group.

Goal: Maximize the number of groups that can be seated, considering that each group requires 4 adjacent seats, either entirely in one side or across the aisle.

Approach:
Group Possible Configurations: There are several configurations of four contiguous seats:

A group entirely on the left side: [1, 2, 3, 4], [2, 3, 4, 5]
A group entirely on the right side: [6, 7, 8, 9], [7, 8, 9, 10]
A split group across the aisle: [1, 2, 9, 10], [2, 3, 9, 10], [3, 4, 9, 10], [4, 5, 9, 10]
Mark Reserved Seats: For each row, track which seats are reserved, and then check which groups of 4 seats can still fit.

Iterate Over Reserved Seats: Using a dictionary to store which rows have reserved seats, process each row independently to determine how many four-person groups can fit.

Edge Case: If no seats are reserved in a row, all 4 possible group configurations are available.

D
Plan
Seats Layout:

Each row has 10 seats, and a four-person group requires four contiguous seats. There are five possible placements for such a group:
[1,2,3,4]
[2,3,4,5]
[3,4,5,6]
[4,5,6,7]
[5,6,7,8]
[6,7,8,9]
[7,8,9,10]
The split by aisle happens when a group can sit across two segments of the row: [1,2,3,4] and [7,8,9,10] are the two segments of interest. Each group across the aisle will consist of two people on each side of the aisle.
Efficient Representation:

Since the number of rows can be up to
1
0
9
10
9
 , we don't need to explicitly represent each row. Instead, we can represent the reserved seats row by row.
A dictionary can be used to store the reserved seats for each row.
Maximize Group Assignments:

For each row, we need to identify the available positions for a four-person group. This can be done by checking for blocks of reserved seats in each row and counting how many groups fit in the unreserved sections.
For rows that have split groups across an aisle, we check if such a split can be utilized.
Approach:
Data Structure:
Use a dictionary to track reserved seats by row. Each key is a row number, and the value is a set of reserved seat numbers in that row.
Row Analysis:
For each row, check if there are any four contiguous seats available or any split four-person groups across the aisle.
Edge Case:
If the row has no reserved seats, we can easily allocate as many four-person groups as possible.*/

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Cinema {

    public static void main(String[] args) {
        Cinema cinema = new Cinema();

        // Test case 1
        int[][] reservedSeats1 = {{1, 2}, {1, 3}, {1, 8}, {2, 6}, {3, 1}, {3, 10}};
        System.out.println(cinema.maxGroups(3, reservedSeats1)); // Output: 4

        // Test case 2
        int[][] reservedSeats2 = {{2, 1}, {1, 8}, {2, 6}};
        System.out.println(cinema.maxGroups(2, reservedSeats2)); // Output: 2

        // Test case 3
        int[][] reservedSeats3 = {{4, 3}, {1, 4}, {4, 6}, {1, 7}};
        System.out.println(cinema.maxGroups(4, reservedSeats3)); // Output: 4
    }

    public int maxGroups(int n, int[][] reservedSeats) {
        // Dictionary to store reserved seats by row
        Map<Integer, Set<Integer>> reserved = new HashMap<>();

        // Populate reserved seats in the map
        for (int[] seat : reservedSeats) {
            int row = seat[0];
            int seatNum = seat[1];
            reserved.computeIfAbsent(row, k -> new HashSet<>()).add(seatNum);
        }

        // Possible positions for a four-person group
        int[] groupPatterns = {15, 7, 11, 13, 14}; // Corresponds to binary numbers for reserved seats in a row

        int totalGroups = 0;

        // Iterate over each row
        for (int row = 1; row <= n; row++) {
            Set<Integer> rowReserved = reserved.getOrDefault(row, new HashSet<>());
            int availableGroups = 0;

            // Try all possible 4-person group placements
            for (int pattern : groupPatterns) {
                boolean canPlaceGroup = true;

                // Check if the pattern is blocked by reserved seats
                for (int i = 0; i < 10; i++) {
                    if ((pattern & (1 << i)) != 0 && rowReserved.contains(i + 1)) {
                        canPlaceGroup = false;
                        break;
                    }
                }

                if (canPlaceGroup) {
                    availableGroups++;
                }
            }

            totalGroups += availableGroups;
        }

        return totalGroups;
    }
}
