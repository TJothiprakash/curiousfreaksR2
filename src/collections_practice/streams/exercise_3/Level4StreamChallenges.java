package collections_practice.streams.exercise_3;

import java.util.*;
import java.util.stream.Collectors;

public class Level4StreamChallenges {

    static class Item {
        private final String name;
        private final int quantity;
        private final double price;

        public Item(String name, int quantity, double price) {
            this.name = name;
            this.quantity = quantity;
            this.price = price;
        }

        public String getName() {
            return name;
        }

        public int getQuantity() {
            return quantity;
        }

        public double getPrice() {
            return price;
        }

        public double total() {
            return price * quantity;
        }

        public String toString() {
            return name + ": " + quantity + " @ ₹" + price;
        }
    }

    static class Order {
        private final String orderId;
        private final String customerName;
        private final List<Item> items;

        public Order(String orderId, String customerName, List<Item> items) {
            this.orderId = orderId;
            this.customerName = customerName;
            this.items = items;
        }

        public String getOrderId() {
            return orderId;
        }

        public String getCustomerName() {
            return customerName;
        }

        public List<Item> getItems() {
            return items;
        }

        public double getTotalValue() {
            return items.stream().mapToDouble(Item::total).sum();
        }

        public String toString() {
            return orderId + " by " + customerName;
        }
    }

