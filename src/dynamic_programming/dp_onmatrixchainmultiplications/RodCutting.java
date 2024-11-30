package dynamic_programming.dp_onmatrixchainmultiplications;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class RodCutting {


    // Recursive function to calculate the minimum cost
    static int f(int i, int j, ArrayList<Integer> cuts) {
        // Base case
        if (i > j) {
            return 0;
        }

        int mini = Integer.MAX_VALUE;

        for (int ind = i; ind <= j; ind++) {
            int ans = cuts.get(j + 1) - cuts.get(i - 1) + f(i, ind - 1, cuts) + f(ind + 1, j, cuts);

            mini = Math.min(mini, ans);
        }

        return mini;
    }

    // Function to calculate the minimum cost
    static int cost(int n, int c, ArrayList<Integer> cuts) {
        // Modify the cuts array
        cuts.add(n);
        cuts.add(0);
        Collections.sort(cuts);

        return f(1, c, cuts);
    }

    public static void main(String[] args) {
        ArrayList<Integer> cuts = new ArrayList<>(Arrays.asList(3, 5, 1, 4));
        int c = cuts.size();
        int n = 7;

        System.out.println("The minimum cost incurred: " + cost(n, c, cuts));
    }
}


