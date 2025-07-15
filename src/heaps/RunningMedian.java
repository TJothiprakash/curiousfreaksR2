package heaps;
/*Given a data stream arr[] where integers are read sequentially,
the task is to determine the median of the elements encountered so far after each new integer is read.

There are two cases for median on the basis of data set size.

1. If the data set has an odd number then the middle one will be consider as median.
2. If the data set has an even number then there is no distinct middle value and the
 median will be the arithmetic mean of the two middle values.

Examples:

Input:  arr[] = [5, 15, 1, 3, 2, 8]
Output: [5.0, 10.0, 5.0, 4.0, 3.0, 4.0]
Explanation:
After reading 1st element of stream – 5 -> median = 5.0
After reading 2nd element of stream – 5, 15 -> median = (5+15)/2 = 10.0
After reading 3rd element of stream – 5, 15, 1 -> median = 5.0
After reading 4th element of stream – 5, 15, 1, 3 ->  median = (3+5)/2 = 4.0
After reading 5th element of stream – 5, 15, 1, 3, 2 -> median = 3.0
After reading 6th element of stream – 5, 15, 1, 3, 2, 8 ->  median = (3+5)/2 = 4.0
Input: arr[] = [2, 2, 2, 2]
Output: [2.0, 2.0, 2.0, 2.0]
Explanation:
After reading 1st element of stream – 2 -> median = 2.0
After reading 2nd element of stream – 2, 2 -> median = (2+2)/2 = 2.0
After reading 3rd element of stream – 2, 2, 2 -> median = 2.0
After reading 4th element of stream – 2, 2, 2, 2 ->  median = (2+2)/2 = 2.0
Constraints:
1 <= arr.size() <= 105
1 <= x <= 106
Expected Complexities


✅ Heaps Used:
Max Heap (left) → keeps the smaller half of the numbers.

Min Heap (right) → keeps the larger half of the numbers.

This allows us to:

Quickly get the largest of the smaller half.

Quickly get the smallest of the larger half.

Maintain balance between the two heaps (size difference at most 1).

⚙️ Algorithm Steps:
For each incoming number x:

Insert into max heap if x is smaller than or equal to top of max heap.

Else insert into min heap.

Balance the heaps so that the size difference is at most 1.

Calculate the median:

If both heaps are equal size → median is average of tops of both heaps.

Else → median is the top of the bigger heap.

*/
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class RunningMedian {

    public static @NotNull List<Double> getMedians(int[] arr) {
        List<Double> medians = new ArrayList<>();

        // Max Heap for lower half
        PriorityQueue<Integer> left = new PriorityQueue<>(Collections.reverseOrder());
        // Min Heap for upper half
        PriorityQueue<Integer> right = new PriorityQueue<>();

        for (int num : arr) {
            // Step 1: Insert
            if (left.isEmpty() || num <= left.peek()) {
                left.add(num);
            } else {
                right.add(num);
            }

            // Step 2: Balance heaps
            if (left.size() > right.size() + 1) {
                right.add(left.poll());
            } else if (right.size() > left.size()) {
                left.add(right.poll());
            }

            // Step 3: Get median
            if (left.size() == right.size()) {
                medians.add((left.peek() + right.peek()) / 2.0);
            } else {
                medians.add((double) left.peek());
            }
        }

        return medians;
    }

    public static void main(String[] args) {
        int[] stream1 = {5, 15, 1, 3, 2, 8};
        System.out.println(getMedians(stream1));  // [5.0, 10.0, 5.0, 4.0, 3.0, 4.0]

        int[] stream2 = {2, 2, 2, 2};
        System.out.println(getMedians(stream2));  // [2.0, 2.0, 2.0, 2.0]
    }
}
