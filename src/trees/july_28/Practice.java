package trees.july_28;

import java.lang.reflect.Array;
import java.util.*;

class Node {
    int data;
    Node right;
    Node left;

    public Node(int data) {
        this.data = data;
        right = null;
        left = null;
    }
}

public class Practice {
    public static void main(String[] args) {
        System.out.println("hello world !!!");
        Practice tree = new Practice();

        // Creating a sample tree
        //        1
        //       / \
        //      2   3
        //     / \
        //    4   5

        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);

//        System.out.println("Preorder:");
//        tree.preorder(root);
//
//        System.out.println("Inorder:");
//        tree.inorder(root);
//
//        System.out.println("Postorder:");
//        tree.postorder(root);
        tree.levelOrder(root, 6);

    }

//    tree traversals inorder, postorder, preorder


    public void preorder(Node root) {
        if (root == null) return;

        System.out.println(root.data);
        preorder(root.left);
        preorder(root.right);
    }

    public void postorder(Node root) {
        if (root == null) return;

        postorder(root.left);
        postorder(root.right);
        System.out.println(root.data);
    }

    public void inorder(Node root) {
        if (root == null) return;

        inorder(root.left);
        System.out.println(root.data);
        inorder(root.right);
    }

    public void levelOrder(Node root, int V) {
        Queue<Node> queue = new LinkedList<>();
        List<ArrayList<Integer>> levelOrder = new ArrayList<>();
        queue.add(root);

        while(!queue.isEmpty()){
            List<Integer> currLevel= new ArrayList<>();
            int levelSize = queue.size();
            for (int i = 0; i < levelSize; i++) {
                Node curr = queue.poll();
                currLevel.add(curr.data);
                if (curr.left != null) queue.add(curr.left);
                if (curr.right != null) queue.add(curr.right);
            }
            levelOrder.add(new ArrayList<>(currLevel));
        }
        System.out.println(levelOrder);

    }

    public List<Integer> boundaryTraversal(Node root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;
        if (!isLeaf(root)) result.add(root.data);
        addLeftBoundary(root.left, result);
        addLeaves(root, result);
        addRightBoundary(root.right, result);
        return result;
    }

    private boolean isLeaf(Node node) {
        return node.left == null && node.right == null;
    }

    private void addLeftBoundary(Node node, List<Integer> result) {
        while (node != null) {
            if (!isLeaf(node)) result.add(node.data);
            node = (node.left != null) ? node.left : node.right;
        }
    }

    private void addLeaves(Node node, List<Integer> result) {
        if (node == null) return;

        if (isLeaf(node)) {
            result.add(node.data);
            return;
        }

        addLeaves(node.left, result);
        addLeaves(node.right, result);
    }

    private void addRightBoundary(Node node, List<Integer> result) {
        List<Integer> temp = new ArrayList<>();
        while (node != null) {
            if (!isLeaf(node)) temp.add(node.data);
            node = (node.right != null) ? node.right : node.left;
        }
        Collections.reverse(temp);
        result.addAll(temp);
    }
}