    public static void main(String[] args) {
        List<Order> orders = List.of(
                new Order("O1", "JP", List.of(
                        new Item("Pen", 2, 10),
                        new Item("Notebook", 1, 100),
                        new Item("Pencil", 5, 5)
                )),
                new Order("O2", "Arun", List.of(
                        new Item("Notebook", 2, 100),
                        new Item("Marker", 1, 50)
                )),
                new Order("O3", "JP", List.of(
                        new Item("Pen", 1, 10),
                        new Item("Laptop", 1, 55000)
                )),
                new Order("O4", "Gopal", List.of(
                        new Item("Laptop", 1, 60000),
                        new Item("Mouse", 2, 500)
                ))
        );

        // 4️⃣ Group orders by customer name
        Map<String, List<Order>> ordersByCustomer = orders.stream()
                .collect(Collectors.groupingBy(Order::getCustomerName));

        // 5️⃣ For each customer, get a list of item names they bought
        Map<String, List<String>> itemsPerCustomer = orders.stream()
                .collect(Collectors.groupingBy(
                        Order::getCustomerName,
                        Collectors.flatMapping(order -> order.getItems().stream().map(Item::getName), Collectors.toList())
                ));

        // 6️⃣ For each customer, get total spent
        Map<String, Double> totalSpentPerCustomer = orders.stream()
                .collect(Collectors.groupingBy(
                        Order::getCustomerName,
                        Collectors.summingDouble(Order::getTotalValue)
                ));

        // 7️⃣ Most expensive item purchased across all orders
        Item mostExpensiveItem = orders.stream()
                .flatMap(o -> o.getItems().stream())
                .max(Comparator.comparingDouble(Item::getPrice)).orElse(null);

        // 8️⃣ Average price of all items sold
        double avgPrice = orders.stream()
                .flatMap(o -> o.getItems().stream())
                .mapToDouble(Item::getPrice).average().orElse(0);

        // 9️⃣ Group items by name and count how many times sold (sum quantity)
        Map<String, Integer> itemSales = orders.stream()
                .flatMap(o -> o.getItems().stream())
                .collect(Collectors.groupingBy(Item::getName, Collectors.summingInt(Item::getQuantity)));

        // 🔟 Comma-separated string of all distinct item names
        String itemCsv = orders.stream()
                .flatMap(o -> o.getItems().stream())
                .map(Item::getName).distinct().collect(Collectors.joining(", "));

        // 1️⃣1️⃣ Partition items into expensive (> 1000) and cheap
        Map<Boolean, List<Item>> partitionByExpensive = orders.stream()
                .flatMap(o -> o.getItems().stream())
                .collect(Collectors.partitioningBy(item -> item.getPrice() > 1000));

        // 1️⃣2️⃣ Group by item name and get average quantity ordered
        Map<String, Double> avgQtyPerItem = orders.stream()
                .flatMap(o -> o.getItems().stream())
                .collect(Collectors.groupingBy(Item::getName, Collectors.averagingInt(Item::getQuantity)));

        // 1️⃣3️⃣ Sort all items by total revenue (quantity × price)
        List<Item> itemsSortedByRevenue = orders.stream()
                .flatMap(o -> o.getItems().stream())
                .sorted(Comparator.comparingDouble(Item::total).reversed())
                .collect(Collectors.toList());

        // 1️⃣4️⃣ Find order with highest total value
        Order mostValuableOrder = orders.stream()
                .max(Comparator.comparingDouble(Order::getTotalValue)).orElse(null);

        // 1️⃣5️⃣ For each customer, most expensive item they purchased
        Map<String, Item> maxItemPerCustomer = orders.stream()
                .collect(Collectors.groupingBy(
                        Order::getCustomerName,
                        Collectors.collectingAndThen(
                                Collectors.flatMapping(o -> o.getItems().stream(), Collectors.toList()),
                                list -> list.stream().max(Comparator.comparingDouble(Item::getPrice)).orElse(null)
                        )
                ));

        // 1️⃣6️⃣ Item name → total revenue
        Map<String, Double> itemRevenueMap = orders.stream()
                .flatMap(o -> o.getItems().stream())
                .collect(Collectors.groupingBy(Item::getName, Collectors.summingDouble(Item::total)));

        // 1️⃣7️⃣ Customers who bought more than 5 total items
        List<String> customersWithMoreThan5Items = orders.stream()
                .collect(Collectors.groupingBy(
                        Order::getCustomerName,
                        Collectors.summingInt(o -> o.getItems().stream().mapToInt(Item::getQuantity).sum())
                )).entrySet().stream()
                .filter(e -> e.getValue() > 5)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        // 1️⃣8️⃣ For each item, list customers who bought it
        Map<String, Set<String>> itemToCustomers = orders.stream()
                .flatMap(order -> order.getItems().stream()
                        .map(item -> new AbstractMap.SimpleEntry<>(item.getName(), order.getCustomerName())))
                .collect(Collectors.groupingBy(
                        Map.Entry::getKey,
                        Collectors.mapping(Map.Entry::getValue, Collectors.toSet())
                ));

        // 1️⃣9️⃣ Top 3 items by revenue
        List<String> top3Items = itemRevenueMap.entrySet().stream()
                .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
                .limit(3).map(Map.Entry::getKey).collect(Collectors.toList());

        // 2️⃣0️⃣ Histogram: price range → item count
        Map<String, Long> priceHistogram = orders.stream()
                .flatMap(o -> o.getItems().stream())
                .collect(Collectors.groupingBy(
                        item -> {
                            double p = item.getPrice();
                            if (p <= 100) return "<=100";
                            else if (p <= 1000) return "101-1000";
                            else return ">1000";
                        }, Collectors.counting()
                ));

        // 🖨️ You can print any of these here for verification
        System.out.println("4️⃣ Orders by customer: " + ordersByCustomer);
        System.out.println("5️⃣ Items per customer: " + itemsPerCustomer);
        System.out.println("6️⃣ Total spent per customer: " + totalSpentPerCustomer);
        System.out.println("7️⃣ Most expensive item: " + mostExpensiveItem);
        System.out.println("8️⃣ Average item price: " + avgPrice);
        System.out.println("9️⃣ Item sales count: " + itemSales);
        System.out.println("🔟 CSV item names: " + itemCsv);
        System.out.println("1️⃣1️⃣ Expensive items: " + partitionByExpensive);
        System.out.println("1️⃣2️⃣ Avg qty per item: " + avgQtyPerItem);
        System.out.println("1️⃣3️⃣ Items sorted by revenue: " + itemsSortedByRevenue);
        System.out.println("1️⃣4️⃣ Most valuable order: " + mostValuableOrder);
        System.out.println("1️⃣5️⃣ Max item per customer: " + maxItemPerCustomer);
        System.out.println("1️⃣6️⃣ Item revenue: " + itemRevenueMap);
        System.out.println("1️⃣7️⃣ Customers with >5 items: " + customersWithMoreThan5Items);
        System.out.println("1️⃣8️⃣ Item to customers: " + itemToCustomers);
        System.out.println("1️⃣9️⃣ Top 3 items: " + top3Items);
        System.out.println("2️⃣0️⃣ Price histogram: " + priceHistogram);
    }
}


class Level4StreamChallenges21_40 {

    static class Item {
        private final String name;
        private final int quantity;
        private final double price;

