package file_handling.syncdirectories;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class DirectorySync {

    /**
     * Intuition:
     * - Walk the source directory tree recursively.
     * - For each file:
     *     - Check if it exists in the destination.
     *     - If missing or changed (size/last-modified differs), copy it.
     *     - Preserve attributes like last-modified.
     *     - Optionally replace atomically.
     */

    public static void syncDirectories(Path srcDir, Path destDir) throws IOException {
        if (!Files.exists(srcDir) || !Files.isDirectory(srcDir)) {
            throw new IllegalArgumentException("Source directory does not exist or is not a directory");
        }
        if (!Files.exists(destDir)) {
            Files.createDirectories(destDir);
        }

        Files.walk(srcDir).forEach(srcPath -> {
            try {
                Path relative = srcDir.relativize(srcPath);
                Path destPath = destDir.resolve(relative);

                if (Files.isDirectory(srcPath)) {
                    if (!Files.exists(destPath)) {
                        Files.createDirectories(destPath);
                        System.out.println("Created directory: " + destPath);
                    }
                } else if (Files.isRegularFile(srcPath)) {
                    boolean copyRequired = true;

                    if (Files.exists(destPath) && Files.isRegularFile(destPath)) {
                        // Compare size and last modified
                        long srcSize = Files.size(srcPath);
                        long destSize = Files.size(destPath);
                        long srcMod = Files.getLastModifiedTime(srcPath).toMillis();
                        long destMod = Files.getLastModifiedTime(destPath).toMillis();

                        copyRequired = !(srcSize == destSize && srcMod == destMod);
                    }

                    if (copyRequired) {
                        // Use atomic move via temp file in destination folder
                        Path tempFile = destPath.resolveSibling(destPath.getFileName() + ".tmp");
                        Files.copy(srcPath, tempFile, StandardCopyOption.COPY_ATTRIBUTES, StandardCopyOption.REPLACE_EXISTING);
                        Files.move(tempFile, destPath, StandardCopyOption.ATOMIC_MOVE, StandardCopyOption.REPLACE_EXISTING);
                        System.out.println("Copied/Updated: " + destPath);
                    }
                }
            } catch (IOException e) {
                System.err.println("Failed to process: " + srcPath + " -> " + e.getMessage());
            }
        });
    }

    // Dry Run / Example
    public static void main(String[] args) throws IOException {
        if (args.length != 2) {
            System.err.println("Usage: java DirectorySync <sourceDir> <destinationDir>");
            System.exit(1);
        }

        Path src = Paths.get(args[0]);
        Path dest = Paths.get(args[1]);

        long startTime = System.nanoTime();
        syncDirectories(src, dest);
        long endTime = System.nanoTime();

        System.out.println("Directory sync completed in " + ((endTime - startTime) / 1_000_000) + " ms");
    }
}
