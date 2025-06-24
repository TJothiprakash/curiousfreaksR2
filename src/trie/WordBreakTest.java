package trie;

import java.util.Arrays;
import java.util.List; /**
 * 🧪 Test Class
 */
public class WordBreakTest {
    public static void main(String[] args) {
        WordBreak sol = new WordBreak();

        List<String> dict = Arrays.asList(
                "i", "like", "sam", "sung", "samsung",
                "mobile", "ice", "cream", "icecream",
                "man", "go", "mango"
        );

        String A1 = "ilike";
        String A2 = "ilikesamsung";
        String A3 = "ilikemangokar";

        System.out.println(sol.wordBreak(dict.size(), dict, A1)); // Output: 1
        System.out.println(sol.wordBreak(dict.size(), dict, A2)); // Output: 1
        System.out.println(sol.wordBreak(dict.size(), dict, A3)); // Output: 0
    }
}
