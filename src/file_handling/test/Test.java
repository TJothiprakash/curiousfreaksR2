package file_handling.test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.stream.Stream;

public class Test {
    public static void main(String[] args) throws IOException {
        Path file = Path.of("C:\\Users\\DELL\\IdeaProjects\\curiousfreaksR2\\src");
        try (var stream = Files.list(file)) {
//            stream.forEach(System.out::println);
        }

        try (var stream = Files.walk(file)) {
//            stream.forEach(System.out::println);
        }

        Path test = Paths.get("C:\\Users\\DELL\\IdeaProjects\\curiousfreaksR2\\src\\arrays\\arraysubset.java");

//        System.out.println("test = " + test);

        String ans = Files.readString(test);
//        System.out.println("ans = " + ans);

        Path test1 = Paths.get("C:\\Users\\DELL\\IdeaProjects\\curiousfreaksR2\\src\\arrays\\", "trappingwater.java");
        Stream<String> res  = Files.lines(test1);
//        res.forEach(System.out::println);

        List<String> res1 = Files.readAllLines(test1);
//        for (String s: res1)
//            System.out.println( s+" ");

        Path newfile = Path.of("newText1.txt");
//        Path created =  Files.createFile(newfile);
//        System.out.println(created.toAbsolutePath());
        System.out.println(System.getProperty("user.dir"));

        String ansd = "letswritessomething\n";
        Path newTextfile = Path.of("newText1.txt");
        Path writeout = Files.writeString(newTextfile,ansd, StandardOpenOption.APPEND);
        Stream<String> sout = Files.lines(writeout);
        sout.forEach(System.out::println);


    }




}
