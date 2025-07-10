package queue;

public class GasStation {

//    O(n)O(1)
    public static int canCompleteCircuit(int[] gas, int[] cost) {
        int totalGas = 0, totalCost = 0, currentGas = 0, startIndex = 0;

        for (int i = 0; i < gas.length; i++) {
            totalGas += gas[i];
            totalCost += cost[i];
            currentGas += gas[i] - cost[i];

            // If currentGas becomes negative, reset the starting point
            if (currentGas < 0) {
                startIndex = i + 1; // Start from the next station
                currentGas = 0; // Reset the current gas
            }
        }

        // If total gas is less than total cost, it's impossible to complete the circuit
        if (totalGas < totalCost) {
            return -1;
        }

        return startIndex;
    }


}

/*public class GasStation {
    public static int canCompleteCircuit(int[] gas, int[] cost) {
        int totalGas = 0;   // Total gas in all stations
        int totalCost = 0;  // Total cost to travel between stations
        int currentGas = 0; // Current gas while making a trip
        int startStation = 0; // Starting station index

        for (int i = 0; i < gas.length; i++) {
            totalGas += gas[i];
            totalCost += cost[i];
            currentGas += gas[i] - cost[i];

            // If currentGas is negative, we can't complete the circuit starting from this station
            if (currentGas < 0) {
                // Reset the starting station to the next station
                startStation = i + 1;
                currentGas = 0; // Reset currentGas because we're starting fresh
            }
        }

        // If totalGas is greater than or equal to totalCost, return the starting station
        return (totalGas >= totalCost) ? startStation : -1;
    }

    public static void main(String[] args) {
        // Test cases
        int[] gas1 = {4, 6, 7, 4};
        int[] cost1 = {6, 5, 3, 5};
        System.out.println(canCompleteCircuit(gas1, cost1)); // Output: 1

        int[] gas2 = {1, 2, 3, 4, 5};
        int[] cost2 = {3, 4, 5, 1, 2};
        System.out.println(canCompleteCircuit(gas2, cost2)); // Output: 3

        int[] gas3 = {2, 3, 4};
        int[] cost3 = {3, 4, 3};
        System.out.println(canCompleteCircuit(gas3, cost3)); // Output: -1
    }
}
*/