package collections_practice.streams.sept_11;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/*Use groupingBy + mapping + collectingAndThen to compute the top N transactions per account
by amount with stable ordering, and discuss memory usage trade-offs versus
 streaming/heap-bound approaches.

 */

public class TopTransactions {
    static class Transaction {
        int accountId;
        double amount;
        long timestamp;

        public int getAccountId() {
            return accountId;
        }

        public void setAccountId(int accountId) {
            this.accountId = accountId;
        }

        public double getAmount() {
            return amount;
        }

        public void setAmount(double amount) {
            this.amount = amount;
        }

        public long getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(long timestamp) {
            this.timestamp = timestamp;
        }

        public Transaction(int accountId, double amount, long timestamp) {
            this.accountId = accountId;
            this.amount = amount;
            this.timestamp = timestamp;
        }

        @Override
        public String toString() {
            return "Txn{" + "acc=" + accountId + ", amt=" + amount + ", ts=" + timestamp + '}';
        }
    }
    public static void main(String[] args) {
        List<Transaction> txns = List.of(
                new Transaction(1, 100, 1000),
                new Transaction(1, 300, 1001),
                new Transaction(1, 200, 1002),
                new Transaction(2, 50, 2000),
                new Transaction(2, 400, 2001),
                new Transaction(2, 150, 2002)
        );

        int N = 2; // top 2 transactions per account

        Map<Integer, List<Transaction>> topNPerAccount = txns.stream()
                .collect(Collectors.groupingBy(
                        txn -> txn.accountId, // group by account
                        Collectors.collectingAndThen(
                                Collectors.toList(), // collect all transactions per account
                                list -> list.stream()
                                        .sorted(Comparator.comparingDouble(Transaction::getAmount).reversed()
                                                .thenComparingLong(t -> t.timestamp)) // stable sort: amount desc, timestamp asc
                                        .limit(N)
                                        .toList()
                        )
                ));

        System.out.println(topNPerAccount);
    }

}


/*2️⃣ Explanation

groupingBy(txn -> txn.accountId, …)

Splits transactions into buckets per account.

Result type: Map<Integer, List<Transaction>>.

collectingAndThen(Collectors.toList(), fn)

First collects all transactions for the account into a list.

Then applies fn to produce a final value per key.

Here fn is the stream sort → limit → collect pipeline to pick top N.

Stable ordering

Comparator.comparingDouble(Transaction::getAmount).reversed() → sort by amount descending.

.thenComparingLong(t -> t.timestamp) → tie-breaker, ensures stable ordering (earlier timestamp first if amounts are equal).

limit(N) → takes only top N per account.

3️⃣ Memory usage trade-offs
Approach	Memory	Pros	Cons
groupingBy + collectingAndThen	O(total transactions) per account (collects full list per account first)	Simple, stable, readable	Uses full memory per account, may blow up with huge data
Streaming with min-heap per account	O(N × #accounts)	Memory-bounded, can handle very large data	Slightly more complex code, requires custom collector / heap logic

✅ So if you have millions of transactions per account, prefer heap-based approach instead of collecting all transactions first.

4️⃣ Heap-based (memory-efficient) variant
Map<Integer, PriorityQueue<Transaction>> topNHeap = new HashMap<>();

for (Transaction txn : txns) {
    topNHeap.computeIfAbsent(txn.accountId, k -> new PriorityQueue<>(Comparator.comparingDouble(t -> t.amount)))
            .add(txn);
    if (topNHeap.get(txn.accountId).size() > N) {
        topNHeap.get(txn.accountId).poll(); // remove smallest
    }
}


Keeps only N largest transactions per account.

Memory = O(N × #accounts) instead of O(total transactions).

✅ Takeaways

groupingBy + collectingAndThen is easy and readable, but memory grows with total transactions.

Stable ordering is preserved using .sorted().thenComparing().

For very large datasets, consider heap-based streaming (memory-bounded top-N).*/