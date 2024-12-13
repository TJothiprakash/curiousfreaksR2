package greedy;

/*A parentheses string is valid if and only if:

It is the empty string,
It can be written as AB (A concatenated with B), where A and B are valid strings, or
It can be written as (A), where A is a valid string.
You are given a parentheses string s. In one move, you can insert a parenthesis at any position of the string.

For example, if s = "()))", you can insert an opening parenthesis to be "(()))" or a closing parenthesis to be "())))".
Return the minimum number of moves required to make s valid.



Example 1:

Input: s = "())"
Output: 1
Example 2:

Input: s = "((("
Output: 3


Constraints:

1 <= s.length <= 1000
s[i] is either '(' or ')'.
To solve the problem of making a parentheses string valid with the minimum number of insertions, we need to ensure that the string follows the basic rules of valid parentheses:

For each closing parenthesis ), there should be a matching opening parenthesis ( before it.
By the end of the string, the number of opening parentheses ( should be equal to the number of closing parentheses ).
Approach:
We can traverse the string and maintain a balance between the opening and closing parentheses.

Balance:
Start with a balance variable set to 0.
For each character in the string:
If it is an opening parenthesis (, increment the balance.
If it is a closing parenthesis ), decrement the balance.
If the balance goes negative (i.e., a closing parenthesis appears without a matching opening parenthesis), we have an imbalance, and we need to insert an opening parenthesis ( to fix this. Each such imbalance requires one move.
After traversing the string:
If balance is greater than 0, we have more opening parentheses than closing ones, so we need to insert balance closing parentheses ) to balance the string.
Thus, the total number of moves will be the number of unmatched closing parentheses plus the number of unmatched opening parentheses.

Plan:
Traverse the string to calculate how many unmatched closing parentheses there are.
After traversal, calculate how many unmatched opening parentheses there are.
The result is the sum of these two values.
Code Implem*/
public class ParenthesesValidator {

    public static int minAddToMakeValid(String s) {
        int balance = 0;
        int moves = 0;

        // Traverse through the string
        for (char c : s.toCharArray()) {
            if (c == '(') {
                balance++; // Increase balance for opening parenthesis
            } else if (c == ')') {
                balance--; // Decrease balance for closing parenthesis
                if (balance < 0) {
                    // If balance is negative, we have more ')' than '('
                    moves++; // We need to add an opening parenthesis
                    balance = 0; // Reset balance after the fix
                }
            }
        }

        // If balance > 0, we need to add `balance` closing parentheses
        moves += balance;

        return moves;
    }

    public static void main(String[] args) {
        // Test case 1
        String s1 = "())";
        System.out.println(minAddToMakeValid(s1)); // Output: 1

        // Test case 2
        String s2 = "(((";
        System.out.println(minAddToMakeValid(s2)); // Output: 3

        // Test case 3
        String s3 = "()";
        System.out.println(minAddToMakeValid(s3)); // Output: 0
    }
}
