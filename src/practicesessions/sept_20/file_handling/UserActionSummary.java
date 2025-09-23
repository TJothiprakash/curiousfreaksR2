package practicesessions.sept_20.file_handling;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UserActionSummary {

    /*
     * Problem:
     * Summarize per-user action counts from a semi-structured log file
     * and print them sorted by descending count.
     *
     * Intuition:
     * - Use Files.lines(Path) to read lines lazily.
     * - Filter out blank or malformed lines.
     * - Split lines by comma, extract userId.
     * - Count actions per user using Collectors.groupingBy + counting().
     * - Sort by count descending and print.
     *
     * Dry Run Example:
     * Input lines:
     * u1,LOGIN,2025-09-20T04:01:00Z
     * u2,UPLOAD,2025-09-20T04:02:00Z
     * u1,DOWNLOAD,2025-09-20T04:03:00Z
     * u3,LOGIN,2025-09-20T04:04:00Z
     * (blank line)
     *
     * Step 1: Filter blank lines -> ["u1,...", "u2,...", "u1,...", "u3,..."]
     * Step 2: Map to userId -> ["u1", "u2", "u1", "u3"]
     * Step 3: Count occurrences -> {u1=2, u2=1, u3=1}
     * Step 4: Sort descending by count -> u1=2, u2=1, u3=1
     */

    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Usage: java UserActionSummary <logfile>");
            System.exit(1);
        }

        Path logFile = Paths.get(args[0]);

        try (Stream<String> lines = Files.lines(logFile)) {
            Map<String, Long> userCounts = lines
                    .map(String::trim)
                    .filter(line -> !line.isEmpty())          // skip blank lines
                    .map(line -> line.split(",", 3))          // split into [userId,action,timestamp]
                    .filter(parts -> parts.length == 3)       // skip malformed lines
                    .map(parts -> parts[0])                   // extract userId
                    .collect(Collectors.groupingBy(
                            userId -> userId,
                            Collectors.counting()
                    ));

            userCounts.entrySet().stream()
                    .sorted((e1, e2) -> Long.compare(e2.getValue(), e1.getValue())) // descending count
                    .forEach(entry -> System.out.println(entry.getKey() + "=" + entry.getValue()));

        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }
}
