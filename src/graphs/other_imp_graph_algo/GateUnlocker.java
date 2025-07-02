package graphs.other_imp_graph_algo;

import org.jetbrains.annotations.NotNull;

import java.util.*;



/*
🎯 Problem:
Given a grid map of characters and a list of gate names (words), find all **distinct** gate names
that can be formed using 8-directionally adjacent characters. A cell may only be used once per word.

🧠 Intuition:
- For each word, search from every cell in the grid using DFS.
- Check if the word can be formed from that starting point.
- Use a visited matrix to avoid revisiting cells in a single path.
- Add only unique gate names to the result set.

🔍 Dry Run:
Input: gate_names = {"ZARA", "ZETH", "MYTH"}
Map:
A Z E
R Y T
M A H

Search each word:
- ZARA: Found
- ZETH: Found
- MYTH: Found

📤 Output: {"ZARA", "ZETH", "MYTH"}

📊 Time Complexity:
O(N * R * C * 8^W)
Where N = number of gate names, W = max word length, R = rows, C = cols

💾 Space Complexity:
O(W) recursion depth + O(R*C) visited matrix
*/

public class GateUnlocker {

    static int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
    static int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};

    public static boolean dfs(char[][] map, @NotNull String word, int idx, int x, int y, boolean[][] visited) {
        if (idx == word.length()) return true;

        int r = map.length, c = map[0].length;
        if (x < 0 || y < 0 || x >= r || y >= c) return false;
        if (visited[x][y] || map[x][y] != word.charAt(idx)) return false;

        visited[x][y] = true;

        for (int d = 0; d < 8; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];
            if (dfs(map, word, idx + 1, nx, ny, visited)) {
                visited[x][y] = false;
                return true;
            }
        }

        visited[x][y] = false;
        return false;
    }

    public static @NotNull List<String> openGates(@NotNull String[] gate_names, @NotNull char[]  [] map) {
        Set<String> result = new HashSet<>();
        Set<String> uniqueWords = new HashSet<>(Arrays.asList(gate_names));

        int r = map.length, c = map[0].length;

        for (String word : uniqueWords) {
            boolean found = false;
            for (int i = 0; i < r && !found; i++) {
                for (int j = 0; j < c && !found; j++) {
                    boolean[][] visited = new boolean[r][c];
                    if (dfs(map, word, 0, i, j, visited)) {
                        result.add(word);
                        found = true;
                    }
                }
            }
        }

        List<String> finalResult = new ArrayList<>(result);
        Collections.sort(finalResult);
        return finalResult;
    }

    // 🧪 Sample Test
    public static void main(String[] args) {
        String[] gates1 = {"ZARA", "ZETH", "MYTH"};
        char[][] map1 = {
                {'A', 'Z', 'E'},
                {'R', 'Y', 'T'},
                {'M', 'A', 'H'}
        };
        System.out.println("Output 1: " + openGates(gates1, map1)); // ["MYTH", "ZARA", "ZETH"]

        String[] gates2 = {"NYX", "ONYX", "VIRA", "KARA", "NYX"};
        char[][] map2 = {
                {'N', 'O', 'A'},
                {'P', 'Y', 'V'},
                {'K', 'R', 'X'}
        };
        System.out.println("Output 2: " + openGates(gates2, map2)); // ["NYX", "ONYX"]
    }
}
