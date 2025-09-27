package practicesessions.sept_27.filehandling;

import com.google.gson.Gson;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class ChunkIndexer {

    private static final int CHUNK_SIZE = 1024 * 1024; // 1 MB
    private static final Path CHECKPOINT_FILE = Paths.get("checkpoint.txt");
    private static final Path OUTPUT_FILE = Paths.get("chunk_hashes.ndjson");

    public static void main(String[] args) throws Exception {
        if (args.length < 1) {
            System.out.println("Usage: java ChunkIndexer <input_file>");
            return;
        }

        Path inputFile = Paths.get(args[0]);
        int startChunk = loadCheckpoint();
        System.out.println("Resuming from chunk " + startChunk);

        try (RandomAccessFile raf = new RandomAccessFile(inputFile.toFile(), "r");
             FileChannel channel = raf.getChannel();
             BufferedWriter writer = Files.newBufferedWriter(OUTPUT_FILE, StandardOpenOption.CREATE, StandardOpenOption.APPEND)) {

            long fileSize = channel.size();
            int chunkIndex = 0;
            Gson gson = new Gson();

            ByteBuffer buffer = ByteBuffer.allocate(CHUNK_SIZE);

            while (channel.read(buffer) > 0) {
                buffer.flip();

                if (chunkIndex < startChunk) {
                    // skip already processed chunks
                    buffer.clear();
                    chunkIndex++;
                    continue;
                }

                // calculate SHA-256 for this chunk
                byte[] chunkData = new byte[buffer.remaining()];
                buffer.get(chunkData);

                String sha256 = sha256Hex(chunkData);

                // build JSON object
                String jsonLine = gson.toJson(new ChunkInfo(chunkIndex, sha256, chunkData.length));
                writer.write(jsonLine);
                writer.newLine();
                writer.flush();

                // update checkpoint atomically
                saveCheckpoint(chunkIndex + 1);

                System.out.println("Processed chunk " + chunkIndex);
                buffer.clear();
                chunkIndex++;
            }
        }
    }

    private static int loadCheckpoint() {
        try {
            if (!Files.exists(CHECKPOINT_FILE)) return 0;
            String s = Files.readString(CHECKPOINT_FILE);
            return Integer.parseInt(s.trim());
        } catch (IOException | NumberFormatException e) {
            return 0;
        }
    }

    private static void saveCheckpoint(int chunkIndex) throws IOException {
        Path temp = CHECKPOINT_FILE.resolveSibling("checkpoint.tmp");
        Files.writeString(temp, String.valueOf(chunkIndex), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.SYNC);
        Files.move(temp, CHECKPOINT_FILE, StandardCopyOption.REPLACE_EXISTING, StandardCopyOption.ATOMIC_MOVE);
    }

    private static String sha256Hex(byte[] data) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(data);
            StringBuilder sb = new StringBuilder();
            for (byte b : hash) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    static class ChunkInfo {
        int chunkIndex;
        String sha256;
        int size;

        public ChunkInfo(int chunkIndex, String sha256, int size) {
            this.chunkIndex = chunkIndex;
            this.sha256 = sha256;
            this.size = size;
        }
    }
}
