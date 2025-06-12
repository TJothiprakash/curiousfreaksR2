package backtracking;

import java.util.*;
/*

import java.util.*;
Given a dictionary of distinct words and an M x N board where every cell has one character. Find all possible words from the dictionary that can be formed by a sequence of adjacent characters on the board. We can move to any of 8 adjacent characters

Note: While forming a word we can move to any of the 8 adjacent cells. A cell can be used only once in one word.


Example 1:

Input:
N = 1
dictionary = {"CAT"}
R = 3, C = 3
board = {{C,A,P},{A,N,D},{T,I,E}}
Output:
CAT
Explanation:
C A P
A N D
T I E
Words we got is denoted using same color.
Example 2:

Input:
N = 4
dictionary = {"GEEKS","FOR","QUIZ","GO"}
R = 3, C = 3
board = {{G,I,Z},{U,E,K},{Q,S,E}}
Output:
GEEKS QUIZ
Explanation:
G I Z
U E K
Q S E
Words we got is denoted using same color.

Your task:
You dont need to read input or print anything. Your task is to complete the function wordBoggle() which takes the dictionary contaning N space-separated strings and R*C board as input parameters and returns a list of words that exist on the board in lexicographical order.


Expected Time Complexity: O(N*W + R*C^2)
Expected Auxiliary Space: O(N*W + R*C)


Constraints:
        1 ≤ N ≤ 15
        1 ≤ R, C ≤ 50
        1 ≤ length of Word ≤ 60
Each word can consist of both lowercase and uppercase letters.


✅ Approach Summary
Build a Trie from the given dictionary of words — for efficient prefix checking.

For each cell on the board, start DFS (Depth-First Search) if the character exists in the Trie.

In DFS:

Explore all 8 directions.

Use a visited[][] matrix to ensure each cell is used only once per word.

If the current path is a complete word in the Trie, add it to the result.

Use a Set to store words (to avoid duplicates).

Finally, return all found words in lexicographical order.
*/

public class WordBoggle {
    static class TrieNode {
        TrieNode[] children = new TrieNode[52];
        boolean isEnd = false;

        int getIndex(char c) {
            return Character.isUpperCase(c) ? c - 'A' : c - 'a' + 26;
        }

        boolean containsKey(char c) {
            return children[getIndex(c)] != null;
        }

        TrieNode get(char c) {
            return children[getIndex(c)];
        }

        void put(char c, TrieNode node) {
            children[getIndex(c)] = node;
        }
    }

    static TrieNode root = new TrieNode();

    static void insert(String word) {
        TrieNode node = root;
        for (char ch : word.toCharArray()) {
            if (!node.containsKey(ch)) {
                node.put(ch, new TrieNode());
            }
            node = node.get(ch);
        }
        node.isEnd = true;
    }

    static void dfs(char[][] board, int i, int j, boolean[][] visited, TrieNode node, StringBuilder path, Set<String> result) {
        char c = board[i][j];
        if (!node.containsKey(c)) return;

        node = node.get(c);
        path.append(c);
        if (node.isEnd) {
            result.add(path.toString());
        }

        visited[i][j] = true;

        // 8 directions
        int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};

        for (int d = 0; d < 8; d++) {
            int ni = i + dx[d], nj = j + dy[d];
            if (ni >= 0 && nj >= 0 && ni < board.length && nj < board[0].length && !visited[ni][nj]) {
                dfs(board, ni, nj, visited, node, path, result);
            }
        }

        visited[i][j] = false;
        path.deleteCharAt(path.length() - 1);
    }

    public String[] wordBoggle(String[] dictionary, char[][] board) {
        root = new TrieNode(); // reset root
        for (String word : dictionary) {
            insert(word);
        }

        int R = board.length, C = board[0].length;
        Set<String> result = new HashSet<>();
        boolean[][] visited = new boolean[R][C];

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                dfs(board, i, j, visited, root, new StringBuilder(), result);
            }
        }

        List<String> res = new ArrayList<>(result);
        Collections.sort(res); // lexicographical order
        return res.toArray(new String[0]);
    }

    public static void main(String[] args) {
        String[] dict = {"GEEKS", "FOR", "QUIZ", "GO"};
        char[][] board = {
                {'G', 'I', 'Z'},
                {'U', 'E', 'K'},
                {'Q', 'S', 'E'}
        };

        WordBoggle sol = new WordBoggle();
        System.out.println(Arrays.toString(sol.wordBoggle(dict, board)));

    }
}
