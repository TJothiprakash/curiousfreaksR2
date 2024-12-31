package collections_practice.streams.exercise_1;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Customer> customers = List.of(
                new Customer(
                        "C001",
                        "Alice",
                        List.of(
                                new Order("O101", 15000, "DELIVERED", LocalDate.of(2024, 12, 01)),
                                new Order("O102", 20000, "PENDING", LocalDate.of(2024, 12, 15)),
                                new Order("O103", 5000, "CANCELLED", LocalDate.of(2024, 11, 28))
                        )
                ),
                new Customer(
                        "C002",
                        "Bob",
                        List.of(
                                new Order("O201", 50000, "DELIVERED", LocalDate.of(2024, 12, 05)),
                                new Order("O202", 25000, "DELIVERED", LocalDate.of(2024, 12, 12)),
                                new Order("O203", 3000, "PENDING", LocalDate.of(2024, 12, 20))
                        )
                ),
                new Customer(
                        "C003",
                        "Charlie",
                        List.of(
                                new Order("O301", 10000, "CANCELLED", LocalDate.of(2024, 12, 1)),
                                new Order("O302", 45000, "DELIVERED", LocalDate.of(2024, 12, 8)),
                                new Order("O303", 3000, "DELIVERED", LocalDate.of(2024, 12, 18))
                        )
                ),
                new Customer(
                        "C004",
                        "David",
                        List.of(
                                new Order("O401", 12000, "PENDING", LocalDate.of(2024, 11, 30)),
                                new Order("O402", 8000, "CANCELLED", LocalDate.of(2024, 12, 05))
                        )
                ),
                new Customer(
                        "C005",
                        "Eve",
                        List.of(
                                new Order("O501", 60000, "DELIVERED", LocalDate.of(2024, 12, 10)),
                                new Order("O502", 20000, "DELIVERED", LocalDate.of(2024, 12, 15)),
                                new Order("O503", 15000, "DELIVERED", LocalDate.of(2024, 12, 25))
                        )
                )
        );

        double totalRevenue = customers.stream()
                .flatMap(customer -> customer.getOrders().stream())
                .filter(order -> order.getStatus().equals("DELIVERED"))
                .mapToDouble(Order::getAmount)
                .sum();

        System.out.println(totalRevenue);

        /*For each task, use a combination of Streams and the appropriate methods:

Use filter for conditions (e.g., filter "DELIVERED" orders).
Use map to extract specific fields or transform data (e.g., map orders to their amounts).
Use reduce to calculate totals (e.g., sum of amounts).
Use sorted for sorting (e.g., recent orders).
Use collect for grouping or converting results into collections (e.g., Collectors.groupingBy).
Step 3: Test and Debug
Print intermediate results to ensure your code works as expected.
Handle edge cases like customers with no orders*/
        List<Order> totalOrders = customers.stream().flatMap(customer -> customer.getOrders().stream()).collect(Collectors.toList());
        System.out.println(totalOrders);
        List<Order> deliveredOrders = totalOrders.stream().filter((order -> order.getStatus().equals("DELIVERED"))).collect(Collectors.toList());
        System.out.println(deliveredOrders);
        double totalDeliveredAmount = deliveredOrders.stream()
                .map(Order::getAmount)
                .mapToDouble(Double::doubleValue)
                .sum();
        System.out.println(totalDeliveredAmount);
    }
}