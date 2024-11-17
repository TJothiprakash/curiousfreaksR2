package basicmath;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NumberDivision {
    public static void main(String[] args) {
        int n = 12;
        int m=1;
        Solution Sol = new Solution();
        System.out.println(Sol.printDivisors(m));
    }
}

class Solution {
    public static List<Integer> printDivisors(int n) {
        ArrayList<Integer> li = new ArrayList<Integer>();

        // Iterate up to sqrt(n)
        int sqrtN = (int) Math.sqrt(n);
        for (int i = 1; i <= sqrtN; i++) {
            if (n % i == 0) {
                li.add(i); // Add divisor i
                if (i != n / i) {
                    li.add(n / i); // Add divisor n/i if it's different from i
                }
            }
        }

        // Handle edge case for n = 1
        if (n == 1) {
            li.add(1);
        }
        Collections.sort(li);

        return li;
    }
}