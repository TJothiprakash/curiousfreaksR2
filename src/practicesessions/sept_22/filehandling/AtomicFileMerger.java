package practicesessions.sept_22.filehandling;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class AtomicFileMerger {

    /**
     * Merges multiple small files into a single target file atomically.
     *
     * @param target Path to the final merged file
     * @param parts  List of Paths to parts to merge
     * @throws IOException if file operations fail
     */
    public static void mergeParts(Path target, List<Path> parts) throws IOException {
        // Ensure parent directory exists
        Path parent = target.getParent();
        System.out.println("parent = " + parent);
        System.out.println("parent = " + parent.getRoot());
        if (parent != null) {
            Files.createDirectories(parent);

        }

        // Create a temporary file in the same directory
        Path temp = Files.createTempFile(parent, "merge-", ".tmp");

        try {
            // Open temp file for writing (create/truncate)
            try (var out = Files.newOutputStream(temp,
                    StandardOpenOption.CREATE,
                    StandardOpenOption.WRITE,
                    StandardOpenOption.APPEND)) {

                long expectedLength = 0;

                for (Path part : parts) {
                    byte[] data = Files.readAllBytes(part);
                    out.write(data);
                    expectedLength += data.length;
                }

                out.flush();
                out.close();

                // Verify length
                long actualLength = Files.size(temp);
                if (actualLength != expectedLength) {
                    throw new IOException("Merged file size mismatch: expected "
                            + expectedLength + " but got " + actualLength);
                }
            }

            // Attempt atomic move; fallback to REPLACE_EXISTING if ATOMIC_MOVE not supported
            try {
                Files.move(temp, target, StandardCopyOption.ATOMIC_MOVE, StandardCopyOption.REPLACE_EXISTING);
            } catch (AtomicMoveNotSupportedException e) {
                Files.move(temp, target, StandardCopyOption.REPLACE_EXISTING);
            }

            System.out.println("Merged file created at: " + target + " (" + Files.size(target) + " bytes)");

        } finally {
            // Cleanup temp if still exists
            if (Files.exists(temp)) {
                Files.deleteIfExists(temp);
            }
        }
    }

    // Demo / test
    public static void main(String[] args) throws IOException {
        Path dir = Paths.get("/tmp/demo");
        Files.createDirectories(dir);

        Path p1 = dir.resolve("p1.txt");
        Path p2 = dir.resolve("p2.txt");
        Path p3 = dir.resolve("p3.txt");

        Files.writeString(p1, "Hel");
        Files.writeString(p2, "lo ");
        Files.writeString(p3, "JP");

        Path target = dir.resolve("merged.txt");
        mergeParts(target, List.of(p1, p2, p3));

        // Verify contents
        byte[] merged = Files.readAllBytes(target);
        System.out.println("Merged content: \"" + new String(merged) + "\"");
        System.out.println("Merged size: " + Files.size(target) + " bytes");
        System.out.println(target.toAbsolutePath());
    }
}
