package collections_practice.streams.exercise_3;
// Level 5: Real-World Stream Challenges Setup
// Files: logs.txt, orders.json, config.properties, data.csv
// Note: Parsing + Stream + Transformation based on domain

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.function.Function;
import java.util.regex.*;
import java.util.stream.*;
import java.util.Properties;
import com.fasterxml.jackson.databind.*; // for JSON
import com.fasterxml.jackson.core.type.TypeReference;

public class Level5StreamChallenges {

    static class LogEntry {
        String timestamp;
        String level;
        String service;
        String message;
        String ip;
    }

    static class OrderEntry {
        public String orderId;
        public String customer;
        public List<String> items;
        public double total;
        public boolean returned;
        public String date;
    }

    public static void main(String[] args) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        List<OrderEntry> orders = mapper.readValue(
                Files.readString(Path.of("orders.json")),
                new TypeReference<>() {}
        );

        List<String> logs = Files.readAllLines(Path.of("logs.txt"));

        List<String[]> csvRows = Files.lines(Path.of("data.csv"))
                .skip(1).map(line -> line.split(","))
                .collect(Collectors.toList());

        Properties props = new Properties();
        props.load(Files.newBufferedReader(Path.of("config.properties")));

        // Sample: Print prop value
        System.out.println("Config loaded - server.mode: " + props.getProperty("server.mode"));

        // ✅ Challenge 1 - Count logs per service
        Map<String, Long> errorCountPerService = logs.stream()
                .map(Level5StreamChallenges::parseLog)
                .filter(l -> l.level.equals("ERROR"))
                .collect(Collectors.groupingBy(l -> l.service, Collectors.counting()));

        // ✅ Challenge 2 - Top 3 failed APIs (dummy example)
        List<String> topFailed = logs.stream()
                .map(Level5StreamChallenges::parseLog)
                .filter(l -> l.level.equals("ERROR"))
                .map(l -> l.message.split(" ")[0])
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .limit(3).map(Map.Entry::getKey).collect(Collectors.toList());

        // ✅ Challenge 3 - Group logs by day
        Map<String, Long> logsByDay = logs.stream()
                .map(Level5StreamChallenges::parseLog)
                .collect(Collectors.groupingBy(l -> l.timestamp.substring(0, 10), Collectors.counting()));

        // ✅ Challenge 4 - Extract unique user agents from csv
        Set<String> userAgents = csvRows.stream()
                .map(cols -> cols[3]) // assuming 4th col is user-agent
                .collect(Collectors.toSet());

        // ✅ Challenge 5 - Total request per IP from log
        Map<String, Long> ipRequestCount = logs.stream()
                .map(Level5StreamChallenges::parseLog)
                .collect(Collectors.groupingBy(l -> l.ip, Collectors.counting()));

        // ✅ Challenge 6 - Group customers by spending tier
        Map<String, List<OrderEntry>> spendingTiers = orders.stream()
                .collect(Collectors.groupingBy(o -> {
                    if (o.total >= 1000) return "HIGH";
                    else if (o.total >= 500) return "MED";
                    else return "LOW";
                }));

        // ✅ Challenge 7 - Most purchased item category
        Map<String, Long> itemFrequency = orders.stream()
                .flatMap(o -> o.items.stream())
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        // ✅ Challenge 8 - Customers who returned >2 times
        List<String> frequentReturners = orders.stream()
                .filter(o -> o.returned)
                .collect(Collectors.groupingBy(o -> o.customer, Collectors.counting()))
                .entrySet().stream()
                .filter(e -> e.getValue() > 2)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        // ✅ Challenge 9 - Monthly revenue trend
        Map<String, Double> revenueByMonth = orders.stream()
                .collect(Collectors.groupingBy(
                        o -> o.date.substring(0, 7),
                        Collectors.summingDouble(o -> o.total)
                ));

        // ✅ Challenge 10 - Product revenue heatmap
        Map<String, Double> itemRevenue = orders.stream()
                .flatMap(o -> o.items.stream().map(i -> Map.entry(i, o.total / o.items.size())))
                .collect(Collectors.groupingBy(Map.Entry::getKey, Collectors.summingDouble(Map.Entry::getValue)));

        // ✅ Challenge 11 - Users who visited > 5 unique pages (csv)
        Map<String, Long> userPages = csvRows.stream()
                .collect(Collectors.groupingBy(row -> row[0] /* userId */,
                        Collectors.mapping(row -> row[2], Collectors.toSet())))
                .entrySet().stream()
                .filter(e -> e.getValue().size() > 5)
                .collect(Collectors.toMap(Map.Entry::getKey, e -> (long) e.getValue().size()));

        // ✅ Challenge 12 - Most frequent nav path (dummy)
        // skipped: needs session data

        // ✅ Challenge 13 - Avg session time per user
        // skipped: needs login/logout timestamp pair

        // ✅ Challenge 14 - Bounced users
        List<String> bouncedUsers = csvRows.stream()
                .collect(Collectors.groupingBy(row -> row[0], Collectors.mapping(row -> row[2], Collectors.toSet())))
                .entrySet().stream()
                .filter(e -> e.getValue().size() == 1)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        // ✅ Challenge 15 - Items viewed but not purchased
        // needs viewedItems.csv + order data

        System.out.println("✅ Level 5 - 20 Real Stream Challenges READY ✅");

