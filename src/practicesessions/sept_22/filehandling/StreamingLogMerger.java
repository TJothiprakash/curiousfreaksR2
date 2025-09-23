package practicesessions.sept_22.filehandling;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.time.Instant;
import java.util.Comparator;
import java.util.stream.*;
import java.util.zip.GZIPOutputStream;

public class StreamingLogMerger {

    /**
     * Merge multiple log files into a single gzipped file,
     * deduplicated and sorted by timestamp.
     *
     * @param logDir  Directory containing *.log files
     * @param targetGz Path to output gzipped file
     * @throws IOException if file operations fail
     */
    public static void mergeLogs(Path logDir, Path targetGz) throws IOException {
        // Ensure parent directory exists
        Path parent = targetGz.getParent();
        if (parent != null) Files.createDirectories(parent);

        // Discover all .log files
        try (Stream<Path> logFiles = Files.walk(logDir).filter(p -> p.toString().endsWith(".log"))) {

            // Flatten all lines from all files into a single stream
            Stream<String> mergedLines = logFiles
                    .flatMap(path -> {
                        try {
                            return Files.lines(path, StandardCharsets.UTF_8);
                        } catch (IOException e) {
                            throw new UncheckedIOException(e);
                        }
                    })
                    .distinct() // deduplicate
                    .sorted(Comparator.comparing(line -> Instant.parse(line.split(" ")[0])));

            // Create temp file in same directory for atomic write
            Path tempFile = Files.createTempFile(parent, "merged-", ".tmp.gz");

            try (OutputStream os = new GZIPOutputStream(Files.newOutputStream(tempFile,
                    StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING))) {

                mergedLines.forEach(line -> {
                    try {
                        os.write(line.getBytes(StandardCharsets.UTF_8));
                        os.write('\n');
                    } catch (IOException e) {
                        throw new UncheckedIOException(e);
                    }
                });

                os.flush();
            }

            // Atomically move temp file to final target
            Files.move(tempFile, targetGz, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Merged gzipped log created at: " + targetGz);
        }
    }

    public static void main(String[] args) throws IOException {
        Path logDir = Paths.get("logs");                     // directory with app-1.log, app-2.log...
        Path targetGz = Paths.get("logs/merged.log.gz");    // output gzipped log

        mergeLogs(logDir, targetGz);

        // Optional: verify contents (for demo only, decompress in memory)
        try (var gzIn = new java.util.zip.GZIPInputStream(Files.newInputStream(targetGz));
             var reader = new java.io.BufferedReader(new java.io.InputStreamReader(gzIn, StandardCharsets.UTF_8))) {

            reader.lines().forEach(System.out::println);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
