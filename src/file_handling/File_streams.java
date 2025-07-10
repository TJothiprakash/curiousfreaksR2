package file_handling;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class File_streams {
    public static void main(String[] args) {
        try {
            // ✅ Read all lines into List<String>
            List<String> lines = Files.lines(Path.of("file.txt")).collect(Collectors.toList());

// ✅ Print file contents (streaming)
            Files.lines(Path.of("file.txt")).forEach(System.out::println);

// ✅ Count lines
            long totalLines = Files.lines(Path.of("file.txt")).count();

// ✅ Count non-empty lines
            long nonEmptyLines = Files.lines(Path.of("file.txt"))
                    .filter(line -> !line.trim().isEmpty())
                    .count();

// ✅ Join all lines into one String
            String joined = Files.lines(Path.of("file.txt"))
                    .collect(Collectors.joining("\n"));

// ✅ Count total words
            long wordCount = Files.lines(Path.of("file.txt"))
                    .flatMap(line -> Arrays.stream(line.split("\\s+")))
                    .filter(word -> !word.isBlank())
                    .count();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
    }
}
