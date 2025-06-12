package trees.june_11;

import java.util.*;

class BinaryTreeViews {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);
        root.right.left.right = new TreeNode(8);
        root.right.right.right = new TreeNode(9);

        System.out.println("Top View: " + Views.topView(root));
        System.out.println("Bottom View: " + Views.bottomView(root));
        System.out.println("Left View: " + Views.leftView(root));
        System.out.println("Right View: " + Views.rightView(root));
        System.out.println("Diagonal Traversal: " + Views.diagonalTraversal(root));
    }

    static class TreeNode {
        int data;
        TreeNode left, right;

        TreeNode(int data) {
            this.data = data;
        }
    }

    public static class Views {
        public static List<Integer> topView(TreeNode root) {
            Map<Integer, Integer> map = new TreeMap<>();
            Queue<Pair> queue = new LinkedList<>();
            queue.offer(new Pair(root, 0));

            while (!queue.isEmpty()) {
                Pair current = queue.poll();
                TreeNode node = current.node;
                int hd = current.hd;

                if (!map.containsKey(hd)) {
                    map.put(hd, node.data);
                }

                if (node.left != null) {
                    queue.offer(new Pair(node.left, hd - 1));
                }
                if (node.right != null) {
                    queue.offer(new Pair(node.right, hd + 1));
                }
            }

            return new ArrayList<>(map.values());
        }

        public static List<Integer> bottomView(TreeNode root) {

            Map<Integer, Integer> map = new TreeMap<>();
            Queue<Pair> queue = new LinkedList<>();
            queue.offer(new Pair(root, 0));

            while (!queue.isEmpty()) {
                Pair current = queue.poll();
                TreeNode node = current.node;
                int hd = current.hd;

                map.put(hd, node.data);

                if (node.left != null) {
                    queue.offer(new Pair(node.left, hd - 1));
                }
                if (node.right != null) {
                    queue.offer(new Pair(node.right, hd + 1));
                }
            }

            return new ArrayList<>(map.values());
        }


        /*Aspect	Left View	Right View
Selection	First node at each level	Last node at each level
Logic	Check if the level has been visited	Overwrite value for each level
Output Order	Leftmost nodes at each level	Rightmost nodes at each level
*/
        public static List<Integer> leftView(TreeNode root) {
            List<Integer> result = new ArrayList<>();
            leftViewHelper(root, 0, result);
            return result;
        }

        /* public static List<Integer> leftView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;

        Queue<Pair> queue = new LinkedList<>();
        queue.offer(new Pair(root, 0));
        Set<Integer> levels = new HashSet<>();

        while (!queue.isEmpty()) {
            Pair current = queue.poll();
            TreeNode node = current.node;
            int level = current.level;

            // Add the first node of each level
            if (!levels.contains(level)) {
                result.add(node.data);
                levels.add(level);
            }

            if (node.left != null) {
                queue.offer(new Pair(node.left, level + 1));
            }
            if (node.right != null) {
                queue.offer(new Pair(node.right, level + 1));
            }
        }

        return result;
    }
*/

        private static void leftViewHelper(TreeNode node, int level, List<Integer> result) {
            if (node == null) return;

            if (level == result.size()) {
                result.add(node.data);
            }

            leftViewHelper(node.left, level + 1, result);
            leftViewHelper(node.right, level + 1, result);
        }

        public static List<Integer> rightView(TreeNode root) {
            List<Integer> result = new ArrayList<>();
            rightViewHelper(root, 0, result);
            return result;
        }

        /*  public static List<Integer> rightView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;

        Queue<Pair> queue = new LinkedList<>();
        queue.offer(new Pair(root, 0));
        Map<Integer, Integer> levelMap = new HashMap<>();

        while (!queue.isEmpty()) {
            Pair current = queue.poll();
            TreeNode node = current.node;
            int level = current.level;

            // Overwrite the value for each level
            levelMap.put(level, node.data);

            if (node.left != null) {
                queue.offer(new Pair(node.left, level + 1));
            }
            if (node.right != null) {
                queue.offer(new Pair(node.right, level + 1));
            }
        }

        // Collect the nodes from the map
        for (int i = 0; i < levelMap.size(); i++) {
            result.add(levelMap.get(i));
        }

        return result;
    }*/
        private static void rightViewHelper(TreeNode node, int level, List<Integer> result) {
            if (node == null) return;

            if (level == result.size()) {
                result.add(node.data);
            }

            rightViewHelper(node.right, level + 1, result);
            rightViewHelper(node.left, level + 1, result);
        }

        public static List<List<Integer>> diagonalTraversal(TreeNode root) {
            Map<Integer, List<Integer>> map = new TreeMap<>();
            diagonalTraversalHelper(root, 0, map);

            return new ArrayList<>(map.values());
        }

        private static void diagonalTraversalHelper(TreeNode node, int diagonal, Map<Integer, List<Integer>> map) {
            if (node == null) return;

            map.putIfAbsent(diagonal, new ArrayList<>());
            map.get(diagonal).add(node.data);

            diagonalTraversalHelper(node.left, diagonal + 1, map);
            diagonalTraversalHelper(node.right, diagonal, map);
        }

        private static class Pair {
            TreeNode node;
            int hd;

            Pair(TreeNode node, int hd) {
                this.node = node;
                this.hd = hd;
            }
        }
    }
}

