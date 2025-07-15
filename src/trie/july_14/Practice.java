package trie.july_14;

public class Practice {

}

class Node {
    Node[] child = new Node[26];
    boolean isEnd = false;

    public Node() {

    }
}

class Trie {
    private final Node root = new Node();

    public static void main(String[] args) {

    }

    public void insert(String s) {
        char[] input = s.toCharArray();
        Node curr = root;
        for (int i = 0; i < input.length; i++) {
            int index = input[i] - 'a';

            if (curr.child[index] == null) {
                curr.child[index] = new Node();

            }
            curr = curr.child[index];
        }
        curr.isEnd = true;
        return;
    }

    public boolean startsWith(String s) {

        Node curr = root;
        char[] input = s.toCharArray();
        for (char c : input) {
            int index = c - 'a';
            if (curr.child[index] == null) {
                return false;
            }
            curr = curr.child[index];
        }

        return true;
    }

    public boolean search(String s) {
        Node curr = root;
        char[] input = s.toCharArray();
        for (char c : input) {
            int index = c - 'a';
            if (curr.child[index] == null) {
                return false;
            }
            curr = curr.child[index];
        }
        return curr.isEnd;
    }
    public boolean delete(String word) {
        return deleteHelper(root, word, 0);
    }

    private boolean deleteHelper(Node curr, String word, int depth) {
        if (curr == null) return false;

        if (depth == word.length()) {
            if (!curr.isEnd) return false; // word not found
            curr.isEnd = false;
            return isEmpty(curr); // if no children, signal to delete this node
        }

        int index = word.charAt(depth) - 'a';
        Node child = curr.child[index];
        boolean shouldDeleteChild = deleteHelper(child, word, depth + 1);

        if (shouldDeleteChild) {
            curr.child[index] = null;
            return !curr.isEnd && isEmpty(curr);
        }

        return false;
    }

    private boolean isEmpty(Node node) {
        for (int i = 0; i < 26; i++) {
            if (node.child[i] != null) return false;
        }
        return true;
    }
    public void printAllWords() {
        printHelper(root, new StringBuilder());
    }

    private void printHelper(Node curr, StringBuilder path) {
        if (curr == null) return;

        if (curr.isEnd) {
            System.out.println(path.toString());
        }

        for (char c = 'a'; c <= 'z'; c++) {
            int idx = c - 'a';
            if (curr.child[idx] != null) {
                path.append(c);
                printHelper(curr.child[idx], path);
                path.deleteCharAt(path.length() - 1); // backtrack
            }
        }
    }

}