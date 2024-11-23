package queue;

public class GasStation {
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

