package trees.june_11;


import java.util.*;

public class DiffOrderTraversals {

    static class TreeNode {
        int data;
        TreeNode left;
        TreeNode right;

        public TreeNode(int data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    class Pair {
        TreeNode node;
        int vertical;

        Pair(TreeNode node, int vertical) {
            this.node = node;
            this.vertical = vertical;
        }
    }
//    class Pair<U, V> {
//        public U first;
//        public V second;
//        public Pair(U first, V second) {
//            this.first = first;
//            this.second = second;
//        }
//        public U getKey() { return first; }
//        public V getValue() { return second; }
//    }


    public void inorderTraversal(TreeNode root) {
        if (root == null) return;
        inorderTraversal(root.left);
        System.out.print(root.data + " ");
        inorderTraversal(root.right);
    }

    public void preorderTraversal(TreeNode root) {
        if (root == null) return;
        System.out.print(root.data + " ");
        preorderTraversal(root.left);
        preorderTraversal(root.right);
    }

    public void postorderTraversal(TreeNode root) {
        if (root == null) return;
        postorderTraversal(root.left);
        postorderTraversal(root.right);
        System.out.print(root.data + " ");
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);


        DiffOrderTraversals traversals = new DiffOrderTraversals();
        System.out.println("Vertical Order Traversal:");
        System.out.println(traversals.verticalOrder(root));
//        System.out.println(traversals.findVertical(root));
        System.out.println();
        System.out.println();
//        System.out.println("Boundary Traversal:");
//        System.out.println("Inorder:");
//        traversals.inorderTraversal(root);
//        System.out.println();
//        System.out.println("Preorder:");
//        traversals.preorderTraversal(root);
//        System.out.println();
//        System.out.println("Postorder:");
//        traversals.postorderTraversal(root);
//        System.out.println(
//        );
//        System.out.println("Level order:");
//        traversals.levelOrderTraversal(root);
    }

    public void levelOrderTraversal(TreeNode root) {
        if (root == null) return;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int levelSize = queue.size();  // Number of nodes at current level

            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();
                System.out.print(node.data + " ");

                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            System.out.println();  // End of current level
        }
    }

    public void boundaryTraversal(TreeNode root) {
        if (root == null) return;

        Set<TreeNode> printed = new HashSet<>();

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int levelSize = queue.size();

            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();

                // Print first/last node of the level
                if (i == 0 || i == levelSize - 1) {
                    if (!printed.contains(node)) {
                        System.out.print(node.data + " ");
                        printed.add(node);
                    }
                }

                // Print leaf node
                if (node.left == null && node.right == null) {
                    if (!printed.contains(node)) {
                        System.out.print(node.data + " ");
                        printed.add(node);
                    }
                }

                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
            System.out.println();  // End of level
        }
    }
//
//    public List<List<Integer>> findVertical(TreeNode root) {
//        // Map to store nodes based on
//        // vertical and level information
//        Map<Integer, Map<Integer, TreeSet<Integer>>> nodes = new TreeMap<>();
//
//        // Queue for BFS traversal, each
//        // element is a pair containing node
//        // and its vertical and level information
//        Queue<Pair<TreeNode, Pair<Integer, Integer>>> todo = new LinkedList<>();
//
//        // Push the root node with initial vertical
//        // and level values (0, 0)
//        todo.add(new Pair<>(root, new Pair<>(0, 0)));
//
//        // BFS traversal
//        while (!todo.isEmpty()) {
//            // Retrieve the node and its vertical
//            // and level information from
//            // the front of the queue
//            Pair<TreeNode, Pair<Integer, Integer>> p = todo.poll();
//            TreeNode temp = p.getKey();
//
//            // Extract the vertical and level information
//            // x -> vertical
//            int x = p.getValue().getKey();
//            // y -> level
//            int y = p.getValue().getValue();
//
//            // Insert the node value into the
//            // corresponding vertical and level
//            // in the map
//            nodes.computeIfAbsent(x, k -> new TreeMap<>())
//                    .computeIfAbsent(y, k -> new TreeSet<>())
//                    .add(temp.data);
//
//            // Process left child
//            if (temp.left != null) {
//                todo.add(new Pair<>(temp.left, new Pair<>(x - 1, y + 1)));
//            }
//
//            // Process right child
//            if (temp.right != null) {
//                todo.add(new Pair<>(temp.right, new Pair<>(x + 1, y + 1)));
//            }
//        }
//
//        // Prepare the final result list
//        // by combining values from the map
//        List<List<Integer>> ans = new ArrayList<>();
//        for (Map.Entry<Integer, Map<Integer, TreeSet<Integer>>> entry : nodes.entrySet()) {
//            List<Integer> col = new ArrayList<>();
//            for (TreeSet<Integer> set : entry.getValue().values()) {
//                // Insert node values
//                // into the column list
//                col.addAll(set);
//            }
//            // Add the column list
//            // to the final result
//            ans.add(col);
//        }
//        return ans;
//    }
//
//

    public List<List<Integer>> verticalOrder(TreeNode root) {
        if (root == null) return new ArrayList<>();

        Map<Integer, List<Integer>> verticalMap = new TreeMap<>();
        Queue<Pair> queue = new LinkedList<>();

        queue.offer(new Pair(root, 0));

        while (!queue.isEmpty()) {
            Pair current = queue.poll();
            TreeNode node = current.node;
            int vertical = current.vertical;
            if (!verticalMap.containsKey(vertical)) {
                verticalMap.put(vertical, new ArrayList<>());
            }
            verticalMap.get(vertical).add(node.data);

            if (node.left != null) {
                queue.offer(new Pair(node.left, vertical - 1));
            }
            if (node.right != null) {
                queue.offer(new Pair(node.right, vertical + 1));
            }
        }

        return new ArrayList<>(verticalMap.values());
    }


}