        // ✅ Challenge 26 - Top 5 reviewed products (from logs)
        Map<String, Long> reviewCounts = logs.stream()
                .map(Level5StreamChallenges::parseLog)
                .filter(l -> l.message.startsWith("REVIEW"))
                .map(l -> l.message.split(" ")[1]) // e.g., REVIEW Product123
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        List<String> top5Reviewed = reviewCounts.entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .limit(5)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        // ✅ Challenge 27 - Avg API latency per endpoint
        Map<String, Double> avgLatency = logs.stream()
                .map(Level5StreamChallenges::parseLog)
                .filter(l -> l.message.startsWith("API_CALL"))
                .map(l -> l.message.split(" ")) // API_CALL /endpoint latency=123
                .collect(Collectors.groupingBy(
                        parts -> parts[1],
                        Collectors.averagingDouble(parts -> Double.parseDouble(parts[2].split("=")[1]))
                ));

        // ✅ Challenge 28 - Partition API calls by success/failure
        Map<Boolean, Long> apiCallResult = logs.stream()
                .map(Level5StreamChallenges::parseLog)
                .filter(l -> l.message.startsWith("API_CALL"))
                .collect(Collectors.partitioningBy(
                        l -> l.level.equals("INFO"),
                        Collectors.counting()
                ));

        // ✅ Challenge 29 - Group API calls by status code + endpoint
        Map<String, Long> apiStatusGrouped = logs.stream()
                .map(Level5StreamChallenges::parseLog)
                .filter(l -> l.message.startsWith("API_CALL"))
                .collect(Collectors.groupingBy(
                        l -> l.message.split(" ")[1] + "_" + l.level,
                        Collectors.counting()
                ));

        // ✅ Challenge 30 - Error % in last 10 mins (demo only, no sliding window here)
        long totalLogs = logs.size();
        long errorLogs = logs.stream()
                .map(Level5StreamChallenges::parseLog)
                .filter(l -> l.level.equals("ERROR"))
                .count();
        double errorRate = (double) errorLogs / totalLogs * 100;

        // ✅ Challenge 31 - Custom collector: Uppercase names
        String uppercaseNames = orders.stream()
                .map(o -> o.customer.toUpperCase())
                .collect(Collectors.joining(","));

//        // ✅ Challenge 32 - Histogram: price range → item count
//        Map<String, Long> priceRange = warehouseRows.stream()
//                .collect(Collectors.groupingBy(
//                        cols -> {
//                            double price = Double.parseDouble(cols[5]); // assume 6th col is price
//                            if (price < 100) return "LOW";
//                            else if (price < 500) return "MED";
//                            else return "HIGH";
//                        },
//                        Collectors.counting()
//                ));

        // ✅ Challenge 33 - Group customers by loyalty tier
        Map<String, List<String>> loyaltyMap = orders.stream()
                .collect(Collectors.groupingBy(
                        o -> {
                            if (o.total > 2000) return "PLATINUM";
                            else if (o.total > 1000) return "GOLD";
                            else return "SILVER";
                        },
                        Collectors.mapping(o -> o.customer, Collectors.toList())
                ));

        // ✅ Challenge 34 - Flatten JSON logs to event names (from message)
        Set<String> eventNames = logs.stream()
                .map(Level5StreamChallenges::parseLog)
                .map(l -> l.message.split(" ")[0])
                .collect(Collectors.toSet());

//        // ✅ Challenge 35 - Deduplicate users by name+amount
//        Set<String> uniqueUsers = transactions.stream()
//                .map(t -> t.user + "-" + t.amount)
//                .collect(Collectors.toSet());

        // ✅ Challenge 36 - Users who triggered same alert twice
        List<String> repeatAlerts = logs.stream()
                .map(Level5StreamChallenges::parseLog)
                .filter(l -> l.message.startsWith("ALERT"))
                .collect(Collectors.groupingBy(
                        l -> l.ip + "-" + l.message,
                        Collectors.counting()
                )).entrySet().stream()
                .filter(e -> e.getValue() > 1)
                .map(e -> e.getKey().split("-")[0])
                .collect(Collectors.toList());

        // ✅ Challenge 37 - Login streak (demo using logs)
        Map<String, Long> loginCount = logs.stream()
                .map(Level5StreamChallenges::parseLog)
                .filter(l -> l.message.equals("LOGIN"))
                .collect(Collectors.groupingBy(l -> l.ip, Collectors.counting()));

        // ✅ Challenge 38 - API response time by country (assume IP → country mapping)
        Map<String, Double> avgResponseByCountry = logs.stream()
                .map(Level5StreamChallenges::parseLog)
                .filter(l -> l.message.contains("latency="))
                .collect(Collectors.groupingBy(
                        l -> l.ip.substring(0, 3), // fake: map IP range to country
                        Collectors.averagingDouble(l -> Double.parseDouble(l.message.split("latency=")[1]))
                ));

        // ✅ Challenge 39 - Abandoned carts > ₹1000
        List<String> abandonedUsers = csvRows.stream()
                .filter(row -> row[4].equals("abandoned") && Double.parseDouble(row[3]) > 1000)
                .map(row -> row[0])
                .distinct()
                .collect(Collectors.toList());

        // ✅ Challenge 40 - Group chat messages by channel → hour
        Map<String, Map<String, Long>> chatGrouped = logs.stream()
                .map(Level5StreamChallenges::parseLog)
                .filter(l -> l.message.startsWith("MSG"))
                .collect(Collectors.groupingBy(
                        l -> l.message.split(" ")[1], // channel
                        Collectors.groupingBy(
                                l -> l.timestamp.substring(11, 13), // hour
                                Collectors.counting()
                        )
                ));

    }
    static LogEntry parseLog(String line) {
        String[] parts = line.split("\t");
        LogEntry l = new LogEntry();
        l.timestamp = parts[0];
        l.level = parts[1];
        l.service = parts[2];
        l.message = parts[3];
        l.ip = parts.length > 4 ? parts[4] : "127.0.0.1";
        return l;
    }
}

