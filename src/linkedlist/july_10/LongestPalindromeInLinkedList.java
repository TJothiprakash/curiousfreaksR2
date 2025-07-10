package linkedlist.july_10;


// ✅ Java Solution to find longest palindromic sublist length
public class LongestPalindromeInLinkedList {
    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
        }
    }

    // Function to count common matching nodes
    private static int countCommon(Node a, Node b) {
        int count = 0;
        while (a != null && b != null) {
            if (a.data == b.data)
                count++;
            else
                break;
            a = a.next;
            b = b.next;
        }
        return count;
    }

    public static int maxPalindrome(Node head) {
        Node prev = null, curr = head;
        int result = 0;

        while (curr != null) {
            Node next = curr.next;

            // Reverse current node pointer
            curr.next = prev;

            // Check for odd-length palindrome (centered at curr)
            int oddLen = 2 * countCommon(prev, next) + 1;

            // Check for even-length palindrome (centered between prev and curr)
            int evenLen = 2 * countCommon(curr, next);

            result = Math.max(result, Math.max(oddLen, evenLen));

            // Move to next node
            prev = curr;
            curr = next;
        }

        return result;
    }

    // Helper: Build list from array
    public static Node buildList(int[] arr) {
        Node dummy = new Node(0), curr = dummy;
        for (int val : arr) {
            curr.next = new Node(val);
            curr = curr.next;
        }
        return dummy.next;
    }

    public static void main(String[] args) {
        Node head1 = buildList(new int[]{2, 3, 7, 3, 2, 12, 24});
        System.out.println("Output: " + maxPalindrome(head1)); // 5

        Node head2 = buildList(new int[]{12, 4, 4, 3, 14});
        System.out.println("Output: " + maxPalindrome(head2)); // 2
    }
}
