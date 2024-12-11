package strings;

import java.util.ArrayList;
import java.util.List;

public class ZAlgorithm {
    public List<Integer> search(String S, String pat) {
        String concat = pat + "$" + S; // Combine pattern, a separator, and the text
        int lenPat = pat.length();
        int lenConcat = concat.length();

        // Compute the Z array
        int[] Z = new int[lenConcat];
        computeZArray(concat, Z);

        List<Integer> result = new ArrayList<>();

        // Check for matches in the Z array
        for (int i = lenPat + 1; i < lenConcat; i++) {
            if (Z[i] == lenPat) { // A match is found
                result.add(i - lenPat); // Add 1-based index
            }
        }

        return result;
    }

    private void computeZArray(String str, int[] Z) {
        int n = str.length();
        int L = 0, R = 0;

        for (int i = 1; i < n; i++) {
            if (i > R) {
                // Outside current Z-box
                L = R = i;
                while (R < n && str.charAt(R) == str.charAt(R - L)) {
                    R++;
                }
                Z[i] = R - L;
                R--;
            } else {
                // Inside current Z-box
                int k = i - L;
                if (Z[k] < R - i + 1) {
                    Z[i] = Z[k];
                } else {
                    L = i;
                    while (R < n && str.charAt(R) == str.charAt(R - L)) {
                        R++;
                    }
                    Z[i] = R - L;
                    R--;
                }
            }
        }
    }
}
