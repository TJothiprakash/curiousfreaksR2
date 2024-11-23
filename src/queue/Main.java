package queue;

import static queue.FirstNonRepeatingCharacter.firstNonRepeating;
import static queue.GasStation.canCompleteCircuit;

public class Main {
    public static void main(String[] args) {
        /*try {
            QueueUsingStacks<Integer> queue = new QueueUsingStacks<>();

            // Insert elements
            queue.insert(1);
            queue.insert(2);
            queue.insert(3);
            System.out.println("Queue after insertions:");
            queue.display(); // Output: Front -> 1 <- 2 <- 3 <- END

            // Remove an element
            System.out.println("Removed: " + queue.remove()); // Output: Removed: 1
            System.out.println("Queue after removal:");
            queue.display(); // Output: Front -> 2 <- 3 <- END

            // Insert another element
            queue.insert(4);
            System.out.println("Queue after another insertion:");
            queue.display(); // Output: Front -> 2 <- 3 <- 4 <- END

            // Remove another element
            System.out.println("Removed: " + queue.remove()); // Output: Removed: 2
            System.out.println("Queue after removal:");
            queue.display(); // Output: Front -> 3 <- 4 <- END

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
*/
       /* // Test case 1
        int[] gas1 = {4, 6, 7, 4};
        int[] cost1 = {6, 5, 3, 5};
        System.out.println(canCompleteCircuit(gas1, cost1)); // Output: 1

        // Test case 2
        int[] gas2 = {1, 2, 3, 4, 5};
        int[] cost2 = {3, 4, 5, 1, 2};
        System.out.println(canCompleteCircuit(gas2, cost2)); // Output: 3

        // Test case 3
        int[] gas3 = {2, 3, 4};
        int[] cost3 = {3, 4, 3};
        System.out.println(canCompleteCircuit(gas3, cost3)); // Output: -1
*/
      /*  System.out.println(firstNonRepeating("aabc"));  // Output: "a#bb"
        System.out.println(firstNonRepeating("zz"));    // Output: "z#"
        System.out.println(firstNonRepeating("bbafg"));    // Output: "b#"
*/

        // Example Test Case 1
        LRUCache cache = new LRUCache(2);
        cache.set(1, 2);
        System.out.println(cache.get(1)); // Output: 2
        cache.set(2, 3);
        System.out.println(cache.get(2)); // Output: 3
        cache.set(1, 5); // Updates the value of key 1 to 5
        System.out.println(cache.get(1)); // Output: 5

        // Example Test Case 2
        LRUCache cache2 = new LRUCache(2);
        cache2.set(1, 2);
        cache2.set(2, 3);
        cache2.set(1, 5);
        cache2.set(4, 5);
        System.out.println(cache2.get(4)); // Output: 5
        System.out.println(cache2.get(3)); // Output: -1

    }
}
