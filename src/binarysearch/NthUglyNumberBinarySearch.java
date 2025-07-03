package binarysearch;

public class NthUglyNumberBinarySearch {

    public static void main(String[] args) {
        int n = 10;
        System.out.println(nthUglyNumber(n));  // Output: 12
    }

    //O(log n * min(a,b) )O(n)
    public static int nthUglyNumber(int n) {
        int low = 1, high = 2_000_000_000;  // upper bound guess

        while (low < high) {
            int mid = low + (high - low) / 2;
            if (countUglyNumbers(mid) >= n) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }

        return low;
    }

    private static long countUglyNumbers(long x) {
        return x / 2 + x / 3 + x / 5
                - x / lcm(2, 3) - x / lcm(2, 5) - x / lcm(3, 5)
                + x / lcm(2, lcm(3, 5));
    }

    //O(min(a,b))O(n)->stack space
    private static long gcd(long a, long b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    //O(min(a,b))O(1)
    private static long lcm(long a, long b) {
        return a * b / gcd(a, b);
    }
}
//// nthUglyNumber: Time: O(log(range) × log(min(a, b))), Space: O(1)
//// gcd: Time: O(log(min(a, b))), Space: O(log(min(a, b))) if recursive (stack)
//// lcm: Time: O(log(min(a, b))), Space: O(log(min(a, b)))
