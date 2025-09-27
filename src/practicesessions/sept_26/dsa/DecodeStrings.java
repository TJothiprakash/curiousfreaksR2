package practicesessions.sept_26.dsa;

import java.util.HashMap;
import java.util.Map;

public class DecodeStrings {

    public int numDecodings(String s) {
        Map<Integer, Integer> memo = new HashMap<>();
        return helper(s, 0, memo);
    }

    // index = current position in the string
    // memo stores results for each index
    private int helper(String s, int index, Map<Integer, Integer> memo) {
        // Base case: reached end of string => valid decoding
        if (index == s.length()) return 1;

        // If string starts with '0', invalid
        if (s.charAt(index) == '0') return 0;

        // Check memo
        if (memo.containsKey(index)) return memo.get(index);

        // Option 1: take single digit
        int ways = helper(s, index + 1, memo);

        // Option 2: take two digits if valid (10-26)
        if (index + 1 < s.length()) {
            int twoDigit = Integer.parseInt(s.substring(index, index + 2));
            if (twoDigit >= 10 && twoDigit <= 26) {
                ways += helper(s, index + 2, memo);
            }
        }

        memo.put(index, ways);
        return ways;
    }

    // Test
    public static void main(String[] args) {
        DecodeStrings ds = new DecodeStrings();
        System.out.println(ds.numDecodings("12"));    // 2: "AB" or "L"
        System.out.println(ds.numDecodings("226"));   // 3: "BZ", "VF", "BBF"
        System.out.println(ds.numDecodings("06"));    // 0: invalid
        System.out.println(ds.numDecodings("11106")); // 2: "AAJF" or "KJF"
    }
}
