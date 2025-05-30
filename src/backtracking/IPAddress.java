package backtracking;

import java.util.ArrayList;
import java.util.List;

public class IPAddress {
    public static void main(String[] args) {
        IPAddress sol = new IPAddress();
        System.out.println(sol.restoreIpAddresses("25525511135"));
        System.out.println(sol.restoreIpAddresses("191222123175"));
        System.out.println(sol.restoreIpAddresses("1111"));


    }
    public List<String> restoreIpAddresses(String s) {
        List<String> result = new ArrayList<>();
        backtrack(s, 0, "", 0, result);
        return result;
    }

    private void backtrack(String s, int index, String current, int count, List<String> result) {
        // If 4 parts are used and all characters are consumed
        if (count == 4 && index == s.length()) {
            result.add(current.substring(0, current.length() - 1)); // remove trailing '.'
            return;
        }

        // If more than 4 segments are used, return
        if (count >= 4) return;

        // Try segments of length 1 to 3
        for (int len = 1; len <= 3; len++) {
            if (index + len > s.length()) break;

            String part = s.substring(index, index + len);

            // Leading zero check
            if (part.startsWith("0") && part.length() > 1) continue;

            // Range check
            int val = Integer.parseInt(part);
            if (val > 255) continue;

            // Recurse
            backtrack(s, index + len, current + part + ".", count + 1, result);
        }
    }
}
