package greedy;
/*Given two arrays, val[] and wt[], representing the values and weights of items, and an integer capacity representing the maximum weight a knapsack can hold, determine the maximum total value that can be achieved by putting items in the knapsack. You are allowed to break items into fractions if necessary.
Return the maximum value as a double, rounded to 6 decimal places.

Examples :

Input: val[] = [60, 100, 120], wt[] = [10, 20, 30], capacity = 50
Output: 240.000000
Explanation: Take the item with value 60 and weight 10, value 100 and weight 20 and split the third item with value 120 and weight 30, to fit it into weight 20. so it becomes (120/30)*20=80, so the total value becomes 60+100+80.0=240.0 Thus, total maximum value of item we can have is 240.00 from the given capacity of sack.
Input: val[] = [60, 100], wt[] = [10, 20], capacity = 50
Output: 160.000000
Explanation: Take both the items completely, without breaking. Total maximum value of item we can have is 160.00 from the given capacity of sack.
Input: val[] = [10, 20, 30], wt[] = [5, 10, 15], capacity = 100
Output: 60.000000
Explanation: In this case, the knapsack capacity exceeds the combined weight of all items (5 + 10 + 15 = 30). Therefore, we can take all items completely, yielding a total maximum value of 10 + 20 + 30 = 60.000000.
Constraints:
1 <= val.size=wt.size <= 105
1 <= capacity <= 109
1 <= val[i], wt[i] <= 104


This is a Fractional Knapsack Problem, where you are allowed to break items into fractions. The goal is to maximize the total value of the items placed in the knapsack without exceeding the weight capacity.

Approach:
Greedy Strategy:
For each item, calculate its value-to-weight ratio (i.e., value[i] / weight[i]).
Sort the items by this ratio in descending order.
Then, iterate through the sorted items and:
If the item can be completely accommodated within the knapsack's remaining capacity, take it fully.
If not, take the fraction of the item that fits into the remaining capacity.
Termination:
Once the knapsack is full (capacity is exhausted), stop.
Steps:
Calculate the value-to-weight ratio for each item.
Sort items by this ratio in descending order.
Greedy selection:
Select items based on the sorted order, first filling the knapsack with the highest value-to-weight ratio.
If the current item cannot be fully taken (because of weight limitations), take as much as can fit.
Calculate total value as you fill the knapsack.
*/

import java.util.Arrays;
import java.util.Comparator;

class Item {
    int value, weight;

    Item(int value, int weight) {
        this.value = value;
        this.weight = weight;
    }
}

public class FractionalKnapsack {

    public static double getMaxValue(int[] val, int[] wt, int capacity) {
        int n = val.length;

        // Create an array of Item objects
        Item[] items = new Item[n];
        for (int i = 0; i < n; i++) {
            items[i] = new Item(val[i], wt[i]);
        }

        // Sort items by value/weight ratio in descending order
        Arrays.sort(items, new ItemComparator());

        double totalValue = 0.0;
        for (int i = 0; i < n; i++) {
            // If the knapsack can still take more weight
            if (capacity == 0) break;

            // If the entire item can be taken
            if (items[i].weight <= capacity) {
                totalValue += items[i].value;
                capacity -= items[i].weight;
            } else {
                // Take fraction of the item
                totalValue += items[i].value * ((double) capacity / items[i].weight);
                capacity = 0; // knapsack is full
            }
        }

        // Return the result rounded to 6 decimal places
        return Math.round(totalValue * 1000000.0) / 1000000.0;
    }

    public static void main(String[] args) {
        int[] val1 = {60, 100, 120};
        int[] wt1 = {10, 20, 30};
        int capacity1 = 50;
        System.out.println(getMaxValue(val1, wt1, capacity1)); // Output: 240.000000

        int[] val2 = {60, 100};
        int[] wt2 = {10, 20};
        int capacity2 = 50;
        System.out.println(getMaxValue(val2, wt2, capacity2)); // Output: 160.000000

        int[] val3 = {10, 20, 30};
        int[] wt3 = {5, 10, 15};
        int capacity3 = 100;
        System.out.println(getMaxValue(val3, wt3, capacity3)); // Output: 60.000000
    }

    // Comparator to sort items by value/weight ratio in descending order
    static class ItemComparator implements Comparator<Item> {
        public int compare(Item a, Item b) {
            double ratio1 = (double) a.value / a.weight;
            double ratio2 = (double) b.value / b.weight;
            return Double.compare(ratio2, ratio1);
        }
    }
}
