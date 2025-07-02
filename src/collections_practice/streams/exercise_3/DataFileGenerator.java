package collections_practice.streams.exercise_3;
import java.io.*;
import java.nio.file.*;
import java.util.*;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataFileGenerator {

    public static void main(String[] args) throws IOException {
        generateOrdersJson();
        generateTransactionsJson();
        generateLogsTxt();
        generateDataCsv();
        generateWarehouseCsv();
        generatePropertiesFile();

        System.out.println("✅ All files generated successfully.");
    }

    private static void generateOrdersJson() throws IOException {
        List<Map<String, Object>> orders = new ArrayList<>();
        orders.add(Map.of("orderId", "O1", "customer", "Alice", "items", List.of("iPhone", "AirPods"), "total", 1200.0, "returned", false, "date", "2025-06-29"));
        orders.add(Map.of("orderId", "O2", "customer", "Bob", "items", List.of("Mouse", "Keyboard"), "total", 300.0, "returned", true, "date", "2025-06-28"));
        orders.add(Map.of("orderId", "O3", "customer", "Alice", "items", List.of("Monitor"), "total", 700.0, "returned", false, "date", "2025-06-28"));

        ObjectMapper mapper = new ObjectMapper();
        mapper.writerWithDefaultPrettyPrinter().writeValue(Path.of("orders.json").toFile(), orders);
    }

    private static void generateTransactionsJson() throws IOException {
        List<Map<String, Object>> txns = new ArrayList<>();
        txns.add(Map.of("user", "Alice", "type", "WITHDRAW", "location", "Delhi", "amount", 3000.0, "date", "2025-06-29", "success", true, "currency", "INR"));
        txns.add(Map.of("user", "Bob", "type", "WITHDRAW", "location", "Mumbai", "amount", 4500.0, "date", "2025-06-29", "success", false, "currency", "INR"));
        txns.add(Map.of("user", "Charlie", "type", "DEPOSIT", "location", "Bangalore", "amount", 6000.0, "date", "2025-06-28", "success", true, "currency", "USD"));

        ObjectMapper mapper = new ObjectMapper();
        mapper.writerWithDefaultPrettyPrinter().writeValue(Path.of("transactions.json").toFile(), txns);
    }

    private static void generateLogsTxt() throws IOException {
        List<String> logs = List.of(
                "2025-06-29T10:00:00\tINFO\tapi-service\tAPI_CALL /products latency=120\t192.168.0.1",
                "2025-06-29T10:02:00\tERROR\tauth-service\tALERT Unauthorized access\t192.168.0.2",
                "2025-06-29T10:03:00\tINFO\tapi-service\tLOGIN\t192.168.0.1",
                "2025-06-29T10:04:00\tINFO\tapi-service\tMSG #general Hello world\t192.168.0.3",
                "2025-06-29T10:05:00\tERROR\tapi-service\tREVIEW iPhone\t192.168.0.4"
        );
        Files.write(Path.of("logs.txt"), logs);
    }

    private static void generateDataCsv() throws IOException {
        List<String> lines = new ArrayList<>();
        lines.add("userId,timestamp,page,amount,status");
        lines.add("Alice,2025-06-29T10:01:00,/home,1200.0,abandoned");
        lines.add("Bob,2025-06-29T10:02:00,/checkout,1300.0,abandoned");
        lines.add("Charlie,2025-06-29T10:03:00,/login,0.0,completed");
        Files.write(Path.of("data.csv"), lines);
    }

    private static void generateWarehouseCsv() throws IOException {
        List<String> lines = new ArrayList<>();
        lines.add("item,weight,quantity,supplier,warehouse,price");
        lines.add("iPhone,0.2,8,Apple,W1,999.0");
        lines.add("AirPods,0.1,20,Apple,W1,199.0");
        lines.add("Keyboard,0.7,5,Logitech,W2,49.0");
        lines.add("Mouse,0.4,3,Logitech,W2,29.0");
        lines.add("Monitor,3.5,2,Dell,W3,249.0");
        Files.write(Path.of("warehouse.csv"), lines);
    }

    private static void generatePropertiesFile() throws IOException {
        Properties props = new Properties();
        props.setProperty("server.mode", "production");
        props.setProperty("feature.cart.tracking", "enabled");
        try (Writer writer = Files.newBufferedWriter(Path.of("config.properties"))) {
            props.store(writer, "System Config");
        }
    }
}

