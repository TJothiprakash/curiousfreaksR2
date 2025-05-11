package practicesessions.april_24;

import java.util.ArrayList;

public class Test {

    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<String>();
        list.add("A");
        list.add("B");
        list.add("C");

        list.add("D");
        list.add("E");
        list.forEach(System.out::println);

    }
}
