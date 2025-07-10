package file_handling;

import java.io.*;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class File_Handling {
    public static void main(String[] args) throws IOException {
        File_Handling file_handling = new File_Handling();
        try {

            Object obj = new  Object();
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("obj.dat"));
            oos.writeObject(obj);
            oos.close();

            try (BufferedReader br = new BufferedReader(new FileReader("file.txt"))) {
                // Safe auto-close
            }

            RandomAccessFile file = new RandomAccessFile("big.txt", "rw");
            FileChannel channel = file.getChannel();
            MappedByteBuffer buffer = channel.map(FileChannel.MapMode.READ_WRITE, 0, channel.size());
            Path path = Paths.get("big.txt");


//            file_handling.readFile("D:/practice/src/file_handling/test.txt");
            // file_handling.readLargeFile("D:/practice/src/file_handling/test.txt");
            System.out.println("file_handling.countLines(\"D:\\\\practice\\\\src\\\\file_handling\\\\test.txt\") = " + file_handling.countLines("D:\\practice\\src\\file_handling\\test.txt"));
            System.out.println("file_handling.countWords(\"D:\\\\practice\\\\src\\\\file_handling\\\\test.txt\") = " + file_handling.countWords("D:\\practice\\src\\file_handling\\test.txt"));


            System.out.println("file_handling.countNonEmptyLines(\"D:\\\\practice\\\\src\\\\file_handling\\\\test.txt\") = " + file_handling.countNonEmptyLines("D:\\practice\\src\\file_handling\\test.txt"));
            System.out.println("file_handling.joinLines(\"D:\\\\practice\\\\src\\\\file_handling\\\\test.txt\") = " + file_handling.joinLines("D:\\practice\\src\\file_handling\\test.txt"));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // Count the number of non-empty lines
    public long countNonEmptyLines(String filePath) throws IOException {
        return Files.lines(Paths.get(filePath)).filter(line -> !line.trim().isEmpty()).count();
    }

    // Count total words in a file
    public long countWords(String filePath) throws IOException {
        return Files.lines(Paths.get(filePath))
                .flatMap(line -> Arrays.stream(line.trim().split("\\s+")))
                .count();
    }

    // Read file and join all lines into a single String
    public String joinLines(String filePath) throws IOException {
        return Files.lines(Paths.get(filePath)).collect(Collectors.joining("\n"));
    }

    // Write stream data to a file (Files.write)
    public void writeToFile(List<String> data, String filePath) throws IOException {
        Files.write(Paths.get(filePath), data);
    }


    public long countLines(String filePath) throws IOException {
        return Files.lines(Paths.get(filePath)).count();
    }

    // ✅ Read file line-by-line using while loop
    public void readFile(String filePath) throws IOException {
        Path path = Paths.get(filePath);
        try (BufferedReader bufferedReader = Files.newBufferedReader(path)) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    // ✅ Read using streams
    public void readLargeFile(String filePath) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
            List<String> content = bufferedReader.lines().collect(Collectors.toList());
            content.forEach(System.out::println);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
