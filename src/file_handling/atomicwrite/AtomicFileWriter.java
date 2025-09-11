package file_handling.atomicwrite;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.*;
import java.util.Objects;

public class AtomicFileWriter {

    /**
     * Writes data to a file safely using temp-then-atomic-move strategy.
     * Guarantees that either the full file is written or nothing is replaced,
     * preventing partial writes in case of crashes.
     *
     * @param targetPath Path to the final file
     * @param data       Data to write
     * @throws IOException If writing fails
     */
    public static void writeAtomically(Path targetPath, byte[] data) throws IOException {
        Objects.requireNonNull(targetPath, "targetPath cannot be null");
        Objects.requireNonNull(data, "data cannot be null");

        Path parentDir = targetPath.getParent();
        if (parentDir == null) {
            parentDir = Paths.get("."); // current directory
        }

        // Create temp file in the same directory
        Path tempFile = Files.createTempFile(parentDir, targetPath.getFileName().toString(), ".tmp");

        boolean success = false;
        try (FileChannel channel = FileChannel.open(tempFile, StandardOpenOption.WRITE)) {
            // Write all data
            channel.write(ByteBuffer.wrap(data));

            // Force flush to disk (fsync)
            channel.force(true);

            // Atomic move to final target
            Files.move(tempFile, targetPath, StandardCopyOption.ATOMIC_MOVE, StandardCopyOption.REPLACE_EXISTING);
            success = true;
        } finally {
            if (!success) {
                try {
                    Files.deleteIfExists(tempFile);
                } catch (IOException e) {
                    System.err.println("Failed to delete temp file: " + tempFile + " -> " + e.getMessage());
                }
            }
        }
    }

    // Dry-run example
    public static void main(String[] args) throws IOException {
        Path target = Paths.get("test_output.txt");
        System.out.println("Target path: " + target);
        String content = "Hello JP! This is written atomically.";
        writeAtomically(target, content.getBytes());
        System.out.println("File written atomically to " + target.toAbsolutePath());
    }
}

