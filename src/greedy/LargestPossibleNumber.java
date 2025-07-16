package greedy;

/*Given two numbers n and s ,
 find the largest number that can be formed with n digits and
  whose sum of digits should be equals to s. Return -1 if it is not possible.

Examples :

Input: n = 2, s = 9
Output: 90
Explaination: It is the biggest number with sum of digits equals to 9.
Input: n = 3, s = 20
Output: 992
Explaination: It is the biggest number with sum of digits equals to 20.
Input: n = 1, s = 0
Output: 0
Constraints:
1 ≤ n ≤ 105
0 ≤ s ≤ 105*/
public class LargestPossibleNumber {
    static String findLargest(int n, int s) {
        // If the sum `s` is too large to be split across `n` digits, return "-1"
        if (s > (9 * n)) {
            return "-1";
        }

        // If the sum is 0 and `n` > 1, it's impossible to create the number
        if (s == 0 && n > 1) {
            return "-1";
        }

        StringBuilder sb = new StringBuilder();

        // Construct the largest number digit by digit
        for (int i = 0; i < n; i++) {
            if (s >= 9) {
                sb.append(9);
                s -= 9;
            } else {
                sb.append(s);
                s = 0;
            }
        }

        // Fill remaining digits with 0 if `s` becomes 0 before using all digits
        while (sb.length() < n) {
            sb.append(0);
        }

        return sb.toString();
    }

    public static void main(String[] args) {
//        System.out.println(findLargest(2, 9));  // Output: 90
        System.out.println(findLargest(3, 20)); // Output: 992
//        System.out.println(findLargest(1, 0));  // Output: 0
//        System.out.println(findLargest(2, 0));  // Output: -1
    }
}
