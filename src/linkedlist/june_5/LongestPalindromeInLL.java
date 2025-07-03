package linkedlist.june_5;


public class LongestPalindromeInLL {
//O(n * n)O(1)
    public int maxPalindrome(Node head) {
        Node prev = null, curr = head;
        int result = 0;

        while (curr != null) {
            Node next = curr.next;
            curr.next = prev;

            // Odd length palindrome (centered at curr)
            result = Math.max(result, 2 * countCommon(prev, next) + 1);

            // Even length palindrome (centered between prev and curr)
            result = Math.max(result, 2 * countCommon(curr, next));

            // Move ahead
            prev = curr;
            curr = next;
        }

        return result;
    }
//O(n)O(1)
    private int countCommon(Node a, Node b) {
        int count = 0;
        while (a != null && b != null && a.data == b.data) {
            count++;
            a = a.next;
            b = b.next;
        }
        return count;
    }
}
