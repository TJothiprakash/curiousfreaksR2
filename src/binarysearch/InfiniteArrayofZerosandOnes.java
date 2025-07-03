/*
Problem:
You are given an **infinite binary array** of 0's followed by 1's (i.e., [0, 0, 0, ..., 1, 1, 1, ...]).
You have access only through a function `get(index)` which returns:
- 0 for zero at that index
- 1 for one at that index

Your task is to find the **first index where 1 appears**. The array is conceptually infinite,
so you don't know its length.

Intuition:
- Since we can't apply binary search directly (as length is unknown), we use exponential search:
    1. Start with a small range.
    2. Keep doubling the range until we find a 1.
    3. Once we know a 1 exists between `low` and `high`, apply binary search in that range.

Dry Run:
Simulated infinite array: [0, 0, 0, 0, 1, 1, 1, 1, ...]
- Start low = 0, high = 1 → get(1) = 0
- Double high → 2 → 4 → 8 → get(8) = 1
- Binary search from low = 4 to high = 8 to find first 1
- Eventually return 4

Code:
*/

package binarysearch;

public class InfiniteArrayofZerosandOnes {
    public static void main(String[] args) {
        int[] infiniteArray = {0, 0, 0, 0, 1, 1, 1, 1, 1};  // Simulated finite slice
        long ans = InfiniteArray.findFirstOne(infiniteArray);
        System.out.println("✅ The first one is at index: " + ans);
    }
}

class InfiniteArray {

    // Simulated infinite array get() function — in real problem, assume only this is available
    static int[] simulatedArray;

    static int get(long index) {
        if (index >= simulatedArray.length) return 1; // Pretend everything beyond last index is 1
        return simulatedArray[(int) index];
    }

    public static long findFirstOne(int[] arrayForTest) {
        simulatedArray = arrayForTest; // assign for testing

        long low = 0;
        long high = 1;

        System.out.println("🔍 Finding the first 1 in simulated infinite array...");

        // Step 1: Exponential expansion
        while (get(high) == 0) {
            System.out.println("   📈 get(" + high + ") = 0 → expanding range");
            low = high;
            high *= 2;
        }

        System.out.println("   ✅ Found 1 at or before index " + high + " → binary search between [" + low + ", " + high + "]");

        // Step 2: Binary search between low and high
        long ans = -1;
        while (low <= high) {
            long mid = low + (high - low) / 2;
            int value = get(mid);
            System.out.println("   🔍 Checking mid = " + mid + " → get(" + mid + ") = " + value);

            if (value == 1) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return ans;
    }

    /*
    Time Complexity:
        - Exponential search: O(log index_of_first_1)
        - Binary search: O(log index_of_first_1)
        - Total = O(log n), where n = index of first 1

    Space Complexity:
        - O(1)
    */
}
