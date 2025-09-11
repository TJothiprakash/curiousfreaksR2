package collections_practice.streams;
/*
Problem:
--------
Given a list/array of integers, find the sum using Java Streams in multiple ways.

Intuition:
----------
Java Streams provide specialized methods (like mapToInt().sum()) and generalized ones
(like reduce(), collectors, statistics). Each has different use-cases:
- mapToInt().sum() → best for sum.
- reduce() → general folding (can be used for sum, product, min, max, etc.).
- collectors → more declarative, works well with downstream collectors.
- summaryStatistics() → useful when we need min, max, average along with sum.
- Arrays.stream() → shortcut for primitive arrays.

Dry Run (reduce with identity = 0, input = [1,2,3,4,5]):
--------------------------------------------------------
Initial sum = 0
Step 1: (0,1) = 1
Step 2: (1,2) = 3
Step 3: (3,3) = 6
Step 4: (6,4) = 10
Step 5: (10,5) = 15
Final Answer = 15

Code:
-----
*/

import java.util.*;
import java.util.stream.*;

public class SumUsingStreams {
    public static void main(String[] args) {
        List<Integer> list = List.of(1, 2, 3, 4, 5);
        int[] arr = {1, 2, 3, 4, 5};

        // 1. mapToInt + sum()
        int sum1 = list.stream().mapToInt(Integer::intValue).sum();
        System.out.println("1. mapToInt().sum() = " + sum1);

        // 2. reduce() without identity
        Optional<Integer> sum2 = list.stream().reduce((a, b) -> a + b);
        System.out.println("2. reduce() without identity = " + sum2.orElse(0));

        // 3. reduce() with identity
        int sum3 = list.stream().reduce(0, (a, b) -> a + b);
        System.out.println("3. reduce() with identity = " + sum3);

        // 4. Collectors.summingInt()
        int sum4 = list.stream().collect(Collectors.summingInt(Integer::intValue));
        System.out.println("4. Collectors.summingInt() = " + sum4);

        // 5. Collectors.reducing()
        int sum5 = list.stream().collect(Collectors.reducing(0, Integer::intValue, Integer::sum));
        System.out.println("5. Collectors.reducing() = " + sum5);

        // 6. IntSummaryStatistics
        int sum6 = Math.toIntExact(list.stream().mapToInt(Integer::intValue).summaryStatistics().getSum());
        System.out.println("6. summaryStatistics().getSum() = " + sum6);

        // 7. Arrays.stream() for arrays
        int sum7 = Arrays.stream(arr).sum();
        System.out.println("7. Arrays.stream(arr).sum() = " + sum7);
    }
}

/*
Complexity:
-----------
Let n = number of elements in the list/array.
- Time Complexity: O(n) for all methods (each element is visited once).
- Space Complexity: O(1) extra space (streams internally may allocate small helpers).
*/
