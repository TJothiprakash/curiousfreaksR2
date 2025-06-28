package graphs.shortestpath;

import java.util.*;

public class WordLadderAllSequences {

    /*
     ✅ QUESTION
     Given two distinct words `startWord` and `targetWord`, and a list `wordList` of unique words of equal lengths,
     find all shortest transformation sequences from `startWord` to `targetWord`.

     Rules:
     - Only one letter can be changed at a time.
     - Each intermediate word must exist in the wordList.
     - Return a list of all sequences that are of the minimum possible length.
     - If no sequence exists, return an empty list.

     Example:
     Input:
     startWord = "der", targetWord = "dfs"
     wordList = {"des", "der", "dfr", "dgt", "dfs"}

     Output:
     [
       ["der", "des", "dfs"],
       ["der", "dfr", "dfs"]
     ]
    */

    /*
     💡 INTUITION
     - This is a BFS-based shortest path problem.
     - But instead of returning just the length, we collect all shortest transformation **paths**.
     - So we perform BFS to build levels and parent mapping.
     - Then use backtracking to reconstruct all sequences from `targetWord` to `startWord`.
    */

    public static List<List<String>> findSequences(String startWord, String targetWord, String[] wordListArray) {
        Set<String> wordList = new HashSet<>(Arrays.asList(wordListArray));
        List<List<String>> results = new ArrayList<>();

        if (!wordList.contains(targetWord)) return results;

        Map<String, List<String>> parentMap = new HashMap<>();
        Map<String, Integer> levelMap = new HashMap<>();

        Queue<String> queue = new LinkedList<>();
        queue.add(startWord);
        levelMap.put(startWord, 0);

        while (!queue.isEmpty()) {
            String word = queue.poll();
            int level = levelMap.get(word);

            for (int i = 0; i < word.length(); i++) {
                char[] chArr = word.toCharArray();
                for (char ch = 'a'; ch <= 'z'; ch++) {
                    chArr[i] = ch;
                    String nextWord = new String(chArr);

                    if (wordList.contains(nextWord)) {
                        if (!levelMap.containsKey(nextWord)) {
                            levelMap.put(nextWord, level + 1);
                            queue.add(nextWord);
                            parentMap.putIfAbsent(nextWord, new ArrayList<>());
                            parentMap.get(nextWord).add(word);
                        } else if (levelMap.get(nextWord) == level + 1) {
                            parentMap.get(nextWord).add(word);
                        }
                    }
                }
            }
        }

        if (!parentMap.containsKey(targetWord)) return results;

        LinkedList<String> path = new LinkedList<>();
        path.add(targetWord);
        backtrack(targetWord, startWord, parentMap, path, results);
        return results;
    }

    private static void backtrack(String word, String startWord, Map<String, List<String>> parentMap,
                                  LinkedList<String> path, List<List<String>> results) {
        if (word.equals(startWord)) {
            LinkedList<String> pathCopy = new LinkedList<>(path);
            Collections.reverse(pathCopy);
            results.add(new ArrayList<>(pathCopy));
            return;
        }

        if (!parentMap.containsKey(word)) return;

        for (String parent : parentMap.get(word)) {
            path.add(parent);
            backtrack(parent, startWord, parentMap, path, results);
            path.removeLast();
        }
    }

    /*
     🕰️ TIME & SPACE COMPLEXITY
     - Time: O(N × M × 26) for BFS + O(k^d) for backtracking, where:
       N = number of words, M = length of each word
       k = branching factor (avg number of parents), d = depth
     - Space: O(N × M) for maps and queue
    */

    public static void main(String[] args) {
        System.out.println("Test Case 1:");
        String[] wordList1 = {"des", "der", "dfr", "dgt", "dfs"};
        List<List<String>> result1 = findSequences("der", "dfs", wordList1);
        result1.forEach(System.out::println);

        System.out.println("\nTest Case 2:");
        String[] wordList2 = {"geek", "gefk"};
        List<List<String>> result2 = findSequences("gedk", "geek", wordList2);
        result2.forEach(System.out::println);

        System.out.println("\nTest Case 3:");
        String[] wordList3 = {"poon", "plee", "same", "poie", "plea", "plie", "poin"};
        List<List<String>> result3 = findSequences("toon", "plea", wordList3);
        result3.forEach(System.out::println);
    }
}

