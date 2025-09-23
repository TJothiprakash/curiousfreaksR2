package practicesessions.sept_17.file_handling;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class ChunkMergerDemo {

    public static void main(String[] args) throws IOException {
        // === Step 0: Setup directories ===
        Path inputDir = Paths.get("data/incoming/upload-42");
        Path mergedDir = Paths.get("data/merged");
        Files.createDirectories(inputDir);
        Files.createDirectories(mergedDir);

        // === Step 1: Create sample chunk files ===
        createSampleChunk(inputDir.resolve("part-0001.bin"), "Hello");
        createSampleChunk(inputDir.resolve("part-0002.bin"), " World!!");
        createSampleChunk(inputDir.resolve("part-0003.bin"), "Bye.");

        // === Step 2: Create manifest.json ===
        String manifestContent = """
        {
          "uploadId": "upload-42",
          "order": [
            {"name": "part-0001.bin", "size": 5},
            {"name": "part-0002.bin", "size": 7},
            {"name": "part-0003.bin", "size": 4}
          ]
        }
        """;
        Path manifestPath = inputDir.resolve("manifest.json");
        Files.writeString(manifestPath, manifestContent);

        // === Step 3: Read manifest and prepare chunks ===
        List<ChunkInfo> chunks = new ArrayList<>();
        try (Scanner sc = new Scanner(manifestPath)) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine().trim();
                if (line.startsWith("\"name\"")) {
                    String name = line.split(":")[1].replace("\"", "").replace(",", "").trim();
                    String sizeLine = sc.nextLine().trim();
                    long size = Long.parseLong(sizeLine.split(":")[1].replace(",", "").trim());
                    chunks.add(new ChunkInfo(name, size));
                }
            }
        }

        // === Step 4: Merge chunks with streaming I/O ===
        Path mergedFile = mergedDir.resolve("upload-42.bin");
        Path indexFile = mergedDir.resolve("upload-42.index.json");
        Path errorFile = mergedDir.resolve("upload-42.error.json");
        List<IndexEntry> indexList = new ArrayList<>();
        long currentOffset = 0;
        final int BUFFER_SIZE = 8192;

        try (OutputStream out = Files.newOutputStream(mergedFile, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)) {
            for (ChunkInfo chunk : chunks) {
                Path chunkPath = inputDir.resolve(chunk.name);
                if (!Files.exists(chunkPath)) {
                    writeError(errorFile, "Missing chunk: " + chunk.name);
                    Files.deleteIfExists(mergedFile);
                    return;
                }

                long bytesWritten = 0;
                try (InputStream in = Files.newInputStream(chunkPath)) {
                    byte[] buffer = new byte[BUFFER_SIZE];
                    int read;
                    while ((read = in.read(buffer)) != -1) {
                        out.write(buffer, 0, read);
                        bytesWritten += read;
                    }
                }

                if (bytesWritten != chunk.size) {
                    writeError(errorFile, "Size mismatch for " + chunk.name + ". Expected: " + chunk.size + ", Actual: " + bytesWritten);
                    Files.deleteIfExists(mergedFile);
                    return;
                }

                indexList.add(new IndexEntry(chunk.name, currentOffset, currentOffset + bytesWritten - 1));
                currentOffset += bytesWritten;
            }
        }

        // === Step 5: Write index JSON ===
        try (BufferedWriter writer = Files.newBufferedWriter(indexFile)) {
            writer.write("[\n");
            for (int i = 0; i < indexList.size(); i++) {
                IndexEntry e = indexList.get(i);
                writer.write(String.format("  {\"name\":\"%s\",\"start\":%d,\"end\":%d}", e.name, e.start, e.end));
                if (i < indexList.size() - 1) writer.write(",");
                writer.write("\n");
            }
            writer.write("]\n");
        }

        System.out.println("Merge completed successfully. Merged file length: " + currentOffset + " bytes.");
    }

    static void createSampleChunk(Path path, String content) throws IOException {
        Files.write(path, content.getBytes());
    }

    static void writeError(Path errorFile, String message) {
        try (BufferedWriter writer = Files.newBufferedWriter(errorFile)) {
            writer.write("{\"error\":\"" + message + "\"}\n");
            System.err.println("Error: " + message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class ChunkInfo {
        String name;
        long size;
        ChunkInfo(String name, long size) { this.name = name; this.size = size; }
    }

    static class IndexEntry {
        String name;
        long start;
        long end;
        IndexEntry(String name, long start, long end) { this.name = name; this.start = start; this.end = end; }
    }
}
