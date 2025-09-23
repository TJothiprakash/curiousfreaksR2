package practicesessions.sept_18.dsa;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LongestSubstringWithoutRepeatingCharacter {
    public int lengthOfLongestSubstring(String s) {

        Map<Character, Integer> characterIntegerMap = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            characterIntegerMap.put(s.charAt(i),
                    characterIntegerMap.getOrDefault(s.charAt(i), 0) + 1);

        }
        int freq = characterIntegerMap.size();
        Set<Character> set = new HashSet<>();

        int windowFreq = 0;
        // first window
        for (int i = 0; i < freq; i++) {
            if (!set.contains(s.charAt(i))) {
                set.add(s.charAt(i));
            }

        }

        if (set.size() == freq) return freq;

        for(int i = freq; i < s.length();i++){

        }
return 0;
    }
}