        public Item(String name, int quantity, double price) {
            this.name = name;
            this.quantity = quantity;
            this.price = price;
        }

        public String getName() {
            return name;
        }

        public int getQuantity() {
            return quantity;
        }

        public double getPrice() {
            return price;
        }

        public double total() {
            return price * quantity;
        }

        public String toString() {
            return name + ": " + quantity + " @ ₹" + price;
        }
    }

    static class Order {
        private final String orderId;
        private final String customerName;
        private final List<Item> items;

        public Order(String orderId, String customerName, List<Item> items) {
            this.orderId = orderId;
            this.customerName = customerName;
            this.items = items;
        }

        public String getOrderId() {
            return orderId;
        }

        public String getCustomerName() {
            return customerName;
        }

        public List<Item> getItems() {
            return items;
        }

        public double getTotalValue() {
            return items.stream().mapToDouble(Item::total).sum();
        }

        public String toString() {
            return orderId + " by " + customerName;
        }
    }

    public static void main(String[] args) {
        List<Order> orders = List.of(
                new Order("O1", "JP", List.of(
                        new Item("Pen", 2, 10),
                        new Item("Notebook", 1, 100),
                        new Item("Pencil", 5, 5)
                )),
                new Order("O2", "Arun", List.of(
                        new Item("Notebook", 2, 100),
                        new Item("Marker", 1, 50)
                )),
                new Order("O3", "JP", List.of(
                        new Item("Pen", 1, 10),
                        new Item("Laptop", 1, 55000)
                )),
                new Order("O4", "Gopal", List.of(
                        new Item("Laptop", 1, 60000),
                        new Item("Mouse", 2, 500)
                ))
        );
        // 1️⃣8️⃣ For each item, list all customers who bought it
        Map<String, Set<String>> itemToCustomers = orders.stream()
                .flatMap(order -> order.getItems().stream()
                        .map(item -> new AbstractMap.SimpleEntry<>(item.getName(), order.getCustomerName())))
                .collect(Collectors.groupingBy(
                        Map.Entry::getKey,
                        Collectors.mapping(Map.Entry::getValue, Collectors.toSet())
                ));


        // Previous 20 Questions Code Here (Skipped for brevity)

        // 2️⃣1️⃣ Group by customer, count total items bought
        Map<String, Integer> totalItemsPerCustomer = orders.stream()
                .collect(Collectors.groupingBy(
                        Order::getCustomerName,
                        Collectors.summingInt(order -> order.getItems().stream().mapToInt(Item::getQuantity).sum())
                ));

        // 2️⃣2️⃣ List all items bought more than once
        List<String> itemsBoughtMultipleTimes = orders.stream()
                .flatMap(o -> o.getItems().stream())
                .collect(Collectors.groupingBy(Item::getName, Collectors.summingInt(Item::getQuantity)))
                .entrySet().stream()
                .filter(e -> e.getValue() > 1)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        // 2️⃣3️⃣ Group orders by item name
        Map<String, List<Order>> ordersByItem = orders.stream()
                .flatMap(order -> order.getItems().stream()
                        .map(item -> new AbstractMap.SimpleEntry<>(item.getName(), order)))
                .collect(Collectors.groupingBy(
                        Map.Entry::getKey,
                        Collectors.mapping(Map.Entry::getValue, Collectors.toList())
                ));

        // 2️⃣4️⃣ Find item with highest total quantity sold
        String mostSoldItem = orders.stream()
                .flatMap(o -> o.getItems().stream())
                .collect(Collectors.groupingBy(Item::getName, Collectors.summingInt(Item::getQuantity)))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey).orElse(null);

        // 2️⃣5️⃣ Group orders by number of items in them
        Map<Integer, List<Order>> ordersByItemCount = orders.stream()
                .collect(Collectors.groupingBy(order -> order.getItems().size()));

        // 2️⃣6️⃣ Create Map<ItemName, Set<OrderId>>
        Map<String, Set<String>> itemToOrderIds = orders.stream()
                .flatMap(order -> order.getItems().stream()
                        .map(item -> new AbstractMap.SimpleEntry<>(item.getName(), order.getOrderId())))
                .collect(Collectors.groupingBy(Map.Entry::getKey,
                        Collectors.mapping(Map.Entry::getValue, Collectors.toSet())));

