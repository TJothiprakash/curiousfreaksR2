package trees.july_16;

import java.util.*;

class Node {
    int data;
    Node left;
    Node right;

    public Node(int data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }

}

public class Practice {
    private static String[] args;

    public static void main(String[] args) {

    }

    //    preorder
    public void preOrder(Node root) {
        if (root == null) {
            return;
        }
        System.out.println(root.data);
        preOrder(root.left);
        preOrder(root.right);
    }

    //    preorder
    public void postOrder(Node root) {
        if (root == null) {
            return;
        }
        preOrder(root.left);
        preOrder(root.right);
        System.out.println(root.data);
    }

    //    preorder
    public void inOrder(Node root) {
        if (root == null) {
            return;
        }
        preOrder(root.left);
        System.out.println(root.data);
        preOrder(root.right);
    }

    //     levelorder
    public void levelOrder(Node root) {
        if (root == null) return;
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {

            Node temp = queue.poll();
            System.out.println(temp.data);
            if (temp.left != null) {
                queue.add(temp.left);
            }
            if (temp.right != null) {
                queue.add(temp.right);
            }

        }
    }

    //boundary traversals
    public void boundaryTraversal(Node root) {
        if (root == null) return;

        if (!isLeaf(root)) System.out.println(root.data);

        printLeftBoundary(root.left);

        printLeaves(root.left);
        printLeaves(root.right);

        printRightBoundary(root.right);

    }

    private void printRightBoundary(Node node) {
        if (node == null) return;
        Stack<Node> stack = new Stack<>();
        while (node != null) {
            if (!isLeaf(node)) stack.push(node);

            if (node.right != null) {
                node = node.right;
            } else {
                node = node.left;
            }

        }
        while (!stack.isEmpty()) {
            System.out.println(stack.pop().data);
        }
    }

    private void printLeaves(Node node) {
        if (node == null) return;

        if (isLeaf(node)) {
            System.out.println(node.data);
            return;
        }
        printLeaves(node.left);
        printLeaves(node.right);
    }

    private void printLeftBoundary(Node node) {
        while (node != null) {
            if (!isLeaf(node)) System.out.println(node.data);

            if (node.left != null) {
                node = node.left;
            } else {
                node = node.right;
            }
        }
    }


    private boolean isLeaf(Node root) {
        return root.left == null && root.right == null;
    }

    //    vertical traversal
    public void verticalTraversal(Node root) {
        if (root == null) return;
        TreeMap<Integer, List<Integer>> treeMap = new TreeMap<>();
        Queue<Pair> queue = new LinkedList<>();
        queue.offer(new Pair(root, 0));
        while (!queue.isEmpty()) {
            Pair current = queue.poll();
            Node node = current.node;
            int hd = current.hd;
            treeMap.computeIfAbsent(hd, k -> new ArrayList<>()).add(node.data);

            if (node.left != null) {
                queue.offer(new Pair(node.left, hd - 1));
            }
            if (node.right != null) {
                queue.offer(new Pair(node.right, hd + 1));
            }
        }

        for (Map.Entry<Integer, List<Integer>> entry : treeMap.entrySet()) {
            for (int val : entry.getValue()) {
                System.out.print(val + " ");
            }
            System.out.println(); // one vertical per line (optional)
        }

    }

    public void topView(Node root) {
        TreeMap<Integer, Integer> treeMap = new TreeMap<>();
        Queue<Pair> queue = new LinkedList<>();
        queue.add(new Pair(root, 0));
        while (!queue.isEmpty()) {
            Pair current = queue.poll();
            Node node = current.node;
            int hd = current.hd;

            if (!treeMap.containsKey(hd)) {
                treeMap.put(hd, node.data);
            }

            if (node.left != null) {
                queue.offer(new Pair(node, hd - 1));

            }
            if (node.right != null) {
                queue.offer(new Pair(node, hd + 1));

            }

        }
        System.out.println(treeMap.values());
    }


    public void bottomView(Node root) {
        TreeMap<Integer, Integer> treeMap = new TreeMap<>();
        Queue<Pair> queue = new LinkedList<>();
        queue.add(new Pair(root, 0));
        while (!queue.isEmpty()) {
            Pair current = queue.poll();
            Node node = current.node;
            int hd = current.hd;

//            if (!treeMap.containsKey(hd)) {
            treeMap.put(hd, node.data);
//            }

            if (node.left != null) {
                queue.offer(new Pair(node, hd - 1));

            }
            if (node.right != null) {
                queue.offer(new Pair(node, hd + 1));

            }

        }
        System.out.println(treeMap.values());
    }

    public void leftView(Node root) {
        if (root == null) return;
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            for (int i = 0; i < levelSize; i++) {
                Node temp = queue.poll();
                if (i == 0) {
                    System.out.print(temp.data + " ");
                }
                if (temp.left != null) {
                    queue.offer(temp.left);
                }
                if (temp.right != null) {
                    queue.offer(temp.right);
                }


            }
        }
    }

    public void rightView(Node root) {
        if (root == null) return;
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            for (int i = 0; i < levelSize; i++) {
                Node temp = queue.poll();
                if (i == levelSize - 1) {
                    System.out.print(temp.data + " ");
                }
                if (temp.left != null) {
                    queue.offer(temp.left);
                }
                if (temp.right != null) {
                    queue.offer(temp.right);
                }


            }
        }
    }

    public void diagnalTraversal(Node root ){
        if(root == null) return;

    }
}

class Pair {
    Node node;
    int hd;

    Pair(Node node, int data) {
        this.hd = data;
        this.node = node;

    }
}
