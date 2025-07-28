package trees.pathanddistance;
import java.util.*;

class TreeNode {
    int val;
    TreeNode left, right;
    TreeNode(int val) { this.val = val; }
}

public class TreeProblems {

    // 1. Root to leaf paths
    public static void rootToLeafPaths(TreeNode root, List<String> result, String path) {
        if (root == null) return;
        path += root.val;
        if (root.left == null && root.right == null) {
            result.add(path);
            return;
        }
        rootToLeafPaths(root.left, result, path + "->");
        rootToLeafPaths(root.right, result, path + "->");
    }

    // 2. Root to leaf path sum
    public static boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) return false;
        if (root.left == null && root.right == null) return root.val == targetSum;
        return hasPathSum(root.left, targetSum - root.val) ||
                hasPathSum(root.right, targetSum - root.val);
    }

    // 3. Maximum path sum from any node
    static int maxPathSum = Integer.MIN_VALUE;
    public static int maxPathDown(TreeNode root) {
        if (root == null) return 0;
        int left = Math.max(0, maxPathDown(root.left));
        int right = Math.max(0, maxPathDown(root.right));
        maxPathSum = Math.max(maxPathSum, left + right + root.val);
        return Math.max(left, right) + root.val;
    }

    // 4. K Sum Paths
    public static int countKSumPaths(TreeNode root, int k) {
        Map<Integer, Integer> prefixSum = new HashMap<>();
        prefixSum.put(0, 1);
        return dfsKSum(root, 0, k, prefixSum);
    }

    private static int dfsKSum(TreeNode root, int currSum, int k, Map<Integer, Integer> map) {
        if (root == null) return 0;
        currSum += root.val;
        int count = map.getOrDefault(currSum - k, 0);
        map.put(currSum, map.getOrDefault(currSum, 0) + 1);
        count += dfsKSum(root.left, currSum, k, map) + dfsKSum(root.right, currSum, k, map);
        map.put(currSum, map.get(currSum) - 1);
        return count;
    }

    // 5. Nodes at given distance K from target
    public static List<Integer> nodesAtDistanceK(TreeNode root, TreeNode target, int k) {
        Map<TreeNode, TreeNode> parentMap = new HashMap<>();
        buildParentMap(root, null, parentMap);

        Queue<TreeNode> queue = new LinkedList<>();
        Set<TreeNode> visited = new HashSet<>();
        queue.add(target);
        visited.add(target);

        int dist = 0;
        while (!queue.isEmpty()) {
            if (dist == k) break;
            int size = queue.size();
            while (size-- > 0) {
                TreeNode curr = queue.poll();
                for (TreeNode neighbor : Arrays.asList(curr.left, curr.right, parentMap.get(curr))) {
                    if (neighbor != null && visited.add(neighbor)) {
                        queue.add(neighbor);
                    }
                }
            }
            dist++;
        }

        List<Integer> result = new ArrayList<>();
        for (TreeNode node : queue) result.add(node.val);
        return result;
    }

    private static void buildParentMap(TreeNode node, TreeNode parent, Map<TreeNode, TreeNode> map) {
        if (node == null) return;
        map.put(node, parent);
        buildParentMap(node.left, node, map);
        buildParentMap(node.right, node, map);
    }

    // 6. Range sum of BST
    public static int rangeSumBST(TreeNode root, int low, int high) {
        if (root == null) return 0;
        if (root.val < low) return rangeSumBST(root.right, low, high);
        if (root.val > high) return rangeSumBST(root.left, low, high);
        return root.val + rangeSumBST(root.left, low, high) + rangeSumBST(root.right, low, high);
    }

    // 7. Minimum distance between 2 nodes
    public static int findDistance(TreeNode root, int p, int q) {
        TreeNode lca = findLCA(root, p, q);
        return pathLength(lca, p, 0) + pathLength(lca, q, 0);
    }

    private static TreeNode findLCA(TreeNode root, int p, int q) {
        if (root == null || root.val == p || root.val == q) return root;
        TreeNode left = findLCA(root.left, p, q);
        TreeNode right = findLCA(root.right, p, q);
        return left == null ? right : (right == null ? left : root);
    }

    private static int pathLength(TreeNode root, int target, int depth) {
        if (root == null) return -1;
        if (root.val == target) return depth;
        int left = pathLength(root.left, target, depth + 1);
        if (left != -1) return left;
        return pathLength(root.right, target, depth + 1);
    }

    // 8. Maximum distance between node and ancestor
    static int maxAncestorDiff = 0;
    public static int maxAncestorDiff(TreeNode root) {
        dfsMaxAncestor(root, root.val, root.val);
        return maxAncestorDiff;
    }

    private static void dfsMaxAncestor(TreeNode node, int max, int min) {
        if (node == null) return;
        maxAncestorDiff = Math.max(maxAncestorDiff, Math.max(Math.abs(max - node.val), Math.abs(min - node.val)));
        max = Math.max(max, node.val);
        min = Math.min(min, node.val);
        dfsMaxAncestor(node.left, max, min);
        dfsMaxAncestor(node.right, max, min);
    }

    // 9. Minimum time to burn a tree
    public static int minTimeToBurn(TreeNode root, TreeNode target) {
        Map<TreeNode, TreeNode> parentMap = new HashMap<>();
        buildParentMap(root, null, parentMap);

        Queue<TreeNode> queue = new LinkedList<>();
        Set<TreeNode> visited = new HashSet<>();
        queue.add(target);
        visited.add(target);

        int time = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            boolean burned = false;
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                for (TreeNode neighbor : Arrays.asList(node.left, node.right, parentMap.get(node))) {
                    if (neighbor != null && visited.add(neighbor)) {
                        queue.add(neighbor);
                        burned = true;
                    }
                }
            }
            if (burned) time++;
        }
        return time;
    }

    // Sample usage
    public static void main(String[] args) {
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5); root.right = new TreeNode(15);
        root.left.left = new TreeNode(3); root.left.right = new TreeNode(7);
        root.right.left = new TreeNode(13); root.right.right = new TreeNode(18);

        // Root to Leaf Paths
        List<String> paths = new ArrayList<>();
        rootToLeafPaths(root, paths, "");
        System.out.println("Root to Leaf Paths: " + paths);

        // Path Sum
        System.out.println("Has Path Sum 18: " + hasPathSum(root, 18));

        // Max Path Sum from any node
        maxPathSum = Integer.MIN_VALUE;
        maxPathDown(root);
        System.out.println("Max Path Sum from any node: " + maxPathSum);

        // K Sum Path Count
        System.out.println("K Sum Path Count (k=18): " + countKSumPaths(root, 18));

        // Nodes at Distance K
        System.out.println("Nodes at Distance 2 from node 5: " + nodesAtDistanceK(root, root.left, 2));

        // Range Sum of BST
        System.out.println("Range Sum BST [7, 15]: " + rangeSumBST(root, 7, 15));

        // Min Distance Between 2 Nodes
        System.out.println("Distance between 3 and 7: " + findDistance(root, 3, 7));

        // Max Distance Between Node and Ancestor
        maxAncestorDiff = 0;
        System.out.println("Max Distance Node-Ancestor: " + maxAncestorDiff(root));

        // Min Time to Burn Tree
        System.out.println("Min Time to Burn Tree from Node 5: " + minTimeToBurn(root, root.left));
    }
}