        // 2️⃣7️⃣ Top N customers by total spending
        List<String> topCustomers = orders.stream()
                .collect(Collectors.groupingBy(Order::getCustomerName,
                        Collectors.summingDouble(Order::getTotalValue)))
                .entrySet().stream()
                .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
                .limit(2).map(Map.Entry::getKey)
                .collect(Collectors.toList());

        // 2️⃣8️⃣ Find order containing the highest number of items
        Order orderWithMostItems = orders.stream()
                .max(Comparator.comparingInt(o -> o.getItems().size())).orElse(null);

        // 2️⃣9️⃣ Find average number of items per order
        double avgItemsPerOrder = orders.stream()
                .mapToInt(o -> o.getItems().size()).average().orElse(0);

        // 3️⃣0️⃣ Group items by name and find max price for each
        Map<String, Double> maxPricePerItem = orders.stream()
                .flatMap(o -> o.getItems().stream())
                .collect(Collectors.groupingBy(Item::getName,
                        Collectors.mapping(Item::getPrice, Collectors.collectingAndThen(Collectors.maxBy(Double::compare), Optional::get))));

        // 3️⃣1️⃣ Get item names sorted by how many customers bought them
        List<String> itemsByCustomerCount = itemToCustomers.entrySet().stream()
                .sorted((a, b) -> Integer.compare(b.getValue().size(), a.getValue().size()))
                .map(Map.Entry::getKey).collect(Collectors.toList());

        // 3️⃣2️⃣ Create a Map<OrderId, TotalValue>
        Map<String, Double> orderValueMap = orders.stream()
                .collect(Collectors.toMap(Order::getOrderId, Order::getTotalValue));

        // 3️⃣3️⃣ Group items by name, and list quantities (List<Integer>)
        Map<String, List<Integer>> quantitiesPerItem = orders.stream()
                .flatMap(o -> o.getItems().stream())
                .collect(Collectors.groupingBy(Item::getName,
                        Collectors.mapping(Item::getQuantity, Collectors.toList())));

        // 3️⃣4️⃣ List items that appear in more than 1 order
        List<String> popularItems = itemToOrderIds.entrySet().stream()
                .filter(e -> e.getValue().size() > 1)
                .map(Map.Entry::getKey).collect(Collectors.toList());

        // 3️⃣5️⃣ Create Map<Customer, Set<ItemName>)>
        Map<String, Set<String>> customerItemMap = orders.stream()
                .collect(Collectors.groupingBy(Order::getCustomerName,
                        Collectors.flatMapping(order -> order.getItems().stream().map(Item::getName), Collectors.toSet())));

        // 3️⃣6️⃣ Create a Map<String, DoubleSummaryStatistics> per item
        Map<String, DoubleSummaryStatistics> itemStats = orders.stream()
                .flatMap(o -> o.getItems().stream())
                .collect(Collectors.groupingBy(Item::getName,
                        Collectors.summarizingDouble(Item::getPrice)));

        // 3️⃣7️⃣ Count total quantity sold per price point
        Map<Double, Integer> quantityPerPrice = orders.stream()
                .flatMap(o -> o.getItems().stream())
                .collect(Collectors.groupingBy(Item::getPrice,
                        Collectors.summingInt(Item::getQuantity)));

        // 3️⃣8️⃣ Find average spending per customer per order
        Map<String, Double> avgPerOrder = orders.stream()
                .collect(Collectors.groupingBy(Order::getCustomerName,
                        Collectors.averagingDouble(Order::getTotalValue)));
        System.out.println();
        System.out.println("avg per order " + avgPerOrder);
        // 3️⃣9️⃣ Group orders by city (assume customer name implies city)
        Map<String, List<Order>> fakeCityOrders = orders.stream()
                .collect(Collectors.groupingBy(o -> o.getCustomerName().substring(0, 1))); // dummy city logic
        System.out.println();
        System.out.println("Fake city orders :" + fakeCityOrders);
        // 4️⃣0️⃣ Find most expensive item per order
        Map<String, Item> maxItemPerOrder = orders.stream()
                .collect(Collectors.toMap(Order::getOrderId,
                        o -> o.getItems().stream().max(Comparator.comparingDouble(Item::getPrice)).orElse(null)));
        System.out.println();
        System.out.println("Max item per order" + maxItemPerOrder);

        System.out.println("✅ Remaining Level 4 questions (21-40) executed!");

    }


}
