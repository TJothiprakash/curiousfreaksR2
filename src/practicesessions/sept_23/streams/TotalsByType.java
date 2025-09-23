package practicesessions.sept_23.streams;

import java.security.Key;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.*;

class Transaction {
    int id;
    String type;
    int amount;

    public Transaction(int id, String type, int amount) {
        this.id = id;
        this.type = type;
        this.amount = amount;
    }

    // getters if needed
}

public class TotalsByType {
    public static void main(String[] args) {
        List<Transaction> transactions = List.of(
                new Transaction(1, "FOOD", 120),
                new Transaction(2, "FOOD", 80),
                new Transaction(3, "FUEL", 500),
                new Transaction(4, "BILLS", 300),
                new Transaction(5, "FUEL", 200)
        );

        // Step 1: Compute total amount per type
        Map<String, Integer> totalsByType = transactions.stream()
                .collect(Collectors.groupingBy(t -> t.type, Collectors.summingInt(t -> t.amount)));

        // TODO: use stream() and Collectors.groupingBy / summingInt

        // Step 2: Find the type with max total
        String maxType = "";
        int maxTotal = 0;
        // TODO: iterate over totalsByType to find max
        Map.Entry<String, Integer> maxEntry = totalsByType.entrySet().stream()
                .max(Map.Entry.comparingByValue()).orElse(null);

        // Print results
        maxTotal = maxEntry.getValue();
        maxType = maxEntry.getKey();

        System.out.println("Totals by type: " + totalsByType);
        System.out.println("Max type: " + maxType + " (" + maxTotal + ")");
    }
}
