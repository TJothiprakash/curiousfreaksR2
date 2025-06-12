package trees.june_11.pathanddistance;

import java.util.*;


public class BurnTree {

    // Main function to calculate burn time
    public static int minTimeToBurn(TreeNode root, int target) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        buildGraph(root, null, graph);

        return bfsBurn(graph, target);
    }

    // Step 1: Build undirected graph from the binary tree
    private static void buildGraph(TreeNode node, TreeNode parent, Map<Integer, List<Integer>> graph) {
        if (node == null) return;

        graph.putIfAbsent(node.data, new ArrayList<>());

        if (parent != null) {
            graph.get(node.data).add(parent.data);
            graph.get(parent.data).add(node.data);
        }

        buildGraph(node.left, node, graph);
        buildGraph(node.right, node, graph);
    }

    // Step 2: BFS from target node and count time
    private static int bfsBurn(Map<Integer, List<Integer>> graph, int start) {
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();

        queue.offer(start);
        visited.add(start);
        int time = -1;

        while (!queue.isEmpty()) {
            int size = queue.size();
            time++;

            for (int i = 0; i < size; i++) {
                int curr = queue.poll();

                for (int neighbor : graph.getOrDefault(curr, new ArrayList<>())) {
                    if (!visited.contains(neighbor)) {
                        visited.add(neighbor);
                        queue.offer(neighbor);
                    }
                }
            }
        }
        return time;
    }
}
