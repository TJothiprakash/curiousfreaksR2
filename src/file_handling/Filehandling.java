package file_handling;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

public class Filehandling {

    static void main(String[] args) throws IOException {
        createFiles();
        getRandomStrings();
        writeToOne(new File("test.txt"));
        writeToTwo();
    }


    // create two files
    public static void createFiles() {
        Path path = Paths.get("one.txt");
        Path path1 = Paths.get("two.txt");
        Path path2 = Paths.get("three.txt");
        if (!Files.exists(path)) {
            try {
                Path one = Files.createFile(path);
                System.out.println("file created " + one.toAbsolutePath());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        try {
            if (!Files.exists(path1)) {
                Path two = Files.createFile(path1);
                System.out.println(" = " + two.toAbsolutePath());
                System.out.println("two.getRoot() = " + two.getRoot());
                System.out.println(" " + two.toUri());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println("end of operation ");
        }


    }


    // write something to the file one

    public static void writeToOne(File file) throws IOException {
        Path path = file.toPath();
        String randomText = getRandomStrings();

    }

    private static String getRandomStrings() throws IOException {
        Path path = Paths.get("one.txt");

        // create file if not exists
        if (!Files.exists(path)) {
            Files.createFile(path);
        }

        StringBuilder allWords = new StringBuilder();

        for (int k = 0; k < 100; k++) {
            StringBuilder word = new StringBuilder();

            for (int i = 0; i < 5; i++) {
                char randomAlphabet = (char) ('a' + (int) (Math.random() * 26));
                word.append(randomAlphabet);
            }

            // print whole 5-letter word
            System.out.println(word);

            // append word + newline
            allWords.append(word).append(System.lineSeparator());
        }

        // write once (better performance)
        Files.write(path, allWords.toString().getBytes(),
                StandardOpenOption.WRITE);

        return allWords.toString();
    }

    public static void readOne() throws IOException {
        Path path = Paths.get("one.txt");
        List<String> stringStrem = Files.readAllLines(path);
        for (String string : stringStrem) {
            System.out.println(string);
        }
    }

    // write to file two
    public static void writeToTwo() throws IOException {
        Path path = Paths.get("two.txt");
        Path path1 = Paths.get("one.txt");

        Stream<String> stream = Files.lines(path1);
        Files.write(path, stream.toList(), StandardOpenOption.APPEND);
    }

//


}
