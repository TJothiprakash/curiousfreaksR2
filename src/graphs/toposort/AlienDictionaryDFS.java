package graphs.toposort;/*
✅ PROBLEM: Alien Dictionary Order (DFS Topological Sort)

A new alien language uses the English alphabet, but the order of letters is unknown.
You are given a list of words[] from the alien language’s dictionary, where the words
are claimed to be sorted lexicographically according to the language’s rules.

Your task is to determine the correct order of letters in this alien language based
on the given words. If valid, return any one correct character order. If invalid, return "".

*/

import java.util.*;

public class AlienDictionaryDFS {

    /*
    💡 INTUITION:

    - Each unique character is a node.
    - For each adjacent pair of words:
        - Find the first position where they differ → gives a directional edge.
        - E.g., "abc" vs "abd" → 'c' should come before 'd'.
    - Build a graph and use DFS to get a topological ordering.
    - During DFS:
        - Mark node as VISITING (1)
        - After visiting neighbors → mark as VISITED (2)
        - If you revisit a VISITING node → cycle detected (invalid case)
    */

    /*
    🧠 DRY RUN:
    Input: ["caa", "aaa", "aab"]

    Step 1: Compare "caa" vs "aaa" → 'c' != 'a' → edge: c → a
    Step 2: Compare "aaa" vs "aab" → 'a' == 'a', 'a' == 'a', 'a' != 'b' → edge: a → b

    Graph:
        c → a
        a → b

    Topological sort via DFS:
        Start DFS at c:
            visit c → a → b
            append b, a, c → reverse → "cab"

    Final order: "cab" (valid)
    */

    public static String findOrder(String[] words) {
        Map<Character, Set<Character>> graph = new HashMap<>();
        Map<Character, Integer> visited = new HashMap<>();
        StringBuilder result = new StringBuilder();

        System.out.println("Step 1: Initialize all unique characters in graph");
        for (String word : words) {
            for (char c : word.toCharArray()) {
                graph.putIfAbsent(c, new HashSet<>());
            }
        }

        System.out.println("Step 2: Building graph edges by comparing adjacent words");
        for (int i = 0; i < words.length - 1; i++) {
            String w1 = words[i], w2 = words[i + 1];
            if (w1.length() > w2.length() && w1.startsWith(w2)) {
                System.out.println("Invalid prefix order detected between: " + w1 + " and " + w2);
                return "";
            }

            for (int j = 0; j < Math.min(w1.length(), w2.length()); j++) {
                char c1 = w1.charAt(j), c2 = w2.charAt(j);
                if (c1 != c2) {
                    if (!graph.get(c1).contains(c2)) {
                        graph.get(c1).add(c2);
                        System.out.println("Edge Added: " + c1 + " -> " + c2);
                    }
                    break;
                }
            }
        }

        System.out.println("Step 3: Performing DFS Topological Sort");
        for (char node : graph.keySet()) {
            if (!visited.containsKey(node)) {
                System.out.println("Start DFS at node: " + node);
                if (!dfs(node, graph, visited, result)) {
                    System.out.println("Cycle detected starting at: " + node);
                    return "";
                }
            }
        }

        System.out.println("Topological Sort Result (Reversed Post-order): " + result.reverse());
        return result.toString();
    }

    private static boolean dfs(char node, Map<Character, Set<Character>> graph,
                               Map<Character, Integer> visited, StringBuilder result) {
        visited.put(node, 1); // 1 = VISITING
        System.out.println("Visiting: " + node);

        for (char neighbor : graph.get(node)) {
            if (!visited.containsKey(neighbor)) {
                System.out.println("→ DFS to neighbor: " + neighbor);
                if (!dfs(neighbor, graph, visited, result)) return false;
            } else if (visited.get(neighbor) == 1) {
                System.out.println("⚠️ Cycle detected via: " + neighbor);
                return false;
            }
        }

        visited.put(node, 2); // 2 = VISITED
        result.append(node);
        System.out.println("Added to result: " + node);
        return true;
    }

    // Optional: Validation function to check correctness of order
    public static boolean isValidOrder(String order, String[] words) {
        if (order.equals("")) return false;
        Map<Character, Integer> pos = new HashMap<>();
        for (int i = 0; i < order.length(); i++) {
            pos.put(order.charAt(i), i);
        }

        for (int i = 0; i < words.length - 1; i++) {
            String w1 = words[i], w2 = words[i + 1];
            int min = Math.min(w1.length(), w2.length());
            boolean valid = false;
            for (int j = 0; j < min; j++) {
                if (w1.charAt(j) != w2.charAt(j)) {
                    if (pos.get(w1.charAt(j)) > pos.get(w2.charAt(j))) return false;
                    valid = true;
                    break;
                }
            }
            if (!valid && w1.length() > w2.length()) return false;
        }

        return true;
    }

    public static void main(String[] args) {
        String[][] testCases = {
                {"baa", "abcd", "abca", "cab", "cad"},
                {"caa", "aaa", "aab"},
                {"ab", "cd", "ef", "ad"},
                {"abc", "ab"}
        };

        for (String[] words : testCases) {
            System.out.println("================================================");
            System.out.println("Words: " + Arrays.toString(words));
            String order = findOrder(words);
            if (order.equals("")) {
                System.out.println("→ Output: \"\" (Invalid ordering)");
            } else {
                System.out.println("→ Order: \"" + order + "\"");
                System.out.println("→ Valid? " + isValidOrder(order, words));
            }
            System.out.println("================================================\n");
        }
    }
}

/*
--------------------------------------------------------
⏱️ TIME COMPLEXITY:
- Graph Build: O(N * L), where N = number of words, L = average word length
- DFS Traversal: O(V + E), V = unique characters (≤ 26), E = edges

🧠 SPACE COMPLEXITY:
- Graph: O(V + E)
- Visited Map: O(V)
- Recursion Stack: O(V)
- Result StringBuilder: O(V)

--------------------------------------------------------
*/
