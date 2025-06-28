package graphs.shortestpath;
/*
✅ Question:
You're given a list of unique lowercase words (`wordList`) of the same length.
You are also given a `startWord` and a `targetWord`.

Each transformation step can change only **one character** at a time, and each resulting word **must be present in wordList**.

Find the **length** of the shortest transformation sequence from `startWord` to `targetWord`.

Return 0 if no such transformation is possible.

---

✅ Examples:

Input:
wordList = {"des","der","dfr","dgt","dfs"}
startWord = "der", targetWord = "dfs"
Output: 3
Explanation:
der -> dfr -> dfs

Input:
wordList = {"geek", "gefk"}
startWord = "gedk", targetWord = "geek"
Output: 2
Explanation:
gedk -> geek

Input:
wordList = {"poon", "plee", "same", "poie","plea","plie","poin"}
startWord = "toon", targetWord = "plea"
Output: 7
Explanation:
toon → poon → poin → poie → plie → plee → plea

---

✅ Intuition:

- Model the words as nodes in a graph.
- Two nodes (words) are connected if they differ by **only one character**.
- Use **BFS** starting from `startWord` to find the shortest path to `targetWord`.

---

✅ Dry Run:
start: "toon", target: "plea"
Step 1: toon → poon
Step 2: poon → poin
Step 3: poin → poie
Step 4: poie → plie
Step 5: plie → plee
Step 6: plee → plea → ✅ found

---

✅ Time Complexity:
O(N² * M) where:
- N = number of words in wordList
- M = word length

✅ Space Complexity:
O(N * M) — wordSet and visited map

---

✅ Constraints:
1 ≤ N ≤ 100
1 ≤ M ≤ 10
*/

import java.util.*;

public class WordLadder {

    public int wordLadderLength(String startWord, String targetWord, String[] wordList) {
        Set<String> wordSet = new HashSet<>(Arrays.asList(wordList));
        if (!wordSet.contains(targetWord)) return 0;

        Queue<Pair> queue = new LinkedList<>();
        queue.offer(new Pair(startWord, 1));

        Set<String> visited = new HashSet<>();
        visited.add(startWord);

        while (!queue.isEmpty()) {
            Pair curr = queue.poll();
            String word = curr.word;
            int level = curr.level;

            if (word.equals(targetWord)) {
                return level;
            }

            // Try changing every character in the word
            for (int i = 0; i < word.length(); i++) {
                char[] wordArray = word.toCharArray();
                for (char ch = 'a'; ch <= 'z'; ch++) {
                    char original = wordArray[i];
                    wordArray[i] = ch;
                    String nextWord = new String(wordArray);
                    if (wordSet.contains(nextWord) && !visited.contains(nextWord)) {
                        visited.add(nextWord);
                        queue.offer(new Pair(nextWord, level + 1));
                    }
                    wordArray[i] = original; // backtrack
                }
            }
        }

        return 0;
    }

    static class Pair {
        String word;
        int level;
        Pair(String word, int level) {
            this.word = word;
            this.level = level;
        }
    }
}
