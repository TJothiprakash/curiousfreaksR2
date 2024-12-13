package greedy;

/*You are given a string s. We want to partition the string into as many parts as possible so that each letter appears in at most one part.

Note that the partition is done so that after concatenating all the parts in order, the resultant string should be s.

Return a list of integers representing the size of these parts.



Example 1:

Input: s = "ababcbacadefegdehijhklij"
Output: [9,7,8]
Explanation:
The partition is "ababcbaca", "defegde", "hijhklij".
This is a partition so that each letter appears in at most one part.
A partition like "ababcbacadefegde", "hijhklij" is incorrect, because it splits s into less parts.
Example 2:

Input: s = "eccbbbbdec"
Output: [10]


Constraints:

1 <= s.length <= 500
s consists of lowercase English letters.
Approach:
Track the last occurrence of each character: We can use a map or array to track the last index at which each character appears in the string.

Iterate through the string: As we go through the string, we keep track of the farthest point (rightmost index) that we need to consider. This ensures that all the characters in the current partition are included before we start the next partition.

Partitioning logic:

Start at the first character and determine the last occurrence of that character.
Keep extending the partition until you reach the farthest index of any character encountered so far.
Once you reach this index, you finalize the current partition and start a new one.
Algorithm:
Create a map that stores the last occurrence of each character in the string.
Iterate through the string, and for each character, determine the farthest point you need to go.
If the current index reaches the farthest point, finalize the current partition and record its length.
Continue until you’ve processed the entire string.
Jav*/

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PartitionLabels {

    public static void main(String[] args) {
        PartitionLabels solution = new PartitionLabels();

        String s1 = "ababcbacadefegdehijhklij";
        System.out.println(solution.partitionLabels(s1));  // Output: [9, 7, 8]

        String s2 = "eccbbbbdec";
        System.out.println(solution.partitionLabels(s2));  // Output: [10]
    }

    public List<Integer> partitionLabels(String s) {
        Map<Character, Integer> lastIndex = new HashMap<>();
        List<Integer> result = new ArrayList<>();

        // Step 1: Record the last occurrence of each character
        for (int i = 0; i < s.length(); i++) {
            lastIndex.put(s.charAt(i), i);
        }

        int start = 0;
        int end = 0;

        // Step 2: Iterate through the string and find partitions
        for (int i = 0; i < s.length(); i++) {
            // Update the end to be the farthest last index of the current character
            end = Math.max(end, lastIndex.get(s.charAt(i)));

            // If we reached the end of a partition
            if (i == end) {
                result.add(i - start + 1);  // Add the size of the partition
                start = i + 1;  // Start a new partition
            }
        }

        return result;
    }
}
