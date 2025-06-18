package binarysearch;

public class InfiniteArrayofZerosandOnes {
    public static void main(String[] args) {
        int [] infiniteArray ={1,2,3,4,5,6,7,8,9,10, 1};
        long ans = InfiniteArray.findFirstOne(infiniteArray);
        System.out.println("The first one is at index: " + ans);
    }
}

class InfiniteArray {

    // Simulated infinite array access function (provided to user)
    static int get(long index) {
        // This will be implemented by the system internally,
        int [] infiniteArray ={1,2,3,4,5,6,7,8,9,10, 1};
        // Here’s a dummy version just for testing.
        // You won’t implement this in actual test.
        return index >= findFirstOne(infiniteArray) ? 1 : 0;
    }

    public static long findFirstOne(int[] infiniteArray) {
        long low = 0;
        long high = 1;

        // Step 1: Exponentially increase high until you find a 1
        while (get(high) == 0) {
            low = high;
            high = high * 2;
        }

        // Step 2: Binary search for the first 1 between low and high
        long ans = -1;
        while (low <= high) {
            long mid = low + (high - low) / 2;

            if (get(mid) == 1) {
                ans = mid;      // potential answer, but check if there's a smaller one
                high = mid - 1; // move to left side
            } else {
                low = mid + 1;
            }
        }

        return ans;
    }
}

