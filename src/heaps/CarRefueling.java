package heaps;
/*A car travels from a starting position to a destination which is target miles
east of the starting position.

There are gas stations along the way. The gas stations are represented as an array stations
 where stations[i] = [positioni, fueli] indicates that the ith gas station is positioni miles
 east of the starting position and has fueli liters of gas.

The car starts with an infinite tank of gas, which initially has startFuel liters of fuel in it.
 It uses one liter of gas per one mile that it drives. When the car reaches a gas station,
  it may stop and refuel, transferring all the gas from the station into the car.

Return the minimum number of refueling stops the car must make in order to reach its
destination. If it cannot reach the destination, return -1.

Note that if the car reaches a gas station with 0 fuel left, the car can still refuel there.
If the car reaches the destination with 0 fuel left, it is still considered to have arrived.



Example 1:

Input: target = 1, startFuel = 1, stations = []
Output: 0
Explanation: We can reach the target without refueling.
Example 2:

Input: target = 100, startFuel = 1, stations = [[10,100]]
Output: -1
Explanation: We can not reach the target (or even the first gas station).
Example 3:

Input: target = 100, startFuel = 10, stations = [[10,60],[20,30],[30,30],[60,40]]
Output: 2
Explanation: We start with 10 liters of fuel.
We drive to position 10, expending 10 liters of fuel.  We refuel from 0 liters to 60 liters of gas.
Then, we drive from position 10 to position 60 (expending 50 liters of fuel),
and refuel from 10 liters to 50 liters of gas.  We then drive to and reach the target.
We made 2 refueling stops along the way, so we return 2.


Constraints:

1 <= target, startFuel <= 109
0 <= stations.length <= 500
1 <= positioni < positioni+1 < target
1 <= fueli < 109
o solve the problem of determining the minimum number of refueling stops a car must make
 in order to reach its destination, we can employ a greedy algorithm with the help of a
 max-heap (priority queue). Here's the step-by-step approach:

Approach:
Initial Setup:

The car starts with startFuel liters of fuel and needs to reach a target distance.
There are gas stations along the way, and each station provides fuel. The stations
are sorted by their position in ascending order.
Greedy Strategy:

At any point, the car can either:
Continue driving with the fuel it currently has.
Refuel at a gas station if it has enough fuel to reach that station.
To minimize the number of refueling stops, we want to always refuel at the station that
 provides the most fuel when we run out of enough fuel to reach the target or the next station.
  This can be achieved using a max-heap.
Algorithm Steps:

Start by considering the car’s initial fuel.
Traverse through each station, and for each station, check if the car can reach it with the current fuel.
 If yes, continue.
If not, use a max-heap to keep track of all the stations visited so far, and refuel at the
 station that gives the maximum fuel when the car is about to run out of fuel.
If at any point the car cannot reach a station or the target with the available fuel, return -1.
Continue until the car reaches the target.
Edge Cases:

If the car can already reach the target with the initial fuel, return 0.
If there are no gas stations and the initial fuel is insufficient to reach the target, return -1.
*/

import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

public class CarRefueling {
    public static int minRefuelStops(int target, int startFuel, int[][] stations) {
        // Adding a dummy station at the target to facilitate the loop
        stations = Arrays.copyOf(stations, stations.length + 1);
        stations[stations.length - 1] = new int[]{target, 0};

        // Max-heap to store the fuel available at each station we have visited
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

        int fuel = startFuel;
        int prevPosition = 0;
        int stops = 0;

        for (int[] station : stations) {
            int position = station[0];
            int availableFuel = station[1];

            // Decrease fuel by the distance between the current station and the previous station
            fuel -= position - prevPosition;

            // If we run out of fuel, use the max-heap to refuel
            while (fuel < 0 && !maxHeap.isEmpty()) {
                fuel += maxHeap.poll();  // Refuel by taking the largest available fuel from the heap
                stops++;
            }

            // If we still can't reach this station, return -1
            if (fuel < 0) {
                return -1;
            }

            // Add the current station's available fuel to the max-heap
            maxHeap.offer(availableFuel);

            // Update previous position
            prevPosition = position;
        }

        return stops;
    }

    public static void main(String[] args) {
        CarRefueling carRefueling = new CarRefueling();

        // Example 1
        int target1 = 1, startFuel1 = 1;
        int[][] stations1 = {};
        System.out.println(CarRefueling.minRefuelStops(target1, startFuel1, stations1));  // Output: 0

        // Example 2
        int target2 = 100, startFuel2 = 1;
        int[][] stations2 = {{10, 100}};
        System.out.println(CarRefueling.minRefuelStops(target2, startFuel2, stations2));  // Output: -1

        // Example 3
        int target3 = 100, startFuel3 = 10;
        int[][] stations3 = {{10, 60}, {20, 30}, {30, 30}, {60, 40}};
        System.out.println(CarRefueling.minRefuelStops(target3, startFuel3, stations3));  // Output: 2
    }
}
