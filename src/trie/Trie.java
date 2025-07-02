package trie;

import org.jetbrains.annotations.NotNull;

class TrieNode {
    TrieNode[] children;
    boolean isEnd;

    public TrieNode() {
        children = new TrieNode[26]; // links for 'a' to 'z'
        isEnd = false;
    }
}

public class Trie {
    private final TrieNode root;

    public Trie() {
        root = new TrieNode(); // root is just an empty node
    }

    // INSERT a word into the Trie
    public void insert(@NotNull String word) {
        TrieNode curr = root;
        for (char ch : word.toCharArray()) {
            int idx = ch - 'a';
            if (curr.children[idx] == null) {
                curr.children[idx] = new TrieNode(); // create new node if missing
            }
            curr = curr.children[idx]; // move to the next node
        }
        curr.isEnd = true; // mark end of word
    }

    // SEARCH a full word in the Trie
    public boolean search(String word) {
        TrieNode curr = root;
        for (char ch : word.toCharArray()) {
            int idx = ch - 'a';
            if (curr.children[idx] == null) {
                return false; // path breaks, word not found
            }
            curr = curr.children[idx];
        }
        return curr.isEnd; // must be end of word
    }

    // CHECK if any word starts with given prefix
    public boolean startsWith(String prefix) {
        TrieNode curr = root;
        for (char ch : prefix.toCharArray()) {
            int idx = ch - 'a';
            if (curr.children[idx] == null) {
                return false; // prefix path breaks
            }
            curr = curr.children[idx];
        }
        return true; // all prefix chars matched
    }

    public boolean delete(String word) {
        return deleteHelper(root, word, 0);
    }

    private boolean deleteHelper(TrieNode curr, String word, int index) {
        if (curr == null) return false;

        if (index == word.length()) {
            // End of the word
            if (!curr.isEnd) return false; // word doesn't exist
            curr.isEnd = false;

            // If current node has no children, we can delete it
            return isEmpty(curr);
        }

        int idx = word.charAt(index) - 'a';
        TrieNode child = curr.children[idx];
        boolean shouldDeleteCurrentNode = deleteHelper(child, word, index + 1);

        if (shouldDeleteCurrentNode) {
            curr.children[idx] = null; // delete the link

            // Return true if no children and not end of another word
            return !curr.isEnd && isEmpty(curr);
        }

        return false;
    }

    // Helper: check if all children are null
    private boolean isEmpty(TrieNode node) {
        for (int i = 0; i < 26; i++) {
            if (node.children[i] != null) return false;
        }
        return true;
    }

}
