package collections_practice.utilitymethods;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Collections;

public class Practice {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(0);
        list.add(1);
        list.add(87);
        list.add(64);
        list.add(100);

        System.out.println("Original Integer list: " + list);
        Collections.sort(list); // uses natural order (Comparable of Integer)
        System.out.println("Sorted Integer list: " + list);

        List<Book> bookList = new ArrayList<>();
        bookList.add(new Book(1, "jp"));
        bookList.add(new Book(2, "arun"));
        bookList.add(new Book(3, "arvind"));
        bookList.add(new Book(4, "balaji"));
        bookList.add(new Book(2, "zebra"));

        // 🔹 Comparator 1: sort by id descending
        Comparator<Book> comparatorByIdDesc = (b1, b2) -> Integer.compare(b2.id, b1.id);

        // 🔹 Comparator 2: sort by name ascending
        Comparator<Book> comparatorByName = (b1, b2) -> b1.name.compareTo(b2.name);

        // 🔹 Comparator 3: sort by id ascending, then name ascending
        Comparator<Book> comparatorByIdThenName = Comparator
                .comparingInt((Book b) -> b.id)
                .thenComparing(b -> b.name);

        System.out.println("\nOriginal book list: " + bookList);

        Collections.sort(bookList, comparatorByIdDesc);
        System.out.println("Sorted by id (descending): " + bookList);

        Collections.sort(bookList, comparatorByName);
        System.out.println("Sorted by name: " + bookList);

        Collections.sort(bookList, comparatorByIdThenName);
        System.out.println("Sorted by id, then name: " + bookList);
    }
}

class Book {
    int id;
    String name;

    public Book(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return id + "-" + name;
    }
}
