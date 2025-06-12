package trees.june_11.pathanddistance;

import java.util.HashMap;
import java.util.Map;


public class PathSumK {
    public static int pathSum(TreeNode root, int k) {
        Map<Integer, Integer> prefixMap = new HashMap<>();
        prefixMap.put(0, 1); // base case for path starting at root
        return dfs(root, 0, k, prefixMap);
    }

    private static int dfs(TreeNode node, int currSum, int k, Map<Integer, Integer> prefixMap) {
        if (node == null) return 0;

        currSum += node.data;

        // Count of paths ending at current node with sum = k
        int count = prefixMap.getOrDefault(currSum - k, 0);

        // Update the prefix map
        prefixMap.put(currSum, prefixMap.getOrDefault(currSum, 0) + 1);

        // Recur for left and right children
        count += dfs(node.left, currSum, k, prefixMap);
        count += dfs(node.right, currSum, k, prefixMap);

        // Backtrack (remove current sum from map to avoid affecting other paths)
        prefixMap.put(currSum, prefixMap.get(currSum) - 1);

        return count;
    }
}
