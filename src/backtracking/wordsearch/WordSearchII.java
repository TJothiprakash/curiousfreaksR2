package backtracking.wordsearch;
import java.util.*;
/*🔹 Problem: Word Search II

Given:

A 2D board of characters

A list/dictionary of words

Goal:

Find all words from the dictionary that can be formed in the board

You can move horizontally or vertically (up/down/left/right)

A cell cannot be reused in the same word

🔹 Intuition

Trie for dictionary:

Build a Trie from all words in the dictionary

This allows prefix pruning — if a path cannot form any word, we stop early

DFS + backtracking on board:

Start DFS from each cell

Explore all 4 directions

Track visited cells

Only proceed if the current path is a prefix in Trie

Collect words:

When a Trie node marks a word end → collect it

Continue DFS to find other words

Pattern takeaway: prefix pruning with Trie + grid DFS

🔹 Dry Run (Example)

Board:

A B C
D E F
G H I


Dictionary: ["ABE", "ABC", "ADG", "CFI", "AEI"]

Build Trie with all dictionary words

Start DFS at (0,0) 'A'

'A' → 'B' → 'E' forms "ABE" ✅ add to result

'A' → 'B' → 'C' forms "ABC" ✅ add to result

Start DFS at (1,0) 'D' → forms "ADG" ✅ add

Etc…

Prefix pruning avoids useless DFS paths (like "AF" → no word starts with "AF").*/
public class WordSearchII {

    static class TrieNode {
        Map<Character, TrieNode> children = new HashMap<>();
        String word = null; // store word at the end
    }

    public static void main(String[] args) {
        char[][] board = {
                {'A','B','C'},
                {'D','E','F'},
                {'G','H','I'}
        };
        String[] words = {"ABE", "ABC", "ADG", "CFI", "AEI"};

        List<String> found = findWords(board, words);
        System.out.println("Words found: " + found);
    }

    public static List<String> findWords(char[][] board, String[] words) {
        // 1. Build Trie
        TrieNode root = new TrieNode();
        for (String word : words) insertWord(root, word);

        int m = board.length, n = board[0].length;
        boolean[][] visited = new boolean[m][n];
        Set<String> result = new HashSet<>();

        // 2. DFS from each cell
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dfs(board, i, j, root, visited, result);
            }
        }

        return new ArrayList<>(result);
    }

    private static void insertWord(TrieNode root, String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            node = node.children.computeIfAbsent(c, k -> new TrieNode());
        }
        node.word = word; // mark end of word
    }

    private static void dfs(char[][] board, int i, int j, TrieNode node,
                            boolean[][] visited, Set<String> result) {
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length) return;
        if (visited[i][j]) return;

        char c = board[i][j];
        if (!node.children.containsKey(c)) return; // prefix not in Trie

        TrieNode nextNode = node.children.get(c);
        if (nextNode.word != null) {
            result.add(nextNode.word); // found a word
        }

        visited[i][j] = true;

        // explore 4 directions
        dfs(board, i+1, j, nextNode, visited, result);
        dfs(board, i-1, j, nextNode, visited, result);
        dfs(board, i, j+1, nextNode, visited, result);
        dfs(board, i, j-1, nextNode, visited, result);

        visited[i][j] = false; // backtrack
    }
}
/*🔹 Complexity

Time: O(M*N*4^L) worst-case (like single word DFS)

But Trie pruning reduces paths drastically

Space:

O(T) for Trie (T = total letters in dictionary)

O(M*N) visited array

O(foundWords * L) for results

This is the standard approach for Word Search II — you see how Trie + backtracking combine.*/