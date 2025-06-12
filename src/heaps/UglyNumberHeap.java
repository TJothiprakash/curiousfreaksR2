package heaps;
import java.util.*;
/*n ugly number is a positive integer whose prime factors are limited to 2, 3, and 5.

Given an integer n, return the nth ugly number.



Example 1:

Input: n = 10
Output: 12
Explanation: [1, 2, 3, 4, 5, 6, 8, 9, 10, 12] is the sequence of the first 10 ugly numbers.
Example 2:

Input: n = 1
Output: 1
Explanation: 1 has no prime factors, therefore all of its prime factors are limited to 2, 3, and 5.


Constraints:

1 <= n <= 1690
*/
public class UglyNumberHeap {
    public static int nthUglyNumber(int n) {
        Set<Long> seen = new HashSet<>();
        PriorityQueue<Long> minHeap = new PriorityQueue<>();
        long[] primes = {2, 3, 5};

        minHeap.add(1L);
        seen.add(1L);

        long currentUgly = 1;

        for (int i = 0; i < n; i++) {
            currentUgly = minHeap.poll();

            for (long prime : primes) {
                long next = currentUgly * prime;
                if (!seen.contains(next)) {
                    seen.add(next);
                    minHeap.add(next);
                }
            }
        }

        return (int) currentUgly;
    }

    public static void main(String[] args) {
        System.out.println(nthUglyNumber(10)); // Output: 12
        System.out.println(nthUglyNumber(1));  // Output: 1
    }
}

