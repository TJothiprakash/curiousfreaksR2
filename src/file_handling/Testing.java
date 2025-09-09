package file_handling;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Testing {
    static void main() {

    }

    static void main(String[] args) {
        createFile();
    }
    static void createFile() {
        System.out.println(System.getProperty("user.dir"));
        String rootFolder = System.getProperty("user.dir");
        Path path = Path.of( "testing.txt");
        Path testing;
        try {
            testing = Files.createFile(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("testing = " + testing);
        Path path1 = Paths.get(".gitignore");
        System.out.println(Files.exists(path1));



    }


}
