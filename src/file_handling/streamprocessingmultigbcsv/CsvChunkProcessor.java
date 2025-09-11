package file_handling.streamprocessingmultigbcsv;
import java.io.*;
import java.util.*;

public class CsvChunkProcessor {

    private static final int BUFFER_SIZE = 50 * 1024 * 1024; // 50MB

    public static void main(String[] args) throws IOException {
        String inputFile = "C:\\Users\\DELL\\Desktop\\New folder\\bigfile.csv";
        String outputFile = "C:\\Users\\DELL\\Desktop\\New folder\\category_totals.csv";
        long startTime = System.nanoTime();
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile), BUFFER_SIZE);
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile), BUFFER_SIZE)) {

            Map<String, Integer> chunkTotals = new HashMap<>();
            String line;

            // Skip header if needed
            line = reader.readLine();

            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields.length < 3) continue;

                String category = fields[1];
                int quantity = Integer.parseInt(fields[2]);

                chunkTotals.merge(category, quantity, Integer::sum);

                // Optional: flush periodically if map grows too big
                if (chunkTotals.size() > 100_000) {
                    flushChunk(chunkTotals, writer);
                    chunkTotals.clear();
                }
            }

            // Flush any remaining data
            flushChunk(chunkTotals, writer);

            long endTime = System.nanoTime();
            long totalTime = endTime - startTime;
            System.out.println("Time in nanoseconds: " + totalTime);
            System.out.println("Time in microseconds: " + totalTime / 1_000);
            System.out.println("Time in milliseconds: " + totalTime / 1_000_000);
            System.out.println("Time in seconds: " + (totalTime / 1_000_000_000.0));;
        }
    }

    private static void flushChunk(Map<String, Integer> chunkTotals, BufferedWriter writer) throws IOException {
        for (Map.Entry<String, Integer> entry : chunkTotals.entrySet()) {
            writer.write(entry.getKey() + "," + entry.getValue());
            writer.newLine();
        }
        writer.flush(); // ensure buffered data is written
    }
}
