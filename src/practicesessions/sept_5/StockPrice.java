package practicesessions.sept_5;
/*You are given a stream of records about a particular stock. Each record contains a timestamp and the corresponding price of the stock at that timestamp.

Unfortunately due to the volatile nature of the stock market, the records do not come in order. Even worse, some records may be incorrect. Another record with the same timestamp may appear later in the stream correcting the price of the previous wrong record.

Design an algorithm that:

Updates the price of the stock at a particular timestamp, correcting the price from any previous records at the timestamp.
Finds the latest price of the stock based on the current records. The latest price is the price at the latest timestamp recorded.
Finds the maximum price the stock has been based on the current records.
Finds the minimum price the stock has been based on the current records.
Implement the StockPrice class:

StockPrice() Initializes the object with no price records.
void update(int timestamp, int price) Updates the price of the stock at the given timestamp.
int current() Returns the latest price of the stock.
int maximum() Returns the maximum price of the stock.
int minimum() Returns the minimum price of the stock.


Example 1:

Input
["StockPrice", "update", "update", "current", "maximum", "update", "maximum", "update", "minimum"]
[[], [1, 10], [2, 5], [], [], [1, 3], [], [4, 2], []]
Output
[null, null, null, 5, 10, null, 5, null, 2]

Explanation
StockPrice stockPrice = new StockPrice();
stockPrice.update(1, 10); // Timestamps are [1] with corresponding prices [10].
stockPrice.update(2, 5);  // Timestamps are [1,2] with corresponding prices [10,5].
stockPrice.current();     // return 5, the latest timestamp is 2 with the price being 5.
stockPrice.maximum();     // return 10, the maximum price is 10 at timestamp 1.
stockPrice.update(1, 3);  // The previous timestamp 1 had the wrong price, so it is updated to 3.
                          // Timestamps are [1,2] with corresponding prices [3,5].
stockPrice.maximum();     // return 5, the maximum price is 5 after the correction.
stockPrice.update(4, 2);  // Timestamps are [1,2,4] with corresponding prices [3,5,2].
stockPrice.minimum();     // return 2, the minimum price is 2 at timestamp 4.


Constraints:

1 <= timestamp, price <= 109
At most 105 calls will be made in total to update, current, maximum, and minimum.
current, maximum, and minimum will be called only after update has been called at least once.*/
import java.util.*;

class StockPrice {
    private Map<Integer, Integer> timePrice;
    private PriorityQueue<int[]> maxHeap;
    private PriorityQueue<int[]> minHeap;
    private int latestTimestamp;

    public StockPrice() {
        timePrice = new HashMap<>();
        maxHeap = new PriorityQueue<>((a, b) -> b[0] - a[0]); // max-heap by price
        minHeap = new PriorityQueue<>((a, b) -> a[0] - b[0]); // min-heap by price
        latestTimestamp = 0;
    }

    public void update(int timestamp, int price) {
        timePrice.put(timestamp, price);
        latestTimestamp = Math.max(latestTimestamp, timestamp);
        maxHeap.offer(new int[]{price, timestamp});
        minHeap.offer(new int[]{price, timestamp});
    }

    public int current() {
        return timePrice.get(latestTimestamp);
    }

    public int maximum() {
        while (!maxHeap.isEmpty()) {
            int[] top = maxHeap.peek();
            if (timePrice.get(top[1]) == top[0]) {
                return top[0];
            }
            maxHeap.poll(); // discard outdated entry
        }
        return -1; // should never reach
    }

    public int minimum() {
        while (!minHeap.isEmpty()) {
            int[] top = minHeap.peek();
            if (timePrice.get(top[1]) == top[0]) {
                return top[0];
            }
            minHeap.poll(); // discard outdated entry
        }
        return -1; // should never reach
    }
}

