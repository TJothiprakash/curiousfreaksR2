package trees.binarysearchtree;

/*Given a BST and an integer. Find the least absolute difference between any node value of the BST and the given integer.

Example 1:

Input:
        10
      /   \
     2    11
   /  \
  1    5
      /  \
     3    6
      \
       4
K = 13
Output:
2
Explanation: K=13. The node that has
value nearest to K is 11. so the answer
is 2
Example 2:

Input:
      8
    /   \
   1     9
    \     \
     4    10
    /
   3
K = 9
Output:
0
Explanation: K=9. The node that has
value nearest to K is 9. so the answer
is 0.
Your Task:
You don't need to read input or print anything. Your task is to complete the function minDiff() that takes the root of the BST and an integer K as its input and returns the minimum absolute difference between any node value of the BST and the integer K.

Expected Time Complexity: O(Height of the BST).
Expected Auxiliary Space: O(Height of the BST).

Constraints:
1 <= Number of nodes <= 105
1 <= Value stored at nodes(data), K <= 105*/
public class ClosestElementinBST {

    // Helper function to calculate the minimum absolute difference
    static int minDiffUtil(Node_ root, int K, int minDiff) {
        // Base case: if the node is null, return the current minDiff
        if (root == null) {
            return minDiff;
        }

        // Calculate the absolute difference between the node value and K
        minDiff = Math.min(minDiff, Math.abs(root.data - K));

        // If K is smaller than the current node's value, move to the left subtree
        if (K < root.data) {
            return minDiffUtil(root.left, K, minDiff);
        }
        // If K is larger, move to the right subtree
        else if (K > root.data) {
            return minDiffUtil(root.right, K, minDiff);
        }
        // If K equals the current node's value, the difference is 0
        return 0;
    }

    // Function to find the minimum absolute difference between node values and K
    static int minDiff(Node_ root, int K) {
        return minDiffUtil(root, K, Integer.MAX_VALUE);
    }

}
