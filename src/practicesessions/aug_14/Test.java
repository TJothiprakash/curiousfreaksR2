package practicesessions.aug_14;

import java.util.LinkedList;
import java.util.Queue;

public class Test {
}

class Solution {
    public String largestGoodInteger(String num) {
        Queue<Integer> q = new LinkedList<>();

        for (int i = 0; i < num.length() - 2; i++) {
            String temp = num.substring(i, i + 3);
            if (temp.charAt(0) == temp.charAt(1) && temp.charAt(1) == temp.charAt(2)) {
                q.add(Integer.parseInt(temp));
            }

        }
        String ans = String.valueOf(q.poll());
        if (ans != null) {
            if (ans.equals("0")) return "000";
            else {
                return ans;
            }
        } else {
            return "";
        }
//        return String.valueOf(q.poll());
    }
}

// Problem: Largest Odd Number in String
// Intuition: No need to parse; just find last odd digit
class Solution1 {
    public String largestOddNumber(String num) {
        for (int i = num.length() - 1; i >= 0; i--) {
            int digit = num.charAt(i) - '0';
            if (digit % 2 == 1) {
                return num.substring(0, i + 1);
            }
        }
        return "";
    }
}
