package slidingwindow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DistinctElementsinWindow {
    public static void main(String[] args) {
        int[] nums = {1, 2, 1, 3, 4, 2, 3};
        int k = 3;
        List<Integer> result = findDistinctElements(nums, k);
        System.out.println(result);
    }
    public static List<Integer> findDistinctElements(int[] nums, int k) {
        List<Integer> result = new ArrayList<>();
        Map<Integer, Integer> freqMap = new HashMap<>();

        // Initialize the frequency map for the first window
        for (int i = 0; i < k; i++) {
            freqMap.put(nums[i], freqMap.getOrDefault(nums[i], 0) + 1);
        }
        result.add(freqMap.size());

        // Slide the window
        for (int i = k; i < nums.length; i++) {
            // Remove the element going out of the window
            int elementToRemove = nums[i - k];
            freqMap.put(elementToRemove, freqMap.get(elementToRemove) - 1);
            if (freqMap.get(elementToRemove) == 0) {
                freqMap.remove(elementToRemove);
            }

            // Add the new element coming into the window
            int elementToAdd = nums[i];
            freqMap.put(elementToAdd, freqMap.getOrDefault(elementToAdd, 0) + 1);

            // Add the count of distinct elements to the result
            result.add(freqMap.size());
        }

        return result;
    }
}
