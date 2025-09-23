package practicesessions.sept_23.filehandling;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.*;
import java.util.HashSet;

public class CSVDeDupWriter {

    /**
     * Task:
     * Read a CSV file line by line, split first occurrences vs duplicates by email.
     * Write valid.csv (first occurrence) and duplicates.csv (subsequent occurrences).
     * Use NIO.2, buffered reader/writer, atomic replace via temp files.
     */
    public static void splitCSVByEmail(Path inputCsv, Path validCsv, Path duplicatesCsv) throws IOException {
        Path tempValid = validCsv.resolveSibling(validCsv.getFileName() + ".tmp");
        Path tempDuplicates = duplicatesCsv.resolveSibling(duplicatesCsv.getFileName() + ".tmp");

        HashSet<String> seenEmails = new HashSet<>();

        try (BufferedReader reader = Files.newBufferedReader(inputCsv);
             BufferedWriter validWriter = Files.newBufferedWriter(tempValid);
             BufferedWriter duplicateWriter = Files.newBufferedWriter(tempDuplicates)) {

            String header = reader.readLine();
            if (header == null) {
                throw new IOException("Empty CSV file: " + inputCsv);
            }

            // Write header to both output files
            validWriter.write(header);
            validWriter.newLine();
            duplicateWriter.write(header);
            duplicateWriter.newLine();

            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",", -1);
                if (parts.length < 3) {
                    // Skip or throw error for malformed line
                    throw new IOException("Malformed CSV line: " + line);
                }
                String email = parts[2].trim();
                if (seenEmails.add(email)) {
                    validWriter.write(line);
                    validWriter.newLine();
                } else {
                    duplicateWriter.write(line);
                    duplicateWriter.newLine();
                }
            }
        } catch (IOException e) {
            // Clean up temp files on error
            Files.deleteIfExists(tempValid);
            Files.deleteIfExists(tempDuplicates);
            throw new IOException("Error processing CSV: " + e.getMessage(), e);
        }

        // Atomic replace of target files
        Files.move(tempValid, validCsv, StandardCopyOption.REPLACE_EXISTING, StandardCopyOption.ATOMIC_MOVE);
        Files.move(tempDuplicates, duplicatesCsv, StandardCopyOption.REPLACE_EXISTING, StandardCopyOption.ATOMIC_MOVE);
    }

    public static void main(String[] args) {
        Path inputCsv = Paths.get("E:\\java_projects\\daily\\daily_practice\\src\\main\\java\\com\\jp\\sept_23\\filehandling\\users.csv");
        Path validCsv = Paths.get("valid.csv");
        Path duplicatesCsv = Paths.get("duplicates.csv");

        try {
            splitCSVByEmail(inputCsv, validCsv, duplicatesCsv);
            System.out.println("CSV split successfully.");
        } catch (IOException e) {
            System.err.println("Failed to split CSV: " + e.getMessage());
        }
    }
}
