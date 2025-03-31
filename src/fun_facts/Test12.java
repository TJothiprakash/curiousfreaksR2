package fun_facts;

import java.util.ArrayList;
import java.util.List;

public class Test12 {
    public static void main(String[] args) {
        List<Short> A = new ArrayList<>();

        for (short i = 0; i < 100; i++) {
            A.add(i);  // Add current value to the list

            if (A.size() > 1) {  // Ensure more than 1 element exists before removing
                A.remove(A.size() - 1);  // Remove the last (most recently added) element
            }
        }

        System.out.println(A);  // Print the list after the loop completes
    }
}