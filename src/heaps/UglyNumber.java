package heaps;

import java.util.*;

public class UglyNumber {
    public static boolean isUgly(int num) {
        if (num <= 0) {
            return false; // Negative numbers and zero are not ugly numbers
        }

        // Divide the number by 2, 3, and 5 as long as possible
        while (num % 2 == 0) {
            num /= 2;
        }
        while (num % 3 == 0) {
            num /= 3;
        }
        while (num % 5 == 0) {
            num /= 5;
        }

        // If the remaining number is 1, it's an ugly number, otherwise it's not
        return num == 1;
    }

    public static int nthUglyNumber(int n) {
        List<Integer> uglyNumbers = new ArrayList<>();
        int num = 1; // Start with 1 (the first ugly number)

        while (uglyNumbers.size() < n) {
            if (isUgly(num)) {
                uglyNumbers.add(num);
            }
            num++;
        }

        // Return the nth ugly number
        return uglyNumbers.get(n - 1);
    }

    public static void main(String[] args) {
        int n = 1352; // Example input
        System.out.println("The " + n + "th ugly number is: " + nthUglyNumber(n));
    }
}
